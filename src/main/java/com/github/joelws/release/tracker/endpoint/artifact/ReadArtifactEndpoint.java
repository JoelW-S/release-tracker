package com.github.joelws.release.tracker.endpoint.artifact;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDto;
import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.interfaces.ResourceEndpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/artifact")
@Api(value = "/artifact", description = "Endpoint to interact with artifacts")
public class ReadArtifactEndpoint extends ResourceEndpoint<String> {

    public ReadArtifactEndpoint(BusinessService<String> service) {
        super(service);
    }

    @GET
    @Path("/")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    @ApiOperation(value = "Find artifact based on query",
            response = ArtifactDto.class)
    @ApiResponse(code = 404, message = "Artifact doesn't exist")
    @Override
    public Response method(@ApiParam(required = true) @QueryParam("query") @DefaultValue("") final String query) {
        return service.read(query);
    }
}
