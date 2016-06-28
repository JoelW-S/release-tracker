package com.github.joelws.release.tracker.handler

import com.github.joelws.release.tracker.response.RestResponse.BadRequest
import com.github.joelws.release.tracker.response.build
import org.apache.log4j.Logger
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper

open class RuntimeExceptionHandler : ExceptionMapper<RuntimeException> {

    companion object {
        private val LOGGER = Logger.getLogger(RuntimeExceptionHandler::class.java)
    }

    override fun toResponse(exception: RuntimeException): Response {
        LOGGER.error(exception.message)
        return BadRequest().build()
    }
}
