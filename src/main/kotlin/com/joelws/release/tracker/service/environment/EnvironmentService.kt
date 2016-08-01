package com.joelws.release.tracker.service.environment


import com.joelws.release.tracker.interfaces.BusinessService
import com.joelws.release.tracker.service.ServiceOperation

open class EnvironmentService(private val createEnvironmentServiceOperation: ServiceOperation<String>,
                              private val readEnvironmentServiceOperation: ServiceOperation<String>,
                              private val updateEnvironmentServiceOperation: ServiceOperation<String>,
                              private val deleteEnvironmentServiceOperation: ServiceOperation<String>,
                              private val listEnvironmentServiceOperation: ServiceOperation<Nothing?>) : BusinessService<String> {

    override fun create(json: String) = createEnvironmentServiceOperation.execute(json)

    override fun read(identifier: String) = readEnvironmentServiceOperation.execute(identifier)

    override fun update(json: String) = updateEnvironmentServiceOperation.execute(json)

    override fun delete(identifier: String) = deleteEnvironmentServiceOperation.execute(identifier)

    override fun list() = listEnvironmentServiceOperation.execute(null)
}
