package com.basic_authentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @GetMapping("/soundCheck")
    public String soundCheck(){
        return "sound-check 1-2";
    }
}
