package com.github.joelws.release.tracker.dao.environment

import com.github.joelws.release.tracker.dao.GenericDaoImpl
import com.github.joelws.release.tracker.entity.environment.Environment

import org.springframework.stereotype.Repository

@Repository
open class EnvironmentDaoImpl : GenericDaoImpl<Environment, String>(), EnvironmentDao {

    override fun list(): List<Environment> {
        val query = "SELECT e FROM Environment e"
        @Suppress("UNCHECKED_CAST")
        return entityManager.createQuery(query).resultList as List<Environment>
    }
}
