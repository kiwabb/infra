package com.jackmouse.filecenter.service.impl;


import com.jackmouse.filecenter.mapper.FileInfoMapper;
import com.jackmouse.filecenter.model.ObjectInfo;
import com.jackmouse.filecenter.properties.FileServerProperties;
import com.jackmouse.filecenter.template.AliyunOssTemplate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件表 服务层实现。
 *
 * @author zhoujiaangyao
 * @since 2024-11-22
 */
@Service
@AllArgsConstructor
public class FileInfoServiceImpl extends AbstractIFileService{

    private final AliyunOssTemplate aliyunOssTemplate;

    @Override
    protected ObjectInfo uploadFile(MultipartFile file) {
        return aliyunOssTemplate.upload(file);
    }

    @Override
    protected String fileType() {
        return FileServerProperties.TYPE_ALIYUN;
    }

    @Override
    protected void deleteFile(String objectPath) {

    }
}
