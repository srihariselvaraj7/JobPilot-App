package com.srihari.jobpilot.exception;

public class UserPreferenceAlreadyExistsException extends RuntimeException{
    public UserPreferenceAlreadyExistsException(String errorMessage){
        super(errorMessage);
    }
}
