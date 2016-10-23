package com.joelws.release.tracker.service

import com.joelws.release.tracker.response.RestResponse

interface ServiceOperation<in I> {
    fun execute(param: I) = delegate(param)

    fun delegate(param: I): RestResponse
}
