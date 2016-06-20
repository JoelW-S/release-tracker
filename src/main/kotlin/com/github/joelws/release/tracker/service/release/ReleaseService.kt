package com.github.joelws.release.tracker.service.release


import com.github.joelws.release.tracker.interfaces.BusinessService
import com.github.joelws.release.tracker.service.release.operation.*

open class ReleaseService(private val createReleaseServiceOperation: CreateReleaseServiceOperation,
                          private val readReleaseServiceOperation: ReadReleaseServiceOperation,
                          private val updateReleaseServiceOperation: UpdateReleaseServiceOperation,
                          private val deleteReleaseServiceOperation: DeleteReleaseServiceOperation,
                          private val listReleaseServiceOperation: ListReleaseServiceOperation) : BusinessService<String> {

    override fun create(json: String) = createReleaseServiceOperation.execute(json)

    override fun read(identifier: String) = readReleaseServiceOperation.execute(identifier)

    override fun update(json: String) = updateReleaseServiceOperation.execute(json)

    override fun delete(identifier: String) = deleteReleaseServiceOperation.execute(identifier)

    override fun list() = listReleaseServiceOperation.execute(null)
}
