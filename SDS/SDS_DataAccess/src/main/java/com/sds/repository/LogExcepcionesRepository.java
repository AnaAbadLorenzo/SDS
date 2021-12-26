package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.sds.model.LogExcepcionesEntity;

@Component
public interface LogExcepcionesRepository extends JpaRepository<LogExcepcionesEntity, String> {

	List<LogExcepcionesEntity> findByUsuario(String usuario);
}
