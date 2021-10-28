package com.sds.service.login;

import com.sds.service.login.model.Login;

import exception.PasswordIncorrectoException;
import exception.UserNotFound;

public interface LoginService {

	String loginUser(final Login login) throws UserNotFound, PasswordIncorrectoException;

}
