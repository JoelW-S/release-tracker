package com.github.joelws.release.tracker.service.artifact

import com.github.joelws.release.tracker.interfaces.BusinessService
import com.github.joelws.release.tracker.service.ServiceOperation

open class ArtifactService(private val createArtifactServiceOperation: ServiceOperation<String>,
                           private val readArtifactServiceOperation: ServiceOperation<String>,
                           private val updateArtifactServiceOperation: ServiceOperation<String>,
                           private val deleteArtifactServiceOperation: ServiceOperation<String>,
                           private val listArtifactServiceOperation: ServiceOperation<Nothing>) : BusinessService<String> {

    override fun create(json: String) = createArtifactServiceOperation.execute(json)

    override fun read(identifier: String) = readArtifactServiceOperation.execute(identifier)

    override fun update(json: String) = updateArtifactServiceOperation.execute(json)

    override fun delete(identifier: String) = deleteArtifactServiceOperation.execute(identifier)

    override fun list() = listArtifactServiceOperation.execute(null)

}
