package com.github.joelws.release.tracker.conversion

import com.github.joelws.release.tracker.dto.release.ReleaseDto
import com.github.joelws.release.tracker.entity.release.Release
import com.github.joelws.release.tracker.interfaces.Adapter
import org.apache.log4j.Logger

class ReleaseDtoToReleaseAdapter : Adapter<ReleaseDto, Release> {

    companion object {
        private val LOGGER = Logger.getLogger(ReleaseDtoToReleaseAdapter::class.java)
    }

    override fun adapt(incoming: ReleaseDto): Release {

        LOGGER.info("Adapt - in: ${incoming.javaClass}")

        val artifactDtoToArtifactAdapter = ArtifactDtoToArtifactAdapter()
        val hotfixDtoToHotfixAdapter = HotfixDtoToHotfixAdapter()

        val inArtifactList = incoming.artifacts
        val inHotfixSet = incoming.hotfixes

        val out = Release();

        out.name = incoming.name

        out.artifacts = inArtifactList.map { artifactDtoToArtifactAdapter.adapt(it) }.toMutableList()


        out.hotfixes = inHotfixSet.map { hotfixDtoToHotfixAdapter.adapt(it) }.toMutableSet()

        LOGGER.info("Adapt - out: ${out.javaClass}");

        return out;

    }
}

