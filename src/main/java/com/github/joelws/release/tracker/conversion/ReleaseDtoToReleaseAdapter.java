package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDto;
import com.github.joelws.release.tracker.dto.release.ReleaseDto;
import com.github.joelws.release.tracker.entity.release.Release;
import com.github.joelws.release.tracker.interfaces.Adapter;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ReleaseDtoToReleaseAdapter implements Adapter<ReleaseDto, Release> {
    private static final Logger LOGGER = Logger.getLogger(ReleaseDtoToReleaseAdapter.class);

    @Override
    public Release adapt(ReleaseDto releaseDto) {
        LOGGER.info("Adapt - in: " + releaseDto.getClass());

        ArtifactDtoToArtifactAdapter artifactDtoToArtifactAdapter = new ArtifactDtoToArtifactAdapter();
        HotfixDtoToHotfixAdapter hotfixDtoToHotfixAdapter = new HotfixDtoToHotfixAdapter();

        List<ArtifactDto> inArtifactList = releaseDto.getArtifacts();
        Set<ReleaseDto> inHotfixSet = releaseDto.getHotfixes();

        Release out = new Release();

        out.setName(releaseDto.getName());

        out.setArtifacts(inArtifactList
                .stream()
                .map(artifactDtoToArtifactAdapter::adapt)
                .collect(Collectors.toList()));


        out.setHotfixes(inHotfixSet
                .stream()
                .map(hotfixDtoToHotfixAdapter::adapt)
                .collect(Collectors.toSet()));

        LOGGER.info("Adapt - out: " + out.getClass());

        return out;

    }
}

