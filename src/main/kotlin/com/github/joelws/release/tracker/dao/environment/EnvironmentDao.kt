package com.github.joelws.release.tracker.dao.environment

import com.github.joelws.release.tracker.dao.GenericDao
import com.github.joelws.release.tracker.entity.environment.Environment

interface EnvironmentDao : GenericDao<Environment, String> {
    fun list(): List<Environment>
}
