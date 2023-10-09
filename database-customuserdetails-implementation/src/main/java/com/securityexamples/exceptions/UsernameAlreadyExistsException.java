package com.securityexamples.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException{

    public UsernameAlreadyExistsException(){
        super("Username already exists");
    }
}
