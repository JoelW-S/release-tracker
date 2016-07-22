package com.github.joelws.release.tracker.service.artifact.operation


import com.github.joelws.release.tracker.response.RestResponse
import com.github.joelws.release.tracker.response.build
import com.github.joelws.release.tracker.service.ServiceOperation

open class UpdateArtifactServiceOperation : ServiceOperation<String> {

    override fun delegate(param: String?) = RestResponse.BadRequest("Unsupported operation").build()
}
