package com.joelws.release.tracker.service.artifact.execution


import com.joelws.release.tracker.dao.artifact.ArtifactDao
import com.joelws.release.tracker.dao.release.ReleaseDao
import com.joelws.release.tracker.entity.artifact.Artifact
import com.joelws.release.tracker.exception.ReleaseTrackerException
import com.joelws.release.tracker.response.ErrorMessage
import com.joelws.release.tracker.service.ServiceExecution

open class DeleteArtifactServiceExecution(private val artifactDao: ArtifactDao, private val releaseDao: ReleaseDao) : ServiceExecution<Artifact, Unit> {

    override fun execute(param: Artifact) {
        val result = artifactDao.read(param.id)

        val hasDependency = if (result.nonEmpty()) releaseDao.list().flatMap { release -> release.artifacts }.contains(result.get()) else throw ReleaseTrackerException(ErrorMessage.ARTIFACT_NOT_EXIST)

        if (hasDependency) throw ReleaseTrackerException(ErrorMessage.ARTIFACT_DEPENDENCY) else artifactDao.delete(param.id)
    }
}
