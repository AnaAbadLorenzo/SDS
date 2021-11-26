package com.sds.controller.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.controller.test.model.RespuestaTestAcciones;
import com.sds.controller.test.model.RespuestaTestAtributos;
import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.service.common.Constantes;
import com.sds.service.test.TestLoginService;
import com.sds.service.test.TestRegistrarService;
import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;

@RestController
public class TestController {

	@Autowired
	TestLoginService testLoginService;
	
	@Autowired 
	TestRegistrarService testRegistrarService;

	@GetMapping(value = "/test/login/atributos")
	@ResponseBody
	public RespEntity TestLoginAtributos() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoUsuario = testLoginService.getPruebasAtributoUsuario();
			final List<DatosPruebaAtributos> pruebaAtributoContrasena = testLoginService.getPruebasAtributoContrasena();
			resultadoPruebasAtributos.addAll(pruebaAtributoUsuario);
			resultadoPruebasAtributos.addAll(pruebaAtributoContrasena);
		} catch (IOException | ParseException e) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_LOGIN_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.LOGIN);
		respuestaTestAtributos.setAccion(Constantes.LOGIN);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_LOGIN_OK, respuestaTestAtributos);
	}

	@GetMapping(value = "/test/login/acciones")
	@ResponseBody
	public RespEntity TestLoginAcciones() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		try {
			datosPruebaAcciones = testLoginService.getPruebasAccionesLogin();
		} catch (IOException | ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_LOGIN_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.LOGIN);
		respuestaTestAcciones.setAccion(Constantes.LOGIN);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_LOGIN_OK, respuestaTestAcciones);
	}
	
	@GetMapping(value = "/test/registrar/atributos")
	@ResponseBody
	public RespEntity TestRegistrarAtributos() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<DatosPruebaAtributos>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoDniPersona = testRegistrarService.getPruebasAtributoDniP();
			final List<DatosPruebaAtributos> pruebaAtributoNombrePersona = testRegistrarService.getPruebasAtributoNombreP();
			resultadoPruebasAtributos.addAll(pruebaAtributoDniPersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoNombrePersona);
		} catch (IOException | ParseException  | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ACCIONES_REGISTRO_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.REGISTRAR);
		respuestaTestAtributos.setAccion(Constantes.REGISTRAR);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_REGISTRO_OK, respuestaTestAtributos);
	}

	
	

}
