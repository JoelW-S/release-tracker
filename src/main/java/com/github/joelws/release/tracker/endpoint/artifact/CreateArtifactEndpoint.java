package com.github.joelws.release.tracker.endpoint.artifact;


import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.interfaces.ResourceEndpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("artifacts")
public class CreateArtifactEndpoint extends ResourceEndpoint<String> {

    public CreateArtifactEndpoint(final BusinessService<String> service) {
        super(service);
    }

    @POST
    @Path("/")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    @Override
    public Response method(final String json) {
        return service.create(json);
    }
}
