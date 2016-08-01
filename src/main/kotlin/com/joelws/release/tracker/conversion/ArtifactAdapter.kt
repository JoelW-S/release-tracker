package com.joelws.release.tracker.conversion

import com.joelws.release.tracker.entity.artifact.Artifact
import com.joelws.release.tracker.interfaces.Adapter
import com.joelws.release.tracker.model.artifact.ArtifactModel

open class ArtifactAdapter : Adapter<Artifact, ArtifactModel> {


    override fun adapt(incoming: Artifact): ArtifactModel {

        val out = ArtifactModel(groupId = incoming.id?.groupId!!,
                artifactId = incoming.id?.artifactId!!,
                version = incoming.id?.version!!)
        return out
    }
}
