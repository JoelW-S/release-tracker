package com.github.joelws.release.tracker.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity public class Artifact

{
    @EmbeddedId ArtifactPK id;


    public Artifact()
    {
    }

    public ArtifactPK getId()
    {
        return id;
    }

    public void setId(ArtifactPK id)
    {
        this.id = id;
    }
}
