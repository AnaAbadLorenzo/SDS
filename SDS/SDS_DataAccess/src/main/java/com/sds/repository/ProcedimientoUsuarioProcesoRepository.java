package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.ProcedimientoUsuarioEntity;
import com.sds.model.ProcedimientoUsuarioProcesoEntity;
import com.sds.model.ProcesoEntity;

@Component
public interface ProcedimientoUsuarioProcesoRepository
		extends JpaRepository<ProcedimientoUsuarioProcesoEntity, Integer> {

	List<ProcedimientoUsuarioProcesoEntity> findProcedimientoUsuarioProcesoByIdProceso(final ProcesoEntity proceso);

	List<ProcedimientoUsuarioProcesoEntity> findProcedimientoUsuarioProcesoByIdProcedimientoUsuario(
			final ProcedimientoUsuarioEntity procedimientoUsuario);

	ProcedimientoUsuarioProcesoEntity findProcedimientoUsuarioProceso(
			final ProcedimientoUsuarioEntity procedimientoUsuario, final ProcesoEntity proceso);

	List<ProcedimientoUsuarioProcesoEntity> findAllProcedimientosUsuariosProcesos(final int inicio,
			final int tamanhoPagina);

	Integer numberFindAllProcedimientosUsuariosProcesos();

	@Transactional
	@Modifying
	@Query("DELETE FROM ProcedimientoUsuarioProcesoEntity p WHERE p.idProcedimientoUsuarioProceso = ?1")
	void deleteProcedimientoUsuarioProceso(Integer idProcedimientoUsuarioProceso);
}
