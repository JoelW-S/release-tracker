package com.github.joelws.release.tracker.util

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.joelws.release.tracker.exception.ReleaseTrackerException
import com.github.joelws.release.tracker.interfaces.JsonAdapter
import com.github.joelws.release.tracker.response.RestResponse.BadRequest
import org.apache.log4j.Logger
import java.io.IOException

open class JsonAdapterImpl : JsonAdapter {

    companion object {
        private val LOGGER = Logger.getLogger(JsonAdapterImpl::class.java)
    }

    override fun getJsonFromObject(any: Any): String? = try {
        jacksonObjectMapper().writeValueAsString(any)
    } catch (e: Exception) {
        LOGGER.info("Unable to map object to JSON: $any")
        throw ReleaseTrackerException(BadRequest())
    }


    override fun <T> getObjectFromJson(jsonString: String?, klazz: Class<T>): T = try {
        jacksonObjectMapper().readValue(jsonString, klazz)
    } catch (e: IOException) {
        LOGGER.info("Unable to map JSON to object: $jsonString")
        throw ReleaseTrackerException(BadRequest())
    }
}
