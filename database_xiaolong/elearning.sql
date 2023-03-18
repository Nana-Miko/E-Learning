-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: elearning
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `black_board`
--

DROP TABLE IF EXISTS `black_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `black_board` (
  `id` int NOT NULL AUTO_INCREMENT,
  `text` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `black_board_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `black_board`
--

LOCK TABLES `black_board` WRITE;
/*!40000 ALTER TABLE `black_board` DISABLE KEYS */;
INSERT INTO `black_board` VALUES (1,'opipo'),(2,'你好'),(3,'今天维护！！'),(4,'今天维护了！！！');
/*!40000 ALTER TABLE `black_board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `t_id` int DEFAULT NULL COMMENT '老师的id',
  `score` int NOT NULL COMMENT '学分',
  PRIMARY KEY (`id`),
  UNIQUE KEY `course_id_uindex` (`id`),
  KEY `fk_course_user` (`t_id`),
  CONSTRAINT `fk_course_user` FOREIGN KEY (`t_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (2,'数据结构',15,5),(3,'数据库',15,1),(4,'黑神话',20,10);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_select`
--

DROP TABLE IF EXISTS `course_select`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_select` (
  `c_id` int NOT NULL COMMENT '课程id',
  `s_id` int DEFAULT NULL COMMENT '学生id',
  KEY `fk_course_select_user` (`s_id`),
  KEY `fk_course_select_course` (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_select`
--

LOCK TABLES `course_select` WRITE;
/*!40000 ALTER TABLE `course_select` DISABLE KEYS */;
INSERT INTO `course_select` VALUES (2,15),(2,17),(18,0),(18,0),(18,0),(4,18),(4,18),(4,18),(4,18),(4,18),(4,18),(4,18),(2,18),(2,18),(4,18),(4,18),(2,18),(2,18),(2,18),(2,18),(3,18),(3,18),(3,18),(3,18),(4,18),(4,18);
/*!40000 ALTER TABLE `course_select` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `t_id` int NOT NULL COMMENT '老师id',
  `s_id` int NOT NULL COMMENT '学生id',
  `message` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `send` int DEFAULT NULL COMMENT '发布方，1是老师，2是学生',
  PRIMARY KEY (`id`),
  UNIQUE KEY `message_id_uindex` (`id`),
  KEY `fk_message_user_t_id` (`t_id`),
  KEY `fk_message_user_s_id` (`s_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (2,15,17,'123',1),(3,15,17,'可以啊',1),(4,15,17,'ok',1),(5,15,17,'asd',1),(6,15,17,'你好',1),(7,15,17,'okok',1),(8,15,17,'？',1),(9,15,17,'ok',1),(10,15,17,'老师你好么',1),(11,15,17,'五五',1),(12,15,17,'？',1),(13,15,17,'呃呃',1),(14,15,17,'你好',1),(15,15,17,'擦掉',1),(16,15,17,'呃呃',1),(17,15,17,'okok',1),(18,15,17,'asd',1),(19,15,17,'啊搜',1),(20,15,17,'？？？？',1),(21,15,17,'呃呃',1),(22,15,17,'出来！',1),(23,15,17,'华东',1),(24,15,17,'test',1),(25,15,17,'ok',1),(26,15,17,'老师你好',2),(27,15,17,'dsadsa',1),(28,15,17,'大苏打撒',1);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
  `c_id` int DEFAULT NULL COMMENT '课程id',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '题目名称',
  `A` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `B` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `C` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `D` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `right` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '正确的答案',
  `time` mediumtext NOT NULL,
  KEY `fk_task_course` (`c_id`),
  CONSTRAINT `fk_task_course` FOREIGN KEY (`c_id`) REFERENCES `course` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='考试表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (2,'啊实打实的','asdasd','asdsa','asdasdas','asdasd','D','1678983982137'),(2,'阿嘎','大概','撒旦给','爱的故事','撒旦给','D','1679031316192'),(2,'阿桑的歌大概','附加费公积金','飞机函数返回','的是否合适','的水分挥发的是','C','1679031316192');
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_do`
--

DROP TABLE IF EXISTS `task_do`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task_do` (
  `s_id` int NOT NULL COMMENT '学生id',
  `s_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学生姓名',
  `c_id` int NOT NULL COMMENT '课程号',
  `score` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `time` mediumtext NOT NULL COMMENT '对应试题的time',
  `finish_time` mediumtext NOT NULL COMMENT '完成时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_do`
--

LOCK TABLES `task_do` WRITE;
/*!40000 ALTER TABLE `task_do` DISABLE KEYS */;
INSERT INTO `task_do` VALUES (12,'小明',2,'25/60','1678983982137','1678983999545');
/*!40000 ALTER TABLE `task_do` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `psw` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` int NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  UNIQUE KEY `user_id_uindex` (`id`),
  UNIQUE KEY `user_no_uindex` (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'123456','123456',0,'管理员'),(15,'112233','123456',1,'小明'),(17,'xs','123456',2,'李四'),(18,'zxl','123',2,'zxl'),(20,'tea','123456',1,'王老师'),(21,'987','123456',2,'okk');
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

-- Dump completed on 2023-03-18 19:32:43
