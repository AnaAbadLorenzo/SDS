package com.sds.controller.menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.service.acl.ACLService;
import com.sds.service.acl.model.Menu;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {

	@Autowired
	ACLService aclService;

	@GetMapping(value = "/funcionalidadesUsuario")
	@ResponseBody
	public Menu funcionalidadesUsuario(final String usuario) {
		return aclService.funcionesUsuario(usuario);

	}

	@GetMapping(value = "/accionesFuncionalidad")
	@ResponseBody
	public List<String> accionesFuncionalidad(final String usuario, final String funcionalidad) {

		return aclService.accionesUsuarioFuncionalidad(usuario, funcionalidad);

	}

}
