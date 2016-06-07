package com.github.joelws.release.tracker.service.artifact.execution;

import com.github.joelws.release.tracker.dao.artifact.ArtifactDao;
import com.github.joelws.release.tracker.entity.artifact.Artifact;
import com.github.joelws.release.tracker.service.ServiceExecution;
import org.apache.log4j.Logger;

public class ReadArtifactServiceExecution implements ServiceExecution<Artifact, Artifact>
{
    private static final Logger LOGGER = Logger.getLogger(ReadArtifactServiceExecution.class);

    private final ArtifactDao artifactDao;

    public ReadArtifactServiceExecution(final ArtifactDao artifactDao)
    {
        this.artifactDao = artifactDao;
    }

    @Override public Artifact execute(Artifact param)
    {
        LOGGER.info("Finding Artifact with the following: " +
                param.getId().getGroupId() + " " +
                param.getId().getArtifactId() + " " +
                param.getId().getVersion());

        return artifactDao.read(param.getId());
    }
}
