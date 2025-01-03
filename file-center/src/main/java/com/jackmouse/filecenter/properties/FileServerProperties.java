package com.jackmouse.filecenter.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = FileServerProperties.PREFIX)
public class FileServerProperties {
    public static final String PREFIX = "jackmouse.file-server";
    public static final String TYPE_FDFS = "fastdfs";
    public static final String TYPE_S3 = "s3";
    public static final String TYPE_ALIYUN = "aliyun";

    /**
     * 为以下2个值，指定不同的自动化配置
     * s3：aws s3协议的存储（七牛oss、阿里云oss、minio等）
     * fastdfs：本地部署的fastDFS
     */
    private String type;


    /**
     * fastDFS配置
     */
    //FdfsProperties fdfs = new FdfsProperties();
    /**
     * 阿里云配置
     */
    AliyunProperties aliyun = new AliyunProperties();
}
