package com.example.eatandgo.service;

import com.example.eatandgo.dto.request.LoginRequest;
import com.example.eatandgo.dto.request.RegisterUserRequest;
import com.example.eatandgo.dto.response.ApiResponse;
import com.example.eatandgo.dto.response.LoginResponse;

public interface CustomerService {

    ApiResponse<?> registerUser(RegisterUserRequest registerUserRequest);

    ApiResponse<LoginResponse> login(LoginRequest loginRequest);
}
