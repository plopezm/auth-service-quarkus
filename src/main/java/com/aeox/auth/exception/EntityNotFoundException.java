package com.aeox.auth.exception;

public class EntityNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    public EntityNotFoundException() {
        super("Not found");
    }
}
