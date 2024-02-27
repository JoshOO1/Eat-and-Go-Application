package com.example.eatandgo.service.impl;

import com.example.eatandgo.dto.request.LoginRequest;
import com.example.eatandgo.dto.response.ApiResponse;
import com.example.eatandgo.dto.response.LoginResponse;
import com.example.eatandgo.enums.Role;
import com.example.eatandgo.exception.CustomException;
import com.example.eatandgo.model.Customer;
import com.example.eatandgo.model.UserBaseEntity;
import com.example.eatandgo.repository.CustomerRepository;
import com.example.eatandgo.security.JwtService;
import com.example.eatandgo.service.CustomerService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Builder
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public ApiResponse<LoginResponse> login(LoginRequest loginRequest) {
        log.info("Login Request");

        Authentication authenticationUser;
        try {
            authenticationUser = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            log.info("Authenticated the user using the AuthenticationManager");
        } catch (DisabledException e) {
            return Stream.of(
                    new AbstractMap.SimpleEntry<>("message", "Disabled exception occurred"),
                    new AbstractMap.SimpleEntry<>("status", "failure"),
                    new AbstractMap.SimpleEntry<>("httpStatus", HttpStatus.BAD_REQUEST)).collect(Collectors.collectingAndThen(
                    Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue),
                    map -> new ApiResponse<>((Map<String, String>) map)
            ));
        } catch (BadCredentialsException e) {
            throw new CustomException( HttpStatus.BAD_REQUEST,"Invalid email or password");
        }

        log.info("tell securityContext that this is in the context");
        SecurityContextHolder.getContext().setAuthentication(authenticationUser);

        log.info("Retrieve the user from repository");
        Customer customer = (Customer) customerRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() ->
                new CustomException(HttpStatus.BAD_REQUEST,"User not found"));

        customer.setLastLogin(LocalDateTime.now());
        log.info("Last login date updated");

        UserBaseEntity user1 = customerRepository.save(customer);
        log.info("User saved back to database");

        //Generate and send token
        String tokenGenerated = "Bearer" + jwtService.generateToken(authenticationUser, user1.getRole());
        log.info("Jwt token generated for the User " + tokenGenerated);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(tokenGenerated);

        return new ApiResponse<>("Successful", "00", HttpStatus.OK, loginResponse);
    }
}
