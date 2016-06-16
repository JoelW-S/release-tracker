package com.github.joelws.release.tracker.service.artifact.execution

import com.github.joelws.release.tracker.dao.artifact.ArtifactDao
import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.service.ServiceExecution
import org.apache.log4j.Logger

open class ReadArtifactServiceExecution(val artifactDao: ArtifactDao) : ServiceExecution<Artifact, Artifact?> {


    companion object {
        private var LOGGER = Logger.getLogger(ReadArtifactServiceExecution::class.java)
    }

    override fun execute(param: Artifact): Artifact? {
        LOGGER.info("Finding artifact with the following: ${param.id?.groupId}, ${param.id?.artifactId}, ${param.id?.version}")

        return artifactDao.read(param.id)
    }
}
