package com.sds.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.sds.model.LogAccionesEntity;

@Component
public interface LogAccionesRepository extends JpaRepository<LogAccionesEntity, String> {

	List<LogAccionesEntity> findByUsuario(String usuario);

	List<LogAccionesEntity> findByAccion(String accion);

	List<LogAccionesEntity> findByUsuarioAccion(String usuario, String accion);

	List<LogAccionesEntity> findAllLogAcciones();

	Integer numberFindAllLogAcciones();

	List<LogAccionesEntity> findLogAcciones(String usuario, Date fechaInicio, Date fechaFin);

	Integer numberFindLogAcciones(String usuario, Date fechaInicio, Date fechaFin);

}