package com.sds.service.objetivo;

import com.sds.model.ObjetivoEntity;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.ObjetivoAsociadoPlanException;
import com.sds.service.exception.ObjetivoAsociadoProcesoException;
import com.sds.service.exception.ObjetivoNoExisteException;
import com.sds.service.exception.ObjetivoYaExisteException;
import com.sds.service.objetivo.model.Objetivo;

public interface ObjetivoService {

	ReturnBusquedas<ObjetivoEntity> buscarObjetivo(final String nombreObjetivo, final String descripObjetivo,
			final int inicio, final int tamanhoPagina);

	ReturnBusquedas<ObjetivoEntity> buscarTodos(final int inicio, final int tamanhoPagina);

	ReturnBusquedas<ObjetivoEntity> buscarTodosSinP();

	ReturnBusquedas<ObjetivoEntity> buscarObjetivosEliminados(final int inicio, final int tamanhoPagina);

	String anadirObjetivo(final Objetivo objetivo)
			throws ObjetivoYaExisteException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException;

	String eliminaObjetivo(final Objetivo objetivo)
			throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, ObjetivoNoExisteException,
			ObjetivoAsociadoPlanException, ObjetivoAsociadoProcesoException;

	String modificarObjetivo(final Objetivo objetivo)
			throws LogExcepcionesNoGuardadoException, ObjetivoNoExisteException, LogAccionesNoGuardadoException;

	String reactivarObjetivo(final Objetivo objetivo)
			throws LogExcepcionesNoGuardadoException, ObjetivoNoExisteException, LogAccionesNoGuardadoException;

	void deleteObjetivo(final ObjetivoEntity objetivo) throws ObjetivoNoExisteException;
}
