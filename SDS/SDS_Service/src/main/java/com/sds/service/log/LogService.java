package com.sds.service.log;

import java.util.List;

import com.sds.model.LogAccionesEntity;
import com.sds.model.LogExcepcionesEntity;

public interface LogService {

	List<LogExcepcionesEntity> buscarTodosLogExcepciones();

	List<LogExcepcionesEntity> buscarPorUsuarioLogExcepciones(String usuario);

	String guardarLogExcepciones(LogExcepcionesEntity logExcepciones);

	List<LogAccionesEntity> buscarTodosLogAcciones();

	List<LogAccionesEntity> buscarPorUsuarioLogAcciones(String usuario);

	List<LogAccionesEntity> buscarPorAccionLogAcciones(String accion);

	List<LogAccionesEntity> buscarPorUsuarioAccionLogAcciones(String usuario, String accion);

	String guardarLogAcciones(LogAccionesEntity logAcciones);

}
