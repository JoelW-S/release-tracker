package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.release.ReleaseDto;
import com.github.joelws.release.tracker.entity.artifact.Artifact;
import com.github.joelws.release.tracker.entity.release.Release;
import com.github.joelws.release.tracker.interfaces.Adapter;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;


public class HotfixToHotfixDtoAdapter implements Adapter<Release, ReleaseDto> {

    private static final Logger LOGGER = Logger.getLogger(HotfixToHotfixDtoAdapter.class);

    @Override
    public ReleaseDto adapt(Release release) {
        LOGGER.info("Adapt - in: " + release.getClass());

        ArtifactToArtifactDtoAdapter artifactToArtifactDtoAdapter = new ArtifactToArtifactDtoAdapter();
        ReleaseDto out = new ReleaseDto();
        List<Artifact> inArtifactList = release.getArtifacts();

        out.setName(release.getName());

        out.setArtifacts(inArtifactList
                .stream()
                .map(artifactToArtifactDtoAdapter::adapt)
                .collect(Collectors.toList()));

        LOGGER.info("Adapt - out: " + out.getClass());
        return out;
    }
}
