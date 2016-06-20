package com.github.joelws.release.tracker.service.artifact.operation

import com.github.joelws.release.tracker.conversion.ArtifactDtoToArtifactAdapter
import com.github.joelws.release.tracker.conversion.ArtifactToArtifactDtoAdapter
import com.github.joelws.release.tracker.dto.artifact.ArtifactDto
import com.github.joelws.release.tracker.response.RestResponse.NotFound
import com.github.joelws.release.tracker.response.RestResponse.SuccessWithEntity
import com.github.joelws.release.tracker.response.build
import com.github.joelws.release.tracker.service.ServiceHelper
import com.github.joelws.release.tracker.service.ServiceOperation
import com.github.joelws.release.tracker.service.artifact.execution.ReadArtifactServiceExecution
import org.apache.log4j.Logger
import javax.ws.rs.core.Response

open class ReadArtifactServiceOperation(val helper: ServiceHelper,
                                        val readArtifactServiceExecution: ReadArtifactServiceExecution) : ServiceOperation<String> {
    companion object {
        private val LOGGER = Logger.getLogger(ReadArtifactServiceOperation::class.java)
    }

    override fun delegate(param: String?): Response {

        LOGGER.info("Starting ReadArtifactServiceOperation, In: $param")
        val fromArtifactDtoToArtifactAdapter = helper.factory.getImpl(ArtifactDtoToArtifactAdapter::class.java)

        val artifactDto = helper.jsonAdapter.getObjectFromJson(param, ArtifactDto::class.java)

        val result = readArtifactServiceExecution.execute(fromArtifactDtoToArtifactAdapter.adapt(artifactDto))

        val response = if (result != null) {
            LOGGER.info("Adapting entity for client: ")
            val fromArtifactToArtifactDtoAdapter = helper.factory.getImpl(ArtifactToArtifactDtoAdapter::class.java)
            val adaptedResult = fromArtifactToArtifactDtoAdapter.adapt(result)
            SuccessWithEntity(adaptedResult)
        } else {
            NotFound("Artifact doesn't exist")
        }
        return response.build()
    }
}
