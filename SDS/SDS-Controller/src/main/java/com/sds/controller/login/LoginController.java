package com.sds.controller.login;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sds.service.login.LoginService;
import com.sds.service.login.impl.LoginServiceImpl;

@RestController
public class LoginController {

	private final LoginService loginService;

	public LoginController() {
		loginService = new LoginServiceImpl();
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(final String usuario, final String passwdUsuario) {
		return loginService.loginUser(usuario, passwdUsuario);
	}
}
