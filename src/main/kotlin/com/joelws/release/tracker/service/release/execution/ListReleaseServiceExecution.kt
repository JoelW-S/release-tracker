package com.joelws.release.tracker.service.release.execution

import com.joelws.release.tracker.dao.release.ReleaseDao
import com.joelws.release.tracker.entity.release.Release
import com.joelws.release.tracker.service.ServiceExecution

open class ListReleaseServiceExecution(private val releaseDao: ReleaseDao) : ServiceExecution<Nothing?, List<Release>> {

    override fun execute(param: Nothing?): List<Release> = releaseDao.list()
}
