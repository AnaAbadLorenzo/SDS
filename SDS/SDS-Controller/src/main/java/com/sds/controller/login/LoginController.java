package com.sds.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.service.exception.PasswordIncorrectoException;
import com.sds.service.exception.UsuarioNoEncontradoException;
import com.sds.service.login.LoginService;
import com.sds.service.login.model.Login;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@RestController
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

		if (loginValido) {
			try {
				final String resultado = loginService.loginUser(login);
				if (CodeMessageErrors.LOGIN_VACIO.name().equals(resultado)) {
					return new RespEntity(RespCode.LOGIN_VACIO, login);
				}
				return new RespEntity(RespCode.LOGIN_OK, resultado);
			} catch (final UsuarioNoEncontradoException userNotFound) {
				return new RespEntity(RespCode.USUARIO_NO_ENCONTRADO, login);
			} catch (final PasswordIncorrectoException passwordIncorrecto) {
				return new RespEntity(RespCode.PASSWORD_INCORRECTO, login);
			}
		}

		return new RespEntity(RespCode.LOGIN_VACIO, login);
	}
}
