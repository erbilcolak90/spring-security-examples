package com.examples.springsecurityexamples.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @GetMapping("/soundCheck")
    public String soundCheck(){
        return "sound check 1-2";
    }

    @PostMapping("/hello")
    public String hello(){
        return "hello to you";
    }
}
