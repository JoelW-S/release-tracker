package com.joelws.release.tracker.service.release.execution

import com.joelws.release.tracker.dao.release.ReleaseDao
import com.joelws.release.tracker.entity.release.Release
import com.joelws.release.tracker.service.ServiceExecution

open class UpdateReleaseServiceExecution(private val releaseDao: ReleaseDao) : ServiceExecution<Release, Release?> {

    override fun execute(param: Release): Release? =
            releaseDao.read(param.name)?.let {
                releaseDao.update(param) ?: null
            }
}
