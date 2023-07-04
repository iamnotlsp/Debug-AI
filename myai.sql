/*
 Navicat Premium Data Transfer

 Source Server         : 本机MySQL
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : myai

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 04/07/2023 11:12:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_id` int(11) UNSIGNED NOT NULL COMMENT '组号',
  `group_nums` int(11) NULL DEFAULT NULL COMMENT '人数',
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组名',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of group
-- ----------------------------

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `resource_id` int(11) UNSIGNED NOT NULL COMMENT '资源id',
  `resource_type` int(11) NOT NULL DEFAULT 1 COMMENT '类型 1文章，2图片，3视频',
  `resource_label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签',
  `resource_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `resource_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `resource_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `resource_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缩略图',
  `resource_reads` int(11) NULL DEFAULT 0 COMMENT '阅读量',
  `resource_search` int(11) NULL DEFAULT 0 COMMENT '搜索量',
  `resource_likes` int(11) NULL DEFAULT 0 COMMENT '点赞数',
  `resource_comments` int(11) NULL DEFAULT 0 COMMENT '总评论数',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` int(11) NULL DEFAULT NULL COMMENT '是否被删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `resource_id`(`resource_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源总表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of resource
-- ----------------------------

-- ----------------------------
-- Table structure for resource_comment
-- ----------------------------
DROP TABLE IF EXISTS `resource_comment`;
CREATE TABLE `resource_comment`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机账号',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `resource_id` int(11) UNSIGNED NOT NULL COMMENT '资源id',
  `comment` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评论内容',
  `comment_likes` int(11) NULL DEFAULT NULL COMMENT '点赞数',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `phone5`(`user_phone`) USING BTREE,
  INDEX `resource5`(`resource_id`) USING BTREE,
  INDEX `name5`(`user_name`) USING BTREE,
  CONSTRAINT `name5` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `phone5` FOREIGN KEY (`user_phone`) REFERENCES `user` (`user_phone`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `resource5` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`resource_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource_comment
-- ----------------------------

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机账号',
  `group_id` int(11) UNSIGNED NOT NULL COMMENT '组号',
  `score` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '积分',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `phone6`(`user_phone`) USING BTREE,
  INDEX `group6`(`group_id`) USING BTREE,
  INDEX `score`(`score`) USING BTREE,
  CONSTRAINT `phone6` FOREIGN KEY (`user_phone`) REFERENCES `user` (`user_phone`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `group6` FOREIGN KEY (`group_id`) REFERENCES `group` (`group_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '积分总表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of score
-- ----------------------------

-- ----------------------------
-- Table structure for score_log
-- ----------------------------
DROP TABLE IF EXISTS `score_log`;
CREATE TABLE `score_log`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机账号',
  `score_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '积分类型（登录，看视频，答题，看文章）',
  `score_add` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '积分增加数',
  `score_reduce` int(11) NULL DEFAULT NULL COMMENT '积分减少数',
  `score` int(11) UNSIGNED NOT NULL COMMENT '积分总数',
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `phone8`(`user_phone`) USING BTREE,
  INDEX `score8`(`score`) USING BTREE,
  CONSTRAINT `phone8` FOREIGN KEY (`user_phone`) REFERENCES `user` (`user_phone`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `score8` FOREIGN KEY (`score`) REFERENCES `score` (`score`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '积分日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of score_log
-- ----------------------------

-- ----------------------------
-- Table structure for study_plan
-- ----------------------------
DROP TABLE IF EXISTS `study_plan`;
CREATE TABLE `study_plan`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机账号',
  `plan_id` int(11) NOT NULL COMMENT '计划id',
  `plan_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '计划名称',
  `plan_label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '计划的标签',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `is_deleted` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `phone10`(`user_phone`) USING BTREE,
  INDEX `plan_id`(`plan_id`) USING BTREE,
  CONSTRAINT `phone10` FOREIGN KEY (`user_phone`) REFERENCES `user` (`user_phone`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学习计划表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of study_plan
-- ----------------------------

-- ----------------------------
-- Table structure for study_plan_event
-- ----------------------------
DROP TABLE IF EXISTS `study_plan_event`;
CREATE TABLE `study_plan_event`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机账号',
  `plan_id` int(11) NOT NULL COMMENT '计划id',
  `event_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件名称',
  `event_time` datetime NULL DEFAULT NULL COMMENT '事件时间',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `is_deleted` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `phone11`(`user_phone`) USING BTREE,
  INDEX `plan11`(`plan_id`) USING BTREE,
  CONSTRAINT `phone11` FOREIGN KEY (`user_phone`) REFERENCES `user` (`user_phone`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `plan11` FOREIGN KEY (`plan_id`) REFERENCES `study_plan` (`plan_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学习事件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of study_plan_event
-- ----------------------------

-- ----------------------------
-- Table structure for study_time
-- ----------------------------
DROP TABLE IF EXISTS `study_time`;
CREATE TABLE `study_time`  (
  `id` int(11) NOT NULL COMMENT '主键',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机账号',
  `group_id` int(11) UNSIGNED NOT NULL COMMENT '组号',
  `study_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学习时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `phone9`(`user_phone`) USING BTREE,
  INDEX `group9`(`group_id`) USING BTREE,
  CONSTRAINT `group9` FOREIGN KEY (`group_id`) REFERENCES `group` (`group_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `phone9` FOREIGN KEY (`user_phone`) REFERENCES `user` (`user_phone`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学习时长表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of study_time
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机账号',
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '小智' COMMENT '名称',
  `head_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '归属地',
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实姓名',
  `user_age` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '25' COMMENT '年龄',
  `user_sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '男' COMMENT '性别',
  `user_likes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '感兴趣的标签',
  `user_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '介绍（个签',
  `group_id` int(11) NULL DEFAULT NULL COMMENT '组群号',
  `achievement` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '成就称号',
  `work` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `is_deleted` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_password`(`user_password`) USING BTREE COMMENT '密码索引',
  INDEX `user_phone`(`user_phone`) USING BTREE,
  INDEX `user_name`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1742614531 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for user_collection
-- ----------------------------
DROP TABLE IF EXISTS `user_collection`;
CREATE TABLE `user_collection`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机账号',
  `resource_id` int(11) UNSIGNED NOT NULL COMMENT '资源id',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `phone1`(`user_phone`) USING BTREE,
  INDEX `resource1`(`resource_id`) USING BTREE,
  CONSTRAINT `phone1` FOREIGN KEY (`user_phone`) REFERENCES `user` (`user_phone`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `resource1` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`resource_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_collection
-- ----------------------------

-- ----------------------------
-- Table structure for user_follow
-- ----------------------------
DROP TABLE IF EXISTS `user_follow`;
CREATE TABLE `user_follow`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT ' 主键',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机账号',
  `follow_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博主名',
  `follow_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '博主描述',
  `head_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像url',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `is_deleted` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `phone4`(`user_phone`) USING BTREE,
  CONSTRAINT `phone4` FOREIGN KEY (`user_phone`) REFERENCES `user` (`user_phone`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户关注表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_follow
-- ----------------------------

-- ----------------------------
-- Table structure for user_history
-- ----------------------------
DROP TABLE IF EXISTS `user_history`;
CREATE TABLE `user_history`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机账号',
  `resource_id` int(11) UNSIGNED NOT NULL COMMENT '资源id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `phone2`(`user_phone`) USING BTREE,
  INDEX `resource2`(`resource_id`) USING BTREE,
  CONSTRAINT `phone2` FOREIGN KEY (`user_phone`) REFERENCES `user` (`user_phone`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `resource2` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`resource_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '浏览记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_history
-- ----------------------------

-- ----------------------------
-- Table structure for user_note
-- ----------------------------
DROP TABLE IF EXISTS `user_note`;
CREATE TABLE `user_note`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机账号',
  `resource_id` int(11) UNSIGNED NOT NULL COMMENT '资源id',
  `note_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '笔记内容',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `is_deleted` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `phone3`(`user_phone`) USING BTREE,
  INDEX `resource3`(`resource_id`) USING BTREE,
  CONSTRAINT `phone3` FOREIGN KEY (`user_phone`) REFERENCES `user` (`user_phone`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `resource3` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`resource_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '笔记表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_note
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
