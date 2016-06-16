package com.github.joelws.release.tracker.service.artifact.execution

import com.github.joelws.release.tracker.dao.artifact.ArtifactDao
import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.service.ServiceExecution
import org.apache.log4j.Logger

open class ListArtifactServiceExecution(val artifactDao: ArtifactDao) : ServiceExecution<Nothing?, List<Artifact>> {


    companion object {
        private val LOGGER = Logger.getLogger(ListArtifactServiceExecution::class.java)
    }

    override fun execute(param: Nothing?): List<Artifact> {
        LOGGER.info("Finding all artifacts")

        return artifactDao.list()
    }
}
