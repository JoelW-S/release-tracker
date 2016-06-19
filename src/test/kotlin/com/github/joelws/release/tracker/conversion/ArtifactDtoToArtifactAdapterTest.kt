package com.github.joelws.release.tracker.conversion

import com.github.joelws.release.tracker.dto.artifact.ArtifactDto
import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.entity.artifact.ArtifactPK
import org.junit.Assert.assertEquals
import org.junit.Test

class ArtifactDtoToArtifactAdapterTest {

    private val from = ArtifactDto(artifactId = "artifactId",
            groupId = "groupId",
            version = "version")

    private val adapter = ArtifactDtoToArtifactAdapter()

    @Test
    fun testAdapt() {

        val expected = Artifact(ArtifactPK().apply {
            artifactId = "artifactId"
            groupId = "groupId"
            version = "version"
        })

        val result = adapter.adapt(from)

        assertEquals(expected, result)

    }
}
