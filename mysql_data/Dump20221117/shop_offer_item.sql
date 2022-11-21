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
-- Table structure for table `offer_item`
--

DROP TABLE IF EXISTS `offer_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offer_item` (
  `offer_item_id` bigint NOT NULL,
  `reg_time` date DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `modified_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `count` int NOT NULL,
  `item_id` bigint DEFAULT NULL,
  `offer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`offer_item_id`),
  KEY `FK7qw9s6lo4qt1oe706eey3xj4e` (`item_id`),
  KEY `FKfv8rsqmdj63y7fnkgvthevrk8` (`offer_id`),
  CONSTRAINT `FK7qw9s6lo4qt1oe706eey3xj4e` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`),
  CONSTRAINT `FKfv8rsqmdj63y7fnkgvthevrk8` FOREIGN KEY (`offer_id`) REFERENCES `offers` (`offer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offer_item`
--

LOCK TABLES `offer_item` WRITE;
/*!40000 ALTER TABLE `offer_item` DISABLE KEYS */;
INSERT INTO `offer_item` VALUES (29,'2022-10-11','2022-10-11','donator','donator',1,5,28),(116,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com',1,93,115),(118,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com',1,93,117),(120,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com',1,93,119),(122,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com',1,93,121),(124,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com',1,93,123),(126,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com',1,93,125),(130,'2022-10-14','2022-10-14','donator@naver.com','donator@naver.com',1,93,128),(133,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com',1,93,131),(134,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com',1,93,132),(138,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com',1,93,136),(141,'2022-10-15','2022-10-15','tee2@naver.com','tee2@naver.com',1,93,139),(145,'2022-10-15','2022-10-15','tee2@naver.com','tee2@naver.com',5,5,143),(146,'2022-10-15','2022-10-15','tee2@naver.com','tee2@naver.com',5,5,144),(149,'2022-10-15','2022-10-15','tee2@naver.com','tee2@naver.com',1,93,147),(150,'2022-10-15','2022-10-15','tee2@naver.com','tee2@naver.com',1,93,148),(153,'2022-10-15','2022-10-15','tee2@naver.com','tee2@naver.com',1,93,151),(154,'2022-10-15','2022-10-15','tee2@naver.com','tee2@naver.com',1,93,152),(157,'2022-10-15','2022-10-15','tee2@naver.com','tee2@naver.com',2,93,155),(172,'2022-10-17','2022-10-17','donator@naver.com','donator@naver.com',1,169,171),(174,'2022-10-17','2022-10-17','donator@naver.com','donator@naver.com',1,169,173),(184,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,183),(188,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',5,180,187),(192,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,169,191),(196,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,195),(198,'2022-10-17','2022-10-17','donatee@naver.com','donatee@naver.com',1,180,197),(200,'2022-10-17','2022-10-17','donatee@naver.com','donatee@naver.com',1,180,199),(203,'2022-10-17','2022-10-17','donatee@naver.com','donatee@naver.com',1,180,201),(214,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',5,180,213),(242,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,241),(253,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,252),(257,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,255),(262,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,260),(268,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,266),(275,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,273),(288,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,287),(292,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,290),(297,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,295),(318,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,317),(386,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,93,385),(398,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,396),(404,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,402),(424,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,423),(471,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,470),(476,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,475),(479,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,478),(482,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,481),(485,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,484),(488,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,487),(491,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',2,180,490),(496,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,495),(499,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,498),(502,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,501),(561,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,559),(570,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,569),(576,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,575),(579,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,578),(585,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,583),(594,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,593),(629,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,628),(632,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,631),(661,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,660),(664,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,663),(677,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,675),(683,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,682),(686,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,169,684),(690,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com',1,180,689),(692,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com',1,169,691),(694,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com',1,169,693),(696,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com',1,180,695),(698,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com',1,167,697),(700,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com',1,180,699),(702,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com',1,180,701),(704,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com',1,167,703),(706,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com',1,180,705),(708,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com',1,167,707),(710,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com',1,167,709),(712,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com',1,167,711),(714,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com',1,169,713),(716,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com',1,180,715),(718,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com',1,167,717),(720,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com',1,180,719),(722,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com',1,167,721),(724,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com',1,180,723),(726,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com',1,167,725),(728,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com',1,180,727),(730,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com',1,180,729),(732,'2022-10-19','2022-10-19','donator@naver.com','donator@naver.com',1,169,731),(736,'2022-10-19','2022-10-19','both123@naver.com','both123@naver.com',1,180,735),(740,'2022-10-19','2022-10-19','both123@naver.com','both123@naver.com',1,180,739),(745,'2022-10-19','2022-10-19','both123@naver.com','both123@naver.com',2,180,744),(749,'2022-10-19','2022-10-19','both123@naver.com','both123@naver.com',1,180,748),(754,'2022-10-19','2022-10-19','both123@naver.com','both123@naver.com',2,180,753);
/*!40000 ALTER TABLE `offer_item` ENABLE KEYS */;
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

-- Dump completed on 2022-11-17 15:26:50
