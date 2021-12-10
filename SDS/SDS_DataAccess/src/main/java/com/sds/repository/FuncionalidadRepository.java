package com.sds.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sds.model.FuncionalidadEntity;

public interface FuncionalidadRepository extends JpaRepository<FuncionalidadEntity, Integer> {

	FuncionalidadEntity findByIdFuncionalidad(Integer idFuncionalidad);

	Integer findIdFuncionalidadByName(String nombreFuncionalidad);
}
