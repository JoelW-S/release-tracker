package com.github.joelws.release.tracker.service;

import com.github.joelws.release.tracker.dao.ReleaseDao;
import com.github.joelws.release.tracker.exception.DatabaseException;
import com.github.joelws.release.tracker.model.Artifact;
import com.github.joelws.release.tracker.model.Release;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;

public class ReleaseServiceImpl implements ReleaseService
{
    private Logger LOGGER = Logger.getLogger(ReleaseServiceImpl.class);

    private ReleaseDao releaseDao;

    public ReleaseServiceImpl(ReleaseDao releaseDao)
    {
        this.releaseDao = releaseDao;
    }

    @Override public Release create(Release release) throws DatabaseException
    {
            try
            {
                LOGGER.info("Creating new release: " + release.getName());
                return releaseDao.create(release);
            }
            catch (final Exception e)
            {
                LOGGER.error("Failed to create release: " + e.getMessage());
                throw new DatabaseException("Failed to persist", e.getCause());
            }
    }

    @Override public Release read(String releaseName) throws DatabaseException
    {

        try
        {
            LOGGER.info("Finding release: " + releaseName);
            return releaseDao.read(releaseName);
        }
        catch (final Exception e)
        {
            LOGGER.error("Failed to find release: " + e.getMessage());
            throw new DatabaseException("failed to read: ", e.getCause());
        }
    }

    @Override public Release update(Release release) throws DatabaseException
    {

        try
        {
            LOGGER.info("Updating release: " + release.getName());
            return releaseDao.update(release);
        }
        catch (final Exception e)
        {
            LOGGER.error("Failed to update release: " + e.getMessage());
            throw new DatabaseException("failed to update: " + e.getCause());
        }
    }

    @Override public void delete(String releaseName) throws DatabaseException
    {
        try
        {
            LOGGER.info("Deleting release: " + releaseName);
            releaseDao.delete(releaseName);
        }
        catch (final Exception e)
        {
            LOGGER.error("Failed to delete release: " + e.getMessage());
            throw new DatabaseException("Failed to delete: " + e.getCause());
        }
    }

    @Transactional @Override public Release addArtifact(Artifact artifact, String releaseName) throws DatabaseException
    {
        try
        {
            LOGGER.info(String.format("Adding artifact %s to %s", artifact.getArtifactId(), releaseName));
            Release release = releaseDao.read(releaseName);

            release.getArtifacts().add(artifact);

            return update(release);
        }
        catch (final Exception e)
        {
            LOGGER.error("Failed to add artifact " + artifact.getArtifactId());
            throw new DatabaseException("Failed to add artifact " + e.getCause());
        }
    }

    @Transactional @Override public Release deleteArtifact(String artifactId, String releaseName)
            throws DatabaseException
    {
        try
        {
            LOGGER.info(String.format("Deleting artifact %s from %s", artifactId, releaseName));
            Release release = read(releaseName);
            for (Iterator<Artifact> it = release.getArtifacts().iterator(); it.hasNext(); )
            {
                Artifact artifactInQuestion = it.next();
                if (artifactInQuestion.getArtifactId().equals(artifactId))
                {
                    it.remove();
                }
            }
            update(release);
        }
        catch (final DatabaseException de)
        {
            LOGGER.error("Failed to delete artifact " + artifactId);
            throw de;
        }

        return null;
    }

    @Transactional @Override public Release addHotfixRelease(Release hotfix, String baseReleaseName)
            throws DatabaseException
    {
        if (hotfix != null && baseReleaseName != null)
        {
            try
            {
                LOGGER.info(String.format("Adding Hotfix %s to %s", hotfix.getName(), baseReleaseName));
                Release baseRelease = read(baseReleaseName);
                baseRelease.getHotfixes().add(hotfix);
                releaseDao.update(baseRelease);
            }
            catch (final DatabaseException de)
            {
                LOGGER.error("Failed to add Hotfix: " + de.getMessage());
                throw de;
            }
        }
        return null;
    }

    @Transactional @Override public Release removeHotfixRelease(Release hotfix, String baseReleaseName)
            throws DatabaseException
    {
        try
        {
            LOGGER.info(String.format("Removing Hotfix %s from %s", hotfix.getName(), baseReleaseName));
            Release baseRelease = read(baseReleaseName);
            if (baseRelease != null)
            {
                for (Iterator<Release> it = baseRelease.getHotfixes().iterator(); it.hasNext(); )
                {
                    Release releaseInQuestion = it.next();
                    if (releaseInQuestion.getName().equals(hotfix.getName()))
                    {
                        it.remove();
                    }
                }
                return update(baseRelease);
            }
        }
        catch (final DatabaseException de)
        {
            LOGGER.error("Failed to remove hotfix from release: " + de.getMessage());
            throw de;
        }
        return null;
    }
}
