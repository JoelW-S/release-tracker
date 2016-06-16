package com.github.joelws.release.tracker.entity.artifact

import java.io.Serializable

import javax.persistence.Column
import javax.persistence.Embeddable

open @Embeddable class ArtifactPK(@Column(name = "group_id")
                                  var groupId: String? = null,
                                  @Column(name = "artifact_id")
                                  var artifactId: String? = null,
                                  @Column(name = "version")
                                  var version: String? = null) : Serializable
