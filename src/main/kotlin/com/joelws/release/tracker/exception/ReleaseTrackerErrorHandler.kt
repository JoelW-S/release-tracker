package com.joelws.release.tracker.exception

import com.joelws.release.tracker.response.ErrorMessage
import com.joelws.release.tracker.response.build
import org.apache.log4j.Logger
import ratpack.error.internal.ErrorHandler
import ratpack.handling.Context

/*
Copyright 2016 Joel Whittaker-Smith

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/open class ReleaseTrackerErrorHandler : ErrorHandler {
    override fun error(context: Context, statusCode: Int) {
        context.response.status(statusCode).send()
    }

    private val logger = Logger.getLogger(ReleaseTrackerErrorHandler::class.java)

    override fun error(context: Context, throwable: Throwable) {
        when (throwable) {
            is ReleaseTrackerException -> handleReleaseTrackerException(context, throwable)
            else -> handleElse(context, throwable)
        }
    }

    private fun handleReleaseTrackerException(context: Context, exception: ReleaseTrackerException) {
        logger.error("Release Tracker specific exception: ", exception)
        val generateResponse = exception.errorMessage.response.build()
        generateResponse(context)
    }

    private fun handleElse(context: Context, throwable: Throwable) {
        logger.error("All other exceptions: ", throwable)
        val generateResponse = ErrorMessage.UNKNOWN_ERROR.response.build()
        generateResponse(context)
    }

}
