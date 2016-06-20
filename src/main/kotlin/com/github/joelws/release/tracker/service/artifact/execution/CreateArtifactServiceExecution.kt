package com.github.joelws.release.tracker.service.artifact.execution

import com.github.joelws.release.tracker.dao.artifact.ArtifactDao
import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.service.ServiceExecution
import org.apache.log4j.Logger

open class CreateArtifactServiceExecution(private val artifactDao: ArtifactDao) : ServiceExecution<Artifact, Artifact?> {

    companion object {
        private val LOGGER = Logger.getLogger(CreateArtifactServiceExecution::class.java)
    }

    override fun execute(param: Artifact): Artifact? {

        LOGGER.info("Creating artifact: ${param}")

        return when {
            artifactDao.read(param.id) == null -> artifactDao.create(param)
            else -> null
        }
    }
}
