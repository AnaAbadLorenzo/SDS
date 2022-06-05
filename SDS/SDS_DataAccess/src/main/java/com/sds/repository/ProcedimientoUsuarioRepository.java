package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.ProcedimientoEntity;
import com.sds.model.ProcedimientoUsuarioEntity;
import com.sds.model.UsuarioEntity;

public interface ProcedimientoUsuarioRepository extends JpaRepository<ProcedimientoUsuarioEntity, Integer> {

	List<ProcedimientoUsuarioEntity> findProcedimientoUsuarioByProcedimiento(final ProcedimientoEntity procedimiento);

	List<ProcedimientoUsuarioEntity> findProcedimientoUsuario(final Integer puntuacionProcedimientoUsuario,
			final String fechaProcedimientoUsuario, final UsuarioEntity usuario,
			final ProcedimientoEntity procedimiento);

	@Transactional
	@Modifying
	@Query("DELETE FROM ProcedimientoUsuarioEntity p WHERE p.idProcedimientoUsuario = ?1")
	void deleteProcedimientoUsuario(Integer idProcedimientoUsuario);

}
