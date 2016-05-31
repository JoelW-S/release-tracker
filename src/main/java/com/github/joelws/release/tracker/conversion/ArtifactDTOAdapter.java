package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDTO;
import com.github.joelws.release.tracker.entity.artifact.Artifact;
import com.github.joelws.release.tracker.interfaces.Adapter;
import org.apache.log4j.Logger;

public class ArtifactDTOAdapter implements Adapter<Artifact, ArtifactDTO>
{
    private static final Logger LOGGER = Logger.getLogger(ArtifactDTOAdapter.class);

    @Override public ArtifactDTO adapt(Artifact artifact)
    {
        LOGGER.info("Adapt - in: " + artifact.getClass());
        ArtifactDTO out = new ArtifactDTO();

        out.setArtifactId(artifact.getId().getArtifactId());
        out.setGroupId(artifact.getId().getGroupId());
        out.setVersion(artifact.getId().getVersion());

        LOGGER.info("Adapt - out: " + out.getClass());
        return out;
    }
}
