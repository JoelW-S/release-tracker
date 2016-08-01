package com.joelws.release.tracker.boot

import com.joelws.release.tracker.configuration.AppConfig
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.orm.jpa.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.ImportResource
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EntityScan(basePackages = arrayOf("com.joelws.release.tracker.entity"))
@Import(AppConfig::class)
@EnableAutoConfiguration(exclude = arrayOf(AppConfig::class))
@EnableTransactionManagement
@ImportResource("classpath:META-INF/cxf/cxf.xml", "classpath:META-INF/cxf/cxf-servlet.xml")
open class Application {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }

    }

}


