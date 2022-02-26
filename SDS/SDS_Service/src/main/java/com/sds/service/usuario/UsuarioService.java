package com.sds.service.usuario;

import java.text.ParseException;

import com.sds.model.RolEntity;
import com.sds.model.UsuarioEntity;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.PersonaNoExisteException;
import com.sds.service.exception.RolNoExisteException;
import com.sds.service.exception.UsuarioNoEncontradoException;
import com.sds.service.usuario.model.Usuario;

public interface UsuarioService {

	ReturnBusquedas<UsuarioEntity> buscarTodos(final int inicio, final int tamanhoPagina);

	ReturnBusquedas<UsuarioEntity> buscarUsuario(final String dniUsuario, final String usuario, final RolEntity rol,
			final int inicio, final int tamanhoPagina);

	ReturnBusquedas<UsuarioEntity> buscarUsuariosEliminados(final int inicio, final int tamanhoPagina);

	String modificarRolUsuario(final RolEntity rol, final Usuario usuario) throws LogAccionesNoGuardadoException,
			UsuarioNoEncontradoException, LogExcepcionesNoGuardadoException, RolNoExisteException;

	void deleteUsuario(final UsuarioEntity user) throws UsuarioNoEncontradoException;

	String eliminarUsuario(final Usuario usuario)
			throws UsuarioNoEncontradoException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException;

	String cambiarContraseña(final Usuario usuario, String passwdUsuario)
			throws UsuarioNoEncontradoException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException;

	String reactivarUsuario(final Usuario usuario) throws UsuarioNoEncontradoException,
			LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException, PersonaNoExisteException, ParseException;
}
