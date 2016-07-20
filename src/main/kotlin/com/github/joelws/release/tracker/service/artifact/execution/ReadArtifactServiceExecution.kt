package com.github.joelws.release.tracker.service.artifact.execution

import com.github.joelws.release.tracker.dao.artifact.ArtifactDao
import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.service.ServiceExecution

open class ReadArtifactServiceExecution(private val artifactDao: ArtifactDao) : ServiceExecution<Artifact, Artifact?> {

    override fun execute(param: Artifact): Artifact? = artifactDao.read(param.id)
}
