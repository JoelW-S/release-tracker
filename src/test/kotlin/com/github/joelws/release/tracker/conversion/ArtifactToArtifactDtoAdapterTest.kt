package com.github.joelws.release.tracker.conversion

import com.github.joelws.release.tracker.dto.artifact.ArtifactDto
import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.entity.artifact.ArtifactPK
import org.junit.Assert.assertEquals
import org.junit.Test


class ArtifactToArtifactDtoAdapterTest {

    private val from = Artifact(ArtifactPK(artifactId = "artifactId",
            groupId = "groupId",
            version = "version"))

    private val adapter = ArtifactToArtifactDtoAdapter()

    @Test
    fun testAdapt() {

        val expected = ArtifactDto(artifactId = "artifactId",
                groupId = "groupId",
                version = "version")

        val result = adapter.adapt(from)

        assertEquals(expected, result)
    }
}