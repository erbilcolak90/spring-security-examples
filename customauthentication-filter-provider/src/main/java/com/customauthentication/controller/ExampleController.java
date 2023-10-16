package com.customauthentication.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class ExampleController {

    @GetMapping("/soundCheck")
    public String soundCheck(){
        return "sound-check 1-2";
    }
}
