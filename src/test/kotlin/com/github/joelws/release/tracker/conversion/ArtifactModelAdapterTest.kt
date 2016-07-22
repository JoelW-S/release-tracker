package com.github.joelws.release.tracker.conversion

import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.entity.artifact.ArtifactPK
import com.github.joelws.release.tracker.model.artifact.ArtifactModel
import org.junit.Assert.assertEquals
import org.junit.Test

class ArtifactModelAdapterTest {

    private val from = ArtifactModel(artifactId = "artifactId",
            groupId = "groupId",
            version = "version")

    private val adapter = ArtifactModelAdapter()

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
