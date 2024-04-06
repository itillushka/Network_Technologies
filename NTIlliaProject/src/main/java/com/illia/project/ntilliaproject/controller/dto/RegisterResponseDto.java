package com.illia.project.ntilliaproject.controller.dto;

import com.illia.project.ntilliaproject.commonTypes.UserRole;

public class RegisterResponseDto {

    private String username;
    private UserRole role;
    private long userID;

    public RegisterResponseDto(String username, UserRole role, long userID) {
        this.username = username;
        this.role = role;
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }


}
