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
import com.joelws.release.tracker.response.ErrorMessage

open class AdapterFactoryImpl(private val artifactModelAdapter: Adapter<ArtifactModel, Artifact>,
                              private val artifactAdapter: Adapter<Artifact, ArtifactModel>,
                              private val releaseModelAdapter: Adapter<ReleaseModel, Release>,
                              private val releaseAdapter: Adapter<Release, ReleaseModel>,
                              private val environmentModelAdapter: Adapter<EnvironmentModel, Environment>,
                              private val environmentAdapter: Adapter<Environment, EnvironmentModel>) : AdapterFactory {

    override fun <In, Out> getAdapter(klazz: Class<out Adapter<In, Out>>): Adapter<In, Out> {
        @Suppress("UNCHECKED_CAST")
        return when (klazz) {
            ArtifactModelAdapter::class.java -> artifactModelAdapter as Adapter<In, Out>
            ArtifactAdapter::class.java -> artifactAdapter as Adapter<In, Out>
            ReleaseModelAdapter::class.java -> releaseModelAdapter as Adapter<In, Out>
            ReleaseAdapter::class.java -> releaseAdapter as Adapter<In, Out>
            EnvironmentModelAdapter::class.java -> environmentModelAdapter as Adapter<In, Out>
            EnvironmentAdapter::class.java -> environmentAdapter as Adapter<In, Out>
            else -> throw ReleaseTrackerException(ErrorMessage.INCORRECT_ADAPTER)
        }
    }

}
