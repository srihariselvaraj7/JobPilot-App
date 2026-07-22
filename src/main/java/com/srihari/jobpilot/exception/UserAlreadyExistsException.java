package com.srihari.jobpilot.exception;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String errorMessage){
        super(errorMessage);
    }
}
