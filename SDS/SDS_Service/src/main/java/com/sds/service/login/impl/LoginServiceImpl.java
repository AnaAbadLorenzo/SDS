package com.sds.service.login.impl;

import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.model.LogAccionesEntity;
import com.sds.model.LogExcepcionesEntity;
import com.sds.model.PersonaEntity;
import com.sds.model.UsuarioEntity;
import com.sds.repository.PersonaRepository;
import com.sds.repository.RolRepository;
import com.sds.repository.UsuarioRepository;
import com.sds.service.common.Constantes;
import com.sds.service.exception.EmailNoEncontradoException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.MailNoEnviadoException;
import com.sds.service.exception.PasswordIncorrectoException;
import com.sds.service.exception.PersonaNoExisteException;
import com.sds.service.exception.UsuarioNoEncontradoException;
import com.sds.service.log.LogService;
import com.sds.service.login.LoginService;
import com.sds.service.login.model.Login;
import com.sds.service.login.model.LoginRol;
import com.sds.service.mail.MailService;
import com.sds.service.mail.model.Mail;
import com.sds.service.usuario.UsuarioService;
import com.sds.service.usuario.model.Usuario;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	RolRepository rolRepository;

	@Autowired
	LogService logServiceImpl;

	@Autowired
	UsuarioService usuarioServiceImpl;

	@Autowired
	MailService mailServiceImpl;

	private final GetJWTToken jWTToken;
	private final Validaciones validaciones;
	private final Util util;

	public LoginServiceImpl() {
		jWTToken = new GetJWTToken();
		validaciones = new Validaciones();
		util = new Util();
	}

	@Override
	public LoginRol loginUser(final Login login) throws UsuarioNoEncontradoException, PasswordIncorrectoException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {

		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		final LoginRol result = new LoginRol();

		final Boolean loginValido = validaciones.comprobarLoginBlank(login);

		if (loginValido) {
			if (existeUsuario(login)) {
				resultado = jWTToken.getJWTToken(login.getUsuario());
				final UsuarioEntity usuario = usuarioRepository.findByUsuario(login.getUsuario());
				result.setTokenUsuario(resultado);
				result.setRolUsuario(usuario.getRol().getRolName());
				result.setUsuario(login.getUsuario());
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
			result.setTokenUsuario(resultado);
			result.setRolUsuario(StringUtils.EMPTY);
			result.setUsuario(login.getUsuario());
		}

		return result;
	}

	@Override
	public String recuperarPasswdUsuario(final String usuario, final String emailUsuario)
			throws UsuarioNoEncontradoException, MessagingException, LogExcepcionesNoGuardadoException,
			PersonaNoExisteException, EmailNoEncontradoException, LogAccionesNoGuardadoException,
			MailNoEnviadoException {
		String passwdTemp = StringUtils.EMPTY;
		String resultado = StringUtils.EMPTY;
		String resultadoLog = StringUtils.EMPTY;
		final Boolean usuarioValido = validaciones.comprobarUsuarioBlank(usuario);

		if (usuarioValido) {
			final Boolean emailUsuarioValido = validaciones.comprobarEmailUsuarioBlank(emailUsuario);
			if (emailUsuarioValido) {
				final UsuarioEntity user = usuarioRepository.findByUsuario(usuario);

				if (user != null) {
					final Optional<PersonaEntity> persona = personaRepository.findById(user.getDniUsuario());

					if (persona.isPresent()) {
						if (persona.get().getEmailP().equals(emailUsuario)) {
							passwdTemp = generarPasswdAleatoria();
							final Mail email = new Mail(Constantes.EMISOR_EMAIL, emailUsuario,
									Constantes.ASUNTO_EMAIL_RECU, passwdTemp, Constantes.TIPO_CONTENIDO, null);

							final String result = mailServiceImpl.enviarCorreo(email);

							if (result.equals(StringUtils.EMPTY)) {
								final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(usuario,
										CodeMessageErrors.getTipoNameByCodigo(
												CodeMessageErrors.MAIL_NO_ENVIADO_EXCEPTION.getCodigo()),
										CodeMessageErrors.MAIL_NO_ENVIADO_EXCEPTION.getCodigo());
								resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

								if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
									throw new LogExcepcionesNoGuardadoException(
											CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
											CodeMessageErrors.getTipoNameByCodigo(
													CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
								}

								throw new MailNoEnviadoException(
										CodeMessageErrors.MAIL_NO_ENVIADO_EXCEPTION.getCodigo(),
										CodeMessageErrors.getTipoNameByCodigo(
												CodeMessageErrors.MAIL_NO_ENVIADO_EXCEPTION.getCodigo()));
							} else {

								final String passEncrypt = DigestUtils.md5Hex(passwdTemp);

								user.setPasswdUsuario(passEncrypt);
								final Usuario usuarioM = new Usuario(usuario, user);
								usuarioServiceImpl.cambiarContraseña(usuarioM, passEncrypt);
								resultado = Constantes.OK;
							}

						} else {
							final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(usuario,
									CodeMessageErrors.getTipoNameByCodigo(
											CodeMessageErrors.EMAIL_NO_ENCONTRADO_EXCEPTION.getCodigo()),
									CodeMessageErrors.EMAIL_NO_ENCONTRADO_EXCEPTION.getCodigo());

							resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

							if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
								throw new LogExcepcionesNoGuardadoException(
										CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(),
										CodeMessageErrors.getTipoNameByCodigo(
												CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
							}

							throw new EmailNoEncontradoException(
									CodeMessageErrors.EMAIL_NO_ENCONTRADO_EXCEPTION.getCodigo(),
									CodeMessageErrors.getTipoNameByCodigo(
											CodeMessageErrors.EMAIL_NO_ENCONTRADO_EXCEPTION.getCodigo()));
						}
					} else {
						final LogExcepcionesEntity logExcepciones = util.generarDatosLogExcepciones(usuario,
								CodeMessageErrors
										.getTipoNameByCodigo(CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo()),
								CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo());

						resultadoLog = logServiceImpl.guardarLogExcepciones(logExcepciones);

						if (CodeMessageErrors.LOG_EXCEPCIONES_VACIO.name().equals(resultadoLog)) {
							throw new LogExcepcionesNoGuardadoException(
									CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo(), CodeMessageErrors
											.getTipoNameByCodigo(CodeMessageErrors.LOG_EXCEPCIONES_VACIO.getCodigo()));
						}

						throw new PersonaNoExisteException(CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo(),
								CodeMessageErrors.getTipoNameByCodigo(
										CodeMessageErrors.PERSONA_NO_EXISTE_EXCEPTION.getCodigo()));
					}
				} else {
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
				}
			} else {
				resultado = CodeMessageErrors.EMAIL_VACIO.name();
			}
		} else {
			resultado = CodeMessageErrors.USUARIO_VACIO.name();
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

	private String generarPasswdAleatoria() {
		final int longitudPasswd = 10;
		String passwdTemporal = StringUtils.EMPTY;

		final char[] caract = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'e', 'h', 'i', 'j', 'l', 'k', 'm', 'n', 'o', 'p',
				'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
				'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6',
				'7', '8', '9' };

		final Random rand = new Random();

		for (int i = 0; i < longitudPasswd; i++) {
			passwdTemporal += caract[rand.nextInt(caract.length)];
		}

		return passwdTemporal;
	}

}
