package com.sds.service.proceso;

import java.text.ParseException;
import java.util.Date;

import com.sds.model.ProcesoEntity;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.FechaAnteriorFechaActualException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.NivelYaExisteException;
import com.sds.service.exception.ObjetivoNoExisteException;
import com.sds.service.exception.ProcedimientoAsociadoProcesoException;
import com.sds.service.exception.ProcedimientoNoExisteException;
import com.sds.service.exception.ProcesoAsociadoObjetivoException;
import com.sds.service.exception.ProcesoAsociadoRespuestaPosibleException;
import com.sds.service.exception.ProcesoAsociadoUsuarioProcedimientoException;
import com.sds.service.exception.ProcesoNoExisteException;
import com.sds.service.exception.ProcesoProcedimientoYaExisteException;
import com.sds.service.exception.ProcesoRespuestaPosibleYaExisteException;
import com.sds.service.exception.ProcesoYaExisteException;
import com.sds.service.exception.RespuestaPosibleNoExisteException;
import com.sds.service.proceso.model.Proceso;
import com.sds.service.proceso.model.ProcesoReturn;

public interface ProcesoService {

	ReturnBusquedas<ProcesoEntity> buscarProceso(final String nombreProceso, final String descripProceso,
			final Date fechaProceso, final int inicio, final int tamanhoPagina);

	ReturnBusquedas<ProcesoReturn> buscarTodos(final int inicio, final int tamanhoPagina);

	ReturnBusquedas<ProcesoEntity> buscarProcesosEliminados(final int inicio, final int tamanhoPagina);

	String anadirProceso(final Proceso proceso)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, ProcesoYaExisteException,
			ParseException, FechaAnteriorFechaActualException, ObjetivoNoExisteException, NivelYaExisteException,
			ProcesoNoExisteException, RespuestaPosibleNoExisteException, ProcesoRespuestaPosibleYaExisteException,
			ProcesoProcedimientoYaExisteException, ProcedimientoNoExisteException;

	String eliminaProceso(final Proceso proceso)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, ProcesoNoExisteException,
			ParseException, FechaAnteriorFechaActualException, ProcedimientoAsociadoProcesoException,
			ProcesoAsociadoRespuestaPosibleException, ProcesoAsociadoUsuarioProcedimientoException,
			ProcesoAsociadoObjetivoException, ObjetivoNoExisteException, NivelYaExisteException;

	String modificarProceso(final Proceso proceso) throws LogExcepcionesNoGuardadoException,
			LogAccionesNoGuardadoException, ProcesoNoExisteException, ParseException, FechaAnteriorFechaActualException,
			ProcesoAsociadoUsuarioProcedimientoException, ObjetivoNoExisteException, NivelYaExisteException;

	String reactivarProceso(final Proceso proceso) throws LogExcepcionesNoGuardadoException,
			LogAccionesNoGuardadoException, ProcesoNoExisteException, ParseException;

	void deleteProceso(final ProcesoEntity proceso) throws ProcesoNoExisteException, ParseException;
}
