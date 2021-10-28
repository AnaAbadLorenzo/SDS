package com.sds.service.login.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.sds.service.login.LoginService;
import com.sds.service.login.model.Login;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;

import dao.DaoImplementation;
import dao.GenericDao;
import exception.UserNotFound;
import pojos.Usuario;

@Service(value = "LoginService")
public class LoginServiceImpl implements LoginService {

	private static final String USUARIO = "usuario";

	private final GenericDao dao;
	private final GetJWTToken jWTToken;
	private final Util util;

	public LoginServiceImpl() {
		dao = new DaoImplementation();
		jWTToken = new GetJWTToken();
		util = new Util();
	}

	@Override
	public String loginUser(final Login login) throws UserNotFound {

		String resultado = StringUtils.EMPTY;

		final Boolean loginValido = util.comprobarLogin(login);

		if (loginValido) {
			if (existsUser(login)) {
				resultado = jWTToken.getJWTToken(login.getUsuario());
			}
		} else {
			resultado = CodeMessageErrors.LOGIN_BLANK.name();
		}

		return resultado;
	}

	private boolean existsUser(final Login login) throws UserNotFound {

		final DetachedCriteria crit = DetachedCriteria.forClass(Usuario.class);
		crit.add(Restrictions.like(USUARIO, login.getUsuario()));

		final List<Usuario> usuarios = dao.buscarPorCriteria(Usuario.class, crit);

		if (usuarios.isEmpty()) {
			throw new UserNotFound(CodeMessageErrors.USERNAME_NOT_FOUND_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.USERNAME_NOT_FOUND_EXCEPTION.getCodigo()));
		} else {
			//
		}

		return true;
	}

}
