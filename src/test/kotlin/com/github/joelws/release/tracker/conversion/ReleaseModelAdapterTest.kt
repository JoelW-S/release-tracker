package com.github.joelws.release.tracker.conversion

import com.github.joelws.release.tracker.entity.release.Release
import com.github.joelws.release.tracker.model.artifact.ArtifactModel
import com.github.joelws.release.tracker.model.release.ReleaseModel
import org.hamcrest.CoreMatchers.hasItem
import org.junit.Assert.*
import org.junit.Test

class ReleaseModelAdapterTest {

    private lateinit var mockReleaseModel: ReleaseModel

    private val mockArtifactModel = ArtifactModel(artifactId = "artifactId",
            groupId = "groupId",
            version = "version")

    private val mockHotfixReleaseModel = ReleaseModel(name = "R1-HF1")

    private val adapter = ReleaseModelAdapter()


    @Test
    fun testAdapt() {
        val artifactList = mutableListOf(mockArtifactModel)


        val hotfixSet = mutableSetOf(mockHotfixReleaseModel)

        mockReleaseModel = ReleaseModel(name = "R1",
                artifacts = artifactList,
                hotfixes = hotfixSet)

        val result = adapter.adapt(mockReleaseModel)

        assertEquals("R1", result.name);

        assertTrue(result.artifacts.size == 1)

        assertTrue(result.hotfixes.size == 1)

        assertThat(result.hotfixes, hasItem(Release(name = "R1-HF1")))

    }
}
