package com.joelws.release.tracker.dao.artifact

import com.joelws.release.tracker.dao.GenericDaoImpl
import com.joelws.release.tracker.entity.artifact.Artifact
import com.joelws.release.tracker.entity.artifact.ArtifactPK

import org.springframework.stereotype.Repository

@Repository
open class ArtifactDaoImpl : GenericDaoImpl<Artifact, ArtifactPK>(), ArtifactDao {

    override fun list(): List<Artifact> {
        val query = "SELECT a FROM Artifact a"
        @Suppress("UNCHECKED_CAST")
        return entityManager.createQuery(query).resultList as List<Artifact>
    }
}
