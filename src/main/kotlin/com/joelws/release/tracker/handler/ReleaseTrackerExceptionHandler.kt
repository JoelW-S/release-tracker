package com.joelws.release.tracker.handler

import com.joelws.release.tracker.exception.ReleaseTrackerException
import com.joelws.release.tracker.response.build
import javax.ws.rs.core.Response

import javax.ws.rs.ext.ExceptionMapper

open class ReleaseTrackerExceptionHandler : ExceptionMapper<ReleaseTrackerException> {

    override fun toResponse(exception: ReleaseTrackerException): Response = exception.restResponse.build()
}
