package com.sds.service.usuario.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.model.LogAccionesEntity;
import com.sds.model.LogExcepcionesEntity;
import com.sds.model.PersonaEntity;
import com.sds.model.RolEntity;
import com.sds.model.UsuarioEntity;
import com.sds.repository.PersonaRepository;
import com.sds.repository.RolRepository;
import com.sds.repository.UsuarioRepository;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.PersonaNoExisteException;
import com.sds.service.exception.RolNoExisteException;
import com.sds.service.exception.UsuarioNoEncontradoException;
import com.sds.service.log.LogService;
import com.sds.service.persona.PersonaService;
import com.sds.service.persona.model.Persona;
import com.sds.service.rol.RolService;
import com.sds.service.usuario.UsuarioService;
import com.sds.service.usuario.model.Usuario;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	RolRepository rolRepository;

	@Autowired
	RolService rolServiceImpl;

	@Autowired
	PersonaService personaServiceImpl;

	@Autowired
	LogService logServiceImpl;

	private final Util util;
	private final Validaciones validaciones;

	public UsuarioServiceImpl() {
		util = new Util();
		validaciones = new Validaciones();
	}

	@Override
	public ReturnBusquedas<UsuarioEntity> buscarTodos(final int inicio, final int tamanhoPagina) {
		final List<UsuarioEntity> usuariosToret = new ArrayList<>();

		final List<UsuarioEntity> usuarios = entityManager.createNamedQuery("UsuarioEntity.findAllUsuarios")
				.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = usuarioRepository.numberFindAllUsuarios();

		if (!usuarios.isEmpty()) {
			for (final UsuarioEntity usuario : usuarios) {
				final RolEntity rolUsuario = new RolEntity(usuario.getRol().getIdRol(), usuario.getRol().getRolName(),
						usuario.getRol().getRolDescription(), usuario.getRol().getBorradoRol());
				final Optional<PersonaEntity> persona = personaRepository.findById(usuario.getDniUsuario());
				final PersonaEntity personaEntity = new PersonaEntity(persona.get().getDniP(),
						persona.get().getNombreP(), persona.get().getApellidosP(), persona.get().getFechaNacP(),
						persona.get().getDireccionP(), persona.get().getTelefonoP(), persona.get().getEmailP(),
						persona.get().getBorradoP());

				final UsuarioEntity user = new UsuarioEntity(usuario.getDniUsuario(), usuario.getUsuario(),
						usuario.getPasswdUsuario(), usuario.getBorradoUsuario(), rolUsuario, personaEntity);

				usuariosToret.add(user);
			}
		}

		final ReturnBusquedas<UsuarioEntity> result = new ReturnBusquedas<UsuarioEntity>(usuariosToret,
				numberTotalResults, usuariosToret.size());

		return result;
	}

	@Override
	public ReturnBusquedas<UsuarioEntity> buscarUsuario(final String usuario, final RolEntity rol, final int inicio,
			final int tamanhoPagina) {
		final List<UsuarioEntity> toret = new ArrayList<>();
		final List<String> datosBusqueda = new ArrayList<>();
		List<UsuarioEntity> usuarios = new ArrayList<>();
		Integer numberTotalResults = 0;

		if (rol.getIdRol() == -1) {
			usuarios = entityManager.createNamedQuery("UsuarioEntity.findUsuario").setParameter("usuario", usuario)
					.setParameter("rol", "").setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();

			numberTotalResults = usuarioRepository.numberFindUsuario(usuario);

		} else {
			usuarios = entityManager.createNamedQuery("UsuarioEntity.findUsuario").setParameter("usuario", usuario)
					.setParameter("rol", rol).setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();

			numberTotalResults = usuarioRepository.numberFindUsuarioWithRol(usuario, rol);
		}

		if (!usuarios.isEmpty()) {
			for (final UsuarioEntity usuarioBuscar : usuarios) {
				final RolEntity rolUsuario = new RolEntity(usuarioBuscar.getRol().getIdRol(),
						usuarioBuscar.getRol().getRolName(), usuarioBuscar.getRol().getRolDescription(),
						usuarioBuscar.getRol().getBorradoRol());
				final Optional<PersonaEntity> persona = personaRepository.findById(usuarioBuscar.getDniUsuario());
				final PersonaEntity personaEntity = new PersonaEntity(persona.get().getDniP(),
						persona.get().getNombreP(), persona.get().getApellidosP(), persona.get().getFechaNacP(),
						persona.get().getDireccionP(), persona.get().getTelefonoP(), persona.get().getEmailP(),
						persona.get().getBorradoP());

				final UsuarioEntity user = new UsuarioEntity(usuarioBuscar.getDniUsuario(), usuarioBuscar.getUsuario(),
						usuarioBuscar.getPasswdUsuario(), usuarioBuscar.getBorradoUsuario(), rolUsuario, personaEntity);

				toret.add(user);
			}
		}

		datosBusqueda.add("usuario: " + usuario);
		datosBusqueda.add("rol: " + rol.toString());

		final ReturnBusquedas<UsuarioEntity> result = new ReturnBusquedas<UsuarioEntity>(toret, datosBusqueda,
				numberTotalResults, toret.size());

		return result;
	}

	@Override
	public ReturnBusquedas<UsuarioEntity> buscarUsuariosEliminados(final int inicio, final int tamanhoPagina) {
		final List<UsuarioEntity> usuariosToret = new ArrayList<>();

		final List<UsuarioEntity> usuarios = entityManager.createNamedQuery("UsuarioEntity.findUsuariosEliminados")
				.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();

		final Integer numberTotalResults = usuarioRepository.numberFindUsuariosEliminados();

		if (!usuarios.isEmpty()) {
			for (final UsuarioEntity usuario : usuarios) {
				final RolEntity rolUsuario = new RolEntity(usuario.getRol().getIdRol(), usuario.getRol().getRolName(),
						usuario.getRol().getRolDescription(), usuario.getRol().getBorradoRol());
				final UsuarioEntity user = new UsuarioEntity(usuario.getDniUsuario(), usuario.getUsuario(),
						usuario.getPasswdUsuario(), usuario.getBorradoUsuario(), rolUsuario);

				usuariosToret.add(user);
			}
		}
		final ReturnBusquedas<UsuarioEntity> result = new ReturnBusquedas<UsuarioEntity>(usuariosToret,
				numberTotalResults, usuariosToret.size());

		return result;
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
	public String reactivarUsuario(final Usuario usuario)
			throws UsuarioNoEncontradoException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			PersonaNoExisteException, ParseException {

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
			user.setBorradoUsuario(0);
			usuario.setUsuarioEntity(user);
			usuarioRepository.saveAndFlush(user);

			final Optional<PersonaEntity> persona = personaRepository.findById(user.getDniUsuario());

			if (persona.isPresent()) {
				final Persona person = new Persona(usuario.getUsuario(), persona.get());
				personaServiceImpl.reactivarPersona(person);
			} else {
				logExcepciones = util.generarDatosLogExcepciones(usuario.getUsuario(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new PersonaNoExisteException(CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo()));
			}

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
				resultado = CodeMessageErrors.USUARIO_VACIO.name();
			}
		} else {
			resultado = CodeMessageErrors.ROL_VACIO.name();
		}

		return resultado;
	}

	@Override
	public String cambiarContraseña(final String usuario, final String passwdUsuario)
			throws UsuarioNoEncontradoException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException {
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		String resultadoLog2 = StringUtils.EMPTY;

		final Boolean usuarioValido = validaciones.comprobarUsuarioBlank(usuario);
		final Boolean passWdUsuarioValido = validaciones.comprobarPasswdUsuarioBlank(passwdUsuario);

		if (usuarioValido) {
			if (passWdUsuarioValido) {
				final UsuarioEntity usuarioBD = usuarioRepository.findByUsuario(usuario);

				if (usuarioBD == null) {
					final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(usuario,
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
					usuarioBD.setPasswdUsuario(passwdUsuario);
					usuarioRepository.saveAndFlush(usuarioBD);

					final LogAccionesEntity logAccionesBuscar = util.generarDatosLogAcciones(usuario,
							Constantes.ACCION_MODIFICAR_USUARIO, usuario);

					resultadoLog = logServiceImpl.guardarLogAcciones(logAccionesBuscar);

					final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(usuario,
							Constantes.ACCION_MODIFICAR_USUARIO, usuario);

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
