package com.github.joelws.release.tracker.service.environment.operation

import com.github.joelws.release.tracker.conversion.EnvironmentAdapter
import com.github.joelws.release.tracker.entity.environment.Environment
import com.github.joelws.release.tracker.response.RestResponse.NotFound
import com.github.joelws.release.tracker.response.RestResponse.SuccessWithEntity
import com.github.joelws.release.tracker.response.build
import com.github.joelws.release.tracker.service.ServiceExecution
import com.github.joelws.release.tracker.service.ServiceHelper
import com.github.joelws.release.tracker.service.ServiceOperation
import javax.ws.rs.core.Response

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
*/open class ReadEnvironmentServiceOperation(private val helper: ServiceHelper,
                                             private val readEnvironmentServiceExecution: ServiceExecution<String?, Environment?>) : ServiceOperation<String> {

    override fun delegate(param: String?): Response {

        val result = readEnvironmentServiceExecution.execute(param)

        return if (result != null) {

            val adapter = helper.factory.getImpl(EnvironmentAdapter::class.java)

            val adaptedResult = adapter.adapt(result)

            SuccessWithEntity(adaptedResult).build()

        } else {
            NotFound("Environment doesn't exist").build()
        }
    }
}
