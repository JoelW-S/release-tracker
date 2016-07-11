package com.github.joelws.release.tracker.conversion

import com.github.joelws.release.tracker.dto.release.ReleaseDto
import com.github.joelws.release.tracker.entity.release.Release
import com.github.joelws.release.tracker.interfaces.Adapter
import org.apache.log4j.Logger


class ReleaseToReleaseDtoAdapter : Adapter<Release, ReleaseDto> {

    companion object {
        private val LOGGER = Logger.getLogger(ReleaseToReleaseDtoAdapter::class.java)
    }


    override fun adapt(incoming: Release): ReleaseDto {
        LOGGER.info("Adapt - in: ${incoming.javaClass}")

        val artifactToArtifactDtoAdapter = ArtifactToArtifactDtoAdapter()
        val hotfixToHotfixDtoAdapter = HotfixToHotfixDtoAdapter()

        val out = ReleaseDto(name = incoming.name!!, artifacts = incoming.artifacts.mapNotNull { artifactToArtifactDtoAdapter.adapt(it) },
                hotfixes = incoming.hotfixes.map { hotfixToHotfixDtoAdapter.adapt(it) }.toSet())

        LOGGER.info("Adapt - out: ${out.javaClass}")

        return out

    }
}

