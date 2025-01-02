package com.jackmouse.gatewayserver.config;

import com.jackmouse.gatewayserver.filters.TokenTransferFilter;
import com.jackmouse.gatewayserver.utils.WebfluxResponseUtil;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;

@Configuration
@EnableWebFluxSecurity
public class JackMouseSecurityConfig {
    @Resource
    private JackMouseReactiveAuthorizationManager reactiveAuthorizationManager;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(Customizer.withDefaults())
                .authorizeExchange(authorize -> authorize
                        .pathMatchers("/public/**", "/protected/**")
                        .permitAll()
                        .anyExchange()                                // 其他所有路径
                        .access(reactiveAuthorizationManager)         // 需要进行权限校验
                )
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(serverAuthenticationEntryPoint())
                        .accessDeniedHandler(serverAccessDeniedHandler())
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults())
                )
                .addFilterAfter(new TokenTransferFilter(), SecurityWebFiltersOrder.AUTHORIZATION)
                .build();
    }

    @Bean
    public ServerAuthenticationEntryPoint serverAuthenticationEntryPoint() {
        return (exchange, e) -> WebfluxResponseUtil.responseFailed(
            exchange,
            HttpStatus.UNAUTHORIZED.value(),
            "未认证: " + e.getMessage()
        );
    }

    @Bean
    public ServerAccessDeniedHandler serverAccessDeniedHandler() {
        return (exchange, e) -> WebfluxResponseUtil.responseFailed(
            exchange,
            HttpStatus.FORBIDDEN.value(),
            e.getMessage()
        );
    }
}
