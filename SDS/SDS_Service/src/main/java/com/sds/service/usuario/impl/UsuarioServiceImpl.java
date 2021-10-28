package com.sds.service.usuario.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.sds.service.usuario.UsuarioService;

import dao.DaoImplementation;
import dao.GenericDao;
import pojos.Usuario;

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

}
