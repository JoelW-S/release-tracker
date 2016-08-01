package com.joelws.release.tracker.service.release.execution

import com.joelws.release.tracker.dao.release.ReleaseDao
import com.joelws.release.tracker.entity.release.Release
import com.joelws.release.tracker.service.ServiceExecution

open class CreateReleaseServiceExecution(private val releaseDao: ReleaseDao) : ServiceExecution<Release, Release?> {

    override fun execute(param: Release): Release? = when {
            releaseDao.read(param.name) == null -> releaseDao.create(param)
            else -> null
        }
}
