package com.securityexamples.controller;

import com.securityexamples.entities.User;
import com.securityexamples.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final CustomUserDetailsService customUserDetailsService;

    @PostMapping("/createUser")
    public void createUser(@RequestBody User user){
        customUserDetailsService.createUser(user);
    }
}
