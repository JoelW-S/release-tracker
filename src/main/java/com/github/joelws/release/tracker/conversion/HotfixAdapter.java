package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDTO;
import com.github.joelws.release.tracker.dto.release.ReleaseDTO;
import com.github.joelws.release.tracker.entity.release.Release;
import com.github.joelws.release.tracker.interfaces.Adapter;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;


public class HotfixAdapter implements Adapter<ReleaseDTO, Release> {

    private static final Logger LOGGER = Logger.getLogger(HotfixAdapter.class);

    @Override
    public Release adapt(ReleaseDTO releaseDTO) {
        LOGGER.info("Adapt - in: " + releaseDTO.getClass());

        ArtifactAdapter artifactAdapter = new ArtifactAdapter();
        Release out = new Release();
        List<ArtifactDTO> inArtifactList = releaseDTO.getArtifacts();


        out.setName(releaseDTO.getName());

        out.setArtifacts(inArtifactList
                .stream()
                .map(artifactAdapter::adapt)
                .collect(Collectors.toList()));

        LOGGER.info("Adapt - out: " + out.getClass());
        return out;
    }
}
