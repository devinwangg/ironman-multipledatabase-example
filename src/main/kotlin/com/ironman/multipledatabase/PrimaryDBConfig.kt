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
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
        basePackages = ["com.ironman.multipledatabase.repository.mysql"],
        entityManagerFactoryRef = "primaryDBEntityManager",
        transactionManagerRef = "primaryDBTransactionManager"
)
class PrimaryDBConfig {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    fun primaryDBProperties(): DataSourceProperties {
        return DataSourceProperties()
    }

    @Bean
    @Primary
    @Autowired
    fun primaryDBDataSource(
            @Qualifier("primaryDBProperties") properties: DataSourceProperties
    ): DataSource {
        return properties.initializeDataSourceBuilder().build()
    }

    @Bean
    @Primary
    @Autowired
    fun primaryDBEntityManager(
            builder: EntityManagerFactoryBuilder,
            @Qualifier("primaryDBDataSource") dataSource: DataSource
    ): LocalContainerEntityManagerFactoryBean {
        return builder.dataSource(dataSource)
                .packages("com.ironman.multipledatabase.entity.mysql")
                .properties(mapOf("hibernate.hbm2ddl.auto" to "update"))
                .persistenceUnit("primary")
                .build()
    }

    @Bean
    @Primary
    @Autowired
    fun primaryDBTransactionManager(
            @Qualifier("primaryDBEntityManager") primaryDBEntityManager: EntityManagerFactory
    ): JpaTransactionManager {
        return JpaTransactionManager(primaryDBEntityManager)
    }
}