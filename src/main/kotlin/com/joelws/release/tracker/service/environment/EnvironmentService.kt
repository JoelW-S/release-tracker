package com.joelws.release.tracker.service.environment


import com.joelws.release.tracker.interfaces.BusinessService
import com.joelws.release.tracker.response.build
import com.joelws.release.tracker.service.ServiceOperation
import org.funktionale.option.Option.None

open class EnvironmentService(private val createEnvironmentServiceOperation: ServiceOperation<String>,
                              private val readEnvironmentServiceOperation: ServiceOperation<String>,
                              private val updateEnvironmentServiceOperation: ServiceOperation<String>,
                              private val deleteEnvironmentServiceOperation: ServiceOperation<String>,
                              private val listEnvironmentServiceOperation: ServiceOperation<None>) : BusinessService<String> {

    override fun create(json: String) = createEnvironmentServiceOperation.execute(json).build()

    override fun read(identifier: String) = readEnvironmentServiceOperation.execute(identifier).build()

    override fun update(json: String) = updateEnvironmentServiceOperation.execute(json).build()

    override fun delete(identifier: String) = deleteEnvironmentServiceOperation.execute(identifier).build()

    override fun list() = listEnvironmentServiceOperation.execute(None).build()
}
