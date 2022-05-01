CREATE DATABASE  IF NOT EXISTS `sds` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `sds`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: sds
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `accion`
--

DROP TABLE IF EXISTS `accion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accion` (
  `id_accion` int NOT NULL AUTO_INCREMENT,
  `nombre_accion` varchar(48) NOT NULL,
  `descrip_accion` text NOT NULL,
  `borrado_accion` int NOT NULL,
  PRIMARY KEY (`id_accion`),
  UNIQUE KEY `nombre_accion_UNIQUE` (`nombre_accion`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accion`
--

LOCK TABLES `accion` WRITE;
/*!40000 ALTER TABLE `accion` DISABLE KEYS */;
INSERT INTO `accion` VALUES (1,'Añadir','Permite añadir nuevos datos a la aplicación',0),(2,'Modificar','Permite modificar datos de la aplicación',0),(3,'Eliminar','Permite eliminar datos de la aplicación',0),(4,'Listar','Permite visualizar los datos de la aplicación en forma de listado',0),(5,'Visualizar','Permite ver en detalle los datos de la aplicación',0),(6,'Reactivar','Permite reactivar los datos eliminados de la aplicación',0);
/*!40000 ALTER TABLE `accion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empresa` (
  `id_empresa` int NOT NULL AUTO_INCREMENT,
  `cif_empresa` varchar(9) NOT NULL,
  `nombre_empresa` varchar(48) NOT NULL,
  `direccion_empresa` varchar(128) NOT NULL,
  `telefono_empresa` varchar(9) NOT NULL,
  `borrado_empresa` int NOT NULL,
  PRIMARY KEY (`id_empresa`),
  UNIQUE KEY `cifEmpresa_UNIQUE` (`cif_empresa`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES (1,'J26903286','Prueba','Prueba','988212121',0);
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evidencia`
--

DROP TABLE IF EXISTS `evidencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evidencia` (
  `id_evidencia` int NOT NULL,
  `fecha_evidencia` date NOT NULL,
  `borrado_evidencia` int NOT NULL,
  `id_procedimiento_usuario_proceso` int NOT NULL,
  PRIMARY KEY (`id_evidencia`),
  KEY `idProcedimiento_usuario_proceso_fk` (`id_procedimiento_usuario_proceso`),
  CONSTRAINT `idProcedimiento_usuario_proceso_fk` FOREIGN KEY (`id_procedimiento_usuario_proceso`) REFERENCES `procedimientousuarioproceso` (`id_procedimiento_usuario_proceso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evidencia`
--

LOCK TABLES `evidencia` WRITE;
/*!40000 ALTER TABLE `evidencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `evidencia` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionalidad`
--

LOCK TABLES `funcionalidad` WRITE;
/*!40000 ALTER TABLE `funcionalidad` DISABLE KEYS */;
INSERT INTO `funcionalidad` VALUES (1,'Gestión de personas','Permite realizar acciones sobre las personas de la aplicación',0),(2,'Gestión de usuarios','Permite realizar acciones sobre los usuarios de la aplicación',0),(3,'Gestión de roles','Permite realizar acciones sobre los roles de la aplicación',0),(4,'Gestión de empresas','Permite realizar acciones sobre las empresas de la aplicación',0),(5,'Gestión de acciones','Permite realizar acciones sobre las acciones de la aplicación',0),(6,'Gestión de funcionalidades','Permite realizar acciones sobre las funcionalidades de la aplicación',0),(7,'Test','Permite ver los test de acciones y de atributos realizados sobre las funcionalidades',0),(8,'Log de acciones','Permite ver los logs almacenados de acciones llevadas a cabo por los usuarios del sistema',0),(9,'Log de excepciones','Permite ver los logs almacenados de excepciones que se han producido en el sistema',0);
/*!40000 ALTER TABLE `funcionalidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logacciones`
--

DROP TABLE IF EXISTS `logacciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logacciones` (
  `id_logAcciones` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(32) NOT NULL,
  `accion` varchar(50) NOT NULL,
  `datos` varchar(2000) NOT NULL,
  `fecha` datetime NOT NULL,
  PRIMARY KEY (`id_logAcciones`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logacciones`
--

LOCK TABLES `logacciones` WRITE;
/*!40000 ALTER TABLE `logacciones` DISABLE KEYS */;
INSERT INTO `logacciones` VALUES (1,'aicuna','Login','com.sds.service.login.model.Login@1180aee','2022-01-15 00:00:00'),(2,'aicuna','Login','Login [usuario=aicuna, passwdUsuario=aicuna]','2022-01-15 00:00:00');
/*!40000 ALTER TABLE `logacciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logexcepciones`
--

DROP TABLE IF EXISTS `logexcepciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logexcepciones` (
  `id_logExcepciones` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(32) NOT NULL,
  `tipo_excepcion` varchar(50) NOT NULL,
  `descripcion_excepcion` varchar(200) NOT NULL,
  `fecha` datetime NOT NULL,
  PRIMARY KEY (`id_logExcepciones`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logexcepciones`
--

LOCK TABLES `logexcepciones` WRITE;
/*!40000 ALTER TABLE `logexcepciones` DISABLE KEYS */;
INSERT INTO `logexcepciones` VALUES (1,'usuario_generico','PASSWORD_INCORRECTO_EXCEPTION','El password es incorrecto','2022-01-15 00:00:00'),(2,'aicuna','PASSWORD_INCORRECTO_EXCEPTION','El password es incorrecto','2022-01-15 00:00:00'),(3,'pepito','USUARIO_NO_ENCONTRADO_EXCEPTION','No se ha encontrado el usuario','0000-00-00 00:00:00'),(4,'aicuna','PASSWORD_INCORRECTO_EXCEPTION','El password es incorrecto','0000-00-00 00:00:00'),(5,'pepito','USUARIO_NO_ENCONTRADO_EXCEPTION','No se ha encontrado el usuario','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `logexcepciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nivel`
--

DROP TABLE IF EXISTS `nivel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nivel` (
  `id_objetivo` int NOT NULL,
  `id_proceso` int NOT NULL,
  `nivel` int NOT NULL,
  `fecha_nivel` date NOT NULL,
  PRIMARY KEY (`id_objetivo`,`id_proceso`),
  KEY `id_objetivo_idx` (`id_objetivo`),
  KEY `id_proceso_idx` (`id_proceso`),
  KEY `idObjetivo_nivel_fk` (`id_objetivo`),
  KEY `idProceso_nivel_fk` (`id_proceso`),
  CONSTRAINT `idObjetivo_nivel_fk` FOREIGN KEY (`id_objetivo`) REFERENCES `objetivo` (`id_objetivo`),
  CONSTRAINT `idProceso_nivel_fk` FOREIGN KEY (`id_proceso`) REFERENCES `proceso` (`id_proceso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nivel`
--

LOCK TABLES `nivel` WRITE;
/*!40000 ALTER TABLE `nivel` DISABLE KEYS */;
/*!40000 ALTER TABLE `nivel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `noticias`
--

DROP TABLE IF EXISTS `noticias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `noticias` (
  `id_noticia` int NOT NULL AUTO_INCREMENT,
  `titulo_noticia` varchar(256) NOT NULL,
  `texto_noticia` text NOT NULL,
  `fecha_noticia` date NOT NULL,
  PRIMARY KEY (`id_noticia`),
  UNIQUE KEY `titulo_noticia_UNIQUE` (`titulo_noticia`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `noticias`
--

LOCK TABLES `noticias` WRITE;
/*!40000 ALTER TABLE `noticias` DISABLE KEYS */;
INSERT INTO `noticias` VALUES (1,'Bienvenidos a SDS','Os damos la bienvenida a SDS, una web donde podréis certificaros con los objetivos de la ONU 2030','2022-04-15');
/*!40000 ALTER TABLE `noticias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `objetivo`
--

DROP TABLE IF EXISTS `objetivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `objetivo` (
  `id_objetivo` int NOT NULL AUTO_INCREMENT,
  `nombre_objetivo` varchar(48) NOT NULL,
  `descripcion_objetivo` text NOT NULL,
  `borrado_objetivo` int NOT NULL,
  PRIMARY KEY (`id_objetivo`),
  UNIQUE KEY `nombre_objetivo_UNIQUE` (`nombre_objetivo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `objetivo`
--

LOCK TABLES `objetivo` WRITE;
/*!40000 ALTER TABLE `objetivo` DISABLE KEYS */;
INSERT INTO `objetivo` VALUES (1,'Fin de la pobreza','Objetivo de la ONU 2030 número 1',0);
/*!40000 ALTER TABLE `objetivo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `dni_persona` varchar(9) NOT NULL,
  `nombre_persona` varchar(56) NOT NULL,
  `apellidos_persona` varchar(128) NOT NULL,
  `fecha_nac_persona` date NOT NULL,
  `direccion_persona` varchar(128) NOT NULL,
  `telefono_persona` varchar(9) NOT NULL,
  `email_persona` varchar(48) NOT NULL,
  `borrado_persona` int NOT NULL,
  `id_empresa` int DEFAULT NULL,
  PRIMARY KEY (`dni_persona`),
  UNIQUE KEY `dni_p_UNIQUE` (`dni_persona`),
  KEY `idEmpresa_fk` (`id_empresa`),
  CONSTRAINT `idEmpresa_fk` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES ('44460662Y','Alexandra','Iglesias','1987-03-09','Prueba','988222222','a@a.com',0,1);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan` (
  `id_plan` int NOT NULL AUTO_INCREMENT,
  `nombre_plan` varchar(48) NOT NULL,
  `descrip_plan` text NOT NULL,
  `fecha_plan` date NOT NULL,
  `borrado_plan` int NOT NULL,
  `id_objetivo` int NOT NULL,
  PRIMARY KEY (`id_plan`),
  UNIQUE KEY `nombre_plan_UNIQUE` (`nombre_plan`),
  KEY `idObjetivo_fk` (`id_objetivo`),
  CONSTRAINT `idObjetivo_fk` FOREIGN KEY (`id_objetivo`) REFERENCES `objetivo` (`id_objetivo`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
/*!40000 ALTER TABLE `plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procedimiento`
--

DROP TABLE IF EXISTS `procedimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `procedimiento` (
  `id_procedimiento` int NOT NULL AUTO_INCREMENT,
  `nombre_procedimiento` varchar(48) NOT NULL,
  `descrip_procedimiento` text NOT NULL,
  `fecha_procedimiento` date NOT NULL,
  `borrado_procedimiento` int NOT NULL,
  `check_usuario` int NOT NULL,
  `id_plan` int NOT NULL,
  PRIMARY KEY (`id_procedimiento`),
  UNIQUE KEY `nombre_procedimiento_UNIQUE` (`nombre_procedimiento`),
  KEY `idPlan_fk` (`id_plan`),
  CONSTRAINT `idPlan_fk` FOREIGN KEY (`id_plan`) REFERENCES `plan` (`id_plan`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procedimiento`
--

LOCK TABLES `procedimiento` WRITE;
/*!40000 ALTER TABLE `procedimiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `procedimiento` ENABLE KEYS */;
UNLOCK TABLES;

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
  `id_procedimiento` int NOT NULL,
  PRIMARY KEY (`id_procedimiento_usuario`),
  KEY `dniUsuario_fk` (`dni_usuario`),
  KEY `idProcedimiento_fk` (`id_procedimiento`),
  CONSTRAINT `dniUsuario_fk` FOREIGN KEY (`dni_usuario`) REFERENCES `usuario` (`dni_usuario`) ON UPDATE CASCADE,
  CONSTRAINT `idProcedimiento_fk` FOREIGN KEY (`id_procedimiento`) REFERENCES `procedimiento` (`id_procedimiento`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procedimientousuario`
--

LOCK TABLES `procedimientousuario` WRITE;
/*!40000 ALTER TABLE `procedimientousuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `procedimientousuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procedimientousuarioproceso`
--

DROP TABLE IF EXISTS `procedimientousuarioproceso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `procedimientousuarioproceso` (
  `id_procedimiento_usuario_proceso` int NOT NULL,
  `fecha_procedimiento_usuario_proceso` date NOT NULL,
  `borrado_procedimiento_usuario_proceso` int NOT NULL,
  `id_respuesta` int NOT NULL,
  `id_proceso` int NOT NULL,
  PRIMARY KEY (`id_procedimiento_usuario_proceso`,`id_proceso`),
  KEY `id_procedimiento_usuario_proceso_idx` (`id_procedimiento_usuario_proceso`),
  KEY `id_proceso_procedimiento_usuario_proceso_idx` (`id_proceso`),
  KEY `idRespuesta_procedimiento_usuario_proceso_fk` (`id_respuesta`),
  KEY `idProceso_procedimiento_usuario_proceso_fk` (`id_procedimiento_usuario_proceso`),
  CONSTRAINT `idProceso_procedimiento_usuario_proceso_fk` FOREIGN KEY (`id_proceso`) REFERENCES `proceso` (`id_proceso`),
  CONSTRAINT `idRespuesta_procedimiento_usuario_proceso_fk` FOREIGN KEY (`id_respuesta`) REFERENCES `respuesta_posible` (`id_respuesta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procedimientousuarioproceso`
--

LOCK TABLES `procedimientousuarioproceso` WRITE;
/*!40000 ALTER TABLE `procedimientousuarioproceso` DISABLE KEYS */;
/*!40000 ALTER TABLE `procedimientousuarioproceso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proceso`
--

DROP TABLE IF EXISTS `proceso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proceso` (
  `id_proceso` int NOT NULL AUTO_INCREMENT,
  `nombre_proceso` varchar(48) NOT NULL,
  `descrip_proceso` text NOT NULL,
  `fecha_proceso` date NOT NULL,
  `borrado_procedimiento` int NOT NULL,
  `id_procedimiento` int NOT NULL,
  PRIMARY KEY (`id_proceso`),
  UNIQUE KEY `nombre_proceso_UNIQUE` (`nombre_proceso`),
  KEY `idProcedimiento_fk` (`id_procedimiento`),
  CONSTRAINT `id_Procedimiento_fk` FOREIGN KEY (`id_procedimiento`) REFERENCES `procedimiento` (`id_procedimiento`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proceso`
--

LOCK TABLES `proceso` WRITE;
/*!40000 ALTER TABLE `proceso` DISABLE KEYS */;
/*!40000 ALTER TABLE `proceso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procesoprocedimiento`
--

DROP TABLE IF EXISTS `procesoprocedimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `procesoprocedimiento` (
  `id_proceso` int NOT NULL,
  `id_procedimiento` int NOT NULL,
  PRIMARY KEY (`id_proceso`,`id_procedimiento`),
  KEY `id_proceso_idx` (`id_proceso`),
  KEY `id_procedimiento_idx` (`id_procedimiento`),
  KEY `procesoprocedimiento_proceso_fk` (`id_proceso`),
  KEY `procesoprocedimiento_procedimiento_fk` (`id_procedimiento`),
  CONSTRAINT `procesoprocedimiento_procedimiento_fk` FOREIGN KEY (`id_procedimiento`) REFERENCES `procedimiento` (`id_procedimiento`),
  CONSTRAINT `procesoprocedimiento_proceso_fk` FOREIGN KEY (`id_proceso`) REFERENCES `proceso` (`id_proceso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procesoprocedimiento`
--

LOCK TABLES `procesoprocedimiento` WRITE;
/*!40000 ALTER TABLE `procesoprocedimiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `procesoprocedimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procesorespuesta_posible`
--

DROP TABLE IF EXISTS `procesorespuesta_posible`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `procesorespuesta_posible` (
  `id_proceso` int NOT NULL,
  `id_respuesta` int NOT NULL,
  PRIMARY KEY (`id_proceso`,`id_respuesta`),
  KEY `id_proceso_idx` (`id_proceso`),
  KEY `id_respuesta_idx` (`id_respuesta`),
  KEY `procesorespuesta_posible_proceso_fk` (`id_proceso`),
  KEY `procesorespuesta_posible_respuestaPosible_fk` (`id_respuesta`),
  CONSTRAINT `procesorespuesta_posible_proceso_fk` FOREIGN KEY (`id_proceso`) REFERENCES `proceso` (`id_proceso`),
  CONSTRAINT `procesorespuesta_posible_respuestaPosible_fk` FOREIGN KEY (`id_respuesta`) REFERENCES `respuesta_posible` (`id_respuesta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procesorespuesta_posible`
--

LOCK TABLES `procesorespuesta_posible` WRITE;
/*!40000 ALTER TABLE `procesorespuesta_posible` DISABLE KEYS */;
/*!40000 ALTER TABLE `procesorespuesta_posible` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `respuesta_posible`
--

DROP TABLE IF EXISTS `respuesta_posible`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `respuesta_posible` (
  `id_respuesta` int NOT NULL AUTO_INCREMENT,
  `texto_respuesta` text NOT NULL,
  `fecha_respuesta` date NOT NULL,
  `borrado_respuesta` int NOT NULL,
  PRIMARY KEY (`id_respuesta`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `respuesta_posible`
--

LOCK TABLES `respuesta_posible` WRITE;
/*!40000 ALTER TABLE `respuesta_posible` DISABLE KEYS */;
/*!40000 ALTER TABLE `respuesta_posible` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id_rol` int NOT NULL AUTO_INCREMENT,
  `rol_name` varchar(32) NOT NULL,
  `rol_description` text NOT NULL,
  `borrado_rol` int NOT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'admin','admin',0),(2,'usuario','Contendra a todos los usuarios registrados de la aplicacion',0),(3,'superadministrador','Contendrá a todos los superadministradores de la aplicacion',1);
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rolaccionfuncionalidad`
--

DROP TABLE IF EXISTS `rolaccionfuncionalidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rolaccionfuncionalidad` (
  `id_rol` int NOT NULL,
  `id_accion` int NOT NULL,
  `id_funcionalidad` int NOT NULL,
  PRIMARY KEY (`id_rol`,`id_accion`,`id_funcionalidad`),
  KEY `id_rol_idx` (`id_rol`),
  KEY `id_accion_idx` (`id_accion`),
  KEY `id_funcionalidad_idx` (`id_funcionalidad`),
  KEY `rolaccionfuncionalidad_accion_fk` (`id_accion`),
  KEY `rolaccionfuncionalidad_funcionalidad_fk` (`id_funcionalidad`),
  KEY `rolaccionfuncionalidad_rol_fk` (`id_rol`),
  CONSTRAINT `rolaccionfuncionalidad_accion_fk` FOREIGN KEY (`id_accion`) REFERENCES `accion` (`id_accion`),
  CONSTRAINT `rolaccionfuncionalidad_funcionalidad_fk` FOREIGN KEY (`id_funcionalidad`) REFERENCES `funcionalidad` (`id_funcionalidad`),
  CONSTRAINT `rolaccionfuncionalidad_rol_fk` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rolaccionfuncionalidad`
--

LOCK TABLES `rolaccionfuncionalidad` WRITE;
/*!40000 ALTER TABLE `rolaccionfuncionalidad` DISABLE KEYS */;
INSERT INTO `rolaccionfuncionalidad` VALUES (1,1,1),(1,1,2),(1,1,3),(1,1,4),(1,1,5),(1,1,6),(1,1,7),(1,1,8),(1,1,9);
/*!40000 ALTER TABLE `rolaccionfuncionalidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `dni_usuario` varchar(9) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `passwd_usuario` varchar(45) NOT NULL,
  `borrado_usuario` int NOT NULL,
  `id_rol` int NOT NULL,
  PRIMARY KEY (`dni_usuario`),
  UNIQUE KEY `dniUsuario_UNIQUE` (`dni_usuario`),
  UNIQUE KEY `usuario_UNIQUE` (`usuario`),
  KEY `id_rol_usuario_idx` (`id_rol`),
  CONSTRAINT `dniUsuarioPersona_fk` FOREIGN KEY (`dni_usuario`) REFERENCES `persona` (`dni_persona`) ON UPDATE CASCADE,
  CONSTRAINT `idRol_fk` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('44460662Y','aicuna','43c73508499069fa09afaa010c6ef2f6',0,1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-29 18:28:15
