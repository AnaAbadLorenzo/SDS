package com.sds.controller.registro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.service.exception.EmpresaYaExisteException;
import com.sds.service.exception.PersonaYaExisteException;
import com.sds.service.exception.UsuarioYaExisteException;
import com.sds.service.registro.RegistroService;
import com.sds.service.registro.model.Registro;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.validaciones.Validaciones;

@RestController
public class RegistroController {

	@Autowired
	RegistroService registroService;

	private final Validaciones validaciones;

	public RegistroController() {
		validaciones = new Validaciones();
	}

	@PostMapping(value = "/registro")
	@ResponseBody
	public RespEntity registerUser(@RequestBody final Registro registro) {

		final Boolean registroValido = validaciones.comprobarRegistroBlank(registro);

		if (registroValido) {
			try {
				final String resultado = registroService.registrar(registro);
				if (CodeMessageErrors.REGISTRO_VACIO.name().equals(resultado)) {
					return new RespEntity(RespCode.REGISTRO_VACIO, registro);
				}
				return new RespEntity(RespCode.REGISTRO_OK, resultado);
			} catch (final UsuarioYaExisteException useralredyExists) {
				return new RespEntity(RespCode.USUARIO_YA_EXISTE, registro);
			} catch (final PersonaYaExisteException personAlreadyExists) {
				return new RespEntity(RespCode.PERSONA_YA_EXISTE, registro);
			} catch (final EmpresaYaExisteException empresaAlreadyExists) {
				return new RespEntity(RespCode.EMPRESA_YA_EXISTE, registro);
			}
		}
		return new RespEntity(RespCode.REGISTRO_VACIO, registro);
	}
}