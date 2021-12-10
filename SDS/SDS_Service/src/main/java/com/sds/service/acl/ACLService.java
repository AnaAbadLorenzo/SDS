package com.sds.service.acl;

import com.sds.model.FuncionalidadEntity;

import java.util.List;

import com.sds.model.AccionEntity;
import com.sds.service.acl.model.Menu;

public interface ACLService {

	Menu funcionesUsuario(String usuario);
	List<String> accionesUsuarioFuncionalidad (String usuario, String funcionalidad);
}
