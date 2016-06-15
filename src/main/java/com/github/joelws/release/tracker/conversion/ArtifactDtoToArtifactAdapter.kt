package com.github.joelws.release.tracker.conversion;


import com.github.joelws.release.tracker.dto.artifact.ArtifactDto
import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.entity.artifact.ArtifactPK
import com.github.joelws.release.tracker.interfaces.Adapter
import org.apache.log4j.Logger;

class ArtifactDtoToArtifactAdapter : Adapter<ArtifactDto, Artifact> {

    companion object {
        private val LOGGER = Logger.getLogger(ArtifactDtoToArtifactAdapter::class.java)
    }

    override fun adapt(artifactDto: ArtifactDto): Artifact {

        LOGGER.info("Adapt - in: ${artifactDto.javaClass}")

        val out = Artifact()
        val artifactPK = ArtifactPK()

        artifactPK.groupId = artifactDto.groupId
        artifactPK.artifactId = artifactDto.artifactId
        artifactPK.version = artifactDto.version
        out.id = artifactPK

        LOGGER.info("Adapt - out: ${out.javaClass}")
        return out
    }
}
