package com.github.joelws.release.tracker.handler

import com.github.joelws.release.tracker.response.RestResponse.BadRequest
import com.github.joelws.release.tracker.response.build
import javax.ws.rs.ext.ExceptionMapper

open class RuntimeExceptionHandler : ExceptionMapper<RuntimeException> {

    override fun toResponse(exception: RuntimeException) = BadRequest().build()
}
