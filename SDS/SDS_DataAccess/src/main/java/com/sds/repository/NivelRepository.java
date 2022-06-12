package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.NivelEntity;
import com.sds.model.compositekey.NivelKey;

public interface NivelRepository extends JpaRepository<NivelEntity, NivelKey> {

	List<NivelEntity> findNivelByIdProceso(final Integer idProceso);

	List<NivelEntity> findNivelByIdObjetivo(final Integer idObjetivo);

	@Transactional
	@Modifying
	@Query("DELETE FROM NivelEntity n WHERE n.idObjetivo = ?1 AND n.idProceso = ?2")
	void deleteNivel(final Integer idObjetivo, final Integer idNivel);
}
