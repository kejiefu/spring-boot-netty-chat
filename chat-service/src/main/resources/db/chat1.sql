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

 Date: 14/01/2021 21:25:09
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
INSERT INTO `t_chat_record_0` VALUES ('1347789815269801986', '6', '2', '1610173107341', 0, 1610173107342, 1610173107342);
INSERT INTO `t_chat_record_0` VALUES ('1347789818352615425', '12', '2', '1610173108077', 0, 1610173108077, 1610173108077);
INSERT INTO `t_chat_record_0` VALUES ('1347789821741613057', '18', '2', '1610173108885', 0, 1610173108885, 1610173108885);

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
INSERT INTO `t_chat_record_1` VALUES ('1347789814263169025', '4', '2', '1610173107101', 0, 1610173107101, 1610173107101);
INSERT INTO `t_chat_record_1` VALUES ('1347789816700059649', '10', '2', '1610173107683', 0, 1610173107683, 1610173107683);
INSERT INTO `t_chat_record_1` VALUES ('1347789820974055425', '16', '2', '1610173108702', 0, 1610173108703, 1610173108703);

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
INSERT INTO `t_chat_record_2` VALUES ('1347789813529165826', '2', '2', '1610173106926', 0, 1610173106927, 1610173106927);
INSERT INTO `t_chat_record_2` VALUES ('1347789815865393153', '8', '2', '1610173107483', 0, 1610173107483, 1610173107483);
INSERT INTO `t_chat_record_2` VALUES ('1347789819816427521', '14', '2', '1610173108426', 0, 1610173108427, 1610173108427);
INSERT INTO `t_chat_record_2` VALUES ('1347789822655971329', '20', '2', '1610173109102', 0, 1610173109102, 1610173109102);

SET FOREIGN_KEY_CHECKS = 1;
