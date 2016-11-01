package com.joelws.release.tracker.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.joelws.release.tracker.aspect.LoggingAspect
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.*
import ratpack.server.BaseDir
import ratpack.server.ServerConfig
import java.net.InetAddress

@Configuration
@Import(EndpointConfig::class)
@PropertySource("/application.properties")
@EnableAspectJAutoProxy
open class AppConfig {


    @Autowired
    lateinit var endpointConfig: EndpointConfig

    @Bean
    open fun objectMapper() = ObjectMapper().registerKotlinModule()

    @Bean
    open fun loggingAspect() = LoggingAspect()

    @Value("\${rest.service.port}")
    var serverPort: Int? = null

    @Value("\${dev.mode}")
    var devMode: Boolean = true

    @Bean
    open fun serverConfig(): ServerConfig {
        return ServerConfig.builder().development(devMode)
                .address(if (devMode) InetAddress.getByName("127.0.0.1") else null)
                .baseDir(BaseDir.find())
                .port(serverPort!!).build()
    }

}
