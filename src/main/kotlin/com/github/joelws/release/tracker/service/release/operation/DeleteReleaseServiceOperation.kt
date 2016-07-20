package com.github.joelws.release.tracker.service.release.operation

import com.github.joelws.release.tracker.response.RestResponse.Success
import com.github.joelws.release.tracker.response.build
import com.github.joelws.release.tracker.service.ServiceExecution
import com.github.joelws.release.tracker.service.ServiceHelper
import com.github.joelws.release.tracker.service.ServiceOperation
import javax.ws.rs.core.Response

open class DeleteReleaseServiceOperation(val helper: ServiceHelper,
                                         val deleteReleaseServiceExecution: ServiceExecution<String?, Unit>) : ServiceOperation<String> {

    override fun delegate(param: String?): Response {

        deleteReleaseServiceExecution
                .execute(param)

        return Success().build()
    }
}
