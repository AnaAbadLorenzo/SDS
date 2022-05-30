package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.RolAccionFuncionalidadEntity;
import com.sds.model.compositekey.RolAccionFuncionalidadKey;

@Component
public interface RolAccionFuncionalidadRepository
		extends JpaRepository<RolAccionFuncionalidadEntity, RolAccionFuncionalidadKey> {

	List<Integer> findFuncionalityByRolId(Integer idRol);

	List<Integer> findAccionByIdFuncionality(Integer idFuncionalidad);

	List<Integer> findAccionByIdFuncionalityAndIdRol(Integer idFuncionalidad, Integer idRol);

	List<RolAccionFuncionalidadEntity> findByAccionId(Integer idAccion);

	List<RolAccionFuncionalidadEntity> findAllPermissions();

	Integer numberFindAllPermissions();

	List<RolAccionFuncionalidadEntity> findByFuncionalityId(Integer idFuncionalidad);

	@Transactional
	@Modifying
	@Query("DELETE FROM RolAccionFuncionalidadEntity a WHERE a.idAccion = ?1 AND a.idRol =?2 AND a.idFuncionalidad = ?3")
	void deleteRolAccionFuncionalidad(Integer idAccion, Integer idRol, Integer idFuncionalidad);
}
