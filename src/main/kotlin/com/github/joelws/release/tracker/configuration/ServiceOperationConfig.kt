package com.github.joelws.release.tracker.configuration

import com.github.joelws.release.tracker.factory.ReleaseTrackerFactory
import com.github.joelws.release.tracker.service.ServiceHelper
import com.github.joelws.release.tracker.service.ServiceOperation
import com.github.joelws.release.tracker.service.artifact.operation.*
import com.github.joelws.release.tracker.service.environment.operation.*
import com.github.joelws.release.tracker.service.release.operation.*
import com.github.joelws.release.tracker.util.JsonAdapterImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(ExecutionConfig::class)
open class ServiceOperationConfig {

    @Autowired
    lateinit var executionConfig: ExecutionConfig

    @Bean
    open fun jsonAdapter() = JsonAdapterImpl()

    @Bean
    open fun releaseTrackerFactory() = ReleaseTrackerFactory()

    @Bean
    open fun serviceHelper() = ServiceHelper(jsonAdapter(), releaseTrackerFactory())

    @Bean
    open fun createArtifactServiceOperation(): ServiceOperation<String> = CreateArtifactServiceOperation(serviceHelper(), executionConfig.createArtifactServiceExecution())

    @Bean
    open fun readArtifactServiceOperation(): ServiceOperation<String> = ReadArtifactServiceOperation(serviceHelper(), executionConfig.readArtifactServiceExecution())

    @Bean
    open fun updateArtifactServiceOperation(): ServiceOperation<String> = UpdateArtifactServiceOperation()

    @Bean
    open fun deleteArtifactServiceOperation(): ServiceOperation<String> = DeleteArtifactServiceOperation(serviceHelper(), executionConfig.deleteArtifactServiceExecution())

    @Bean
    open fun listArtifactServiceOperation(): ServiceOperation<Nothing> = ListArtifactServiceOperation(serviceHelper(), executionConfig.listArtifactServiceExecution())

    @Bean
    open fun createReleaseServiceOperation(): ServiceOperation<String> = CreateReleaseServiceOperation(serviceHelper(), executionConfig.createReleaseServiceExecution())

    @Bean
    open fun readReleaseServiceOperation(): ServiceOperation<String> = ReadReleaseServiceOperation(serviceHelper(), executionConfig.readReleaseServiceExecution())

    @Bean
    open fun updateReleaseServiceOperation(): ServiceOperation<String> = UpdateReleaseServiceOperation(serviceHelper(), executionConfig.updateReleaseServiceExecution())

    @Bean
    open fun deleteReleaseServiceOperation(): ServiceOperation<String> = DeleteReleaseServiceOperation(executionConfig.deleteReleaseServiceExecution())

    @Bean
    open fun listReleaseServiceOperation(): ServiceOperation<Nothing?> = ListReleaseServiceOperation(serviceHelper(), executionConfig.listReleaseServiceExecution())

    @Bean
    open fun createEnvironmentServiceOperation(): ServiceOperation<String> = CreateEnvironmentServiceOperation(serviceHelper(), executionConfig.createEnvironmentServiceExecution())

    @Bean
    open fun readEnvironmentServiceOperation(): ServiceOperation<String> = ReadEnvironmentServiceOperation(serviceHelper(), executionConfig.readEnvironmentServiceExecution())

    @Bean
    open fun updateEnvironmentServiceOperation(): ServiceOperation<String> = UpdateEnvironmentServiceOperation(serviceHelper(), executionConfig.updateEnvironmentServiceExecution())

    @Bean
    open fun deleteEnvironmentServiceOperation(): ServiceOperation<String> = DeleteEnvironmentServiceOperation(executionConfig.deleteEnvironmentServiceExecution())

    @Bean
    open fun listEnvironmentServiceOperation(): ServiceOperation<Nothing?> = ListEnvironmentServiceOperation(serviceHelper(), executionConfig.listEnvironmentServiceExecution())

}
