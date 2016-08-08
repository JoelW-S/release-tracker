package com.joelws.release.tracker.service

interface ServiceExecution<in In, out Out> {
    fun execute(param: In): Out
}
