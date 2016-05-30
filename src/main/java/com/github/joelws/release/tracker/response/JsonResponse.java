package com.github.joelws.release.tracker.response;

import com.github.joelws.release.tracker.interfaces.RestResponse;

import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.ResponseBuilder;
import static javax.ws.rs.core.Response.status;

public class JsonResponse implements RestResponse
{
    private final static String MEDIA_TYPE = APPLICATION_JSON;

    public JsonResponse()
    {
    }

    public Response build(Integer status, Object entity)
    {
        final ResponseBuilder response = status(status);
        response.entity(entity);
        response.type(MEDIA_TYPE);
        return response.build();
    }

    public Response build(Integer status)
    {
        final ResponseBuilder response = status(status);
        response.type(MEDIA_TYPE);
        return response.build();
    }

    public Response build(final com.github.joelws.release.tracker.handler.JsonResponse jsonResponse)
    {
        final ResponseBuilder response = status(jsonResponse.getStatus());
        response.entity(jsonResponse);
        response.type(MEDIA_TYPE);
        return response.build();
    }

}
