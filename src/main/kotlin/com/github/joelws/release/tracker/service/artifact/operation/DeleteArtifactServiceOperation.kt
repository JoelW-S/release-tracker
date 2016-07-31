package com.github.joelws.release.tracker.service.artifact.operation

import com.github.joelws.release.tracker.conversion.ArtifactModelAdapter
import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.interfaces.Adapter
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

        @Suppress("UNCHECKED_CAST")
        val artifactModelAdapter: Adapter<ArtifactModel, Artifact> = helper.adapterFactory.getAdapter(ArtifactModelAdapter::class.java) as Adapter<ArtifactModel, Artifact>

        val artifactInQuestion = artifactModelAdapter.adapt(helper.jsonAdapter.getObjectFromJson(param, ArtifactModel::class.java))

        deleteArtifactServiceExecution.execute(artifactInQuestion)

        return Success().build()

    }


}
