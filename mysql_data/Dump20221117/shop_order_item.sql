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
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `order_item_id` bigint NOT NULL,
  `reg_time` date DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  `created_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `modified_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `count` int NOT NULL,
  `item_id` bigint DEFAULT NULL,
  `order_id` bigint DEFAULT NULL,
  PRIMARY KEY (`order_item_id`),
  KEY `FKija6hjjiit8dprnmvtvgdp6ru` (`item_id`),
  KEY `FKt4dc2r9nbvbujrljv3e23iibt` (`order_id`),
  CONSTRAINT `FKija6hjjiit8dprnmvtvgdp6ru` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`),
  CONSTRAINT `FKt4dc2r9nbvbujrljv3e23iibt` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (27,'2022-10-11','2022-10-11','donator','donator',1,5,26),(81,'2022-10-11','2022-10-11','tor11@naver.com','tor11@naver.com',1,5,80),(83,'2022-10-11','2022-10-11','tor11@naver.com','tor11@naver.com',1,5,82),(88,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com',1,5,87),(92,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com',3,5,91),(97,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com',1,93,96),(99,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com',1,93,98),(101,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com',1,93,100),(104,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com',1,5,103),(106,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com',1,93,105),(108,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com',1,93,107),(110,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com',1,93,109),(112,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com',1,93,111),(114,'2022-10-14','2022-10-14','tee2@naver.com','tee2@naver.com',1,93,113),(158,'2022-10-15','2022-10-15','tee2@naver.com','tee2@naver.com',2,93,156),(186,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',5,180,185),(190,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,169,189),(194,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,193),(204,'2022-10-17','2022-10-17','donatee@naver.com','donatee@naver.com',1,180,202),(209,'2022-10-17','2022-10-17','donatee@naver.com','donatee@naver.com',1,180,208),(210,'2022-10-17','2022-10-17','donatee@naver.com','donatee@naver.com',1,5,208),(212,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',5,180,211),(216,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,169,215),(217,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',4,180,215),(220,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,219),(223,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,222),(226,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',2,180,225),(229,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,228),(232,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,231),(235,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,234),(238,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,237),(240,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,239),(245,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',2,180,244),(249,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,247),(271,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,270),(279,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,278),(282,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',2,180,281),(285,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,284),(301,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,300),(304,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',2,180,303),(307,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,306),(310,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,309),(315,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,313),(322,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,320),(326,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,325),(329,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,328),(332,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,331),(335,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,334),(338,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,337),(341,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',3,180,340),(344,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,343),(347,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,346),(350,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,349),(353,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,352),(356,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,355),(360,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,359),(364,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,363),(368,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,367),(371,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',3,180,370),(374,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',3,180,373),(377,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',2,180,376),(382,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,180,380),(384,'2022-10-17','2022-10-17','both123@naver.com','both123@naver.com',1,93,383),(392,'2022-10-18','2022-10-18','donatee@naver.com','donatee@naver.com',1,180,391),(407,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,406),(410,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,409),(413,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,412),(416,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,415),(421,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',6,180,420),(426,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,425),(428,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',3,180,427),(431,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,430),(434,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,433),(437,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,436),(441,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',2,180,440),(445,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',2,180,444),(446,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,169,444),(456,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,455),(459,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,458),(463,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,462),(466,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,465),(469,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,468),(473,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,472),(505,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,504),(510,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,509),(514,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,512),(519,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,517),(523,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,522),(526,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,525),(530,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,169,529),(535,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',2,180,533),(539,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,538),(542,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,541),(545,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,544),(549,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,547),(553,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,552),(557,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,555),(562,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,560),(566,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,564),(573,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,572),(590,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,588),(597,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,596),(600,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,599),(603,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,602),(608,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,607),(611,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,610),(614,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,613),(617,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,616),(620,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,619),(626,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,625),(635,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,634),(638,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,637),(641,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,640),(644,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,643),(649,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,648),(652,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,651),(655,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,654),(658,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,657),(667,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,666),(670,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,669),(673,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,672),(681,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,180,680),(687,'2022-10-18','2022-10-18','both123@naver.com','both123@naver.com',1,169,685),(734,'2022-10-19','2022-10-19','both123@naver.com','both123@naver.com',1,180,733),(738,'2022-10-19','2022-10-19','both123@naver.com','both123@naver.com',1,180,737),(743,'2022-10-19','2022-10-19','both123@naver.com','both123@naver.com',2,180,742),(747,'2022-10-19','2022-10-19','both123@naver.com','both123@naver.com',1,180,746),(752,'2022-10-19','2022-10-19','both123@naver.com','both123@naver.com',2,180,751);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
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
