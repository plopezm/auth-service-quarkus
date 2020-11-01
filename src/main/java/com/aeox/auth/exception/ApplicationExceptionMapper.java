package com.aeox.auth.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.aeox.auth.dto.ErrorMessage;

@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException> {
    @Override
    public Response toResponse(final ApplicationException exception) {
        return Response.status(exception.getHttpStatus()).entity(new ErrorMessage(exception.getMessage())).build(); 
    }    
}
