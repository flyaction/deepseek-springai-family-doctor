package com.itzixi.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: action
 * @create: 2025/3/7 08:15
 **/

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

}
