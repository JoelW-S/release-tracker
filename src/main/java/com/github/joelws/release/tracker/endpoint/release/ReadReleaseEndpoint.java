package com.github.joelws.release.tracker.endpoint.release;


import com.github.joelws.release.tracker.dto.release.ReleaseDto;
import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.interfaces.ResourceEndpoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/release")
@Api(value = "/release", description = "Endpoint to interact with releases")
public class ReadReleaseEndpoint extends ResourceEndpoint<String> {

    public ReadReleaseEndpoint(BusinessService<String> service) {
        super(service);
    }

    @GET
    @Path("/{name}")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Find release based on name",
            response = ReleaseDto.class)
    @ApiResponse(code = 404, message = "Release doesn't exist")
    public Response read(@ApiParam(required = true) @PathParam("name") final String name) {
        return service.read(name);
    }
}
