package com.joelws.release.tracker.service.artifact.operation

import com.joelws.release.tracker.conversion.ArtifactModelAdapter
import com.joelws.release.tracker.entity.artifact.Artifact
import com.joelws.release.tracker.model.artifact.ArtifactModel
import com.joelws.release.tracker.response.RestResponse
import com.joelws.release.tracker.response.RestResponse.Success
import com.joelws.release.tracker.service.ServiceExecution
import com.joelws.release.tracker.service.ServiceHelper
import com.joelws.release.tracker.service.ServiceOperation
import com.joelws.release.tracker.util.getObjectFromJson

open class DeleteArtifactServiceOperation(val helper: ServiceHelper,
                                          val deleteArtifactServiceExecution: ServiceExecution<Artifact, Unit>) : ServiceOperation<String> {

    override fun delegate(param: String): RestResponse {

        val artifactModelAdapter = helper.adapterFactory.getAdapter(ArtifactModelAdapter::class.java)

        val artifactInQuestion = artifactModelAdapter.adapt(getObjectFromJson<ArtifactModel>(param))

        deleteArtifactServiceExecution.execute(artifactInQuestion)

        return Success

    }


}
