package com.github.joelws.release.tracker.configuration

import com.github.joelws.release.tracker.service.artifact.execution.CreateArtifactServiceExecution
import com.github.joelws.release.tracker.service.artifact.execution.DeleteArtifactServiceExecution
import com.github.joelws.release.tracker.service.artifact.execution.ListArtifactServiceExecution
import com.github.joelws.release.tracker.service.artifact.execution.ReadArtifactServiceExecution
import com.github.joelws.release.tracker.service.release.execution.*
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
    open fun createArtifactServiceExecution() = CreateArtifactServiceExecution(daoConfiguration.artifactDao())

    @Bean
    open fun readArtifactServiceExecution() = ReadArtifactServiceExecution(daoConfiguration.artifactDao())

    @Bean
    open fun deleteArtifactServiceExecution() = DeleteArtifactServiceExecution(daoConfiguration.artifactDao())

    @Bean
    open fun listArtifactServiceExecution() = ListArtifactServiceExecution(daoConfiguration.artifactDao())

    @Bean
    open fun createReleaseServiceExecution() = CreateReleaseServiceExecution(daoConfiguration.releaseDao())

    @Bean
    open fun readReleaseServiceExecution() = ReadReleaseServiceExecution(daoConfiguration.releaseDao())

    @Bean
    open fun updateReleaseServiceExecution() = UpdateReleaseServiceExecution(daoConfiguration.releaseDao())

    @Bean
    open fun deleteReleaseServiceExecution() = DeleteReleaseServiceExecution(daoConfiguration.releaseDao())

    @Bean
    open fun listReleaseServiceExecution() = ListReleaseServiceExecution(daoConfiguration.releaseDao())


}