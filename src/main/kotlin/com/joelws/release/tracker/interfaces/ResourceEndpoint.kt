package com.joelws.release.tracker.interfaces

import ratpack.func.Action
import ratpack.handling.Chain

abstract class ResourceEndpoint<in I>(protected val service: BusinessService<I>) {

    abstract val endpoints: Action<Chain>
}
