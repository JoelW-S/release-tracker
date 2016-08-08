package com.joelws.release.tracker.factory

import com.joelws.release.tracker.conversion.*
import com.joelws.release.tracker.entity.artifact.Artifact
import com.joelws.release.tracker.entity.environment.Environment
import com.joelws.release.tracker.entity.release.Release
import com.joelws.release.tracker.exception.ReleaseTrackerException
import com.joelws.release.tracker.interfaces.Adapter
import com.joelws.release.tracker.model.artifact.ArtifactModel
import com.joelws.release.tracker.model.environment.EnvironmentModel
import com.joelws.release.tracker.model.release.ReleaseModel
import com.joelws.release.tracker.response.RestResponse.ServerError

open class AdapterFactoryImpl(private val artifactModelAdapter: Adapter<ArtifactModel, Artifact>,
                              private val artifactAdapter: Adapter<Artifact, ArtifactModel>,
                              private val releaseModelAdapter: Adapter<ReleaseModel, Release>,
                              private val releaseAdapter: Adapter<Release, ReleaseModel>,
                              private val environmentModelAdapter: Adapter<EnvironmentModel, Environment>,
                              private val environmentAdapter: Adapter<Environment, EnvironmentModel>) : AdapterFactory {
    override fun getAdapter(klazz: Class<out Adapter<*, *>>): Adapter<*, *> {
        return when (klazz) {
            ArtifactModelAdapter::class.java -> artifactModelAdapter
            ArtifactAdapter::class.java -> artifactAdapter
            ReleaseModelAdapter::class.java -> releaseModelAdapter
            ReleaseAdapter::class.java -> releaseAdapter
            EnvironmentModelAdapter::class.java -> environmentModelAdapter
            EnvironmentAdapter::class.java -> environmentAdapter
            else -> throw ReleaseTrackerException(ServerError("No such adapter exists"))
        }
    }

}
