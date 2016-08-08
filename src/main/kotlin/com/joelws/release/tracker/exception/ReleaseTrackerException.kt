package com.joelws.release.tracker.exception

import com.joelws.release.tracker.response.RestResponse

open class ReleaseTrackerException(val restResponse: RestResponse) : RuntimeException()
