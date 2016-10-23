package com.joelws.release.tracker.service.release.operation

import com.joelws.release.tracker.conversion.ReleaseAdapter
import com.joelws.release.tracker.entity.release.Release
import com.joelws.release.tracker.response.ErrorMessage
import com.joelws.release.tracker.response.RestResponse
import com.joelws.release.tracker.response.RestResponse.SuccessWithEntity
import com.joelws.release.tracker.service.ServiceExecution
import com.joelws.release.tracker.service.ServiceHelper
import com.joelws.release.tracker.service.ServiceOperation
import org.funktionale.option.Option.None


open class ListReleaseServiceOperation(private val helper: ServiceHelper,
                                       private val listReleaseServiceExecution: ServiceExecution<None, List<Release>>) : ServiceOperation<None> {

    override fun delegate(param: None): RestResponse {

        val resultList = listReleaseServiceExecution.execute(param)

        return if (resultList.isNotEmpty()) {
            val releaseAdapter = helper.adapterFactory.getAdapter(ReleaseAdapter::class.java)
            val adaptedResultList = resultList.map { release -> releaseAdapter.adapt(release) }
            SuccessWithEntity(adaptedResultList)
        } else {
            ErrorMessage.RELEASES_NOT_FOUND.response
        }
    }
}
