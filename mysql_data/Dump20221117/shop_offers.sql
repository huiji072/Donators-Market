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
-- Table structure for table `offers`
--

DROP TABLE IF EXISTS `offers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offers` (
  `offer_id` bigint NOT NULL,
  `reg_time` date DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `modified_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `offer_date` datetime(6) DEFAULT NULL,
  `offer_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `item_id` bigint DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`offer_id`),
  KEY `FKk1i6eubnulmr7m85427bdnbrm` (`item_id`),
  KEY `FKq08gysc35588s8qcm62egn9ps` (`member_id`),
  CONSTRAINT `FKk1i6eubnulmr7m85427bdnbrm` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`),
  CONSTRAINT `FKq08gysc35588s8qcm62egn9ps` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offers`
--

LOCK TABLES `offers` WRITE;
/*!40000 ALTER TABLE `offers` DISABLE KEYS */;
INSERT INTO `offers` VALUES (28,'2022-10-11','2022-10-11','donator','donator','2022-10-11 08:33:15.385359','Offer',NULL,1),(115,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com','2022-10-14 14:12:29.068703','Offer',NULL,85),(117,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com','2022-10-14 14:13:14.089678','Offer',NULL,85),(119,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com','2022-10-14 14:14:13.471091','Offer',NULL,85),(121,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com','2022-10-14 14:14:18.060675','Offer',NULL,85),(123,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com','2022-10-14 14:14:51.823325','Offer',NULL,85),(125,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com','2022-10-14 14:15:16.587778','Offer',NULL,85),(128,'2022-10-14','2022-10-14','donator@naver.com','donator@naver.com','2022-10-14 14:50:40.291262','Offer',NULL,3),(131,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com','2022-10-14 14:50:55.261634','Offer',NULL,85),(132,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com','2022-10-14 14:50:55.264085','Offer',NULL,85),(136,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com','2022-10-14 14:53:27.461845','Offer',NULL,85),(139,'2022-10-15','2022-10-15','tee2@naver.com','tee2@naver.com','2022-10-14 15:05:58.020507','Offer',NULL,3),(143,'2022-10-15','2022-10-15','tee2@naver.com','tee2@naver.com','2022-10-14 15:06:58.838222','Offer',NULL,1),(144,'2022-10-15','2022-10-15','tee2@naver.com','tee2@naver.com','2022-10-14 15:06:58.838222','Offer',NULL,1),(147,'2022-10-15','2022-10-15','tee2@naver.com','tee2@naver.com','2022-10-14 15:07:28.471280','Offer',NULL,3),(148,'2022-10-15','2022-10-15','tee2@naver.com','tee2@naver.com','2022-10-14 15:07:28.471280','Offer',NULL,3),(151,'2022-10-15','2022-10-17','tee2@naver.com','donator@naver.com','2022-10-14 15:08:04.070507','CANCEL',NULL,3),(152,'2022-10-15','2022-10-15','tee2@naver.com','tee2@naver.com','2022-10-14 15:08:04.070507','Offer',NULL,3),(155,'2022-10-15','2022-10-17','tee2@naver.com','donator@naver.com','2022-10-14 15:11:16.753409','CANCEL',NULL,3),(171,'2022-10-17','2022-10-17','donator@naver.com','donator@naver.com','2022-10-17 05:24:58.310304','CANCEL',NULL,3),(173,'2022-10-17','2022-10-17','donator@naver.com','donator@naver.com','2022-10-17 05:25:02.622645','CANCEL',NULL,3),(197,'2022-10-17','2022-10-17','donatee@naver.com','donatee@naver.com','2022-10-17 07:20:06.107996','Offer',NULL,175),(199,'2022-10-17','2022-10-17','donatee@naver.com','donatee@naver.com','2022-10-17 07:20:06.336696','Offer',NULL,175),(201,'2022-10-17','2022-10-17','donatee@naver.com','donatee@naver.com','2022-10-17 07:20:30.513757','Offer',NULL,175),(396,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 03:00:21.457058','Offer',NULL,175),(402,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 03:04:00.545221','Offer',NULL,175),(423,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 05:35:41.494697','Offer',NULL,175),(470,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 07:47:22.850395','Offer',NULL,175),(475,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 07:49:28.632484','Offer',NULL,175),(478,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 07:50:21.359205','Offer',NULL,175),(481,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 07:53:13.587743','Offer',NULL,175),(484,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 08:07:13.738537','Offer',NULL,175),(487,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 08:09:00.389314','Offer',NULL,175),(490,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 08:10:32.438646','Offer',NULL,175),(495,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 08:12:07.646973','Offer',NULL,175),(498,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 08:13:31.899468','Offer',NULL,175),(501,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 08:14:24.127881','Offer',NULL,175),(559,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 08:57:15.181087','Offer',NULL,175),(569,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 09:06:05.634412','Offer',NULL,175),(575,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 09:16:14.043194','Offer',NULL,175),(578,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 09:16:36.830975','Offer',NULL,175),(583,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 09:18:47.172530','Offer',NULL,175),(593,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 11:01:51.928600','Offer',NULL,175),(628,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 11:18:19.104748','Offer',NULL,175),(631,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 11:20:16.473522','Offer',NULL,175),(660,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 12:45:48.919445','Offer',NULL,175),(663,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 12:46:17.953775','Offer',NULL,175),(675,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 12:50:46.591516','Offer',NULL,175),(682,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 12:52:28.020815','Offer',NULL,175),(684,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com','2022-10-18 12:52:47.236408','Offer',NULL,3),(689,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','2022-10-19 01:35:48.128690','Offer',NULL,175),(691,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','2022-10-19 01:35:57.081751','Offer',NULL,3),(693,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','2022-10-19 01:41:14.436479','Offer',NULL,3),(695,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','2022-10-19 01:48:05.297275','Offer',NULL,175),(697,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','2022-10-19 01:48:15.693312','Offer',NULL,3),(699,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','2022-10-19 01:49:33.782149','Offer',NULL,175),(701,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','2022-10-19 01:50:20.543425','Offer',NULL,175),(703,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','2022-10-19 01:50:44.791456','Offer',NULL,3),(705,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','2022-10-19 01:52:47.788993','Offer',NULL,175),(707,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','2022-10-19 01:53:19.317784','Offer',NULL,3),(709,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','2022-10-19 01:53:23.304287','Offer',NULL,3),(711,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','2022-10-19 01:53:31.945214','Offer',NULL,3),(713,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','2022-10-19 01:55:59.304963','Offer',NULL,3),(715,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','2022-10-19 01:56:51.887511','Offer',NULL,175),(717,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','2022-10-19 01:57:00.437895','Offer',NULL,3),(719,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','2022-10-19 02:01:57.590103','Offer',NULL,175),(721,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','2022-10-19 02:02:04.439530','Offer',NULL,3),(723,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','2022-10-19 02:03:35.636043','Offer',NULL,175),(725,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','2022-10-19 02:03:52.086848','Offer',NULL,3),(727,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','2022-10-19 02:05:10.128989','Offer',NULL,175),(729,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','2022-10-19 02:07:01.710098','Offer',NULL,175),(731,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com','2022-10-19 02:07:08.977994','Offer',NULL,3),(735,'2022-10-19','2022-10-19','both123@naver.com','both123@naver.com','2022-10-19 02:15:32.043229','Offer',NULL,175),(739,'2022-10-19','2022-10-19','both123@naver.com','both123@naver.com','2022-10-19 02:24:08.090577','Offer',NULL,175),(744,'2022-10-19','2022-10-19','both123@naver.com','both123@naver.com','2022-10-19 02:24:27.190795','Offer',NULL,175),(748,'2022-10-19','2022-10-19','both123@naver.com','both123@naver.com','2022-10-19 02:31:01.025353','Offer',NULL,175),(753,'2022-10-19','2022-10-19','both123@naver.com','both123@naver.com','2022-10-19 02:31:18.274911','Offer',NULL,175);
/*!40000 ALTER TABLE `offers` ENABLE KEYS */;
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

-- Dump completed on 2022-11-17 15:26:52
