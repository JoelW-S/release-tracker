package com.joelws.release.tracker.service.environment.execution

import com.joelws.release.tracker.dao.environment.EnvironmentDao
import com.joelws.release.tracker.entity.environment.Environment
import com.joelws.release.tracker.service.ServiceExecution
import org.funktionale.option.Option
import org.funktionale.option.Option.None
import org.funktionale.option.Option.Some

open class CreateEnvironmentServiceExecution(private val environmentDao: EnvironmentDao) : ServiceExecution<Environment, Option<Environment>> {

    override fun execute(param: Environment): Option<Environment> {
        val maybeEnvironment = environmentDao.read(param.environmentName)

        return when (maybeEnvironment) {
            is Some<Environment> -> None
            is None -> environmentDao.create(param)
        }

    }
}
