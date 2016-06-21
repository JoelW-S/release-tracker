package com.github.joelws.release.tracker.service.release.execution

import com.github.joelws.release.tracker.dao.release.ReleaseDao
import com.github.joelws.release.tracker.entity.release.Release
import com.github.joelws.release.tracker.service.ServiceExecution
import org.apache.log4j.Logger

open class CreateReleaseServiceExecution(private val releaseDao: ReleaseDao) : ServiceExecution<Release, Release?> {

    companion object {
        private val LOGGER = Logger.getLogger(CreateReleaseServiceExecution::class.java)
    }

    override fun execute(param: Release): Release? {
        LOGGER.info("Starting CreateReleaseServiceExecution, In: $param")

        return when {
            releaseDao.read(param.name) == null -> releaseDao.create(param)
            else -> null
        }
    }
}
