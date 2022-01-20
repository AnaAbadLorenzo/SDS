package com.sds.controller.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.model.UsuarioEntity;
import com.sds.service.usuario.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@RequestMapping(value = "/listarUsuarios", method = RequestMethod.GET)
	@ResponseBody
	public RespEntity buscarTodos() {
		final List<UsuarioEntity> resultado = usuarioService.buscarTodos();

		return new RespEntity(RespCode.USUARIOS_LISTADOS, resultado);
	}

}
