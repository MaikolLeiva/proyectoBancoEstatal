/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.security;

import com.wings.designs.ProyectoFraude.jwt.JwtConfig;
import com.wings.designs.ProyectoFraude.jwt.JwtTokenVerfier;
import com.wings.designs.ProyectoFraude.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.SecretKey;
import java.util.Arrays;


@Configuration
@EnableWebSecurity

public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * The secret key used on the JWT token.
     */
    private final SecretKey secretKey;

    /**
     * Configuration class with information about
     * the JWT token.
     */
    private final JwtConfig jwtConfig;

    /**
     * Main Constructor.
     * @param secretKey secret key used on the JWT token.
     * @param jwtConfig class with information about
     *                  the JWT token.
     */
    @Autowired
    public ApplicationSecurityConfig(final SecretKey secretKey,
                                     final JwtConfig jwtConfig) {
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    /**
     * Set the security configuration for the HTTP
     * security.
     * @param http the HttpSecurity to be modified.
     * @throws Exception
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(
                        authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerfier(secretKey, jwtConfig),
                        JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/register/**").permitAll()
                .antMatchers(HttpMethod.GET, "/tickets/**")
                .hasRole("MANAGER")
                .antMatchers(HttpMethod.PATCH, "/tickets/**")
                .hasRole("MANAGER")
                .antMatchers(HttpMethod.POST, "/tickets")
                .hasRole("CLIENT")
                .anyRequest()
                .authenticated();

    }

    /**
     * Change the Cors configuration with the
     * one set on this method.
     * @return Class with the new Configuration for
     * Cors policy.
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        source.registerCorsConfiguration(
                "/**", config.applyPermitDefaultValues());
        //allow Authorization to be exposed
        config.setExposedHeaders(Arrays.asList("Authorization"));
        return source;
    }

}
