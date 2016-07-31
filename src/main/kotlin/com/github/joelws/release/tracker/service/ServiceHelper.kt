package com.github.joelws.release.tracker.service

import com.github.joelws.release.tracker.factory.AdapterFactory
import com.github.joelws.release.tracker.interfaces.JsonAdapter

data class ServiceHelper(val jsonAdapter: JsonAdapter,
                         val adapterFactory: AdapterFactory)

