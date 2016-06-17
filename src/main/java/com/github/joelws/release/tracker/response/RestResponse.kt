package com.github.joelws.release.tracker.response

import com.github.joelws.release.tracker.response.RestResponse.*
import javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE
import javax.ws.rs.core.Response

sealed class RestResponse {
    class Success : RestResponse()
    class SuccessWithEntity(val entity: Any) : RestResponse()
    class BadRequest(val error: String = "400 error has occurred") : RestResponse()
    class NotFound(val error: String = "404 error has occurred") : RestResponse()
    class ServerError(val error: String = "500 error has occurred") : RestResponse()
}


private val success = Response.ok().build()

private val successWithEntity = { entity: Any -> Response.ok(entity).type(APPLICATION_JSON_TYPE).build() }

private val badRequest = { entity: Any -> Response.status(400).type(APPLICATION_JSON_TYPE).entity(entity).build() }

private val notFound = { entity: Any -> Response.status(404).entity(entity).type(APPLICATION_JSON_TYPE).build() }

private val serverError = { entity: Any -> Response.status(500).entity(entity).type(APPLICATION_JSON_TYPE).build() }

fun RestResponse.build() = when (this) {
    is Success -> success
    is SuccessWithEntity -> successWithEntity(this.entity)
    is BadRequest -> badRequest(this)
    is NotFound -> notFound(this)
    is ServerError -> serverError(this)
}






