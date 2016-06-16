package com.github.joelws.release.tracker.entity.artifact

import javax.persistence.EmbeddedId
import javax.persistence.Entity


open @Entity class Artifact(@EmbeddedId var id: ArtifactPK? = null)
