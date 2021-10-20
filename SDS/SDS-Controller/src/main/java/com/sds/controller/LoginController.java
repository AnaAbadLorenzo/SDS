package com.sds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sds.service.IULoginService;

@RestController
public class LoginController {
	
	@Autowired
	private IULoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(String usuario, String passwdUsuario){
		return loginService.loginUser(usuario, passwdUsuario);
	}


}
