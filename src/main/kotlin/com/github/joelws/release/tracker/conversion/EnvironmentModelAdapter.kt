package com.github.joelws.release.tracker.conversion

import com.github.joelws.release.tracker.entity.environment.Environment
import com.github.joelws.release.tracker.interfaces.Adapter
import com.github.joelws.release.tracker.model.environment.EnvironmentModel


open class EnvironmentModelAdapter : Adapter<EnvironmentModel, Environment> {

    override fun adapt(incoming: EnvironmentModel): Environment {

        val releaseModelAdapter = ReleaseModelAdapter()

        val out = Environment(environmentName = incoming.name, release = releaseModelAdapter.adapt(incoming.release))

        return out
    }

}
