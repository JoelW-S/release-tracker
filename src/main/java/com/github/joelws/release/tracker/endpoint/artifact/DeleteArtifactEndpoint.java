package com.github.joelws.release.tracker.endpoint.artifact;

import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.interfaces.ResourceEndpoint;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("artifacts")
public class DeleteArtifactEndpoint extends ResourceEndpoint<String> {

    public DeleteArtifactEndpoint(BusinessService<String> service) {
        super(service);
    }

    @DELETE
    @Path("/")
    @Produces(APPLICATION_JSON)
    @Override
    public Response method(@QueryParam("query") final String json) {
        return service.delete(json);
    }
}
