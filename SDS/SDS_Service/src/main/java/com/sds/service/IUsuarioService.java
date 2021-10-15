package com.sds.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.sds.pojos.Usuario;

public interface IUsuarioService {
	void insertar(Usuario usuario);
	void modificar(Usuario usuario);
	void eliminar(Usuario usuario);
	List buscarCriteria(DetachedCriteria criteria);
	
}
