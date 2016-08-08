package com.joelws.release.tracker.entity.artifact

import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
data class Artifact(@EmbeddedId var id: ArtifactPK? = null)
