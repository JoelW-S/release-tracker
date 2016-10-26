package com.joelws.release.tracker.service.release.execution


import com.joelws.release.tracker.dao.release.ReleaseDao
import com.joelws.release.tracker.entity.release.Release
import com.joelws.release.tracker.service.ServiceExecution
import org.funktionale.option.Option

open class ReadReleaseServiceExecution(private val releaseDao: ReleaseDao) : ServiceExecution<String, Option<Release>> {

    override fun execute(param: String): Option<Release> = releaseDao.read(param)
}
