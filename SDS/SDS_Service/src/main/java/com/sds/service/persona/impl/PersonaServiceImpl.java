package com.sds.service.persona.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.model.EmpresaEntity;
import com.sds.model.LogAccionesEntity;
import com.sds.model.LogExcepcionesEntity;
import com.sds.model.PersonaEntity;
import com.sds.model.UsuarioEntity;
import com.sds.repository.PersonaRepository;
import com.sds.repository.UsuarioRepository;
import com.sds.service.common.Constantes;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.PersonaNoExisteException;
import com.sds.service.exception.PersonaYaExisteException;
import com.sds.service.exception.UsuarioAsociadoPersonaException;
import com.sds.service.log.LogService;
import com.sds.service.persona.PersonaService;
import com.sds.service.persona.model.Persona;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class PersonaServiceImpl implements PersonaService {

	private final Util util;
	private final Validaciones validaciones;

	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	LogService logServiceImpl;

	public PersonaServiceImpl() {
		util = new Util();
		validaciones = new Validaciones();
	}

	@Override
	public List<PersonaEntity> buscarTodos() {
		final List<PersonaEntity> personas = personaRepository.findAll();
		final List<PersonaEntity> toret = new ArrayList<>();

		if (!personas.isEmpty()) {
			for (final PersonaEntity persona : personas) {
				final PersonaEntity person = new PersonaEntity(persona.getDniP(), persona.getNombreP(),
						persona.getApellidosP(), persona.getFechaNacP(), persona.getDireccionP(),
						persona.getTelefonoP(), persona.getEmailP(), persona.getBorradoP(), persona.getEmpresa(),
						persona.getUsuario());

				toret.add(person);
			}
		}

		return toret;
	}

	@Override
	public List<PersonaEntity> buscarPersonasEliminadas() {
		final List<PersonaEntity> personas = personaRepository.findPersonasEliminadas(1);
		final List<PersonaEntity> toret = new ArrayList<>();

		if (!personas.isEmpty()) {
			for (final PersonaEntity persona : personas) {
				final PersonaEntity person = new PersonaEntity(persona.getDniP(), persona.getNombreP(),
						persona.getApellidosP(), persona.getFechaNacP(), persona.getDireccionP(),
						persona.getTelefonoP(), persona.getEmailP(), persona.getBorradoP(), persona.getEmpresa(),
						persona.getUsuario());

				toret.add(person);
			}
		}

		return toret;
	}

	@Override
	public List<PersonaEntity> buscarPersona(final String dniP, final String nombreP, final String apellidosP,
			final Date fechaNacP, final String direccionP, final String telefonoP, final String emailP,
			final EmpresaEntity empresa) {
		final List<PersonaEntity> personasToret = personaRepository.findPersona(dniP, nombreP, apellidosP, fechaNacP,
				direccionP, telefonoP, emailP, empresa);
		final List<PersonaEntity> toret = new ArrayList<>();

		if (!personasToret.isEmpty()) {
			for (final PersonaEntity persona : personasToret) {
				if (persona.getBorradoP() == 0) {

					final PersonaEntity person = new PersonaEntity(persona.getDniP(), persona.getNombreP(),
							persona.getApellidosP(), persona.getFechaNacP(), persona.getDireccionP(),
							persona.getTelefonoP(), persona.getEmailP(), persona.getBorradoP(), persona.getEmpresa(),
							persona.getUsuario());

					toret.add(person);
				}
			}
		}

		return toret;
	}

	@Override
	public String eliminarPersona(final Persona persona) throws LogExcepcionesNoGuardadoException,
			PersonaNoExisteException, UsuarioAsociadoPersonaException, ParseException, LogAccionesNoGuardadoException {
		final PersonaEntity personaEntity = persona.getPersona();

		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		String resultadoLog2 = StringUtils.EMPTY;

		LogExcepcionesEntity logExcepciones = new LogExcepcionesEntity();

		final Optional<PersonaEntity> person = personaRepository.findById(personaEntity.getDniP());

		if (!person.isPresent()) {
			logExcepciones = util.generarDatosLogExcepciones(persona.getUsuario(),
					CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo()),
					CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo());

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
				logExcepciones = util.generarDatosLogExcepciones(persona.getUsuario(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.USUARIO_ASOCIADO_PERSONA_EXCEPTION.getCodigo()),
						CodeMessageErrors.USUARIO_ASOCIADO_PERSONA_EXCEPTION.getCodigo());

				resultadoLog2 = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog2)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new UsuarioAsociadoPersonaException(
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.USUARIO_ASOCIADO_PERSONA_EXCEPTION.getCodigo()),
						CodeMessageErrors.USUARIO_ASOCIADO_PERSONA_EXCEPTION.getCodigo());

			} else {
				personaEntity.setBorradoP(1);
				persona.setPersona(personaEntity);
				modificarPersona(persona);
				resultado = Constantes.OK;
			}

		}

		return resultado;
	}

	@Override
	public String añadirPersona(final Persona persona) throws PersonaYaExisteException, ParseException,
			LogExcepcionesNoGuardadoException, LogAccionesNoGuardadoException {
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		final PersonaEntity personaEntity = persona.getPersona();

		final Boolean personaValida = validaciones.comprobarPersonaBlank(personaEntity);

		if (personaValida) {
			final Optional<PersonaEntity> personaBD = personaRepository.findById(personaEntity.getDniP());
			if (!personaBD.isPresent()) {
				personaRepository.saveAndFlush(personaEntity);

				final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(persona.getUsuario(),
						Constantes.ACCION_AÑADIR_PERSONA, persona.getPersona().toString());

				resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

				if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
				}

				resultado = Constantes.OK;
			} else {
				final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(persona.getUsuario(),
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
				personaEntity.setNombreP(personaEntity.getNombreP());
				personaEntity.setApellidosP(personaEntity.getApellidosP());
				personaEntity.setDireccionP(personaEntity.getDireccionP());
				personaEntity.setEmailP(personaEntity.getEmailP());
				personaEntity.setFechaNacP(personaEntity.getFechaNacP());
				personaEntity.setTelefonoP(personaEntity.getTelefonoP());

				persona.setPersona(personaEntity);

				personaRepository.saveAndFlush(personaEntity);

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
