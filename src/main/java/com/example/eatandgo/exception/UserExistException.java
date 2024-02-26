package com.example.eatandgo.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Setter
@Getter
public class UserExistException extends RuntimeException{
    private String message;
    private HttpStatus httpStatus;

    public UserExistException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
