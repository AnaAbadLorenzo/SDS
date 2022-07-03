package com.sds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.sds.model.EvidenciaEntity;

@Component
public interface EvidenciaRepository extends JpaRepository<EvidenciaEntity, Integer> {

	EvidenciaEntity findEvidenciaByIdProcedimientoUsuarioProceso(final Integer idProcedimientoUsuarioProceso);

}
