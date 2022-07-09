package com.sds.service.procedimientousuarioproceso;

import java.util.Date;

import com.sds.model.ProcedimientoEntity;
import com.sds.model.ProcedimientoUsuarioProcesoEntity;
import com.sds.model.ProcesoEntity;
import com.sds.model.UsuarioEntity;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.ProcedimientoUsuarioNoExisteException;
import com.sds.service.exception.ProcedimientoUsuarioProcesoNoExisteException;
import com.sds.service.exception.ProcedimientoUsuarioProcesoYaExisteException;
import com.sds.service.exception.ProcesoNoExisteException;
import com.sds.service.exception.RespuestaPosibleNoExisteException;
import com.sds.service.procedimientousuarioproceso.model.ProcedimientoUsuarioProceso;
import com.sds.service.procedimientousuarioproceso.model.ProcedimientoUsuarioProcesoReturn;

public interface ProcedimientoUsuarioProcesoService {

	ReturnBusquedas<ProcedimientoUsuarioProcesoEntity> buscarTodosPaginacion(final int inicio, final int tamanhoPagina);

	ReturnBusquedas<ProcedimientoUsuarioProcesoEntity> buscarProcedimientoUsuarioProceso(
			final ProcedimientoEntity procedimiento, final UsuarioEntity usuario, final ProcesoEntity proceso,
			final Date fechaProcedimientoUsuarioProceso, final int inicio, final int tamanhoPagina);

	ReturnBusquedas<ProcedimientoUsuarioProcesoEntity> buscarTodos();

	ProcedimientoUsuarioProcesoReturn buscarProcesosOfProcedimientoUsuario(final Integer idProcedimientoUsuario);

	String anadirProcedimientoUsuarioProceso(final ProcedimientoUsuarioProceso procedimientoUsuarioProceso)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			ProcedimientoUsuarioProcesoYaExisteException, ProcedimientoUsuarioNoExisteException,
			ProcesoNoExisteException, RespuestaPosibleNoExisteException;

	String modificarProcedimientoUsuarioProceso(final ProcedimientoUsuarioProceso procedimientoUsuarioProceso)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			ProcedimientoUsuarioProcesoNoExisteException, ProcedimientoUsuarioNoExisteException,
			ProcesoNoExisteException, RespuestaPosibleNoExisteException;

	String eliminaProcedimientoUsuarioProceso(final ProcedimientoUsuarioProceso procedimientoUsuarioProceso)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			ProcedimientoUsuarioProcesoNoExisteException;

	void deleteProcedimientoUsuarioProceso(final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProcesoEntity)
			throws ProcedimientoUsuarioProcesoNoExisteException;
}
