package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.ProcesoEntity;

@Component
public interface ProcesoRepository extends JpaRepository<ProcesoEntity, Integer> {

	Integer findIdProcesoByName(final String nombreProceso);

	ProcesoEntity findProcesoByName(final String nombreProceso);

	List<ProcesoEntity> findAllProcesos();

	Integer numberFindAllProcesos();

	List<ProcesoEntity> findProceso(final String nombreProceso, final String descripProceso, final String fechaProceso);

	Integer numberFindProceso(final String nombreProceso, final String descripProceso, final String fechaProceso);

	List<ProcesoEntity> findProcesosEliminados();

	Integer numberFindProcesosEliminados();

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Modifying
	@Query("DELETE FROM ProcesoEntity p WHERE p.idProceso = ?1")
	void deleteProceso(Integer idProceso);
}
