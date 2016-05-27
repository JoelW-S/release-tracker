package com.github.joelws.release.tracker.interfaces;

import com.github.joelws.release.tracker.handler.JsonResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public class RestResponseBuilder
{
    private final static String MEDIA_TYPE = APPLICATION_JSON;

    public RestResponseBuilder()
    {
    }

    public Response build(Integer status, Object entity)
    {
        final ResponseBuilder response = Response.status(status);
        response.entity(entity);
        response.type(MEDIA_TYPE);
        return response.build();
    }

    public Response build(Integer status)
    {
        final ResponseBuilder response = Response.status(status);
        response.type(MEDIA_TYPE);
        return response.build();
    }

    public Response build(final JsonResponse jsonResponse)
    {
        final ResponseBuilder response = Response.status(jsonResponse.getStatus());
        response.entity(jsonResponse);
        response.type(MEDIA_TYPE);
        return response.build();
    }

}
