package com.joelws.release.tracker.configuration

import com.github.springtestdbunit.bean.DatabaseConfigBean
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean
import org.dbunit.ext.h2.H2DataTypeFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
open class DbUnitConfig {

    @Autowired
    lateinit var dataSource: DataSource

    @Bean
    open fun dbUnitDatabaseConnection(): DatabaseDataSourceConnectionFactoryBean {
        val bean = DatabaseConfigBean()
        bean.datatypeFactory = H2DataTypeFactory()

        val dbConnectionFactory = DatabaseDataSourceConnectionFactoryBean(dataSource)
        dbConnectionFactory.setDatabaseConfig(bean)
        return dbConnectionFactory
    }

}
