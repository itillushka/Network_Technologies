package com.illia.project.ntilliaproject.controller.dto;

public class SignInResponseDto {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SignInResponseDto(String token) {
        this.token = token;
    }
}
