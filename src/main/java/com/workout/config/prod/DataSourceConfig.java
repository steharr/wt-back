package com.workout.config.prod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Profile("PROD")
@Configuration
@PropertySources({
        @PropertySource("classpath:prod.properties"),
})
public class DataSourceConfig {

    @Value("${db.driver}")
    private String driver;
    @Value("${db.url}")
    private String url;

    @Value("${db.user}")
    private String user;
    @Value("${db.pw}")
    private String pw;

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