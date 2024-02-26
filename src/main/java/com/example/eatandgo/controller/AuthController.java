package com.example.eatandgo.controller;

import com.example.eatandgo.dto.request.LoginRequest;
import com.example.eatandgo.dto.request.RegisterUserRequest;
import com.example.eatandgo.dto.response.ApiResponse;
import com.example.eatandgo.dto.response.LoginResponse;
import com.example.eatandgo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
   private final CustomerService userService;
    @PostMapping("/sign-up                                                                                                                                                              ")
    public ResponseEntity<ApiResponse<?>> signup (@RequestBody RegisterUserRequest registerUserRequest){
        System.out.println("i am in ################################");
        ApiResponse<?> response = userService.registerUser(registerUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login (@RequestBody LoginRequest loginRequest){
       ApiResponse <LoginResponse> response = userService.login(loginRequest);
       return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
