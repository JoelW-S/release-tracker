package com.github.joelws.release.tracker.dto.artifact;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArtifactDTO
{
    @JsonProperty("groupid")
    private String groupId;

    @JsonProperty("artifactid")
    private String artifactId;

    @JsonProperty("version")
    private String version;

    public ArtifactDTO()
    {
    }

    public String getGroupId()
    {
        return groupId;
    }

    public void setGroupId(String groupId)
    {
        this.groupId = groupId;
    }

    public String getArtifactId()
    {
        return artifactId;
    }

    public void setArtifactId(String artifactId)
    {
        this.artifactId = artifactId;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }
}
