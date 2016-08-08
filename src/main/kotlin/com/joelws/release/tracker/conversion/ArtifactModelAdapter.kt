package com.joelws.release.tracker.conversion


import com.joelws.release.tracker.entity.artifact.Artifact
import com.joelws.release.tracker.entity.artifact.ArtifactPK
import com.joelws.release.tracker.interfaces.Adapter
import com.joelws.release.tracker.model.artifact.ArtifactModel

open class ArtifactModelAdapter : Adapter<ArtifactModel, Artifact> {

    override fun adapt(incoming: ArtifactModel): Artifact {

        val out = Artifact()
        val artifactPK = ArtifactPK()

        artifactPK.groupId = incoming.groupId
        artifactPK.artifactId = incoming.artifactId
        artifactPK.version = incoming.version
        out.id = artifactPK

        return out
    }
}
