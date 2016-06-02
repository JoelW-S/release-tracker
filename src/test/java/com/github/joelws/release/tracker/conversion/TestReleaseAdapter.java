package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDto;
import com.github.joelws.release.tracker.dto.release.ReleaseDto;
import com.github.joelws.release.tracker.entity.release.Release;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class) public class TestReleaseAdapter
{
    @Mock
    private ReleaseDto mockReleaseDto;

    @Mock
    private ArtifactDto mockArtifactDto;

    @Mock
    private ReleaseDto mockHotfixReleaseDto;

    private ReleaseDtoToReleaseAdapter adapter;

    @Before public void setUp() throws Exception
    {
        adapter = new ReleaseDtoToReleaseAdapter();

    }

    @Test public void testAdapter() throws Exception
    {
        List<ArtifactDto> artifactList = new ArrayList<>();
        artifactList.add(mockArtifactDto);

        Set<ReleaseDto> hotfixSet = new HashSet<>();
        hotfixSet.add(mockHotfixReleaseDto);

        when(mockReleaseDto.getArtifacts()).thenReturn(artifactList);
        when(mockReleaseDto.getName()).thenReturn("R1");
        when(mockReleaseDto.getHotfixes()).thenReturn(hotfixSet);
        Release result = adapter.adapt(mockReleaseDto);

        verify(mockReleaseDto, times(1)).getArtifacts();
        verify(mockReleaseDto, times(1)).getHotfixes();
        verify(mockReleaseDto, times(1)).getName();

        Assert.assertEquals(result.getName(), "R1");

        Assert.assertTrue(result.getArtifacts().size() == 1);

        Assert.assertTrue(result.getHotfixes().size() == 1);

    }
}