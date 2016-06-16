package com.github.joelws.release.tracker.service.artifact.operation;

import com.github.joelws.release.tracker.conversion.ArtifactToArtifactDtoAdapter
import com.github.joelws.release.tracker.response.RestResponse.NotFound
import com.github.joelws.release.tracker.response.RestResponse.SuccessWithEntity
import com.github.joelws.release.tracker.response.build
import com.github.joelws.release.tracker.service.ServiceHelper
import com.github.joelws.release.tracker.service.ServiceOperation
import com.github.joelws.release.tracker.service.artifact.execution.ListArtifactServiceExecution
import org.apache.log4j.Logger
import javax.ws.rs.core.Response

class ListArtifactServiceOperation(val helper: ServiceHelper,
                                   val listArtifactServiceExecution: ListArtifactServiceExecution) : ServiceOperation<Nothing>() {

    companion object {
        private val LOGGER = Logger.getLogger(ListArtifactServiceOperation::class.java)
    }

    override fun delegate(nothing: Nothing?): Response {
        LOGGER.info("Starting List service operation:")

        val result = listArtifactServiceExecution.execute(nothing)

        return if (!result.isEmpty()) {
            LOGGER.info("Starting adaption process: ")
            val adapter = helper.factory.getImpl(ArtifactToArtifactDtoAdapter::class.java)
            val adaptedResultList = result.map { adapter.adapt(it) }
            SuccessWithEntity(adaptedResultList).build()

        } else {
            NotFound("No artifacts could be found").build()
        }

    }
}
