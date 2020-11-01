package com.aeox.auth.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.aeox.auth.dto.ErrorMessage;

public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException> {
    @Override
    public Response toResponse(ApplicationException exception) {
        return Response.status(exception.getHttpStatus()).entity(new ErrorMessage(exception.getMessage())).build(); 
    }    
}
