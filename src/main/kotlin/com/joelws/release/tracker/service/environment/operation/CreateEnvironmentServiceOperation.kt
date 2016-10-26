package com.joelws.release.tracker.service.environment.operation

import com.joelws.release.tracker.conversion.EnvironmentAdapter
import com.joelws.release.tracker.conversion.EnvironmentModelAdapter
import com.joelws.release.tracker.entity.environment.Environment
import com.joelws.release.tracker.model.environment.EnvironmentModel
import com.joelws.release.tracker.response.ErrorMessage
import com.joelws.release.tracker.response.RestResponse
import com.joelws.release.tracker.response.RestResponse.SuccessWithEntity
import com.joelws.release.tracker.service.ServiceExecution
import com.joelws.release.tracker.service.ServiceHelper
import com.joelws.release.tracker.service.ServiceOperation
import com.joelws.release.tracker.util.getObjectFromJson
import org.funktionale.option.Option
import org.funktionale.option.Option.None
import org.funktionale.option.Option.Some

open class CreateEnvironmentServiceOperation(private val helper: ServiceHelper,
                                             private val createEnvironmentServiceExecution: ServiceExecution<Environment, Option<Environment>>) : ServiceOperation<String> {

    override fun delegate(param: String): RestResponse {

        val environmentModelAdapter = helper.adapterFactory.getAdapter(EnvironmentModelAdapter::class.java)

        val result = createEnvironmentServiceExecution.execute(environmentModelAdapter.adapt(getObjectFromJson<EnvironmentModel>(param)))

        return when (result) {

            is Some<Environment> -> {
                val environmentAdapter = helper.adapterFactory.getAdapter(EnvironmentAdapter::class.java)

                val adaptedResult = result.map { environment -> environmentAdapter.adapt(environment) }.get()

                SuccessWithEntity(adaptedResult)
            }
            is None -> ErrorMessage.ENVIRONMENT_EXIST.response

        }
    }
}
