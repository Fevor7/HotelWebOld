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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) CHARACTER SET big5 NOT NULL,
  `password` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `surname` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `role` enum('0','1') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (7,'user',3599307,'user','user','user@gmail.com','0'),(8,'admin',92668751,'admin','admin','admin@gmail.com','1'),(10,'Fevor',1419608229,'Виктор','Гринько','Fevor@tut.by','1'),(12,'User111',59349032,'Katya','User111','User111@fr.rr','0'),(13,'user2',648316000,'user2','user2','user2@gmail.com','0'),(15,'user3',676945152,'user3','user3','use3@gmail.com','0'),(17,'user4',705574304,'user4','user4','user4@gmail.com','0'),(20,'user7',791461760,'user7','user7','user7@fg.ff','0'),(21,'user77',1264745366,'user77','user77','user77@gmail.com','0'),(22,'user159',1370141736,'user159','user159','user159@tut.by','0'),(24,'user5',734203456,'user5','user5','user5@gg.gg','0'),(25,'user6',762832608,'user6','user6','user6@tut.gg','0'),(27,'user777',1430062720,'user777','user777','user777@gmail.com','0'),(28,'Fevordd77',1762924864,'Fevordd','Fevordd','Fevordd77@fff.ff','0'),(29,'sdfsdf@fdg.fg',-1944250643,'dsfsdf','sdfsdf','sdfsdf@fdg.fg','0'),(30,'Fevor@frfgr.tt',472124047,'Fevor','Viktor','Fevor@frfgr.tt','0'),(31,'fdgdfg',1404117995,'dfgdfgdf','gfhgfhfg','dsfgsd@fgfg.fg','0'),(32,'dsfgdfg',860419190,'sdtdsft','fdgdfg','fdgdf@fgf.fg','0');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
