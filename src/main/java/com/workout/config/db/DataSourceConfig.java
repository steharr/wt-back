//package com.workout.config.db;
//
//
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSourceConfig {
//
//    @Bean
//    public DataSource getDataSource() {
//        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName("org.postgresql.Driver");
//        dataSourceBuilder.url("jdbc:postgresql://@mel.db.elephantsql.com:5432/bglpmagf");
//        dataSourceBuilder.username("bglpmagf");
//        dataSourceBuilder.password("9beKbOeyotUHZZQ_O4-9ELEfzMAvZNF0");
//        return dataSourceBuilder.build();
//    }
//}
