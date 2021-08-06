/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : user

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 05/08/2021 10:42:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `online_status` tinyint(0) NULL DEFAULT 0 COMMENT '在线状态，0：不在线，1：在线',
  `status` tinyint(0) NULL DEFAULT NULL COMMENT '状态，0：启用，1：禁用',
  `is_delete` tinyint(0) NULL DEFAULT NULL COMMENT '是否删除',
  `create_time` bigint(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(0) NULL DEFAULT NULL COMMENT '更新时间',
  `head_portrait` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1349643297211699201, '吕布', 'e10adc3949ba59abbe56e057f20f883e', 1, 0, 0, 1610615012173, 1610615012173, 'http://static.bbs.9wee.com/attachment/forum/201306/07/210751qbp4p4c5yzhhbpym.jpg');
INSERT INTO `t_user` VALUES (1349643297211699202, '诸葛亮', 'e10adc3949ba59abbe56e057f20f883e', 1, 0, 0, 1610615012173, 1610615012173, 'http://i0.9wee.com/201308/14QVBz0L.jpg');
INSERT INTO `t_user` VALUES (1349643297211699203, '曹操', 'e10adc3949ba59abbe56e057f20f883e', 1, 0, 0, 1610615012173, 1610615012173, 'http://i0.9wee.com/201811/17LK75WX.jpg');

-- ----------------------------
-- Table structure for t_user_friend
-- ----------------------------
DROP TABLE IF EXISTS `t_user_friend`;
CREATE TABLE `t_user_friend`  (
  `id` bigint(0) NOT NULL COMMENT '主键',
  `user_id` bigint(0) NOT NULL COMMENT '用户id,t_user.id',
  `friend_id` bigint(0) NOT NULL COMMENT '好友id',
  `is_delete` tinyint(0) NULL DEFAULT NULL COMMENT '是否删除',
  `create_time` bigint(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户好友表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_friend
-- ----------------------------
INSERT INTO `t_user_friend` VALUES (1349643297211699201, 1349643297211699201, 1349643297211699202, 0, 1610615012173, 1610615012173);
INSERT INTO `t_user_friend` VALUES (1349643297211699202, 1349643297211699201, 1349643297211699203, 0, 1610615012173, 1610615012173);

SET FOREIGN_KEY_CHECKS = 1;
