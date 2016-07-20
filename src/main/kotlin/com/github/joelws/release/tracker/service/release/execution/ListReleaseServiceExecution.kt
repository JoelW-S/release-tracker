package com.github.joelws.release.tracker.service.release.execution

import com.github.joelws.release.tracker.dao.release.ReleaseDao
import com.github.joelws.release.tracker.entity.release.Release
import com.github.joelws.release.tracker.service.ServiceExecution

open class ListReleaseServiceExecution(private val releaseDao: ReleaseDao) : ServiceExecution<Nothing?, List<Release>> {

    override fun execute(param: Nothing?): List<Release> = releaseDao.list()
}
