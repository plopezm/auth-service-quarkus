package com.aeox.auth.exception;

import javax.ws.rs.core.Response.Status;

public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Status httpStatus;

    public ApplicationException(final Status httpStatus, final String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ApplicationException(final Status httpStatus, final String message, final Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }

    public Status getHttpStatus() {
        return httpStatus;
    }    
}
