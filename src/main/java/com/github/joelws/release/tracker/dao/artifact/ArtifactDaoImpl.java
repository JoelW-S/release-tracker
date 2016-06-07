package com.github.joelws.release.tracker.dao.artifact;

import com.github.joelws.release.tracker.dao.GenericDaoImpl;
import com.github.joelws.release.tracker.entity.artifact.Artifact;
import com.github.joelws.release.tracker.entity.artifact.ArtifactPK;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository public class ArtifactDaoImpl extends GenericDaoImpl<Artifact, ArtifactPK> implements ArtifactDao
{
    public ArtifactDaoImpl()
    {
        super();
    }

    @SuppressWarnings("unchecked") @Override public List<Artifact> list()
    {
        final String query = "SELECT a FROM Artifact a";
        return (List<Artifact>) getEntityManager().createQuery(query).getResultList();
    }
}
