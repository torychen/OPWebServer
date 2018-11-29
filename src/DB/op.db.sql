-- MySQL dump 10.13  Distrib 5.7.18, for Win64 (x86_64)
--
-- Host: localhost    Database: op.db
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(20) DEFAULT '最新面试题' COMMENT '标题',
  `body` varchar(1000) NOT NULL COMMENT '你的问题',
  `answer` varchar(1000) DEFAULT NULL COMMENT '答案',
  `submitter` varchar(20) DEFAULT '小明' COMMENT '问题提交人',
  `modifier` varchar(20) DEFAULT '我不知道' COMMENT '交提答案的人',
  `datetime` datetime DEFAULT NULL,
  `language` varchar(10) DEFAULT 'common',
  `filter` varchar(10) DEFAULT NULL COMMENT '分类，如算法，数据结构，数据库',
  `company` varchar(20) DEFAULT NULL COMMENT '你懂的',
  `rate` int(1) DEFAULT '1' COMMENT '评分',
  `imgpath` varchar(256) DEFAULT NULL COMMENT '图片的路径，而不是图片自身',
  `heat` int(1) unsigned zerofill DEFAULT '1' COMMENT '最近被问到的次数',
  `syncflag` int(1) DEFAULT '0' COMMENT '同步标记1',
  `blame` char(1) DEFAULT '0' COMMENT '举报',
  `duplicate` int(1) DEFAULT '0' COMMENT '是否是重复的问题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'面试题','单链表插入节点','我不知道','小明','我不知道','2018-11-24 17:41:40','java',NULL,NULL,1,NULL,1,0,'0',0),(2,'面试题','Linux内存分配','我不知道','admin','我不知道','2018-11-24 17:41:47','java',NULL,NULL,1,NULL,1,0,'0',0),(3,'面试题','TCP 拥塞避免','我不知道','小明','我不知道','2018-11-24 17:48:38','java',NULL,NULL,1,NULL,1,0,'0',0),(11,'面试题','Fragment 为什么被称为第5大组件','我不知道','小明','我不知道','2018-11-25 22:22:59','java',NULL,NULL,1,NULL,1,0,'0',0),(12,'最新面试题','test','na','xiao ming','我不知道','2018-11-27 17:51:58','java',NULL,NULL,1,NULL,1,0,'0',0),(13,'最新面试题','这是一个新问题','na','xiao ming','我不知道','2018-11-27 18:06:41','java',NULL,NULL,1,NULL,1,0,'0',0),(14,'最新面试题','测试','na','xiao ming','我不知道','2018-11-28 10:53:05','java',NULL,NULL,1,NULL,1,0,'0',0),(15,'最新面试题','122345','na','xiao ming','我不知道','2018-11-28 10:53:54','java',NULL,NULL,1,NULL,1,0,'0',0),(16,'最新面试题','111','na','xiao ming','我不知道','2018-11-28 10:55:59','java',NULL,NULL,1,NULL,1,0,'0',0),(17,'最新面试题','111','na','xiao ming','我不知道','2018-11-28 10:59:56','java',NULL,NULL,1,NULL,1,0,'0',0),(18,'最新面试题','111','na','xiao ming','我不知道','2018-11-28 11:05:16','java',NULL,NULL,1,NULL,1,0,'0',0),(19,'最新面试题','111','na','xiao ming','我不知道','2018-11-28 11:06:05','java',NULL,NULL,1,NULL,1,0,'0',0),(20,'最新面试题','5455','na','xiao ming','我不知道','2018-11-28 11:16:19','java',NULL,NULL,1,NULL,1,0,'0',0),(21,'最新面试题','888','na','xiao ming','我不知道','2018-11-28 12:32:19','java',NULL,NULL,1,NULL,1,0,'0',0),(22,'最新面试题','111','na','xiao ming','我不知道','2018-11-28 17:31:04','java',NULL,NULL,1,NULL,1,0,'0',0),(23,'最新面试题','111','na','xiao ming','我不知道','2018-11-29 11:03:14','java',NULL,NULL,1,NULL,1,0,'0',0),(24,'最新面试题','222','na','xiao ming','我不知道','2018-11-29 11:03:21','java',NULL,NULL,1,NULL,1,0,'0',0),(25,'最新面试题','333','na','xiao ming','我不知道','2018-11-29 11:03:28','java',NULL,NULL,1,NULL,1,0,'0',0),(26,'最新面试题','444','na','xiao ming','我不知道','2018-11-29 11:03:37','java',NULL,NULL,1,NULL,1,0,'0',0),(27,'最新面试题','555','na','xiao ming','我不知道','2018-11-29 11:03:44','java',NULL,NULL,1,NULL,1,0,'0',0),(28,'最新面试题','666','na','xiao ming','我不知道','2018-11-29 11:03:52','java',NULL,NULL,1,NULL,1,0,'0',0),(29,'最新面试题','777','na','xiao ming','我不知道','2018-11-29 11:03:59','java',NULL,NULL,1,NULL,1,0,'0',0),(30,'最新面试题','888','na','xiao ming','我不知道','2018-11-29 11:04:07','java',NULL,NULL,1,NULL,1,0,'0',0),(31,'最新面试题','999','na','xiao ming','我不知道','2018-11-29 11:04:13','java',NULL,NULL,1,NULL,1,0,'0',0),(32,'最新面试题','测试大量文字\r\n测试大量文字\r\n测试大量文字\r\n测试大量文字\r\n测试大量文字\r\n测试大量文字\r\n测试大量文字\r\n测试大量文字\r\n测试大量文字\r\n测试大量文字\r\n测试大量文字\r\n测试大量文字\r\n测试大量文字','na','xiao ming','我不知道','2018-11-29 11:11:25','java',NULL,NULL,1,NULL,1,0,'0',0),(33,'最新面试题','qqq','na','xiao ming','我不知道','2018-11-29 13:55:59','java',NULL,NULL,1,NULL,1,0,'0',0);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT '桥墩' COMMENT '和question 表的 submitter 一一对应',
  `password` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'桥墩','123456'),(2,'guest',NULL),(3,'tester',NULL),(4,'test1','123'),(5,'test1','123'),(6,'test1','123'),(7,'test1','123'),(8,'test1','123'),(9,'test1','123'),(10,'test1','123');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-29 14:15:08
