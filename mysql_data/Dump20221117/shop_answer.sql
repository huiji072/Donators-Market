-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: database-1.cdy4xklunoyt.ap-northeast-2.rds.amazonaws.com    Database: shop
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `reg_time` date DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `modified_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `depth` int DEFAULT NULL,
  `group_id` int DEFAULT NULL,
  `item_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmd5my8n6cqajta2wolky3v1x0` (`item_id`),
  CONSTRAINT `FKmd5my8n6cqajta2wolky3v1x0` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (1,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com','ㅎㅇ',0,1,5),(2,'2022-10-14','2022-10-14','donator@naver.com','donator@naver.com','adaf',0,2,5),(3,'2022-10-14','2022-10-14','donator@naver.com','donator@naver.com','fdfd',0,3,93),(4,'2022-10-14','2022-10-14','donator@naver.com','donator@naver.com','fdfd',1,3,93),(5,'2022-10-14','2022-10-14','donator@naver.com','donator@naver.com','',0,5,5),(6,'2022-10-14','2022-10-14','donator@naver.com','donator@naver.com','fff',1,2,5),(7,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com','fdfd',1,5,5),(8,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com','as',0,8,93),(9,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com','fd',1,8,93),(10,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com','fd',1,8,93),(11,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com','fdfd',1,8,93),(12,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com','fd',1,8,93),(13,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com','fd',1,3,93),(14,'2022-10-17','2022-10-17','donator@naver.com','donator@naver.com','fd',1,3,93),(15,'2022-10-17','2022-10-17','donator@naver.com','donator@naver.com','aaaaaaaaaaaaa',1,3,93),(16,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com','ㅁㅁㅁ',0,16,167),(17,'2022-10-17','2022-10-17','donatee@naver.com','donatee@naver.com','fdfd',0,17,180),(18,'2022-10-19','2022-10-19','gmlwl0720@naver.com','gmlwl0720@naver.com','ㅇㄹㅇㄹ',0,18,5),(19,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','ffff',0,19,5),(20,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','ff',0,20,5),(21,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','gf',0,21,5),(22,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','gfg',0,22,5),(23,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','gfgf',0,23,5);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-17 15:26:54
