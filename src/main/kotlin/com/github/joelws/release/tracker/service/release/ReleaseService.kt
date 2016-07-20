package com.github.joelws.release.tracker.service.release


import com.github.joelws.release.tracker.interfaces.BusinessService
import com.github.joelws.release.tracker.service.ServiceOperation

open class ReleaseService(private val createReleaseServiceOperation: ServiceOperation<String>,
                          private val readReleaseServiceOperation: ServiceOperation<String>,
                          private val updateReleaseServiceOperation: ServiceOperation<String>,
                          private val deleteReleaseServiceOperation: ServiceOperation<String>,
                          private val listReleaseServiceOperation: ServiceOperation<Nothing?>) : BusinessService<String> {

    override fun create(json: String) = createReleaseServiceOperation.execute(json)

    override fun read(identifier: String) = readReleaseServiceOperation.execute(identifier)

    override fun update(json: String) = updateReleaseServiceOperation.execute(json)

    override fun delete(identifier: String) = deleteReleaseServiceOperation.execute(identifier)

    override fun list() = listReleaseServiceOperation.execute(null)
}
