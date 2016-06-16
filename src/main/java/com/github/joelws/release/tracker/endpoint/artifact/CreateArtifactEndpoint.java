package com.github.joelws.release.tracker.endpoint.artifact;


import com.github.joelws.release.tracker.dto.artifact.ArtifactDto;
import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.interfaces.ResourceEndpoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/artifact")
@Api(value = "/artifact", description = "Endpoint to interact with artifacts")
public class CreateArtifactEndpoint extends ResourceEndpoint<String> {

    public CreateArtifactEndpoint(final BusinessService<String> service) {
        super(service);
    }

    @POST
    @Path("/")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    @ApiOperation(value = "Create artifact",
            response = ArtifactDto.class)
    @ApiResponse(code = 422, message = "Artifact already exists")
    public Response create(final String json) {
        return service.create(json);
    }
}
