package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.PlanEntity;
import com.sds.model.ProcedimientoEntity;

@Component
public interface ProcedimientoRepository extends JpaRepository<ProcedimientoEntity, Integer> {

	Integer findIdProcedimientoByName(final String nombreProcedimiento);

	ProcedimientoEntity findProcedimientoByName(final String nombreProcedimiento);

	List<ProcedimientoEntity> findAllProcedimientos();

	Integer numberFindAllProcedimientos();

	List<ProcedimientoEntity> findProcedimiento(final String nombreProcedimiento, final String descripProcedimiento,
			final String fechaProcedimiento, final Boolean checkUsuario, final PlanEntity plan);

	List<ProcedimientoEntity> findProcedimientosByPlan(final PlanEntity plan);

	List<ProcedimientoEntity> findProcedimientoWithoutCheck(final String nombreProcedimiento,
			final String descripProcedimiento, final String fechaProcedimiento, final PlanEntity plan);

	Integer numberFindProcedimiento(final String nombreProcedimiento, final String descripProcedimiento,
			final String fechaProcedimiento, final Boolean checkUsuario);

	Integer numberFindProcedimientosByPlan(final PlanEntity plan);

	Integer numberFindProcedimientoWithoutCheck(final String nombreProcedimiento, final String descripProcedimiento,
			final String fechaProcedimiento);

	Integer numberFindProcedimientoWithoutCheckAndPlan(final String nombreProcedimiento,
			final String descripProcedimiento, final String fechaProcedimiento, final PlanEntity plan);

	Integer numberFindProcedimientoWithPlan(final String nombreProcedimiento, final String descripProcedimiento,
			final String fechaProcedimiento, final Boolean checkUsuario, final PlanEntity plan);

	List<ProcedimientoEntity> findProcedimientosEliminados();

	Integer numberFindProcedimientosEliminados();

	@Transactional
	@Modifying
	@Query("DELETE FROM ProcedimientoEntity p WHERE p.idProcedimiento = ?1")
	void deleteProcedimiento(Integer idProcedimiento);
}
