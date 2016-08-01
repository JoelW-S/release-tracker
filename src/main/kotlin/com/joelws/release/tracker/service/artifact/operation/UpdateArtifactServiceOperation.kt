package com.joelws.release.tracker.service.artifact.operation


import com.joelws.release.tracker.response.RestResponse.BadRequest
import com.joelws.release.tracker.response.build
import com.joelws.release.tracker.service.ServiceOperation
import javax.ws.rs.core.Response

open class UpdateArtifactServiceOperation : ServiceOperation<String> {

    override fun delegate(param: String?): Response = BadRequest("Unsupported operation").build()
}
