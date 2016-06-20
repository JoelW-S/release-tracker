package com.github.joelws.release.tracker.service.artifact

import com.github.joelws.release.tracker.interfaces.BusinessService
import com.github.joelws.release.tracker.service.artifact.operation.*

open class ArtifactService(private val createArtifactServiceOperation: CreateArtifactServiceOperation,
                           private val readArtifactServiceOperation: ReadArtifactServiceOperation,
                           private val updateArtifactServiceOperation: UpdateArtifactServiceOperation,
                           private val deleteArtifactServiceOperation: DeleteArtifactServiceOperation,
                           private val listArtifactServiceOperation: ListArtifactServiceOperation) : BusinessService<String> {

    override fun create(json: String) = createArtifactServiceOperation.execute(json)

    override fun read(identifier: String) = readArtifactServiceOperation.execute(identifier)

    override fun update(json: String) = updateArtifactServiceOperation.execute(json)

    override fun delete(identifier: String) = deleteArtifactServiceOperation.execute(identifier)

    override fun list() = listArtifactServiceOperation.execute(null)

}
