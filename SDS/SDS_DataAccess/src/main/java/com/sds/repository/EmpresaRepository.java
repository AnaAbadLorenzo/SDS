package com.sds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.sds.model.EmpresaEntity;

@Component
public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Integer> {

	EmpresaEntity findByCif(String cifEmpresa);
}
