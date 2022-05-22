package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.ObjetivoEntity;

@Component
public interface ObjetivoRepository extends JpaRepository<ObjetivoEntity, Integer> {

	Integer findIdObjetivoByName(String nombreObjetivo);

	ObjetivoEntity findObjetivoByName(String nombreObjetivo);

	List<ObjetivoEntity> findAllObjetivos();

	Integer numberFindAllObjetivos();

	List<ObjetivoEntity> findObjetivo(String nombreObjetivo, String descripObjetivo);

	Integer numberFindObjetivo(String nombreObjetivo, String descripObjetivo);

	List<ObjetivoEntity> findObjetivosEliminados();

	Integer numberFindObjetivosEliminados();

	@Transactional
	@Modifying
	@Query("DELETE FROM ObjetivoEntity o WHERE o.idObjetivo = ?1")
	void deleteObjetivo(Integer idObjetivo);
}
