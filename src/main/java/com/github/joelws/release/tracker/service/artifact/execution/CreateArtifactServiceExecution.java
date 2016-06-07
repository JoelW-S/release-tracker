package com.github.joelws.release.tracker.service.artifact.execution;

import com.github.joelws.release.tracker.dao.artifact.ArtifactDao;
import com.github.joelws.release.tracker.entity.artifact.Artifact;
import com.github.joelws.release.tracker.service.ServiceExecution;

public class CreateArtifactServiceExecution implements ServiceExecution<Artifact, Artifact> {
    private final ArtifactDao artifactDao;

    public CreateArtifactServiceExecution(final ArtifactDao artifactDao) {
        this.artifactDao = artifactDao;
    }

    @Override
    public Artifact execute(Artifact param) {
        return (artifactDao.read(param.getId()) == null) ? artifactDao.create(param) : null;
    }
}
