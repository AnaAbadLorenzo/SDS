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
-- Table structure for table `procedimientousuarioproceso`
--

DROP TABLE IF EXISTS `procedimientousuarioproceso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `procedimientousuarioproceso` (
  `id_procedimiento_usuario_proceso` int NOT NULL,
  `fecha_procedimiento_usuario_proceso` date NOT NULL,
  'borrado_procedimiento_usuario_proceso' int NOT NULL,
  `id_respuesta` int NOT NULL,
  `id_proceso` int NOT NULL,
  PRIMARY KEY (`id_procedimiento_usuario_proceso`,`id_proceso`),
  KEY `id_procedimiento_usuario_proceso_idx` (`id_procedimiento_usuario_proceso`),
  KEY `id_proceso_procedimiento_usuario_proceso_idx` (`id_proceso`),
  KEY `idRespuesta_procedimiento_usuario_proceso_fk` (`id_respuesta`),
  KEY `idProceso_procedimiento_usuario_proceso_fk` (`id_procedimiento_usuario_proceso`),
  CONSTRAINT `idRespuesta_procedimiento_usuario_proceso_fk` FOREIGN KEY (`id_respuesta`) REFERENCES `respuesta_posible` (`id_respuesta`),
  CONSTRAINT `idProceso_procedimiento_usuario_proceso_fk` FOREIGN KEY (`id_proceso`) REFERENCES `proceso` (`id_proceso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;