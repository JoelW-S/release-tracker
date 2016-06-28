package com.github.joelws.release.tracker.conversion

import com.github.joelws.release.tracker.dto.artifact.ArtifactDto
import com.github.joelws.release.tracker.dto.release.ReleaseDto
import com.github.joelws.release.tracker.entity.release.Release
import org.hamcrest.CoreMatchers.hasItem
import org.junit.Assert.*
import org.junit.Test
import java.util.*

class ReleaseDtoToReleaseAdapterTest {

    private lateinit var mockReleaseDto: ReleaseDto

    private val mockArtifactDto = ArtifactDto()

    private val mockHotfixReleaseDto = ReleaseDto(name = "R1-HF1")

    private val adapter = ReleaseDtoToReleaseAdapter()


    @Test
    fun testAdapt() {
        val artifactList = ArrayList<ArtifactDto>().apply {
            add(mockArtifactDto)
        }


        val hotfixSet = HashSet<ReleaseDto>().apply {
            add(mockHotfixReleaseDto)
        }

        mockReleaseDto = ReleaseDto(name = "R1",
                artifacts = artifactList,
                hotfixes = hotfixSet)

        val result = adapter.adapt(mockReleaseDto)

        assertEquals("R1", result.name);

        assertTrue(result.artifacts.size == 1)

        assertTrue(result.hotfixes.size == 1)

        assertThat(result.hotfixes, hasItem(Release(name = "R1-HF1")))

    }
}
