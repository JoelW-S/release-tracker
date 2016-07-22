package com.github.joelws.release.tracker.service.artifact.operation


import com.github.joelws.release.tracker.conversion.ArtifactAdapter
import com.github.joelws.release.tracker.conversion.ArtifactModelAdapter
import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.model.artifact.ArtifactModel
import com.github.joelws.release.tracker.response.RestResponse.BadRequest
import com.github.joelws.release.tracker.response.RestResponse.SuccessWithEntity
import com.github.joelws.release.tracker.response.build
import com.github.joelws.release.tracker.service.ServiceExecution
import com.github.joelws.release.tracker.service.ServiceHelper
import com.github.joelws.release.tracker.service.ServiceOperation
import javax.ws.rs.core.Response


open class CreateArtifactServiceOperation(private val helper: ServiceHelper,
                                          private val createArtifactServiceExecution: ServiceExecution<Artifact, Artifact?>) : ServiceOperation<String> {

    override fun delegate(param: String?): Response {

        val artifactModelAdapter = helper.factory.getImpl(ArtifactModelAdapter::class.java)

        val result = createArtifactServiceExecution
                .execute(artifactModelAdapter.adapt(helper.jsonAdapter.getObjectFromJson(param, ArtifactModel::class.java)))

        return if (result != null) {

            val fromArtifactToArtifactDtoAdapter = helper.factory.getImpl(ArtifactAdapter::class.java)

            val adaptedResult = fromArtifactToArtifactDtoAdapter.adapt(result)

            SuccessWithEntity(adaptedResult).build()

        } else {
            BadRequest("Artifact already exists").build()
        }
    }


}
