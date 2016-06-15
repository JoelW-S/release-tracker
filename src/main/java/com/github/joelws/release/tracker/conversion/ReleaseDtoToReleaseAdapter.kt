package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.release.ReleaseDto
import com.github.joelws.release.tracker.entity.release.Release
import com.github.joelws.release.tracker.interfaces.Adapter
import org.apache.log4j.Logger

class ReleaseDtoToReleaseAdapter : Adapter<ReleaseDto, Release> {

    companion object {
        private val LOGGER = Logger.getLogger(ReleaseDtoToReleaseAdapter::class.java)
    }

    override fun adapt(releaseDto: ReleaseDto): Release {

        LOGGER.info("Adapt - in: ${releaseDto.javaClass}")

        val artifactDtoToArtifactAdapter = ArtifactDtoToArtifactAdapter()
        val hotfixDtoToHotfixAdapter = HotfixDtoToHotfixAdapter()

        val inArtifactList = releaseDto.artifacts
        val inHotfixSet = releaseDto.hotfixes;

        val out = Release();

        out.name = releaseDto.name

        out.artifacts = inArtifactList.map { artifactDtoToArtifactAdapter.adapt(it) }


        out.hotfixes = inHotfixSet.map { hotfixDtoToHotfixAdapter.adapt(it) }.toSet()

        LOGGER.info("Adapt - out: ${out.javaClass}");

        return out;

    }
}

