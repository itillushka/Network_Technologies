package com.illia.project.ntilliaproject.controller.dto.user;


public class GetUserDto {

    private int userID;

    private String email;

    private String name;

    public GetUserDto(int userID, String email, String name) {
        this.userID = userID;
        this.email = email;
        this.name = name;
    }

    public GetUserDto() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
