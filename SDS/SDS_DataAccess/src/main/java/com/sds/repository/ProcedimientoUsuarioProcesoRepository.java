package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.ProcedimientoUsuarioProcesoEntity;
import com.sds.model.compositekey.ProcedimientoUsuarioProcesoKey;

@Component
public interface ProcedimientoUsuarioProcesoRepository
		extends JpaRepository<ProcedimientoUsuarioProcesoEntity, ProcedimientoUsuarioProcesoKey> {

	List<ProcedimientoUsuarioProcesoEntity> findProcedimientoUsuarioProcesoByIdProceso(final Integer idProceso);

	List<ProcedimientoUsuarioProcesoEntity> findProcedimientoUsuarioProcesoByIdProcedimientoUsuario(
			final Integer idProcedimientoUsuario);

	@Transactional
	@Modifying
	@Query("DELETE FROM ProcedimientoUsuarioProcesoEntity p WHERE p.idProceso = ?1 AND p.idProcedimientoUsuario = ?2")
	void deleteProcedimientoUsuarioProceso(Integer idProceso, Integer idProcedimientoUsuario);
}
