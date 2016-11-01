package com.joelws.release.tracker.boot

import com.joelws.release.tracker.configuration.AppConfig
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.orm.jpa.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.transaction.annotation.EnableTransactionManagement
import ratpack.spring.config.EnableRatpack

@Configuration
@EntityScan(basePackages = arrayOf("com.joelws.release.tracker.entity"))
@Import(AppConfig::class)
@EnableAutoConfiguration(exclude = arrayOf(AppConfig::class))
@EnableTransactionManagement
@EnableRatpack
open class Application {


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(Application::class.java, *args)
        }

    }

}


