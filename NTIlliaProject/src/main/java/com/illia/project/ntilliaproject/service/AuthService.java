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
import com.illia.project.ntilliaproject.service.error.UserAlreadyExistsException;
import com.illia.project.ntilliaproject.service.error.WrongPasswordException;
import com.illia.project.ntilliaproject.service.error.WrongRegisterDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final JwtService jwtService;

    // Define the valid roles
    private static final List<UserRole> VALID_ROLES = Arrays.asList(UserRole.ROLE_ADMIN, UserRole.ROLE_READER);

    @Autowired
    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, AuthRepository authRepository, JwtService jwtService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.authRepository = authRepository;
        this.jwtService = jwtService;
    }

    @Transactional
    public RegisterResponseDto register(RegisterDto dto){

        Optional<AuthEntity> existingAuth = authRepository.findByUsername(dto.getUsername());

        if(existingAuth.isPresent()){
            throw UserAlreadyExistsException.create(dto.getUsername());
        }

        if(!VALID_ROLES.contains(dto.getRole())){
            throw WrongRegisterDataException.create();
        }


        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(dto.getEmail());
        userRepository.save(userEntity);

        AuthEntity authEntity = new AuthEntity();
        String hashedPassword = passwordEncoder.encode(dto.getPassword());
        authEntity.setUsername(dto.getUsername());
        authEntity.setPassword(hashedPassword);
        authEntity.setRole(dto.getRole());
        authEntity.setUser(userEntity);

        authRepository.save(authEntity);

        return new RegisterResponseDto(authEntity.getUsername(), authEntity.getRole(), userEntity.getUserID());
    }

    public LoginResponseDto login(LoginDto dto){
        AuthEntity authEntity = authRepository.findByUsername(dto.getUsername()).orElseThrow(RuntimeException::new);

        if(!passwordEncoder.matches(dto.getPassword(), authEntity.getPassword())){
            throw WrongPasswordException.create();
        }

        String token = jwtService.generateToken(authEntity);



        return new LoginResponseDto(token);
    }
}
