package com.joelws.release.tracker.service.artifact.execution

import com.joelws.release.tracker.dao.artifact.ArtifactDao
import com.joelws.release.tracker.entity.artifact.Artifact
import com.joelws.release.tracker.service.ServiceExecution

open class CreateArtifactServiceExecution(private val artifactDao: ArtifactDao) : ServiceExecution<Artifact, Artifact?> {

    override fun execute(param: Artifact): Artifact? = when {
            artifactDao.read(param.id) == null -> artifactDao.create(param)
            else -> null
        }
    }
