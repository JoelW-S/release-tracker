package com.github.joelws.release.tracker.factory

import com.github.joelws.release.tracker.conversion.*
import com.github.joelws.release.tracker.entity.artifact.Artifact
import com.github.joelws.release.tracker.entity.environment.Environment
import com.github.joelws.release.tracker.entity.release.Release
import com.github.joelws.release.tracker.exception.ReleaseTrackerException
import com.github.joelws.release.tracker.interfaces.Adapter
import com.github.joelws.release.tracker.model.artifact.ArtifactModel
import com.github.joelws.release.tracker.model.environment.EnvironmentModel
import com.github.joelws.release.tracker.model.release.ReleaseModel
import com.github.joelws.release.tracker.response.RestResponse.ServerError

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
