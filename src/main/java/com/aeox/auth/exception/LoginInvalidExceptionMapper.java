package com.aeox.auth.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.aeox.auth.dto.ErrorMessage;

@Provider
public class LoginInvalidExceptionMapper implements ExceptionMapper<LoginInvalidException> {

    @Override
    public Response toResponse(LoginInvalidException exception) {
        return Response.status(Status.BAD_REQUEST).entity(new ErrorMessage(exception.getMessage())).build(); 
    }
    
}
