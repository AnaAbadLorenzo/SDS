package com.sds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sds.model.AccionEntity;

public interface AccionRepository extends JpaRepository<AccionEntity, Integer>{

	String findNombreAccionById(Integer idAccion);
}
