package com.joelws.release.tracker.dao.artifact

import com.joelws.release.tracker.dao.GenericDao
import com.joelws.release.tracker.entity.artifact.Artifact
import com.joelws.release.tracker.entity.artifact.ArtifactPK

interface ArtifactDao : GenericDao<Artifact, ArtifactPK> {
    fun list(): List<Artifact>
}
