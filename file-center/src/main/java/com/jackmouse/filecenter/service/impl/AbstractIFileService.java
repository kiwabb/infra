package com.jackmouse.filecenter.service.impl;

import com.jackmouse.filecenter.entity.FileInfo;
import com.jackmouse.filecenter.mapper.FileInfoMapper;
import com.jackmouse.filecenter.model.ObjectInfo;
import com.jackmouse.filecenter.service.FileInfoService;
import com.jackmouse.filecenter.util.FileUtil;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
public abstract class AbstractIFileService extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {
    private static final String FILE_SPLIT = ".";

    @Override
    public FileInfo upload(MultipartFile file, String module) {
        FileInfo fileInfo = FileUtil.getFileInfo(file);
        if (!fileInfo.getName().contains(FILE_SPLIT)) {
            throw new IllegalArgumentException("缺少后缀名");
        }
        ObjectInfo objectInfo = uploadFile(file);
        fileInfo.setPath(objectInfo.getObjectPath());
        fileInfo.setUrl(objectInfo.getObjectUrl());
        fileInfo.setModule(module);
        // 设置文件来源
        fileInfo.setSource(fileType());
        // 将文件信息保存到数据库
        this.save(fileInfo);

        return fileInfo;
    }

    /**
     * 上传文件
     *
     * @param file
     */
    protected abstract ObjectInfo uploadFile(MultipartFile file);

    /**
     * 文件来源
     *
     * @return
     */
    protected abstract String fileType();


    /**
     * 删除文件资源
     *
     * @param objectPath 文件路径
     */
    protected abstract void deleteFile(String objectPath);

}
