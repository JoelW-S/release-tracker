package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDto;
import com.github.joelws.release.tracker.entity.artifact.Artifact;
import com.github.joelws.release.tracker.interfaces.Adapter;

import org.apache.log4j.Logger;

public class ArtifactDtoAdapter implements Adapter<Artifact, ArtifactDto> {
    private static final Logger LOGGER = Logger.getLogger(ArtifactDtoAdapter.class);

    @Override
    public ArtifactDto adapt(Artifact artifact) {
        LOGGER.info("Adapt - in: " + artifact.getClass());
        ArtifactDto out = new ArtifactDto();

        out.setArtifactId(artifact.getId().getArtifactId());
        out.setGroupId(artifact.getId().getGroupId());
        out.setVersion(artifact.getId().getVersion());

        LOGGER.info("Adapt - out: " + out.getClass());
        return out;
    }
}
