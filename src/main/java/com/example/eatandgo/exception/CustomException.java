package com.example.eatandgo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class CustomException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;

    public CustomException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public CustomException(String message) {
        this.message = message;
    }
}
