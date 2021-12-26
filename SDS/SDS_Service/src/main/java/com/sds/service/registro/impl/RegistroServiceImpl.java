package com.sds.service.registro.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.model.EmpresaEntity;
import com.sds.model.LogAccionesEntity;
import com.sds.model.LogExcepcionesEntity;
import com.sds.model.PersonaEntity;
import com.sds.model.RolEntity;
import com.sds.model.UsuarioEntity;
import com.sds.repository.EmpresaRepository;
import com.sds.repository.PersonaRepository;
import com.sds.repository.RolRepository;
import com.sds.repository.UsuarioRepository;
import com.sds.service.common.Constantes;
import com.sds.service.exception.EmpresaYaExisteException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.PersonaYaExisteException;
import com.sds.service.exception.UsuarioYaExisteException;
import com.sds.service.log.LogService;
import com.sds.service.registro.RegistroService;
import com.sds.service.registro.model.Registro;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class RegistroServiceImpl implements RegistroService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	EmpresaRepository empresaRepository;

	@Autowired
	RolRepository rolRepository;

	@Autowired
	LogService logServiceImpl;

	private final Validaciones validaciones;
	private final Util util;

	public RegistroServiceImpl() {
		validaciones = new Validaciones();
		util = new Util();
	}

	@Override
	public String registrar(final Registro registro) throws UsuarioYaExisteException, PersonaYaExisteException,
			EmpresaYaExisteException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {

		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		LogAccionesEntity logAcciones = new LogAccionesEntity();

		final boolean registroValido = validaciones.comprobarRegistroBlank(registro);

		if (registroValido) {
			if (!existeRegistro(registro)) {

				if (registro.getDatosEmpresa().getIdEmpresa() == null) {
					final EmpresaEntity empresa = empresaRepository
							.findByCif(registro.getDatosEmpresa().getCifEmpresa());

					if (empresa == null) {
						registro.getDatosEmpresa().setBorradoEmpresa(0);
						empresaRepository.saveAndFlush(registro.getDatosEmpresa());

						logAcciones = util.generarDatosLogAcciones(Constantes.USUARIO_GENERICO, Constantes.REGISTRAR,
								registro.getDatosEmpresa().toString());

						resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

						if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
							throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
									CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
						}
					} else {
						final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(
								Constantes.USUARIO_GENERICO,
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.EMPRESA_YA_EXISTE_EXCEPTION.getCodigo()),
								CodeMessageErrors.EMPRESA_YA_EXISTE_EXCEPTION.getCodigo());

						resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

						if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
							throw new LogExcepcionesNoGuardadoException(
									CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(), CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
						}

						throw new EmpresaYaExisteException(CodeMessageErrors.EMPRESA_YA_EXISTE_EXCEPTION.getCodigo(),
								CodeMessageErrors.getTipoNameByCodigo(
										CodeMessageErrors.EMPRESA_YA_EXISTE_EXCEPTION.getCodigo()));
					}

				}

				registro.getDatosPersona().setBorradoP(0);
				registro.getDatosPersona().setEmpresa(registro.getDatosEmpresa());
				personaRepository.saveAndFlush(registro.getDatosPersona());

				logAcciones = util.generarDatosLogAcciones(Constantes.USUARIO_GENERICO, Constantes.REGISTRAR,
						registro.getDatosPersona().toString());

				resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

				if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
				}

				final RolEntity rol = rolRepository.findByRolName(Constantes.USUARIO);
				registro.getDatosUsuario().setRol(rol);
				registro.getDatosUsuario().setPersona(registro.getDatosPersona());
				registro.getDatosUsuario().setDniUsuario(registro.getDatosPersona().getDniP());
				registro.getDatosUsuario().setBorradoUsuario(0);
				usuarioRepository.saveAndFlush(registro.getDatosUsuario());

				logAcciones = util.generarDatosLogAcciones(Constantes.USUARIO_GENERICO, Constantes.REGISTRAR,
						registro.getDatosUsuario().toString());

				resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

				if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
				}

				resultado = Constantes.OK;
			}
		} else {
			resultado = CodeMessageErrors.REGISTRO_VACIO.name();
		}

		return resultado;

	}

	public boolean existeRegistro(final Registro registro) throws UsuarioYaExisteException, PersonaYaExisteException,
			EmpresaYaExisteException, LogExcepcionesNoGuardadoException {
		final Optional<PersonaEntity> persona = personaRepository.findById(registro.getDatosPersona().getDniP());
		LogExcepcionesEntity logExcepciones = new LogExcepcionesEntity();
		String resultadoLog = StringUtils.EMPTY;

		if (!persona.isPresent()) {
			final UsuarioEntity usuario = usuarioRepository.findByUsuario(registro.getDatosUsuario().getUsuario());

			if (usuario == null) {
				return false;
			} else {
				logExcepciones = util.generarDatosLogExcepciones(Constantes.USUARIO_GENERICO,
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.USUARIO_YA_EXISTE_EXCEPTION.getCodigo()),
						CodeMessageErrors.USUARIO_YA_EXISTE_EXCEPTION.getCodigo());

				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new UsuarioYaExisteException(CodeMessageErrors.USUARIO_YA_EXISTE_EXCEPTION.getCodigo(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.USUARIO_YA_EXISTE_EXCEPTION.getCodigo()));
			}
		} else {
			logExcepciones = util.generarDatosLogExcepciones(Constantes.USUARIO_GENERICO,
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PERSONA_YA_EXISTE_EXCEPTION.getCodigo()),
					CodeMessageErrors.PERSONA_YA_EXISTE_EXCEPTION.getCodigo());

			resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

			if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
				throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
						CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
			}

			throw new PersonaYaExisteException(CodeMessageErrors.PERSONA_YA_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PERSONA_YA_EXISTE_EXCEPTION.getCodigo()));
		}
	}
}
