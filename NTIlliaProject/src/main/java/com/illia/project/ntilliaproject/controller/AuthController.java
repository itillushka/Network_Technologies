package com.illia.project.ntilliaproject.controller;

import com.illia.project.ntilliaproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    public void SignIn(){

    }
    /*
    @PostMapping("/signup")
    public ResponseEntity<CreateUserResponseDto> signUp(@RequestBody CreateUserDto user){
        var newUser = userService.create(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

     */

}
