package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sds.model.RolAccionFuncionalidadEntity;
import com.sds.model.compositekey.RolAccionFuncionalidadKey;

public interface RolAccionFuncionalidadRepository
		extends JpaRepository<RolAccionFuncionalidadEntity, RolAccionFuncionalidadKey> {

	List<Integer> findFuncionalityByRolId(Integer idRol);

	List<Integer> findAccionByIdFuncionality(Integer idFuncionalidad);
}
