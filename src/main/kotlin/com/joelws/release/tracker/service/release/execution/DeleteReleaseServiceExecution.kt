package com.joelws.release.tracker.service.release.execution

import com.joelws.release.tracker.dao.environment.EnvironmentDao
import com.joelws.release.tracker.dao.release.ReleaseDao
import com.joelws.release.tracker.entity.release.Release
import com.joelws.release.tracker.exception.ReleaseTrackerException
import com.joelws.release.tracker.response.ErrorMessage
import com.joelws.release.tracker.service.ServiceExecution
import org.funktionale.option.Option.None
import org.funktionale.option.Option.Some

open class DeleteReleaseServiceExecution(private val releaseDao: ReleaseDao, private val environmentDao: EnvironmentDao) : ServiceExecution<String, Unit> {

    override fun execute(param: String) {

        val maybeRelease = releaseDao.read(param)


        when (maybeRelease) {
            is Some<Release> -> {
                checkIfDependency(maybeRelease.get())
            }
            is None -> throw ReleaseTrackerException(ErrorMessage.RELEASE_NOT_EXIST)
        }

    }


    private fun checkIfDependency(release: Release) {
        val isEnvironmentDependency = environmentDao.list().map { environment -> environment.release }.contains(release)

        if (isEnvironmentDependency) {
            throw ReleaseTrackerException(ErrorMessage.RELEASE_DEPENDENCY)
        } else
            checkIfHotfix(release)
    }

    private fun checkIfHotfix(release: Release) {
        return if (releaseDao.list().flatMap { release -> release.hotfixes }.contains(release)) throw ReleaseTrackerException(ErrorMessage.RELEASE_IS_HOTFIX) else releaseDao.delete(release.name)
    }
}
