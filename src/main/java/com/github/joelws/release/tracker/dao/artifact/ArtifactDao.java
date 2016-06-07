package com.github.joelws.release.tracker.dao.artifact;

import com.github.joelws.release.tracker.dao.GenericDao;
import com.github.joelws.release.tracker.entity.artifact.Artifact;
import com.github.joelws.release.tracker.entity.artifact.ArtifactPK;

import java.util.List;

public interface ArtifactDao extends GenericDao<Artifact, ArtifactPK>
{
    List<Artifact> list();
}
