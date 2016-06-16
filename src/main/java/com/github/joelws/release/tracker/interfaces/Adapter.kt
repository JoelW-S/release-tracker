package com.github.joelws.release.tracker.interfaces

interface Adapter<In, Out> {
    fun adapt(In: In): Out
}
