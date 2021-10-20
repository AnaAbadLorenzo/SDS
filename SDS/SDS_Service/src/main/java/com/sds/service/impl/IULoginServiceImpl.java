package com.sds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sds.model.TokenAuth;
import com.sds.security.JWToken;
import com.sds.service.IULoginService;

@Service(value="IULoginService")
public class IULoginServiceImpl implements IULoginService {

	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JWToken jwtToken;
	
	public ResponseEntity<?> loginUser(String usuario, String passwdUsuario){
		UsernamePasswordAuthenticationToken user = new 
				UsernamePasswordAuthenticationToken(usuario, passwdUsuario);
		
		Authentication auth = authManager.authenticate(user);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		String token = jwtToken.generateToken(auth);
		
		
		return ResponseEntity.ok(new TokenAuth(token));
		
	}
		
}
