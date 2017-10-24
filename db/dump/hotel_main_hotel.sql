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
-- Table structure for table `main_hotel`
--

DROP TABLE IF EXISTS `main_hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `main_hotel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name_en` varchar(45) NOT NULL,
  `name_ru` varchar(45) DEFAULT NULL,
  `address_en` varchar(2000) NOT NULL,
  `address_ru` varchar(2000) NOT NULL,
  `location` varchar(2000) NOT NULL,
  `about_en` varchar(2000) NOT NULL,
  `about_ru` varchar(2000) DEFAULT NULL,
  `star_rating` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `main_hotel`
--

LOCK TABLES `main_hotel` WRITE;
/*!40000 ALTER TABLE `main_hotel` DISABLE KEYS */;
INSERT INTO `main_hotel` VALUES (1,'Hotel','Гостиница','Dzerzhinsky avenue, 1, 220014, Minsk, Belarus','пр. Дзержинского, 1, 220014, Минск, Беларусь.','<iframe class=\"location\" src=\"https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d2351.2519944702076!2d27.52801630290223!3d53.89172486995453!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sru!2sru!4v1499302681734\" width=\"1000\" height=\"300\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>','My Hotel is a contemporary 5-star superior property offering stylish accommodation near the heart of Minsk. Highlights include an indoor swimming pool, a fitness centre and a good location near the Belarusian National History and Culture Museum. Minsk is the capital of Belarus and a major cultural centre with a rich and fascinating history. Places of interest include Independence Square, the Red Church, the Belarusian National History and Culture Museum, and the National Academic Bolshoi Opera and Ballet Theatre of the Republic of Belarus. Lenin Square metro station is located in the vicinity, providing fast and easy access to other popular Minsk destinations. The hotel is housed in a modern mid-rise building. Facilities include an indoor swimming pool and a fitness centre. Free Wi-Fi is available in public areas. Deluxe rooms are 45sqm and decorated in a modern style with neutral tones. Amenities include air-conditioning, a flat-screen satellite TV, a mini-bar, a safe, a telephone, tea and coffee equipment and a work desk. The en-suite bathrooms featuring a bathtub with integrated shower and a hairdryer. The hotel restaurant serves international cuisine in a stylish and elegant setting. Guests can also enjoy a range of drinks at the hotel bar, and a buffet breakfast is provided every morning. The hotel has a 24-hour reception. Chargeable parking is possible onsite.','Эта гостиница - современная 5-звездочная гостиница повышенной комфортности, предлагающая стильные номера недалеко от центра Минска. В числе основных достопримечательностей: крытый бассейн, фитнес-центр и хорошее месторасположение возле Белорусского национального музея истории и культуры. Минск - столица Беларуси и крупный культурный центр с богатой и увлекательной историей. Достопримечательности включают площадь Независимости, Красную церковь, Белорусский национальный музей истории и культуры, Национальный академический Большой театр оперы и балета Республики Беларусь. Станция метро «Площадь Ленина» находится в непосредственной близости, обеспечивая быстрый и легкий доступ к другим популярным местам в Минске. Отель расположен в современном среднеэтажном здании. В гостинице есть крытый бассейн и фитнес-центр. Бесплатный Wi-Fi предоставляется в зонах общественного пользования. Номера Deluxe 45sqm и оформлены в современном стиле с нейтральными тонами. В номерах есть кондиционер, телевизор с плоским экраном и спутниковыми каналами, мини-бар, сейф, телефон, принадлежности для чая / кофе и рабочий стол. В ванных комнатах есть ванна со встроенным душем и феном. В ресторане отеля подают блюда интернациональной кухни в стильной и элегантной обстановке. В баре отеля гости могут заказать различные напитки, а каждое утро сервируется завтрак \"шведский стол\". Стойка регистрации отеля работает круглосуточно. Платная парковка возможна на месте.','&#9885 &#9885 &#9885 &#9885 &#9885');
/*!40000 ALTER TABLE `main_hotel` ENABLE KEYS */;
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
