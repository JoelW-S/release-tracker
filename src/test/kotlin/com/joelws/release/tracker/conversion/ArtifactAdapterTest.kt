package com.joelws.release.tracker.conversion

import com.joelws.release.tracker.entity.artifact.Artifact
import com.joelws.release.tracker.entity.artifact.ArtifactPK
import com.joelws.release.tracker.model.artifact.ArtifactModel
import org.junit.Assert.assertEquals
import org.junit.Test


class ArtifactAdapterTest {

    private val from = Artifact(ArtifactPK(artifactId = "artifactId",
            groupId = "groupId",
            version = "version"))

    private val adapter = ArtifactAdapter()

    @Test
    fun testAdapt() {

        val expected = ArtifactModel(artifactId = "artifactId",
                groupId = "groupId",
                version = "version")

        val result = adapter.adapt(from)

        assertEquals(expected, result)
    }
}
