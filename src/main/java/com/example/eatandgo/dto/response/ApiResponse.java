package com.example.eatandgo.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Builder
public class ApiResponse<T> {

    private String message;
    private String status;
    private HttpStatus httpStatus;
    private T data;

    public ApiResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public ApiResponse(String message) {
        this.message = message;
    }

    public ApiResponse(String message, String status, HttpStatus httpStatus) {
        this.message = message;
        this.status = status;
        this.httpStatus = httpStatus;
    }

    public ApiResponse(String message, String status, HttpStatus httpStatus, T data) {
        this.message = message;
        this.status = status;
        this.httpStatus = httpStatus;
        this.data = data;
    }
    public ApiResponse(Map<String, String> map) {
    }
}
