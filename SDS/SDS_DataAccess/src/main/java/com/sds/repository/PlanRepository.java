package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.PlanEntity;

@Component
public interface PlanRepository extends JpaRepository<PlanEntity, Integer> {

	Integer findIdPlanByName(String nombrePlan);

	PlanEntity findPlanByName(String nombrePlan);

	List<PlanEntity> findAllPlanes();

	Integer numberFindAllPlanes();

	List<PlanEntity> findPlan(String nombrePlan, String descripPlan, String fechaPlan);

	Integer numberFindPlan(String nombrePlan, String descripPlan, String fechaPlan);

	List<PlanEntity> findPlanesEliminados();

	Integer numberFindPlanesEliminados();

	@Transactional
	@Modifying
	@Query("DELETE FROM PlanEntity p WHERE p.idPlan = ?1")
	void deletePlan(Integer idPlan);
}
