package com.workout.config.infrastructure.db.prod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Profile("PROD")
@Configuration
public class DataSourceConfig {
    @Autowired
    private Environment environment;

    @Bean
    @Qualifier("postgresDataSource")
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(environment.getProperty("db.driver"));
        dataSourceBuilder.url(environment.getProperty("db.url"));
        dataSourceBuilder.username(environment.getProperty("db.user"));
        dataSourceBuilder.password(environment.getProperty("db.pw"));
        return dataSourceBuilder.build();
    }
}