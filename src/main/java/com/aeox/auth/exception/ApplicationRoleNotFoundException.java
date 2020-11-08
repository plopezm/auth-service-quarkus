package com.aeox.auth.exception;

import javax.ws.rs.core.Response.Status;

public class ApplicationRoleNotFoundException extends ApplicationException {
    private static final long serialVersionUID = 1L;

    public ApplicationRoleNotFoundException(final String role, final String app) {
        super(Status.NOT_FOUND, "Role [" + role + "] was not found on application [" + app + "]");
    }    
}
