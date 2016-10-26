package com.joelws.release.tracker.service.release.operation

import com.joelws.release.tracker.response.RestResponse
import com.joelws.release.tracker.response.RestResponse.Success
import com.joelws.release.tracker.service.ServiceExecution
import com.joelws.release.tracker.service.ServiceOperation

open class DeleteReleaseServiceOperation(private val deleteReleaseServiceExecution: ServiceExecution<String, Unit>) : ServiceOperation<String> {

    override fun delegate(param: String): RestResponse {

        deleteReleaseServiceExecution
                .execute(param)

        return Success
    }
}
