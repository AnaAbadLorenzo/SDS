package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.ProcesoProcedimientoEntity;
import com.sds.model.compositekey.ProcesoProcedimientoKey;

@Component
public interface ProcesoProcedimientoRepository
		extends JpaRepository<ProcesoProcedimientoEntity, ProcesoProcedimientoKey> {

	List<ProcesoProcedimientoEntity> findProcesoProcedimiento(final Integer idProceso, final Integer idProcedimiento);

	List<ProcesoProcedimientoEntity> findAllProcesosOrderByOrden(final Integer idProcedimiento);

	Integer numberFindAllProcesosOrderByOrden(final Integer idProcedimiento);

	List<ProcesoProcedimientoEntity> findProcesoProcedimientoByIdProceso(final Integer idProceso);

	Integer numberFindProcesoProcedimiento(final Integer idProceso, final Integer idProcedimiento);

	Integer numberFindProcesoProcedimientoByIdProcedimiento(final Integer idProcedimiento);

	List<Integer> findIdProcesoByIdProcedimiento(final Integer idProcedimiento);

	List<Integer> findIdProcedimientoByIdProceso(final Integer idProceso);

	List<Integer> findProcedimientoByIdProcedimiento(final Integer idProcedimiento);

	@Transactional
	@Modifying
	@Query("DELETE FROM ProcesoProcedimientoEntity p WHERE p.idProceso = ?1 AND p.idProcedimiento =?2")
	void deleteProcesoProcedimiento(Integer idProceso, Integer idProcedimiento);
}
