-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.14

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

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `o_id` int(10) unsigned NOT NULL,
  `o_order_status` enum('ACCEPTED','CONFIRMED','PREPARED','MOVING','RECEIVED','CANCELLED') NOT NULL DEFAULT 'ACCEPTED',
  `o_detail` varchar(100) DEFAULT NULL,
  `o_payment_type` enum('CASH','CARD') NOT NULL DEFAULT 'CARD',
  `o_card_credential` varchar(50) DEFAULT NULL,
  `o_delivery_type` enum('EXW','CPT','FCA','DDP') NOT NULL DEFAULT 'EXW',
  `o_delivery_address` varchar(80) NOT NULL,
  `o_user_id` int(10) unsigned NOT NULL,
  `o_creation_date` timestamp NOT NULL,
  PRIMARY KEY (`o_id`),
  UNIQUE KEY `o_id_UNIQUE` (`o_id`),
  KEY `FK_user_id_idx` (`o_user_id`),
  CONSTRAINT `FK_user_id` FOREIGN KEY (`o_user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (35147,'ACCEPTED',NULL,'CASH',NULL,'DDP','Украина, г. Киев, ул. В.Панасовская 15, 61000',5290,'2022-10-15 11:26:13'),(80496,'ACCEPTED',NULL,'CASH',NULL,'DDP','Ukraine, Kyiv, V.Panasivska st. 15, 61000',5290,'2022-10-15 09:35:53'),(85114,'ACCEPTED',NULL,'CASH',NULL,'DDP','Украина, г. Киев, ул. В.Панасовская 15, 61000',5290,'2022-10-15 12:52:06');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-15 16:30:49
