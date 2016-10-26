package com.joelws.release.tracker.exception

import com.joelws.release.tracker.response.ErrorMessage

open class ReleaseTrackerException(val errorMessage: ErrorMessage) : RuntimeException()
