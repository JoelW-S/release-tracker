package com.github.joelws.release.tracker.handler

import com.github.joelws.release.tracker.exception.ReleaseTrackerException
import com.github.joelws.release.tracker.response.build
import javax.ws.rs.core.Response

import javax.ws.rs.ext.ExceptionMapper

open class ReleaseTrackerExceptionHandler : ExceptionMapper<ReleaseTrackerException> {

    override fun toResponse(exception: ReleaseTrackerException): Response = exception.restResponse.build()
}
