package com.joelws.release.tracker.service.release.execution

import com.joelws.release.tracker.dao.release.ReleaseDao
import com.joelws.release.tracker.entity.release.Release
import com.joelws.release.tracker.service.ServiceExecution
import org.funktionale.option.Option.None

open class ListReleaseServiceExecution(private val releaseDao: ReleaseDao) : ServiceExecution<None, List<Release>> {

    override fun execute(param: None): List<Release> = releaseDao.list()
}
