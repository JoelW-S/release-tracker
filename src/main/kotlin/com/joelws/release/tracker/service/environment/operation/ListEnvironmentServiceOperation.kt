package com.joelws.release.tracker.service.environment.operation

import com.joelws.release.tracker.conversion.EnvironmentAdapter
import com.joelws.release.tracker.entity.environment.Environment
import com.joelws.release.tracker.response.ErrorMessage
import com.joelws.release.tracker.response.RestResponse
import com.joelws.release.tracker.response.RestResponse.SuccessWithEntity
import com.joelws.release.tracker.service.ServiceExecution
import com.joelws.release.tracker.service.ServiceHelper
import com.joelws.release.tracker.service.ServiceOperation
import org.funktionale.option.Option.None

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
*/open class ListEnvironmentServiceOperation(private val helper: ServiceHelper,
                                             private val listEnvironmentServiceExecution: ServiceExecution<None, List<Environment>>) : ServiceOperation<None> {

    override fun delegate(param: None): RestResponse {

        val resultList = listEnvironmentServiceExecution.execute(param)

        return if (resultList.isNotEmpty()) {

            val environmentAdapter = helper.adapterFactory.getAdapter(EnvironmentAdapter::class.java)
            val adaptedResultList = resultList.map { environment -> environmentAdapter.adapt(environment) }
            SuccessWithEntity(adaptedResultList)
        } else {
            ErrorMessage.ENVIRONMENTS_NOT_EXIST.response
        }
    }
}
