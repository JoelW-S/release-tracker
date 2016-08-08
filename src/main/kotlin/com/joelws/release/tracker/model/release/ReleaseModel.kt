package com.joelws.release.tracker.model.release

import com.joelws.release.tracker.model.artifact.ArtifactModel
import java.util.*

data class ReleaseModel(val name: String,
                        val artifacts: List<ArtifactModel> = ArrayList<ArtifactModel>(),
                        val hotfixes: Set<ReleaseModel> = HashSet<ReleaseModel>())
