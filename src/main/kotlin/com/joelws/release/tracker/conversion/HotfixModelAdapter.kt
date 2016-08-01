package com.joelws.release.tracker.conversion

import com.joelws.release.tracker.entity.release.Release
import com.joelws.release.tracker.interfaces.Adapter
import com.joelws.release.tracker.model.release.ReleaseModel


open class HotfixModelAdapter : Adapter<ReleaseModel, Release> {

    override fun adapt(incoming: ReleaseModel): Release {

        val artifactDtoToArtifactAdapter = ArtifactModelAdapter()
        val out = Release()
        val inArtifactList = incoming.artifacts

        out.name = incoming.name

        out.artifacts = inArtifactList.map { artifactDtoToArtifactAdapter.adapt(it) }.toMutableList()

        return out
    }
}
