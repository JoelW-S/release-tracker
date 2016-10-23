@file:JvmName("Utils")

/*
Copyright 2016 Joel Whittaker-Smith

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.joelws.release.tracker.util

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.joelws.release.tracker.exception.ReleaseTrackerException
import com.joelws.release.tracker.response.RestResponse.ServerError


fun getJsonFromObject(any: Any): String {
    try {
        return jacksonObjectMapper().writeValueAsString(any)
    } catch (e: Exception) {
        throw ReleaseTrackerException(ServerError("Can't map $any to JSON"))
    }
}

inline fun <reified T : Any> getObjectFromJson(jsonString: String?): T {
    try {
        return jacksonObjectMapper().readValue(jsonString, T::class.java)
    } catch (e: Exception) {
        throw ReleaseTrackerException(ServerError("Incorrect JSON has been sent"))
    }
}
