package com.joelws.release.tracker.service.artifact.operation


import com.joelws.release.tracker.response.ErrorMessage
import com.joelws.release.tracker.response.RestResponse
import com.joelws.release.tracker.service.ServiceOperation

open class UpdateArtifactServiceOperation : ServiceOperation<String> {

    override fun delegate(param: String): RestResponse = ErrorMessage.UNSUPPORTED_OPERATION.response
}
