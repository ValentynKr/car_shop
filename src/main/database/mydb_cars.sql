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
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cars` (
  `c_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `c_name` varchar(45) NOT NULL,
  `c_price` float unsigned NOT NULL,
  `c_mileage` int(10) unsigned NOT NULL,
  `c_engine_volume` float unsigned NOT NULL,
  `c_category` int(10) unsigned NOT NULL,
  `c_manufacturer` int(10) unsigned NOT NULL,
  `c_production_year` int(10) unsigned NOT NULL,
  `c_image_name` varchar(45) NOT NULL,
  PRIMARY KEY (`c_id`),
  UNIQUE KEY `c_id_UNIQUE` (`c_id`),
  UNIQUE KEY `c_image_name_UNIQUE` (`c_image_name`),
  KEY `c_category_idx` (`c_category`),
  KEY `c_manufacturer_idx` (`c_manufacturer`),
  CONSTRAINT `c_category` FOREIGN KEY (`c_category`) REFERENCES `c_category` (`ca_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `c_manufacturer` FOREIGN KEY (`c_manufacturer`) REFERENCES `c_manufacturer` (`m_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` VALUES (1,'Land Cruiser 300',152000,53000,3.5,5,1,2020,'carImage0001.png'),(12,'Mustang GT-S 400',15000,120500,5,10,8,2010,'carImage0012.png'),(233,'Land Cruiser 200',42000,150000,3.5,5,1,2015,'carImage0233.png'),(236,'Cabrio 320',35400,95500,3.2,7,5,2006,'carImage0236.png'),(934,'Carens',10000,200000,2.2,9,20,2008,'carImage934.png'),(1325,'105',5500,120000,0.9,6,18,2011,'carImage1325.png'),(1562,'H2',65000,132500,6,8,19,2015,'carImage1562.png'),(2666,'GLE 300 Coupe',120000,12500,3,4,2,2018,'carImage2666.png'),(3362,'308 SW',11000,163000,2,2,18,2007,'carImage3362.png'),(3626,'Sprinter S120',15000,203000,3,1,2,2000,'carImage3626.png'),(5551,'Fiesta GT',8500,210000,1.8,6,8,2009,'carImage5551.png'),(5623,'Forester',12300,85000,2.5,5,17,2013,'carImage5623.png'),(9999,'Model S',75200,32000,0,10,4,2018,'carImage9999.png');
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
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
