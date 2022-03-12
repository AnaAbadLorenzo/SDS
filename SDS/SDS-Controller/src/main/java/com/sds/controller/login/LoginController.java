package com.sds.controller.login;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.service.exception.EmailNoEncontradoException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.MailNoEnviadoException;
import com.sds.service.exception.PasswordIncorrectoException;
import com.sds.service.exception.PersonaNoExisteException;
import com.sds.service.exception.UsuarioNoEncontradoException;
import com.sds.service.login.LoginService;
import com.sds.service.login.model.Login;
import com.sds.service.login.model.LoginRol;
import com.sds.service.login.model.RecuperarPass;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	LoginService loginService;

	private final Validaciones validaciones;

	public LoginController() {
		validaciones = new Validaciones();
	}

	@PostMapping(value = "/login")
	@ResponseBody
	public RespEntity loginUser(@RequestBody final Login login) {

		final Boolean loginValido = validaciones.comprobarLoginBlank(login);

		if (Boolean.TRUE.equals(loginValido)) {
			try {
				LoginRol resultado;
				try {
					resultado = loginService.loginUser(login);
					if (CodeMessageErrors.LOGIN_VACIO.name().equals(resultado.getTokenUsuario())) {
						return new RespEntity(RespCode.LOGIN_VACIO, login);
					}
					return new RespEntity(RespCode.LOGIN_OK, resultado);
				} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, login);
				} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
					return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, login);
				}
			} catch (final UsuarioNoEncontradoException userNotFound) {
				return new RespEntity(RespCode.USUARIO_NO_ENCONTRADO, login);
			} catch (final PasswordIncorrectoException passwordIncorrecto) {
				return new RespEntity(RespCode.PASSWORD_INCORRECTO, login);
			}
		}

		return new RespEntity(RespCode.LOGIN_VACIO, login);
	}

	@PostMapping(value = "/recuperarPass")
	@ResponseBody
	public RespEntity recuperarPass(@RequestBody final RecuperarPass recuperarPass) {
		final Boolean infoValida = validaciones.comprobarRecuperarPassBlank(recuperarPass);

		if (Boolean.TRUE.equals(infoValida)) {
			try {
				String resultado;
				resultado = loginService.recuperarPasswdUsuario(recuperarPass.getUsuario(),
						recuperarPass.getEmailUsuario(), recuperarPass.getIdioma());
				if (CodeMessageErrors.EMAIL_VACIO.name().equals(resultado)) {
					return new RespEntity(RespCode.EMAIL_VACIO, recuperarPass);
				}
				if (CodeMessageErrors.USUARIO_VACIO.name().equals(resultado)) {
					return new RespEntity(RespCode.USUARIO_VACIO, recuperarPass);
				}
				return new RespEntity(RespCode.RECUPERAR_PASS_OK, resultado);
			} catch (final UsuarioNoEncontradoException usuarioNoEncontradoException) {
				return new RespEntity(RespCode.USUARIO_NO_ENCONTRADO, recuperarPass);
			} catch (final LogExcepcionesNoGuardadoException logExcepcionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_EXCEPCIONES_NO_GUARDADO, recuperarPass);
			} catch (final LogAccionesNoGuardadoException logAccionesNoGuardadoException) {
				return new RespEntity(RespCode.LOG_ACCIONES_NO_GUARDADO, recuperarPass);
			} catch (final EmailNoEncontradoException emailNoEncontradoException) {
				return new RespEntity(RespCode.EMAIL_NO_ENCONTRADO, recuperarPass);
			} catch (final PersonaNoExisteException personaNoExisteException) {
				return new RespEntity(RespCode.PERSONA_NO_EXISTE, recuperarPass);
			} catch (final MailNoEnviadoException | MessagingException messagingException) {
				return new RespEntity(RespCode.ENVIO_EMAIL_EXCEPTION, recuperarPass);
			}
		}

		return new RespEntity(RespCode.RECUPERAR_PASS_VACIO, recuperarPass);

	}
}
