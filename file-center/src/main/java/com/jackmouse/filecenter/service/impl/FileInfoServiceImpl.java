package com.jackmouse.filecenter.service.impl;


import com.aliyun.oss.model.OSSObject;
import com.jackmouse.filecenter.entity.FileInfo;
import com.jackmouse.filecenter.mapper.FileInfoMapper;
import com.jackmouse.filecenter.model.ObjectInfo;
import com.jackmouse.filecenter.properties.FileServerProperties;
import com.jackmouse.filecenter.template.AliyunOssTemplate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 文件表 服务层实现。
 *
 * @author zhoujiaangyao
 * @since 2024-11-22
 */
@Service
@AllArgsConstructor
@Slf4j
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


    @Override
    public ResponseEntity<InputStreamResource> getFile(String id) {
        try {
            return this.getByIdOpt(id)
                    .map(fileInfo -> {
                        InputStreamResource inputStreamResource = aliyunOssTemplate.get(fileInfo);

                        // 4. 设置响应头
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.parseMediaType(fileInfo.getContentType()));
                        headers.setContentDispositionFormData("attachment", fileInfo.getName());

                        return ResponseEntity
                                .ok()
                                .headers(headers)
                                .body(new InputStreamResource(inputStreamResource));
                    })
                    .orElseThrow(() -> new RuntimeException("文件不存在"));
        } catch (Exception e) {
            log.error("预览图片失败", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public ResponseEntity<InputStreamResource> previewImage(String id) {
        try {
            return this.getByIdOpt(id)
                    .map(fileInfo -> {
                        InputStreamResource inputStreamResource = aliyunOssTemplate.get(fileInfo);

                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentType(MediaType.parseMediaType(fileInfo.getContentType()));
                        // 设置为inline，浏览器会直接显示图片
                        headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline");

                        return ResponseEntity
                                .ok()
                                .headers(headers)
                                .body(new InputStreamResource(inputStreamResource));
                    })
                    .orElseThrow(() -> new RuntimeException("文件不存在"));
        } catch (Exception e) {
            log.error("预览图片失败", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @Override
    public Boolean removeFileById(String id) {
        return this.getByIdOpt(id).map(fileInfo -> {
            aliyunOssTemplate.deleteFile(fileInfo);
            this.removeById(id);
            return true;
        })
        .orElseThrow(() -> new RuntimeException("文件不存在"));
    }
}
