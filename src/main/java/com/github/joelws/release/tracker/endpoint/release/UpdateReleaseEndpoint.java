package com.github.joelws.release.tracker.endpoint.release;

import com.github.joelws.release.tracker.dto.release.ReleaseDto;
import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.interfaces.ResourceEndpoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/release")
@Api(value = "/release", description = "Endpoint to interact with releases")
public class UpdateReleaseEndpoint extends ResourceEndpoint<String> {


    public UpdateReleaseEndpoint(BusinessService<String> service) {
        super(service);
    }

    @PUT
    @Path("/")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    @ApiOperation(value = "Update release",
            response = ReleaseDto.class)
    @ApiResponse(code = 404, message = "Release doesn't exist")
    public Response update(final String json) {
        return service.update(json);
    }
}
