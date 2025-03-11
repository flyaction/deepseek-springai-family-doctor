package com.itzixi.service.impl;

import com.itzixi.service.OllamaService;
import com.itzixi.utils.SSEMsgType;
import com.itzixi.utils.SSEServer;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: action
 * @create: 2025/3/10 11:15
 **/
@Service
@Slf4j
public class OllamaServiceImpl implements OllamaService {

    @Resource
    private OllamaChatClient ollamaChatClient;

    @Override
    public Object aiOllamaChat(String msg) {
        //同步调用deepseek，当前页面会卡住，直到获得所有的数据才会返回给页面
        return ollamaChatClient.call(msg);
    }

    @Override
    public Flux<ChatResponse> aiOllamaStream1(String msg) {
        Prompt prompt = new Prompt(new UserMessage(msg));
        Flux<ChatResponse> streamResponse = ollamaChatClient.stream(prompt);
        return streamResponse;
    }

    @Override
    public List<String> aiOllamaStream2(String msg) {
        Prompt prompt = new Prompt(new UserMessage(msg));
        Flux<ChatResponse> streamResponse = ollamaChatClient.stream(prompt);
        List<String> list = streamResponse.toStream().map(chatResponse -> {
            String content = chatResponse.getResult().getOutput().getContent();
            log.info(content);
            return content;
        }).collect(Collectors.toList());
        return list;
    }

    @Override
    public void doDoctorStreamV3(String userName, String message) {

        Prompt prompt = new Prompt(new UserMessage(message));
        Flux<ChatResponse> streamResponse = ollamaChatClient.stream(prompt);
        List<String> list = streamResponse.toStream().map(chatResponse -> {
            String content = chatResponse.getResult().getOutput().getContent();

            SSEServer.sendMessage(userName, content, SSEMsgType.ADD);

            log.info(content);
            return content;
        }).collect(Collectors.toList());

        SSEServer.sendMessage(userName, "GG", SSEMsgType.FINISH);

    }
}
