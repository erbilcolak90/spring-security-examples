package com.securityexamples.authorization;

import org.springframework.stereotype.Component;

@Component
public class ExampleEndpointAuthorizationManager {

    public boolean authorize(RoleType roleType, String methodName){
        /*
        there is some logic about client role check
        for example using user-role table, we can check the user-role name with the userId
        then check the role name with enum or String.
         */
        return roleType == RoleType.ADMIN;
    }

    enum RoleType{
        ADMIN,
        USER,
        CUSTOM_USER
    }
}
