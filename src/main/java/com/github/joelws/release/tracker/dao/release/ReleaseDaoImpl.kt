package com.github.joelws.release.tracker.dao.release

import com.github.joelws.release.tracker.dao.GenericDaoImpl
import com.github.joelws.release.tracker.entity.release.Release

import org.springframework.stereotype.Repository

@Repository
open class ReleaseDaoImpl : GenericDaoImpl<Release, String>(), ReleaseDao {

    @SuppressWarnings("unchecked")
    override fun list(): List<Release> {
        val query = "SELECT r FROM Release r"
        return entityManager!!.createQuery(query).resultList as List<Release>
    }
}
