package com.joelws.release.tracker.service.artifact.operation

import com.joelws.release.tracker.conversion.ArtifactAdapter
import com.joelws.release.tracker.conversion.ArtifactModelAdapter
import com.joelws.release.tracker.entity.artifact.Artifact
import com.joelws.release.tracker.interfaces.Adapter
import com.joelws.release.tracker.model.artifact.ArtifactModel
import com.joelws.release.tracker.response.RestResponse.NotFound
import com.joelws.release.tracker.response.RestResponse.SuccessWithEntity
import com.joelws.release.tracker.response.build
import com.joelws.release.tracker.service.ServiceExecution
import com.joelws.release.tracker.service.ServiceHelper
import com.joelws.release.tracker.service.ServiceOperation
import javax.ws.rs.core.Response

open class ReadArtifactServiceOperation(private val helper: ServiceHelper,
                                        private val readArtifactServiceExecution: ServiceExecution<Artifact, Artifact?>) : ServiceOperation<String> {

    override fun delegate(param: String?): Response {

        @Suppress("UNCHECKED_CAST")
        val artifactModelAdapter: Adapter<ArtifactModel, Artifact> = helper.adapterFactory.getAdapter(ArtifactModelAdapter::class.java) as Adapter<ArtifactModel, Artifact>

        val artifactModel = helper.jsonAdapter.getObjectFromJson(param, ArtifactModel::class.java)

        val result = readArtifactServiceExecution.execute(artifactModelAdapter.adapt(artifactModel))

        val response = if (result != null) {

            @Suppress("UNCHECKED_CAST")
            val artifactAdapter: Adapter<Artifact, ArtifactModel> = helper.adapterFactory.getAdapter(ArtifactAdapter::class.java) as Adapter<Artifact, ArtifactModel>

            val adaptedResult = artifactAdapter.adapt(result)

            SuccessWithEntity(adaptedResult)
        } else {
            NotFound("Artifact doesn't exist")
        }
        return response.build()
    }
}
