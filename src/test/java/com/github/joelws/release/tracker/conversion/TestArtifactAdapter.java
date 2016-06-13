package com.github.joelws.release.tracker.conversion;

import com.github.joelws.release.tracker.dto.artifact.ArtifactDto;
import com.github.joelws.release.tracker.entity.artifact.Artifact;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestArtifactAdapter {

    private ArtifactDto from;

    private ArtifactDtoToArtifactAdapter adapter;

    @Before
    public void setUp() throws Exception {
        adapter = new ArtifactDtoToArtifactAdapter();
        from = new ArtifactDto();

    }

    @Test
    public void testAdapt() throws Exception {

        from.setArtifactId("artifactId");
        from.setGroupId("groupId");
        from.setVersion("version");

        Artifact result = adapter.adapt(from);

        assertEquals("artifactId", result.getId().getArtifactId());
        assertEquals("groupId", result.getId().getGroupId());
        assertEquals("version", result.getId().getVersion());

    }
}
