CREATE DATABASE IF NOT EXISTS `system` DEFAULT CHARACTER SET = utf8;
Use `system`;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
     `parent_id` bigint NOT NULL COMMENT '父菜单id',
     `name` varchar(64) DEFAULT NULL COMMENT '菜单名称',
     `path` varchar(256) DEFAULT NULL COMMENT '菜单访问路径',
     `component` varchar(256) DEFAULT NULL COMMENT '前端组件地址',
     `type` tinyint(1) NOT NULL COMMENT '菜单类型',
     `hidden` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否隐藏',
     `css` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
     `sort` bigint NOT NULL COMMENT '排序',
     `create_time` datetime DEFAULT NULL COMMENT '创建时间',
     `create_by` bigint DEFAULT NULL COMMENT '创建人',
     `update_time` datetime DEFAULT NULL COMMENT '修改时间',
     `update_by` bigint DEFAULT NULL COMMENT '修改人',
     `delete_flag` char(1) DEFAULT '0' COMMENT '删除标识 0 正常 1删除',
     `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
     PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单表';


-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
        `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
        `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色code',
        `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名',
        `data_scope` varchar(32) comment '数据权限范围配置',
        `enabled` tinyint(1) NOT NULL DEFAULT 1 comment '是否启用',
        `create_time` datetime DEFAULT NULL COMMENT '创建时间',
        `create_by` bigint DEFAULT NULL COMMENT '创建人',
        `update_time` datetime DEFAULT NULL COMMENT '修改时间',
        `update_by` bigint DEFAULT NULL COMMENT '修改人',
        `delete_flag` char(1) DEFAULT '0' COMMENT '删除标识 0 正常 1删除',
        `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
        PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';


-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
      `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
      `role_id` int(11) NOT NULL COMMENT '角色表id',
      `menu_id` int(11) NOT NULL COMMENT '菜单表id',
      `create_time` datetime DEFAULT NULL COMMENT '创建时间',
      `create_by` bigint DEFAULT NULL COMMENT '创建人',
      `update_time` datetime DEFAULT NULL COMMENT '修改时间',
      `update_by` bigint DEFAULT NULL COMMENT '修改人',
      `delete_flag` char(1) DEFAULT '0' COMMENT '删除标识 0 正常 1删除',
      `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
    `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
    `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录密码',
    `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
    `avatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
    `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
    `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
    `sex` tinyint(1) NULL DEFAULT NULL COMMENT  '性别',
    `enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT  '是否可用',
    `type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT  '用户类型',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `create_by` bigint DEFAULT NULL COMMENT '创建人',
    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
    `update_by` bigint DEFAULT NULL COMMENT '修改人',
    `delete_flag` char(1) DEFAULT '0' COMMENT '删除标识 0 正常 1删除',
    `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
                              `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                              `user_id` int(11) NOT NULL COMMENT '用户表id',
                              `role_id` int(11) NOT NULL COMMENT '角色表id',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `create_by` bigint DEFAULT NULL COMMENT '创建人',
                             `update_time` datetime DEFAULT NULL COMMENT '修改时间',
                             `update_by` bigint DEFAULT NULL COMMENT '修改人',
                             `delete_flag` char(1) DEFAULT '0' COMMENT '删除标识 0 正常 1删除',
                             `tenant_id` bigint DEFAULT NULL COMMENT '租户id',
                             PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

