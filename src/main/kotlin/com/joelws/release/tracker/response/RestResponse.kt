package com.joelws.release.tracker.response

import com.joelws.release.tracker.response.RestResponse.*
import javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE
import javax.ws.rs.core.Response

sealed class RestResponse {
    object Success : RestResponse()
    class SuccessWithEntity(val entity: Any) : RestResponse()
    class BadRequest(val error: String) : RestResponse()
    class NotFound(val error: String) : RestResponse()
    class ServerError(val error: String = "Unhandled error") : RestResponse()
}

enum class ErrorMessage(val response: RestResponse) {

    ARTIFACT_FOUND(BadRequest("Artifact already exists")),
    RELEASE_FOUND(BadRequest("Release already exists")),
    RELEASE_NOT_FOUND(NotFound("Release doesn't exist")),
    RELEASES_NOT_FOUND(NotFound("No releases exist")),
    ENVIRONMENT_FOUND(BadRequest("Environment already exists")),
    ARTIFACT_NOT_FOUND(NotFound("Artifact doesn't exist")),
    ARTIFACTS_NOT_FOUND(NotFound("No artifacts could be found")),
    ENVIRONMENT_NOT_FOUND(NotFound("Environment doesn't exist")),
    ENVIRONMENTS_NOT_FOUND(NotFound("No environments exist")),
    UNSUPPORTED_OPERATION(BadRequest("Unsupported operation")),
    UNKNOWN_ERROR(ServerError())
}


private val success = Response.ok().build()

private val successWithEntity = buildResponse(200)

private val badRequest = buildResponse(400)

private val notFound = buildResponse(404)

private val serverError = buildResponse(500)

fun RestResponse.build(): Response = when (this) {
    is Success -> success
    is SuccessWithEntity -> successWithEntity(this.entity)
    is BadRequest -> badRequest(this)
    is NotFound -> notFound(this)
    is ServerError -> serverError(this)
}

private fun buildResponse(statusCode: Int): (Any) -> Response {
    return { entity: Any -> Response.status(statusCode).type(APPLICATION_JSON_TYPE).entity(entity).build() }
}
