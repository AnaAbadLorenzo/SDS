package com.sds.service.rol;

import com.sds.model.RolEntity;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.RolAsociadoAccionFuncionalidadException;
import com.sds.service.exception.RolAsociadoUsuarioException;
import com.sds.service.exception.RolNoExisteException;
import com.sds.service.exception.RolYaExisteException;
import com.sds.service.rol.model.Rol;

public interface RolService {

	ReturnBusquedas<RolEntity> buscarRol(final String rolName, final String rolDescription, final int inicio,
			final int tamanhoPagina);

	ReturnBusquedas<RolEntity> buscarTodos(final int inicio, final int tamanhoPagina);

	ReturnBusquedas<RolEntity> buscarRolesEliminados(final int inicio, final int tamanhoPagina);

	String guardarRol(final Rol rol)
			throws RolYaExisteException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException;

	String eliminarRol(final Rol rol) throws RolNoExisteException, RolAsociadoUsuarioException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, RolAsociadoAccionFuncionalidadException;

	String modificarRol(final Rol rol)
			throws RolNoExisteException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException;

	void deleteRol(final Rol rol) throws RolNoExisteException;

}
