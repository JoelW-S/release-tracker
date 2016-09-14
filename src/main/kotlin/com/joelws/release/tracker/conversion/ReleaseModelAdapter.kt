package com.joelws.release.tracker.conversion

import com.joelws.release.tracker.entity.release.Release
import com.joelws.release.tracker.interfaces.Adapter
import com.joelws.release.tracker.model.release.ReleaseModel

open class ReleaseModelAdapter : Adapter<ReleaseModel, Release> {

    override fun adapt(incoming: ReleaseModel): Release {

        val artifactModelAdapter = ArtifactModelAdapter()
        val hotfixModelAdapter = HotfixModelAdapter()

        val inArtifactList = incoming.artifacts
        val inHotfixSet = incoming.hotfixes

        val out = Release()

        out.name = incoming.name

        out.artifacts = inArtifactList.map { artifactModelAdapter.adapt(it) }.toMutableList()


        out.hotfixes = inHotfixSet.map { hotfixModelAdapter.adapt(it) }.toMutableSet()

        return out

    }
}

