package com.github.joelws.release.tracker.service.release.execution

import com.github.joelws.release.tracker.dao.release.ReleaseDao
import com.github.joelws.release.tracker.entity.release.Release
import com.github.joelws.release.tracker.service.ServiceExecution
import org.apache.log4j.Logger

open class UpdateReleaseServiceExecution(private val releaseDao: ReleaseDao) : ServiceExecution<Release, Release?> {

    companion object {
        private val LOGGER = Logger.getLogger(UpdateReleaseServiceExecution::class.java)
    }

    override fun execute(param: Release): Release? {
        LOGGER.info("Starting UpdateReleaseServiceExecution, In: $param")

        return releaseDao.read(param.name)?.let {
            releaseDao.update(param) ?: null
        }
    }
}
