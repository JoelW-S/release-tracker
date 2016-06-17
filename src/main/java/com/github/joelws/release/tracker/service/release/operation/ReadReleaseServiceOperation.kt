package com.github.joelws.release.tracker.service.release.operation

import com.github.joelws.release.tracker.conversion.ReleaseToReleaseDtoAdapter
import com.github.joelws.release.tracker.response.RestResponse.NotFound
import com.github.joelws.release.tracker.response.RestResponse.SuccessWithEntity
import com.github.joelws.release.tracker.response.build
import com.github.joelws.release.tracker.service.ServiceHelper
import com.github.joelws.release.tracker.service.ServiceOperation
import com.github.joelws.release.tracker.service.release.execution.ReadReleaseServiceExecution
import javax.ws.rs.core.Response

open class ReadReleaseServiceOperation(private val helper: ServiceHelper,
                                       private val readReleaseServiceExecution: ReadReleaseServiceExecution) : ServiceOperation<String> {

    override fun delegate(param: String?): Response {

        val result = readReleaseServiceExecution.execute(param)

        return if (result != null) {
            val adapter = helper.factory.getImpl(ReleaseToReleaseDtoAdapter::class.java)
            val adaptedResult = adapter.adapt(result)
            SuccessWithEntity(adaptedResult).build()
        } else {
            NotFound("Release doesn't exist").build()
        }
    }
}
