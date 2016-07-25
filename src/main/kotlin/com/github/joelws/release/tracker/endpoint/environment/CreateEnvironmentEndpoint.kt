package com.github.joelws.release.tracker.endpoint.environment


import com.github.joelws.release.tracker.interfaces.BusinessService
import com.github.joelws.release.tracker.interfaces.ResourceEndpoint
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType.APPLICATION_JSON

@Path("/environment")
open class CreateEnvironmentEndpoint(service: BusinessService<String>) : ResourceEndpoint<String>(service) {


    @POST
    @Path("/")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    fun create(json: String) = service.create(json)
}
