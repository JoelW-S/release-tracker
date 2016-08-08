package com.joelws.release.tracker.conversion

import com.joelws.release.tracker.model.artifact.ArtifactModel
import com.joelws.release.tracker.model.release.ReleaseModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test


class HotfixModelAdapterTest {
    private lateinit var from: ReleaseModel

    private val mockArtifactModel = ArtifactModel(artifactId = "artifactId",
            groupId = "groupId",
            version = "version")

    private val adapter = HotfixModelAdapter()

    @Test
    fun testAdapt() {

        val artifactList = mutableListOf(mockArtifactModel)

        from = ReleaseModel(name = "R1-HF1",
                artifacts = artifactList)

        val result = adapter.adapt(from)

        assertEquals("R1-HF1", result.name)

        assertTrue(result.artifacts.size == 1)

        assertTrue(result.hotfixes.isEmpty())

    }
}
