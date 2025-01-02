package com.jackmouse.filecenter.template;


import com.jackmouse.filecenter.entity.FileInfo;
import com.jackmouse.filecenter.model.ObjectInfo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName OssTemplate
 * @Description 文件上传接口
 * @Author zhoujiaangyao
 * @Date 2024/10/16 11:26
 * @Version 1.0
 **/
public interface OssTemplate extends InitializingBean {
    ObjectInfo upload(MultipartFile file);

    InputStreamResource get(FileInfo fileInfo);

    void deleteFile(FileInfo fileInfo);
}
