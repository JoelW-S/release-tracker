package com.github.joelws.release.tracker.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


public class ServiceExceptionhandler implements ExceptionMapper<ServiceException> {
    private static final Logger LOGGER = Logger.getLogger(ServiceExceptionhandler.class);

    @Override
    public Response toResponse(ServiceException exception) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(exception.getResponseError());
            return Response.status(exception.getResponseError().getErrorCode()).entity(json).build();
        } catch (JsonProcessingException e) {
            LOGGER.error("Failed to convert object to JSON: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
