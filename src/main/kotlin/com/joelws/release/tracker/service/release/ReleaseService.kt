package com.joelws.release.tracker.service.release


import com.joelws.release.tracker.interfaces.BusinessService
import com.joelws.release.tracker.response.build
import com.joelws.release.tracker.service.ServiceOperation
import org.funktionale.option.Option.None

open class ReleaseService(private val createReleaseServiceOperation: ServiceOperation<String>,
                          private val readReleaseServiceOperation: ServiceOperation<String>,
                          private val updateReleaseServiceOperation: ServiceOperation<String>,
                          private val deleteReleaseServiceOperation: ServiceOperation<String>,
                          private val listReleaseServiceOperation: ServiceOperation<None>) : BusinessService<String> {

    override fun create(json: String) = createReleaseServiceOperation.execute(json).build()

    override fun read(identifier: String) = readReleaseServiceOperation.execute(identifier).build()

    override fun update(json: String) = updateReleaseServiceOperation.execute(json).build()

    override fun delete(identifier: String) = deleteReleaseServiceOperation.execute(identifier).build()

    override fun list() = listReleaseServiceOperation.execute(None).build()
}
