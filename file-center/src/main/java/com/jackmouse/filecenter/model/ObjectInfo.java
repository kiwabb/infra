package com.jackmouse.filecenter.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ObjectInfo {
    private String name;
    /**
     * 对象查看路径
     */
    private String objectUrl;
    /**
     * 对象保存路径
     */
    private String objectPath;

    private String bucketName;

    private String objectName;
}
