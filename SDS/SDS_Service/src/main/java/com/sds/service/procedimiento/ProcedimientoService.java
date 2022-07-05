package com.sds.service.procedimiento;

import java.text.ParseException;
import java.util.Date;

import com.sds.model.PlanEntity;
import com.sds.model.ProcedimientoEntity;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.FechaAnteriorFechaActualException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.PlanNoExisteException;
import com.sds.service.exception.ProcedimientoNoExisteException;
import com.sds.service.exception.ProcedimientoYaExisteException;
import com.sds.service.exception.ProcesoAsociadoProcedimientoException;
import com.sds.service.exception.UsuarioAsociadoProcedimientoException;
import com.sds.service.procedimiento.model.Procedimiento;

public interface ProcedimientoService {

	ReturnBusquedas<ProcedimientoEntity> buscarProcedimiento(final String nombreProcedimiento,
			final String descripProcedimiento, final Date fechaProcedimiento, final Boolean checkUsuario,
			final PlanEntity plan, final int inicio, final int tamanhoPagina);

	ReturnBusquedas<ProcedimientoEntity> buscarProcedimientoByPlan(final PlanEntity plan, final int inicio,
			final int tamanhoPagina);

	ReturnBusquedas<ProcedimientoEntity> buscarTodos(final int inicio, final int tamanhoPagina);

	ReturnBusquedas<ProcedimientoEntity> buscarTodos();

	ReturnBusquedas<ProcedimientoEntity> buscarProcedimientosEliminados(final int inicio, final int tamanhoPagina);

	String anadirProcedimiento(final Procedimiento procedimiento)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, ProcedimientoYaExisteException,
			FechaAnteriorFechaActualException, PlanNoExisteException, ParseException;

	String eliminaProcedimiento(final Procedimiento procedimiento)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, ProcedimientoNoExisteException,
			ProcesoAsociadoProcedimientoException, UsuarioAsociadoProcedimientoException;

	String modificarProcedimiento(final Procedimiento procedimiento)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, ProcedimientoNoExisteException,
			FechaAnteriorFechaActualException, PlanNoExisteException, ParseException;

	String reactivarProcedimiento(final Procedimiento procedimiento)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, ProcedimientoNoExisteException;

	void deleteProcedimiento(final ProcedimientoEntity procedimientoEntity)
			throws ProcedimientoNoExisteException, ParseException;

}
