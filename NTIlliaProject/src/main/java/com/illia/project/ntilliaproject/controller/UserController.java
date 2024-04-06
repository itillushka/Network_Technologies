package com.illia.project.ntilliaproject.controller;

import com.illia.project.ntilliaproject.controller.dto.user.GetUserDto;
import com.illia.project.ntilliaproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This is the controller class for users.
 * It handles HTTP requests related to users.
 */
@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('ADMIN')")
public class UserController {
    private final UserService userService;

    /**
     * Constructor for the UserController class.
     * @param userService the service that this controller will use to interact with the database
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * Method to get all users.
     * @return List<GetUserDto> the list of DTOs containing the users
     */
    @GetMapping
    public List<GetUserDto> getAllUsers(){
        return userService.getAll();
    }

    /**
     * Method to get a single user by their ID.
     * @param id the ID of the user
     * @return GetUserDto the DTO containing the user
     */
    @GetMapping("/{id}")
    public GetUserDto getOne(@PathVariable long id){
        return userService.getOne(id);
    }

    /**
     * Method to delete a user by their ID.
     * @param id the ID of the user
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
