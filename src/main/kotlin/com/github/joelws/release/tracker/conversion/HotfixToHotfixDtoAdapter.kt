package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.release.ReleaseDto
import com.github.joelws.release.tracker.entity.release.Release
import com.github.joelws.release.tracker.interfaces.Adapter
import org.apache.log4j.Logger


class HotfixToHotfixDtoAdapter : Adapter<Release, ReleaseDto> {

    companion object {
        private var LOGGER = Logger.getLogger(HotfixToHotfixDtoAdapter::class.java)
    }

    override fun adapt(incoming: Release): ReleaseDto {
        LOGGER.info("Adapt - in: ${incoming.javaClass}")

        val artifactToArtifactDtoAdapter = ArtifactToArtifactDtoAdapter()
        val out = ReleaseDto()
        val inArtifactList = incoming.artifacts

        out.name = incoming.name

        out.artifacts = inArtifactList.mapNotNull { artifactToArtifactDtoAdapter.adapt(it) }

        LOGGER.info("Adapt - out: ${out.javaClass}")
        return out
    }

}