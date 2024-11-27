package com.jackmouse.filecenter.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @ClassName AliyunProperies
 * @Description aliyun文件上传配置
 * @Author zhoujiaangyao
 * @Date 2024/10/16 13:14
 * @Version 1.0
 **/
@Getter
@Setter
@ConfigurationProperties(prefix = AliyunProperties.PREFIX)
public class AliyunProperties {
    public static final String PREFIX = "jackmouse.file-server.aliyun";
    private String accessKeyId;
    private String accessKeySecret;
    private String endpoint;
    private String region;
    private String bucketName;
}
