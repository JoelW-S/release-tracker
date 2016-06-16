package com.github.joelws.release.tracker.interfaces


import javax.ws.rs.core.Response

abstract class ResourceEndpoint<I>(protected val service: BusinessService<I>) {

    abstract fun method(param: I): Response
}
