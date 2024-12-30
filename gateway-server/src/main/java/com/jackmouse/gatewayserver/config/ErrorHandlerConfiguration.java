package com.jackmouse.gatewayserver.config;

import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;

//@Configuration
public class ErrorHandlerConfiguration {
    
    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes();
    }
    
    @Bean
    public ServerCodecConfigurer serverCodecConfigurer() {
        return ServerCodecConfigurer.create();
    }
} 