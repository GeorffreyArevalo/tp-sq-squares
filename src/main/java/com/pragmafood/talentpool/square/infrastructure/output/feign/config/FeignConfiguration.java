package com.pragmafood.talentpool.square.infrastructure.output.feign.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class FeignConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            
            @Override
            public void apply(RequestTemplate template) {
                String token = getToken();

                if (token != null) {
                    template.header("Authorization", "Bearer " + token);
                }
            }

            private String getToken() {
                var authentication = SecurityContextHolder.getContext().getAuthentication();

                if (authentication instanceof JwtAuthenticationToken jwtAuth) {
                    return jwtAuth.getToken().getTokenValue();
                }

                return null;
            }
        };
    }
}
