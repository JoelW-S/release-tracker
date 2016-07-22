package com.github.joelws.release.tracker.service.artifact.execution

import com.github.joelws.release.tracker.dao.artifact.ArtifactDao
import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.service.ServiceExecution

open class ListArtifactServiceExecution(private val artifactDao: ArtifactDao) : ServiceExecution<Nothing?, List<Artifact>> {

    override fun execute(param: Nothing?): List<Artifact> = artifactDao.list()
}
