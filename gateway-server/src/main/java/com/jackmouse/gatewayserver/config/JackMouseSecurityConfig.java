package com.jackmouse.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

///**
// * @ClassName JackMouseSecurityConfig
// * @Description
// * @Author zhoujiaangyao
// * @Date 2024/12/11 15:20
// * @Version 1.0
// **/
@Configuration
public class JackMouseSecurityConfig {

//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        http
//                .authorizeExchange(authorize -> authorize
//                        .anyExchange().authenticated()
//                )
//                .exceptionHandling(exceptionHandlingSpec -> exceptionHandlingSpec
//                        .authenticationEntryPoint(authenticationEntryPoint()))
//                .oauth2Login(oAuth2LoginSpec -> oAuth2LoginSpec
//                       .authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler(this.appBaseUri)))
//                .logout(logout ->
//                        logout
//                                .logoutSuccessHandler(new HttpStatusReturningServerLogoutSuccessHandler(HttpStatus.OK))
//                )
//                .csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .oauth2Client(Customizer.withDefaults());
//
//        return http.build();
//    }
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        http
                .authorizeExchange(authorize -> authorize
                        .anyExchange().authenticated()
                )
        .oauth2ResourceServer((resourceServer) -> resourceServer
                .jwt(Customizer.withDefaults()));
        return http.build();
    }


}
