package com.joelws.release.tracker.handler

import com.joelws.release.tracker.response.ErrorMessage
import com.joelws.release.tracker.response.build
import org.apache.log4j.Logger
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper

open class RuntimeExceptionHandler : ExceptionMapper<RuntimeException> {

    companion object {
        private val LOGGER = Logger.getLogger(RuntimeExceptionHandler::class.java)
    }

    override fun toResponse(exception: RuntimeException): Response {
        LOGGER.error("Runtime exception: ", exception)
        return ErrorMessage.UNKNOWN_ERROR.response.build()
    }
}
