package com.github.joelws.release.tracker.service.release.execution

import com.github.joelws.release.tracker.dao.release.ReleaseDao
import com.github.joelws.release.tracker.service.ServiceExecution
import org.apache.log4j.Logger

open class DeleteReleaseServiceExecution(private val releaseDao: ReleaseDao) : ServiceExecution<String?, Unit> {

    companion object {
        private val LOGGER = Logger.getLogger(DeleteReleaseServiceExecution::class.java)
    }

    override fun execute(param: String?) {
        LOGGER.info("Starting DeleteReleaseServiceExecution, In: $param")
        releaseDao.read(param)?.let {
            releaseDao.delete(param)
        }
    }
}
