package com.sds.service.usuario;

import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.UsuarioNoEncontradoException;
import com.sds.service.usuario.model.Usuario;

public interface UsuarioService {

	String eliminarUsuario(Usuario usuario)
			throws UsuarioNoEncontradoException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException;

	String modificarUsuario(Usuario usuario)
			throws LogExcepcionesNoGuardadoException, UsuarioNoEncontradoException, LogAccionesNoGuardadoException;
}
