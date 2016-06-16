package com.github.joelws.release.tracker.conversion


import com.github.joelws.release.tracker.dto.artifact.ArtifactDto
import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.entity.artifact.ArtifactPK
import com.github.joelws.release.tracker.interfaces.Adapter
import org.apache.log4j.Logger;

class ArtifactDtoToArtifactAdapter : Adapter<ArtifactDto, Artifact> {

    companion object {
        private val LOGGER = Logger.getLogger(ArtifactDtoToArtifactAdapter::class.java)
    }

    override fun adapt(In: ArtifactDto): Artifact {

        LOGGER.info("Adapt - in: ${In.javaClass}")

        val out = Artifact()
        val artifactPK = ArtifactPK()

        artifactPK.groupId = In.groupId
        artifactPK.artifactId = In.artifactId
        artifactPK.version = In.version
        out.id = artifactPK

        LOGGER.info("Adapt - out: ${out.javaClass}")
        return out
    }
}
