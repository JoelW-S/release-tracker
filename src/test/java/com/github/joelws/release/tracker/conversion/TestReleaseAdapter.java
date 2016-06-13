package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDto;
import com.github.joelws.release.tracker.dto.release.ReleaseDto;
import com.github.joelws.release.tracker.entity.release.Release;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class TestReleaseAdapter {

    private ReleaseDto mockReleaseDto;

    private ArtifactDto mockArtifactDto;

    private ReleaseDto mockHotfixReleaseDto;

    private ReleaseDtoToReleaseAdapter adapter;

    @Before
    public void setUp() throws Exception {
        mockReleaseDto = new ReleaseDto();
        mockArtifactDto = new ArtifactDto();
        mockHotfixReleaseDto = new ReleaseDto();
        adapter = new ReleaseDtoToReleaseAdapter();

    }

    @Test
    public void testAdapter() throws Exception {
        List<ArtifactDto> artifactList = new ArrayList<>();
        artifactList.add(mockArtifactDto);

        Set<ReleaseDto> hotfixSet = new HashSet<>();
        hotfixSet.add(mockHotfixReleaseDto);

        mockReleaseDto.setArtifacts(artifactList);
        mockReleaseDto.setName("R1");
        mockReleaseDto.setHotfixes(hotfixSet);

        Release result = adapter.adapt(mockReleaseDto);

        assertEquals("R1", result.getName());

        assertTrue(result.getArtifacts().size() == 1);

        assertTrue(result.getHotfixes().size() == 1);

    }
}
