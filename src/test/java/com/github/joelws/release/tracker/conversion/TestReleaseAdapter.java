package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDTO;
import com.github.joelws.release.tracker.dto.release.ReleaseDTO;
import com.github.joelws.release.tracker.entity.release.Release;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class) public class TestReleaseAdapter
{
    @Mock private ReleaseDTO mockReleaseDTO;

    @Mock private ArtifactDTO mockArtifactDTO;

    @Mock private ReleaseDTO mockHotfixReleaseDTO;

    private ReleaseAdapter adapter;

    @Before public void setUp() throws Exception
    {
        adapter = new ReleaseAdapter();

    }

    @Test public void testAdapter() throws Exception
    {
        List<ArtifactDTO> artifactList = new ArrayList<>();
        artifactList.add(mockArtifactDTO);

        Set<ReleaseDTO> hotfixSet = new HashSet<>();
        hotfixSet.add(mockHotfixReleaseDTO);

        Mockito.when(mockReleaseDTO.getArtifacts()).thenReturn(artifactList);
        Mockito.when(mockReleaseDTO.getName()).thenReturn("R1");
        Mockito.when(mockReleaseDTO.getHotfixes()).thenReturn(hotfixSet);
        Release result = adapter.adapt(mockReleaseDTO);

        Mockito.verify(mockReleaseDTO, Mockito.times(1)).getArtifacts();
        Mockito.verify(mockReleaseDTO, Mockito.times(1)).getHotfixes();
        Mockito.verify(mockReleaseDTO, Mockito.times(1)).getName();

        Assert.assertEquals(result.getName(), "R1");

        Assert.assertTrue(result.getArtifacts().size() == 1);

        Assert.assertTrue(result.getHotfixes().size() == 1);

    }
}
