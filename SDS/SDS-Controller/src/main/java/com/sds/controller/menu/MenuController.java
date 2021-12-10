package com.sds.controller.menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.service.acl.ACLService;
import com.sds.service.acl.model.Menu;

@RestController
public class MenuController {

	@Autowired
	ACLService aclService;

	@GetMapping(value = "/menu/funcionalidadesUsuario")
	@ResponseBody
	public Menu funcionalidadesUsuario(final String usuario) {

		final Menu menu = aclService.funcionesUsuario(usuario);

		return menu;
	}

	@GetMapping(value = "/menu/accionesFuncionalidad")
	@ResponseBody
	public List<String> accionesFuncinalidad(final String usuario, final String funcionalidad) {

		final List<String> acciones = aclService.accionesUsuarioFuncionalidad(usuario, funcionalidad);

		return acciones;
	}

}
