package com.itzixi.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: action
 * @create: 2025/3/7 08:15
 **/
@Slf4j
@RestController
@RequestMapping("ollama")
public class OllamaController {

    @Resource
    private OllamaChatClient ollamaChatClient;

    @GetMapping("ai/chat")
    public Object aiOllamaChat(@RequestParam String msg){
        //同步调用deepseek，当前页面会卡住，直到获得所有的数据才会返回给页面
        return ollamaChatClient.call(msg);
    }

    @GetMapping("ai/stream1")
    public Flux<ChatResponse> aiOllamaStream1(@RequestParam String msg){
        Prompt prompt = new Prompt(new UserMessage(msg));
        Flux<ChatResponse> streamResponse = ollamaChatClient.stream(prompt);
        return streamResponse;
    }

    @GetMapping("ai/stream2")
    public List<String> aiOllamaStream2(@RequestParam String msg){
        Prompt prompt = new Prompt(new UserMessage(msg));
        Flux<ChatResponse> streamResponse = ollamaChatClient.stream(prompt);
        List<String> list = streamResponse.toStream().map(chatResponse -> {
            String content = chatResponse.getResult().getOutput().getContent();
            log.info(content);
            return content;
        }).collect(Collectors.toList());

        return list;
    }

}
