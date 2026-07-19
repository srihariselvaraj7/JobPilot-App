package com.srihari.jobpilot.exception;

public class JobNotFoundException extends RuntimeException{

    public JobNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
