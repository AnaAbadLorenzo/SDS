package com.sds.service.login;

import org.springframework.http.ResponseEntity;

public interface LoginService {

	ResponseEntity<?> loginUser(final String usuario, final String passwdUsuario);

}
