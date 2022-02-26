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

	List<AccionEntity> findAllAccion();

	Integer numberFindAllAccion();

	List<AccionEntity> findAccion(String nombreAccion, String descripAccion);

	Integer numberFindAccion(String nombreAccion, String descripAccion);

	List<AccionEntity> findAccionesEliminadas();

	Integer numberFindAccionesEliminadas();

	@Transactional
	@Modifying
	@Query("DELETE FROM AccionEntity a WHERE a.idAccion = ?1")
	void deleteAccion(Integer idAccion);
}
