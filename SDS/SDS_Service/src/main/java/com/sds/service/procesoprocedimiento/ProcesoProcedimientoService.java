package com.sds.service.procesoprocedimiento;

import java.text.ParseException;

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

	ReturnBusquedas<ProcesoProcedimientoEntity> buscarProcesoProcedimiento(final Integer idProceso,
			final Integer idProcedimiento);

	ReturnBusquedas<ProcesoProcedimientoEntity> buscarProcesosByProcedimiento(final Integer idProcedimiento);

	public String anadirProcesoProcedimiento(final ProcesoProcedimiento procesoProcedimiento)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			ProcesoProcedimientoYaExisteException, ProcedimientoNoExisteException, ProcesoNoExisteException,
			ParseException;

	String modificarProcesoProcedimiento(final ProcesoProcedimiento procesoProcedimiento)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			ProcesoProcedimientoNoExisteException, ProcedimientoNoExisteException, ProcesoNoExisteException,
			ParseException;

	String eliminaProcesoProcedimiento(final ProcesoProcedimiento procesoProcedimiento)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			ProcesoProcedimientoNoExisteException;

	void deleteProcesoProcedimiento(final ProcesoProcedimientoEntity procesoProcedimientoEntity)
			throws ProcesoProcedimientoNoExisteException, ParseException;
}
