package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDto;
import com.github.joelws.release.tracker.dto.release.ReleaseDto;
import com.github.joelws.release.tracker.entity.release.Release;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class TestHotfixAdapter {
    private ReleaseDto from;

    private ArtifactDto mockArtifactDto;

    private HotfixDtoToHotfixAdapter adapter;

    @Before
    public void setUp() throws Exception {
        from = new ReleaseDto();
        mockArtifactDto = new ArtifactDto();
        adapter = new HotfixDtoToHotfixAdapter();
    }

    @Test
    public void testAdapter() throws Exception {
        List<ArtifactDto> artifactList = new ArrayList<>();
        artifactList.add(mockArtifactDto);

        from.setArtifacts(artifactList);
        from.setName("R1-HF1");

        Release result = adapter.adapt(from);

        assertEquals("R1-HF1", result.getName());

        assertTrue(result.getArtifacts().size() == 1);

        assertTrue(result.getHotfixes().size() == 0);
    }
}
