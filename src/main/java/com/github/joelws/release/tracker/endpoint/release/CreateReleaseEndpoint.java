package com.github.joelws.release.tracker.endpoint.release;


import com.github.joelws.release.tracker.dto.release.ReleaseDto;
import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.interfaces.ResourceEndpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/release")
@Api(value = "/release", description = "Endpoint to interact with releases")
public class CreateReleaseEndpoint extends ResourceEndpoint<String> {

    public CreateReleaseEndpoint(BusinessService<String> service) {
        super(service);
    }

    @POST
    @Path("/")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    @ApiOperation(value = "Create release",
            response = ReleaseDto.class)
    @ApiResponse(code = 422, message = "Release already exists")
    @Override
    public Response method(final String json) {
        return service.create(json);
    }
}
