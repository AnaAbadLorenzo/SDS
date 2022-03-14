package com.sds.controller.menu;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.service.acl.ACLService;
import com.sds.service.acl.model.Menu;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {

	@Autowired
	ACLService aclService;

	@GetMapping(value = "/funcionalidadesUsuario")
	@ResponseBody
	public RespEntity funcionalidadesUsuario(@RequestBody final String usuario) {
		Menu menu = new Menu();
		menu = aclService.funcionesUsuario(usuario);

		return new RespEntity(RespCode.MENU_USUARIO_OK, menu);

	}

	@GetMapping(value = "/accionesFuncionalidad")
	@ResponseBody
	public List<String> accionesFuncionalidad(final String usuario, final String funcionalidad) {

		return aclService.accionesUsuarioFuncionalidad(usuario, funcionalidad);

	}

}
