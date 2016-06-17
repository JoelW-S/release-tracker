package com.github.joelws.release.tracker.entity.artifact

import javax.persistence.EmbeddedId
import javax.persistence.Entity

@Entity
open class Artifact(@EmbeddedId var id: ArtifactPK? = null)
