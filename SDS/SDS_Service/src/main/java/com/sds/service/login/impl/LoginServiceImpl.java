package com.sds.service.login.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sds.model.UsuarioEntity;
import com.sds.repository.UsuarioRepository;
import com.sds.service.exception.PasswordIncorrectoException;
import com.sds.service.exception.UsuarioNoEncontrado;
import com.sds.service.login.LoginService;
import com.sds.service.login.model.Login;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;

@Service(value = "loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	@Qualifier("usuarioRepository")
	UsuarioRepository usuarioRepository;

	private final GetJWTToken jWTToken;
	private final Util util;

	public LoginServiceImpl() {
		jWTToken = new GetJWTToken();
		util = new Util();
	}

	@Override
	public String loginUser(final Login login) throws UsuarioNoEncontrado, PasswordIncorrectoException {

		String resultado = StringUtils.EMPTY;

		final Boolean loginValido = util.comprobarLogin(login);

		if (loginValido) {
			if (existsUser(login)) {
				resultado = jWTToken.getJWTToken(login.getUsuario());
			}
		} else {
			resultado = CodeMessageErrors.LOGIN_VACIO.name();
		}

		return resultado;
	}

	private boolean existsUser(final Login login) throws UsuarioNoEncontrado, PasswordIncorrectoException {

		final UsuarioEntity usuario = usuarioRepository.findByUsuario(login.getUsuario());

		if (usuario == null) {
			throw new UsuarioNoEncontrado(CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo(),
					CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.USUARIO_NO_ENCONTRADO_EXCEPTION.getCodigo()));
		} else {
			final String pass = login.getPasswdUsuario();

			if (pass.equals(usuario.getPasswdUsuario())) {
				return true;
			} else {
				throw new PasswordIncorrectoException(CodeMessageErrors.PASSWORD_INCORRECTO_EXCEPTION.getCodigo(),
						CodeMessageErrors
								.getTipoNameByCodigo(CodeMessageErrors.PASSWORD_INCORRECTO_EXCEPTION.getCodigo()));
			}
		}
	}

}
