package com.joelws.release.tracker.service.artifact.execution

import com.joelws.release.tracker.dao.artifact.ArtifactDao
import com.joelws.release.tracker.entity.artifact.Artifact
import com.joelws.release.tracker.service.ServiceExecution
import org.funktionale.option.Option
import org.funktionale.option.Option.None
import org.funktionale.option.Option.Some

open class CreateArtifactServiceExecution(private val artifactDao: ArtifactDao) : ServiceExecution<Artifact, Option<Artifact>> {

    override fun execute(param: Artifact): Option<Artifact> {

        val maybeArtifact = artifactDao.read(param.id)
        return when (maybeArtifact) {
            is Some<Artifact> -> None
            is None -> artifactDao.create(param)
        }

    }
}

