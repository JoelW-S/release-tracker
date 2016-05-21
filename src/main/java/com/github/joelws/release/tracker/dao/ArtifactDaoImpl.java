package com.github.joelws.release.tracker.dao;


import com.github.joelws.release.tracker.common.GenericDaoImpl;
import com.github.joelws.release.tracker.model.Artifact;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public class ArtifactDaoImpl extends GenericDaoImpl<Artifact, String> implements ArtifactDao
{
    public ArtifactDaoImpl()
    {
        super(Artifact.class);
    }
    @Transactional
    @SuppressWarnings(value = "unchecked") @Override public List<Artifact> findAllById(String id)
    {
        String query = String.format("SELECT a FROM Artifact a WHERE a.id = '%s'", id);
        return (List<Artifact>) super.getEntityManager().createQuery(query).getResultList();
    }
}
