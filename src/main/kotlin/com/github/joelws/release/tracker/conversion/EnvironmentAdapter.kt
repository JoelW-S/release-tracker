package com.github.joelws.release.tracker.conversion

import com.github.joelws.release.tracker.entity.environment.Environment
import com.github.joelws.release.tracker.interfaces.Adapter
import com.github.joelws.release.tracker.model.environment.EnvironmentModel


open class EnvironmentAdapter : Adapter<Environment, EnvironmentModel> {

    override fun adapt(incoming: Environment): EnvironmentModel {

        val releaseAdapter = ReleaseAdapter()

        val out = EnvironmentModel(name = incoming.environmentName!!, release = releaseAdapter.adapt(incoming.release!!))

        return out
    }

}
