package com.github.joelws.release.tracker.service.release.execution


import com.github.joelws.release.tracker.dao.release.ReleaseDao
import com.github.joelws.release.tracker.entity.release.Release
import com.github.joelws.release.tracker.service.ServiceExecution
import org.apache.log4j.Logger

open class ReadReleaseServiceExecution(private val releaseDao: ReleaseDao) : ServiceExecution<String?, Release?> {

    companion object {
        private val LOGGER = Logger.getLogger(ReadReleaseServiceExecution::class.java)
    }

    override fun execute(param: String?): Release? {
        LOGGER.info("Starting ReadReleaseServiceExecution, In: $param")
        return releaseDao.read(param)

    }
}
