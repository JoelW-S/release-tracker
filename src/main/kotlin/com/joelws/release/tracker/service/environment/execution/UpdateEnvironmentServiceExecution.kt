package com.joelws.release.tracker.service.environment.execution

import com.joelws.release.tracker.dao.environment.EnvironmentDao
import com.joelws.release.tracker.entity.environment.Environment
import com.joelws.release.tracker.service.ServiceExecution
import org.funktionale.option.Option
import org.funktionale.option.Option.None
import org.funktionale.option.Option.Some

/*
Copyright 2016 Joel Whittaker-Smith

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/open class UpdateEnvironmentServiceExecution(private val environmentDao: EnvironmentDao) : ServiceExecution<Environment, Option<Environment>> {

    override fun execute(param: Environment): Option<Environment> {
        val maybeEnvironment = environmentDao.read(param.environmentName)
        return when (maybeEnvironment) {
            is Some<Environment> -> environmentDao.update(param)
            is None -> None
        }
    }
}
