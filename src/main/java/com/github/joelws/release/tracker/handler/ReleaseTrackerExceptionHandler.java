package com.github.joelws.release.tracker.handler;

import com.github.joelws.release.tracker.exception.ReleaseTrackerException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ReleaseTrackerExceptionHandler implements ExceptionMapper<ReleaseTrackerException> {

    @Override
    public Response toResponse(ReleaseTrackerException exception) {
        return Response.status(exception.getErrorResponse().getStatus()).entity(exception.getErrorResponse()).build();
    }
}
