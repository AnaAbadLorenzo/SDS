package com.sds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.EmpresaEntity;

@Component
public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Integer> {

	EmpresaEntity findByCif(String cifEmpresa);

	@Transactional
	@Modifying
	@Query("DELETE FROM EmpresaEntity e WHERE e.cifEmpresa = ?1")
	void deleteEmpresa(String cifEmpresa);
}
