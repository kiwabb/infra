package com.jackmouse.filecenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.jackmouse.filecenter.mapper")
public class FileCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileCenterApplication.class, args);
    }

}
