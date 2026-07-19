package com.srihari.jobpilot.exception;

import com.srihari.jobpilot.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleJobNotFoundException(
            JobNotFoundException exception, WebRequest webRequest){
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
