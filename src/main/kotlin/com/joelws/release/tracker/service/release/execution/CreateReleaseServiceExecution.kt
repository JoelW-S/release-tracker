package com.joelws.release.tracker.service.release.execution

import com.joelws.release.tracker.dao.release.ReleaseDao
import com.joelws.release.tracker.entity.release.Release
import com.joelws.release.tracker.service.ServiceExecution
import org.funktionale.option.Option
import org.funktionale.option.Option.None
import org.funktionale.option.Option.Some

open class CreateReleaseServiceExecution(private val releaseDao: ReleaseDao) : ServiceExecution<Release, Option<Release>> {

    override fun execute(param: Release): Option<Release> {
        val maybeRelease = releaseDao.read(param.name)
        return when (maybeRelease) {
            is Some<Release> -> None
            is None -> releaseDao.create(param)
        }
    }
}
