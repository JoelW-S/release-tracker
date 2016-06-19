package com.github.joelws.release.tracker.service.artifact.operation


import com.github.joelws.release.tracker.response.RestResponse
import com.github.joelws.release.tracker.response.build
import com.github.joelws.release.tracker.service.ServiceHelper
import com.github.joelws.release.tracker.service.ServiceOperation
import org.apache.log4j.Logger

import javax.ws.rs.core.Response

open class UpdateArtifactServiceOperation(private val helper: ServiceHelper) : ServiceOperation<String> {

    companion object {
        private val LOGGER = Logger.getLogger(UpdateArtifactServiceOperation::class.java)
    }

    override fun delegate(param: String?): Response {

        LOGGER.info("Exiting with response: " + "unsupported operation")

        return RestResponse.BadRequest("Unsupported operation").build()
    }
}
