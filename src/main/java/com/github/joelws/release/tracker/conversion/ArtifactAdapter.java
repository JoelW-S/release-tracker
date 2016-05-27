package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDTO;
import com.github.joelws.release.tracker.entity.artifact.Artifact;
import com.github.joelws.release.tracker.interfaces.Adapter;
import org.apache.log4j.Logger;

public class ArtifactAdapter implements Adapter<ArtifactDTO, Artifact>
{
    private static final Logger LOGGER = Logger.getLogger(ArtifactAdapter.class);

    @Override public Artifact adapt(ArtifactDTO artifactDTO)
    {
        Artifact out = new Artifact();
        LOGGER.info("Adapt - in: " + artifactDTO.getClass());
        out.getId().setArtifactId(artifactDTO.getArtifactId());
        out.getId().setGroupId(artifactDTO.getGroupId());
        out.getId().setVersion(artifactDTO.getVersion());
        LOGGER.info("Adapt - out: " + out.getClass());
        return out;
    }
}
