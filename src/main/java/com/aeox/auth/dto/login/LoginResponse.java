package com.aeox.auth.dto.login;

public class LoginResponse {
    public String token;

    public LoginResponse(String token) {
        this.token = token;
    }
}