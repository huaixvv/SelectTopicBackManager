/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : topicmanager

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 05/05/2020 22:29:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for applythesis
-- ----------------------------
DROP TABLE IF EXISTS `applythesis`;
CREATE TABLE `applythesis` (
  `thesis_id` varchar(25) COLLATE utf8_bin NOT NULL,
  `thesis_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '课题名称',
  `thesis_college` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '学院',
  `thesis_type` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '课题类型',
  `thesis_from` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '课题来源',
  `student_name` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '申请学生',
  `thesis_date` datetime NOT NULL,
  `thesis_doc` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '附件url',
  `teacher` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '指定教师',
  `thesis_status` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '审核状态',
  `thesis_desc` text COLLATE utf8_bin COMMENT '简述',
  PRIMARY KEY (`thesis_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of applythesis
-- ----------------------------
BEGIN;
INSERT INTO `applythesis` VALUES ('asi-c83e0c', '基于web的物流管理系统', '计算机与电气工程学院', '工程工业设计', '生产/社会实践', '王晴', '2020-05-05 14:50:47', 'http://192.168.1.3/group1/M00/00/00/wKgBA16xDL6AYKiGAAAsODodPHQ64.docx', '郭晶', '待审核', '基于web的物流管理系统');
COMMIT;

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `manager_id` varchar(25) COLLATE utf8_bin NOT NULL,
  `login_name` varchar(25) COLLATE utf8_bin NOT NULL,
  `manager_pwd` varchar(25) COLLATE utf8_bin NOT NULL,
  `manager_name` varchar(25) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`manager_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of manager
-- ----------------------------
BEGIN;
INSERT INTO `manager` VALUES ('110', 'admin', 'admin', '王建');
COMMIT;

-- ----------------------------
-- Table structure for orderinfo
-- ----------------------------
DROP TABLE IF EXISTS `orderinfo`;
CREATE TABLE `orderinfo` (
  `id` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '订单id',
  `stu_num` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '外键关联学生',
  `sis_num` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '外键关联课题',
  `thesis_name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '课题名称',
  `thesis_college` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '所属学院',
  `thesis_type` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '课题类型',
  `thesis_from` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '课题来源',
  `classroom` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '教研室',
  `teacher` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '指导教师',
  `model` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '选题模式',
  `allow_special` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '可选专业',
  `thesis_date` datetime NOT NULL COMMENT '申报日期',
  `thesis_doc` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '附件',
  `thesis_desc` text COLLATE utf8_bin NOT NULL COMMENT '简述',
  `student` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '所属学生',
  `create_time` datetime DEFAULT NULL COMMENT '选题时间',
  `status` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `stu_num` (`stu_num`),
  KEY `sis_num` (`sis_num`),
  CONSTRAINT `orderinfo_ibfk_1` FOREIGN KEY (`stu_num`) REFERENCES `student` (`student_id`),
  CONSTRAINT `orderinfo_ibfk_2` FOREIGN KEY (`sis_num`) REFERENCES `thesis` (`thesis_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of orderinfo
-- ----------------------------
BEGIN;
INSERT INTO `orderinfo` VALUES ('ori-dcb82c', 'stu-vsd3df', 'sis-732be5', '基于安卓的视频播放器的设计与实现', '计算机与电气工程学院', '工程工业设计', '生产/社会实践', NULL, '郭晶 ', '师生互选课题', '计算机与电气工程学院(全部专业)', '2020-05-05 14:14:44', 'http://192.168.1.3/group1/M00/00/00/wKgBA16xBEmASIznAAAsODodPHQ45.docx', '基于安卓的视频播放器的设计与实现', '王晴', '2020-05-05 14:58:55', '待审核');
COMMIT;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `student_id` varchar(25) COLLATE utf8_bin NOT NULL,
  `login_name` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '登录账号--学号',
  `student_pwd` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '密码',
  `student_name` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '学生姓名',
  `sex` varchar(5) COLLATE utf8_bin NOT NULL COMMENT '性别',
  `college` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '学院',
  `speciality` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '专业',
  `class_number` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '班级',
  `phone` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '电话',
  `email` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '邮箱',
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES ('stu-2cb5c7', '20160005', '888888', '钮楠', '男', '计算机与电气工程学院', '网络工程', '网络16101', '13007190536', 't8mu5f@263.net');
INSERT INTO `student` VALUES ('stu-54gtrd', '20160012', '888888', '赵明月', '男', '计算机科学与技术学院', '计算机科学与技术', '计科16101', '17867854352', 'genxiaogu@qq.cn');
INSERT INTO `student` VALUES ('stu-63b9f9', '20160000', '888888', '欧筠', '女', '艺术学院', '音乐学', '音乐16102', '15704528973', 'wv93qhck@yahoo.com');
INSERT INTO `student` VALUES ('stu-8ce50f', '20160002', '888888', '方媛', '女', '文史与法学学院', '新闻学', '新闻16103', '15308751969', 'mtet3p1@qq.com');
INSERT INTO `student` VALUES ('stu-a21514', '20160004', '888888', '扶伯振', '男', '文史与法学学院', '法学', '法律16101', '13703186748', 'mr6c0snzj@aol.com');
INSERT INTO `student` VALUES ('stu-c0726e', '20160007', '888888', '伍鸣志', '男', '计算机与电气工程学院', '软件工程', '软工16102', '15301796328', 're8b29@yahoo.com');
INSERT INTO `student` VALUES ('stu-c26144', '20160003', '888888', '谯琼晓', '女', '机械工程学院', '自动化', '自动16102', '13608251056', 'umowy14@sohu.com');
INSERT INTO `student` VALUES ('stu-c2dade', '20160010', '888888', '董悦', '女', '计算机科学与技术', '计算机科学与技术', '计科16102', '12445364534', 'geneg@113.com');
INSERT INTO `student` VALUES ('stu-cf3fab', '20160001', '888888', '叔亚', '女', '计算机与电气工程学院', '软件工程', '软工16101', '13305214328', '54tkeos@googlemail.com');
INSERT INTO `student` VALUES ('stu-cfe930', '20160009', '888888', '董安才', '男', '经济与管理学院', '会计学', '会计16101', '15608882690', 'm2kz18k@sohu.com');
INSERT INTO `student` VALUES ('stu-e7bc35', '20160008', '888888', '梁明强', '男', '计算机与电气工程学院', '计算机科学与技术', '计科16101', '15207876834', '5taix55e@163.com');
INSERT INTO `student` VALUES ('stu-vsd3df', '20160011', '888888', '王晴', '女', '计算机科学与技术', '计算机科学与技术', '计科16101', '13465789867', 'thyrr@127.com');
COMMIT;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teacher_id` varchar(25) COLLATE utf8_bin NOT NULL,
  `login_name` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '教师登录账号--工号',
  `teacher_pwd` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '登录密码',
  `teacher_name` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '教师姓名',
  `college` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '教师所属学院',
  `sex` varchar(5) COLLATE utf8_bin NOT NULL COMMENT '性别',
  `phone` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '电话',
  `email` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '邮箱',
  `teacher_post` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '职务',
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of teacher
-- ----------------------------
BEGIN;
INSERT INTO `teacher` VALUES ('tea-04487f', 'huast2009', '888888', '易翰', '文史与法学学院', '男', '13807130238', 'xno1ywo@ask.com', '教授');
INSERT INTO `teacher` VALUES ('tea-22fb4a', 'huast2008', '888888', '林毅', '艺术学院', '男', '13404543705', '2uub75@mail.com', '教授');
INSERT INTO `teacher` VALUES ('tea-2d9f7d', 'huast2007', '888888', '盖冠炎', '外国语学院', '男', '15504270029', 'p8xpeg7jd@aol.com', '教研组长');
INSERT INTO `teacher` VALUES ('tea-375062', 'huast2012', '888888', '邴和功', '艺术学院', '男', '13101314154', 't8oyrin@163.com', '教授');
INSERT INTO `teacher` VALUES ('tea-431c64', 'huas3001', '888888', '武安国', '艺传学院', '男', '12343246758', '1123@qq.com', '组长');
INSERT INTO `teacher` VALUES ('tea-518754', 'huast2011', '888888', '程泽博', '经济与管理学院', '男', '15802600398', 'wzje2xxuf@ask.com', '教研组长');
INSERT INTO `teacher` VALUES ('tea-51dc10', 'huast2017', '888888', '周江', '经济与管理学院', '男', '15202158347', 'cauuj4fi3@163.net', '教师');
INSERT INTO `teacher` VALUES ('tea-583bdd', 'huast2019', '888888', '木山', '外国语学院', '男', '15100933430', 'dfgptyiz@gmail.com', '教授');
INSERT INTO `teacher` VALUES ('tea-58f0cd', 'huast2004', '888888', '郭晶 ', '计算机与电气工程学院', '女', '13002350401', 'c95ntjsx@aol.com', '教研组长');
INSERT INTO `teacher` VALUES ('tea-62716c', 'huast2006', '888888', '程姣', '机械工程学院', '女', '13802131598', 'dryxqxr@ask.com', '教研组长');
INSERT INTO `teacher` VALUES ('tea-676d84', 'huast2016', '888888', '虞航', '经济与管理学院', '男', '15704037147', 'wue1ny@qql.com', '教研组长');
INSERT INTO `teacher` VALUES ('tea-877d46', 'huast2003', '888888', '叔华娥', '文史与法学学院', '女', '15902567128', 'ezqs8gq@sina.com', '教师');
INSERT INTO `teacher` VALUES ('tea-9167bb', 'huast2005', '888888', '第佳卿', '外国语学院', '女', '15901247112', 'mcf2tb@163.com', '教授');
INSERT INTO `teacher` VALUES ('tea-a0a682', 'huast2001', '888888', '乔荣璐', '艺术学院', '女', '15208712764', 'vgk5k0g@yahoo.cn', '教研组长');
INSERT INTO `teacher` VALUES ('tea-a20dad', 'huas3001', '888888', '李林', '计算机科学与技术', '男', '187573565427', 'ewrww@126.com', '教师');
INSERT INTO `teacher` VALUES ('tea-a24709', 'huast2015', '888888', '佘子善', '外国语学院', '男', '15306847001', '4aheyr1@sina.com', '教师');
INSERT INTO `teacher` VALUES ('tea-b134fd', 'huast2020', '888888', '王旭', '文史与法学学院', '男', '16735629867', 'ensuxi@163.com', '教师');
INSERT INTO `teacher` VALUES ('tea-c5151d', 'huast2010', '888888', '向全航', '艺术学院', '男', '15303898287', 'vynhzg@gmail.com', '教授');
INSERT INTO `teacher` VALUES ('tea-c517a0', 'huast2002', '888888', '侯筠', '机械工程学院', '女', '13806831633', 'vo40flby2@126.com', '教授');
INSERT INTO `teacher` VALUES ('tea-ca05d3', 'huast2000', '888888', '段蓓晓', '外国语学院', '女', '13505434636', 'tdna69@gmail.com', '教研组长');
INSERT INTO `teacher` VALUES ('tea-e6faa7', 'huast2013', '888888', '巴龙', '文史与法学学院', '男', '15301171171', 't5nbmv@aol.com', '教研组长');
INSERT INTO `teacher` VALUES ('tea-fed836', 'huast2014', '888888', '百新士', '计算机与电气工程学院', '男', '13801483561', 'xewhur8@126.com', '教师');
COMMIT;

-- ----------------------------
-- Table structure for thesis
-- ----------------------------
DROP TABLE IF EXISTS `thesis`;
CREATE TABLE `thesis` (
  `thesis_id` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '课题id',
  `thesis_name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '课题名称',
  `thesis_college` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '所属学院',
  `thesis_type` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '课题类型',
  `thesis_from` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '课题来源',
  `classroom` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '教研室',
  `teacher` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '指导教师',
  `model` varchar(25) COLLATE utf8_bin NOT NULL COMMENT '选题模式',
  `allow_special` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '可选专业',
  `thesis_date` datetime NOT NULL COMMENT '申报日期',
  `thesis_doc` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '附件',
  `thesis_desc` text COLLATE utf8_bin NOT NULL COMMENT '简述',
  `student` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '所属学生',
  `ischoose` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '是否被选',
  PRIMARY KEY (`thesis_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of thesis
-- ----------------------------
BEGIN;
INSERT INTO `thesis` VALUES ('sis-0b7328', '基于web的物流管理系统', '计算机与电气工程学院', '工程工业设计', '生产/社会实践', NULL, '郭晶', '学生自拟', '', '2020-05-05 14:50:47', 'http://192.168.1.3/group1/M00/00/00/wKgBA16xDL6AYKiGAAAsODodPHQ64.docx', '基于web的物流管理系统', '王晴', NULL);
INSERT INTO `thesis` VALUES ('sis-2e8f66', '人民币升值 的利与弊', '经济与管理学院', '应用研究', '教师科研课题', NULL, '虞航', '师生互选课题', '经济与管理学院(全部专业)', '2020-05-04 22:09:01', '', '人民币升值 的利与弊', NULL, NULL);
INSERT INTO `thesis` VALUES ('sis-35d847', '基于Jsp的课程在线学习系统', '计算机与电气工程学院', '工程工业设计', '生产/社会实践', NULL, '郭晶 ', '师生互选课题', '计算机与电气工程学院(全部专业)', '2020-05-04 20:57:06', '', '基于Jsp的课程在线学习系统', NULL, 'yes');
INSERT INTO `thesis` VALUES ('sis-3ca501', '论民法上的正当防卫', '文史与法学学院', '理论研究', '其他', NULL, '易翰', '师生互选课题', '文史与法学学院(全部专业)', '2020-04-30 14:51:56', 'http://192.168.1.3/group1/M00/00/00/wKgBA16qdQaAP9SlAAAsODodPHQ45.docx', '关于民法研究以及如何进行正当防卫', NULL, NULL);
INSERT INTO `thesis` VALUES ('sis-4362b5', '基于Java的博客系统', '计算机与电气工程学院', '工程工业设计', '生产/社会实践', NULL, '郭晶 ', '师生互选课题', '计算机与电气工程学院(全部专业)', '2020-05-04 20:25:30', '', '基于Java的博客系统', NULL, 'yes');
INSERT INTO `thesis` VALUES ('sis-5b3896', '浅谈汉语与英语的优缺', '外国语学院', '理论研究', '其他', NULL, '木山', '师生互选课题', '外国语学院(全部专业)', '2020-05-04 22:06:18', '', '浅谈汉语与英语的优缺', NULL, NULL);
INSERT INTO `thesis` VALUES ('sis-732be5', '基于安卓的视频播放器的设计与实现', '计算机与电气工程学院', '工程工业设计', '生产/社会实践', NULL, '郭晶 ', '师生互选课题', '计算机与电气工程学院(全部专业)', '2020-05-05 14:14:44', 'http://192.168.1.3/group1/M00/00/00/wKgBA16xBEmASIznAAAsODodPHQ45.docx', '基于安卓的视频播放器的设计与实现', NULL, 'yes');
INSERT INTO `thesis` VALUES ('sis-997cdb', '欧洲中世纪应用英语的状况研究', '外国语学院', '应用研究', '教师科研课题', NULL, '木山', '师生互选课题', '外国语学院(全部专业)', '2020-05-04 22:05:03', '', '欧洲中世纪应用英语的状况研究', NULL, NULL);
INSERT INTO `thesis` VALUES ('sis-b54a8d', '商务英语的应用', '外国语学院', '应用研究', '教师科研课题', NULL, '木山', '师生互选课题', '外国语学院(全部专业)', '2020-05-04 22:06:51', '', '商务英语的应用', NULL, NULL);
INSERT INTO `thesis` VALUES ('sis-d41741', '金融危机对社会经济发展的影响', '经济与管理学院', '应用研究', '教师科研课题', NULL, '虞航', '师生互选课题', '经济与管理学院(全部专业)', '2020-05-04 22:08:31', '', '金融危机对社会经济发展的影响', NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
