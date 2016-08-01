package com.joelws.release.tracker.service

import javax.ws.rs.core.Response

interface ServiceOperation<in I> {
    fun execute(param: I?) = delegate(param)

    fun delegate(param: I?): Response
}
