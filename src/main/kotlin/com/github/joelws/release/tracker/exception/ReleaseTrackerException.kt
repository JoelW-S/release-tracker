package com.github.joelws.release.tracker.exception

import com.github.joelws.release.tracker.response.RestResponse

open class ReleaseTrackerException(val restResponse: RestResponse) : RuntimeException()
