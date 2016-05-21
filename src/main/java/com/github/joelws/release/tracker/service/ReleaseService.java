package com.github.joelws.release.tracker.service;

import com.github.joelws.release.tracker.exception.DatabaseException;
import com.github.joelws.release.tracker.model.Artifact;
import com.github.joelws.release.tracker.model.Release;

public interface ReleaseService
{
    Release create(final Release release) throws DatabaseException;

    Release read(final String releaseName) throws DatabaseException;

    Release update(final Release release) throws DatabaseException;

    void delete(final String releaseName) throws DatabaseException;

    Release addArtifact(final Artifact artifact, final String releaseName) throws DatabaseException;

    Release deleteArtifact(final String artifactId, final String releaseName) throws DatabaseException;

    Release addHotfixRelease(final Release hotfix, final String releaseName) throws DatabaseException;

    Release removeHotfixRelease(final Release hotfix, final String releaseName) throws DatabaseException;

}
