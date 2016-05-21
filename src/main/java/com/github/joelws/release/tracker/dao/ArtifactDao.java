package com.github.joelws.release.tracker.dao;

import com.github.joelws.release.tracker.common.GenericDao;
import com.github.joelws.release.tracker.model.Artifact;

import java.util.List;


public interface ArtifactDao extends GenericDao<Artifact, String>
{

        List<Artifact> findAllById(final String id);
}
