package com.github.joelws.release.tracker.dto.release;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDTO;

import java.util.ArrayList;
import java.util.List;

public class ReleaseDTO
{
    private String name;

    private List<ArtifactDTO> artifacts = new ArrayList<>();

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
}
