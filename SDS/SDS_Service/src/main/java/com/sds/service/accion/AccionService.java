package com.sds.service.accion;

import java.util.List;

import com.sds.model.AccionEntity;
import com.sds.service.accion.model.Accion;
import com.sds.service.exception.AccionAsociadaRolFuncionalidadException;
import com.sds.service.exception.AccionNoExisteException;
import com.sds.service.exception.AccionYaExisteException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;

public interface AccionService {

	AccionEntity buscarAccion(final String nombreAccion) throws AccionNoExisteException;

	List<AccionEntity> buscarTodos();

	List<AccionEntity> buscarAccionesEliminadas();

	String anadirAccion(final Accion accion)
			throws AccionYaExisteException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException;

	String eliminarAccion(final Accion accion) throws LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			AccionNoExisteException, AccionAsociadaRolFuncionalidadException;

	String modificarAccion(final Accion accion)
			throws LogExcepcionesNoGuardadoException, AccionNoExisteException, LogAccionesNoGuardadoException;

}
