package com.jwt.authentication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class OktaAuthenticationSecurity{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests(configure -> configure
                        .requestMatchers("/saveBook/{userId}", "/saveUser")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .oauth2ResourceServer(configure -> configure
                        .jwt(jwtConfigurer -> jwtConfigurer
                                .jwkSetUri("https://dev-69560756.okta.com/oauth2/default/v1/keys")
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                        )
                ).csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        return new JwtAuthenticationConverter();
    }
}