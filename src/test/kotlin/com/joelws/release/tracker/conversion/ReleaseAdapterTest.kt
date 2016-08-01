package com.joelws.release.tracker.conversion

import com.joelws.release.tracker.entity.artifact.Artifact
import com.joelws.release.tracker.entity.artifact.ArtifactPK
import com.joelws.release.tracker.entity.release.Release
import com.joelws.release.tracker.model.release.ReleaseModel
import org.hamcrest.CoreMatchers.hasItem
import org.junit.Assert.*
import org.junit.Test

class ReleaseAdapterTest {


    private val mockRelease = Release()

    private val mockArtifact = Artifact(ArtifactPK(artifactId = "artifactId",
            groupId = "groupId",
            version = "version"))

    private val mockHotfixRelease = Release(name = "R1-HF1")

    private val adapter = ReleaseAdapter()

    @Test
    fun testAdapt() {
        val artifactList = mutableListOf<Artifact>(mockArtifact)

        val hotfixSet = mutableSetOf(mockHotfixRelease)

        mockRelease.apply {
            name = "R1"
            artifacts = artifactList
            hotfixes = hotfixSet
        }

        val result = adapter.adapt(mockRelease)

        assertEquals("R1", result.name)

        assertTrue(result.artifacts.size == 1)

        assertTrue(result.hotfixes.size == 1)

        assertThat(result.hotfixes, hasItem(ReleaseModel(name = "R1-HF1")))
    }
}
