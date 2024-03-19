package com.illia.project.ntilliaproject.service;

import com.illia.project.ntilliaproject.commonTypes.UserRole;
import com.illia.project.ntilliaproject.controller.dto.LoginDto;
import com.illia.project.ntilliaproject.controller.dto.LoginResponseDto;
import com.illia.project.ntilliaproject.controller.dto.RegisterDto;
import com.illia.project.ntilliaproject.controller.dto.RegisterResponseDto;
import com.illia.project.ntilliaproject.infrastructure.entity.AuthEntity;
import com.illia.project.ntilliaproject.infrastructure.entity.UserEntity;
import com.illia.project.ntilliaproject.infrastructure.repository.AuthRepository;
import com.illia.project.ntilliaproject.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final AuthRepository authRepository;

    private final JwtService jwtService;

    @Autowired
    public AuthService(UserRepository userRepository, AuthRepository authRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.authRepository = authRepository;
        this.jwtService = jwtService;
    }

    public RegisterResponseDto register(RegisterDto dto){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(dto.getEmail());
        UserEntity newUser = userRepository.save(userEntity);

        AuthEntity authEntity = new AuthEntity();
        authEntity.setUsername(dto.getUsername());
        authEntity.setPassword(dto.getPassword());
        authEntity.setRole(dto.getRole());
        authEntity.setUser(newUser);

        AuthEntity newAuth = authRepository.save(authEntity);

        return new RegisterResponseDto(newAuth.getUsername(), newAuth.getRole());
    }

    public LoginResponseDto login(LoginDto dto){
        AuthEntity authEntity = authRepository.findByUsername(dto.getUsername()).orElseThrow(RuntimeException::new);

        if(!authEntity.getPassword().equals(dto.getPassword())){
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }

        String token = jwtService.generateToken(authEntity);

        UserRole userRole = jwtService.extractRole(token);

        return new LoginResponseDto(userRole.name());
    }
}
