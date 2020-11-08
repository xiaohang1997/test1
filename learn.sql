/*
 Navicat Premium Data Transfer

 Source Server         : my
 Source Server Type    : MySQL
 Source Server Version : 50714
 Source Host           : localhost:3306
 Source Schema         : learn

 Target Server Type    : MySQL
 Target Server Version : 50714
 File Encoding         : 65001

 Date: 27/09/2020 11:21:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chapter
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `courseId` int(11) NOT NULL,
  `videoId` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chapter
-- ----------------------------
INSERT INTO `chapter` VALUES (5, 'Typescript第1章', 1, 5);
INSERT INTO `chapter` VALUES (10, '计算机与网络第1章', 2, 6);
INSERT INTO `chapter` VALUES (11, 'Typescript第2章', 1, 7);
INSERT INTO `chapter` VALUES (12, '计算机与网络第1章', 2, 8);
INSERT INTO `chapter` VALUES (13, 'Java第1章', 3, 9);
INSERT INTO `chapter` VALUES (14, 'Android第1章', 4, 10);
INSERT INTO `chapter` VALUES (15, 'Bootstrap第1章', 5, 11);
INSERT INTO `chapter` VALUES (16, 'SpringBoot第1章', 6, 12);
INSERT INTO `chapter` VALUES (17, 'IOS第1章', 7, 13);
INSERT INTO `chapter` VALUES (18, '算法与数据结构第1章', 8, 14);
INSERT INTO `chapter` VALUES (19, '运维第1章', 9, 15);
INSERT INTO `chapter` VALUES (20, '自动化运维', 10, 16);
INSERT INTO `chapter` VALUES (21, 'WebApp第1章', 11, 17);
INSERT INTO `chapter` VALUES (22, 'SpringCloud第1章', 12, 18);
INSERT INTO `chapter` VALUES (23, 'WEEX第1章', 13, 19);
INSERT INTO `chapter` VALUES (24, '数学第1章', 14, 20);
INSERT INTO `chapter` VALUES (25, '安全测试第1章', 15, 21);
INSERT INTO `chapter` VALUES (26, '模型制作第1章', 16, 22);
INSERT INTO `chapter` VALUES (27, '动效动画', 17, 23);
INSERT INTO `chapter` VALUES (28, 'PS', 18, 24);

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `courseId` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collection
-- ----------------------------
INSERT INTO `collection` VALUES (59, 1, 1);

-- ----------------------------
-- Table structure for content
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `videoId` int(11) NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of content
-- ----------------------------
INSERT INTO `content` VALUES (1, 1, 6, '没意思', '2020-09-25 09:43:26');
INSERT INTO `content` VALUES (2, 1, 6, '试试', '2020-09-25 03:22:40');
INSERT INTO `content` VALUES (3, 1, 6, '还可以', '2020-09-25 03:24:22');
INSERT INTO `content` VALUES (4, 1, 6, '四楼', '2020-09-25 03:27:52');
INSERT INTO `content` VALUES (5, 1, 6, '五楼', '2020-09-25 03:32:56');
INSERT INTO `content` VALUES (6, 1, 6, '七楼', '2020-09-25 09:18:51');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `courseDirectionId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '无',
  `price` int(10) NOT NULL DEFAULT 0,
  `number` int(11) NOT NULL DEFAULT 0,
  `level` int(11) NOT NULL,
  `courseTime` int(11) NOT NULL,
  `createTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, 'Typescript', 1, 1, '使用RN+TS开发听书App，从需求分析，到功能实现，一步步带领大家完成功能，学会如何解决实际开发问题，是一个完整的项目开发实例', 0, 9, 1, 135, '2020-09-21 03:03:06');
INSERT INTO `course` VALUES (2, '计算机与网络', 4, 1, '编译原理，操作系统，图形学被称为程序员的三大浪漫，不仅因为它们是大学计算机系的必修内容，更因为它们在回答计算机领域三个基本问题', 0, 29, 1, 144, '2020-09-21 03:05:13');
INSERT INTO `course` VALUES (3, 'Java', 2, 1, '课程中还融入了很多开发小Tips。最后基于RabbitMQ开发分布式事务框架，提升技术深度，培养框架思维', 0, 36, 2, 126, '2020-09-21 03:06:30');
INSERT INTO `course` VALUES (4, 'Android', 3, 1, '对于Android开发来说，无论是对于工作项目中的使用，还是未来职业发展中拥有更多的职业选择，学会AI语音开发技术，都是一个非常好的选择', 0, 47, 2, 154, '2020-09-21 03:07:49');
INSERT INTO `course` VALUES (5, 'Bootstrap', 1, 1, 'Bootstrap是用于前端开发的工具包，提供了优雅的HTML和CSS规范，并基于jQuery开发了丰富的Web组件', 99, 10, 2, 141, '2020-09-22 01:33:09');
INSERT INTO `course` VALUES (6, 'SpringBoot', 2, 1, '工作流引擎可以减少硬编码，高效处理业务需求变更，所以领悟工作流引擎背后的思想，将有助于在职场中高效交付工作结果', 299, 35, 1, 146, '2020-09-23 16:43:54');
INSERT INTO `course` VALUES (7, 'IOS', 3, 1, '无需原生开发基础，也能完美呈现京东商城。本课程融合vue、Android、IOS等目前流行的前端和移动端技术，混合开发经典电商APP京东', 139, 42, 2, 151, '2020-09-23 16:45:43');
INSERT INTO `course` VALUES (8, '算法与数据结构', 4, 1, '从个人发展的角度，掌握数据结构与算法，有助于更好地阅读源码和设计编写一些复杂的工具', 249, 21, 2, 147, '2020-09-23 16:45:49');
INSERT INTO `course` VALUES (9, '运维', 5, 1, 'Linux作为操作系统，被诸多企业和开发者所喜爱。尤其对于企业运维而言，更是如此', 0, 48, 1, 134, '2020-09-23 16:47:38');
INSERT INTO `course` VALUES (10, '自动化运维', 5, 1, '自动化部署作为工作中的常见环节，是求职中的必备能力。本课程提供了高效可用的部署方法', 209, 52, 2, 137, '2020-09-23 16:47:45');
INSERT INTO `course` VALUES (11, 'WebApp', 1, 3, '慕课网首发，Vue3.0企业级项目实战。热门技术双剑合璧，Vue3.0配合TypeScript', 223, 43, 3, 128, '2020-09-26 22:14:25');
INSERT INTO `course` VALUES (12, 'SpringCloud', 2, 3, '让你全面掌握主流后端技术栈：Spring Cloud+MongoDB+Redis+RabbitMQ等', 265, 60, 3, 165, '2020-09-26 22:16:34');
INSERT INTO `course` VALUES (13, 'WEEX', 3, 4, '主要带领同学入门weex框架的使用，能够利用这套框架做出一个接近原生应用的案例', 164, 54, 3, 152, '2020-09-26 22:18:21');
INSERT INTO `course` VALUES (14, '数学', 4, 4, '数学作为计算机行业的基础学科，对整个计算机行业的影响及其深远。毫不夸张地说，我们身边的每一行代码里，都有数学的影子', 621, 55, 3, 166, '2020-09-26 22:21:05');
INSERT INTO `course` VALUES (15, '安全测试', 5, 5, 'Vim作为Linux 平台首选编辑器，无论是运维还是开发等IT人员都应该系统地学习下这款优秀经典的上古神器', 288, 46, 3, 177, '2020-09-26 22:22:09');
INSERT INTO `course` VALUES (16, '模型制作', 6, 5, '本套课程结合基础知识,从建模到材质渲染,带你完整的完成一个建模作品', 0, 13, 1, 121, '2020-09-26 22:23:00');
INSERT INTO `course` VALUES (17, '动效动画', 6, 6, '本课程从Maya工程目录的创建、高模低模的制作、UV的拆分、法线贴图的烘培、贴图的制作到最终模型渲染出效果图，完整的介绍了Maya次世代模型制作的整个流程', 121, 33, 2, 132, '2020-09-26 22:23:05');
INSERT INTO `course` VALUES (18, 'PS', 6, 6, '课程系统的介绍了PS基本操作工具，掌握蓝湖第三方切图标注工具的基本使用，着重教大家切图的方式和技巧', 188, 51, 3, 142, '2020-09-26 22:24:03');

-- ----------------------------
-- Table structure for coursedirection
-- ----------------------------
DROP TABLE IF EXISTS `coursedirection`;
CREATE TABLE `coursedirection`  (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of coursedirection
-- ----------------------------
INSERT INTO `coursedirection` VALUES (1, '前端');
INSERT INTO `coursedirection` VALUES (2, '后端');
INSERT INTO `coursedirection` VALUES (3, '移动');
INSERT INTO `coursedirection` VALUES (4, '云计算');
INSERT INTO `coursedirection` VALUES (5, '运维');
INSERT INTO `coursedirection` VALUES (6, 'UI设计');

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `courseId` int(11) NOT NULL,
  `createTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of history
-- ----------------------------

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `courseId` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 0,
  `date` datetime(0) NOT NULL,
  `orderNum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (31, 2, 14, 1, '2020-09-27 01:24:35', '202082792435');
INSERT INTO `orders` VALUES (32, 1, 12, 0, '2020-09-27 03:19:35', '2020827111934');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loginName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `loginPwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `lastVideoUrl` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `identify` int(11) NOT NULL DEFAULT 0,
  `school` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introduce` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `headUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', 'zs', '123', 'zs@163.com', '13656542278', '上海', '20200701_090304.mp4', 1, '四川大学', '这个人很勤快，却没有介绍', NULL);
INSERT INTO `user` VALUES (2, '李四', 'ls', '123', 'ls@qq.com', '18376489822', '北京', NULL, 0, '北京大学', '这个人有点懒，没有介绍', NULL);
INSERT INTO `user` VALUES (3, '王五', 'ww', '123', 'ww@qq.com', '12433435235', '成都', NULL, 1, '成都大学', '这个人有点懒，没有介绍', NULL);
INSERT INTO `user` VALUES (4, '小明', 'xm', '123', 'xm@163.com', '19867821334', '成都', NULL, 1, '成都大学', '这个人有点懒，没有介绍', NULL);
INSERT INTO `user` VALUES (5, '小泽', 'xz', '123', 'xz@163.com', '17632129857', '青海', NULL, 1, '青海大学', '这个人有点懒，没有介绍', NULL);
INSERT INTO `user` VALUES (6, '王六', 'wl', '123', 'wl@163.com', '15428171298', '西藏', NULL, 1, '西藏大学', '这个人有点懒，没有介绍', NULL);
INSERT INTO `user` VALUES (7, '钟就', 'zj', '123', 'zj@163.com', '16378291245', '延安', NULL, 1, '延安大学', '这个人有点懒，没有介绍', NULL);
INSERT INTO `user` VALUES (8, NULL, 'xt', '123', NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chapterId` int(11) NOT NULL,
  `url` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES (5, 5, '20200630_161036.mp4');
INSERT INTO `video` VALUES (6, 10, '20200630_090635.mp4');
INSERT INTO `video` VALUES (7, 11, '20200701_090304.mp4');
INSERT INTO `video` VALUES (8, 12, '20200701_151938.mp4');
INSERT INTO `video` VALUES (9, 13, '20200702_091058.mp4');
INSERT INTO `video` VALUES (10, 14, '20200702_140422.mp4');
INSERT INTO `video` VALUES (11, 15, '20200703_103722.mp4');
INSERT INTO `video` VALUES (12, 16, '20200703_140645.mp4');
INSERT INTO `video` VALUES (13, 17, '20200713_Java语言基础1-Java组成及环境搭建.mp4');
INSERT INTO `video` VALUES (14, 18, '20200713_Java语言基础2-开发简单程序.mp4');
INSERT INTO `video` VALUES (15, 19, '20200714_Java语言基础3-变量和数据类型.mp4');
INSERT INTO `video` VALUES (16, 20, '20200714_Java语言基础4-表达式和运算符.mp4');
INSERT INTO `video` VALUES (17, 21, '20200715_Java语言基础5-分支结构.mp4');
INSERT INTO `video` VALUES (18, 22, '20200715_Java语言基础6-循环结构.mp4');
INSERT INTO `video` VALUES (19, 23, '20200716_Java语言基础7-嵌套循环.mp4');
INSERT INTO `video` VALUES (20, 24, '20200716_Java语言基础8-数组及查找算法.mp4');
INSERT INTO `video` VALUES (21, 25, '20200717_Java语言基础9-排序算法和数组扩容缩容.mp4');
INSERT INTO `video` VALUES (22, 26, '20200717_Java语言基础10-抓阄游戏和二维数组.mp4');
INSERT INTO `video` VALUES (23, 27, '20200720_面向对象基础11-类和对象.mp4');
INSERT INTO `video` VALUES (24, 28, '20200720_面向对象基础12-方法定义和调用.mp4');

SET FOREIGN_KEY_CHECKS = 1;
