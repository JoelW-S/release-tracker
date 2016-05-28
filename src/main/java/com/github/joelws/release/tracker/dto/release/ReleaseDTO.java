package com.github.joelws.release.tracker.dto.release;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReleaseDTO
{
    private String name;

    private List<ArtifactDTO> artifacts = new ArrayList<>();

    private Set<ReleaseDTO> hotfixes = new HashSet<>();

    public ReleaseDTO(final String name)
    {
        this.name = name;
    }

    public ReleaseDTO()
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

    public List<ArtifactDTO> getArtifacts()
    {
        return artifacts;
    }

    public void setArtifacts(List<ArtifactDTO> artifacts)
    {
        this.artifacts = artifacts;
    }

    public Set<ReleaseDTO> getHotfixes() {
        return hotfixes;
    }

    public void setHotfixes(Set<ReleaseDTO> hotfixes) {
        this.hotfixes = hotfixes;
    }
}
