package com.joelws.release.tracker.response

import com.joelws.release.tracker.response.RestResponse.*
import ratpack.handling.Context
import ratpack.jackson.Jackson.json

sealed class RestResponse {
    object Success : RestResponse()
    class SuccessWithEntity(val entity: Any) : RestResponse()
    class BadRequest(val error: String) : RestResponse()
    class NotFound(val error: String) : RestResponse()
    class ServerError(val error: String = "Unhandled error") : RestResponse()
}

enum class ErrorMessage(val response: RestResponse) {

    ARTIFACT_EXIST(BadRequest("Artifact already exists")),
    ARTIFACT_DEPENDENCY(BadRequest("Artifact exists in a Release")),
    RELEASE_EXIST(BadRequest("Release already exists")),
    RELEASE_DEPENDENCY(BadRequest("Release exists in an Environment")),
    RELEASE_IS_HOTFIX(BadRequest("Release exists as another release's hotfix")),
    RELEASE_NOT_EXIST(NotFound("Release doesn't exist")),
    RELEASES_NOT_EXIST(NotFound("No releases exist")),
    ENVIRONMENT_EXIST(BadRequest("Environment already exists")),
    ARTIFACT_NOT_EXIST(NotFound("Artifact doesn't exist")),
    ARTIFACTS_NOT_EXIST(NotFound("No artifacts could be found")),
    ENVIRONMENT_NOT_EXIST(NotFound("Environment doesn't exist")),
    ENVIRONMENTS_NOT_EXIST(NotFound("No environments exist")),
    UNSUPPORTED_OPERATION(BadRequest("Unsupported operation")),
    INCORRECT_JSON(BadRequest("Incorrect JSON has been sent")),
    INCORRECT_ADAPTER(ServerError("No such adapter exists")),
    UNKNOWN_ERROR(ServerError())
}

private val jsonContentType = "application/json"
private val success: (Context) -> Unit = { ctx ->
    ctx.response.send(jsonContentType)
}

private val successWithEntity = buildResponse(200)

private val badRequest = buildResponse(400)

private val notFound = buildResponse(404)

private val serverError = buildResponse(500)

fun RestResponse.build(): (Context) -> Unit {
    return when (this) {
        is Success -> success
        is SuccessWithEntity -> successWithEntity(this.entity)
        is BadRequest -> badRequest(this)
        is NotFound -> notFound(this)
        is ServerError -> serverError(this)
    }
}

private fun buildResponse(statusCode: Int): (Any) -> (Context) -> Unit {

    return { entity: Any ->
        { context: Context ->
            context.apply {
                response.status(statusCode)
                response.contentType(jsonContentType)
                render(json(entity))
            }
        }
    }
}


