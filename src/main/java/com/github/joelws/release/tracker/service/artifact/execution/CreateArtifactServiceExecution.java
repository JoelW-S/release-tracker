package com.github.joelws.release.tracker.service.artifact.execution;

import com.github.joelws.release.tracker.dao.artifact.ArtifactDao;
import com.github.joelws.release.tracker.entity.artifact.Artifact;
import com.github.joelws.release.tracker.service.ServiceExecution;

import org.apache.log4j.Logger;

public class CreateArtifactServiceExecution implements ServiceExecution<Artifact, Artifact> {

    private static final Logger LOGGER = Logger.getLogger(CreateArtifactServiceExecution.class);
    private final ArtifactDao artifactDao;

    public CreateArtifactServiceExecution(final ArtifactDao artifactDao) {
        this.artifactDao = artifactDao;
    }

    @Override
    public Artifact execute(final Artifact param) {
        LOGGER.info("Creating artifact: " + param.getId());
        return (artifactDao.read(param.getId()) == null) ? artifactDao.create(param) : null;
    }
}
