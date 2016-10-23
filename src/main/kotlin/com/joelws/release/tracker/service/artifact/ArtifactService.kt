package com.joelws.release.tracker.service.artifact

import com.joelws.release.tracker.interfaces.BusinessService
import com.joelws.release.tracker.response.build
import com.joelws.release.tracker.service.ServiceOperation
import org.funktionale.option.Option.None

open class ArtifactService(private val createArtifactServiceOperation: ServiceOperation<String>,
                           private val readArtifactServiceOperation: ServiceOperation<String>,
                           private val updateArtifactServiceOperation: ServiceOperation<String>,
                           private val deleteArtifactServiceOperation: ServiceOperation<String>,
                           private val listArtifactServiceOperation: ServiceOperation<None>) : BusinessService<String> {

    override fun create(json: String) = createArtifactServiceOperation.execute(json).build()

    override fun read(identifier: String) = readArtifactServiceOperation.execute(identifier).build()

    override fun update(json: String) = updateArtifactServiceOperation.execute(json).build()

    override fun delete(identifier: String) = deleteArtifactServiceOperation.execute(identifier).build()

    override fun list() = listArtifactServiceOperation.execute(None).build()

}
