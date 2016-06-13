package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.release.ReleaseDto
import com.github.joelws.release.tracker.entity.release.Release
import com.github.joelws.release.tracker.interfaces.Adapter
import org.apache.log4j.Logger


class HotfixToHotfixDtoAdapter : Adapter<Release, ReleaseDto> {

    var LOGGER = Logger.getLogger(HotfixToHotfixDtoAdapter::class.java)


    override fun adapt(release: Release): ReleaseDto? {
        LOGGER.info("Adapt - in: ${release.javaClass}")

        val artifactToArtifactDtoAdapter = ArtifactToArtifactDtoAdapter()
        val out = ReleaseDto()
        val inArtifactList = release.artifacts

        out.name = release.name

        out.artifacts = inArtifactList.mapNotNull { artifactToArtifactDtoAdapter.adapt(it) }

        LOGGER.info("Adapt - out: ${out.javaClass}")
        return out
    }

}
