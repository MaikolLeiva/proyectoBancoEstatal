/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.security;

import com.wings.designs.ProyectoFraude.jwt.JwtConfig;
import com.wings.designs.ProyectoFraude.jwt.JwtTokenVerfier;
import com.wings.designs.ProyectoFraude.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.wings.designs.ProyectoFraude.user.privilege.Privilege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import javax.crypto.SecretKey;


@Configuration
@EnableWebSecurity

public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;


    @Autowired
    public ApplicationSecurityConfig(SecretKey secretKey, JwtConfig jwtConfig) {
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerfier(secretKey, jwtConfig), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/user")
                .hasAuthority(Privilege.EnumPrivilege.READ_USER.toString())
                .anyRequest()
                .authenticated() ;

    }

}
