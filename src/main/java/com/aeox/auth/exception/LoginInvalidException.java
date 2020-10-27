package com.aeox.auth.exception;

public class LoginInvalidException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public LoginInvalidException() {
        super("Login username or password invalid");
    }
}
