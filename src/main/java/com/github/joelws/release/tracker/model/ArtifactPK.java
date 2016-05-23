package com.github.joelws.release.tracker.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ArtifactPK implements Serializable
{
    private String groupId;

    private String artifactId;

    private String version;

    public ArtifactPK()
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
