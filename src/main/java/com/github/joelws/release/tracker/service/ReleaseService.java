package com.github.joelws.release.tracker.service;

import com.github.joelws.release.tracker.model.Artifact;
import com.github.joelws.release.tracker.model.Release;

public interface ReleaseService
{
    Release create(final Release release);

    Release read(final String releaseName);

    Release update(final Release release);

    void delete(final String releaseName);

    Release addArtifact(final Artifact artifact, final String releaseName);

    Release deleteArtifact(final String artifactId, final String releaseName);

    Release addHotfixRelease(final Release hotfix, final String releaseName);

    Release removeHotfixRelease(final Release hotfix, final String releaseName);

}
