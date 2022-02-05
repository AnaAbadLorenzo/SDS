package com.sds.service.usuario.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.model.LogAccionesEntity;
import com.sds.model.LogExcepcionesEntity;
import com.sds.model.RolEntity;
import com.sds.model.UsuarioEntity;
import com.sds.repository.PersonaRepository;
import com.sds.repository.RolRepository;
import com.sds.repository.UsuarioRepository;
import com.sds.service.common.Constantes;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.RolNoExisteException;
import com.sds.service.exception.UsuarioNoEncontradoException;
import com.sds.service.log.LogService;
import com.sds.service.rol.RolService;
import com.sds.service.usuario.UsuarioService;
import com.sds.service.usuario.model.Usuario;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final Util util;
	private final Validaciones validaciones;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	RolRepository rolRepository;

	@Autowired
	RolService rolServiceImpl;

	@Autowired
	LogService logServiceImpl;

	public UsuarioServiceImpl() {
		util = new Util();
		validaciones = new Validaciones();
	}

	@Override
	public List<UsuarioEntity> buscarTodos() {
		final List<UsuarioEntity> usuariosToret = new ArrayList<>();

		final List<UsuarioEntity> usuarios = usuarioRepository.findAll();

		for (final UsuarioEntity usuario : usuarios) {

			final RolEntity rolUsuario = new RolEntity(usuario.getRol().getIdRol(), usuario.getRol().getRolName(),
					usuario.getRol().getRolDescription(), usuario.getRol().getBorradoRol());
			final UsuarioEntity user = new UsuarioEntity(usuario.getDniUsuario(), usuario.getUsuario(),
					usuario.getPasswdUsuario(), usuario.getBorradoUsuario(), rolUsuario);

			usuariosToret.add(user);

		}

		return usuariosToret;
	}

	@Override
	public List<UsuarioEntity> buscarUsuario(final String dniUsuario, final String usuario, final RolEntity rol) {
		final List<UsuarioEntity> toret = new ArrayList<>();
		final List<UsuarioEntity> usuarios = usuarioRepository.findUsuario(dniUsuario, usuario, rol);

		for (final UsuarioEntity usuarioBuscar : usuarios) {
			if (usuarioBuscar.getBorradoUsuario() == 0) {
				final RolEntity rolUsuario = new RolEntity(usuarioBuscar.getRol().getIdRol(),
						usuarioBuscar.getRol().getRolName(), usuarioBuscar.getRol().getRolDescription(),
						usuarioBuscar.getRol().getBorradoRol());
				final UsuarioEntity user = new UsuarioEntity(usuarioBuscar.getDniUsuario(), usuarioBuscar.getUsuario(),
						usuarioBuscar.getPasswdUsuario(), usuarioBuscar.getBorradoUsuario(), rolUsuario);

				toret.add(user);
			}
		}

		return toret;
	}

	@Override
	public List<UsuarioEntity> buscarUsuariosEliminados() {
		final List<UsuarioEntity> usuariosToret = new ArrayList<>();

		final List<UsuarioEntity> usuarios = usuarioRepository.findUsuariosEliminados(1);

		for (final UsuarioEntity usuario : usuarios) {
			final RolEntity rolUsuario = new RolEntity(usuario.getRol().getIdRol(), usuario.getRol().getRolName(),
					usuario.getRol().getRolDescription(), usuario.getRol().getBorradoRol());
			final UsuarioEntity user = new UsuarioEntity(usuario.getDniUsuario(), usuario.getUsuario(),
					usuario.getPasswdUsuario(), usuario.getBorradoUsuario(), rolUsuario);

			usuariosToret.add(user);
		}

		return usuariosToret;
	}

	@Override
	public String eliminarUsuario(final Usuario usuario)
			throws UsuarioNoEncontradoException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException {

		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		String resultadoLog2 = StringUtils.EMPTY;
		final UsuarioEntity user = usuario.getUsuarioEntity();

		LogExcepcionesEntity logExcepciones = new LogExcepcionesEntity();

		final Optional<UsuarioEntity> usuarioBD = usuarioRepository.findById(user.getDniUsuario());

		if (!usuarioBD.isPresent()) {
			logExcepciones = util.generarDatosLogExcepciones(usuario.getUsuario(),
					CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo()),
					CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new UsuarioNoEncontradoException(CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo(),
					CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo()));
		} else {
			user.setBorradoUsuario(1);
			usuario.setUsuarioEntity(user);
			usuarioRepository.saveAndFlush(user);

			final LogAccionesEntity logAccionesBuscar = util.generarDatosLogAcciones(usuario.getUsuario(),
					Constantes.ACCION_MODIFICAR_USUARIO, usuario.getUsuarioEntity().toString());

			resultadoLog = logServiceImpl.guardarLogAcciones(logAccionesBuscar);

			final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(usuario.getUsuario(),
					Constantes.ACCION_MODIFICAR_USUARIO, usuario.getUsuarioEntity().toString());

			resultadoLog2 = logServiceImpl.guardarLogAcciones(logAcciones);

			if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)
					|| CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog2)) {
				throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
			}

			resultado = Constantes.OK;
		}

		return resultado;
	}

	@Override
	public String modificarRolUsuario(final RolEntity rol, final Usuario usuario)
			throws LogExcepcionesNoGuardadoException, UsuarioNoEncontradoException, LogAccionesNoGuardadoException,
			RolNoExisteException {
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		String resultadoLog2 = StringUtils.EMPTY;
		final UsuarioEntity user = usuario.getUsuarioEntity();

		final Boolean rolValido = validaciones.comprobarRolBlank(rol);

		if (rolValido) {
			final Boolean userValido = validaciones.comprobarUsuarioBlank(user);
			if (userValido) {
				final Optional<UsuarioEntity> usuarioBD = usuarioRepository.findById(user.getDniUsuario());

				if (usuarioBD.isPresent()) {
					final Optional<RolEntity> rolUser = rolRepository.findById(rol.getIdRol());

					if (rolUser.isPresent()) {
						user.setRol(rol);
						usuario.setUsuarioEntity(user);
						usuarioRepository.saveAndFlush(user);

						final LogAccionesEntity logAccionesBuscar = util.generarDatosLogAcciones(usuario.getUsuario(),
								Constantes.ACCION_MODIFICAR_USUARIO, usuario.getUsuarioEntity().toString());

						resultadoLog = logServiceImpl.guardarLogAcciones(logAccionesBuscar);

						final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(usuario.getUsuario(),
								Constantes.ACCION_MODIFICAR_USUARIO, usuario.getUsuarioEntity().toString());

						resultadoLog2 = logServiceImpl.guardarLogAcciones(logAcciones);

						if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)
								|| CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog2)) {
							throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
									CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
						}

						resultado = Constantes.OK;
					} else {
						final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
								usuario.getUsuario(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo()),
								CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo());

						resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

						if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
							throw new LogExcepcionesNoGuardadoException(
									CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(), CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
						}

						throw new RolNoExisteException(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.ROL_NO_EXISTE_EXCEPTION.getCodigo()));
					}
				} else {
					final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(usuario.getUsuario(),
							CodeMessageErrors
									.getTipoNameByCodigo(CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo()),
							CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo());

					resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

					if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
						throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
					}

					throw new UsuarioNoEncontradoException(
							CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo()));
				}
			} else {
				resultado = CodeMessageErrors.ROL_VACIO.name();
			}
		} else {
			resultado = CodeMessageErrors.USUARIO_VACIO.name();
		}

		return resultado;
	}

	@Override
	public String cambiarContraseña(final Usuario usuario, final String passwdUsuario)
			throws UsuarioNoEncontradoException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException {
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		String resultadoLog2 = StringUtils.EMPTY;

		final UsuarioEntity user = usuario.getUsuarioEntity();
		final Boolean usuarioValido = validaciones.comprobarUsuarioBlank(user);
		final Boolean passWdUsuarioValido = validaciones.comprobarPasswdUsuarioBlank(passwdUsuario);

		if (usuarioValido) {
			if (passWdUsuarioValido) {
				final Optional<UsuarioEntity> usuarioBD = usuarioRepository.findById(user.getDniUsuario());

				if (!usuarioBD.isPresent()) {
					final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(usuario.getUsuario(),
							CodeMessageErrors
									.getTipoNameByCodigo(CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo()),
							CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo());

					resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

					if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
						throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
					}

					throw new UsuarioNoEncontradoException(
							CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo()));
				} else {
					user.setPasswdUsuario(passwdUsuario);
					usuario.setUsuarioEntity(user);
					usuarioRepository.saveAndFlush(user);

					final LogAccionesEntity logAccionesBuscar = util.generarDatosLogAcciones(usuario.getUsuario(),
							Constantes.ACCION_MODIFICAR_USUARIO, usuario.getUsuarioEntity().toString());

					resultadoLog = logServiceImpl.guardarLogAcciones(logAccionesBuscar);

					final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(usuario.getUsuario(),
							Constantes.ACCION_MODIFICAR_USUARIO, usuario.getUsuarioEntity().toString());

					resultadoLog2 = logServiceImpl.guardarLogAcciones(logAcciones);

					if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)
							|| CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog2)) {
						throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
					}

					resultado = Constantes.OK;

				}
			} else {
				resultado = CodeMessageErrors.PASSWDUSUARIO_VACIA.name();
			}
		} else {
			resultado = CodeMessageErrors.USUARIO_VACIO.name();
		}

		return resultado;
	}

	@Override
	public void deleteUsuario(final UsuarioEntity user) throws UsuarioNoEncontradoException {
		final Boolean usuarioValido = validaciones.comprobarUsuarioBlank(user);

		if (usuarioValido) {
			final Optional<UsuarioEntity> usuarioBD = usuarioRepository.findById(user.getDniUsuario());

			if (!usuarioBD.isPresent()) {
				throw new UsuarioNoEncontradoException(CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo()));
			} else {
				usuarioRepository.deleteUsuario(user.getDniUsuario());
				usuarioRepository.flush();
			}
		}
	}

}
