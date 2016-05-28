package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDTO;
import com.github.joelws.release.tracker.entity.artifact.Artifact;
import com.github.joelws.release.tracker.entity.artifact.ArtifactPK;
import com.github.joelws.release.tracker.interfaces.Adapter;
import org.apache.log4j.Logger;

public class ArtifactAdapter implements Adapter<ArtifactDTO, Artifact>
{
    private static final Logger LOGGER = Logger.getLogger(ArtifactAdapter.class);

    @Override public Artifact adapt(ArtifactDTO artifactDTO)
    {
        LOGGER.info("Adapt - in: " + artifactDTO.getClass());

        Artifact out = new Artifact();
        ArtifactPK artifactPK = new ArtifactPK();

        artifactPK.setGroupId(artifactDTO.getGroupId());
        artifactPK.setArtifactId(artifactDTO.getArtifactId());
        artifactPK.setVersion(artifactDTO.getVersion());
        out.setId(artifactPK);

        LOGGER.info("Adapt - out: " + out.getClass());
        return out;
    }
}
