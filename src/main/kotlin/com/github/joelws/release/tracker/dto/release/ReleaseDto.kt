package com.github.joelws.release.tracker.dto.release

import com.github.joelws.release.tracker.dto.artifact.ArtifactDto
import java.util.*

data class ReleaseDto(val name: String,
                      val artifacts: List<ArtifactDto> = ArrayList<ArtifactDto>(),
                      val hotfixes: Set<ReleaseDto> = HashSet<ReleaseDto>())
