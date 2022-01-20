package com.sds.service.usuario;

import java.util.List;

import com.sds.model.UsuarioEntity;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.UsuarioNoEncontradoException;

public interface UsuarioService {

	List<UsuarioEntity> buscarTodos();

	void deleteUsuario(UsuarioEntity user) throws UsuarioNoEncontradoException;

	String eliminarUsuario(final UsuarioEntity user)
			throws UsuarioNoEncontradoException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException;

	String modificarUsuario(final UsuarioEntity user)
			throws LogExcepcionesNoGuardadoException, UsuarioNoEncontradoException, LogAccionesNoGuardadoException;
}
