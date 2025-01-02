package com.jackmouse.filecenter.template;

import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.comm.SignVersion;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;


import com.jackmouse.filecenter.canstans.CommonConstant;
import com.jackmouse.filecenter.entity.FileInfo;
import com.jackmouse.filecenter.model.ObjectInfo;
import com.jackmouse.filecenter.properties.FileServerProperties;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @ClassName AliyunOssTemplate
 * @Description
 * @Author zhoujiaangyao
 * @Date 2024/10/16 11:30
 * @Version 1.0
 **/
@Component
public class AliyunOssTemplate implements OssTemplate {

    @Autowired
    private FileServerProperties fileServerProperties;

    private OSS ossClient;

    @Override
    public void afterPropertiesSet() {
        CredentialsProvider credentialsProvider = new DefaultCredentialProvider(
                fileServerProperties.getAliyun().getAccessKeyId(),
                fileServerProperties.getAliyun().getAccessKeySecret());
        ClientBuilderConfiguration clientBuilderConfiguration =
                new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        this.ossClient = OSSClientBuilder.create()
                .endpoint(fileServerProperties.getAliyun().getEndpoint())
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(fileServerProperties.getAliyun().getRegion())
                .build();
    }

    @Override
    @SneakyThrows
    public ObjectInfo upload(MultipartFile file) {
        LocalDate now = LocalDate.now();
        String[] split = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String fileName = IdUtil.fastSimpleUUID() + "." + split[split.length - 1];

        return upload(fileServerProperties.getAliyun().getBucketName(),
                now.getYear() + CommonConstant.PATH_SPLIT
                        + now.getMonthValue() + CommonConstant.PATH_SPLIT
                        + now.getDayOfMonth() + CommonConstant.PATH_SPLIT + fileName, fileName, file.getInputStream()
                , ((Long)file.getSize()).intValue(), file.getContentType());

    }

    @Override
    public InputStreamResource get(FileInfo fileInfo) {
        // 2. 从文件信息中获取OSS相关信息
        String bucketName = fileInfo.getBucketName();
        String objectName = fileInfo.getObjectName();

        // 3. 获取OSS对象
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);
        InputStream inputStream = ossObject.getObjectContent();
        return new InputStreamResource(inputStream);
    }

    @Override
    public void deleteFile(FileInfo fileInfo) {
        // 2. 从文件信息中获取OSS相关信息
        String bucketName = fileInfo.getBucketName();
        String objectName = fileInfo.getObjectName();
        ossClient.deleteObject(bucketName, objectName);
    }

    /**
     * 上传对象
     *
     * @param name
     * @param bucketName  bucket名称
     * @param objectName  对象名
     * @param is          对象流
     * @param size        大小
     * @param contentType 类型
     */
    private ObjectInfo upload(String bucketName, String objectName, String fileName, InputStream is, int size, String contentType) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(size);
        objectMetadata.setContentType(contentType);
        ossClient.putObject(new PutObjectRequest(
                bucketName,objectName, is, objectMetadata));
        ObjectInfo objectInfo= new ObjectInfo();
        objectInfo.setObjectPath(bucketName + CommonConstant.PATH_SPLIT + objectName);
        objectInfo.setObjectUrl(fileServerProperties.getAliyun().getEndpoint() + CommonConstant.PATH_SPLIT + objectInfo.getObjectPath());
        objectInfo.setBucketName(bucketName);
        objectInfo.setObjectName(objectName);
        objectInfo.setName(fileName);
        return objectInfo;
    }


}
