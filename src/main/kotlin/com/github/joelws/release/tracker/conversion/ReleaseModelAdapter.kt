package com.github.joelws.release.tracker.conversion

import com.github.joelws.release.tracker.entity.release.Release
import com.github.joelws.release.tracker.interfaces.Adapter
import com.github.joelws.release.tracker.model.release.ReleaseModel

open class ReleaseModelAdapter : Adapter<ReleaseModel, Release> {

    override fun adapt(incoming: ReleaseModel): Release {

        val artifactDtoToArtifactAdapter = ArtifactModelAdapter()
        val hotfixDtoToHotfixAdapter = HotfixModelAdapter()

        val inArtifactList = incoming.artifacts
        val inHotfixSet = incoming.hotfixes

        val out = Release()

        out.name = incoming.name

        out.artifacts = inArtifactList.map { artifactDtoToArtifactAdapter.adapt(it) }.toMutableList()


        out.hotfixes = inHotfixSet.map { hotfixDtoToHotfixAdapter.adapt(it) }.toMutableSet()

        return out

    }
}

