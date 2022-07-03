package com.sds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.ProcedimientoEntity;
import com.sds.model.ProcedimientoUsuarioEntity;
import com.sds.model.UsuarioEntity;

@Component
public interface ProcedimientoUsuarioRepository extends JpaRepository<ProcedimientoUsuarioEntity, Integer> {

	List<ProcedimientoUsuarioEntity> findAllProcedimientosUsuario(final int inicio, final int tamanhoPagina);

	List<ProcedimientoUsuarioEntity> findProcedimientoUsuarioByProcedimientoAndUsuario(final UsuarioEntity usuario,
			final ProcedimientoEntity procedimiento);

	Integer numberFindAllProcedimientosUsuario();

	List<ProcedimientoUsuarioEntity> findProcedimientoUsuarioByProcedimiento(final ProcedimientoEntity procedimiento);

	List<ProcedimientoUsuarioEntity> findProcedimientoUsuarioByUsuario(final UsuarioEntity usuario);

	List<ProcedimientoUsuarioEntity> findProcedimientoUsuario(final Integer puntuacionProcedimientoUsuario,
			final String fechaProcedimientoUsuario, final UsuarioEntity usuario,
			final ProcedimientoEntity procedimiento);

	Integer numberFindProcedimientoUsuario(final Integer puntuacionProcedimientoUsuario,
			final String fechaProcedimientoUsuario, final UsuarioEntity usuario,
			final ProcedimientoEntity procedimiento);

	Integer numberFindProcedimientoUsuarioByUsuario(final UsuarioEntity usuario);

	Integer numberFindProcedimientoUsuarioWithoutUsuario(final Integer puntuacionProcedimientoUsuario,
			final String fechaProcedimientoUsuario, final ProcedimientoEntity procedimiento);

	Integer numberFindProcedimientoUsuarioWithoutProcedimiento(final Integer puntuacionProcedimientoUsuario,
			final String fechaProcedimientoUsuario, final UsuarioEntity usuario);

	Integer numberFindProcedimientoUsuarioWithoutProcedimientoAndUsuario(final Integer puntuacionProcedimientoUsuario,
			final String fechaProcedimientoUsuario);

	@Transactional
	@Modifying
	@Query("DELETE FROM ProcedimientoUsuarioEntity p WHERE p.idProcedimientoUsuario = ?1")
	void deleteProcedimientoUsuario(Integer idProcedimientoUsuario);

}
