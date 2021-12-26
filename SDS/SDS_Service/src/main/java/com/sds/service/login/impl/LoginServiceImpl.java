package com.sds.service.login.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.model.LogAccionesEntity;
import com.sds.model.LogExcepcionesEntity;
import com.sds.model.UsuarioEntity;
import com.sds.repository.UsuarioRepository;
import com.sds.service.common.Constantes;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.PasswordIncorrectoException;
import com.sds.service.exception.UsuarioNoEncontradoException;
import com.sds.service.log.LogService;
import com.sds.service.login.LoginService;
import com.sds.service.login.model.Login;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	LogService logServiceImpl;

	private final GetJWTToken jWTToken;
	private final Validaciones validaciones;
	private final Util util;

	public LoginServiceImpl() {
		jWTToken = new GetJWTToken();
		validaciones = new Validaciones();
		util = new Util();
	}

	@Override
	public String loginUser(final Login login) throws UsuarioNoEncontradoException, PasswordIncorrectoException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {

		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;

		final Boolean loginValido = validaciones.comprobarLoginBlank(login);

		if (loginValido) {
			if (existeUsuario(login)) {
				resultado = jWTToken.getJWTToken(login.getUsuario());

				final LogAccionesEntity logAcciones = util.generarDatosLogAcciones(login.getUsuario(), Constantes.LOGIN,
						login.toString());
				resultadoLog = logServiceImpl.guardarLogAcciones(logAcciones);

				if (CodeMessageErrors.LOG_ACCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogAccionesNoGuardadoException(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_ACCIONES_VACIO.getCodigo()));
				}
			}
		} else {
			resultado = CodeMessageErrors.LOGIN_VACIO.name();
		}

		return resultado;
	}

	private boolean existeUsuario(final Login login)
			throws UsuarioNoEncontradoException, PasswordIncorrectoException, LogExcepcionesNoGuardadoException {

		final UsuarioEntity usuario = usuarioRepository.findByUsuario(login.getUsuario());
		LogExcepcionesEntity logExcepciones = new LogExcepcionesEntity();
		String resultadoLog = StringUtils.EMPTY;

		if (usuario == null) {

			logExcepciones = util.generarDatosLogExcepciones(login.getUsuario(),
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
			final String pass = login.getPasswdUsuario();

			if (pass.equals(usuario.getPasswdUsuario())) {
				return true;
			} else {
				logExcepciones = util.generarDatosLogExcepciones(login.getUsuario(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.PASSWORD_INCORRECTO_EXCEPTION.getCodigo()),
						CodeMessageErrors.PASSWORD_INCORRECTO_EXCEPTION.getCodigo());
				resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

				if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
					throw new LogExcepcionesNoGuardadoException(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
							CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
				}

				throw new PasswordIncorrectoException(CodeMessageErrors.PASSWORD_INCORRECTO_EXCEPTION.getCodigo(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.PASSWORD_INCORRECTO_EXCEPTION.getCodigo()));
			}
		}
	}

}
