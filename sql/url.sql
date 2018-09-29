/*
 Navicat Premium Data Transfer

 Source Server         : dalongm
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3307
 Source Schema         : url

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 29/09/2018 17:07:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for url
-- ----------------------------
DROP TABLE IF EXISTS `url`;
CREATE TABLE `url`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '原始地址',
  `s_url` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '短地址后缀',
  `visited` int(255) NULL DEFAULT NULL COMMENT '访问次数',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `valid_time` double(15, 4) NOT NULL DEFAULT 365.0000 COMMENT '有效时长，单位（天）',
  `valid_times` int(11) NOT NULL DEFAULT 100000 COMMENT '有效次数',
  `visit_pass` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问密码',
  `last_visit_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `s_url`(`s_url`) USING BTREE,
  INDEX `index_s_short`(`s_url`) USING BTREE COMMENT '短url索引',
  INDEX `index_url`(`url`) USING BTREE COMMENT '原始url索引'
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of url
-- ----------------------------
INSERT INTO `url` VALUES (51, 'www.bing.com', 'bing0', 5, '2018-09-29 16:32:43', 0.0100, 5, '123456', '2018-09-29 16:44:19');
INSERT INTO `url` VALUES (52, 'http://www.bing.com', 'bing1', 1, '2018-09-29 16:36:01', 0.0010, 5, '123456', '2018-09-29 16:36:10');
INSERT INTO `url` VALUES (53, 'www.baidu.com', 'baidu2', 10, '2018-09-29 16:51:34', 365.0000, 10, '1234', '2018-09-29 16:52:09');
INSERT INTO `url` VALUES (54, 'https://cn.bing.com/', 'cnbing', 0, '2018-09-29 17:03:41', 0.0001, 5, '123456', NULL);

SET FOREIGN_KEY_CHECKS = 1;
