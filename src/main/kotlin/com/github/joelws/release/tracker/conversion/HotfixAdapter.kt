package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.entity.release.Release
import com.github.joelws.release.tracker.interfaces.Adapter
import com.github.joelws.release.tracker.model.release.ReleaseModel


open class HotfixAdapter : Adapter<Release, ReleaseModel> {

    override fun adapt(incoming: Release): ReleaseModel {

        val artifactToArtifactDtoAdapter = ArtifactAdapter()

        val out = ReleaseModel(name = incoming.name!!, artifacts = incoming.artifacts.mapNotNull { artifactToArtifactDtoAdapter.adapt(it) })

        return out
    }

}
