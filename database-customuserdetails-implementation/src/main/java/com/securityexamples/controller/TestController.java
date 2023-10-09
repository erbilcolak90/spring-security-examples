package com.securityexamples.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    /*
    After the add @EnableMethodSecurity annotation top on SecurityConfig you can use @PreAuthorize annotation for authorization.

     */

    @GetMapping("/test/{testString}")
    @PreAuthorize("@exampleEndpointAuthorizationManager.authorize(#ExampleEndPointAuthorizationManager.#RoleType.#ADMIN, #testString)")
    public String test(@PathVariable String testString){
        return "Test";
    }

    // authorization has been added to provide access to this endpoint for just READ role.
    @GetMapping("/demo")
    @PreAuthorize("hasAuthority('READ')")
    public String demo(){
        return "demo";
    }
}
