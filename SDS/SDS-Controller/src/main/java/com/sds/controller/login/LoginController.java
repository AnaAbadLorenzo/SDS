package com.sds.controller.login;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.service.login.LoginService;
import com.sds.service.login.impl.LoginServiceImpl;
import com.sds.service.login.model.Login;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;

import exception.UserNotFound;

@RestController
public class LoginController {

	private final LoginService loginService;
	private final Util util;

	public LoginController() {
		loginService = new LoginServiceImpl();
		util = new Util();
	}

	@PostMapping(value = "/login")
	@ResponseBody
	public RespEntity loginUser(@RequestBody final Login login) {

		final Boolean loginValido = util.comprobarLogin(login);

		if (loginValido) {
			try {
				final String resultado = loginService.loginUser(login);
				if (CodeMessageErrors.LOGIN_BLANK.name().equals(resultado)) {
					return new RespEntity(RespCode.LOGIN_BLANK, login);
				}
				return new RespEntity(RespCode.LOGIN_OK, resultado);
			} catch (final UserNotFound userNotFound) {
				return new RespEntity(RespCode.USER_NOT_FOUND, login);
			}
		}

		return new RespEntity(RespCode.LOGIN_BLANK, login);
	}
}
