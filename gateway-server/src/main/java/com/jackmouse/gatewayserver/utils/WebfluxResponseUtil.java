package com.jackmouse.gatewayserver.utils;

import com.jackmouse.common.exception.ErrorCode;
import com.jackmouse.common.model.Result;
import com.nimbusds.jose.util.JSONStringUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

/**
 * @ClassName WebfluxResponseUtil
 * @Description
 * @Author zhoujiaangyao
 * @Date 2024/12/30 09:33
 * @Version 1.0
 **/
public class WebfluxResponseUtil {
    public static Mono<Void> responseFailed(ServerWebExchange exchange, String msg) {
        Result<Object> result = Result.failed(new ErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg));
        return responseWrite(exchange, HttpStatus.INTERNAL_SERVER_ERROR.value(), result);
    }
    public static Mono<Void> responseFailed(ServerWebExchange exchange, int httpStatus, String msg) {
        Result<Object> result = Result.failed(new ErrorCode(httpStatus, msg));
        return responseWrite(exchange, httpStatus, result);
    }

    public static Mono<Void> responseWrite(ServerWebExchange exchange, int httpStatus, Result<Object> result) {
        if (httpStatus == 0) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setAccessControlAllowCredentials(true);
        response.getHeaders().setAccessControlAllowOrigin("*");
        response.setStatusCode(HttpStatus.valueOf(httpStatus));
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        DataBufferFactory dataBufferFactory = response.bufferFactory();
        DataBuffer buffer = dataBufferFactory.wrap(JSONStringUtils.toJSONString(result.toString())
                .getBytes(Charset.defaultCharset()));
        return response.writeWith(Mono.just(buffer)).doOnError((error) -> {
            DataBufferUtils.release(buffer);
        });
    }
}
