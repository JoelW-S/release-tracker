package com.github.joelws.release.tracker.configuration

import com.github.joelws.release.tracker.dao.artifact.ArtifactDao
import com.github.joelws.release.tracker.dao.artifact.ArtifactDaoImpl
import com.github.joelws.release.tracker.dao.environment.EnvironmentDao
import com.github.joelws.release.tracker.dao.environment.EnvironmentDaoImpl
import com.github.joelws.release.tracker.dao.release.ReleaseDao
import com.github.joelws.release.tracker.dao.release.ReleaseDaoImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class DaoConfig {

    @Bean
    open fun artifactDao(): ArtifactDao = ArtifactDaoImpl()

    @Bean
    open fun releaseDao(): ReleaseDao = ReleaseDaoImpl()

    @Bean
    open fun environmentDao(): EnvironmentDao = EnvironmentDaoImpl()

}
