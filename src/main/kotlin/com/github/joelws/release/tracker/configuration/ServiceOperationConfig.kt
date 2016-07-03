package com.github.joelws.release.tracker.configuration

import com.github.joelws.release.tracker.factory.ReleaseTrackerFactory
import com.github.joelws.release.tracker.service.ServiceHelper
import com.github.joelws.release.tracker.service.artifact.operation.*
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
    open fun createArtifactServiceOperation() = CreateArtifactServiceOperation(serviceHelper(), executionConfig.createArtifactServiceExecution())

    @Bean
    open fun readArtifactServiceOperation() = ReadArtifactServiceOperation(serviceHelper(), executionConfig.readArtifactServiceExecution())

    @Bean
    open fun updateArtifactServiceOperation() = UpdateArtifactServiceOperation()

    @Bean
    open fun deleteArtifactServiceOperation() = DeleteArtifactServiceOperation(serviceHelper(), executionConfig.deleteArtifactServiceExecution())

    @Bean
    open fun listArtifactServiceOperation() = ListArtifactServiceOperation(serviceHelper(), executionConfig.listArtifactServiceExecution())

    @Bean
    open fun createReleaseServiceOperation() = CreateReleaseServiceOperation(serviceHelper(), executionConfig.createReleaseServiceExecution())

    @Bean
    open fun readReleaseServiceOperation() = ReadReleaseServiceOperation(serviceHelper(), executionConfig.readReleaseServiceExecution())

    @Bean
    open fun updateReleaseServiceOperation() = UpdateReleaseServiceOperation(serviceHelper(), executionConfig.updateReleaseServiceExecution())

    @Bean
    open fun deleteReleaseServiceOperation() = DeleteReleaseServiceOperation(serviceHelper(), executionConfig.deleteReleaseServiceExecution())

    @Bean
    open fun listReleaseServiceOperation() = ListReleaseServiceOperation(serviceHelper(), executionConfig.listReleaseServiceExecution())

}