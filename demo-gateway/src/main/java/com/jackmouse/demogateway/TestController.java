package com.jackmouse.demogateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description
 * @Author zhoujiaangyao
 * @Date 2025/1/13 19:36
 * @Version 1.0
 **/
@RestController
public class TestController {
    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
