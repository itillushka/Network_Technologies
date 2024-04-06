package com.illia.project.ntilliaproject.service;


import com.illia.project.ntilliaproject.controller.dto.user.GetUserDto;
import com.illia.project.ntilliaproject.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This is the controller class for book details.
 * It handles HTTP requests related to book details.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    // dependency injection
    /**
     * Constructor for the UserService class.
     * @param userRepository the repository that this service will use to interact with the database
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Method to get a single user by their ID.
     * @param id the ID of the user
     * @return GetUserDto the DTO containing the user details
     */
    public GetUserDto getOne(long id){
        var userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return new GetUserDto(
                userEntity.getUserID(),
                userEntity.getEmail(),
                userEntity.getName());

    }

    /**
     * Method to get all users.
     * @return List<GetUserDto> the list of DTOs containing the user details
     */
    public List<GetUserDto> getAll(){
        var users = userRepository.findAll();
        return users.stream().map((user)-> new GetUserDto(
                user.getUserID(),
                user.getEmail(),
                user.getName())).collect(Collectors.toList());
    }

    /**
     * Method to delete a user by their ID.
     * @param id the ID of the user
     */
    public void delete(long id){
        if(!userRepository.existsById(id)){
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }

}
