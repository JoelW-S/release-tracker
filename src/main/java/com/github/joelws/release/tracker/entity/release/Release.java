package com.github.joelws.release.tracker.entity.release;

import com.github.joelws.release.tracker.entity.artifact.Artifact;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity public class Release
{
    @Id @Column(name = "NAME") private String name;

    @ManyToMany(
            fetch = FetchType.EAGER) private List<Artifact> artifacts = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER) @JoinTable(name = "RELEASE_HOTFIX") @JoinColumn(name = "HOTFIX_NAME")
    private Set<Release> hotfixes = new HashSet<>();

    public Release(final String name)
    {
        this.name = name;
    }

    public Release()
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

    public List<Artifact> getArtifacts()
    {
        return artifacts;
    }

    public void setArtifacts(List<Artifact> artifacts)
    {
        this.artifacts = artifacts;
    }

    public Set<Release> getHotfixes()
    {
        return hotfixes;
    }

    public void setHotfixes(Set<Release> hotfixes)
    {
        this.hotfixes = hotfixes;
    }
}
