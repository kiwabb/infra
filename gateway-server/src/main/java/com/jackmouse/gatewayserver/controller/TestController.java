package com.jackmouse.gatewayserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description
 * @Author zhoujiaangyao
 * @Date 2025/1/13 19:42
 * @Version 1.0
 **/
@RestController
public class TestController {
    @GetMapping("/public/test")
    public String test() {
        return "public test";
    }
}
