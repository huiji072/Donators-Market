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
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `item_id` bigint NOT NULL,
  `reg_time` date DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `modified_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `item_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `item_nm` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `item_sell_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `stock_number` int NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (5,'2022-10-11','2022-10-17','donator','donatee@naver.com','test','test','SELL',97),(93,'2022-10-14','2022-10-17','donator@naver.com','both123@naver.com','test','test','SELL',80),(165,'2022-10-17','2022-10-17','donator@naver.com','donator@naver.com','ttt','te','SELL',34),(166,'2022-10-17','2022-10-17','donator@naver.com','donator@naver.com','gf','34','SELL',33),(167,'2022-10-17','2022-10-19','donator@naver.com','donator@naver.com','proxy tset ing./..','proxy Test','SELL',115),(169,'2022-10-17','2022-10-19','donator@naver.com','donator@naver.com','TEST','TEST','SELL',3),(180,'2022-10-17','2022-10-19','both123@naver.com','both123@naver.com','test','cartTest','SELL',31),(755,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','aaaa','aaaa','SELL',111),(764,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','1','1','SELL',1),(766,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','1111','1123','SELL',11),(768,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','aaa','aaa','SELL',111),(770,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','11','11','SELL',11),(772,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','22','222','SELL',222),(775,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','333','333','SELL',333),(777,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','333','333','SELL',333),(779,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','aaa','aaa','SELL',123),(783,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','d','d','SELL',2),(785,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','11','11','SELL',1),(787,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','5','5','SELL',5),(789,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','33','123','SELL',123),(791,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','234','234','SELL',234),(793,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','s3','s3','SELL',3),(795,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','ds','ggg','SELL',34);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
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

-- Dump completed on 2022-11-17 15:26:49
