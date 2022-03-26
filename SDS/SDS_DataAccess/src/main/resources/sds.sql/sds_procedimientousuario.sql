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
-- Table structure for table `procedimientousuario`
--

DROP TABLE IF EXISTS `procedimientousuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `procedimientousuario` (
  `id_procedimiento_usuario` int NOT NULL AUTO_INCREMENT,
  `puntuacion_procedimiento_usuario` int NOT NULL,
  `fecha_procedimiento_usuario` date NOT NULL,
  `borrado_procedimiento_usuario` int NOT NULL,
  `dni_usuario` varchar(9) NOT NULL,
  `id_procedimiento`int NOT NULL,
  PRIMARY KEY (`id_procedimiento_usuario`),
  KEY `dniUsuario_fk` (`dni_usuario`),
  CONSTRAINT `dniUsuario_fk` FOREIGN KEY (`dni_usuario`) REFERENCES `usuario` (`dni_usuario`) ON UPDATE CASCADE,
  KEY `idProcedimiento_fk` (`id_procedimiento`),
  CONSTRAINT `idProcedimiento_fk` FOREIGN KEY (`id_procedimiento`) REFERENCES `procedimiento` (`id_procedimiento`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;