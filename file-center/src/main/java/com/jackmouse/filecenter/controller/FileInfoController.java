package com.jackmouse.filecenter.controller;

import com.jackmouse.common.model.Result;
import com.mybatisflex.core.paginate.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.jackmouse.filecenter.entity.FileInfo;
import com.jackmouse.filecenter.service.FileInfoService;
import org.springframework.web.multipart.MultipartFile;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import java.net.URL;
import java.util.Date;

import java.util.List;

import com.aliyun.oss.model.OSSObject;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.io.InputStream;

/**
 * 文件表 控制层。
 *
 * @author zhoujiaangyao
 * @since 2024-11-22
 */
@RestController
@RequestMapping("/v1/file")
public class FileInfoController {

    @Autowired
    private FileInfoService fileInfoService;

    /**
     * 添加文件表。
     *
     * @param fileInfo 文件表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    public Result<Boolean> save(@RequestBody FileInfo fileInfo) {
        return Result.succeed(fileInfoService.save(fileInfo));
    }

    @PostMapping("upload")
    public Result<FileInfo> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("module") String module) {

        return Result.succeed(fileInfoService.upload(file, module));
    }

    /**
     * 根据主键删除文件表。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public Result<Boolean> remove(@PathVariable("id") String id) {
        return Result.succeed(fileInfoService.removeFileById(id));
    }

    /**
     * 根据主键更新文件表。
     *
     * @param fileInfo 文件表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public Result<Boolean> update(@RequestBody FileInfo fileInfo) {
        return Result.succeed(fileInfoService.updateById(fileInfo));
    }

    /**
     * 查询所有文件表。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public Result<List<FileInfo>> list() {
        return Result.succeed(fileInfoService.list());
    }

    /**
     * 根据文件表主键获取详细信息。
     *
     * @param id 文件表主键
     * @return 文件表详情
     */
    @GetMapping("getInfo/{id}")
    public Result<FileInfo> getInfo(@PathVariable String id) {
        return Result.succeed(fileInfoService.getById(id));
    }

    /**
     * 分页查询文件表。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Result<Page<FileInfo>> page(Page<FileInfo> page) {
        return Result.succeed(fileInfoService.page(page));
    }


    /**
     * 获取文件流
     *
     * @param id 文件ID
     * @return 文件流
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<InputStreamResource> getFile(@PathVariable("id") String id) {
        return fileInfoService.getFile(id);
    }

    /**
     * 获取图片预览（可选，如果需要图片预览功能）
     */
    @GetMapping("/preview/{id}")
    public ResponseEntity<InputStreamResource> previewImage(@PathVariable("id") String id) {
        return fileInfoService.previewImage(id);
    }

}
