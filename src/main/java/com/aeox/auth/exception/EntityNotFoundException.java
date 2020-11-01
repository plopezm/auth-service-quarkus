package com.aeox.auth.exception;

import javax.ws.rs.core.Response.Status;

public class EntityNotFoundException extends ApplicationException {
    private static final long serialVersionUID = 1L;
    
    public EntityNotFoundException(Class<?> clazz) {
        super(Status.NOT_FOUND, "Entity not found " + clazz.getName());
    }
}
