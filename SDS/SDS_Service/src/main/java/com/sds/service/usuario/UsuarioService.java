package com.sds.service.usuario;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.sds.pojos.Usuario;

public interface UsuarioService {

	void insertar(final Usuario usuario);

	void modificar(final Usuario usuario);

	void eliminar(final Usuario usuario);

	List buscarPorCriteria(final DetachedCriteria criteria);

}
