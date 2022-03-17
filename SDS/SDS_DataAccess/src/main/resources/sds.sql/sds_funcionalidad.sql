CREATE DATABASE  IF NOT EXISTS `sds` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sds`;
-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: sds
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `funcionalidad`
--

DROP TABLE IF EXISTS `funcionalidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcionalidad` (
  `id_funcionalidad` int NOT NULL AUTO_INCREMENT,
  `nombre_funcionalidad` varchar(48) NOT NULL,
  `descrip_funcionalidad` text NOT NULL,
  `borrado_funcionalidad` int NOT NULL,
  PRIMARY KEY (`id_funcionalidad`),
   UNIQUE KEY `nombre_funcionalidad_UNIQUE` (`nombre_funcionalidad`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionalidad`
--

LOCK TABLES `funcionalidad` WRITE;
/*!40000 ALTER TABLE `funcionalidad` DISABLE KEYS */;
INSERT INTO `funcionalidad` VALUES (1,'Gestión de personas','Permite realizar acciones sobre las personas de la aplicación',0);
INSERT INTO `funcionalidad` VALUES (2,'Gestión de usuarios','Permite realizar acciones sobre los usuarios de la aplicación',0);
INSERT INTO `funcionalidad` VALUES (3,'Gestión de roles','Permite realizar acciones sobre los roles de la aplicación',0);
INSERT INTO `funcionalidad` VALUES (4,'Gestión de empresas','Permite realizar acciones sobre las empresas de la aplicación',0);
INSERT INTO `funcionalidad` VALUES (5,'Gestión de acciones','Permite realizar acciones sobre las acciones de la aplicación',0);
INSERT INTO `funcionalidad` VALUES (6,'Gestión de funcionalidades','Permite realizar acciones sobre las funcionalidades de la aplicación',0);
INSERT INTO `funcionalidad` VALUES (7,'Gestión de permisos','Permite modificar los permisos de la aplicación',0);

/*!40000 ALTER TABLE `funcionalidad` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-04 15:56:18
