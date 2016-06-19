package com.github.joelws.release.tracker.service.release.operation

import com.github.joelws.release.tracker.conversion.ReleaseToReleaseDtoAdapter
import com.github.joelws.release.tracker.response.RestResponse.NotFound
import com.github.joelws.release.tracker.response.RestResponse.SuccessWithEntity
import com.github.joelws.release.tracker.response.build
import com.github.joelws.release.tracker.service.ServiceHelper
import com.github.joelws.release.tracker.service.ServiceOperation
import com.github.joelws.release.tracker.service.release.execution.ListReleaseServiceExecution
import javax.ws.rs.core.Response


open class ListReleaseServiceOperation(private val helper: ServiceHelper,
                                       private val listReleaseServiceExecution: ListReleaseServiceExecution) : ServiceOperation<Nothing?> {

    override fun delegate(param: Nothing?): Response {

        val resultList = listReleaseServiceExecution.execute(null)

        return if (resultList.isNotEmpty()) {
            val adapter = helper.factory.getImpl(ReleaseToReleaseDtoAdapter::class.java)
            val adaptedResultList = resultList.map { adapter.adapt(it) }
            SuccessWithEntity(adaptedResultList).build()
        } else {
            NotFound("No releases exist").build()
        }
    }
}
