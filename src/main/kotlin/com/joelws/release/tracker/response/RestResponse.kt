package com.joelws.release.tracker.response

import com.joelws.release.tracker.response.RestResponse.*
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

private val successWithEntity = buildResponse(200)

private val badRequest = buildResponse(400)

private val notFound = buildResponse(404)

private val serverError = buildResponse(500)

fun RestResponse.build() = when (this) {
    is Success -> success
    is SuccessWithEntity -> successWithEntity(this.entity)
    is BadRequest -> badRequest(this)
    is NotFound -> notFound(this)
    is ServerError -> serverError(this)
}

private fun buildResponse(statusCode: Int): (Any) -> Response {
    return { entity: Any -> Response.status(statusCode).type(APPLICATION_JSON_TYPE).entity(entity).build() }
}
