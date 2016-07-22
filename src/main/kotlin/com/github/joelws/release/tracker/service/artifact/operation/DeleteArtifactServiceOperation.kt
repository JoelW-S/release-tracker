package com.github.joelws.release.tracker.service.artifact.operation

import com.github.joelws.release.tracker.conversion.ArtifactModelAdapter
import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.model.artifact.ArtifactModel
import com.github.joelws.release.tracker.response.RestResponse.Success
import com.github.joelws.release.tracker.response.build
import com.github.joelws.release.tracker.service.ServiceExecution
import com.github.joelws.release.tracker.service.ServiceHelper
import com.github.joelws.release.tracker.service.ServiceOperation
import javax.ws.rs.core.Response

open class DeleteArtifactServiceOperation(val helper: ServiceHelper,
                                          val deleteArtifactServiceExecution: ServiceExecution<Artifact, Unit>) : ServiceOperation<String> {

    override fun delegate(param: String?): Response {

        val artifactModelAdapter = helper.factory.getImpl(ArtifactModelAdapter::class.java)

        val artifactInQuestion = artifactModelAdapter.adapt(helper.jsonAdapter.getObjectFromJson(param, ArtifactModel::class.java))

        deleteArtifactServiceExecution.execute(artifactInQuestion)

        return Success().build()

    }


}
