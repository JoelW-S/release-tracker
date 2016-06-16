package com.github.joelws.release.tracker.service.artifact.operation


import com.github.joelws.release.tracker.conversion.ArtifactDtoToArtifactAdapter
import com.github.joelws.release.tracker.conversion.ArtifactToArtifactDtoAdapter
import com.github.joelws.release.tracker.dto.artifact.ArtifactDto
import com.github.joelws.release.tracker.response.RestResponse.BadRequest
import com.github.joelws.release.tracker.response.RestResponse.SuccessWithEntity
import com.github.joelws.release.tracker.response.build
import com.github.joelws.release.tracker.service.ServiceHelper
import com.github.joelws.release.tracker.service.ServiceOperation
import com.github.joelws.release.tracker.service.artifact.execution.CreateArtifactServiceExecution
import javax.ws.rs.core.Response


open class CreateArtifactServiceOperation(val helper: ServiceHelper,
                                          val createArtifactServiceExecution: CreateArtifactServiceExecution) : ServiceOperation<String>() {
    override fun delegate(json: String?): Response {

        val fromArtifactDtoToArtifactAdapter = helper.factory.getImpl(ArtifactDtoToArtifactAdapter::class.java)

        val result = createArtifactServiceExecution
                .execute(fromArtifactDtoToArtifactAdapter.adapt(helper.jsonAdapter.getObjectFromJson(json, ArtifactDto::class.java)))


        return if (result != null) {

            val fromArtifactToArtifactDtoAdapter = helper.factory.getImpl(ArtifactToArtifactDtoAdapter::class.java)
            val adaptedResult = fromArtifactToArtifactDtoAdapter.adapt(result)
            SuccessWithEntity(adaptedResult).build()

        } else {

            BadRequest("Artifact already exists").build()
        }
    }

}
