package com.github.joelws.release.tracker.service.environment.operation

import com.github.joelws.release.tracker.conversion.EnvironmentAdapter
import com.github.joelws.release.tracker.conversion.EnvironmentModelAdapter
import com.github.joelws.release.tracker.entity.environment.Environment
import com.github.joelws.release.tracker.model.environment.EnvironmentModel
import com.github.joelws.release.tracker.response.RestResponse.BadRequest
import com.github.joelws.release.tracker.response.RestResponse.SuccessWithEntity
import com.github.joelws.release.tracker.response.build
import com.github.joelws.release.tracker.service.ServiceExecution
import com.github.joelws.release.tracker.service.ServiceHelper
import com.github.joelws.release.tracker.service.ServiceOperation
import javax.ws.rs.core.Response

open class CreateEnvironmentServiceOperation(private val helper: ServiceHelper,
                                             private val createEnvironmentServiceExecution: ServiceExecution<Environment, Environment?>) : ServiceOperation<String> {

    override fun delegate(param: String?): Response {

        val environmentModelAdapter = helper.factory.getImpl(EnvironmentModelAdapter::class.java)

        val result = createEnvironmentServiceExecution.execute(environmentModelAdapter.adapt(helper.jsonAdapter.getObjectFromJson(param, EnvironmentModel::class.java)))

        return if (result != null) {

            val adapterForResponse = helper.factory.getImpl(EnvironmentAdapter::class.java)

            val adaptedResult = adapterForResponse.adapt(result)

            SuccessWithEntity(adaptedResult).build()

        } else {
            BadRequest("Environment already exists").build()
        }
    }
}
