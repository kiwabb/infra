CREATE DATABASE IF NOT EXISTS `file_center` DEFAULT CHARACTER SET = utf8;
Use `file_center`;

    -- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info` (
        `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件md5',
        `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件名称',
        `is_img` tinyint(1) NOT NULL COMMENT '是否图片',
        `content_type` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件类型',
        `size` bigint NOT NULL COMMENT '文件大小',
        `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '物理路径',
        `url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件url',
        `source` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件来源',
        `module` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件所属模块',
        `create_time` datetime DEFAULT NULL COMMENT '创建时间',
        `create_by` bigint DEFAULT NULL COMMENT '创建人',
        `update_time` datetime DEFAULT NULL COMMENT '修改时间',
        `update_by` bigint DEFAULT NULL COMMENT '修改人',
        `delete_flag` char(1) DEFAULT '0' COMMENT '删除标识 0 正常 1删除',
        `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
        PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文件表';