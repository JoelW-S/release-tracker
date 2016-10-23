package com.joelws.release.tracker.entity.artifact

import java.io.Serializable

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class ArtifactPK(@Column(name = "group_id")
                      var groupId: String = "",
                      @Column(name = "artifact_id")
                      var artifactId: String = "",
                      @Column(name = "version")
                      var version: String = "") : Serializable
