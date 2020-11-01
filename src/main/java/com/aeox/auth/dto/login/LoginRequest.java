package com.aeox.auth.dto.login;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    public String username;
    @NotBlank
    public String password;
}
