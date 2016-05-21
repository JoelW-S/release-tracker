package com.github.joelws.release.tracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity public class Artifact

{
    @Column(name = "GROUP_ID") private String groupId;

    @Id @Column(name = "ARTIFACT_ID") private String artifactId;
    @Column(name = "VERSION") private String version;

    @JsonIgnore
    @ManyToMany(mappedBy = "artifacts")
    private Set<Release> releases = new HashSet<>();

    public Artifact(final String groupId, final String artifactId, final String version)
    {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }

    public Artifact()
    {
    }

    public Set<Release> getReleases()
    {
        return releases;
    }

    public void setReleases(Set<Release> releases)
    {
        this.releases = releases;
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
