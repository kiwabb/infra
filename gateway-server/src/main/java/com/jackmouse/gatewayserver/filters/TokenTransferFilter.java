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
    public @NonNull Mono<Void> filter(@NonNull ServerWebExchange exchange,@NonNull WebFilterChain chain) {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .cast(JwtAuthenticationToken.class)
                .flatMap(authentication -> {
                    log.info("authentication: {}", authentication.getToken());

                    // 获取用户ID并进行非空检查
                    Object userIdObject = authentication.getToken().getClaims().get(SecurityConstants.USER_ID_HEADER);
                    if (userIdObject != null) {
                        String userId = userIdObject.toString();

                        // 修改请求头，加入获取的 userId
                        ServerHttpRequest request = exchange.getRequest().mutate()
                                .headers(h -> h.add(SecurityConstants.USER_ID_HEADER, userId))
                                .build();

                        // 使用 mutate() 生成一个新的 ServerWebExchange
                        ServerWebExchange newExchange = exchange.mutate().request(request).build();

                        // 继续处理过滤器链
                        return chain.filter(newExchange);
                    }

                    // 如果没有 userId，直接继续过滤链
                    return chain.filter(exchange);
                });
    }
}
