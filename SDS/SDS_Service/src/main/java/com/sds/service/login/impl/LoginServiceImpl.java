package com.sds.service.login.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sds.model.TokenAuth;
import com.sds.security.JWToken;
import com.sds.service.login.LoginService;

@Service(value = "LoginService")
public class LoginServiceImpl implements LoginService {

	AuthenticationManager authManager;
	JWToken jwtToken;

	@Override
	public ResponseEntity<?> loginUser(final String usuario, final String passwdUsuario) {

		final UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(usuario,
				passwdUsuario);

		final Authentication auth = authManager.authenticate(user);

		SecurityContextHolder.getContext().setAuthentication(auth);

		final String token = jwtToken.generateToken(auth);

		return ResponseEntity.ok(new TokenAuth(token));

	}

}
