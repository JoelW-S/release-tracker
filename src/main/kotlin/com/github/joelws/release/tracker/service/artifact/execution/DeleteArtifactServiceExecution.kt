package com.github.joelws.release.tracker.service.artifact.execution


import com.github.joelws.release.tracker.dao.artifact.ArtifactDao
import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.service.ServiceExecution
import org.apache.log4j.Logger

open class DeleteArtifactServiceExecution(private val artifactDao: ArtifactDao) : ServiceExecution<Artifact, Unit> {

    companion object {
        private val LOGGER = Logger.getLogger(DeleteArtifactServiceExecution::class.java)
    }

    override fun execute(param: Artifact) {

        val result = artifactDao.read(param.id)
        if (result != null) {
            LOGGER.info("Deleting artifact with the following: $param")
            artifactDao.delete(param.id)
        }
    }
}
