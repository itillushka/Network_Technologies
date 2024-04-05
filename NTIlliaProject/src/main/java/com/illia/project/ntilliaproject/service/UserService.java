package com.illia.project.ntilliaproject.service;


import com.illia.project.ntilliaproject.controller.dto.user.GetUserDto;
import com.illia.project.ntilliaproject.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    // dependency injection
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public GetUserDto getOne(long id){
        var userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new GetUserDto(
                userEntity.getUserID(),
                userEntity.getEmail(),
                userEntity.getName());

    }
    public List<GetUserDto> getAll(){
        var users = userRepository.findAll();
        return users.stream().map((user)-> new GetUserDto(
                user.getUserID(),
                user.getEmail(),
                user.getName())).collect(Collectors.toList());
    }


    public void delete(long id){
        if(!userRepository.existsById(id)){
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

}
