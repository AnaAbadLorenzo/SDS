package com.sds.service.login;

import javax.mail.MessagingException;

import com.sds.service.exception.EmailNoEncontradoException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.MailNoEnviadoException;
import com.sds.service.exception.PasswordIncorrectoException;
import com.sds.service.exception.PersonaNoExisteException;
import com.sds.service.exception.UsuarioNoEncontradoException;
import com.sds.service.login.model.Login;
import com.sds.service.login.model.LoginRol;

public interface LoginService {

	LoginRol loginUser(final Login login) throws UsuarioNoEncontradoException, PasswordIncorrectoException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException;

	String recuperarPasswdUsuario(final String usuario, final String emailUsuario, final String idioma)
			throws UsuarioNoEncontradoException, MessagingException, LogExcepcionesNoGuardadoException,
			PersonaNoExisteException, EmailNoEncontradoException, LogAccionesNoGuardadoException,
			MailNoEnviadoException;
}
