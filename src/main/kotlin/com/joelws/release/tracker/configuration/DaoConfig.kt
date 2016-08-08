package com.joelws.release.tracker.configuration

import com.joelws.release.tracker.dao.artifact.ArtifactDao
import com.joelws.release.tracker.dao.artifact.ArtifactDaoImpl
import com.joelws.release.tracker.dao.environment.EnvironmentDao
import com.joelws.release.tracker.dao.environment.EnvironmentDaoImpl
import com.joelws.release.tracker.dao.release.ReleaseDao
import com.joelws.release.tracker.dao.release.ReleaseDaoImpl
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
