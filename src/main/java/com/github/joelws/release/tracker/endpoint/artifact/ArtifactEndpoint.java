package com.github.joelws.release.tracker.endpoint.artifact;

import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.interfaces.ResourceEndPoint;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("artifacts") public class ArtifactEndpoint extends ResourceEndPoint<String>
{
    public ArtifactEndpoint(BusinessService<String> service)
    {
        super(service);
    }

    @GET @Path("/") @Produces(APPLICATION_JSON) @Override
    public Response read(@QueryParam("identifier") String identifier)
    {
        return service.read(identifier);
    }

    @DELETE @Path("/") @Produces(APPLICATION_JSON) @Override
    public Response delete(@QueryParam("identifier") String identifier)
    {
        return service.delete(identifier);
    }
}
