package com.github.joelws.release.tracker.endpoint.artifact;

import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.interfaces.ResourceEndpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/artifact")
@Api(value = "/artifact", description = "Endpoint to interact with artifacts")
public class DeleteArtifactEndpoint extends ResourceEndpoint<String> {

    public DeleteArtifactEndpoint(BusinessService<String> service) {
        super(service);
    }

    @DELETE
    @Path("/")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    @ApiOperation(value = "Delete artifact")
    @Override
    public Response method(@ApiParam(required = true) @QueryParam("query") @DefaultValue("") final String json) {
        return service.delete(json);
    }
}
