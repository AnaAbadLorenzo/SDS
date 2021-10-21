package com.sds.service.usuario.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sds.dao.DaoImplementation;
import com.sds.dao.GenericDao;
import com.sds.pojos.Usuario;
import com.sds.service.usuario.UsuarioService;

@Service(value = "UsuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	private static final String USUARIO = "usuario";
	private static final String USERNAME_NOT_FOUND_EXCEPTION = "Invalid username or password.";
	private static final String ROL = "ROLE_";

	private final GenericDao dao;

	public UsuarioServiceImpl() {
		dao = new DaoImplementation();
	}

	@Override
	public void insertar(final Usuario usuario) {
		dao.insertar(usuario);
	}

	@Override
	public void modificar(final Usuario usuario) {
		dao.modificar(usuario);
	}

	@Override
	public void eliminar(final Usuario usuario) {
		dao.eliminar(usuario);
	}

	@Override
	public List buscarPorCriteria(final DetachedCriteria criteria) {
		return dao.buscarPorCriteria(Usuario.class, criteria);
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		final DetachedCriteria criteria = DetachedCriteria.forClass(Usuario.class);
		criteria.add(Restrictions.eq(USUARIO, username));

		final Usuario usuario = (Usuario) dao.buscarPorCriteria(Usuario.class, criteria);
		if (usuario == null) {
			throw new UsernameNotFoundException(USERNAME_NOT_FOUND_EXCEPTION);
		}

		return new org.springframework.security.core.userdetails.User(usuario.getUsuario(), usuario.getPasswdUsuario(),
				getAuth(usuario));
	}

	private Set<SimpleGrantedAuthority> getAuth(final Usuario usuario) {
		final Set<SimpleGrantedAuthority> authorities = new HashSet<>();

		authorities.add(new SimpleGrantedAuthority(ROL + usuario.getRol().getRolName()));

		return authorities;
	}
}
