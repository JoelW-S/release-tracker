package com.joelws.release.tracker.factory

import com.joelws.release.tracker.interfaces.Adapter

interface AdapterFactory {
    fun getAdapter(klazz: Class<out Adapter<*, *>>): Adapter<*, *>
}
