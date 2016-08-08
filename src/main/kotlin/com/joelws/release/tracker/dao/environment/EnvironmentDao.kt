package com.joelws.release.tracker.dao.environment

import com.joelws.release.tracker.dao.GenericDao
import com.joelws.release.tracker.entity.environment.Environment

interface EnvironmentDao : GenericDao<Environment, String> {
    fun list(): List<Environment>
}
