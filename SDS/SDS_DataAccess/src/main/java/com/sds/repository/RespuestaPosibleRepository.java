package com.sds.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.RespuestaPosibleEntity;

@Component
public interface RespuestaPosibleRepository extends JpaRepository<RespuestaPosibleEntity, Integer> {

	RespuestaPosibleEntity findRespuestaPosibleByText(final String textoRespuesta);

	List<RespuestaPosibleEntity> findAllRespuestasPosibles();

	Integer numberFindAllRespuestasPosibles();

	List<RespuestaPosibleEntity> findRespuestaPosible(String textoRespuesta, Date fechaRespuesta);

	Integer numberFindRespuestaPosible(String textoRespuesta, Date fechaRespuesta);

	List<RespuestaPosibleEntity> findRespuestasPosiblesEliminadas();

	Integer numberFindRespuestasPosiblesEliminadas();

	@Transactional
	@Modifying
	@Query("DELETE FROM RespuestaPosibleEntity r WHERE r.idRespuesta = ?1")
	void deleteRespuestaPosible(Integer idRespuesta);
}
