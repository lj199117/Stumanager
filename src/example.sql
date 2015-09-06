/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : example

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-09-05 08:36:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `stuId` int(11) NOT NULL,
  `stuName` varchar(200) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`stuId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '李锦', '呵呵');
INSERT INTO `student` VALUES ('2', '肖峰', '哈哈');