package com.sds.service;

import org.springframework.http.ResponseEntity;

public interface IULoginService {
	
	ResponseEntity<?> loginUser (String usuario, String passwdUsuario);

}
