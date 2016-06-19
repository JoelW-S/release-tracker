package com.github.joelws.release.tracker.service.release.execution


import com.github.joelws.release.tracker.dao.release.ReleaseDao
import com.github.joelws.release.tracker.entity.release.Release
import com.github.joelws.release.tracker.service.ServiceExecution

open class ReadReleaseServiceExecution(private val releaseDao: ReleaseDao) : ServiceExecution<String?, Release?> {

    override fun execute(param: String?) = releaseDao.read(param)
}
