package com.github.joelws.release.tracker.conversion

import com.github.joelws.release.tracker.dto.release.ReleaseDto
import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.entity.release.Release
import org.hamcrest.CoreMatchers.hasItem
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class ReleaseToReleaseDtoAdapterTest {


    private val mockRelease = Release()

    private val mockArtifact = Artifact()

    private val mockHotfixRelease = Release(name = "R1-HF1")

    private val adapter = ReleaseToReleaseDtoAdapter()

    @Test
    fun testAdapt() {
        val artifactList = ArrayList<Artifact>().apply {
            add(mockArtifact)
        }


        val hotfixSet = HashSet<Release>().apply {
            add(mockHotfixRelease)
        }

        mockRelease.apply {
            name = "R1"
            artifacts = artifactList
            hotfixes = hotfixSet
        }

        val result = adapter.adapt(mockRelease)

        assertEquals("R1", result.name);

        assertTrue(result.artifacts.size == 1)

        assertTrue(result.hotfixes.size == 1)

        assertThat(result.hotfixes, hasItem(ReleaseDto(name = "R1-HF1")))
    }
}