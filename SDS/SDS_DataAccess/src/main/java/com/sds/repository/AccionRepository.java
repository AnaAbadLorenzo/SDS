package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.AccionEntity;

public interface AccionRepository extends JpaRepository<AccionEntity, Integer> {

	String findNombreAccionById(Integer idAccion);

	AccionEntity findAccionByName(String nombreAccion);

	List<AccionEntity> findAccionesEliminadas(Integer borradoAccion);

	@Transactional
	@Modifying
	@Query("DELETE FROM AccionEntity a WHERE a.idAccion = ?1")
	void deleteAccion(Integer idAccion);
}
