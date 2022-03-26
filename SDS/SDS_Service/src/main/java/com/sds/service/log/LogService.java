package com.sds.service.log;

import java.util.Date;
import java.util.List;

import com.sds.model.LogAccionesEntity;
import com.sds.model.LogExcepcionesEntity;
import com.sds.service.common.ReturnBusquedas;

public interface LogService {

	List<LogExcepcionesEntity> buscarTodosLogExcepciones();

	List<LogExcepcionesEntity> buscarPorUsuarioLogExcepciones(String usuario);

	ReturnBusquedas<LogExcepcionesEntity> buscarPorUsuarioYFechaLogExcepciones(String usuario, Date fechaInicio,
			Date fechaFin, final int inicio, final int tamanhoPagina);

	String guardarLogExcepciones(LogExcepcionesEntity logExcepciones);

	List<LogAccionesEntity> buscarTodosLogAcciones();

	List<LogAccionesEntity> buscarPorUsuarioLogAcciones(String usuario);

	List<LogAccionesEntity> buscarPorAccionLogAcciones(String accion);

	List<LogAccionesEntity> buscarPorUsuarioAccionLogAcciones(String usuario, String accion);

	String guardarLogAcciones(LogAccionesEntity logAcciones);

}