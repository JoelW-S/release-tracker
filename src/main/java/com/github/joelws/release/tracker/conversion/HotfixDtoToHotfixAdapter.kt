package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.release.ReleaseDto
import com.github.joelws.release.tracker.entity.release.Release
import com.github.joelws.release.tracker.interfaces.Adapter
import org.apache.log4j.Logger


class HotfixDtoToHotfixAdapter : Adapter<ReleaseDto, Release> {

    var LOGGER = Logger.getLogger(HotfixDtoToHotfixAdapter::class.java)


    override fun adapt(releaseDto: ReleaseDto): Release? {
        LOGGER.info("Adapt - in: ${releaseDto.javaClass}")

        val artifactDtoToArtifactAdapter = ArtifactDtoToArtifactAdapter()
        val out = Release()
        val inArtifactList = releaseDto.artifacts


        out.name = releaseDto.name

        out.artifacts = inArtifactList.map { artifactDtoToArtifactAdapter.adapt(it) }

        LOGGER.info("Adapt - out: ${out.javaClass}")
        return out
    }
}
