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
-- Table structure for table `item_img`
--

DROP TABLE IF EXISTS `item_img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_img` (
  `item_img_id` bigint NOT NULL,
  `reg_time` date DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `modified_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `img_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ori_img_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `repimg_yn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `item_id` bigint DEFAULT NULL,
  PRIMARY KEY (`item_img_id`),
  KEY `FKdd5u08y3ap4c46ayrqjf8g88m` (`item_id`),
  CONSTRAINT `FKdd5u08y3ap4c46ayrqjf8g88m` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_img`
--

LOCK TABLES `item_img` WRITE;
/*!40000 ALTER TABLE `item_img` DISABLE KEYS */;
INSERT INTO `item_img` VALUES (6,'2022-10-11','2022-10-11','donator','donator','07a8e94f-0b43-470d-8f3c-7ebc24cb81e7.jpg','/images/item/07a8e94f-0b43-470d-8f3c-7ebc24cb81e7.jpg','00f221ad-f5e6-4e5a-bbfe-e8ea6acc3afd.jpg','Y',5),(94,'2022-10-14','2022-10-14','donator@naver.com','donator@naver.com','c5a6df36-ec8b-4cfc-80ce-feada0cd6248.PNG','/images/item/c5a6df36-ec8b-4cfc-80ce-feada0cd6248.PNG','a258ce01-e03d-4bac-9d11-8484839a85a4.PNG','Y',93),(168,'2022-10-17','2022-10-17','donator@naver.com','donator@naver.com','b906c618-d44d-4347-8dfa-47f02f385eb6.jpg','/images/item/b906c618-d44d-4347-8dfa-47f02f385eb6.jpg','360b529a-fa51-44ab-961b-a1de8fef84bc.jpg','Y',167),(170,'2022-10-17','2022-10-17','donator@naver.com','donator@naver.com','61d46b1d-ad76-4bfd-b57d-c34497db7f53.JPG','/images/item/61d46b1d-ad76-4bfd-b57d-c34497db7f53.JPG','a18a2e05-c38f-4f7c-92a5-2477eb85216c.JPG','Y',169),(181,'2022-10-17','2022-10-18','both123@naver.com','both123@naver.com','e4431d17-e190-4903-a234-b6b8862a4ced.JPG','/images/item/e4431d17-e190-4903-a234-b6b8862a4ced.JPG','a18a2e05-c38f-4f7c-92a5-2477eb85216c.JPG','Y',180),(756,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','23e1757e-9f80-471b-acb3-638743edbf5e.jpg','/images/item/23e1757e-9f80-471b-acb3-638743edbf5e.jpg','00f221ad-f5e6-4e5a-bbfe-e8ea6acc3afd.jpg','Y',755),(765,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','263765b8-446f-49df-854e-421d09da081d.jpg','/images/item/263765b8-446f-49df-854e-421d09da081d.jpg','00f221ad-f5e6-4e5a-bbfe-e8ea6acc3afd.jpg','Y',764),(767,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','c3257aa4-202b-4249-9903-ac627e5411ca.jpg','/images/item/c3257aa4-202b-4249-9903-ac627e5411ca.jpg','00f221ad-f5e6-4e5a-bbfe-e8ea6acc3afd.jpg','Y',766),(769,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','9f704256-7ea0-48d6-bd94-537e6665ae54.JPG','/images/item/9f704256-7ea0-48d6-bd94-537e6665ae54.JPG','e4431d17-e190-4903-a234-b6b8862a4ced.JPG','Y',768),(771,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','cf2891c4-33ba-4dc6-9647-2ef5bfb40d24.jpg','/images/item/cf2891c4-33ba-4dc6-9647-2ef5bfb40d24.jpg','00f221ad-f5e6-4e5a-bbfe-e8ea6acc3afd.jpg','Y',770),(773,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','12679260-a31a-4d5c-aa47-3ccd8d229d8e.JPG','/images/item/12679260-a31a-4d5c-aa47-3ccd8d229d8e.JPG','636705f8-dd56-4801-9a7d-d87c21cfe8a9.JPG','Y',772),(776,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','65864781-fa95-4455-9252-7b69c5bad85a.jpg','/images/item/65864781-fa95-4455-9252-7b69c5bad85a.jpg','00f221ad-f5e6-4e5a-bbfe-e8ea6acc3afd.jpg','Y',775),(778,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','66163f6d-1629-4be2-92ba-c88caaf68622.jpg','/images/item/66163f6d-1629-4be2-92ba-c88caaf68622.jpg','00f221ad-f5e6-4e5a-bbfe-e8ea6acc3afd.jpg','Y',777),(780,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','56005126-ceea-4d98-825f-a6e28bb6f8ac.JPG','/images/item/56005126-ceea-4d98-825f-a6e28bb6f8ac.JPG','636705f8-dd56-4801-9a7d-d87c21cfe8a9.JPG','Y',779),(784,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','5580bc4c-82eb-4e30-99d7-5560cc2b33c8.JPG','/images/item/5580bc4c-82eb-4e30-99d7-5560cc2b33c8.JPG','636705f8-dd56-4801-9a7d-d87c21cfe8a9.JPG','Y',783),(786,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','e238258f-ac63-4720-9e07-4b55b87bd2a6.JPG','/images/item/e238258f-ac63-4720-9e07-4b55b87bd2a6.JPG','e4431d17-e190-4903-a234-b6b8862a4ced.JPG','Y',785),(788,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','456d25f5-6ffd-4cb3-9b68-f46d753db572.jpg','/images/item/456d25f5-6ffd-4cb3-9b68-f46d753db572.jpg','00f221ad-f5e6-4e5a-bbfe-e8ea6acc3afd.jpg','Y',787),(790,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','ac231cd7-229d-4554-a027-f61d13b05d5f.jpg','/images/item/ac231cd7-229d-4554-a027-f61d13b05d5f.jpg','360b529a-fa51-44ab-961b-a1de8fef84bc.jpg','Y',789),(792,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','ff9fc521-9cf0-44fc-800e-104a0460ca69.JPG','/images/item/ff9fc521-9cf0-44fc-800e-104a0460ca69.JPG','e4431d17-e190-4903-a234-b6b8862a4ced.JPG','Y',791),(794,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','44193884-c7ea-4a92-8178-86f7ef3956d8.JPG','/images/item/44193884-c7ea-4a92-8178-86f7ef3956d8.JPG','e4431d17-e190-4903-a234-b6b8862a4ced.JPG','Y',793),(796,'2022-10-20','2022-10-20','donator@naver.com','donator@naver.com','cbe1dd56-aabe-4821-8cb9-ff7ff1d6500f.JPG','/images/item/cbe1dd56-aabe-4821-8cb9-ff7ff1d6500f.JPG','12679260-a31a-4d5c-aa47-3ccd8d229d8e.JPG','Y',795);
/*!40000 ALTER TABLE `item_img` ENABLE KEYS */;
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
