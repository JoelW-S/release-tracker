package com.joelws.release.tracker.service.artifact.execution


import com.joelws.release.tracker.dao.artifact.ArtifactDao
import com.joelws.release.tracker.entity.artifact.Artifact
import com.joelws.release.tracker.service.ServiceExecution

open class DeleteArtifactServiceExecution(private val artifactDao: ArtifactDao) : ServiceExecution<Artifact, Unit> {

    override fun execute(param: Artifact) {

        val result = artifactDao.read(param.id)
        if (result.nonEmpty()) {
            artifactDao.delete(param.id)
        }
    }
}
