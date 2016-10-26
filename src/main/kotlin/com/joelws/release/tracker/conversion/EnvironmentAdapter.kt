package com.joelws.release.tracker.conversion

import com.joelws.release.tracker.entity.environment.Environment
import com.joelws.release.tracker.interfaces.Adapter
import com.joelws.release.tracker.model.environment.EnvironmentModel


open class EnvironmentAdapter : Adapter<Environment, EnvironmentModel> {

    override fun adapt(incoming: Environment): EnvironmentModel {

        val releaseAdapter = ReleaseAdapter()

        val out = EnvironmentModel(name = incoming.environmentName, release = releaseAdapter.adapt(incoming.release))

        return out
    }

}
