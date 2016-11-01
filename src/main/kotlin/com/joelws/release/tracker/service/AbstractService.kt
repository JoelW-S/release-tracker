package com.joelws.release.tracker.service

import com.joelws.release.tracker.interfaces.BusinessService
import com.joelws.release.tracker.response.build
import org.funktionale.option.Option.None
import ratpack.handling.Context

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
*/abstract class AbstractService<in T>(protected val createServiceOperation: ServiceOperation<String>,
                                       protected val readServiceOperation: ServiceOperation<T>,
                                       protected val updateServiceOperation: ServiceOperation<String>,
                                       protected val deleteServiceOperation: ServiceOperation<T>,
                                       protected val listServiceOperation: ServiceOperation<None>) : BusinessService<T> {
    override fun create(json: String): (Context) -> Unit = createServiceOperation.execute(json).build()

    override fun read(identifier: T): (Context) -> Unit = readServiceOperation.execute(identifier).build()

    override fun update(json: String): (Context) -> Unit = updateServiceOperation.execute(json).build()

    override fun delete(identifier: T): (Context) -> Unit = deleteServiceOperation.execute(identifier).build()

    override fun list(): (Context) -> Unit = listServiceOperation.execute(None).build()
}
