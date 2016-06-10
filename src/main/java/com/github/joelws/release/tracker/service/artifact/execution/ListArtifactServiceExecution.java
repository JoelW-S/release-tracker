package com.github.joelws.release.tracker.service.artifact.execution;

import com.github.joelws.release.tracker.dao.artifact.ArtifactDao;
import com.github.joelws.release.tracker.entity.artifact.Artifact;
import com.github.joelws.release.tracker.service.ServiceExecution;

import org.apache.log4j.Logger;

import java.util.List;

public class ListArtifactServiceExecution implements ServiceExecution<Void, List<Artifact>> {
    private static final Logger LOGGER = Logger.getLogger(ListArtifactServiceExecution.class);
    private final ArtifactDao artifactDao;

    public ListArtifactServiceExecution(final ArtifactDao artifactDao) {
        this.artifactDao = artifactDao;
    }

    @Override
    public List<Artifact> execute(final Void param) {
        LOGGER.info("Finding all artifacts");
        return artifactDao.list();
    }
}
