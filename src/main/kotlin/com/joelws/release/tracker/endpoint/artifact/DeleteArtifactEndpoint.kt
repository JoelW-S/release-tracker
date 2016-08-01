package com.joelws.release.tracker.endpoint.artifact

import com.joelws.release.tracker.interfaces.BusinessService
import com.joelws.release.tracker.interfaces.ResourceEndpoint
import javax.ws.rs.*
import javax.ws.rs.core.MediaType.APPLICATION_JSON

@Path("/artifact")
open class DeleteArtifactEndpoint(service: BusinessService<String>) : ResourceEndpoint<String>(service) {

    @DELETE
    @Path("/")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    fun delete(@QueryParam("query") @DefaultValue("") json: String) = service.delete(json)
}
