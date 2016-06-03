package com.github.joelws.release.tracker.endpoint.release;

import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.interfaces.ResourceEndpoint;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("releases")
public class DeleteReleaseEndpoint extends ResourceEndpoint<String> {

    public DeleteReleaseEndpoint(BusinessService<String> service) {
        super(service);
    }

    @DELETE
    @Path("/{name}")
    @Produces(APPLICATION_JSON)
    @Override
    public Response method(@PathParam("name") final String name) {
        return service.delete(name);
    }
}
