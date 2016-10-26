package com.joelws.release.tracker.service.artifact.operation


import com.joelws.release.tracker.conversion.ArtifactAdapter
import com.joelws.release.tracker.conversion.ArtifactModelAdapter
import com.joelws.release.tracker.entity.artifact.Artifact
import com.joelws.release.tracker.model.artifact.ArtifactModel
import com.joelws.release.tracker.response.ErrorMessage
import com.joelws.release.tracker.response.RestResponse
import com.joelws.release.tracker.response.RestResponse.SuccessWithEntity
import com.joelws.release.tracker.service.ServiceExecution
import com.joelws.release.tracker.service.ServiceHelper
import com.joelws.release.tracker.service.ServiceOperation
import com.joelws.release.tracker.util.getObjectFromJson
import org.funktionale.option.Option
import org.funktionale.option.Option.None
import org.funktionale.option.Option.Some


open class CreateArtifactServiceOperation(private val helper: ServiceHelper,
                                          private val createArtifactServiceExecution: ServiceExecution<Artifact, Option<Artifact>>) : ServiceOperation<String> {

    override fun delegate(param: String): RestResponse {

        val artifactModelAdapter = helper.adapterFactory.getAdapter(ArtifactModelAdapter::class.java)

        val result = createArtifactServiceExecution
                .execute(artifactModelAdapter.adapt(getObjectFromJson<ArtifactModel>(param)))

        val artifactAdapter = helper.adapterFactory.getAdapter(ArtifactAdapter::class.java)

        return when (result) {
            is Some<Artifact> ->
                result.map { artifact -> artifactAdapter.adapt(artifact) }
                        .map { artifactModel -> SuccessWithEntity(artifactModel) }
                        .get()

            is None -> ErrorMessage.ARTIFACT_EXIST.response

        }

    }

}
