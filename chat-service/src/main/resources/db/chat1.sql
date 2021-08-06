/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : chat1

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 05/08/2021 10:41:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_chat_record_0
-- ----------------------------
DROP TABLE IF EXISTS `t_chat_record_0`;
CREATE TABLE `t_chat_record_0`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '发话的用户id',
  `to_user_id` bigint(0) NULL DEFAULT NULL COMMENT '接收话的用户id',
  `msg` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '聊天消息',
  `msg_type` tinyint(0) NULL DEFAULT NULL COMMENT '回执数据格式ACK，默认已发送，0：sent（已发送），1：delivered（已送达）, 2：read（已读）',
  `is_delete` tinyint(0) NULL DEFAULT NULL COMMENT '是否删除',
  `create_time` bigint(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户聊天记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_chat_record_0
-- ----------------------------

-- ----------------------------
-- Table structure for t_chat_record_1
-- ----------------------------
DROP TABLE IF EXISTS `t_chat_record_1`;
CREATE TABLE `t_chat_record_1`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '发话的用户id',
  `to_user_id` bigint(0) NULL DEFAULT NULL COMMENT '接收话的用户id',
  `msg` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '聊天消息',
  `msg_type` tinyint(0) NULL DEFAULT NULL COMMENT '回执数据格式ACK，默认已发送，0：sent（已发送），1：delivered（已送达）, 2：read（已读）',
  `is_delete` tinyint(0) NULL DEFAULT NULL COMMENT '是否删除',
  `create_time` bigint(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户聊天记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_chat_record_1
-- ----------------------------

-- ----------------------------
-- Table structure for t_chat_record_2
-- ----------------------------
DROP TABLE IF EXISTS `t_chat_record_2`;
CREATE TABLE `t_chat_record_2`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '发话的用户id',
  `to_user_id` bigint(0) NULL DEFAULT NULL COMMENT '接收话的用户id',
  `msg` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '聊天消息',
  `msg_type` tinyint(0) NULL DEFAULT NULL COMMENT '回执数据格式ACK，默认已发送，0：sent（已发送），1：delivered（已送达）, 2：read（已读）',
  `is_delete` tinyint(0) NULL DEFAULT NULL COMMENT '是否删除',
  `create_time` bigint(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户聊天记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_chat_record_2
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
