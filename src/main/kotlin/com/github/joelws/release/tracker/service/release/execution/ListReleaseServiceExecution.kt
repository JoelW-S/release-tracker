package com.github.joelws.release.tracker.service.release.execution

import com.github.joelws.release.tracker.dao.release.ReleaseDao
import com.github.joelws.release.tracker.entity.release.Release
import com.github.joelws.release.tracker.service.ServiceExecution
import org.apache.log4j.Logger

open class ListReleaseServiceExecution(private val releaseDao: ReleaseDao) : ServiceExecution<Nothing?, List<Release>> {

    companion object {
        private val LOGGER = Logger.getLogger(ListReleaseServiceExecution::class.java)
    }

    override fun execute(param: Nothing?): List<Release> {
        LOGGER.info("Starting ListReleaseServiceExecution")
        return releaseDao.list()
    }
}
