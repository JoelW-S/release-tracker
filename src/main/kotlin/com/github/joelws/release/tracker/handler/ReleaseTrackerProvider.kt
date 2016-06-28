package com.github.joelws.release.tracker.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider
import com.fasterxml.jackson.module.kotlin.registerKotlinModule


open class ReleaseTrackerProvider : JacksonJsonProvider(ObjectMapper().registerKotlinModule())