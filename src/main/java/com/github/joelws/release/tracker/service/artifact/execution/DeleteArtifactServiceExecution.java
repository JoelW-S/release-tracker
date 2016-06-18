package com.github.joelws.release.tracker.service.artifact.execution;


import com.github.joelws.release.tracker.dao.artifact.ArtifactDao;
import com.github.joelws.release.tracker.entity.artifact.Artifact;
import com.github.joelws.release.tracker.service.ServiceExecution;

import org.apache.log4j.Logger;

public class DeleteArtifactServiceExecution implements ServiceExecution<Artifact, Void> {
    private static final Logger LOGGER = Logger.getLogger(DeleteArtifactServiceExecution.class);
    private final ArtifactDao artifactDao;

    public DeleteArtifactServiceExecution(final ArtifactDao artifactDao) {
        this.artifactDao = artifactDao;
    }

    @Override
    public Void execute(final Artifact artifact) {
        if (artifactDao.read(artifact.getId()) != null) {

            LOGGER.info("Deleting artifact with the following: " +
                    artifact.getId().getGroupId() + " " +
                    artifact.getId().getArtifactId() + " " +
                    artifact.getId().getVersion());

            artifactDao.delete(artifact.getId());
        }
        return null;
    }
}
