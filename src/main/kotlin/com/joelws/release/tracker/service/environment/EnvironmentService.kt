package com.joelws.release.tracker.service.environment


import com.joelws.release.tracker.service.AbstractService
import com.joelws.release.tracker.service.ServiceOperation
import org.funktionale.option.Option.None

open class EnvironmentService(createServiceOperation: ServiceOperation<String>, readServiceOperation: ServiceOperation<String>, updateServiceOperation: ServiceOperation<String>, deleteServiceOperation: ServiceOperation<String>, listServiceOperation: ServiceOperation<None>) : AbstractService<String>(createServiceOperation, readServiceOperation, updateServiceOperation, deleteServiceOperation, listServiceOperation)
