package com.github.joelws.release.tracker.common;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public interface GenericEndPoint<T, PK>
{
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response create(T type);

    @GET
    @Path("/{identifier}")
    @Produces(MediaType.APPLICATION_JSON)
    Response read(@PathParam("identifier") PK identifier);

    @PUT
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response update(T type);

    @DELETE
    @Path("/{identifier}")
    @Produces(MediaType.APPLICATION_JSON)
    Response delete(@PathParam("identifier") PK identifier);

}
