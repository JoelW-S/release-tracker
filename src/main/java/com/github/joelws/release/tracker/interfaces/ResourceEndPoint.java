package com.github.joelws.release.tracker.interfaces;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

abstract public class ResourceEndPoint<I>
{
    protected final BusinessService<I> service;

    public ResourceEndPoint(BusinessService<I> service)
    {
        this.service = service;
    }

    @POST
    @Path("/")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response create(String json) {
        return service.create(json);
    }

    @GET
    @Path("/{identifier}")
    @Produces(APPLICATION_JSON)
    public Response read(@PathParam("identifier") I identifier) {
        return service.read(identifier);
    }

    @PUT
    @Path("/")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Response update(String json) {
        return service.update(json);
    }

    @DELETE
    @Path("/{identifier}")
    @Produces(APPLICATION_JSON)
    public Response delete(@PathParam("identifier") I identifier) {
        return service.delete(identifier);
    }

}
