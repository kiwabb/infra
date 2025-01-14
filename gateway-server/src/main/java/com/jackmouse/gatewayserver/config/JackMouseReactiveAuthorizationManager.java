package com.jackmouse.gatewayserver.config;

import com.jackmouse.gatewayserver.constants.ApiPathConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @ClassName JackmouseReactiveAuthorizationManager
 * @Description
 * @Author zhoujiaangyao
 * @Date 2024/12/24 15:00
 * @Version 1.0
 **/
@Component
@Slf4j
public class JackMouseReactiveAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext context) {
        String path = context.getExchange().getRequest().getURI().getPath();
        if (path.startsWith(ApiPathConstants.SERVER_APT)) {
            return authentication
                    .map(auth -> auth != null && auth.isAuthenticated())
                    .map(AuthorizationDecision::new);
        }

        // 管理员API需要ADMIN角色
        if (path.startsWith(ApiPathConstants.ADMIN_API)) {
            return authentication
                    .map(auth -> auth.getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")))
                    .map(AuthorizationDecision::new);
        }

        // 用户API只需要认证
        if (path.startsWith(ApiPathConstants.USER_API)) {
            return authentication
                    .map(auth -> auth != null && auth.isAuthenticated())
                    .map(AuthorizationDecision::new);
        }

        // 默认拒绝访问
        return Mono.just(new AuthorizationDecision(false));
    }
}
