package com.joelws.release.tracker.service.release.operation

import com.joelws.release.tracker.response.RestResponse.Success
import com.joelws.release.tracker.response.build
import com.joelws.release.tracker.service.ServiceExecution
import com.joelws.release.tracker.service.ServiceOperation
import javax.ws.rs.core.Response

open class DeleteReleaseServiceOperation(private val deleteReleaseServiceExecution: ServiceExecution<String?, Unit>) : ServiceOperation<String> {

    override fun delegate(param: String?): Response {

        deleteReleaseServiceExecution
                .execute(param)

        return Success().build()
    }
}
