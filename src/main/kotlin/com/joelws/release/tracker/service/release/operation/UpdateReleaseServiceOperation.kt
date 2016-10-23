package com.joelws.release.tracker.service.release.operation

import com.joelws.release.tracker.conversion.ReleaseAdapter
import com.joelws.release.tracker.conversion.ReleaseModelAdapter
import com.joelws.release.tracker.entity.release.Release
import com.joelws.release.tracker.response.ErrorMessage
import com.joelws.release.tracker.response.RestResponse
import com.joelws.release.tracker.response.RestResponse.SuccessWithEntity
import com.joelws.release.tracker.service.ServiceExecution
import com.joelws.release.tracker.service.ServiceHelper
import com.joelws.release.tracker.service.ServiceOperation
import com.joelws.release.tracker.util.getObjectFromJson
import org.funktionale.option.Option
import org.funktionale.option.Option.None

open class UpdateReleaseServiceOperation(private val helper: ServiceHelper,
                                         private val updateReleaseServiceExecution: ServiceExecution<Release, Option<Release>>) : ServiceOperation<String> {
    override fun delegate(param: String): RestResponse {

        val adapter = helper.adapterFactory.getAdapter(ReleaseModelAdapter::class.java)

        val result = updateReleaseServiceExecution
                .execute(adapter.adapt(getObjectFromJson(param)))

        return when (result) {

            is Option.Some<Release> -> {
                val releaseAdapter = helper.adapterFactory.getAdapter(ReleaseAdapter::class.java)

                val adaptedResult = result.map { release -> releaseAdapter.adapt(release) }.get()

                SuccessWithEntity(adaptedResult)

            }
            is None -> ErrorMessage.RELEASE_NOT_FOUND.response

        }
    }
}
