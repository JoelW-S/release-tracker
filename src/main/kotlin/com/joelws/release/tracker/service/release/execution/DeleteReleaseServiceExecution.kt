package com.joelws.release.tracker.service.release.execution

import com.joelws.release.tracker.dao.release.ReleaseDao
import com.joelws.release.tracker.entity.release.Release
import com.joelws.release.tracker.service.ServiceExecution
import org.funktionale.option.Option.Some

open class DeleteReleaseServiceExecution(private val releaseDao: ReleaseDao) : ServiceExecution<String, Unit> {

    override fun execute(param: String) {

        when (releaseDao.read(param)) {
            is Some<Release> -> releaseDao.delete(param)
        }
    }
}
