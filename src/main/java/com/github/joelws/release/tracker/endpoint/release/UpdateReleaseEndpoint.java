package com.github.joelws.release.tracker.endpoint.release;

import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.interfaces.ResourceEndpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("releases")
public class UpdateReleaseEndpoint extends ResourceEndpoint<String> {


    public UpdateReleaseEndpoint(BusinessService<String> service) {
        super(service);
    }

    @PUT
    @Path("/")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    @Override
    public Response method(final String json) {
        return service.update(json);
    }
}
