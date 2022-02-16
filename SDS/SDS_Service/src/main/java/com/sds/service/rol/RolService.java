package com.sds.service.rol;

import java.util.List;

import com.sds.model.RolEntity;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.RolAsociadoAccionFuncionalidadException;
import com.sds.service.exception.RolAsociadoUsuarioException;
import com.sds.service.exception.RolNoExisteException;
import com.sds.service.exception.RolYaExisteException;
import com.sds.service.rol.model.Rol;

public interface RolService {

	List<RolEntity> buscarRol(String rolName, String rolDescription);

	List<RolEntity> buscarRolPagination(String rolName, String rolDescription, int inicio, int tamanoPagina);

	List<RolEntity> buscarTodos();

	List<RolEntity> buscarRolesEliminados();

	String guardarRol(Rol rol)
			throws RolYaExisteException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException;

	String eliminarRol(Rol rol) throws RolNoExisteException, RolAsociadoUsuarioException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, RolAsociadoAccionFuncionalidadException;

	String modificarRol(Rol rol)
			throws RolNoExisteException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException;

	void deleteRol(final Rol rol) throws RolNoExisteException;

}
