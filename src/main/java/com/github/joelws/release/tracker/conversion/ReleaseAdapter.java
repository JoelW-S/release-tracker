package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDTO;
import com.github.joelws.release.tracker.dto.release.ReleaseDTO;
import com.github.joelws.release.tracker.entity.release.Release;
import com.github.joelws.release.tracker.interfaces.Adapter;
import org.apache.log4j.Logger;

import java.util.List;

public class ReleaseAdapter implements Adapter<ReleaseDTO, Release>
{
    private static final Logger LOGGER = Logger.getLogger(ReleaseAdapter.class);

    @Override public Release adapt(ReleaseDTO releaseDTO)
    {
        LOGGER.info("Adapt - in: " + releaseDTO.getClass());
        ArtifactAdapter artifactAdapter = new ArtifactAdapter();

        List<ArtifactDTO> inArtifactList = releaseDTO.getArtifacts();

        Release out = new Release();
        out.setName(releaseDTO.getName());

        for (ArtifactDTO it : inArtifactList)
        {
            out.getArtifacts().add(artifactAdapter.adapt(it));
        }
        LOGGER.info("Adapt - out: " + out.getClass());
        return out;

    }
}
