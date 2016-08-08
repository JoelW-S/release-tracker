package com.joelws.release.tracker.service.environment.operation

import com.joelws.release.tracker.conversion.EnvironmentAdapter
import com.joelws.release.tracker.entity.environment.Environment
import com.joelws.release.tracker.interfaces.Adapter
import com.joelws.release.tracker.model.environment.EnvironmentModel
import com.joelws.release.tracker.response.RestResponse.NotFound
import com.joelws.release.tracker.response.RestResponse.SuccessWithEntity
import com.joelws.release.tracker.response.build
import com.joelws.release.tracker.service.ServiceExecution
import com.joelws.release.tracker.service.ServiceHelper
import com.joelws.release.tracker.service.ServiceOperation
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
*/open class ListEnvironmentServiceOperation(private val helper: ServiceHelper,
                                             private val listEnvironmentServiceExecution: ServiceExecution<Nothing?, List<Environment>>) : ServiceOperation<Nothing?> {

    override fun delegate(param: Nothing?): Response {

        val resultList = listEnvironmentServiceExecution.execute(null)

        return if (resultList.isNotEmpty()) {

            @Suppress("UNCHECKED_CAST")
            val environmentAdapter: Adapter<Environment, EnvironmentModel> = helper.adapterFactory.getAdapter(EnvironmentAdapter::class.java) as Adapter<Environment, EnvironmentModel>
            val adaptedResultList = resultList.map { environmentAdapter.adapt(it) }
            SuccessWithEntity(adaptedResultList).build()
        } else {
            NotFound("No environments exist").build()
        }
    }
}
