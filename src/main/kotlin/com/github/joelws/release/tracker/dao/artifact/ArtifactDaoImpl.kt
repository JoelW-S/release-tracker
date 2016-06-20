package com.github.joelws.release.tracker.dao.artifact

import com.github.joelws.release.tracker.dao.GenericDaoImpl
import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.entity.artifact.ArtifactPK

import org.springframework.stereotype.Repository
@Repository
open class ArtifactDaoImpl : GenericDaoImpl<Artifact, ArtifactPK>(), ArtifactDao {

    override fun list(): List<Artifact> {
        val query = "SELECT a FROM Artifact a"
        @Suppress("UNCHECKED_CAST")
        return entityManager.createQuery(query).resultList as List<Artifact>
    }
}
