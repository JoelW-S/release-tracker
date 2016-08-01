package com.joelws.release.tracker.service.release.execution


import com.joelws.release.tracker.dao.release.ReleaseDao
import com.joelws.release.tracker.entity.release.Release
import com.joelws.release.tracker.service.ServiceExecution

open class ReadReleaseServiceExecution(private val releaseDao: ReleaseDao) : ServiceExecution<String?, Release?> {

    override fun execute(param: String?): Release? = releaseDao.read(param)
}
