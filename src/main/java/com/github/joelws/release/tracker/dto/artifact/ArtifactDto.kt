package com.github.joelws.release.tracker.dto.artifact

import com.fasterxml.jackson.annotation.JsonProperty


data class ArtifactDto(@JsonProperty("groupid") var groupId: String = "", @JsonProperty("artifactid") var artifactId: String = "", @JsonProperty("version") var version: String = "")