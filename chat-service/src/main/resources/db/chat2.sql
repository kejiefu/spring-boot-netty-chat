/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : chat2

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 14/01/2021 21:25:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_chat_record_0
-- ----------------------------
DROP TABLE IF EXISTS `t_chat_record_0`;
CREATE TABLE `t_chat_record_0`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发话的用户id',
  `to_user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '接收话的用户id',
  `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '聊天内容',
  `is_delete` tinyint(0) NULL DEFAULT NULL COMMENT '是否删除',
  `create_time` bigint(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户聊天记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_chat_record_0
-- ----------------------------
INSERT INTO `t_chat_record_0` VALUES ('1347789814011510786', '3', '2', '1610173107042', 0, 1610173107043, 1610173107043);
INSERT INTO `t_chat_record_0` VALUES ('1347789816356126722', '9', '2', '1610173107600', 0, 1610173107601, 1610173107601);
INSERT INTO `t_chat_record_0` VALUES ('1347789820625928193', '15', '2', '1610173108618', 0, 1610173108619, 1610173108619);

-- ----------------------------
-- Table structure for t_chat_record_1
-- ----------------------------
DROP TABLE IF EXISTS `t_chat_record_1`;
CREATE TABLE `t_chat_record_1`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发话的用户id',
  `to_user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '接收话的用户id',
  `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '聊天内容',
  `is_delete` tinyint(0) NULL DEFAULT NULL COMMENT '是否删除',
  `create_time` bigint(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户聊天记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_chat_record_1
-- ----------------------------
INSERT INTO `t_chat_record_1` VALUES ('1347789811096469506', '1', '2', '1610173106347', 0, 1610173106554, 1610173106554);
INSERT INTO `t_chat_record_1` VALUES ('1347789815659872258', '7', '2', '1610173107434', 0, 1610173107434, 1610173107434);
INSERT INTO `t_chat_record_1` VALUES ('1347789819199864833', '13', '2', '1610173108278', 0, 1610173108278, 1610173108278);
INSERT INTO `t_chat_record_1` VALUES ('1347789822261706754', '19', '2', '1610173109009', 0, 1610173109009, 1610173109009);

-- ----------------------------
-- Table structure for t_chat_record_2
-- ----------------------------
DROP TABLE IF EXISTS `t_chat_record_2`;
CREATE TABLE `t_chat_record_2`  (
  `id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '发话的用户id',
  `to_user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '接收话的用户id',
  `content` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '聊天内容',
  `is_delete` tinyint(0) NULL DEFAULT NULL COMMENT '是否删除',
  `create_time` bigint(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户聊天记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_chat_record_2
-- ----------------------------
INSERT INTO `t_chat_record_2` VALUES ('1347789814921674754', '5', '2', '1610173107259', 0, 1610173107259, 1610173107259);
INSERT INTO `t_chat_record_2` VALUES ('1347789817316622338', '11', '2', '1610173107829', 0, 1610173107830, 1610173107830);
INSERT INTO `t_chat_record_2` VALUES ('1347789821427040257', '17', '2', '1610173108810', 0, 1610173108810, 1610173108810);

SET FOREIGN_KEY_CHECKS = 1;
