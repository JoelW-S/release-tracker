package com.github.joelws.release.tracker.service.artifact.execution;


import com.github.joelws.release.tracker.dao.artifact.ArtifactDao;
import com.github.joelws.release.tracker.entity.artifact.Artifact;
import com.github.joelws.release.tracker.service.ServiceExecution;

public class DeleteArtifactServiceExecution implements ServiceExecution<Artifact, Void> {
    private final ArtifactDao artifactDao;

    public DeleteArtifactServiceExecution(final ArtifactDao artifactDao) {
        this.artifactDao = artifactDao;
    }

    @Override
    public Void execute(final Artifact artifact) {
        if (artifactDao.read(artifact.getId()) != null) {
            artifactDao.delete(artifact.getId());
        }
        return null;
    }
}
