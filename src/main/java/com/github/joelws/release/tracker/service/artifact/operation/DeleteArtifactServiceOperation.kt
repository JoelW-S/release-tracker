package com.github.joelws.release.tracker.service.artifact.operation

import com.github.joelws.release.tracker.conversion.ArtifactDtoToArtifactAdapter
import com.github.joelws.release.tracker.dto.artifact.ArtifactDto
import com.github.joelws.release.tracker.response.RestResponse.Success
import com.github.joelws.release.tracker.response.build
import com.github.joelws.release.tracker.service.ServiceHelper
import com.github.joelws.release.tracker.service.ServiceOperation
import com.github.joelws.release.tracker.service.artifact.execution.DeleteArtifactServiceExecution
import javax.ws.rs.core.Response

open class DeleteArtifactServiceOperation(val helper: ServiceHelper,
                                          val deleteArtifactServiceExecution: DeleteArtifactServiceExecution) : ServiceOperation<String>() {
    override fun delegate(json: String?): Response {
        val artifactDtoToArtifactAdapter = helper.factory.getImpl(ArtifactDtoToArtifactAdapter::class.java)

        val artifactInQuestion = artifactDtoToArtifactAdapter.adapt(helper.jsonAdapter.getObjectFromJson(json, ArtifactDto::class.java))

        deleteArtifactServiceExecution.execute(artifactInQuestion)
        return Success().build()

    }


}
