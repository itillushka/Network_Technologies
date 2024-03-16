package com.illia.project.ntilliaproject.service;

import com.illia.project.ntilliaproject.controller.dto.SignInDto;
import com.illia.project.ntilliaproject.controller.dto.SignInResponseDto;
import com.illia.project.ntilliaproject.controller.dto.user.CreateUserDto;
import com.illia.project.ntilliaproject.controller.dto.user.CreateUserResponseDto;
import com.illia.project.ntilliaproject.controller.dto.user.GetUserDto;
import com.illia.project.ntilliaproject.infrastructure.entity.BookEntity;
import com.illia.project.ntilliaproject.infrastructure.entity.UserEntity;
import com.illia.project.ntilliaproject.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    // dependency injection
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }
    public GetUserDto getOne(long id){
        var userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new GetUserDto(
                userEntity.getUserID(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getRole(),
                userEntity.getEmail(),
                userEntity.getName());

    }
    public List<GetUserDto> getAll(){
        var users = userRepository.findAll();
        return users.stream().map((user)-> new GetUserDto(
                user.getUserID(),
                user.getUsername(),
                user.getPassword(),
                user.getRole(),
                user.getEmail(),
                user.getName())).collect(Collectors.toList());
    }

    public CreateUserResponseDto create(CreateUserDto user){
        var userEntity = new UserEntity();
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setRole(user.getRole());
        var newUser= userRepository.save(userEntity);
        return new CreateUserResponseDto(newUser.getUserID(),
                newUser.getUsername(),
                newUser.getPassword(),
                newUser.getRole(),
                newUser.getEmail(),
                newUser.getName());
    }

    public void delete(long id){
        if(!userRepository.existsById(id)){
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

    public SignInResponseDto signin(SignInDto dto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        UserEntity user = userRepository.findByUsername(dto.getUsername()).orElseThrow();
        var token = jwtService.generateToken(user);
        return new SignInResponseDto(token);

    }

    public UserEntity getOneByUsername(String username){
        return userRepository.findByUsername(username).orElseThrow();
    }
}
