package com.jackmouse.filecenter.service;

import com.mybatisflex.core.service.IService;
import com.jackmouse.filecenter.entity.FileInfo;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 文件表 服务层。
 *
 * @author zhoujiaangyao
 * @since 2024-11-22
 */
public interface FileInfoService extends IService<FileInfo> {

    FileInfo upload(MultipartFile file, String module);

    ResponseEntity<InputStreamResource> getFile(String id);

    ResponseEntity<InputStreamResource> previewImage(String id);

    Boolean removeFileById(String id);
}
