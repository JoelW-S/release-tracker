package com.joelws.release.tracker.service.artifact.execution

import com.joelws.release.tracker.dao.artifact.ArtifactDao
import com.joelws.release.tracker.entity.artifact.Artifact
import com.joelws.release.tracker.service.ServiceExecution
import org.funktionale.option.Option.None

open class ListArtifactServiceExecution(private val artifactDao: ArtifactDao) : ServiceExecution<None, List<Artifact>> {

    override fun execute(param: None): List<Artifact> = artifactDao.list()
}
