package com.github.joelws.release.tracker.service;

import com.github.joelws.release.tracker.dao.ReleaseDao;
import com.github.joelws.release.tracker.exception.ServiceException;
import com.github.joelws.release.tracker.model.Artifact;
import com.github.joelws.release.tracker.model.Release;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import java.util.Iterator;

public class ReleaseServiceImpl implements ReleaseService {
    private Logger LOGGER = Logger.getLogger(ReleaseServiceImpl.class);

    private ReleaseDao releaseDao;

    public ReleaseServiceImpl(ReleaseDao releaseDao) {
        this.releaseDao = releaseDao;
    }

    @Override
    public Release create(Release release) {
        try {
            LOGGER.info("Creating new release: " + release.getName());
            return releaseDao.create(release);
        } catch (final DataAccessException e) {
            LOGGER.error("Failed to create release: " + e.getMessage());
            throw new ServiceException(Response.Status.BAD_REQUEST, e);
        }
    }

    @Override
    public Release read(String releaseName) {

        try {
            LOGGER.info("Finding release: " + releaseName);
            Release release = releaseDao.read(releaseName);
            if (release == null) {
                LOGGER.error("Failed to find release: " + releaseName);
                throw new ServiceException(Response.Status.NOT_FOUND);
            }
            return release;
        } catch (final DataAccessException e) {
            LOGGER.error("Failed to find release: " + e.getMessage());
            throw new ServiceException(Response.Status.NO_CONTENT, e);
        }

    }

    @Override
    public Release update(Release release) {

        try {
            LOGGER.info("Updating release: " + release.getName());
            return releaseDao.update(release);
        } catch (final DataAccessException e) {
            LOGGER.error("Failed to update release: " + e.getMessage());
            throw new ServiceException(Response.Status.BAD_REQUEST, e);
        }
    }

    @Override
    public void delete(String releaseName) {
        try {
            LOGGER.info("Deleting release: " + releaseName);
            releaseDao.delete(releaseName);
        } catch (final DataAccessException e) {
            LOGGER.error("Failed to delete release: " + e.getMessage());
            throw new ServiceException(Response.Status.NOT_FOUND, e);
        }
    }

    @Transactional
    @Override
    public Release addArtifact(Artifact artifact, String releaseName) {
        try {
            LOGGER.info(String.format("Adding artifact %s to %s", artifact.getArtifactId(), releaseName));
            Release release = releaseDao.read(releaseName);

            release.getArtifacts().add(artifact);

            return update(release);
        } catch (final DataAccessException e) {
            LOGGER.error("Failed to add artifact " + artifact.getArtifactId());
            throw new ServiceException(Response.Status.BAD_REQUEST, e);
        }
    }

    @Transactional
    @Override
    public Release deleteArtifact(String artifactId, String releaseName) {
        try {
            LOGGER.info(String.format("Deleting artifact %s from %s", artifactId, releaseName));
            Release release = read(releaseName);
            for (Iterator<Artifact> it = release.getArtifacts().iterator(); it.hasNext(); ) {
                Artifact artifactInQuestion = it.next();
                if (artifactInQuestion.getArtifactId().equals(artifactId)) {
                    it.remove();
                }
            }
            update(release);
        } catch (final DataAccessException e) {
            LOGGER.error("Failed to delete artifact " + artifactId);
            throw new ServiceException(Response.Status.NOT_FOUND, e);
        }

        return null;
    }

    @Transactional
    @Override
    public Release addHotfixRelease(Release hotfix, String baseReleaseName) {
        if (hotfix != null && baseReleaseName != null) {
            try {
                LOGGER.info(String.format("Adding Hotfix %s to %s", hotfix.getName(), baseReleaseName));
                Release baseRelease = read(baseReleaseName);
                baseRelease.getHotfixes().add(hotfix);
                releaseDao.update(baseRelease);
            } catch (final DataAccessException e) {
                LOGGER.error("Failed to add Hotfix: " + e.getMessage());
                throw new ServiceException(Response.Status.BAD_REQUEST, e);
            }
        }
        return null;
    }

    @Transactional
    @Override
    public Release removeHotfixRelease(Release hotfix, String baseReleaseName) {
        try {
            LOGGER.info(String.format("Removing Hotfix %s from %s", hotfix.getName(), baseReleaseName));
            Release baseRelease = read(baseReleaseName);
            if (baseRelease != null) {
                for (Iterator<Release> it = baseRelease.getHotfixes().iterator(); it.hasNext(); ) {
                    Release releaseInQuestion = it.next();
                    if (releaseInQuestion.getName().equals(hotfix.getName())) {
                        it.remove();
                    }
                }
                return update(baseRelease);
            }
        } catch (final DataAccessException e) {
            LOGGER.error("Failed to remove hotfix from release: " + e.getMessage());
            throw new ServiceException(Response.Status.NOT_FOUND, e);
        }
        return null;
    }
}
