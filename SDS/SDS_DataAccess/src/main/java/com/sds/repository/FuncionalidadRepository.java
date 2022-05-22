package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.FuncionalidadEntity;

@Component
public interface FuncionalidadRepository extends JpaRepository<FuncionalidadEntity, Integer> {

	FuncionalidadEntity findByIdFuncionalidad(Integer idFuncionalidad);

	Integer findIdFuncionalidadByName(String nombreFuncionalidad);

	FuncionalidadEntity findFuncionalityByName(String nombreFuncionalidad);

	List<FuncionalidadEntity> findAllFuncionalities();

	Integer numberFindAllFuncionalities();

	List<FuncionalidadEntity> findFuncionality(String nombreFuncionalidad, String descripFuncionalidad);

	Integer numberFindFuncionality(String nombreFuncionalidad, String descripFuncionalidad);

	List<FuncionalidadEntity> findFuncionalidadesEliminadas();

	Integer numberFindFuncionalidadesEliminadas();

	@Transactional
	@Modifying
	@Query("DELETE FROM FuncionalidadEntity f WHERE f.idFuncionalidad = ?1")
	void deleteFuncionalidad(Integer idFuncionalidad);
}
