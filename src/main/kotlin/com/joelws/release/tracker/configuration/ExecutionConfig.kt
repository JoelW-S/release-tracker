package com.joelws.release.tracker.configuration

import com.joelws.release.tracker.entity.artifact.Artifact
import com.joelws.release.tracker.entity.environment.Environment
import com.joelws.release.tracker.entity.release.Release
import com.joelws.release.tracker.service.ServiceExecution
import com.joelws.release.tracker.service.artifact.execution.CreateArtifactServiceExecution
import com.joelws.release.tracker.service.artifact.execution.DeleteArtifactServiceExecution
import com.joelws.release.tracker.service.artifact.execution.ListArtifactServiceExecution
import com.joelws.release.tracker.service.artifact.execution.ReadArtifactServiceExecution
import com.joelws.release.tracker.service.environment.execution.*
import com.joelws.release.tracker.service.release.execution.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(DaoConfig::class)
open class ExecutionConfig {

    @Autowired
    lateinit var daoConfiguration: DaoConfig

    @Bean
    open fun createArtifactServiceExecution(): ServiceExecution<Artifact, Artifact?> = CreateArtifactServiceExecution(daoConfiguration.artifactDao())

    @Bean
    open fun readArtifactServiceExecution(): ServiceExecution<Artifact, Artifact?> = ReadArtifactServiceExecution(daoConfiguration.artifactDao())

    @Bean
    open fun deleteArtifactServiceExecution(): ServiceExecution<Artifact, Unit> = DeleteArtifactServiceExecution(daoConfiguration.artifactDao())

    @Bean
    open fun listArtifactServiceExecution(): ServiceExecution<Nothing?, List<Artifact>> = ListArtifactServiceExecution(daoConfiguration.artifactDao())

    @Bean
    open fun createReleaseServiceExecution(): ServiceExecution<Release, Release?> = CreateReleaseServiceExecution(daoConfiguration.releaseDao())

    @Bean
    open fun readReleaseServiceExecution(): ServiceExecution<String?, Release?> = ReadReleaseServiceExecution(daoConfiguration.releaseDao())

    @Bean
    open fun updateReleaseServiceExecution(): ServiceExecution<Release, Release?> = UpdateReleaseServiceExecution(daoConfiguration.releaseDao())

    @Bean
    open fun deleteReleaseServiceExecution(): ServiceExecution<String?, Unit> = DeleteReleaseServiceExecution(daoConfiguration.releaseDao())

    @Bean
    open fun listReleaseServiceExecution(): ServiceExecution<Nothing?, List<Release>> = ListReleaseServiceExecution(daoConfiguration.releaseDao())

    @Bean
    open fun createEnvironmentServiceExecution(): ServiceExecution<Environment, Environment?> = CreateEnvironmentServiceExecution(daoConfiguration.environmentDao())

    @Bean
    open fun readEnvironmentServiceExecution(): ServiceExecution<String?, Environment?> = ReadEnvironmentServiceExecution(daoConfiguration.environmentDao())

    @Bean
    open fun updateEnvironmentServiceExecution(): ServiceExecution<Environment, Environment?> = UpdateEnvironmentServiceExecution(daoConfiguration.environmentDao())

    @Bean
    open fun deleteEnvironmentServiceExecution(): ServiceExecution<String?, Unit> = DeleteEnvironmentServiceExecution(daoConfiguration.environmentDao())

    @Bean
    open fun listEnvironmentServiceExecution(): ServiceExecution<Nothing?, List<Environment>> = ListEnvironmentServiceExecution(daoConfiguration.environmentDao())
}
