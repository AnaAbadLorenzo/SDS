package com.sds.service.procedimientousuario;

import java.text.ParseException;
import java.util.Date;

import com.sds.model.ProcedimientoEntity;
import com.sds.model.ProcedimientoUsuarioEntity;
import com.sds.model.UsuarioEntity;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.ProcedimientoNoExisteException;
import com.sds.service.exception.ProcedimientoUsuarioNoExisteException;
import com.sds.service.exception.ProcedimientoUsuarioProcesoAsociadoProcedimientoUsuarioException;
import com.sds.service.exception.ProcedimientoUsuarioYaExisteException;
import com.sds.service.exception.UsuarioNoEncontradoException;
import com.sds.service.procedimientousuario.model.ProcedimientoUsuario;

public interface ProcedimientoUsuarioService {

	ReturnBusquedas<ProcedimientoUsuarioEntity> buscarProcedimientoUsuario(final Integer puntuacionProcedimientoUsuario,
			final Date fechaProcedimiento, final UsuarioEntity usuario, final ProcedimientoEntity procedimiento,
			final int inicio, final int tamanhoPagina);

	ReturnBusquedas<ProcedimientoUsuarioEntity> buscarTodos(final int inicio, final int tamanhoPagina);

	String anadirProcedimientoUsuario(final ProcedimientoUsuario procedimientoUsuario)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, UsuarioNoEncontradoException,
			ParseException, ProcedimientoUsuarioYaExisteException, ProcedimientoNoExisteException;

	String modificarProcedimientoUsuario(final ProcedimientoUsuario procedimientoUsuario)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, UsuarioNoEncontradoException,
			ParseException, ProcedimientoUsuarioNoExisteException, ProcedimientoNoExisteException;

	String eliminaProcedimientoUsuario(final ProcedimientoUsuario procedimientoUsuario)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			ProcedimientoUsuarioProcesoAsociadoProcedimientoUsuarioException, ProcedimientoUsuarioNoExisteException;

	void deleteProcedimientoUsuario(final ProcedimientoUsuarioEntity procedimientoUsuarioEntity)
			throws ParseException, ProcedimientoUsuarioNoExisteException;
}
