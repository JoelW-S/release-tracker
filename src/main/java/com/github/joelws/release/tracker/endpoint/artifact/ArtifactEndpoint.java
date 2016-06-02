package com.github.joelws.release.tracker.endpoint.artifact;

import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.interfaces.ResourceEndPoint;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("artifacts")
public class ArtifactEndpoint extends ResourceEndPoint<String> {
    public ArtifactEndpoint(BusinessService<String> service) {
        super(service);
    }

    @GET
    @Path("/")
    @Produces(APPLICATION_JSON)
    @Override
    public Response read(@QueryParam("artifact") String artifact) {
        return service.read(artifact);
    }

    @DELETE
    @Path("/")
    @Produces(APPLICATION_JSON)
    @Override
    public Response delete(@QueryParam("artifact") String artifact) {
        return service.delete(artifact);
    }
}
