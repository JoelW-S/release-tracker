package com.joelws.release.tracker.conversion


import com.joelws.release.tracker.entity.artifact.Artifact
import com.joelws.release.tracker.entity.artifact.ArtifactPK
import com.joelws.release.tracker.interfaces.Adapter
import com.joelws.release.tracker.model.artifact.ArtifactModel

open class ArtifactModelAdapter : Adapter<ArtifactModel, Artifact> {

    override fun adapt(incoming: ArtifactModel): Artifact {

        return Artifact(ArtifactPK(groupId = incoming.groupId,
                artifactId = incoming.artifactId,
                version = incoming.version))
    }
}
