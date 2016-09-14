package com.joelws.release.tracker.conversion

import com.joelws.release.tracker.entity.release.Release
import com.joelws.release.tracker.interfaces.Adapter
import com.joelws.release.tracker.model.release.ReleaseModel


open class HotfixAdapter : Adapter<Release, ReleaseModel> {

    override fun adapt(incoming: Release): ReleaseModel {

        val artifactAdapter = ArtifactAdapter()

        val out = ReleaseModel(name = incoming.name!!, artifacts = incoming.artifacts.mapNotNull { artifactAdapter.adapt(it) })

        return out
    }

}
