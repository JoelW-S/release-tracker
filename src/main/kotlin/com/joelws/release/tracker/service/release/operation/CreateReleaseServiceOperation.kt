package com.joelws.release.tracker.service.release.operation

import com.joelws.release.tracker.conversion.ReleaseAdapter
import com.joelws.release.tracker.conversion.ReleaseModelAdapter
import com.joelws.release.tracker.entity.release.Release
import com.joelws.release.tracker.interfaces.Adapter
import com.joelws.release.tracker.model.release.ReleaseModel
import com.joelws.release.tracker.response.RestResponse.BadRequest
import com.joelws.release.tracker.response.RestResponse.SuccessWithEntity
import com.joelws.release.tracker.response.build
import com.joelws.release.tracker.service.ServiceExecution
import com.joelws.release.tracker.service.ServiceHelper
import com.joelws.release.tracker.service.ServiceOperation
import javax.ws.rs.core.Response

open class CreateReleaseServiceOperation(private val helper: ServiceHelper,
                                         private val createReleaseServiceExecution: ServiceExecution<Release, Release?>) : ServiceOperation<String> {

    override fun delegate(param: String?): Response {
        @Suppress("UNCHECKED_CAST")
        val releaseModelAdapter: Adapter<ReleaseModel, Release> = helper.adapterFactory.getAdapter(ReleaseModelAdapter::class.java) as Adapter<ReleaseModel, Release>

        val result = createReleaseServiceExecution
                .execute(releaseModelAdapter.adapt(helper.jsonAdapter.getObjectFromJson(param, ReleaseModel::class.java)))

        return if (result != null) {

            @Suppress("UNCHECKED_CAST")
            val releaseAdapter: Adapter<Release, ReleaseModel> = helper.adapterFactory.getAdapter(ReleaseAdapter::class.java) as Adapter<Release, ReleaseModel>

            val adaptedResult = releaseAdapter.adapt(result)

            SuccessWithEntity(adaptedResult).build()

        } else {
            BadRequest("Release already exists").build()
        }
    }
}
