package com.github.joelws.release.tracker.service.release.operation

import com.github.joelws.release.tracker.conversion.ReleaseAdapter
import com.github.joelws.release.tracker.entity.release.Release
import com.github.joelws.release.tracker.interfaces.Adapter
import com.github.joelws.release.tracker.model.release.ReleaseModel
import com.github.joelws.release.tracker.response.RestResponse.NotFound
import com.github.joelws.release.tracker.response.RestResponse.SuccessWithEntity
import com.github.joelws.release.tracker.response.build
import com.github.joelws.release.tracker.service.ServiceExecution
import com.github.joelws.release.tracker.service.ServiceHelper
import com.github.joelws.release.tracker.service.ServiceOperation
import javax.ws.rs.core.Response


open class ListReleaseServiceOperation(private val helper: ServiceHelper,
                                       private val listReleaseServiceExecution: ServiceExecution<Nothing?, List<Release>>) : ServiceOperation<Nothing?> {

    override fun delegate(param: Nothing?): Response {

        val resultList = listReleaseServiceExecution.execute(null)

        return if (resultList.isNotEmpty()) {
            @Suppress("UNCHECKED_CAST")
            val releaseAdapter: Adapter<Release, ReleaseModel> = helper.adapterFactory.getAdapter(ReleaseAdapter::class.java) as Adapter<Release, ReleaseModel>
            val adaptedResultList = resultList.map { releaseAdapter.adapt(it) }
            SuccessWithEntity(adaptedResultList).build()
        } else {
            NotFound("No releases exist").build()
        }
    }
}
