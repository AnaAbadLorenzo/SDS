package com.sds.service.login;

import com.sds.service.exception.PasswordIncorrectoException;
import com.sds.service.exception.UsuarioNoEncontradoException;
import com.sds.service.login.model.Login;

public interface LoginService {

	String loginUser(final Login login) throws UsuarioNoEncontradoException, PasswordIncorrectoException;

}
