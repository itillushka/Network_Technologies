package com.illia.project.ntilliaproject.controller;

import com.illia.project.ntilliaproject.controller.dto.LoginDto;
import com.illia.project.ntilliaproject.controller.dto.LoginResponseDto;
import com.illia.project.ntilliaproject.controller.dto.RegisterDto;
import com.illia.project.ntilliaproject.controller.dto.RegisterResponseDto;
import com.illia.project.ntilliaproject.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the controller class for authentication.
 * It handles HTTP requests related to authentication.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    /**
     * Constructor for the AuthController class.
     * @param authService the service that this controller will use to process authentication
     */
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Method to register a new user.
     * @param requestBody the DTO containing the user's registration information
     * @return RegisterResponseDto the DTO containing the registered user's information
     */
    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterDto requestBody){

        RegisterResponseDto dto = authService.register(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    /**
     * Method to log in a user.
     * @param requestBody the DTO containing the user's login information
     * @return LoginResponseDto the DTO containing the logged-in user's information
     */
    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto requestBody){
        LoginResponseDto dto = authService.login(requestBody);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }



}
