package com.workout.config.infrastructure.db.dev;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

@Profile("DEV")
@Configuration
@PropertySources({
        @PropertySource("classpath:dev.properties"),
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

    @Bean
    @Qualifier("h2DataSource")
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driver);
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(user);
        dataSourceBuilder.password(pw);
        return dataSourceBuilder.build();
    }
}


