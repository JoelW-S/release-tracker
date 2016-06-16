package com.github.joelws.release.tracker.response

import com.github.joelws.release.tracker.response.RestResponse.*
import javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE
import javax.ws.rs.core.Response

sealed class RestResponse {
    class Success : RestResponse()
    class SuccessWithEntity(val entity: Any) : RestResponse()
    class BadRequest(val error: String = "An error has occurred") : RestResponse()
    class NotFound(val error: String = "An error has occurred") : RestResponse()
}


private val success = Response.ok().build()

private val successWithEntity = { entity: Any -> Response.ok(entity).type(APPLICATION_JSON_TYPE).build() }

private val badRequest = Response.status(400).type(APPLICATION_JSON_TYPE).build()

private val notFound = Response.status(404).type(APPLICATION_JSON_TYPE).build()

fun RestResponse.build() = when (this) {
    is Success -> success
    is SuccessWithEntity -> successWithEntity(this.entity)
    is BadRequest -> badRequest
    is NotFound -> notFound
}






