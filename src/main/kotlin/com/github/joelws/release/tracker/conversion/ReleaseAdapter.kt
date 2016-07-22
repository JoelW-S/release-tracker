package com.github.joelws.release.tracker.conversion

import com.github.joelws.release.tracker.entity.release.Release
import com.github.joelws.release.tracker.interfaces.Adapter
import com.github.joelws.release.tracker.model.release.ReleaseModel


open class ReleaseAdapter : Adapter<Release, ReleaseModel> {

    override fun adapt(incoming: Release): ReleaseModel {

        val artifactToArtifactDtoAdapter = ArtifactAdapter()
        val hotfixToHotfixDtoAdapter = HotfixAdapter()

        val out = ReleaseModel(name = incoming.name!!, artifacts = incoming.artifacts.mapNotNull { artifactToArtifactDtoAdapter.adapt(it) },
                hotfixes = incoming.hotfixes.map { hotfixToHotfixDtoAdapter.adapt(it) }.toSet())

        return out

    }
}

