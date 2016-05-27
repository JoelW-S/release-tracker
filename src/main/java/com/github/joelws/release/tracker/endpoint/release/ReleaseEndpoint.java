package com.github.joelws.release.tracker.endpoint.release;

import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.interfaces.ResourceEndPoint;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("releases") public class ReleaseEndpoint extends ResourceEndPoint<String>
{
    public ReleaseEndpoint(BusinessService<String> service)
    {
        super(service);
    }

    @POST @Path("/") @Produces(APPLICATION_JSON) @Consumes(APPLICATION_JSON) @Override
    public Response create(String json)
    {
        return service.create(json);
    }

    @GET @Path("/{identifier}") @Produces(APPLICATION_JSON) @Override public Response read(String identifier)
    {
        return service.read(identifier);
    }

    @PUT @Path("/") @Produces(APPLICATION_JSON) @Consumes(APPLICATION_JSON) @Override
    public Response update(String json)
    {
        return service.read(json);
    }

    @DELETE @Path("/{identifier}") @Produces(APPLICATION_JSON) @Override public Response delete(String identifier)
    {
        return service.read(identifier);
    }

}
