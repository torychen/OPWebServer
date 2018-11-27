/*
MySQL Data Transfer
Source Host: localhost
Source Database: op.db
Target Host: localhost
Target Database: op.db
Date: 2018/11/26 23:52:14
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for question
-- ----------------------------
CREATE TABLE `question` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(20) DEFAULT '最新面试题' COMMENT '标题',
  `body` varchar(1000) NOT NULL COMMENT '你的问题',
  `answer` varchar(1000) DEFAULT NULL COMMENT '答案',
  `submitter` varchar(20) DEFAULT '小明' COMMENT '问题提交人',
  `modifier` varchar(20) DEFAULT '我不知道' COMMENT '交提答案的人',
  `datetime` datetime DEFAULT NULL,
  `language` varchar(10) DEFAULT 'java',
  `sort` varchar(10) DEFAULT NULL COMMENT '分类，如算法，数据结构，数据库',
  `company` varchar(20) DEFAULT NULL COMMENT '你懂的',
  `rate` int(1) DEFAULT '1' COMMENT '评分',
  `img` blob,
  `heat` int(1) unsigned zerofill DEFAULT '1' COMMENT '最近被问到的次数',
  `syncflag` int(1) DEFAULT '0' COMMENT '同步标记1',
  `blame` char(1) DEFAULT '0' COMMENT '举报',
  `duplicate` int(1) DEFAULT '0' COMMENT '是否是重复的问题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `question` VALUES ('1', '面试题', '单链表插入节点', '我不知道', '小明', '我不知道', '2018-11-24 17:41:40', 'java', null, null, '1', null, '1', '0', '0', '0');
INSERT INTO `question` VALUES ('2', '面试题', 'Linux内存分配', '我不知道', 'admin', '我不知道', '2018-11-24 17:41:47', 'java', null, null, '1', null, '1', '0', '0', '0');
INSERT INTO `question` VALUES ('3', '面试题', 'TCP 拥塞避免', '我不知道', '小明', '我不知道', '2018-11-24 17:48:38', 'java', null, null, '1', null, '1', '0', '0', '0');
INSERT INTO `question` VALUES ('11', '面试题', 'Fragment 为什么被称为第5大组件', '我不知道', '小明', '我不知道', '2018-11-25 22:22:59', 'java', null, null, '1', null, '1', '0', '0', '0');
