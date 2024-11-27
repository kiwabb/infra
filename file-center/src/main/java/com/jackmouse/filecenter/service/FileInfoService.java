package com.jackmouse.filecenter.service;

import com.mybatisflex.core.service.IService;
import com.jackmouse.filecenter.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件表 服务层。
 *
 * @author zhoujiaangyao
 * @since 2024-11-22
 */
public interface FileInfoService extends IService<FileInfo> {

    FileInfo upload(MultipartFile file, String module);
}
