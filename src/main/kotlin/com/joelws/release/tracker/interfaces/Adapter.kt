package com.joelws.release.tracker.interfaces

interface Adapter<in In, out Out> {
    fun adapt(incoming: In): Out
}
