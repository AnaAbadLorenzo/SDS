package com.sds.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.sds.dao.DaoImplementation;
import com.sds.pojos.Usuario;
import com.sds.service.IUsuarioService;

@Service(value="IUsuarioService")
public class IUsuarioServiceImpl implements IUsuarioService{

	@Autowired
	DaoImplementation dao;
	
	public void insertar(Usuario usuario) {
		dao.insertar(usuario);
	}
	
	public void modificar(Usuario usuario) {
		dao.modificar(usuario);
	}
	
	public void eliminar(Usuario usuario) {
		dao.eliminar(usuario);
	}
}
