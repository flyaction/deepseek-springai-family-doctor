package com.itzixi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: action
 * @create: 2025/3/7 08:15
 **/

@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping("world")
    public Object helloWorld(){

        return "Hello Action";
    }

}
