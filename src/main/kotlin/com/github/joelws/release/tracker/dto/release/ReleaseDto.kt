package com.github.joelws.release.tracker.dto.release

import com.github.joelws.release.tracker.dto.artifact.ArtifactDto
import java.util.*

data class ReleaseDto(var name: String? = null,
                      var artifacts: List<ArtifactDto> = ArrayList<ArtifactDto>(),
                      var hotfixes: Set<ReleaseDto> = HashSet<ReleaseDto>())
