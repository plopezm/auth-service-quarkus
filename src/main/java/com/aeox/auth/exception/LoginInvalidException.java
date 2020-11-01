package com.aeox.auth.exception;

import javax.ws.rs.core.Response.Status;

public class LoginInvalidException extends ApplicationException {
    private static final long serialVersionUID = 1L;
    
    public LoginInvalidException() {
        super(Status.BAD_REQUEST, "Login username or password invalid");
    }

    public LoginInvalidException(final Throwable exception) {
        super(Status.BAD_REQUEST, "Login username or password invalid", exception);
    }
}
