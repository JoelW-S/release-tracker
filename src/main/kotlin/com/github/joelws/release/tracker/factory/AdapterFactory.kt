package com.github.joelws.release.tracker.factory

import com.github.joelws.release.tracker.interfaces.Adapter

interface AdapterFactory {
    fun getAdapter(klazz: Class<out Adapter<*, *>>): Adapter<*, *>
}
