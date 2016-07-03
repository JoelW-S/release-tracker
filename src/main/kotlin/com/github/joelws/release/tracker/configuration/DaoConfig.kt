package com.github.joelws.release.tracker.configuration

import com.github.joelws.release.tracker.dao.artifact.ArtifactDaoImpl
import com.github.joelws.release.tracker.dao.release.ReleaseDaoImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class DaoConfig {

    @Bean
    open fun artifactDao() = ArtifactDaoImpl()

    @Bean
    open fun releaseDao() = ReleaseDaoImpl()

}