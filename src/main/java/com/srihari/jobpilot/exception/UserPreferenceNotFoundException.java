package com.srihari.jobpilot.exception;

public class UserPreferenceNotFoundException extends RuntimeException{

    public UserPreferenceNotFoundException(String message){
        super(message);
    }
}
