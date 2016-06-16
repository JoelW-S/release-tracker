package com.github.joelws.release.tracker.interfaces

interface Adapter<In, Out> {
    fun adapt(incoming: In): Out
}
