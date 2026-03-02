package com.artefatox.spring_jpa_read_replica.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DatabaseConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.replica")
    public DataSource replicaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSource routingDataSource() {
        RountingDataSource routing = new RountingDataSource();

        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DataSourceType.MASTER, masterDataSource());
        targetDataSource.put(DataSourceType.REPLICA, replicaDataSource());

        routing.setTargetDataSources(targetDataSource);
        routing.setDefaultTargetDataSource(masterDataSource());

        return routing;
    }

    @Primary
    @Bean
    public DataSource dataSource() {
        return new LazyConnectionDataSourceProxy(routingDataSource());
    }

}
