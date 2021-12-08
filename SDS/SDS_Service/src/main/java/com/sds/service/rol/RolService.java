package com.sds.service.rol;

import java.util.List;

import com.sds.model.RolEntity;
import com.sds.service.exception.NoHayRolesException;
import com.sds.service.exception.RolNoExisteException;
import com.sds.service.exception.RolYaExisteException;

public interface RolService {

	RolEntity buscarRol(String rolName) throws RolNoExisteException;

	List<RolEntity> buscarTodos() throws NoHayRolesException;
	
	List<RolEntity> buscarRolesEliminados() throws NoHayRolesException;

	String guardarRol(RolEntity rol) throws RolYaExisteException;

	String eliminarRol(RolEntity rol) throws RolNoExisteException;

	String modificarRol(RolEntity rol) throws RolNoExisteException;
	
	

}
