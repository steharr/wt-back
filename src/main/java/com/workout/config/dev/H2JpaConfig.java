package com.workout.config.dev;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Objects;

@Profile("DEV")
@Configuration
@EnableJpaRepositories(
        basePackages = {
                "com.workout.security.infrastructure.repository",
                "com.workout.session.infrastructure.repository",
        },
        entityManagerFactoryRef = "h2EntityManagerFactory",
        transactionManagerRef = "h2DatabaseTransactionManager"

)
public class H2JpaConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory(@Qualifier("h2DataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages(
                        "com.workout.security.infrastructure.repository",
                        "com.workout.session.infrastructure.repository"
                )
                .build();
    }

    @Bean
    public PlatformTransactionManager h2DatabaseTransactionManager(
            @Qualifier("h2EntityManagerFactory") LocalContainerEntityManagerFactoryBean entF) {
        return new JpaTransactionManager(Objects.requireNonNull(entF.getObject()));
    }
}