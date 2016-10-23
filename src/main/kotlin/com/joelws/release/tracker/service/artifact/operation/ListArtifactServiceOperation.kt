package com.joelws.release.tracker.service.artifact.operation

import com.joelws.release.tracker.conversion.ArtifactAdapter
import com.joelws.release.tracker.entity.artifact.Artifact
import com.joelws.release.tracker.response.ErrorMessage
import com.joelws.release.tracker.response.RestResponse
import com.joelws.release.tracker.response.RestResponse.SuccessWithEntity
import com.joelws.release.tracker.service.ServiceExecution
import com.joelws.release.tracker.service.ServiceHelper
import com.joelws.release.tracker.service.ServiceOperation
import org.funktionale.option.Option.None

class ListArtifactServiceOperation(private val helper: ServiceHelper,
                                   private val listArtifactServiceExecution: ServiceExecution<None, List<Artifact>>) : ServiceOperation<None> {

    override fun delegate(param: None): RestResponse {

        val result = listArtifactServiceExecution.execute(param)

        return if (result.isNotEmpty()) {

            val artifactAdapter = helper.adapterFactory.getAdapter(ArtifactAdapter::class.java)

            val adaptedResultList = result.map { artifact -> artifactAdapter.adapt(artifact) }

            SuccessWithEntity(adaptedResultList)

        } else {
            ErrorMessage.ARTIFACTS_NOT_FOUND.response
        }

    }
}
