package com.itzixi.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * @author: action
 * @create: 2025/3/10 14:51
 **/
@Slf4j
public class SSEServer {

    /**
     * 使用map对象，关联用户id和sse的服务连接
     * 进阶提问1：SseEmitter 能不能放在Redis中和userId进行关联？
     * 进阶提问2：SseEmitter 如何在集群SpringBoot中存在
     */
    private static Map<String,SseEmitter> sseClients = new ConcurrentHashMap<>();

    /**
     * 用于统计当前总在线人数
     */
    private static AtomicInteger onlineCounts = new AtomicInteger(0);

    public static SseEmitter connect(String userId){
        // 设置超时时间，0代表永不过期；默认30秒，超时未完成任务则会抛出异常
        SseEmitter sseEmitter = new SseEmitter(0L);

        // 注册SSE的回调方法
        sseEmitter.onCompletion(completionCallback(userId));
        sseEmitter.onError(errorCallback(userId));
        sseEmitter.onTimeout(timeoutCallback(userId));

        sseClients.put(userId,sseEmitter);
        log.info("当前创建新的SSE连接，用户ID为:{}",userId);

        onlineCounts.getAndIncrement();

        return sseEmitter;
    }

    /**
     * SSE连接完成后的回调方法（关闭连接的时候调用）
     * @param userId
     * @return
     */
    private static Runnable completionCallback(String userId) {
        return () -> {
            log.info("SSE连接完成并结束，用户ID为: {}", userId);
            removeConnection(userId);
        };
    }

    /**
     * SSE连接超时的时候进行调用
     * @param userId
     * @return
     */
    private static Runnable timeoutCallback(String userId) {
        return () -> {
            log.info("SSE连接超时，用户ID为: {}", userId);
            removeConnection(userId);
        };
    }

    /**
     * SSE连接发生错误的时候进行调用
     * @param userId
     * @return
     */
    private static Consumer<Throwable> errorCallback(String userId) {
        return Throwable -> {
            log.info("SSE连接发生错误，用户ID为: {}", userId);
            removeConnection(userId);
        };
    }

    /**
     * 从整个SSE服务中移除用户连接
     * @param userId
     */
    public static void removeConnection(String userId) {
        sseClients.remove(userId);
        log.info("SSE连接被移除，移除的用户ID为: {}", userId);
        onlineCounts.getAndDecrement();
    }
}
