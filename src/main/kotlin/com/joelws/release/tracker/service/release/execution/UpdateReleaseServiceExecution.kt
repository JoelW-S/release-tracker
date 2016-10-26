package com.joelws.release.tracker.service.release.execution

import com.joelws.release.tracker.dao.release.ReleaseDao
import com.joelws.release.tracker.entity.release.Release
import com.joelws.release.tracker.service.ServiceExecution
import org.funktionale.option.Option
import org.funktionale.option.Option.None
import org.funktionale.option.Option.Some

open class UpdateReleaseServiceExecution(private val releaseDao: ReleaseDao) : ServiceExecution<Release, Option<Release>> {

    override fun execute(param: Release): Option<Release> {
        return when (releaseDao.read(param.name)) {
            is Some<Release> -> releaseDao.update(param)
            is None -> None
        }
    }
}
