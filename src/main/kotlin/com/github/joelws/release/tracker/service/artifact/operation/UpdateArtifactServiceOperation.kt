package com.github.joelws.release.tracker.service.artifact.operation


import com.github.joelws.release.tracker.response.RestResponse
import com.github.joelws.release.tracker.response.build
import com.github.joelws.release.tracker.service.ServiceOperation
import org.apache.log4j.Logger
import javax.ws.rs.core.Response

open class UpdateArtifactServiceOperation : ServiceOperation<String> {

    companion object {
        private val LOGGER = Logger.getLogger(UpdateArtifactServiceOperation::class.java)
    }

    override fun delegate(param: String?): Response {
        LOGGER.info("Starting UpdateArtifactServiceOperation:")

        LOGGER.info("Exiting with response: Unsupported operation")

        return RestResponse.BadRequest("Unsupported operation").build()
    }
}
