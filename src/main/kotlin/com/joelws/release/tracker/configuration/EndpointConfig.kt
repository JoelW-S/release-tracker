package com.joelws.release.tracker.configuration

import com.joelws.release.tracker.endpoint.artifact.ArtifactEndpoint
import com.joelws.release.tracker.endpoint.environment.EnvironmentEndpoint
import com.joelws.release.tracker.endpoint.release.ReleaseEndpoint
import com.joelws.release.tracker.exception.ReleaseTrackerErrorHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import ratpack.func.Action
import ratpack.handling.Chain

@Configuration
@Import(ServiceConfig::class)
open class EndpointConfig {

    @Autowired
    lateinit var serviceConfig: ServiceConfig

    @Bean
    open fun serverErrorHandler(): Action<Chain> = Action { chain ->
        chain.register { r ->
            r.add(ReleaseTrackerErrorHandler())
        }.insert(artifactEndpoint())
                .insert(releaseEndpoint())
                .insert(envEndpoint())
    }

    private fun artifactEndpoint(): Action<Chain> = ArtifactEndpoint(serviceConfig.artifactService()).endpoints

    private fun releaseEndpoint(): Action<Chain> = ReleaseEndpoint(serviceConfig.releaseService()).endpoints

    private fun envEndpoint(): Action<Chain> = EnvironmentEndpoint(serviceConfig.environmentService()).endpoints
}

