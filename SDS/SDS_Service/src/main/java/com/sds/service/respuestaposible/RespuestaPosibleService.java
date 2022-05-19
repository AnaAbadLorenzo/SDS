package com.sds.service.respuestaposible;

import com.sds.model.RespuestaPosibleEntity;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.RespuestaPosibleAsociadaProcesoException;
import com.sds.service.exception.RespuestaPosibleNoExisteException;
import com.sds.service.exception.RespuestaPosibleYaExisteException;
import com.sds.service.respuestaposible.model.RespuestaPosible;

public interface RespuestaPosibleService {

	ReturnBusquedas<RespuestaPosibleEntity> buscarRespuestaPosible(final String textoRespuesta, final int inicio,
			final int tamanhoPagina);

	ReturnBusquedas<RespuestaPosibleEntity> buscarTodas(final int inicio, final int tamanhoPagina);

	ReturnBusquedas<RespuestaPosibleEntity> buscarRespuestasPosiblesEliminadas(final int inicio,
			final int tamanhoPagina);

	String anadirRespuestaPosible(final RespuestaPosible respuestaPosible)
			throws RespuestaPosibleYaExisteException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException;

	String eliminarRespuestaPosible(final RespuestaPosible respuestaPosible) throws LogExcepcionesNoGuardadoException,
			LogAccionesNoGuardadoException, RespuestaPosibleNoExisteException, RespuestaPosibleAsociadaProcesoException;

	String modificarRespuestaPosible(final RespuestaPosible respuestaPosible)
			throws LogExcepcionesNoGuardadoException, RespuestaPosibleNoExisteException, LogAccionesNoGuardadoException;

	String reactivarRespuestaPosible(final RespuestaPosible respuestaPosible)
			throws LogExcepcionesNoGuardadoException, RespuestaPosibleNoExisteException, LogAccionesNoGuardadoException;

	void deleteRespuestaPosible(final RespuestaPosibleEntity respuestaPosible) throws RespuestaPosibleNoExisteException;
}
