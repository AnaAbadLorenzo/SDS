package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.EmpresaEntity;

@Component
public interface EmpresaRepository extends JpaRepository<EmpresaEntity, Integer> {

	EmpresaEntity findByCif(String cifEmpresa);

	List<EmpresaEntity> findEmpresa(String cifEmpresa, String nombreEmpresa, String direccionEmpresa,
			String telefonoEmpresa);

	List<EmpresaEntity> findEmpresasEliminadas(Integer borradoEmpresa);

	@Transactional
	@Modifying
	@Query("DELETE FROM EmpresaEntity e WHERE e.cifEmpresa = ?1")
	void deleteEmpresa(String cifEmpresa);
}
