package com.sds.service.persona.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.sds.model.EmpresaEntity;
import com.sds.model.LogAccionesEntity;
import com.sds.model.LogExcepcionesEntity;
import com.sds.model.PersonaEntity;
import com.sds.model.RolEntity;
import com.sds.model.UsuarioEntity;
import com.sds.repository.EmpresaRepository;
import com.sds.repository.PersonaRepository;
import com.sds.repository.UsuarioRepository;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.EmpresaNoEncontradaException;
import com.sds.service.exception.EmpresaYaExisteException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.PersonaNoExisteException;
import com.sds.service.exception.PersonaYaExisteException;
import com.sds.service.exception.UsuarioAsociadoPersonaException;
import com.sds.service.exception.UsuarioYaExisteException;
import com.sds.service.log.LogService;
import com.sds.service.persona.PersonaService;
import com.sds.service.persona.model.Persona;
import com.sds.service.persona.model.PersonaAnadir;
import com.sds.service.persona.model.PersonaAsociarEmpresa;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class PersonaServiceImpl implements PersonaService {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	EmpresaRepository empresaRepository;

	@Autowired
	LogService logServiceImpl;

	private final Util util;
	private final Validaciones validaciones;

	public PersonaServiceImpl() {
		util = new Util();
		validaciones = new Validaciones();
	}

	@Override
	public ReturnBusquedas<PersonaEntity> buscarTodos(final int inicio, final int tamanhoPagina) throws ParseException {

		final List<PersonaEntity> personas = entityManager.createNamedQuery(Constantes.PERSONA_QUERY_FINDALL)
				.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();
		final List<PersonaEntity> toret = new ArrayList<>();

		final Integer numberTotalResults = personaRepository.numberFindAll();

		if (!personas.isEmpty()) {
			for (final PersonaEntity persona : personas) {
				if (persona.getEmpresa() == null) {
					final Optional<UsuarioEntity> usuario = usuarioRepository.findById(persona.getDniP());
					final RolEntity rol = new RolEntity(usuario.get().getRol().getIdRol(),
							usuario.get().getRol().getRolName(), usuario.get().getRol().getRolDescription(),
							usuario.get().getRol().getBorradoRol());
					final UsuarioEntity user = new UsuarioEntity(usuario.get().getDniUsuario(),
							usuario.get().getUsuario(), usuario.get().getPasswdUsuario(),
							usuario.get().getBorradoUsuario(), rol);
					final PersonaEntity person = new PersonaEntity(persona.getDniP(), persona.getNombreP(),
							persona.getApellidosP(), persona.getFechaNacP(), persona.getDireccionP(),
							persona.getTelefonoP(), persona.getEmailP(), persona.getBorradoP(), null, user);

					toret.add(person);
				} else {
					final Optional<UsuarioEntity> usuario = usuarioRepository.findById(persona.getDniP());
					final RolEntity rol = new RolEntity(usuario.get().getRol().getIdRol(),
							usuario.get().getRol().getRolName(), usuario.get().getRol().getRolDescription(),
							usuario.get().getRol().getBorradoRol());
					final UsuarioEntity user = new UsuarioEntity(usuario.get().getDniUsuario(),
							usuario.get().getUsuario(), usuario.get().getPasswdUsuario(),
							usuario.get().getBorradoUsuario(), rol);
					final EmpresaEntity empresa = new EmpresaEntity(persona.getEmpresa().getIdEmpresa(),
							persona.getEmpresa().getCifEmpresa(), persona.getEmpresa().getNombreEmpresa(),
							persona.getEmpresa().getDireccionEmpresa(), persona.getEmpresa().getTelefonoEmpresa(),
							persona.getEmpresa().getBorradoEmpresa());

					final PersonaEntity person = new PersonaEntity(persona.getDniP(), persona.getNombreP(),
							persona.getApellidosP(), persona.getFechaNacP(), persona.getDireccionP(),
							persona.getTelefonoP(), persona.getEmailP(), persona.getBorradoP(), empresa, user);
					toret.add(person);
				}
			}
		}

		final ReturnBusquedas<PersonaEntity> ret = new ReturnBusquedas<PersonaEntity>(toret, numberTotalResults,
				toret.size(), inicio);

		return ret;
	}

	@Override
	public ReturnBusquedas<PersonaEntity> buscarPersonasEliminadas(final int inicio, final int tamanhoPagina)
			throws ParseException {

		final List<PersonaEntity> personas = entityManager.createNamedQuery(Constantes.PERSONA_QUERY_FINDELIMINADAS)
				.setFirstResult(inicio).setMaxResults(tamanhoPagina).getResultList();

		final List<PersonaEntity> toret = new ArrayList<>();

		final Integer numberTotalResults = personaRepository.numberPersonasEliminadas();

		if (!personas.isEmpty()) {
			for (final PersonaEntity persona : personas) {
				if (persona.getEmpresa() == null) {
					final Optional<UsuarioEntity> usuario = usuarioRepository.findById(persona.getDniP());
					final RolEntity rol = new RolEntity(usuario.get().getRol().getIdRol(),
							usuario.get().getRol().getRolName(), usuario.get().getRol().getRolDescription(),
							usuario.get().getRol().getBorradoRol());
					final UsuarioEntity user = new UsuarioEntity(usuario.get().getDniUsuario(),
							usuario.get().getUsuario(), usuario.get().getPasswdUsuario(),
							usuario.get().getBorradoUsuario(), rol);
					final PersonaEntity person = new PersonaEntity(persona.getDniP(), persona.getNombreP(),
							persona.getApellidosP(), persona.getFechaNacP(), persona.getDireccionP(),
							persona.getTelefonoP(), persona.getEmailP(), persona.getBorradoP(), null, user);
					toret.add(person);
				} else {
					final Optional<UsuarioEntity> usuario = usuarioRepository.findById(persona.getDniP());
					final RolEntity rol = new RolEntity(usuario.get().getRol().getIdRol(),
							usuario.get().getRol().getRolName(), usuario.get().getRol().getRolDescription(),
							usuario.get().getRol().getBorradoRol());
					final UsuarioEntity user = new UsuarioEntity(usuario.get().getDniUsuario(),
							usuario.get().getUsuario(), usuario.get().getPasswdUsuario(),
							usuario.get().getBorradoUsuario(), rol);
					final EmpresaEntity empresa = new EmpresaEntity(persona.getEmpresa().getIdEmpresa(),
							persona.getEmpresa().getCifEmpresa(), persona.getEmpresa().getNombreEmpresa(),
							persona.getEmpresa().getDireccionEmpresa(), persona.getEmpresa().getTelefonoEmpresa(),
							persona.getEmpresa().getBorradoEmpresa());

					final PersonaEntity person = new PersonaEntity(persona.getDniP(), persona.getNombreP(),
							persona.getApellidosP(), persona.getFechaNacP(), persona.getDireccionP(),
							persona.getTelefonoP(), persona.getEmailP(), persona.getBorradoP(), empresa, user);
					toret.add(person);
				}
			}
		}

		final ReturnBusquedas<PersonaEntity> ret = new ReturnBusquedas<PersonaEntity>(toret, numberTotalResults,
				toret.size(), inicio);

		return ret;
	}

	@Override
	public ReturnBusquedas<PersonaEntity> buscarPersona(final String dniP, final String nombreP,
			final String apellidosP, final Date fechaNacP, final String direccionP, final String telefonoP,
			final String emailP, final int inicio, final int tamanhoPagina) throws ParseException {

		final List<PersonaEntity> toret = new ArrayList<>();
		List<PersonaEntity> personasToret = new ArrayList<>();
		String fecha = StringUtils.EMPTY;
		final List<String> datosBusqueda = new ArrayList<>();

		if (fechaNacP != null) {
			java.sql.Date fechaSql;
			fechaSql = new java.sql.Date(fechaNacP.getTime());
			fecha = fechaSql.toString();
		} else {
			fecha = StringUtils.EMPTY;
		}

		personasToret = entityManager.createNamedQuery(Constantes.PERSONA_QUERY_FINDPERSONA)
				.setParameter(Constantes.DNIP, dniP).setParameter(Constantes.NOMBREP, nombreP)
				.setParameter(Constantes.APELLIDOSP, apellidosP).setParameter(Constantes.FECHANACP, fecha)
				.setParameter(Constantes.DIRECCIONP, direccionP).setParameter(Constantes.TELEFONOP, telefonoP)
				.setParameter(Constantes.EMAILP, emailP).setFirstResult(inicio).setMaxResults(tamanhoPagina)
				.getResultList();

		final Integer numberTotalResults = personaRepository.numberFindPersona(dniP, nombreP, apellidosP, fecha,
				direccionP, telefonoP, emailP);

		if (!personasToret.isEmpty()) {
			for (final PersonaEntity persona : personasToret) {
				if (persona.getEmpresa() == null) {
					final Optional<UsuarioEntity> usuario = usuarioRepository.findById(persona.getDniP());
					final RolEntity rol = new RolEntity(usuario.get().getRol().getIdRol(),
							usuario.get().getRol().getRolName(), usuario.get().getRol().getRolDescription(),
							usuario.get().getRol().getBorradoRol());
					final UsuarioEntity user = new UsuarioEntity(usuario.get().getDniUsuario(),
							usuario.get().getUsuario(), usuario.get().getPasswdUsuario(),
							usuario.get().getBorradoUsuario(), rol);
					final PersonaEntity person = new PersonaEntity(persona.getDniP(), persona.getNombreP(),
							persona.getApellidosP(), persona.getFechaNacP(), persona.getDireccionP(),
							persona.getTelefonoP(), persona.getEmailP(), persona.getBorradoP(), null, user);
					toret.add(person);
				} else {
					final Optional<UsuarioEntity> usuario = usuarioRepository.findById(persona.getDniP());
					final RolEntity rol = new RolEntity(usuario.get().getRol().getIdRol(),
							usuario.get().getRol().getRolName(), usuario.get().getRol().getRolDescription(),
							usuario.get().getRol().getBorradoRol());
					final UsuarioEntity user = new UsuarioEntity(usuario.get().getDniUsuario(),
							usuario.get().getUsuario(), usuario.get().getPasswdUsuario(),
							usuario.get().getBorradoUsuario(), rol);
					final EmpresaEntity empresa = new EmpresaEntity(persona.getEmpresa().getIdEmpresa(),
							persona.getEmpresa().getCifEmpresa(), persona.getEmpresa().getNombreEmpresa(),
							persona.getEmpresa().getDireccionEmpresa(), persona.getEmpresa().getTelefonoEmpresa(),
							persona.getEmpresa().getBorradoEmpresa());

					final PersonaEntity person = new PersonaEntity(persona.getDniP(), persona.getNombreP(),
							persona.getApellidosP(), persona.getFechaNacP(), persona.getDireccionP(),
							persona.getTelefonoP(), persona.getEmailP(), persona.getBorradoP(), empresa, user);
					toret.add(person);
				}
			}

		}

		datosBusqueda.add(Constantes.DNIP + Constantes.DOS_PUNTOS + dniP);
		datosBusqueda.add(Constantes.NOMBREP + Constantes.DOS_PUNTOS + nombreP);
		datosBusqueda.add(Constantes.APELLIDOSP + Constantes.DOS_PUNTOS + apellidosP);
		datosBusqueda.add(Constantes.FECHANACP + Constantes.DOS_PUNTOS + fechaNacP);
		datosBusqueda.add(Constantes.DIRECCIONP + Constantes.DOS_PUNTOS + direccionP);
		datosBusqueda.add(Constantes.TELEFONOP + Constantes.DOS_PUNTOS + telefonoP);
		datosBusqueda.add(Constantes.EMAILP + Constantes.DOS_PUNTOS + emailP);

		final ReturnBusquedas<PersonaEntity> ret = new ReturnBusquedas<PersonaEntity>(toret, datosBusqueda,
				numberTotalResults, toret.size(), inicio);

		return ret;
	}

	@Override
	public ReturnBusquedas<PersonaEntity> buscarPersonaByUsuario(final String usuario, final int inicio,
			final int tamanhoPagina) throws ParseException {

		final List<PersonaEntity> toret = new ArrayList<>();
		List<PersonaEntity> personasToret = new ArrayList<>();
		final List<String> datosBusqueda = new ArrayList<>();

		personasToret = entityManager.createNamedQuery(Constantes.PERSONA_QUERY_FINDPERSONABYUSUARIO)
				.setParameter(Constantes.USUARIO, usuario).setFirstResult(inicio).setMaxResults(tamanhoPagina)
				.getResultList();

		if (!personasToret.isEmpty()) {
			for (final PersonaEntity persona : personasToret) {
				if (persona.getEmpresa() == null) {
					final Optional<UsuarioEntity> user = usuarioRepository.findById(persona.getDniP());
					final RolEntity rol = new RolEntity(user.get().getRol().getIdRol(),
							user.get().getRol().getRolName(), user.get().getRol().getRolDescription(),
							user.get().getRol().getBorradoRol());
					final UsuarioEntity usu = new UsuarioEntity(user.get().getDniUsuario(), user.get().getUsuario(),
							user.get().getPasswdUsuario(), user.get().getBorradoUsuario(), rol);
					final PersonaEntity person = new PersonaEntity(persona.getDniP(), persona.getNombreP(),
							persona.getApellidosP(), persona.getFechaNacP(), persona.getDireccionP(),
							persona.getTelefonoP(), persona.getEmailP(), persona.getBorradoP(), null, usu);
					toret.add(person);
				} else {
					final Optional<UsuarioEntity> user = usuarioRepository.findById(persona.getDniP());
					final RolEntity rol = new RolEntity(user.get().getRol().getIdRol(),
							user.get().getRol().getRolName(), user.get().getRol().getRolDescription(),
							user.get().getRol().getBorradoRol());
					final UsuarioEntity usu = new UsuarioEntity(user.get().getDniUsuario(), user.get().getUsuario(),
							user.get().getPasswdUsuario(), user.get().getBorradoUsuario(), rol);
					final EmpresaEntity empresa = new EmpresaEntity(persona.getEmpresa().getIdEmpresa(),
							persona.getEmpresa().getCifEmpresa(), persona.getEmpresa().getNombreEmpresa(),
							persona.getEmpresa().getDireccionEmpresa(), persona.getEmpresa().getTelefonoEmpresa(),
							persona.getEmpresa().getBorradoEmpresa());

					final PersonaEntity person = new PersonaEntity(persona.getDniP(), persona.getNombreP(),
							persona.getApellidosP(), persona.getFechaNacP(), persona.getDireccionP(),
							persona.getTelefonoP(), persona.getEmailP(), persona.getBorradoP(), empresa, usu);
					toret.add(person);
				}
			}

		}
		datosBusqueda.add(Constantes.USUARIO + Constantes.DOS_PUNTOS + usuario);

		final ReturnBusquedas<PersonaEntity> ret = new ReturnBusquedas<PersonaEntity>(toret, datosBusqueda, 1,
				toret.size(), inicio);

		return ret;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String eliminarPersona(final Persona persona) throws LogExcepcionesNoGuardadoException,
			PersonaNoExisteException, ParseException, LogAccionesNoGuardadoException {
		final PersonaEntity personaEntity = persona.getPersona();

		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		LogExcepcionesEntity logExcepciones = new LogExcepcionesEntity();

		final Optional<PersonaEntity> person = personaRepository.findById(personaEntity.getDniP());

		if (!person.isPresent()) {
			logExcepciones = util.generarDatosLogExcepciones(persona.getUsuario(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new PersonaNoExisteException(
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo());
		} else {
			final Optional<UsuarioEntity> user = usuarioRepository.findById(personaEntity.getDniP());

			if (user.isPresent()) {
				user.get().setBorradoUsuario(1);
				usuarioRepository.saveAndFlush(user.get());
				person.get().setBorradoP(1);
				persona.setPersona(person.get());
				modificarPersona(persona);
				resultado = Constantes.OK;

			} else {
				person.get().setBorradoP(1);
				persona.setPersona(person.get());
				modificarPersona(persona);
				resultado = Constantes.OK;
			}

		}

		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String añadirPersona(final PersonaAnadir personaAñadir) throws PersonaYaExisteException,
			UsuarioYaExisteException, ParseException, LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException,
			EmpresaNoEncontradaException, EmpresaYaExisteException {
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		String resultadoLog2 = StringUtils.EMPTY;
		LogAccionesEntity logAcciones = new LogAccionesEntity();
		final PersonaEntity personaEntity = personaAñadir.getPersonaEntity();
		final UsuarioEntity usuarioEntity = personaAñadir.getUsuarioEntity();
		final EmpresaEntity empresaEntity = personaAñadir.getEmpresaEntity();

		final Boolean personaValida = validaciones.comprobarPersonaBlank(personaEntity);

		if (personaValida) {
			final Optional<PersonaEntity> personaBD = personaRepository.findById(personaEntity.getDniP());
			if (!personaBD.isPresent()) {
				final Boolean usuarioValido = validaciones.comprobarUsuarioBlank(usuarioEntity);

				if (usuarioValido) {
					final Optional<UsuarioEntity> usuarioBD = usuarioRepository.findById(usuarioEntity.getDniUsuario());

					if (!usuarioBD.isPresent()) {
						if (empresaEntity != null) {
							if (empresaEntity.getIdEmpresa() == null
									&& personaAñadir.getSeleccionarEmpresa().equals(Constantes.NO)) {
								if (validaciones.comprobarEmpresaBlank(empresaEntity)) {
									final EmpresaEntity empresa = empresaRepository
											.findByCif(empresaEntity.getCifEmpresa());

									if (empresa != null) {
										final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
												personaAñadir.getUsuario(),
												CodeMessageErrors.getTipoNameByCodigo(
														CodeMessageErrors.EMPRESA_YA_EXISTE_EXCEPTION.getCodigo()),
												CodeMessageErrors.EMPRESA_YA_EXISTE_EXCEPTION.getCodigo());

										resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

										if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
											throw new LogExcepcionesNoGuardadoException(
													CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
													CodeMessageErrors.getTipoNameByCodigo(
															CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
										}

										throw new EmpresaYaExisteException(
												CodeMessageErrors.EMPRESA_YA_EXISTE_EXCEPTION.getCodigo(),
												CodeMessageErrors.getTipoNameByCodigo(
														CodeMessageErrors.EMPRESA_YA_EXISTE_EXCEPTION.getCodigo()));

									} else {

										empresaEntity.setBorradoEmpresa(0);
										empresaRepository.saveAndFlush(empresaEntity);
										logAcciones = util.generarDatosLogAcciones(personaAñadir.getUsuario(),
												Constantes.ACCION_AÑADIR_EMPRESA, empresaEntity.toString());

										resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

										if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
											throw new LogAccionesNoGuardadoException(
													CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
													CodeMessageErrors.getTipoNameByCodigo(
															CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
										}

										personaAñadir.getPersonaEntity().setEmpresa(empresaEntity);
									}
								} else {
									resultado = CodeMessageErrors.PERSONA_VACIO.name();
								}

							} else {
								final Optional<EmpresaEntity> empresa = empresaRepository
										.findById(personaAñadir.getEmpresaEntity().getIdEmpresa());

								if (empresa.isPresent()) {
									personaAñadir.setEmpresaEntity(empresa.get());
									personaAñadir.getPersonaEntity().setEmpresa(personaAñadir.getEmpresaEntity());
								} else {
									final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
											personaAñadir.getUsuario(),
											CodeMessageErrors.getTipoNameByCodigo(
													CodeMessageErrors.EMPRESA_NO_ENCONTRADA_EXCEPTION.getCodigo()),
											CodeMessageErrors.EMPRESA_NO_ENCONTRADA_EXCEPTION.getCodigo());

									resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

									if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
										throw new LogExcepcionesNoGuardadoException(
												CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
												CodeMessageErrors.getTipoNameByCodigo(
														CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
									}

									throw new EmpresaNoEncontradaException(
											CodeMessageErrors.EMPRESA_NO_ENCONTRADA_EXCEPTION.getCodigo(),
											CodeMessageErrors.getTipoNameByCodigo(
													CodeMessageErrors.EMPRESA_NO_ENCONTRADA_EXCEPTION.getCodigo()));
								}
							}
						}

						personaRepository.saveAndFlush(personaEntity);
						usuarioRepository.saveAndFlush(usuarioEntity);

						resultado = Constantes.OK;

						logAcciones = util.generarDatosLogAcciones(personaAñadir.getUsuario(),
								Constantes.ACCION_AÑADIR_PERSONA, personaAñadir.getPersonaEntity().toString());

						final LogAccionesEntity logAcciones2 = util.generarDatosLogAcciones(personaAñadir.getUsuario(),
								Constantes.ACCION_AÑADIR_USUARIO, personaAñadir.getPersonaEntity().toString());

						resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);
						resultadoLog2 = logServiceImpl.guardarLogAcciones(logAcciones2);

						if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
							throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
									CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
						}

						if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog2)) {
							throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
									CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
						}
					} else {
						final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
								personaAñadir.getUsuario(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.USUARIO_YA_EXISTE_EXCEPTION.getCodigo()),
								CodeMessageErrors.USUARIO_YA_EXISTE_EXCEPTION.getCodigo());

						resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

						if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
							throw new LogExcepcionesNoGuardadoException(
									CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(), CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
						}

						throw new UsuarioYaExisteException(CodeMessageErrors.USUARIO_YA_EXISTE_EXCEPTION.getCodigo(),
								CodeMessageErrors.getTipoNameByCodigo(
										CodeMessageErrors.USUARIO_YA_EXISTE_EXCEPTION.getCodigo()));

					}
				} else {
					resultado = CodeMessageErrors.USUARIO_VACIO.name();
				}

			} else {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(personaAñadir.getUsuario(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.PERSONA_YA_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.PERSONA_YA_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new PersonaYaExisteException(CodeMessageErrors.PERSONA_YA_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.PERSONA_YA_EXISTE_EXCEPTION.getCodigo()));
			}
		} else {
			resultado = CodeMessageErrors.PERSONA_VACIO.name();
		}

		return resultado;
	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String modificarPersona(final Persona persona) throws LogExcepcionesNoGuardadoException,
			PersonaNoExisteException, ParseException, LogAccionesNoGuardadoException {
		final PersonaEntity personaEntity = persona.getPersona();
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		String resultadoLog2 = StringUtils.EMPTY;

		final Boolean personaValida = validaciones.comprobarPersonaBlank(personaEntity);

		LogExcepcionesEntity logExcepciones = new LogExcepcionesEntity();

		if (personaValida) {

			final Optional<PersonaEntity> person = personaRepository.findById(personaEntity.getDniP());

			if (!person.isPresent()) {
				logExcepciones = util.generarDatosLogExcepciones(persona.getUsuario(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new PersonaNoExisteException(
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo());
			} else {
				person.get().setNombreP(personaEntity.getNombreP());
				person.get().setApellidosP(personaEntity.getApellidosP());
				person.get().setDireccionP(personaEntity.getDireccionP());
				person.get().setEmailP(personaEntity.getEmailP());
				person.get().setFechaNacP(personaEntity.getFechaNacP());
				person.get().setTelefonoP(personaEntity.getTelefonoP());

				if (persona.getEmpresa() != null) {
					if (persona.getEmpresa().getCifEmpresa().equals("")) {
						final Optional<EmpresaEntity> empresaBD = empresaRepository
								.findById(persona.getEmpresa().getIdEmpresa());
						person.get().setEmpresa(empresaBD.get());
					} else {
						final EmpresaEntity empresaBD = empresaRepository
								.findByCif(persona.getEmpresa().getCifEmpresa());
						person.get().setEmpresa(empresaBD);
					}
				} else {
					person.get().setEmpresa(null);
				}

				persona.setPersona(person.get());

				personaRepository.saveAndFlush(person.get());

				final LogAccionesEntity logAccionesBuscar = util.generarDatosLogAcciones(persona.getUsuario(),
						Constantes.ACCION_BUSCAR_PERSONA, persona.getUsuario());

				resultadoLog = logServiceImpl.guardarLogAcciones(logAccionesBuscar);

				final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(persona.getUsuario(),
						Constantes.ACCION_MODIFICAR_PERSONA, persona.getPersona().toString());

				resultadoLog2 = logServiceImpl.guardarLogAcciones(logAcciones);

				if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)
						|| CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog2)) {
					throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
				}

				resultado = Constantes.OK;
			}
		} else {
			resultado = CodeMessageErrors.PERSONA_VACIO.name();
		}

		return resultado;

	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String reactivarPersona(final Persona persona) throws LogExcepcionesNoGuardadoException,
			PersonaNoExisteException, ParseException, LogAccionesNoGuardadoException {
		final PersonaEntity personaEntity = persona.getPersona();
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		LogExcepcionesEntity logExcepciones = new LogExcepcionesEntity();

		final Optional<PersonaEntity> person = personaRepository.findById(personaEntity.getDniP());

		if (!person.isPresent()) {
			logExcepciones = util.generarDatosLogExcepciones(persona.getUsuario(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new PersonaNoExisteException(
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo());
		} else {

			personaEntity.setBorradoP(0);
			persona.setPersona(personaEntity);
			resultado = modificarPersona(persona);

		}

		return resultado;

	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public String asociarEmpresaPersona(final PersonaAsociarEmpresa personaEmpresa)
			throws LogExcepcionesNoGuardadoException, PersonaNoExisteException, ParseException,
			LogAccionesNoGuardadoException, EmpresaNoEncontradaException {
		final PersonaEntity personaEntity = personaEmpresa.getPersona();
		final EmpresaEntity empresaEntity = personaEmpresa.getEmpresa();
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		LogExcepcionesEntity logExcepciones = new LogExcepcionesEntity();

		final Optional<PersonaEntity> person = personaRepository.findById(personaEntity.getDniP());

		if (!person.isPresent()) {
			logExcepciones = util.generarDatosLogExcepciones(personaEmpresa.getUsuario(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new PersonaNoExisteException(
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo());
		} else {
			if (personaEmpresa.getEmpresa() != null) {
				final Optional<EmpresaEntity> empresaBD = empresaRepository.findById(empresaEntity.getIdEmpresa());

				if (!empresaBD.isPresent()) {
					logExcepciones = util.generarDatosLogExcepciones(personaEmpresa.getUsuario(),
							CodeMessageErrors
									.getTipoNameByCodigo(CodeMessageErrors.EMPRESA_NO_ENCONTRADA_EXCEPTION.getCodigo()),
							CodeMessageErrors.EMPRESA_NO_ENCONTRADA_EXCEPTION.getCodigo());

					resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

					if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
						throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
					}

					throw new EmpresaNoEncontradaException(
							CodeMessageErrors
									.getTipoNameByCodigo(CodeMessageErrors.EMPRESA_NO_ENCONTRADA_EXCEPTION.getCodigo()),
							CodeMessageErrors.EMPRESA_NO_ENCONTRADA_EXCEPTION.getCodigo());
				} else {
					person.get().setEmpresa(empresaBD.get());
					personaRepository.saveAndFlush(person.get());

					resultado = Constantes.OK;
				}
			} else {
				person.get().setEmpresa(null);
				personaRepository.saveAndFlush(person.get());
				resultado = Constantes.OK;
			}

		}

		return resultado;

	}

	@Override
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public void deletePersona(final PersonaEntity persona)
			throws PersonaNoExisteException, ParseException, UsuarioAsociadoPersonaException {
		final Boolean personaValida = validaciones.comprobarPersonaBlank(persona);

		if (personaValida) {

			final Optional<PersonaEntity> person = personaRepository.findById(persona.getDniP());

			if (!person.isPresent()) {
				throw new PersonaNoExisteException(
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo());
			} else {
				final Optional<UsuarioEntity> user = usuarioRepository.findById(persona.getDniP());

				if (user.isPresent()) {
					throw new UsuarioAsociadoPersonaException(
							CodeMessageErrors.getTipoNameByCodigo(
									CodeMessageErrors.USUARIO_ASOCIADO_PERSONA_EXCEPTION.getCodigo()),
							CodeMessageErrors.USUARIO_ASOCIADO_PERSONA_EXCEPTION.getCodigo());

				} else {
					personaRepository.deletePersona(persona.getDniP());
					personaRepository.flush();
				}
			}
		}
	}

}
