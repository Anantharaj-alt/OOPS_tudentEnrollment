-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: oop
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `marketing`
--

DROP TABLE IF EXISTS `marketing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marketing` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Expirience` varchar(45) NOT NULL,
  `Level` varchar(45) NOT NULL,
  `Position` varchar(45) NOT NULL,
  `Incharge` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marketing`
--

LOCK TABLES `marketing` WRITE;
/*!40000 ALTER TABLE `marketing` DISABLE KEYS */;
INSERT INTO `marketing` VALUES (1,'Anand','3','Under Graduate','Staff','Mr.Saman'),(2,'Ganesh','1','Post Graduate','Staff','Mr.Kamal'),(3,'Supun','0.6','Under Graduate','Staff','Mr.Saman'),(4,'Gayan','3','Post Graduate','Lead','Mr.Kamal'),(5,'Antony','6','Certificate Program','Manager','Mr.Nimal'),(6,'Kushan','5','Post Graduate','Manager','Mr.Kamal'),(7,'Hamda','1','Under Graduate','Staff','Mr.Saman'),(8,'Ikshn','6','Under Graduate','Manager','Mr.Saman'),(9,'Sumith','0.8','Post Graduate','Staff','Mr.Kamal'),(10,'Nayani','2','Certificate Program','Staff','Mr.Nimal'),(11,'Ksuni','1','Certificate Program','Staff','Mr.Nimal'),(12,'Ishari','4','Under Graduate','Lead','Mr.Saman'),(13,'Gayathri','0.2','Post Graduate','Staff','Mr.Kamal'),(14,'Fernando','4','Certificate Program','Lead','Mr.Nimal'),(15,'Suniml','1','Post Graduate','Staff','Mr.Kamal');
/*!40000 ALTER TABLE `marketing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `program`
--

DROP TABLE IF EXISTS `program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `program` (
  `RegNo` varchar(45) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Duration` varchar(45) NOT NULL,
  `Level` varchar(45) NOT NULL,
  `Incharge` varchar(45) NOT NULL,
  PRIMARY KEY (`RegNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `program`
--

LOCK TABLES `program` WRITE;
/*!40000 ALTER TABLE `program` DISABLE KEYS */;
INSERT INTO `program` VALUES ('1001','Bsc','4','Under Graduate','Mr.Saman'),('1002','Mba','3','Post Graduate','Mr.Kaml'),('1003','Bba','0.6','Under Graduate','Mr.Saman'),('1004','Network','0.5','Certificate Program','Mr.Nimal'),('1005','Bcs','3','Under Graduate','Mr.Saman'),('1006','MA','3','Post Graduate','Mr.Kaml'),('1007','Hardware','0.8','Certificate Program','Mr.Nimal'),('1008','Graphic','0.6','Certificate Program','Mr.Nimal'),('1009','Web Design','0.9','Certificate Program','Mr.Nimal'),('1010','Mphil','4','Post Graduate','Mr.Kaml');
/*!40000 ALTER TABLE `program` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students` (
  `RegNo` varchar(45) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `NIC` varchar(45) NOT NULL,
  `Course` varchar(45) NOT NULL,
  `Mobile` varchar(45) NOT NULL,
  `Level` varchar(45) NOT NULL,
  PRIMARY KEY (`RegNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES ('1001','Anand','985521254v','Dip','0755255214','Under Graduate');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-27 19:11:28
