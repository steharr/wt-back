package com.workout.security.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@PropertySources({
        @PropertySource("classpath:security.properties"),
})
public class SecurityConfig {

    @Value("${url.whitelist}")
    private String[] URL_WHITELIST;

    @Autowired
    AuthProvider authenticationProvider;
    @Autowired
    JwtTokenAuthorizationFilter jwtTokenAuthorizationFilter;
    @Autowired
    Environment env;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtTokenAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider)
                .authorizeHttpRequests((authz) -> {
                            if (devEnvironment()) {
                                authz.requestMatchers(toH2Console()).permitAll();
                            }
                            authz.requestMatchers(URL_WHITELIST).permitAll();
                        }
                )
                .authorizeHttpRequests().anyRequest().authenticated().and()
                .cors(withDefaults())
                .csrf().disable()
                .httpBasic(withDefaults());

        if (devEnvironment()) {
            http.headers().frameOptions().disable();
        }
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues()); // Apply the configuration to all paths
        configuration.setAllowedMethods(List.of("GET", "POST", "PATCH", "DELETE", "PUT"));
        return source;
    }

    private boolean devEnvironment() {
        return Arrays.stream(env.getActiveProfiles()).anyMatch(
                e -> (e.equalsIgnoreCase("dev")));
    }
}
