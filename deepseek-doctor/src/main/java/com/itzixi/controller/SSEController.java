package com.itzixi.controller;

import com.itzixi.utils.SSEMsgType;
import com.itzixi.utils.SSEServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author: action
 * @create: 2025/3/10 16:15
 **/
@Slf4j
@RestController
@RequestMapping("sse")
public class SSEController {
    /**
     * 连接sse服务的接口
     * @param userId
     * @return
     */
    @GetMapping(path = "connect",produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public SseEmitter connect(@RequestParam String userId){
        return SSEServer.connect(userId);
    }

    /**
     * 发送单条消息给SSE的客户端
     * @param userId
     * @param message
     * @return Object
     */
    @GetMapping("sendMessage")
    public Object sendMessage(@RequestParam String userId, @RequestParam String message) {
        SSEServer.sendMessage(userId, message, SSEMsgType.MESSAGE);
        return "OK";
    }

    /**
     * 发送消息给所有客户端用户
     * @param message
     * @return Object
     */
    @GetMapping("sendMessageAll")
    public Object sendMessageAll(@RequestParam String message) {
        SSEServer.sendMessageToAllUsers(message);
        return "OK";
    }


}
