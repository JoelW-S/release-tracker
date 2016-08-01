package com.joelws.release.tracker.service.release.operation

import com.joelws.release.tracker.conversion.ReleaseAdapter
import com.joelws.release.tracker.entity.release.Release
import com.joelws.release.tracker.interfaces.Adapter
import com.joelws.release.tracker.model.release.ReleaseModel
import com.joelws.release.tracker.response.RestResponse.NotFound
import com.joelws.release.tracker.response.RestResponse.SuccessWithEntity
import com.joelws.release.tracker.response.build
import com.joelws.release.tracker.service.ServiceExecution
import com.joelws.release.tracker.service.ServiceHelper
import com.joelws.release.tracker.service.ServiceOperation
import javax.ws.rs.core.Response

open class ReadReleaseServiceOperation(private val helper: ServiceHelper,
                                       private val readReleaseServiceExecution: ServiceExecution<String?, Release?>) : ServiceOperation<String> {

    override fun delegate(param: String?): Response {

        val result = readReleaseServiceExecution.execute(param)

        return if (result != null) {

            @Suppress("UNCHECKED_CAST")
            val releaseAdapter: Adapter<Release, ReleaseModel> = helper.adapterFactory.getAdapter(ReleaseAdapter::class.java) as Adapter<Release, ReleaseModel>

            val adaptedResult = releaseAdapter.adapt(result)

            SuccessWithEntity(adaptedResult).build()

        } else {
            NotFound("Release doesn't exist").build()
        }
    }
}
