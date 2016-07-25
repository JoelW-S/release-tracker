package com.github.joelws.release.tracker.configuration

import com.github.joelws.release.tracker.interfaces.BusinessService
import com.github.joelws.release.tracker.service.artifact.ArtifactService
import com.github.joelws.release.tracker.service.environment.EnvironmentService
import com.github.joelws.release.tracker.service.release.ReleaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(ServiceOperationConfig::class)
open class ServiceConfig {

    @Autowired
    lateinit var serviceOperationConfig: ServiceOperationConfig

    @Bean
    open fun artifactService(): BusinessService<String> = ArtifactService(serviceOperationConfig.createArtifactServiceOperation(), serviceOperationConfig.readArtifactServiceOperation(), serviceOperationConfig.updateArtifactServiceOperation(), serviceOperationConfig.deleteArtifactServiceOperation(), serviceOperationConfig.listArtifactServiceOperation())

    @Bean
    open fun releaseService(): BusinessService<String> = ReleaseService(serviceOperationConfig.createReleaseServiceOperation(), serviceOperationConfig.readReleaseServiceOperation(), serviceOperationConfig.updateReleaseServiceOperation(), serviceOperationConfig.deleteReleaseServiceOperation(), serviceOperationConfig.listReleaseServiceOperation())

    @Bean
    open fun environmentService(): BusinessService<String> = EnvironmentService(serviceOperationConfig.createEnvironmentServiceOperation(), serviceOperationConfig.readEnvironmentServiceOperation(), serviceOperationConfig.updateEnvironmentServiceOperation(), serviceOperationConfig.deleteEnvironmentServiceOperation(), serviceOperationConfig.listEnvironmentServiceOperation())
}
