package com.github.joelws.release.tracker.dto.release;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReleaseDto
{
    private String name;

    private List<ArtifactDto> artifacts = new ArrayList<>();

    private Set<ReleaseDto> hotfixes = new HashSet<>();

    public ReleaseDto(final String name)
    {
        this.name = name;
    }

    public ReleaseDto()
    {
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<ArtifactDto> getArtifacts()
    {
        return artifacts;
    }

    public void setArtifacts(List<ArtifactDto> artifacts)
    {
        this.artifacts = artifacts;
    }

    public Set<ReleaseDto> getHotfixes() {
        return hotfixes;
    }

    public void setHotfixes(Set<ReleaseDto> hotfixes) {
        this.hotfixes = hotfixes;
    }
}
