package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.ObjetivoEntity;
import com.sds.model.PlanEntity;

@Component
public interface PlanRepository extends JpaRepository<PlanEntity, Integer> {

	Integer findIdPlanByName(final String nombrePlan);

	PlanEntity findPlanByName(final String nombrePlan);

	List<PlanEntity> findAllPlanes();

	Integer numberFindAllPlanes();

	List<PlanEntity> findPlan(final String nombrePlan, final String descripPlan, final String fechaPlan,
			final ObjetivoEntity objetivo);

	Integer numberFindPlan(final String nombrePlan, final String descripPlan, final String fechaPlan);

	Integer numberFindPlanWithObjetivo(final String nombrePlan, final String descripPlan, final String fechaPlan,
			final ObjetivoEntity objetivo);

	List<PlanEntity> findPlanesEliminados();

	Integer numberFindPlanesEliminados();

	@Transactional
	@Modifying
	@Query("DELETE FROM PlanEntity p WHERE p.idPlan = ?1")
	void deletePlan(Integer idPlan);
}
