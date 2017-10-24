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
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number_room` int(11) DEFAULT NULL,
  `id_user` int(11) NOT NULL,
  `date_start` datetime NOT NULL,
  `date_end` datetime NOT NULL,
  `bed` tinyint(4) NOT NULL,
  `person` tinyint(4) NOT NULL,
  `id_type_room` int(11) NOT NULL,
  `total_amount` decimal(10,0) DEFAULT NULL,
  `id_status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=220 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (144,206,8,'2017-08-12 00:00:00','2017-08-15 00:00:00',3,2,1,776,3),(145,108,8,'2017-08-12 00:00:00','2017-08-12 00:00:00',1,1,4,368,3),(146,203,8,'2017-08-12 00:00:00','2017-08-15 00:00:00',1,1,2,116,3),(147,101,8,'2017-08-12 00:00:00','2017-08-15 00:00:00',1,1,1,188,3),(149,304,10,'2017-08-13 00:00:00','2017-08-14 00:00:00',1,1,3,136,3),(152,108,8,'2017-08-17 00:00:00','2017-08-17 00:00:00',1,1,4,NULL,3),(155,202,8,'2017-08-16 00:00:00','2017-08-18 00:00:00',2,3,3,255,3),(156,108,8,'2017-08-14 00:00:00','2017-08-14 00:00:00',2,1,4,368,3),(157,203,8,'2017-08-14 00:00:00','2017-08-16 00:00:00',1,1,2,87,3),(161,307,8,'2017-08-18 00:00:00','2017-08-21 00:00:00',1,1,5,636,3),(164,203,10,'2017-08-21 00:00:00','2017-08-21 00:00:00',1,1,2,29,2),(165,108,10,'2017-08-21 00:00:00','2017-08-24 00:00:00',1,1,4,1472,2),(167,302,10,'2017-08-21 00:00:00','2017-08-23 00:00:00',2,2,1,180,2),(168,307,10,'2017-08-26 00:00:00','2017-08-27 00:00:00',1,2,5,318,2),(169,208,10,'2017-08-21 00:00:00','2017-08-21 00:00:00',1,1,1,NULL,2),(170,305,10,'2017-08-25 00:00:00','2017-08-27 00:00:00',2,3,1,144,2),(171,105,10,'2017-08-24 00:00:00','2017-08-26 00:00:00',1,2,3,318,2),(172,202,10,'2017-08-21 00:00:00','2017-08-21 00:00:00',1,1,1,85,2),(173,305,10,'2017-08-21 00:00:00','2017-08-21 00:00:00',1,3,1,48,2),(176,105,10,'2017-08-21 00:00:00','2017-08-24 00:00:00',1,2,3,424,3),(177,203,10,'2017-08-22 00:00:00','2017-08-22 00:00:00',1,1,2,29,2),(179,206,8,'2017-08-27 00:00:00','2017-08-27 00:00:00',2,1,3,NULL,2),(180,106,8,'2017-08-23 00:00:00','2017-08-24 00:00:00',2,2,3,336,3),(182,304,8,'2017-08-21 00:00:00','2017-08-21 00:00:00',1,4,1,68,2),(183,303,8,'2017-08-21 00:00:00','2017-08-21 00:00:00',1,2,2,33,3),(201,101,10,'2017-08-26 00:00:00','2017-08-26 00:00:00',1,1,1,47,2),(204,303,8,'2017-08-24 00:00:00','2017-08-26 00:00:00',1,2,2,99,2),(208,202,8,'2017-08-27 00:00:00','2017-08-29 00:00:00',1,1,1,255,2),(210,108,8,'2017-08-25 00:00:00','2017-08-25 00:00:00',1,1,4,368,2),(211,305,7,'2017-08-26 00:00:00','2017-08-27 00:00:00',2,3,1,96,2),(213,106,7,'2017-08-26 00:00:00','2017-08-27 00:00:00',1,3,3,336,2),(215,108,8,'2017-08-25 00:00:00','2017-08-25 00:00:00',1,1,4,368,2),(218,NULL,7,'2017-08-30 00:00:00','2017-09-01 00:00:00',1,1,1,NULL,1),(219,NULL,7,'2017-08-30 00:00:00','2017-09-01 00:00:00',2,3,1,NULL,1);
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

-- Dump completed on 2017-08-30 20:02:41
