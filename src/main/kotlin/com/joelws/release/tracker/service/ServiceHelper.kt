package com.joelws.release.tracker.service

import com.joelws.release.tracker.factory.AdapterFactory
import com.joelws.release.tracker.interfaces.JsonAdapter

data class ServiceHelper(val jsonAdapter: JsonAdapter,
                         val adapterFactory: AdapterFactory)

