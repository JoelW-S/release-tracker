package com.github.joelws.release.tracker.endpoint.release

import com.github.joelws.release.tracker.interfaces.BusinessService
import com.github.joelws.release.tracker.interfaces.ResourceEndpoint
import javax.ws.rs.DELETE
import javax.ws.rs.Path
import javax.ws.rs.PathParam

@Path("/release")
open class DeleteReleaseEndpoint(service: BusinessService<String>) : ResourceEndpoint<String>(service) {

    @DELETE
    @Path("/{name}")
    fun delete(@PathParam("name") name: String) = service.delete(name)
}
