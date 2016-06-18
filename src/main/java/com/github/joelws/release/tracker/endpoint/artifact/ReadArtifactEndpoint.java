package com.github.joelws.release.tracker.endpoint.artifact;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDto;
import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.interfaces.ResourceEndpoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

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
    @ApiResponses({@ApiResponse(code = 404, message = "Artifact doesn't exist"),
            @ApiResponse(code = 404, message = "No artifacts exist")})
    public Response read(@QueryParam("query") final String query) {

        Response response;

        if (query == null) {
            response = service.list();
        } else {
            response = service.read(query);
        }

        return response;
    }
}
