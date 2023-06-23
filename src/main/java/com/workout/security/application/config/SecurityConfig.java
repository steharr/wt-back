package com.workout.security.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@PropertySources({
        @PropertySource("classpath:security.properties"),
})
public class SecurityConfig {

    @Value("${url.whitelist}")
    private String[] URL_WHITELIST;

    @Value("${post.whitelist}")
    private String[] POST_WHITELIST;

    @Autowired
    AuthenticationProvider authenticationProvider;
    @Autowired
    JwtTokenFilter jwtTokenFilter;
    @Autowired
    Environment env;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider)
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(URL_WHITELIST).permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .cors().and()
                .csrf().disable();

        if (devEnvironment()) {
            http
                    .authorizeHttpRequests((authz) -> authz
                            .requestMatchers(toH2Console()).permitAll()
                    )
                    .headers().frameOptions().disable();
        }
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/localhost:8080/account/register", "/home");
    }

    private boolean devEnvironment() {
        return Arrays.stream(env.getActiveProfiles()).anyMatch(
                e -> (e.equalsIgnoreCase("dev")));
    }
}
