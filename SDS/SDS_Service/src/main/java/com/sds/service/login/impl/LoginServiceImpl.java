package com.sds.service.login.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.sds.dao.DaoImplementation;
import com.sds.dao.GenericDao;
import com.sds.exception.UserNotFound;
import com.sds.model.TokenAuth;
import com.sds.pojos.Usuario;
import com.sds.security.JWToken;
import com.sds.service.login.LoginService;
import com.sds.service.login.model.Login;

@Service(value = "LoginService")
public class LoginServiceImpl implements LoginService {
	public static final String USUARIO = "usuario";
	public static final String PASSWD = "passwdUsuario";
	public static final String USERNAME_NOT_FOUND_EXCEPTION = "El usuario no existe";

	private final GenericDao dao;

	public LoginServiceImpl() {
		dao = new DaoImplementation();
	}

	AuthenticationManager authManager;
	JWToken jwtToken;

	@Override
	public ResponseEntity<?> loginUser(final Login login) {
		ResponseEntity<?> response = null;

		try {

			if (existsUser(login)) {
				response = getLogUser(login);
			}
		} catch (final UserNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public boolean existsUser(final Login login) throws UserNotFound {
		boolean toret = false;
		final DetachedCriteria criteria = DetachedCriteria.forClass(Usuario.class);
		criteria.add(Restrictions.eq(USUARIO, login.getUsuario()));
		criteria.add(Restrictions.eq(PASSWD, login.getPasswdUsuario()));

		final Usuario usuario = (Usuario) dao.buscarPorCriteria(Login.class, criteria);

		if (usuario == null) {
			throw new UserNotFound(USERNAME_NOT_FOUND_EXCEPTION);
		} else {
			toret = true;
		}

		return toret;
	}

	private ResponseEntity<?> getLogUser(final Login login) {
		final UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(login.getUsuario(),
				login.getPasswdUsuario());

		final Authentication auth = authManager.authenticate(user);

		SecurityContextHolder.getContext().setAuthentication(auth);

		final String token = jwtToken.generateToken(auth);

		return ResponseEntity.ok(new TokenAuth(token));

	}

}
