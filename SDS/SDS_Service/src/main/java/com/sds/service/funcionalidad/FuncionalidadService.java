package com.sds.service.funcionalidad;

import java.util.List;

import com.sds.model.FuncionalidadEntity;
import com.sds.service.exception.FuncionalidadAsociadaRolAccionException;
import com.sds.service.exception.FuncionalidadNoExisteException;
import com.sds.service.exception.FuncionalidadYaExisteException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.funcionalidad.model.Funcionalidad;

public interface FuncionalidadService {

	List<FuncionalidadEntity> buscarFuncionalidad(final String nombreFuncionalidad, final String descripFuncionalidad);

	List<FuncionalidadEntity> buscarTodos();

	List<FuncionalidadEntity> buscarFuncionalidadesEliminadas();

	String anadirFuncionalidad(final Funcionalidad funcionalidad)
			throws FuncionalidadYaExisteException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException;

	String eliminarFuncionalidad(final Funcionalidad funcionalidad) throws LogExcepcionesNoGuardadoException,
			LogAccionesNoGuardadoException, FuncionalidadNoExisteException, FuncionalidadAsociadaRolAccionException;

	String modificarFuncionalidad(final Funcionalidad funcionalidad)
			throws LogExcepcionesNoGuardadoException, FuncionalidadNoExisteException, LogAccionesNoGuardadoException;

	void deleteFuncionalidad(final FuncionalidadEntity funcionalidad) throws FuncionalidadNoExisteException;

}
