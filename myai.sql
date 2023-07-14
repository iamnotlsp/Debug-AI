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

 Date: 14/07/2023 10:12:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `resource_id` int(11) UNSIGNED NOT NULL COMMENT '资源id',
  `resource_type` int(11) NOT NULL DEFAULT 1 COMMENT '类型 1文章，2短视频，3视频',
  `resource_label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签',
  `resource_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `resource_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `resource_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `resource_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '缩略图',
  `resource_reads` int(11) NULL DEFAULT 0 COMMENT '阅读量',
  `resource_search` int(11) NULL DEFAULT 0 COMMENT '搜索量',
  `resource_likes` int(11) NULL DEFAULT 0 COMMENT '点赞数',
  `resource_comments` int(11) NULL DEFAULT 0 COMMENT '总评论数',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` int(11) NULL DEFAULT NULL COMMENT '是否被删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `resource_id`(`resource_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源总表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES (1, 1, 1, '视觉识别', '什么是视觉识别', '视觉识别的作者', '这个就是视觉识别', NULL, 0, 0, 0, 0, '2023-07-03 10:00:11', '2023-07-08 10:01:50', NULL);
INSERT INTO `resource` VALUES (2, 2, 1, '深度学习', '深度学习的奥秘', '作者等描述', '太神奇啦深度学习', NULL, 2, 1, 1, 0, '2023-07-10 10:12:44', NULL, NULL);
INSERT INTO `resource` VALUES (3, 3, 3, '答疑', '十万个为什么', '十万个为什么的为什么', '十万伏特电', NULL, 0, 0, 0, 0, '2023-07-12 14:21:54', NULL, NULL);

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
-- Table structure for resource_image
-- ----------------------------
DROP TABLE IF EXISTS `resource_image`;
CREATE TABLE `resource_image`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `resource_id` int(11) UNSIGNED NOT NULL COMMENT '资源id',
  `photo_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片url',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `resource12`(`resource_id`) USING BTREE,
  CONSTRAINT `resource12` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource_image
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
  CONSTRAINT `group6` FOREIGN KEY (`group_id`) REFERENCES `user_group` (`group_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `phone6` FOREIGN KEY (`user_phone`) REFERENCES `user` (`user_phone`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '积分总表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES (1, '15990771449', 1, 155);
INSERT INTO `score` VALUES (2, '123456', 1, 10);
INSERT INTO `score` VALUES (3, '110', 1, 15);

-- ----------------------------
-- Table structure for score_detail
-- ----------------------------
DROP TABLE IF EXISTS `score_detail`;
CREATE TABLE `score_detail`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机账号',
  `login_score` int(11) UNSIGNED NULL DEFAULT 1 COMMENT '登录积分 1分',
  `article_score` int(11) NULL DEFAULT 0 COMMENT '看文章 6分',
  `view_score` int(11) NULL DEFAULT 0 COMMENT '看视频 6分',
  `answer_score` int(11) NULL DEFAULT 0 COMMENT '答题 5分',
  `pk_score` int(11) NULL DEFAULT 0 COMMENT 'pk 5分',
  `ai_score` int(11) NULL DEFAULT 0 COMMENT 'ai学习 5分',
  `expense_score` int(11) NULL DEFAULT 0 COMMENT '花费积分',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `phone8`(`user_phone`) USING BTREE,
  CONSTRAINT `phone8` FOREIGN KEY (`user_phone`) REFERENCES `user` (`user_phone`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of score_detail
-- ----------------------------
INSERT INTO `score_detail` VALUES (1, '15990771449', 1, 5, 5, 0, 5, 0, 0, '2023-07-05 16:35:01');
INSERT INTO `score_detail` VALUES (2, '15990771449', 1, 5, 5, 0, 0, 0, 0, '2023-07-06 16:45:08');
INSERT INTO `score_detail` VALUES (4, '15990771449', 1, 0, 5, 0, 0, 0, 5, '2023-07-07 18:48:07');
INSERT INTO `score_detail` VALUES (5, '15990771449', 1, 0, 0, 0, 2, 0, 0, '2023-07-08 15:22:59');
INSERT INTO `score_detail` VALUES (6, '15990771449', 1, 1, 1, 0, 1, 1, 0, '2023-07-09 13:32:15');
INSERT INTO `score_detail` VALUES (7, '15990771449', 1, 0, 0, 0, 0, 0, 0, '2023-07-10 09:35:13');
INSERT INTO `score_detail` VALUES (10, '15990771449', 1, 0, 0, 0, 0, 0, 0, '2023-07-11 09:56:15');
INSERT INTO `score_detail` VALUES (11, '15990771449', 1, 0, 0, 0, 0, 0, 0, '2023-07-12 10:45:45');
INSERT INTO `score_detail` VALUES (12, '15990771449', 1, 0, 0, 0, 0, 0, 0, '2023-07-13 09:53:38');

-- ----------------------------
-- Table structure for study_plan
-- ----------------------------
DROP TABLE IF EXISTS `study_plan`;
CREATE TABLE `study_plan`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机账号',
  `plan_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '计划名称',
  `start_time` datetime NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
  `plan_state` int(11) NULL DEFAULT NULL COMMENT '计划的状态（0未开始，1进行中，2已完成）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `phone10`(`user_phone`) USING BTREE,
  CONSTRAINT `phone10` FOREIGN KEY (`user_phone`) REFERENCES `user` (`user_phone`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学习计划表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of study_plan
-- ----------------------------
INSERT INTO `study_plan` VALUES (1, '15990771449', '第一次测试', '2023-07-10 00:00:00', '2023-07-15 00:00:00', 0, '2023-07-10 17:02:14', '2023-07-11 16:14:22', 0);
INSERT INTO `study_plan` VALUES (2, '15990771449', '第一次测试', '2023-07-10 00:00:00', '2023-07-15 00:00:00', 0, '2023-07-10 17:08:51', '2023-07-11 16:14:23', 0);
INSERT INTO `study_plan` VALUES (7, '15990771449', '我的学习计划1.0', '2023-07-11 00:00:00', '2023-07-15 00:00:00', 1, '2023-07-11 11:02:36', '2023-07-11 16:23:47', 1);
INSERT INTO `study_plan` VALUES (8, '15990771449', '我的学习计划2.0', '2023-07-11 00:00:00', '2023-07-15 00:00:00', 0, '2023-07-11 16:24:22', '2023-07-11 16:24:22', 0);
INSERT INTO `study_plan` VALUES (9, '15990771449', '我的学习计划3.0', '2023-07-12 00:00:00', '2023-07-15 00:00:00', 0, '2023-07-12 11:21:32', '2023-07-12 11:22:04', 1);

-- ----------------------------
-- Table structure for study_plan_event
-- ----------------------------
DROP TABLE IF EXISTS `study_plan_event`;
CREATE TABLE `study_plan_event`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机账号',
  `plan_id` int(11) UNSIGNED NOT NULL COMMENT '计划id',
  `event_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '事件名称',
  `event_time` datetime NULL DEFAULT NULL COMMENT '事件时间',
  `event_state` int(11) NULL DEFAULT NULL COMMENT '事件状态（0未1进行2完成）',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `phone11`(`user_phone`) USING BTREE,
  INDEX `planId11`(`plan_id`) USING BTREE,
  CONSTRAINT `phone11` FOREIGN KEY (`user_phone`) REFERENCES `user` (`user_phone`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `planId11` FOREIGN KEY (`plan_id`) REFERENCES `study_plan` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '学习事件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of study_plan_event
-- ----------------------------
INSERT INTO `study_plan_event` VALUES (2, '15990771449', 1, '做作业', '2023-07-10 17:05:39', 1, '2023-07-10 17:07:33', '2023-07-11 16:14:05', 0);
INSERT INTO `study_plan_event` VALUES (14, '15990771449', 8, '读语文', '2023-07-11 00:00:00', 0, '2023-07-11 16:24:22', '2023-07-11 16:24:22', 0);
INSERT INTO `study_plan_event` VALUES (15, '15990771449', 8, '读数学', '2023-07-12 00:00:00', 0, '2023-07-11 16:24:22', '2023-07-11 16:24:22', 0);
INSERT INTO `study_plan_event` VALUES (16, '15990771449', 8, '读英语', '2023-07-13 00:00:00', 0, '2023-07-11 16:24:22', '2023-07-11 16:24:22', 0);
INSERT INTO `study_plan_event` VALUES (17, '15990771449', 8, '读地理', '2023-07-14 00:00:00', 0, '2023-07-11 16:24:22', '2023-07-11 16:24:22', 0);
INSERT INTO `study_plan_event` VALUES (18, '15990771449', 8, '读物理', '2023-07-15 00:00:00', 0, '2023-07-11 16:24:22', '2023-07-11 16:24:22', 0);

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
  CONSTRAINT `group9` FOREIGN KEY (`group_id`) REFERENCES `user_group` (`group_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
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
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `head_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '归属地',
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `user_age` int(11) NOT NULL DEFAULT 25 COMMENT '年龄',
  `user_sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '男' COMMENT '性别',
  `user_likes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '感兴趣的标签',
  `user_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '介绍（个签',
  `group_id` int(11) NULL DEFAULT NULL COMMENT '组群号',
  `achievement` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '成就称号',
  `work` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_password`(`user_password`) USING BTREE COMMENT '密码索引',
  INDEX `user_phone`(`user_phone`) USING BTREE,
  INDEX `user_name`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '15990771449', '123456', '朱元璋', '', '南京', '朱重八', 35, '男', '', '', 1, NULL, '', '2023-07-04 19:35:31', '2023-07-13 11:13:35', NULL);
INSERT INTO `user` VALUES (2, '123456', '123456', '美猴王', NULL, '浙江嘉兴', '孙悟空', 27, '男', '西游记', NULL, 1, NULL, NULL, '2023-07-06 19:37:34', '2023-07-06 19:37:34', NULL);
INSERT INTO `user` VALUES (3, '110', '123456', NULL, NULL, NULL, NULL, 25, '男', NULL, NULL, 1, NULL, NULL, '2023-07-06 19:40:02', '2023-07-06 19:40:02', NULL);

-- ----------------------------
-- Table structure for user_collection
-- ----------------------------
DROP TABLE IF EXISTS `user_collection`;
CREATE TABLE `user_collection`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机账号',
  `resource_id` int(11) UNSIGNED NOT NULL COMMENT '资源id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `phone1`(`user_phone`) USING BTREE,
  INDEX `resource1`(`resource_id`) USING BTREE,
  CONSTRAINT `phone1` FOREIGN KEY (`user_phone`) REFERENCES `user` (`user_phone`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `resource1` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`resource_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_collection
-- ----------------------------
INSERT INTO `user_collection` VALUES (1, '15990771449', 1, '2023-07-04 22:47:27');
INSERT INTO `user_collection` VALUES (2, '15990771449', 2, '2023-07-10 10:19:30');

-- ----------------------------
-- Table structure for user_follow
-- ----------------------------
DROP TABLE IF EXISTS `user_follow`;
CREATE TABLE `user_follow`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT ' 主键',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机账号',
  `follow_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博主名',
  `follow_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '博主描述',
  `head_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像url',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `phone4`(`user_phone`) USING BTREE,
  CONSTRAINT `phone4` FOREIGN KEY (`user_phone`) REFERENCES `user` (`user_phone`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户关注表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_follow
-- ----------------------------
INSERT INTO `user_follow` VALUES (1, '15990771449', '爱因斯坦', '发现相对论', NULL, '2023-07-08 10:32:51', NULL, NULL);

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_id` int(11) UNSIGNED NOT NULL COMMENT '组号',
  `group_nums` int(11) NULL DEFAULT NULL COMMENT '人数',
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组名',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_group
-- ----------------------------
INSERT INTO `user_group` VALUES (1, 1, 3, '浙江嘉兴组');

-- ----------------------------
-- Table structure for user_history
-- ----------------------------
DROP TABLE IF EXISTS `user_history`;
CREATE TABLE `user_history`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机账号',
  `resource_id` int(11) UNSIGNED NOT NULL COMMENT '资源id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `phone2`(`user_phone`) USING BTREE,
  INDEX `resource2`(`resource_id`) USING BTREE,
  CONSTRAINT `phone2` FOREIGN KEY (`user_phone`) REFERENCES `user` (`user_phone`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `resource2` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`resource_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '浏览记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_history
-- ----------------------------
INSERT INTO `user_history` VALUES (1, '15990771449', 1, '2023-07-08 15:21:28');
INSERT INTO `user_history` VALUES (2, '15990771449', 1, '2023-07-10 11:38:01');
INSERT INTO `user_history` VALUES (3, '15990771449', 1, '2023-07-12 11:22:30');

-- ----------------------------
-- Table structure for user_note
-- ----------------------------
DROP TABLE IF EXISTS `user_note`;
CREATE TABLE `user_note`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机账号',
  `resource_id` int(11) UNSIGNED NOT NULL COMMENT '资源id',
  `note_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '笔记内容',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `phone3`(`user_phone`) USING BTREE,
  INDEX `resource3`(`resource_id`) USING BTREE,
  CONSTRAINT `phone3` FOREIGN KEY (`user_phone`) REFERENCES `user` (`user_phone`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `resource3` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`resource_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '笔记表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_note
-- ----------------------------
INSERT INTO `user_note` VALUES (1, '15990771449', 1, 'qaq', '2023-07-08 11:41:05', '2023-07-11 16:27:01', 0);
INSERT INTO `user_note` VALUES (3, '15990771449', 1, '狠狠赚一笔!', '2023-07-09 16:46:14', '2023-07-11 16:27:01', 0);
INSERT INTO `user_note` VALUES (6, '15990771449', 2, '你好，吃了吗？', '2023-07-12 11:20:37', '2023-07-12 11:20:37', 0);

SET FOREIGN_KEY_CHECKS = 1;
