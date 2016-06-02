package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDto;
import com.github.joelws.release.tracker.dto.release.ReleaseDto;
import com.github.joelws.release.tracker.entity.release.Release;
import com.github.joelws.release.tracker.interfaces.Adapter;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;


public class HotfixAdapter implements Adapter<ReleaseDto, Release> {

    private static final Logger LOGGER = Logger.getLogger(HotfixAdapter.class);

    @Override
    public Release adapt(ReleaseDto releaseDto) {
        LOGGER.info("Adapt - in: " + releaseDto.getClass());

        ArtifactAdapter artifactAdapter = new ArtifactAdapter();
        Release out = new Release();
        List<ArtifactDto> inArtifactList = releaseDto.getArtifacts();


        out.setName(releaseDto.getName());

        out.setArtifacts(inArtifactList
                .stream()
                .map(artifactAdapter::adapt)
                .collect(Collectors.toList()));

        LOGGER.info("Adapt - out: " + out.getClass());
        return out;
    }
}
