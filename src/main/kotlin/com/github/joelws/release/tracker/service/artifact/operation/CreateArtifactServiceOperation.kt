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
import org.apache.log4j.Logger
import javax.ws.rs.core.Response


open class CreateArtifactServiceOperation(private val helper: ServiceHelper,
                                          private val createArtifactServiceExecution: CreateArtifactServiceExecution) : ServiceOperation<String> {
    companion object {
        private val LOGGER = Logger.getLogger(CreateArtifactServiceOperation::class.java)
    }

    override fun delegate(param: String?): Response {

        LOGGER.info("Starting CreateArtifactServiceOperation, In: $param")

        val fromArtifactDtoToArtifactAdapter = helper.factory.getImpl(ArtifactDtoToArtifactAdapter::class.java)

        val result = createArtifactServiceExecution
                .execute(fromArtifactDtoToArtifactAdapter.adapt(helper.jsonAdapter.getObjectFromJson(param, ArtifactDto::class.java)))

        return if (result != null) {

            val fromArtifactToArtifactDtoAdapter = helper.factory.getImpl(ArtifactToArtifactDtoAdapter::class.java)
            val adaptedResult = fromArtifactToArtifactDtoAdapter.adapt(result)
            SuccessWithEntity(adaptedResult).build()

        } else {

            BadRequest("Artifact already exists").build()
        }
    }


}
