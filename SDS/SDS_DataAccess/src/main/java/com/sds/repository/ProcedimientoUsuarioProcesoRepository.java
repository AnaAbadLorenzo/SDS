package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sds.model.ProcedimientoUsuarioProcesoEntity;
import com.sds.model.compositekey.ProcedimientoUsuarioProcesoKey;

public interface ProcedimientoUsuarioProcesoRepository
		extends JpaRepository<ProcedimientoUsuarioProcesoEntity, ProcedimientoUsuarioProcesoKey> {

	List<ProcedimientoUsuarioProcesoEntity> findProcedimientoUsuarioProcesoByIdProceso(final Integer idProceso);
}
