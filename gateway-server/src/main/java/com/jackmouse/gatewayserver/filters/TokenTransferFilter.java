package com.jackmouse.gatewayserver.filters;

import com.jackmouse.common.constants.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @ClassName TokenTransferFilter
 * @Description
 * @Author zhoujiaangyao
 * @Date 2024/12/24 16:33
 * @Version 1.0
 **/
@Slf4j
public class TokenTransferFilter implements WebFilter {
    @Override
    public @NonNull Mono<Void> filter(@NonNull ServerWebExchange exchange, @NonNull WebFilterChain chain) {
        log.debug("TokenTransferFilter executing...");
        
        // 获取请求路径
        String path = exchange.getRequest().getURI().getPath();
        
        // 如果是公开接口，直接放行
        if (isPublicPath(path)) {
            return chain.filter(exchange);
        }

        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .cast(JwtAuthenticationToken.class)
                .flatMap(authentication -> {
                    log.info("Extracting authentication token: {}", authentication.getToken());

                    Object userIdObject = authentication.getToken().getClaims().get(SecurityConstants.USER_ID_HEADER);
                    if (userIdObject != null) {
                        String userId = userIdObject.toString();
                        log.info("User ID extracted: {}", userId);

                        ServerHttpRequest request = exchange.getRequest().mutate()
                                .headers(h -> h.add(SecurityConstants.USER_ID_HEADER, userId))
                                .build();

                        return chain.filter(exchange.mutate().request(request).build());
                    }
                    return chain.filter(exchange);
                })
                .switchIfEmpty(chain.filter(exchange))  // 如果没有认证信息，继续处理请求
                .onErrorResume(ex -> {
                    log.error("Error processing authentication token: ", ex);
                    return chain.filter(exchange);
                });
    }

    private boolean isPublicPath(String path) {
        return path.startsWith("/public/") || 
               path.startsWith("/api/v1/public/") ||
               path.startsWith("/api/v1/auth/");
    }
}
