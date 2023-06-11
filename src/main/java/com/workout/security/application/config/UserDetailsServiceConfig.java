package com.workout.security.application.config;

import com.workout.security.application.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class UserDetailsServiceConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsService = new AccountService();
        return userDetailsService;
    }

}
