package com.jackmouse.gatewayserver.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ClassName DefaultController
 * @Description
 * @Author zhoujiaangyao
 * @Date 2024/12/17 09:09
 * @Version 1.0
 **/
@RestController
public class DefaultController {
    @Value("${app.base-uri}")
    private String appBaseUri;

    @GetMapping("/")
    public Mono<Void> root(ServerWebExchange exchange) {
        // 创建一个新的可修改的 HttpHeaders 对象
        HttpHeaders headers = new HttpHeaders();
        headers.putAll(exchange.getResponse().getHeaders());
        headers.add("Location", this.appBaseUri);

        // 设置响应头和状态码
        exchange.getResponse().getHeaders().putAll(headers);
        exchange.getResponse().setStatusCode(HttpStatus.FOUND);

        // 完成响应
        return exchange.getResponse().setComplete();
    }

    @GetMapping("/authorized")
    public String authorized() {
        return "redirect:" + this.appBaseUri;
    }
}
