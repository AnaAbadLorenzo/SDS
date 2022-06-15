package com.sds.service.procesoprocedimiento;

import java.util.List;

import com.sds.model.ProcedimientoEntity;
import com.sds.model.ProcesoEntity;
import com.sds.model.ProcesoProcedimientoEntity;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.ProcedimientoNoExisteException;
import com.sds.service.exception.ProcesoNoExisteException;
import com.sds.service.exception.ProcesoProcedimientoNoExisteException;
import com.sds.service.exception.ProcesoProcedimientoYaExisteException;
import com.sds.service.procesoprocedimiento.model.ProcesoProcedimiento;

public interface ProcesoProcedimientoService {

	ReturnBusquedas<ProcesoProcedimientoEntity> buscarProcedimientoUsuario(final ProcesoEntity proceso,
			final ProcedimientoEntity procedimiento, final Integer ordenProceso, final int inicio,
			final int tamanhoPagina);

	ReturnBusquedas<ProcesoProcedimientoEntity> buscarTodos(final int inicio, final int tamanhoPagina);

	List<ProcesoProcedimientoEntity> buscarTodosSinP();

	String anadirProcesoProcedimiento(final ProcesoProcedimiento procesoProcedimiento)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			ProcesoProcedimientoYaExisteException, ProcedimientoNoExisteException, ProcesoNoExisteException;

	String modificarProcesoProcedimiento(final ProcesoProcedimiento procesoProcedimiento)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			ProcesoProcedimientoNoExisteException, ProcedimientoNoExisteException, ProcesoNoExisteException;

	String eliminaProcesoProcedimiento(final ProcesoProcedimiento procesoProcedimiento)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException;

	void deleteProcesoProcedimiento(final ProcesoProcedimientoEntity procesoProcedimientoEntity)
			throws ProcesoProcedimientoNoExisteException;
}
