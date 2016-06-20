package com.github.joelws.release.tracker.endpoint.release


import com.github.joelws.release.tracker.interfaces.BusinessService
import com.github.joelws.release.tracker.interfaces.ResourceEndpoint
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType.APPLICATION_JSON

@Path("/release")
open class ReadReleaseEndpoint(service: BusinessService<String>) : ResourceEndpoint<String>(service) {

    @GET
    @Path("/{name}")
    @Produces(APPLICATION_JSON)
    fun read(@PathParam("name") name: String) = service.read(name)
}
