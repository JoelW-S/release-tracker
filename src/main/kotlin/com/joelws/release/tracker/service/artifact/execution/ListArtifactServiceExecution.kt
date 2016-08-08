package com.joelws.release.tracker.service.artifact.execution

import com.joelws.release.tracker.dao.artifact.ArtifactDao
import com.joelws.release.tracker.entity.artifact.Artifact
import com.joelws.release.tracker.service.ServiceExecution

open class ListArtifactServiceExecution(private val artifactDao: ArtifactDao) : ServiceExecution<Nothing?, List<Artifact>> {

    override fun execute(param: Nothing?): List<Artifact> = artifactDao.list()
}
