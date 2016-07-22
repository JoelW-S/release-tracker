package com.github.joelws.release.tracker.service.artifact.execution


import com.github.joelws.release.tracker.dao.artifact.ArtifactDao
import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.service.ServiceExecution

open class DeleteArtifactServiceExecution(private val artifactDao: ArtifactDao) : ServiceExecution<Artifact, Unit> {

    override fun execute(param: Artifact) {

        val result = artifactDao.read(param.id)
        if (result != null) {
            artifactDao.delete(param.id)
        }
    }
}
