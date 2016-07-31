package com.github.joelws.release.tracker.service.artifact.operation

import com.github.joelws.release.tracker.conversion.ArtifactAdapter
import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.interfaces.Adapter
import com.github.joelws.release.tracker.model.artifact.ArtifactModel
import com.github.joelws.release.tracker.response.RestResponse.NotFound
import com.github.joelws.release.tracker.response.RestResponse.SuccessWithEntity
import com.github.joelws.release.tracker.response.build
import com.github.joelws.release.tracker.service.ServiceExecution
import com.github.joelws.release.tracker.service.ServiceHelper
import com.github.joelws.release.tracker.service.ServiceOperation
import javax.ws.rs.core.Response

class ListArtifactServiceOperation(private val helper: ServiceHelper,
                                   private val listArtifactServiceExecution: ServiceExecution<Nothing?, List<Artifact>>) : ServiceOperation<Nothing> {

    override fun delegate(param: Nothing?): Response {

        val result = listArtifactServiceExecution.execute(param)

        return if (!result.isEmpty()) {

            @Suppress("UNCHECKED_CAST")
            val artifactAdapter: Adapter<Artifact, ArtifactModel> = helper.adapterFactory.getAdapter(ArtifactAdapter::class.java) as Adapter<Artifact, ArtifactModel>

            val adaptedResultList = result.map { artifactAdapter.adapt(it) }

            SuccessWithEntity(adaptedResultList).build()

        } else {
            NotFound("No artifacts could be found").build()
        }

    }
}
