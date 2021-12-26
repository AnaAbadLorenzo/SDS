package com.sds.service.rol;

import java.util.List;

import com.sds.model.RolEntity;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.RolAsociadoUsuarioException;
import com.sds.service.exception.RolNoExisteException;
import com.sds.service.exception.RolYaExisteException;
import com.sds.service.rol.model.Rol;

public interface RolService {

	RolEntity buscarRol(String rolName) throws RolNoExisteException;

	List<RolEntity> buscarTodos();

	List<RolEntity> buscarRolesEliminados();

	String guardarRol(Rol rol)
			throws RolYaExisteException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException;

	String eliminarRol(Rol rol) throws RolNoExisteException, RolAsociadoUsuarioException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException;

	String modificarRol(Rol rol)
			throws RolNoExisteException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException;

}
