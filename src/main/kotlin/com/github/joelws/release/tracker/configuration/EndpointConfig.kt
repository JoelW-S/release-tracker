package com.github.joelws.release.tracker.configuration

import com.github.joelws.release.tracker.endpoint.artifact.CreateArtifactEndpoint
import com.github.joelws.release.tracker.endpoint.artifact.DeleteArtifactEndpoint
import com.github.joelws.release.tracker.endpoint.artifact.ReadArtifactEndpoint
import com.github.joelws.release.tracker.endpoint.artifact.UpdateArtifactEndpoint
import com.github.joelws.release.tracker.endpoint.environment.*
import com.github.joelws.release.tracker.endpoint.release.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(ServiceConfig::class)
open class EndpointConfig {

    @Autowired
    lateinit var serviceConfig: ServiceConfig

    @Bean
    open fun createArtifactEndpoint() = CreateArtifactEndpoint(serviceConfig.artifactService())

    @Bean
    open fun readArtifactEndpoint() = ReadArtifactEndpoint(serviceConfig.artifactService())

    @Bean
    open fun updateArtifactEndpoint() = UpdateArtifactEndpoint(serviceConfig.artifactService())

    @Bean
    open fun deleteArtifactEndpoint() = DeleteArtifactEndpoint(serviceConfig.artifactService())

    @Bean
    open fun createReleaseEndpoint() = CreateReleaseEndpoint(serviceConfig.releaseService())

    @Bean
    open fun readReleaseEndpoint() = ReadReleaseEndpoint(serviceConfig.releaseService())

    @Bean
    open fun updateReleaseEndpoint() = UpdateReleaseEndpoint(serviceConfig.releaseService())

    @Bean
    open fun deleteReleaseEndpoint() = DeleteReleaseEndpoint(serviceConfig.releaseService())

    @Bean
    open fun listReleaseEndpoint() = ListReleaseEndpoint(serviceConfig.releaseService())

    @Bean
    open fun createEnvironmentEndpoint() = CreateEnvironmentEndpoint(serviceConfig.environmentService())

    @Bean
    open fun readEnvironmentEndpoint() = ReadEnvironmentEndpoint(serviceConfig.environmentService())

    @Bean
    open fun updateEnvironmentEndpoint() = UpdateEnvironmentEndpoint(serviceConfig.environmentService())

    @Bean
    open fun deleteEnvironmentEndpoint() = DeleteEnvironmentEndpoint(serviceConfig.environmentService())

    @Bean
    open fun listEnvironmentEndpoint() = ListEnvironmentEndpoint(serviceConfig.environmentService())

}
