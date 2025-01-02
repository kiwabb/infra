package com.jackmouse.filecenter.entity;

import com.jackmouse.common.entity.BaseEntity;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;

import java.io.Serial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * 文件表 实体类。
 *
 * @author zhoujiaangyao
 * @since 2024-11-22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("file_info")
public class FileInfo extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 文件md5
     */
    @Id
    private String id;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 是否图片
     */
    private Boolean isImg;

    /**
     * 文件类型
     */
    private String contentType;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 物理路径
     */
    private String path;

    /**
     * 文件url
     */
    private String url;

    /**
     * 文件来源
     */
    private String source;

    /**
     * 文件所属模块
     */
    private String module;

    private String bucketName;

    private String objectName;

}
