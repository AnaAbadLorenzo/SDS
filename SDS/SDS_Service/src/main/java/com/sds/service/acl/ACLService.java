package com.sds.service.acl;

import java.util.List;

import com.sds.service.acl.model.Menu;

public interface ACLService {

	Menu funcionesUsuario(String usuario);

	List<String> accionesUsuarioFuncionalidad(String usuario, String funcionalidad);
}
