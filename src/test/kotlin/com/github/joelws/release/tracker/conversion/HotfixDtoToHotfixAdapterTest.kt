package com.github.joelws.release.tracker.conversion

import com.github.joelws.release.tracker.dto.artifact.ArtifactDto
import com.github.joelws.release.tracker.dto.release.ReleaseDto
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*


class HotfixDtoToHotfixAdapterTest {
    private val from = ReleaseDto()

    private val mockArtifactDto = ArtifactDto()

    private val adapter = HotfixDtoToHotfixAdapter()

    @Test
    fun testAdapt() {

        val artifactList = ArrayList<ArtifactDto>().apply {
            add(mockArtifactDto)
        }

        from.apply {
            name = "R1-HF1"
            artifacts = artifactList
        }

        val result = adapter.adapt(from)

        assertEquals("R1-HF1", result.name)

        assertTrue(result.artifacts.size == 1)

        assertTrue(result.hotfixes.isEmpty())

    }
}
