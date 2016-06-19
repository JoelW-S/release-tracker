package com.github.joelws.release.tracker.endpoint.artifact

import com.github.joelws.release.tracker.interfaces.BusinessService
import com.github.joelws.release.tracker.interfaces.ResourceEndpoint
import javax.ws.rs.*
import javax.ws.rs.core.MediaType.APPLICATION_JSON

@Path("/artifact")
open class ReadArtifactEndpoint(service: BusinessService<String>) : ResourceEndpoint<String>(service) {

    @GET
    @Path("/")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    fun read(@QueryParam("query") query: String?) = if (query == null) {
        service.list()
    } else {
        service.read(query)
    }
}

