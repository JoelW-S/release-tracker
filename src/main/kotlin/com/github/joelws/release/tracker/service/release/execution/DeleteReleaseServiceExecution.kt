package com.github.joelws.release.tracker.service.release.execution

import com.github.joelws.release.tracker.dao.release.ReleaseDao
import com.github.joelws.release.tracker.service.ServiceExecution

open class DeleteReleaseServiceExecution(private val releaseDao: ReleaseDao) : ServiceExecution<String?, Unit> {

    override fun execute(param: String?) {
        releaseDao.read(param)?.let {
            releaseDao.delete(param)
        }
    }
}
