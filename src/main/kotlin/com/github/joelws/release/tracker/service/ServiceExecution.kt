package com.github.joelws.release.tracker.service

interface ServiceExecution<In, Out> {
    fun execute(param: In): Out
}
