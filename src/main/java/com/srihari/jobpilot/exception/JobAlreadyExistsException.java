package com.srihari.jobpilot.exception;

public class JobAlreadyExistsException extends RuntimeException{
    public JobAlreadyExistsException(String errorMessage){
        super(errorMessage);
    }
}
