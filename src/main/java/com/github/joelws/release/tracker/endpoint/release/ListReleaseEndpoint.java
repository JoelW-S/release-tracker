package com.github.joelws.release.tracker.endpoint.release;

import com.github.joelws.release.tracker.dto.release.ReleaseDto;
import com.github.joelws.release.tracker.interfaces.BusinessService;
import com.github.joelws.release.tracker.interfaces.ResourceEndpoint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/release")
@Api(value = "/release", description = "Endpoint to interact with releases")
public class ListReleaseEndpoint extends ResourceEndpoint<String> {

    public ListReleaseEndpoint(BusinessService<String> service) {
        super(service);
    }

    @GET
    @Path("/")
    @Produces(APPLICATION_JSON)
    @ApiOperation(value = "Find all releases",
            response = ReleaseDto.class,
            responseContainer = "List")
    @ApiResponse(code = 404, message = "No releases exist")
    public Response list(final String param) {
        return service.list();
    }
}
