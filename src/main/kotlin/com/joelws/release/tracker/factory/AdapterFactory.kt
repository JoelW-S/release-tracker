package com.joelws.release.tracker.factory

import com.joelws.release.tracker.interfaces.Adapter

interface AdapterFactory {
    fun <In, Out> getAdapter(klazz: Class<out Adapter<In, Out>>): Adapter<In, Out>
}
