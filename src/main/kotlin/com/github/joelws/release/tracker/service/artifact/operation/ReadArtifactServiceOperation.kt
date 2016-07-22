package com.github.joelws.release.tracker.service.artifact.operation

import com.github.joelws.release.tracker.conversion.ArtifactAdapter
import com.github.joelws.release.tracker.conversion.ArtifactModelAdapter
import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.model.artifact.ArtifactModel
import com.github.joelws.release.tracker.response.RestResponse.NotFound
import com.github.joelws.release.tracker.response.RestResponse.SuccessWithEntity
import com.github.joelws.release.tracker.response.build
import com.github.joelws.release.tracker.service.ServiceExecution
import com.github.joelws.release.tracker.service.ServiceHelper
import com.github.joelws.release.tracker.service.ServiceOperation
import javax.ws.rs.core.Response

open class ReadArtifactServiceOperation(private val helper: ServiceHelper,
                                        private val readArtifactServiceExecution: ServiceExecution<Artifact, Artifact?>) : ServiceOperation<String> {

    override fun delegate(param: String?): Response {

        val fromArtifactDtoToArtifactAdapter = helper.factory.getImpl(ArtifactModelAdapter::class.java)

        val artifactDto = helper.jsonAdapter.getObjectFromJson(param, ArtifactModel::class.java)

        val result = readArtifactServiceExecution.execute(fromArtifactDtoToArtifactAdapter.adapt(artifactDto))

        val response = if (result != null) {
            val fromArtifactToArtifactDtoAdapter = helper.factory.getImpl(ArtifactAdapter::class.java)

            val adaptedResult = fromArtifactToArtifactDtoAdapter.adapt(result)

            SuccessWithEntity(adaptedResult)
        } else {
            NotFound("Artifact doesn't exist")
        }
        return response.build()
    }
}
