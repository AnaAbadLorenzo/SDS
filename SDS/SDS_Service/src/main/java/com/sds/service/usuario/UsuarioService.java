package com.sds.service.usuario;

import java.util.List;

import com.sds.model.RolEntity;
import com.sds.model.UsuarioEntity;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.RolNoExisteException;
import com.sds.service.exception.UsuarioNoEncontradoException;
import com.sds.service.usuario.model.Usuario;

public interface UsuarioService {

	List<UsuarioEntity> buscarTodos();

	List<UsuarioEntity> buscarUsuario(String dniUsuario, String usuario, RolEntity rol);

	List<UsuarioEntity> buscarUsuariosEliminados();

	String modificarRolUsuario(final RolEntity rol, final Usuario usuario) throws LogAccionesNoGuardadoException,
			UsuarioNoEncontradoException, LogExcepcionesNoGuardadoException, RolNoExisteException;

	void deleteUsuario(UsuarioEntity user) throws UsuarioNoEncontradoException;

	String eliminarUsuario(final Usuario usuario)
			throws UsuarioNoEncontradoException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException;

	String cambiarContraseña(final Usuario usuario, String passwdUsuario)
			throws UsuarioNoEncontradoException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException;
}
