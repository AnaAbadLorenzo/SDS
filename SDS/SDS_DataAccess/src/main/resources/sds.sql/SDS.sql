-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: sds
-- ------------------------------------------------------
-- Server version	8.0.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


DROP USER IF EXISTS `user_sds`@`localhost`;
 
--
-- CREAMOS EL USUARIO Y LE DAMOS PASSWORD,DAMOS PERMISO DE USO Y DAMOS PERMISOS SOBRE LA BASE DE DATOS.
--
 
CREATE USER IF NOT EXISTS 'user_sds'@'localhost' IDENTIFIED BY 'user_sds';
GRANT ALL ON *.* TO 'user_sds'@'localhost';
CREATE USER IF NOT EXISTS 'user_sds'@'%' IDENTIFIED BY 'user_sds';
GRANT ALL ON *.* TO 'user_sds'@'%';
FLUSH PRIVILEGES;

--
-- CREACION BD
--
DROP DATABASE IF EXISTS `sds`;
CREATE DATABASE `sds` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

--
-- SELECCIONAMOS PARA USAR
--
USE `sds`;

--
-- Table structure for table `accion`
--

DROP TABLE IF EXISTS `accion`;
CREATE TABLE `accion` (
  `id_accion` int NOT NULL AUTO_INCREMENT,
  `nombre_accion` varchar(48) NOT NULL,
  `descrip_accion` text NOT NULL,
  `borrado_accion` int NOT NULL,
  PRIMARY KEY (`id_accion`),
  UNIQUE KEY `nombre_accion_UNIQUE` (`nombre_accion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

--
-- Dumping data for table `accion`
--

LOCK TABLES `accion` WRITE;
INSERT INTO `accion` VALUES 
(1,'Añadir','Permite añadir nuevos datos a la aplicación',0),
(2,'Modificar','Permite modificar datos de la aplicación',0),
(3,'Eliminar','Permite eliminar datos de la aplicación',0),
(4,'Listar','Permite visualizar los datos de la aplicación en forma de listado',0),
(5,'Visualizar','Permite ver en detalle los datos de la aplicación',0),
(6,'Reactivar','Permite reactivar los datos eliminados de la aplicación',0);
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
CREATE TABLE `empresa` (
  `id_empresa` int NOT NULL AUTO_INCREMENT,
  `cif_empresa` varchar(9) NOT NULL,
  `nombre_empresa` varchar(48) NOT NULL,
  `direccion_empresa` varchar(128) NOT NULL,
  `telefono_empresa` varchar(9) NOT NULL,
  `borrado_empresa` int NOT NULL,
  PRIMARY KEY (`id_empresa`),
  UNIQUE KEY `cifEmpresa_UNIQUE` (`cif_empresa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
INSERT INTO `empresa` VALUES (1,'J26903286','Prueba','Prueba','988212121',0);
UNLOCK TABLES;

--
-- Table structure for table `funcionalidad`
--

DROP TABLE IF EXISTS `funcionalidad`;
CREATE TABLE `funcionalidad` (
  `id_funcionalidad` int NOT NULL AUTO_INCREMENT,
  `nombre_funcionalidad` varchar(48) NOT NULL,
  `descrip_funcionalidad` text NOT NULL,
  `borrado_funcionalidad` int NOT NULL,
  PRIMARY KEY (`id_funcionalidad`),
  UNIQUE KEY `nombre_funcionalidad_UNIQUE` (`nombre_funcionalidad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

--
-- Dumping data for table `funcionalidad`
--

LOCK TABLES `funcionalidad` WRITE;
INSERT INTO `funcionalidad` VALUES 
('1','Gestión de acciones','Permite realizar acciones sobre las acciones de la aplicación',0),
('2','Gestión de empresas','Permite realizar acciones sobre las empresas de la aplicación',0),
('3','Gestión de funcionalidades','Permite realizar acciones sobre las funcionalidades de la aplicación',0),
('4', 'Gestión de noticias', 'Permite realizar acciones sobre las noticias de la aplicación', '0'),
('5', 'Gestión de objetivos', 'Permite realizar acciones sobre los objetivos de la aplicación', '0'),
('6','Gestión de personas','Permite realizar acciones sobre las personas de la aplicación',0),
('7', 'Gestión de planes', 'Permite realizar acciones sobre los planes de la aplicación', '0'), 
('8', 'Gestión de procedimientos', 'Permite realizar acciones sobre los procedimientos de la aplicación', '0'),
('9', 'Gestión de procedimientos ejecutados', 'Permite realizar acciones sobre los procedimientos ejecutados por los usuarios de la aplicación', '0'),
('10', 'Gestión de procesos', 'Permite realizar acciones sobre los procesos de la aplicación', '0'),
('11', 'Gestión de procesos ejecutados', 'Permite realizar acciones sobre los procesos ejecutados por los usuarios de la aplicación', '0'),
('12', 'Gestión de respuestas posibles', 'Permite realizar acciones sobre las respuestas posibles de la aplicación', '0'), 
('13','Gestión de roles','Permite realizar acciones sobre los roles de la aplicación',0),
('14','Gestión de usuarios','Permite realizar acciones sobre los usuarios de la aplicación',0),
('15','Log de acciones','Permite ver los logs almacenados de acciones llevadas a cabo por los usuarios del sistema',0),
('16','Log de excepciones','Permite ver los logs almacenados de excepciones que se han producido en el sistema',0),
('17','Test','Permite ver los test de acciones y de atributos realizados sobre las funcionalidades',0);
UNLOCK TABLES;

--
-- Table structure for table `logacciones`
--

DROP TABLE IF EXISTS `logacciones`;
CREATE TABLE `logacciones` (
  `id_logAcciones` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) NOT NULL,
  `accion` varchar(50) NOT NULL,
  `datos` varchar(2000) NOT NULL,
  `fecha` datetime NOT NULL,
  PRIMARY KEY (`id_logAcciones`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

--
-- Dumping data for table `logacciones`
--

LOCK TABLES `logacciones` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `logexcepciones`
--

DROP TABLE IF EXISTS `logexcepciones`;
CREATE TABLE `logexcepciones` (
  `id_logExcepciones` int NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) NOT NULL,
  `tipo_excepcion` varchar(50) NOT NULL,
  `descripcion_excepcion` varchar(200) NOT NULL,
  `fecha` datetime NOT NULL,
  PRIMARY KEY (`id_logExcepciones`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

--
-- Dumping data for table `logexcepciones`
--

LOCK TABLES `logexcepciones` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `noticias`
--

DROP TABLE IF EXISTS `noticias`;
CREATE TABLE `noticias` (
  `id_noticia` int NOT NULL AUTO_INCREMENT,
  `titulo_noticia` varchar(256) NOT NULL,
  `texto_noticia` text NOT NULL,
  `fecha_noticia` date NOT NULL,
  PRIMARY KEY (`id_noticia`),
  UNIQUE KEY `titulo_noticia_UNIQUE` (`titulo_noticia`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

--
-- Dumping data for table `noticias`
--

LOCK TABLES `noticias` WRITE;
INSERT INTO `noticias` VALUES (1,'Bienvenidos a SDS','Os damos la bienvenida a SDS, una web donde podréis certificaros con los objetivos de la ONU 2030','2022-04-15');
UNLOCK TABLES;

--
-- Table structure for table `objetivo`
--

DROP TABLE IF EXISTS `objetivo`;
CREATE TABLE `objetivo` (
  `id_objetivo` int NOT NULL AUTO_INCREMENT,
  `nombre_objetivo` varchar(48) NOT NULL,
  `descripcion_objetivo` text NOT NULL,
  `borrado_objetivo` int NOT NULL,
  PRIMARY KEY (`id_objetivo`),
  UNIQUE KEY `nombre_objetivo_UNIQUE` (`nombre_objetivo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

--
-- Dumping data for table `objetivo`
--

LOCK TABLES `objetivo` WRITE;
INSERT INTO `objetivo` VALUES (1,'Fin de la pobreza','Objetivo de la ONU 2030 número 1',0);
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
INSERT INTO `persona` VALUES 
('44460662Y', 'Alexandra', 'Iglesias', '1987-03-09', 'Prueba', '988222222', 'a@a.com', 0, 1),
('44460663F', 'UsuarioGenerico', 'UsuarioGenerico', '1980-01-01', 'Dirección Genérica', '988222222', 'b@b.com', 0, NULL),
('10128407N', 'UsuarioGestor', 'UsuarioGestor', '1966-01-01', 'Dirección Gestor', '988525225', 'c@c.com', '0', NULL);
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
CREATE TABLE `rol` (
  `id_rol` int NOT NULL AUTO_INCREMENT,
  `rol_name` varchar(32) NOT NULL,
  `rol_description` text NOT NULL,
  `borrado_rol` int NOT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
INSERT INTO `rol` VALUES 
(1,'admin','Contendrá a todos los administradores de la aplicación',0),
(2,'usuario','Contendra a todos los usuarios registrados de la aplicacion',0),
(3,'gestor','Contendrá a todos los gestores de la aplicación',0);
UNLOCK TABLES;

--
-- Table structure for table `rolaccionfuncionalidad`
--

DROP TABLE IF EXISTS `rolaccionfuncionalidad`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

--
-- Dumping data for table `rolaccionfuncionalidad`
--

LOCK TABLES `rolaccionfuncionalidad` WRITE;
INSERT INTO `rolaccionfuncionalidad` (`id_rol`, `id_accion`, `id_funcionalidad`) VALUES 
(1,1,1), (1,1,2), (1,1,3), (1,1,4), (1,1,5), (1,1,6), (1,1,7), (1,1,8), (1,1,9), (1,1,10), (1,1,11), (1,1,12), (1,1,13), (1,1,14),
(1,2,1), (1,2,2), (1,2,3), (1,2,4), (1,2,5), (1,2,6), (1,2,7), (1,2,8), (1,2,9), (1,2,10), (1,2,11), (1,2,12), (1,2,13), (1,2,14),
(1,3,1), (1,3,2), (1,3,3), (1,3,4), (1,3,5), (1,3,6), (1,3,7), (1,3,8), (1,3,9), (1,3,10), (1,3,11), (1,3,12), (1,3,13), (1,3,14),
(1,4,1), (1,4,2), (1,4,3), (1,4,4), (1,4,5), (1,4,6), (1,4,7), (1,4,8), (1,4,9), (1,4,10), (1,4,11), (1,4,12), (1,4,13), (1,4,14), (1,4,15), (1,4,16), (1,4,17),
(1,5,1), (1,5,2), (1,5,3), (1,5,4), (1,5,5), (1,5,6), (1,5,7), (1,5,8), (1,5,9), (1,5,10), (1,5,11), (1,5,12), (1,5,13), (1,5,14), (1,5,15), (1,5,16),
(1,6,1), (1,6,2), (1,6,3), (1,6,4), (1,6,5), (1,6,6), (1,6,7), (1,6,8), (1,6,9), (1,6,10), (1,6,11), (1,6,12), (1,6,13), (1,6,14),
(2,2,2), (2,2,6), (2,4,2), (2,4,6), (2,4,7), (2,4,8), (2,4,14), (2,5,14),
(3,1,4), (3,1,5), (3,1,7), (3,1,8), (3,1,9), (3,1,10), (3,1,11), (3,1,12),
(3,2,2), (3,2,4), (3,2,5), (3,2,6), (3,2,7), (3,2,8), (3,2,9), (3,2,10), (3,2,11), (3,2,12), 
(3,3,4), (3,3,5), (3,3,7), (3,3,8), (3,3,9), (3,3,10), (3,3,11), (3,3,12), 
(3,4,2), (3,4,4), (3,4,5), (3,4,6), (3,4,7), (3,4,8), (3,4,9), (3,4,10), (3,4,11), (3,4,12), (3,4,14), 
(3,5,4), (3,5,5), (3,5,7), (3,5,8), (3,5,9), (3,5,10), (3,5,11), (3,5,12), (3,5,14), 
(3,6,4), (3,6,5), (3,6,7), (3,6,8), (3,6,9), (3,6,10), (3,6,11), (3,6,12);
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
INSERT INTO `usuario` VALUES 
('44460662Y', 'aicuna', '43c73508499069fa09afaa010c6ef2f6', 0, 1),
('44460663F', 'UsuarioGenerico', '9731bc6492878b96f1d7b55bc2d289b9', '0', '2'),
('10128407N', 'UsuarioGestor', 'a0489287a565763b5681bb057204dc33', '0', '3');
UNLOCK TABLES;


--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
INSERT INTO `plan` (`id_plan`, `nombre_plan`, `descrip_plan`, `fecha_plan`, `borrado_plan`, `id_objetivo`) VALUES 
('1', 'Plan', 'Plan para verificar el objetivo fin de la pobreza', '2022-05-20', '0', '1');
UNLOCK TABLES;

--
-- Table structure for table `procedimiento`
--

DROP TABLE IF EXISTS `procedimiento`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

--
-- Dumping data for table `procedimiento`
--

LOCK TABLES `procedimiento` WRITE;
INSERT INTO `procedimiento` (`id_procedimiento`, `nombre_procedimiento`, `descrip_procedimiento`, `fecha_procedimiento`, `borrado_procedimiento`, `check_usuario`, `id_plan`) VALUES 
('1', 'Procedimiento', 'Este es el primer procedimiento', '2022-05-20', '0', '1', '1');
UNLOCK TABLES;

--
-- Table structure for table `procedimientousuario`
--

DROP TABLE IF EXISTS `procedimientousuario`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

--
-- Dumping data for table `procedimientousuario`
--

LOCK TABLES `procedimientousuario` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `proceso`
--

DROP TABLE IF EXISTS `proceso`;
CREATE TABLE `proceso` (
  `id_proceso` int NOT NULL AUTO_INCREMENT,
  `nombre_proceso` varchar(48) NOT NULL,
  `descrip_proceso` text NOT NULL,
  `fecha_proceso` date NOT NULL,
  `borrado_proceso` int NOT NULL,
  PRIMARY KEY (`id_proceso`),
  UNIQUE KEY `nombre_proceso_UNIQUE` (`nombre_proceso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

--
-- Dumping data for table `proceso`
--

LOCK TABLES `proceso` WRITE;
INSERT INTO `proceso` (`id_proceso`, `nombre_proceso`, `descrip_proceso`, `fecha_proceso`, `borrado_proceso`) VALUES 
('1', 'Primer Proceso', 'Este es el primer proceso', '2022-07-09', '0');
UNLOCK TABLES;

--
-- Table structure for table `respuesta_posible`
--

DROP TABLE IF EXISTS `respuesta_posible`;
CREATE TABLE `respuesta_posible` (
  `id_respuesta` int NOT NULL AUTO_INCREMENT,
  `texto_respuesta` text NOT NULL,
  `borrado_respuesta` int NOT NULL,
  PRIMARY KEY (`id_respuesta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

--
-- Dumping data for table `respuesta_posible`
--

LOCK TABLES `respuesta_posible` WRITE;
INSERT INTO `respuesta_posible` (`id_respuesta`, `texto_respuesta`, `borrado_respuesta`) VALUES 
('1', 'Sí', '0'), ('2', 'No', 0), ('3', 'A veces', 0), ('4', 'No aplica', 0);
UNLOCK TABLES;

--
-- Table structure for table `procedimientousuarioproceso`
--

DROP TABLE IF EXISTS `procedimientousuarioproceso`;
CREATE TABLE `procedimientousuarioproceso` (
  `id_procedimiento_usuario_proceso`int NOT NULL AUTO_INCREMENT,
  `id_procedimiento_usuario` int NOT NULL,
  `fecha_procedimiento_usuario_proceso` date NOT NULL,
  `borrado_procedimiento_usuario_proceso` int NOT NULL,
  `id_respuesta` int NOT NULL,
  `id_proceso` int NOT NULL,
  PRIMARY KEY (`id_procedimiento_usuario_proceso`),
  KEY `id_procedimiento_usuario_proceso_idx` (`id_procedimiento_usuario`),
  KEY `id_proceso_procedimiento_usuario_proceso_idx` (`id_proceso`),
  KEY `idRespuesta_procedimiento_usuario_proceso_fk` (`id_respuesta`),
  CONSTRAINT `idProceso_procedimiento_usuario_fk` FOREIGN KEY (`id_procedimiento_usuario`) REFERENCES `procedimientousuario` (`id_procedimiento_usuario`),
  CONSTRAINT `idProceso_procedimiento_usuario_proceso_fk` FOREIGN KEY (`id_proceso`) REFERENCES `proceso` (`id_proceso`),
  CONSTRAINT `idRespuesta_procedimiento_usuario_proceso_fk` FOREIGN KEY (`id_respuesta`) REFERENCES `respuesta_posible` (`id_respuesta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

--
-- Dumping data for table `procedimientousuarioproceso`
--

LOCK TABLES `procedimientousuarioproceso` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `nivel`
--

DROP TABLE IF EXISTS `nivel`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

--
-- Dumping data for table `nivel`
--

LOCK TABLES `nivel` WRITE;
INSERT INTO `nivel` (`id_objetivo`, `id_proceso`, `nivel`, `fecha_nivel`) VALUES 
(1, 1, 1, '2022-07-09');
UNLOCK TABLES;

--
-- Table structure for table `procesoprocedimiento`
--

DROP TABLE IF EXISTS `procesoprocedimiento`;
CREATE TABLE `procesoprocedimiento` (
  `id_proceso` int NOT NULL,
  `id_procedimiento` int NOT NULL,
  `orden_proceso` int NOT NULL,
  PRIMARY KEY (`id_proceso`,`id_procedimiento`),
  KEY `id_proceso_idx` (`id_proceso`),
  KEY `id_procedimiento_idx` (`id_procedimiento`),
  KEY `procesoprocedimiento_proceso_fk` (`id_proceso`),
  KEY `procesoprocedimiento_procedimiento_fk` (`id_procedimiento`),
  CONSTRAINT `procesoprocedimiento_procedimiento_fk` FOREIGN KEY (`id_procedimiento`) REFERENCES `procedimiento` (`id_procedimiento`),
  CONSTRAINT `procesoprocedimiento_proceso_fk` FOREIGN KEY (`id_proceso`) REFERENCES `proceso` (`id_proceso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

--
-- Dumping data for table `procesoprocedimiento`
--

LOCK TABLES `procesoprocedimiento` WRITE;
INSERT INTO `procesoprocedimiento` (`id_proceso`, `id_procedimiento`, `orden_proceso`) VALUES (1, 1, 1);
UNLOCK TABLES;

--
-- Table structure for table `evidencia`
--

DROP TABLE IF EXISTS `evidencia`;
CREATE TABLE `evidencia` (
  `id_evidencia` int NOT NULL AUTO_INCREMENT,
  `fecha_evidencia` date NOT NULL,
  `nombre_fichero` varchar(128) NOT NULL,
  `borrado_evidencia` int NOT NULL,
  `id_procedimiento_usuario_proceso` int NOT NULL,
  `ruta_evidencia` TEXT NOT NULL,
  PRIMARY KEY (`id_evidencia`),
  KEY `idProcedimiento_usuario_proceso_fk` (`id_procedimiento_usuario_proceso`),
  CONSTRAINT `idProcedimiento_usuario_proceso_fk` FOREIGN KEY (`id_procedimiento_usuario_proceso`) REFERENCES `procedimientousuarioproceso` (`id_procedimiento_usuario_proceso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;


--
-- Dumping data for table `evidencia`
--

LOCK TABLES `evidencia` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `procesorespuesta_posible`
--

DROP TABLE IF EXISTS `procesorespuesta_posible`;
CREATE TABLE `procesorespuesta_posible` (
  `id_proceso` int NOT NULL,
  `id_respuesta` int NOT NULL,
  `fecha_respuesta` date NOT NULL,
  PRIMARY KEY (`id_proceso`,`id_respuesta`),
  KEY `id_proceso_idx` (`id_proceso`),
  KEY `id_respuesta_idx` (`id_respuesta`),
  KEY `procesorespuesta_posible_proceso_fk` (`id_proceso`),
  KEY `procesorespuesta_posible_respuestaPosible_fk` (`id_respuesta`),
  CONSTRAINT `procesorespuesta_posible_proceso_fk` FOREIGN KEY (`id_proceso`) REFERENCES `proceso` (`id_proceso`),
  CONSTRAINT `procesorespuesta_posible_respuestaPosible_fk` FOREIGN KEY (`id_respuesta`) REFERENCES `respuesta_posible` (`id_respuesta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;

--
-- Dumping data for table `procesorespuesta_posible`
--

LOCK TABLES `procesorespuesta_posible` WRITE;
INSERT INTO `procesorespuesta_posible` (`id_proceso`, `id_respuesta`, `fecha_respuesta`) VALUES 
(1, 1, '2022-07-09'), (1, 2, '2022-07-09'), (1, 3, '2022-07-09');
UNLOCK TABLES;

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
