package com.sds.service.login;

import org.springframework.http.ResponseEntity;

import com.sds.exception.UserNotFound;
import com.sds.service.login.model.Login;

public interface LoginService {

	ResponseEntity<?> loginUser(final Login login);

	boolean existsUser(final Login login) throws UserNotFound;

}
