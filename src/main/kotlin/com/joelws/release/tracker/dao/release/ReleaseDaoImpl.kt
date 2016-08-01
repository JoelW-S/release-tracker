package com.joelws.release.tracker.dao.release

import com.joelws.release.tracker.dao.GenericDaoImpl
import com.joelws.release.tracker.entity.release.Release

import org.springframework.stereotype.Repository

@Repository
open class ReleaseDaoImpl : GenericDaoImpl<Release, String>(), ReleaseDao {

    override fun list(): List<Release> {
        val query = "SELECT r FROM Release r"
        @Suppress("UNCHECKED_CAST")
        return entityManager.createQuery(query).resultList as List<Release>
    }
}
