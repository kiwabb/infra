package com.jackmouse.filecenter.config;


import com.jackmouse.filecenter.properties.AliyunProperties;
import com.jackmouse.filecenter.properties.FileServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({FileServerProperties.class, AliyunProperties.class})
public class OssAutoConfigure {
}
