package com.github.joelws.release.tracker.service.environment.execution

import com.github.joelws.release.tracker.dao.environment.EnvironmentDao
import com.github.joelws.release.tracker.entity.environment.Environment
import com.github.joelws.release.tracker.service.ServiceExecution

open class CreateEnvironmentServiceExecution(private val environmentDao: EnvironmentDao) : ServiceExecution<Environment, Environment?> {

    override fun execute(param: Environment): Environment? = when (environmentDao.read(param.environmentName)) {
        null -> environmentDao.create(param)
        else -> null
    }
}
