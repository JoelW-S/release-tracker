package com.github.joelws.release.tracker.service.release.operation;

import com.github.joelws.release.tracker.conversion.ReleaseAdapter
import com.github.joelws.release.tracker.conversion.ReleaseModelAdapter
import com.github.joelws.release.tracker.entity.release.Release
import com.github.joelws.release.tracker.model.release.ReleaseModel
import com.github.joelws.release.tracker.response.RestResponse.NotFound
import com.github.joelws.release.tracker.response.RestResponse.SuccessWithEntity
import com.github.joelws.release.tracker.response.build
import com.github.joelws.release.tracker.service.ServiceExecution
import com.github.joelws.release.tracker.service.ServiceHelper
import com.github.joelws.release.tracker.service.ServiceOperation
import javax.ws.rs.core.Response

open class UpdateReleaseServiceOperation(private val helper: ServiceHelper,
                                         private val updateReleaseServiceExecution: ServiceExecution<Release, Release?>) : ServiceOperation<String> {
    override fun delegate(param: String?): Response {
        val adapter = helper.factory.getImpl(ReleaseModelAdapter::class.java)

        val result = updateReleaseServiceExecution
                .execute(adapter.adapt(helper.jsonAdapter.getObjectFromJson(param, ReleaseModel::class.java)))

        return if (result != null) {

            val adapterForResponse = helper.factory.getImpl(ReleaseAdapter::class.java)

            val adaptedResult = adapterForResponse.adapt(result)

            SuccessWithEntity(adaptedResult).build()

        } else {
            NotFound("Release doesn't exist").build()
        }
    }
}
