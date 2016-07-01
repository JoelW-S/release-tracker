package com.github.joelws.release.tracker.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.joelws.release.tracker.dao.artifact.ArtifactDaoImpl
import com.github.joelws.release.tracker.dao.release.ReleaseDaoImpl
import com.github.joelws.release.tracker.endpoint.artifact.CreateArtifactEndpoint
import com.github.joelws.release.tracker.endpoint.artifact.DeleteArtifactEndpoint
import com.github.joelws.release.tracker.endpoint.artifact.ReadArtifactEndpoint
import com.github.joelws.release.tracker.endpoint.artifact.UpdateArtifactEndpoint
import com.github.joelws.release.tracker.endpoint.release.*
import com.github.joelws.release.tracker.factory.ReleaseTrackerFactory
import com.github.joelws.release.tracker.handler.ReleaseTrackerExceptionHandler
import com.github.joelws.release.tracker.handler.RuntimeExceptionHandler
import com.github.joelws.release.tracker.interfaces.BusinessService
import com.github.joelws.release.tracker.service.ServiceHelper
import com.github.joelws.release.tracker.service.artifact.ArtifactService
import com.github.joelws.release.tracker.service.artifact.execution.CreateArtifactServiceExecution
import com.github.joelws.release.tracker.service.artifact.execution.DeleteArtifactServiceExecution
import com.github.joelws.release.tracker.service.artifact.execution.ListArtifactServiceExecution
import com.github.joelws.release.tracker.service.artifact.execution.ReadArtifactServiceExecution
import com.github.joelws.release.tracker.service.artifact.operation.*
import com.github.joelws.release.tracker.service.release.ReleaseService
import com.github.joelws.release.tracker.service.release.execution.*
import com.github.joelws.release.tracker.service.release.operation.*
import com.github.joelws.release.tracker.util.JsonAdapterImpl
import org.apache.cxf.endpoint.Server
import org.apache.cxf.feature.LoggingFeature
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class AppConfig {

    @Value("\${rest.service.url}")
    lateinit var serverAddress: String

    @Bean
    open fun releaseTrackerServer(): Server {
        val factory = JAXRSServerFactoryBean()
        factory.address = serverAddress
        factory.providers = listOf(jsonProvider(), RuntimeExceptionHandler(), ReleaseTrackerExceptionHandler())
        factory.setServiceBeans(listOf(createArtifactEndpoint(),
                readArtifactEndpoint(),
                updateArtifactEndpoint(),
                deleteArtifactEndpoint(),
                createReleaseEndpoint(),
                readReleaseEndpoint(),
                updateReleaseEndpoint(),
                deleteReleaseEndpoint(),
                listReleaseEndpoint()))
        factory.features = listOf(LoggingFeature())
        return factory.create()
    }

    @Bean
    open fun jsonProvider() = JacksonJsonProvider(objectMapper())

    @Bean
    open fun objectMapper() = ObjectMapper().registerKotlinModule()

    @Bean
    open fun jsonAdapter() = JsonAdapterImpl()

    @Bean
    open fun releaseTrackerFactory() = ReleaseTrackerFactory()

    @Bean
    open fun serviceHelper() = ServiceHelper(jsonAdapter(), releaseTrackerFactory())

    @Bean
    open fun artifactDao() = ArtifactDaoImpl()

    @Bean
    open fun releaseDao() = ReleaseDaoImpl()

    @Bean
    open fun createArtifactServiceExecution() = CreateArtifactServiceExecution(artifactDao())

    @Bean
    open fun deleteArtifactServiceExecution() = DeleteArtifactServiceExecution(artifactDao())

    @Bean
    open fun listArtifactServiceExecution() = ListArtifactServiceExecution(artifactDao())

    @Bean
    open fun readArtifactServiceExecution() = ReadArtifactServiceExecution(artifactDao())

    @Bean
    open fun createReleaseServiceExecution() = CreateReleaseServiceExecution(releaseDao())

    @Bean
    open fun deleteReleaseServiceExecution() = DeleteReleaseServiceExecution(releaseDao())

    @Bean
    open fun listReleaseServiceExecution() = ListReleaseServiceExecution(releaseDao())

    @Bean
    open fun readReleaseServiceExecution() = ReadReleaseServiceExecution(releaseDao())

    @Bean
    open fun updateReleaseServiceExecution() = UpdateReleaseServiceExecution(releaseDao())

    @Bean
    open fun createArtifactServiceOperation() = CreateArtifactServiceOperation(serviceHelper(), createArtifactServiceExecution())

    @Bean
    open fun readArtifactServiceOperation() = ReadArtifactServiceOperation(serviceHelper(), readArtifactServiceExecution())

    @Bean
    open fun updateArtifactServiceOperation() = UpdateArtifactServiceOperation()

    @Bean
    open fun deleteArtifactServiceOperation() = DeleteArtifactServiceOperation(serviceHelper(), deleteArtifactServiceExecution())

    @Bean
    open fun listArtifactServiceOperation() = ListArtifactServiceOperation(serviceHelper(), listArtifactServiceExecution())

    @Bean
    open fun createReleaseServiceOperation() = CreateReleaseServiceOperation(serviceHelper(), createReleaseServiceExecution())

    @Bean
    open fun readReleaseServiceOperation() = ReadReleaseServiceOperation(serviceHelper(), readReleaseServiceExecution())

    @Bean
    open fun updateReleaseServiceOperation() = UpdateReleaseServiceOperation(serviceHelper(), updateReleaseServiceExecution())

    @Bean
    open fun deleteReleaseServiceOperation() = DeleteReleaseServiceOperation(serviceHelper(), deleteReleaseServiceExecution())

    @Bean
    open fun listReleaseServiceOperation() = ListReleaseServiceOperation(serviceHelper(), listReleaseServiceExecution())

    @Bean
    open fun artifactService(): BusinessService<String> = ArtifactService(createArtifactServiceOperation(), readArtifactServiceOperation(), updateArtifactServiceOperation(), deleteArtifactServiceOperation(), listArtifactServiceOperation())

    @Bean
    open fun releaseService(): BusinessService<String> = ReleaseService(createReleaseServiceOperation(), readReleaseServiceOperation(), updateReleaseServiceOperation(), deleteReleaseServiceOperation(), listReleaseServiceOperation())

    @Bean
    open fun createArtifactEndpoint() = CreateArtifactEndpoint(artifactService())

    @Bean
    open fun readArtifactEndpoint() = ReadArtifactEndpoint(artifactService())

    @Bean
    open fun updateArtifactEndpoint() = UpdateArtifactEndpoint(artifactService())

    @Bean
    open fun deleteArtifactEndpoint() = DeleteArtifactEndpoint(artifactService())

    @Bean
    open fun createReleaseEndpoint() = CreateReleaseEndpoint(releaseService())

    @Bean
    open fun readReleaseEndpoint() = ReadReleaseEndpoint(releaseService())

    @Bean
    open fun updateReleaseEndpoint() = UpdateReleaseEndpoint(releaseService())

    @Bean
    open fun deleteReleaseEndpoint() = DeleteReleaseEndpoint(releaseService())

    @Bean
    open fun listReleaseEndpoint() = ListReleaseEndpoint(releaseService())


}