# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: localhost (MySQL 5.7.9)
# Database: phase3
# Generation Time: 2016-12-03 18:47:51 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table apply
# ------------------------------------------------------------

DROP TABLE IF EXISTS `apply`;

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



# Dump of table category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `Name` varchar(50) NOT NULL,
  PRIMARY KEY (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;

INSERT INTO `category` (`Name`)
VALUES
	('adaptive learning'),
	('collaborative action'),
	('computing for good'),
	('crowd-sourced'),
	('doing good for your neighborhood'),
	('reciprocal teaching and learning'),
	('sustainable communities'),
	('technology for social good'),
	('urban development');

/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table course
# ------------------------------------------------------------

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `Name` varchar(50) NOT NULL,
  `Course_Num` varchar(11) NOT NULL DEFAULT '',
  `Est_Num_Students` int(11) DEFAULT NULL,
  `Instructor` varchar(50) DEFAULT NULL,
  `Designation_Name` varchar(50) NOT NULL,
  PRIMARY KEY (`Course_Num`),
  UNIQUE KEY `Name` (`Name`),
  KEY `Designation_Name` (`Designation_Name`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`Designation_Name`) REFERENCES `designation` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;

INSERT INTO `course` (`Name`, `Course_Num`, `Est_Num_Students`, `Instructor`, `Designation_Name`)
VALUES
	('Green Infrastructure: EPA Campus Rainwater Challen','ARCH 4803',26,'Richard Dagenhart','Sustainable Communities'),
	('Honors Biological Principles; Honors Organismal Bi','BIOL 1511',150,'Brian Hammer','Sustainable Communities'),
	('Problems in Biomedical Engineering','BMED 2250',300,'Barbara Burks Fasse','Community'),
	('Introduction to Gender Inequality: CS Edition','CS 3070',4060,'Lily Lau','Sustainable Communities'),
	('Cutting it with Venkatdaddy: Maximum Flow','CS 3511',1738,'Yamilex Avila Stanley','Sustainable Communities'),
	('Crowdsourcing Censorship of Online Voting Big Data','CS 4002',30,'Douglas Cheong','Sustainable Communities'),
	('Introduction to Brian\'s Dank Queries','DANK 4200',420,'Brian Wang','Community'),
	('Introduction to Environmental Science','EAS 1600',600,'Dana Hartley','Community'),
	('Habitable Planet','EAS 1601',600,'Dana Hartley','Community'),
	('Physics of the Weather','EAS 2750',30,'Dana Hartley','Community'),
	('Urban Forest','EAS 2803 ',10,'Monica Halka','Sustainable Communities'),
	('Advanced Not Caring About the Environment','EAS 666',3580,'Wei Wang','Sustainable Communities'),
	('Being a Decent Human Being I','EASY 1100',20,'Lucas Della Bella','Community'),
	('Being a Decent Human Being II','EASY 1101',40,'Lucas Della Bella','Sustainable Communities'),
	('Advanced Householder Cold Shoulder','MATH 2606',300,'Wuchen Li','Community'),
	('Advanced Honors Failing Combinatorics','MATH 3013',50,'Torin Greenwood','Sustainable Communities'),
	('Advanced Lack of Empathy: Millenials Edition','MILL 2000',570,'Lily Lau','Sustainable Communities'),
	('Environmental Policy and Politics','PUBP 3315',25,'Alice Favero','Sustainable Communities'),
	('Introduction to Wei Bae: Sustainable Wanging','WANG 123',5,'Douglas Cheong','Community'),
	('Interview Preparation: Excrete Before You Beat','WANG 3098',5,'Brian Wang','Community');

/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table course_is_category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `course_is_category`;

CREATE TABLE `course_is_category` (
  `Course_Num` varchar(11) NOT NULL DEFAULT '',
  `Category_Name` varchar(50) NOT NULL,
  PRIMARY KEY (`Course_Num`,`Category_Name`),
  KEY `Category_Name` (`Category_Name`),
  CONSTRAINT `course_is_category_ibfk_1` FOREIGN KEY (`Course_Num`) REFERENCES `course` (`Course_Num`),
  CONSTRAINT `course_is_category_ibfk_2` FOREIGN KEY (`Category_Name`) REFERENCES `category` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `course_is_category` WRITE;
/*!40000 ALTER TABLE `course_is_category` DISABLE KEYS */;

INSERT INTO `course_is_category` (`Course_Num`, `Category_Name`)
VALUES
	('CS 3511','adaptive learning'),
	('MATH 2606','adaptive learning'),
	('CS 4002','collaborative action'),
	('DANK 4200','collaborative action'),
	('EAS 666','collaborative action'),
	('ARCH 4803','computing for good'),
	('BMED 2250','computing for good'),
	('CS 3511','computing for good'),
	('WANG 3098','computing for good'),
	('CS 4002','crowd-sourced'),
	('DANK 4200','crowd-sourced'),
	('MATH 2606','crowd-sourced'),
	('WANG 123','crowd-sourced'),
	('ARCH 4803','doing good for your neighborhood'),
	('BMED 2250','doing good for your neighborhood'),
	('CS 4002','doing good for your neighborhood'),
	('MATH 2606','doing good for your neighborhood'),
	('MILL 2000','doing good for your neighborhood'),
	('WANG 3098','doing good for your neighborhood'),
	('CS 3511','reciprocal teaching and learning'),
	('MATH 2606','reciprocal teaching and learning'),
	('MATH 3013','reciprocal teaching and learning'),
	('BIOL 1511','sustainable communities'),
	('EAS 1600','sustainable communities'),
	('EAS 1601','sustainable communities'),
	('EAS 2750','sustainable communities'),
	('EAS 2803','sustainable communities'),
	('EAS 666','sustainable communities'),
	('PUBP 3315','sustainable communities'),
	('DANK 4200','technology for social good'),
	('EASY 1100','technology for social good'),
	('EASY 1101','technology for social good'),
	('CS 3070','urban development'),
	('EAS 1600','urban development'),
	('EAS 1601','urban development'),
	('EAS 2750','urban development'),
	('EAS 2803','urban development'),
	('PUBP 3315','urban development');

/*!40000 ALTER TABLE `course_is_category` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table department
# ------------------------------------------------------------

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `Dept_Name` varchar(50) NOT NULL,
  PRIMARY KEY (`Dept_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;

INSERT INTO `department` (`Dept_Name`)
VALUES
	('College of Business'),
	('College of Computing'),
	('College of Design'),
	('College of Engineering'),
	('College of Liberal Arts'),
	('College of Sciences');

/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table designation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `designation`;

CREATE TABLE `designation` (
  `Name` varchar(50) NOT NULL,
  PRIMARY KEY (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `designation` WRITE;
/*!40000 ALTER TABLE `designation` DISABLE KEYS */;

INSERT INTO `designation` (`Name`)
VALUES
	('Community'),
	('Sustainable Communities');

/*!40000 ALTER TABLE `designation` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table major
# ------------------------------------------------------------

DROP TABLE IF EXISTS `major`;

CREATE TABLE `major` (
  `Major_Name` varchar(50) NOT NULL,
  `Dept_Name` varchar(50) NOT NULL,
  PRIMARY KEY (`Major_Name`),
  KEY `Dept_Name` (`Dept_Name`),
  CONSTRAINT `major_ibfk_1` FOREIGN KEY (`Dept_Name`) REFERENCES `department` (`Dept_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;

INSERT INTO `major` (`Major_Name`, `Dept_Name`)
VALUES
	('Business Administration','College of Business'),
	('Computational Media','College of Computing'),
	('Computer Science','College of Computing'),
	('Architecture','College of Design'),
	('Industrial Design','College of Design'),
	('Music Technology','College of Design'),
	('Aerospace Engineering','College of Engineering'),
	('Biomedical Engineering','College of Engineering'),
	('Chemical and Biomolecular Engineering','College of Engineering'),
	('Chemical and Biomolecular Engineering (BT)','College of Engineering'),
	('Civil Engineering','College of Engineering'),
	('Computer Engineering','College of Engineering'),
	('Electrical Engineering','College of Engineering'),
	('Environmental Engineering','College of Engineering'),
	('Industrial Engineering','College of Engineering'),
	('Material Science and Engineering','College of Engineering'),
	('Mechanical Engineering','College of Engineering'),
	('Nuclear and Radiological Engineering','College of Engineering'),
	('Applied Language and Intercultural Studies','College of Liberal Arts'),
	('Economics','College of Liberal Arts'),
	('Economics and International Affairs','College of Liberal Arts'),
	('Global Economics and Modern Languages','College of Liberal Arts'),
	('History, Technology, and Society','College of Liberal Arts'),
	('International Affairs','College of Liberal Arts'),
	('International Affairs and Modern Languages','College of Liberal Arts'),
	('Literature, Media, and Communication','College of Liberal Arts'),
	('Public Policy','College of Liberal Arts'),
	('Applied Mathematics','College of Sciences'),
	('Applied Physics','College of Sciences'),
	('Biochemistry','College of Sciences'),
	('Biology','College of Sciences'),
	('Chemistry','College of Sciences'),
	('Discrete Mathematics','College of Sciences'),
	('Earth and Atmospheric Sciences','College of Sciences'),
	('Physics','College of Sciences'),
	('Psychology','College of Sciences');

/*!40000 ALTER TABLE `major` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table project
# ------------------------------------------------------------

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `Name` varchar(50) NOT NULL,
  `Description` varchar(200) DEFAULT NULL,
  `Est_Num_Students` int(11) DEFAULT NULL,
  `Advisor_Name` varchar(50) NOT NULL,
  `Advisor_Email` varchar(50) NOT NULL,
  `Designation_Name` varchar(50) NOT NULL,
  PRIMARY KEY (`Name`),
  KEY `Designation_Name` (`Designation_Name`),
  CONSTRAINT `project_ibfk_1` FOREIGN KEY (`Designation_Name`) REFERENCES `designation` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;

INSERT INTO `project` (`Name`, `Description`, `Est_Num_Students`, `Advisor_Name`, `Advisor_Email`, `Designation_Name`)
VALUES
	('Bench Building ','Build benches across campus',45,'Bench Builder','bbuilder@gatech.edu','Community'),
	('BuzzMovie','Create a movie selecting program thats allows for recommendations, top movies, and searching functionality.',4,'Wei Wang','joewang704@gatech.edu','Sustainable Communities'),
	('Dank Project','The Dankest Project of Them All',28,'Brian Wang','bwang330@gatech.edu','Sustainable Communities'),
	('Discussion Group: 4001','Contribute to the discussion so that you can get your UBER internship',1,'Lily Lau','llau6@gatech.edu','Community'),
	('Elementary School Mentoring','Mentor nearby elementary school children and teach them material',30,'Bob Smith','bsmith@gatech.edu','Community'),
	('Epic Intentions','Epic Intentions connects an interdisciplinary team of students with a local nonprofit to apply technical skills for social and civic good to help make the nonprofits make a greater impact in the commu',20,'Yeji Lee','ylee@gatech.edu','Community'),
	('ESW Hydroponics/Urban Farming Project','The Hydroponics/Urban Farming Project experiments with different ways to grow produce in urban areas using limited space and water resources. We investigate both soil-based and hydroponic methods of g',7,'Nicole Kinnard','nkinnard@gatech.edu','Sustainable Communities'),
	('Excel Current Events','Excel Current Events is a participation (not for credit) course for degree-seeking students who are interested in developing their communication skills in conversations with adults with intellectual a',15,'Ashley Bidlack','abidlack@gatech.edu','Community'),
	('Excel Peer Support Network','Excel (www.excel.gatech.edu) is a four-year, dual certificate program for students with intellectual and developmental disabilities. The Peer Support Network is designed to provide the individualized ',60,'Marnie Williams','mwilliams@gatech.edu','Community'),
	('Fun Project','Funnest Project of Them All',4,'Brian Wang','bwang330@gatech.edu','Community'),
	('Genetically Modified Dopamine','Invent the happy pill by testing various different variations of dopamine by splicing genes into mice',50,'Dope Meme','dmeme@gatech.edu','Sustainable Communities'),
	('HackGT: Registration System','Build a robust registration system for hackers across the country, including an application processing page, search functionality, and an email system',5,'Brian Wang','bwang330@gatech.edu','Community'),
	('Hacking the Tesla','Work with industry best in hacking one of the first commercial auto driving cars on the market.',10,'Douglas Cheong','idkDoug@gatech.edu','Community'),
	('Instagram Redesign','Redesign the UI/UX of Instagram',20,'Yamilex Avila-Stanley','yamifaku@gatech.edu','Sustainable Communities'),
	('Know Your Water Project','This project will allow students to be part of a large, crowd-sourced study – at little cost to themselves – to contribute to a knowledge bank of how different communities treat and track their water ',10,'Neha Kumar','nkumar@gatech.edu','Sustainable Communities'),
	('Lit Green','Run over Tech Green and destroy it a few months after it is redone',450,'Brian Wang','bwang330@gatech.edu','Community'),
	('LoL Client Remake','Prevent Riot from continuing to use Adobe Air and create a cleaner, less cluttered, bug free client.',100,'Jatin Nanda','kpopislife@gatech.edu','Community'),
	('Machine Learning with Weather','Use Machine Learning and Neural Networks to predict weather for every day for the next 30 years',4,'Jay Devanathan','bememan@gatech.edu','Sustainable Communities'),
	('O(n) Sorting','Invent a way to do sorting in O(n) time, for all types of sortable objects',35,'Lucas Della Bella','ldellabella3@gatech.edu','Community'),
	('Oki','Free Form DJ Management System',4,'Douglas Cheong','idkDoug@gatech.edu','Community'),
	('Poop Analyzer','Analyze your poop with computer vision algorithms',30,'Brian Wang','bwang330@gatech.edu','Sustainable Communities'),
	('RoboJackets','Build robots and compete at national competitions',50,'Robo Jackets','rjackets@gatech.edu','Community'),
	('Shakespeare in Prison Project','As the world celebrates the 400th anniversary of Shakespeare’s death in 2016, Georgia Tech students will travel to a high-security men’s prison outside Atlanta to discuss Shakespeare with incarcerated',20,'Sarah Higinbotham','shiginbotham@gatech.edu','Community'),
	('Smael','Build a location based role playing game using unity.',8,'Lucas Della Bell','ldellabella3@gatech.edu','Community'),
	('SpotiCloud','Combining Spotify and Soundcloud into one complete system',12,'Brian Wang','bwang330@gatech.edu','Sustainable Communities'),
	('Statue Building','Build statues that represent significant Georgia Tech figures across campus',45,'Statue Building','stbuilder@gatech.edu','Community'),
	('Swing Building','Build Swings at the Student Center',30,'Swing Builder','sbuilder@gatech.edu','Community');

/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table project_is_category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `project_is_category`;

CREATE TABLE `project_is_category` (
  `Category_Name` varchar(50) NOT NULL,
  `Project_Name` varchar(50) NOT NULL,
  PRIMARY KEY (`Category_Name`,`Project_Name`),
  KEY `Project_Name` (`Project_Name`),
  CONSTRAINT `project_is_category_ibfk_1` FOREIGN KEY (`Category_Name`) REFERENCES `category` (`Name`),
  CONSTRAINT `project_is_category_ibfk_2` FOREIGN KEY (`Project_Name`) REFERENCES `project` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `project_is_category` WRITE;
/*!40000 ALTER TABLE `project_is_category` DISABLE KEYS */;

INSERT INTO `project_is_category` (`Category_Name`, `Project_Name`)
VALUES
	('adaptive learning','Bench Building'),
	('collaborative action','Bench Building'),
	('crowd-sourced','Bench Building'),
	('sustainable communities','Bench Building'),
	('sustainable communities','Dank Project'),
	('urban development','Dank Project'),
	('doing good for your neighborhood','Elementary School Mentoring'),
	('reciprocal teaching and learning','Elementary School Mentoring'),
	('collaborative action','Epic Intentions'),
	('doing good for your neighborhood','Epic Intentions'),
	('sustainable communities','ESW Hydroponics/Urban Farming Project'),
	('urban development','ESW Hydroponics/Urban Farming Project'),
	('computing for good','Excel Current Events'),
	('doing good for your neighborhood','Excel Current Events'),
	('reciprocal teaching and learning','Excel Current Events'),
	('technology for social good','Excel Current Events'),
	('computing for good','Excel Peer Support Network'),
	('doing good for your neighborhood','Excel Peer Support Network'),
	('reciprocal teaching and learning','Excel Peer Support Network'),
	('adaptive Learning','Fun Project'),
	('collaborative action ','Fun Project'),
	('reciprocal teaching and learning','Fun Project'),
	('doing good for your neighborhood','HackGT: Registration System'),
	('technology for social good','HackGT: Registration System'),
	('crowd-sourced','Know Your Water Project'),
	('sustainable communities','Know Your Water Project'),
	('collaborative action','Lit Green'),
	('doing good for your neighborhood','Lit Green'),
	('collaborative action','O(n) Sorting'),
	('computing for good','O(n) Sorting'),
	('adaptive learning','Oki'),
	('computing for good','Oki'),
	('computing for good','Poop Analyzer'),
	('urban development','Poop Analyzer'),
	('sustainable communities','Shakespeare in Prison Project'),
	('urban development','Shakespeare in Prison Project'),
	('sustainable communities','Statue Building'),
	('urban development','Statue Building'),
	('doing good for your neighborhood','Swing Building'),
	('sustainable communities','Swing Building');

/*!40000 ALTER TABLE `project_is_category` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table requirement
# ------------------------------------------------------------

DROP TABLE IF EXISTS `requirement`;

CREATE TABLE `requirement` (
  `Project_Name` varchar(50) NOT NULL,
  `Requirement_Type` varchar(50) NOT NULL,
  PRIMARY KEY (`Project_Name`,`Requirement_Type`),
  CONSTRAINT `requirement_ibfk_1` FOREIGN KEY (`Project_Name`) REFERENCES `project` (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `requirement` WRITE;
/*!40000 ALTER TABLE `requirement` DISABLE KEYS */;

INSERT INTO `requirement` (`Project_Name`, `Requirement_Type`)
VALUES
	('Bench Building','D:Mechanical Engineering'),
	('Bench Building','Y:Freshman'),
	('BuzzMovie','D:College of Computing'),
	('BuzzMovie','M:Computer Science'),
	('BuzzMovie','Y:Junior'),
	('Dank Project','D:College of Business'),
	('Dank Project','Y:Senior'),
	('Discussion Group: 4001','D:College of Computing'),
	('Discussion Group: 4001','Y:Senior'),
	('ESW Hydroponics/Urban Farming Project','Y:Junior'),
	('Excel Current Events','D:College of Computing'),
	('Excel Current Events','Y:Senior'),
	('Excel Peer Support Network','M:Computer Science'),
	('Excel Peer Support Network','Y:Senior'),
	('Genetically Modified Dopamine','M:Biomedical Engineering'),
	('Genetically Modified Dopamine','Y:Senior'),
	('Hacking the Tesla','D:College of Computing'),
	('Hacking the Tesla','M:Computer Science'),
	('Instagram Redesign','D:College of Design'),
	('Instagram Redesign','Y:Freshman'),
	('Know Your Water Project','M:Computer Science'),
	('Know Your Water Project','Y:Senior'),
	('Oki','D:College of Computing'),
	('Oki','M:Computer Science'),
	('Shakespeare in Prison Project','D:College of Design'),
	('SpotiCloud','D:College of Computing'),
	('SpotiCloud','Y:Sophomore'),
	('Statue Building','D:College of Design'),
	('Statue Building','Y:Junior');

/*!40000 ALTER TABLE `requirement` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `Username` varchar(15) NOT NULL,
  `Password` varchar(32) NOT NULL,
  `Type` varchar(15) NOT NULL,
  `GT_Email` varchar(50) NOT NULL,
  `Year` varchar(15) DEFAULT NULL,
  `Major` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Username`),
  UNIQUE KEY `GT_Email` (`GT_Email`),
  KEY `Major` (`Major`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`Major`) REFERENCES `major` (`Major_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`Username`, `Password`, `Type`, `GT_Email`, `Year`, `Major`)
VALUES
	('ava26','urstupid','USER','ava26@gatech.edu','sophomore','Computer Science'),
	('brain11','urmom','USER','brain11@gatech.edu','freshman',NULL),
	('brain12','urpasswordissoobvi','USER','brain12@gatech.edu','senior','Computational Media'),
	('brain17','urthiccaf','USER','brain17@gatech.edu','freshman',NULL),
	('brain4','urdumb','USER','brain4@gatech.edu','sophomore',NULL),
	('brain5','urmom','USER','brain5@gatech.edu','freshman','Business Administration'),
	('drake10','urmom','USER','drake10@gatech.edu','freshman',NULL),
	('drake22','urepidermisisshowing','USER','drake22@gatech.edu','senior',NULL),
	('future14','urstupid','USER','future14@gatech.edu','freshman','Mechanical Engineering'),
	('future25','urmom','USER','future25@gatech.edu','senior','Chemical and Biomolecular Engineering'),
	('future7','urthiccaf','USER','future7@gatech.edu','freshman','Economics'),
	('future8','urpasswordissoobvi','ADMIN','future8@gatech.edu','',NULL),
	('jacob27','uracow','USER','jacob27@gatech.edu','sophomore','Aerospace Engineering'),
	('jacob3','urdumb','ADMIN','jacob3@gatech.edu','',NULL),
	('jacob6','urmom','USER','jacob6@gatech.edu','freshman','Industrial Engineering'),
	('l4p4ance9','urpasswordissoobvi','USER','l4p4ance9@gatech.edu','freshman','Computer Science'),
	('madeon19','urstupid','USER','madeon19@gatech.edu','sophomore','Computer Science'),
	('mrgoogoo13','urmom','ADMIN','mrgoogoo13@gatech.edu','',NULL),
	('mrgoogoo20','urstupid','USER','mrgoogoo20@gatech.edu','junior','Computer Science'),
	('olivia18','urmom','ADMIN','olivia18@gatech.edu','',NULL),
	('olivia23','uracow','ADMIN','olivia23@gatech.edu','',NULL),
	('yummy15','urdumb','USER','yummy15@gatech.edu','freshman','History, Technology, and Society'),
	('yummy16','urpasswordissoobvi','USER','yummy16@gatech.edu','freshman','Mechanical Engineering'),
	('yummy21','urpasswordissoobvi','USER','yummy21@gatech.edu','sophomore','Physics'),
	('yummy24','urepidermisisshowing','USER','yummy24@gatech.edu','sophomore','Business Administration');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
