package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.ProcesoRespuestaPosibleEntity;
import com.sds.model.compositekey.ProcesoRespuestaPosibleKey;

@Component
public interface ProcesoRespuestaPosibleRepository
		extends JpaRepository<ProcesoRespuestaPosibleEntity, ProcesoRespuestaPosibleKey> {

	List<ProcesoRespuestaPosibleEntity> findProcesoByIdRespuestaPosible(final Integer idRespuesta);

	@Transactional
	@Modifying
	@Query("DELETE FROM ProcesoRespuestaPosibleEntity p WHERE p.idRespuesta = ?1 AND p.idProceso =?2")
	void deleteProcesoRespuestaPosible(Integer idRespuesta, Integer idProceso);
}
