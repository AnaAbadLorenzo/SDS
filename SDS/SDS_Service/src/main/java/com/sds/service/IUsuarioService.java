package com.sds.service;

import com.sds.pojos.Usuario;

public interface IUsuarioService {
	void insertar(Usuario usuario);
	void modificar(Usuario usuario);
	void eliminar(Usuario usuario);
	
}
