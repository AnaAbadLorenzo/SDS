package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.FuncionalidadEntity;

public interface FuncionalidadRepository extends JpaRepository<FuncionalidadEntity, Integer> {

	FuncionalidadEntity findByIdFuncionalidad(Integer idFuncionalidad);

	Integer findIdFuncionalidadByName(String nombreFuncionalidad);

	FuncionalidadEntity findFuncionalityByName(String nombreFuncionalidad);

	List<FuncionalidadEntity> findFuncionality(String nombreFuncionalidad, String descripFuncionalidad);

	List<FuncionalidadEntity> findFuncionalidadesEliminadas(Integer borradoFuncionalidad);

	@Transactional
	@Modifying
	@Query("DELETE FROM FuncionalidadEntity f WHERE f.idFuncionalidad = ?1")
	void deleteFuncionalidad(Integer idFuncionalidad);
}
