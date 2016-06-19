package com.github.joelws.release.tracker.dao.artifact

import com.github.joelws.release.tracker.dao.GenericDao
import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.entity.artifact.ArtifactPK

interface ArtifactDao : GenericDao<Artifact, ArtifactPK> {
    fun list(): List<Artifact>
}
