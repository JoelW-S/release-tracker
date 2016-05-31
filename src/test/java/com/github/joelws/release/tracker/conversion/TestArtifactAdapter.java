package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDTO;
import com.github.joelws.release.tracker.entity.artifact.Artifact;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class) public class TestArtifactAdapter
{
    @Mock private ArtifactDTO from;

    private ArtifactAdapter adapter;

    @Before public void setUp() throws Exception
    {
        adapter = new ArtifactAdapter();

    }

    @Test public void testAdapt() throws Exception
    {
        Mockito.when(from.getArtifactId()).thenReturn("artifactId");
        Mockito.when(from.getGroupId()).thenReturn("groupId");
        Mockito.when(from.getVersion()).thenReturn("version");

        Artifact result = adapter.adapt(from);

        Mockito.verify(from, Mockito.times(1)).getArtifactId();
        Mockito.verify(from, Mockito.times(1)).getGroupId();
        Mockito.verify(from, Mockito.times(1)).getVersion();

        Assert.assertEquals(result.getId().getArtifactId(), "artifactId");
        Assert.assertEquals(result.getId().getGroupId(), "groupId");
        Assert.assertEquals(result.getId().getVersion(), "version");

    }
}
