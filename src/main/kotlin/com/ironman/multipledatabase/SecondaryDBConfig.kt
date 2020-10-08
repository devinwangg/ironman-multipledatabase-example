package com.ironman.multipledatabase

/**
 *
 * @author wei-xiang
 * @version 1.0
 * @date 2020/10/8
 */
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource


@Configuration
@EnableJpaRepositories(
        basePackages = ["com.ironman.multipledatabase.repository.mssql"],
        entityManagerFactoryRef = "secondaryDBEntityManager",
        transactionManagerRef = "secondaryDBTransactionManager"
)
class SecondaryDBConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    fun secondaryDBProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    @Autowired
    fun secondaryDBDataSource(
            @Qualifier("secondaryDBProperties") properties: DataSourceProperties
    ): DataSource {
        return properties.initializeDataSourceBuilder().build()
    }

    @Bean
    @Autowired
    fun secondaryDBEntityManager(
            builder: EntityManagerFactoryBuilder,
            @Qualifier("secondaryDBDataSource") dataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean {
        return builder.dataSource(dataSource)
                .packages("com.ironman.multipledatabase.entity.mssql")
                .properties(mapOf("hibernate.hbm2ddl.auto" to "update"))
                .persistenceUnit("secondary")
                .build()
    }

    @Bean
    @Autowired
    fun secondaryDBTransactionManager(
            @Qualifier("secondaryDBEntityManager") primaryDBEntityManager: EntityManagerFactory
    ): JpaTransactionManager {
        return JpaTransactionManager(primaryDBEntityManager)
    }
}