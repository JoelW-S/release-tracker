package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDto;
import com.github.joelws.release.tracker.entity.artifact.Artifact;
import com.github.joelws.release.tracker.entity.artifact.ArtifactPK;
import com.github.joelws.release.tracker.interfaces.Adapter;

import org.apache.log4j.Logger;

public class ArtifactAdapter implements Adapter<ArtifactDto, Artifact> {
    private static final Logger LOGGER = Logger.getLogger(ArtifactAdapter.class);

    @Override
    public Artifact adapt(ArtifactDto artifactDto) {
        LOGGER.info("Adapt - in: " + artifactDto.getClass());

        Artifact out = new Artifact();
        ArtifactPK artifactPK = new ArtifactPK();

        artifactPK.setGroupId(artifactDto.getGroupId());
        artifactPK.setArtifactId(artifactDto.getArtifactId());
        artifactPK.setVersion(artifactDto.getVersion());
        out.setId(artifactPK);

        LOGGER.info("Adapt - out: " + out.getClass());
        return out;
    }
}
