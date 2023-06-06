package com.juancazz.gateway.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class OAuth2LoginSecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {

        return serverHttpSecurity.authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
                        .anyExchange().authenticated())
                .oauth2Login(Customizer.withDefaults()).build();
    }

}
