package com.sror.map.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "addressSqlEntityManagerFactory",
        transactionManagerRef = "addressSqlTransactionManager",
        basePackages = {"com.sror.map.repository"}
)
public class AddressDataSourceConfig {

    @Primary
    @Bean(name = "addressSqlDataSource")
    @ConfigurationProperties( "spring.datasource")
    public DataSource addressSqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "addressSqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("addressSqlDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.sror.map.model")
                .persistenceUnit("sqlDb")
                .build();
    }

    @Primary
    @Bean(name = "addressSqlTransactionManager")
    public PlatformTransactionManager addressSqlTransactionManager(
            @Qualifier("addressSqlEntityManagerFactory") EntityManagerFactory addressSqlEntityManagerFactory
    ) {
        return new JpaTransactionManager(addressSqlEntityManagerFactory);
    }

}
