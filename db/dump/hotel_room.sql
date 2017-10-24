-- MySQL dump 10.13  Distrib 5.7.19, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: hotel
-- ------------------------------------------------------
-- Server version	5.7.19-0ubuntu0.16.04.1

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
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) NOT NULL,
  `id_type` int(11) NOT NULL,
  `size` tinyint(4) NOT NULL,
  `foto_address` varchar(45) NOT NULL,
  `person` tinyint(4) NOT NULL,
  `bed` tinyint(4) NOT NULL,
  `price` decimal(10,0) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `number_UNIQUE` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,101,2,12,'13232388_4_e (1).jpg',2,2,47),(2,201,2,12,'13232388_32_e.jpg',2,1,50),(3,301,2,12,'13232388_9_e.jpg',3,2,50),(4,102,3,26,'13232388_34_e.jpg',3,2,74),(5,202,3,34,'13232388_37_e.jpg',3,2,85),(6,302,2,25,'873b77ff_e.jpg',2,2,60),(7,103,3,50,'178ede34_e.jpg',2,1,112),(8,203,2,12,'f3533307_e.jpg',1,1,29),(9,303,2,24,'4254c884_e.jpg',2,2,33),(10,104,2,24,'b613f58c_e.jpg',1,1,41),(11,204,2,24,'1ce5ac72_e.jpg',2,1,45),(12,304,3,34,'53ac7675_e.jpg',4,3,68),(13,105,3,68,'e941ea1d_e.jpg',2,1,106),(14,205,3,50,'f2a08f15_e.jpg',2,1,120),(15,305,7,29,'c451853b_e.jpg',6,5,48),(16,106,3,32,'4a6a2877_e.jpg',4,3,168),(17,206,3,47,'7527d7ab_e.jpg',5,3,194),(18,306,2,37,'d46dc992_e.jpg',2,2,128),(19,107,2,37,'3be4f9a0_e.jpg',2,1,123),(20,207,4,40,'8ffe4c45_e.jpg',3,2,133),(21,307,5,49,'47961e14_e.jpg',3,2,159),(22,108,4,80,'71dd3e80_e.jpg',3,2,368),(23,208,3,87,'958cc105_e.jpg',4,2,500);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-30 20:02:41
