package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDto;
import com.github.joelws.release.tracker.entity.artifact.Artifact;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class) public class TestArtifactAdapter
{
    @Mock
    private ArtifactDto from;

    private ArtifactDtoToArtifactAdapter adapter;

    @Before public void setUp() throws Exception
    {
        adapter = new ArtifactDtoToArtifactAdapter();

    }

    @Test public void testAdapt() throws Exception
    {
        when(from.getArtifactId()).thenReturn("artifactId");
        when(from.getGroupId()).thenReturn("groupId");
        when(from.getVersion()).thenReturn("version");

        Artifact result = adapter.adapt(from);

        verify(from, times(1)).getArtifactId();
        verify(from, times(1)).getGroupId();
        verify(from, times(1)).getVersion();

        Assert.assertEquals(result.getId().getArtifactId(), "artifactId");
        Assert.assertEquals(result.getId().getGroupId(), "groupId");
        Assert.assertEquals(result.getId().getVersion(), "version");

    }
}
