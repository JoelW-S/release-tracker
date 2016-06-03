package com.github.joelws.release.tracker.endpoint.artifact;

import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.interfaces.ResourceEndpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("artifacts")
public class ReadArtifactEndpoint extends ResourceEndpoint<String> {

    public ReadArtifactEndpoint(BusinessService<String> service) {
        super(service);
    }

    @GET
    @Path("/")
    @Produces(APPLICATION_JSON)
    @Override
    public Response method(@QueryParam("query") final String query) {
        return service.read(query);
    }
}
