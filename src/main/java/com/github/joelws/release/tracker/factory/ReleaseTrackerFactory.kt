package com.github.joelws.release.tracker.factory

import com.github.joelws.release.tracker.exception.ReleaseTrackerException
import com.github.joelws.release.tracker.response.RestResponse
import org.apache.log4j.Logger

open class ReleaseTrackerFactory : Factory {

    companion object {
        val LOGGER = Logger.getLogger(ReleaseTrackerFactory::class.java)
    }

    override fun <T> getImpl(klazz: Class<T>) = try {
        klazz.newInstance()
    } catch (e: InstantiationException) {
        LOGGER.error("Failed to instantiate object: " + klazz)
        throw ReleaseTrackerException(RestResponse.ServerError("Failed to instantiate object"))
    } catch(e: IllegalAccessException) {
        LOGGER.error("Failed to instantiate object: " + klazz)
        throw ReleaseTrackerException(RestResponse.ServerError("Failed to instantiate object"))
    }
}
