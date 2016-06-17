package com.github.joelws.release.tracker.service.release.execution

import com.github.joelws.release.tracker.dao.release.ReleaseDao
import com.github.joelws.release.tracker.entity.release.Release
import com.github.joelws.release.tracker.service.ServiceExecution

open class CreateReleaseServiceExecution(private val releaseDao: ReleaseDao) : ServiceExecution<Release, Release?> {

    override fun execute(param: Release): Release? {
        return if (releaseDao.read(param.name) == null) releaseDao.create(param) else null
    }
}
