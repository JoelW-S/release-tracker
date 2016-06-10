package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDto;
import com.github.joelws.release.tracker.dto.release.ReleaseDto;
import com.github.joelws.release.tracker.entity.release.Release;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestHotfixAdapter {
    @Mock
    private ReleaseDto from;

    @Mock
    private ArtifactDto mockArtifactDto;

    private HotfixDtoToHotfixAdapter adapter;

    @Before
    public void setUp() throws Exception {
        adapter = new HotfixDtoToHotfixAdapter();

    }

    @Test
    public void testAdapter() throws Exception {
        List<ArtifactDto> artifactList = new ArrayList<>();
        artifactList.add(mockArtifactDto);

        when(from.getArtifacts()).thenReturn(artifactList);
        when(from.getName()).thenReturn("R1-HF1");

        Release result = adapter.adapt(from);

        verify(from, times(1)).getArtifacts();
        verify(from, never()).getHotfixes();
        verify(from, times(1)).getName();

        assertEquals("R1-HF1", result.getName());

        assertTrue(result.getArtifacts().size() == 1);

        assertTrue(result.getHotfixes().size() == 0);
    }
}
