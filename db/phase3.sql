-- MySQL dump 10.13  Distrib 5.7.16, for Win64 (x86_64)
--
-- Host: localhost    Database: Phase3_backup
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `apply`
--

DROP TABLE IF EXISTS `apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apply` (
  `Username` varchar(15) NOT NULL,
  `Project_Name` varchar(50) NOT NULL,
  `Date` date DEFAULT NULL,
  `Status` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`Username`,`Project_Name`),
  KEY `Project_Name` (`Project_Name`),
  CONSTRAINT `apply_ibfk_1` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`),
  CONSTRAINT `apply_ibfk_2` FOREIGN KEY (`Project_Name`) REFERENCES `project` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apply`
--

LOCK TABLES `apply` WRITE;
/*!40000 ALTER TABLE `apply` DISABLE KEYS */;
/*!40000 ALTER TABLE `apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `Name` varchar(50) NOT NULL,
  PRIMARY KEY (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `Name` varchar(50) NOT NULL,
  `Course_Num` int(11) NOT NULL,
  `Est_Num_Students` int(11) DEFAULT NULL,
  `Instructor` varchar(50) DEFAULT NULL,
  `Designation_Name` varchar(50) NOT NULL,
  PRIMARY KEY (`Course_Num`),
  UNIQUE KEY `Name` (`Name`),
  KEY `Designation_Name` (`Designation_Name`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`Designation_Name`) REFERENCES `designation` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_is_category`
--

DROP TABLE IF EXISTS `course_is_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_is_category` (
  `Course_Num` int(11) NOT NULL,
  `Category_Name` varchar(50) NOT NULL,
  PRIMARY KEY (`Course_Num`,`Category_Name`),
  KEY `Category_Name` (`Category_Name`),
  CONSTRAINT `course_is_category_ibfk_1` FOREIGN KEY (`Course_Num`) REFERENCES `course` (`Course_Num`),
  CONSTRAINT `course_is_category_ibfk_2` FOREIGN KEY (`Category_Name`) REFERENCES `category` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_is_category`
--

LOCK TABLES `course_is_category` WRITE;
/*!40000 ALTER TABLE `course_is_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_is_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department` (
  `Dept_Name` varchar(50) NOT NULL,
  PRIMARY KEY (`Dept_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES ('College of Business');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `designation`
--

DROP TABLE IF EXISTS `designation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `designation` (
  `Name` varchar(50) NOT NULL,
  PRIMARY KEY (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `designation`
--

LOCK TABLES `designation` WRITE;
/*!40000 ALTER TABLE `designation` DISABLE KEYS */;
/*!40000 ALTER TABLE `designation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `major` (
  `Major_Name` varchar(50) NOT NULL,
  `Dept_Name` varchar(50) NOT NULL,
  PRIMARY KEY (`Major_Name`),
  KEY `Dept_Name` (`Dept_Name`),
  CONSTRAINT `major_ibfk_1` FOREIGN KEY (`Dept_Name`) REFERENCES `department` (`Dept_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
INSERT INTO `major` VALUES ('Business','College of Business');
/*!40000 ALTER TABLE `major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `Name` varchar(50) NOT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `Est_Num_Students` int(11) DEFAULT NULL,
  `Advisor_Name` varchar(50) DEFAULT NULL,
  `Advisor_Email` varchar(50) DEFAULT NULL,
  `Designation_Name` varchar(50) NOT NULL,
  PRIMARY KEY (`Name`),
  KEY `Designation_Name` (`Designation_Name`),
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`Designation_Name`) REFERENCES `designation` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_is_category`
--

DROP TABLE IF EXISTS `project_is_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_is_category` (
  `Category_Name` varchar(50) NOT NULL,
  `Project_Name` varchar(50) NOT NULL,
  PRIMARY KEY (`Category_Name`,`Project_Name`),
  KEY `Project_Name` (`Project_Name`),
  CONSTRAINT `project_is_category_ibfk_1` FOREIGN KEY (`Category_Name`) REFERENCES `category` (`Name`),
  CONSTRAINT `project_is_category_ibfk_2` FOREIGN KEY (`Project_Name`) REFERENCES `project` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_is_category`
--

LOCK TABLES `project_is_category` WRITE;
/*!40000 ALTER TABLE `project_is_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_is_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requirement`
--

DROP TABLE IF EXISTS `requirement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requirement` (
  `Project_Name` varchar(50) NOT NULL,
  `Requirement_Type` varchar(50) NOT NULL,
  PRIMARY KEY (`Project_Name`,`Requirement_Type`),
  CONSTRAINT `requirement_ibfk_1` FOREIGN KEY (`Project_Name`) REFERENCES `project` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requirement`
--

LOCK TABLES `requirement` WRITE;
/*!40000 ALTER TABLE `requirement` DISABLE KEYS */;
/*!40000 ALTER TABLE `requirement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `Username` varchar(15) NOT NULL,
  `Password` varchar(15) DEFAULT NULL,
  `Type` varchar(15) NOT NULL,
  `GT_Email` varchar(50) NOT NULL,
  `Year` varchar(15) DEFAULT NULL,
  `Major` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Username`),
  UNIQUE KEY `GT_Email` (`GT_Email`),
  KEY `Major` (`Major`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`Major`) REFERENCES `major` (`Major_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
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

-- Dump completed on 2016-11-01 13:50:01
