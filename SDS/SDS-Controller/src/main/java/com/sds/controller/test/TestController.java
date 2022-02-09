package com.sds.controller.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

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
import com.sds.service.test.TestAccionService;
import com.sds.service.test.TestFuncionalidadService;
import com.sds.service.test.TestLoginService;
import com.sds.service.test.TestPersonaService;
import com.sds.service.test.TestRecuperarPassService;
import com.sds.service.test.TestRegistrarService;
import com.sds.service.test.TestRolService;
import com.sds.service.test.TestUsuarioService;
import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;

@RestController
public class TestController {

	@Autowired
	TestLoginService testLoginService;

	@Autowired
	TestRegistrarService testRegistrarService;

	@Autowired
	TestRolService testRolService;

	@Autowired
	TestAccionService testAccionService;

	@Autowired
	TestFuncionalidadService testFuncionalidadService;

	@Autowired
	TestUsuarioService testUsuarioService;

	@Autowired
	TestPersonaService testPersonaService;

	@Autowired
	TestRecuperarPassService testRecuperarPass;

	@GetMapping(value = "/test/login/atributos")
	@ResponseBody
	public RespEntity TestLoginAtributos() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<DatosPruebaAtributos>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoUsuario = testLoginService.getPruebasAtributoUsuario();
			final List<DatosPruebaAtributos> pruebaAtributoContrasena = testLoginService.getPruebasAtributoContrasena();
			resultadoPruebasAtributos.addAll(pruebaAtributoUsuario);
			resultadoPruebasAtributos.addAll(pruebaAtributoContrasena);
		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_LOGIN_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.LOGIN);
		respuestaTestAtributos.setAccion(Constantes.LOGIN);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_LOGIN_OK, respuestaTestAtributos);
	}

	@GetMapping(value = "/test/recuperarPass/atributos")
	@ResponseBody
	public RespEntity TestRecuperarPassAtributos() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<DatosPruebaAtributos>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoUsuario = testRecuperarPass.getPruebasAtributoUsuario();
			final List<DatosPruebaAtributos> pruebaAtributoEmail = testRecuperarPass.getPruebasAtributoEmail();
			resultadoPruebasAtributos.addAll(pruebaAtributoUsuario);
			resultadoPruebasAtributos.addAll(pruebaAtributoEmail);
		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_RECUPERARPASS_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.RECUPERAR_PASS);
		respuestaTestAtributos.setAccion(Constantes.RECUPERAR_PASS);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_RECUPERARPASS_OK, respuestaTestAtributos);
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

	@GetMapping(value = "/test/recuperarPass/acciones")
	@ResponseBody
	public RespEntity TestRecuperarPassAcciones() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		try {
			final List<DatosPruebaAcciones> pruebaAtributoAcciones = testRecuperarPass
					.getPruebasAccionesRecuperarPass();
			datosPruebaAcciones.addAll(pruebaAtributoAcciones);
		} catch (IOException | ParseException | java.text.ParseException | MessagingException e) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_RECUPERARPASS_KO, StringUtils.EMPTY);
		}
		respuestaTestAcciones.setFuncionalidad(Constantes.LOGIN);
		respuestaTestAcciones.setAccion(Constantes.RECUPERAR_PASS);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_RECUPERARPASS_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/test/registrar/atributos")
	@ResponseBody
	public RespEntity TestRegistrarAtributos() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<DatosPruebaAtributos>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoDniPersona = testRegistrarService.getPruebasAtributoDniP();
			final List<DatosPruebaAtributos> pruebaAtributoNombrePersona = testRegistrarService
					.getPruebasAtributoNombreP();
			final List<DatosPruebaAtributos> pruebaAtributoApellidosPersona = testRegistrarService
					.getPruebasAtributoApellidosP();
			final List<DatosPruebaAtributos> pruebaAtributoFechaNacPersona = testRegistrarService
					.getPruebasAtributoFechaNacP();
			final List<DatosPruebaAtributos> pruebaAtributoDireccionPersona = testRegistrarService
					.getPruebasAtributoDireccionP();
			final List<DatosPruebaAtributos> pruebaAtributoEmailPersona = testRegistrarService
					.getPruebasAtributoEmailP();
			final List<DatosPruebaAtributos> pruebaAtributoTelefonoPersona = testRegistrarService
					.getPruebasAtributoTelefonoP();
			final List<DatosPruebaAtributos> pruebaAtributoUsuario = testRegistrarService.getPruebasAtributoUsuario();
			final List<DatosPruebaAtributos> pruebaAtributoContrasena = testRegistrarService
					.getPruebasAtributoContrasena();
			final List<DatosPruebaAtributos> pruebaAtributoCifEmpresa = testRegistrarService
					.getPruebasAtributoCifEmpresa();
			final List<DatosPruebaAtributos> pruebaAtributoNombreEmpresa = testRegistrarService
					.getPruebasAtributoNombreEmpresa();
			final List<DatosPruebaAtributos> pruebaAtributoDireccionEmpresa = testRegistrarService
					.getPruebasAtributoDireccionEmpresa();
			final List<DatosPruebaAtributos> pruebaAtributoTelefonoEmpresa = testRegistrarService
					.getPruebasAtributoTelefonoEmpresa();

			resultadoPruebasAtributos.addAll(pruebaAtributoDniPersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoNombrePersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoApellidosPersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoFechaNacPersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoDireccionPersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoEmailPersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoTelefonoPersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoUsuario);
			resultadoPruebasAtributos.addAll(pruebaAtributoContrasena);
			resultadoPruebasAtributos.addAll(pruebaAtributoCifEmpresa);
			resultadoPruebasAtributos.addAll(pruebaAtributoNombreEmpresa);
			resultadoPruebasAtributos.addAll(pruebaAtributoDireccionEmpresa);
			resultadoPruebasAtributos.addAll(pruebaAtributoTelefonoEmpresa);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_REGISTRO_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.REGISTRAR);
		respuestaTestAtributos.setAccion(Constantes.REGISTRAR);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_REGISTRO_OK, respuestaTestAtributos);
	}

	@GetMapping(value = "/test/registrar/acciones")
	@ResponseBody
	public RespEntity TestRegistrarAcciones() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<DatosPruebaAcciones>();

		try {
			datosPruebaAcciones = testRegistrarService.getPruebasAccionesRegistro();
		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_REGISTRO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.REGISTRAR);
		respuestaTestAcciones.setAccion(Constantes.REGISTRAR);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_REGISTRO_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/test/rol/atributos/guardar")
	@ResponseBody
	public RespEntity TestRolAtributosAccionGuardar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<DatosPruebaAtributos>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoRolName = testRolService.getPruebasAtributoRolName();
			final List<DatosPruebaAtributos> pruebaAtributoRolDescription = testRolService
					.getPruebasAtributoRolDescription();

			resultadoPruebasAtributos.addAll(pruebaAtributoRolName);
			resultadoPruebasAtributos.addAll(pruebaAtributoRolDescription);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_ROL_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_ROLES);
		respuestaTestAtributos.setAccion(Constantes.AÑADIR);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_ROL_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/test/rol/atributos/buscar")
	@ResponseBody
	public RespEntity TestRolAtributosAccionBuscar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoRolName = testRolService.getPruebasAtributoRolNameBuscar();
			final List<DatosPruebaAtributos> pruebaAtributoRolDescription = testRolService
					.getPruebasAtributoRolDescriptionBuscar();

			resultadoPruebasAtributos.addAll(pruebaAtributoRolName);
			resultadoPruebasAtributos.addAll(pruebaAtributoRolDescription);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_ROL_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_ROLES);
		respuestaTestAtributos.setAccion(Constantes.BUSCAR);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_ROL_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/test/rol/atributos/modificar")
	@ResponseBody
	public RespEntity TestRolAtributosAccionModificar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<DatosPruebaAtributos>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoRolName = testRolService.getPruebasAtributoRolName();
			final List<DatosPruebaAtributos> pruebaAtributoRolDescription = testRolService
					.getPruebasAtributoRolDescription();

			resultadoPruebasAtributos.addAll(pruebaAtributoRolName);
			resultadoPruebasAtributos.addAll(pruebaAtributoRolDescription);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_ROL_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_ROLES);
		respuestaTestAtributos.setAccion(Constantes.MODIFICAR);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_ROL_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/test/accion/atributos/guardar")
	@ResponseBody
	public RespEntity TestAccionAtributosAccionGuardar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoAccionName = testAccionService
					.getPruebasAtributoAccionName();
			final List<DatosPruebaAtributos> pruebaAtributosAccionDescription = testAccionService
					.getPruebasAtributoAccionDescription();

			resultadoPruebasAtributos.addAll(pruebaAtributoAccionName);
			resultadoPruebasAtributos.addAll(pruebaAtributosAccionDescription);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_ACCION_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_ACCIONES);
		respuestaTestAtributos.setAccion(Constantes.AÑADIR);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_ROL_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/test/accion/atributos/buscar")
	@ResponseBody
	public RespEntity TestAccionAtributosAccionBuscar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoAccionName = testAccionService
					.getPruebasAtributoAccionNameBuscar();
			final List<DatosPruebaAtributos> pruebaAtributoAccionDescription = testAccionService
					.getPruebasAtributoAccionDescriptionBuscar();

			resultadoPruebasAtributos.addAll(pruebaAtributoAccionName);
			resultadoPruebasAtributos.addAll(pruebaAtributoAccionDescription);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_ACCION_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_ACCIONES);
		respuestaTestAtributos.setAccion(Constantes.BUSCAR);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_ACCION_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/test/accion/atributos/modificar")
	@ResponseBody
	public RespEntity TestAccionAtributosAccionModificar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoAccionName = testAccionService
					.getPruebasAtributoAccionName();
			final List<DatosPruebaAtributos> pruebaAtributosAccionDescription = testAccionService
					.getPruebasAtributoAccionDescription();

			resultadoPruebasAtributos.addAll(pruebaAtributoAccionName);
			resultadoPruebasAtributos.addAll(pruebaAtributosAccionDescription);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_ACCION_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_ACCIONES);
		respuestaTestAtributos.setAccion(Constantes.MODIFICAR);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_ROL_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/test/funcionalidad/atributos/modificar")
	@ResponseBody
	public RespEntity TestFuncionalidadAtributosAccionModificar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoFuncionalidadName = testFuncionalidadService
					.getPruebasAtributoFuncionalidadName();
			final List<DatosPruebaAtributos> pruebaAtributosFuncionalidadDescription = testFuncionalidadService
					.getPruebasAtributoFuncionalidadDescription();

			resultadoPruebasAtributos.addAll(pruebaAtributoFuncionalidadName);
			resultadoPruebasAtributos.addAll(pruebaAtributosFuncionalidadDescription);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_FUNCIONALIDAD_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_FUNCIONALIDADES);
		respuestaTestAtributos.setAccion(Constantes.MODIFICAR);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_FUNCIONALIDAD_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/test/funcionalidad/atributos/guardar")
	@ResponseBody
	public RespEntity TestFuncionalidadAtributosAccionGuardar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoFuncionalidadName = testFuncionalidadService
					.getPruebasAtributoFuncionalidadName();
			final List<DatosPruebaAtributos> pruebaAtributosFuncionalidadDescription = testFuncionalidadService
					.getPruebasAtributoFuncionalidadDescription();

			resultadoPruebasAtributos.addAll(pruebaAtributoFuncionalidadName);
			resultadoPruebasAtributos.addAll(pruebaAtributosFuncionalidadDescription);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_FUNCIONALIDAD_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_FUNCIONALIDADES);
		respuestaTestAtributos.setAccion(Constantes.AÑADIR);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_FUNCIONALIDAD_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/test/funcionalidad/atributos/buscar")
	@ResponseBody
	public RespEntity TestFuncionalidadAtributosAccionBuscar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoRolName = testFuncionalidadService
					.getPruebasAtributoFuncionalidadNameBuscar();

			resultadoPruebasAtributos.addAll(pruebaAtributoRolName);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_FUNCIONALIDAD_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_FUNCIONALIDADES);
		respuestaTestAtributos.setAccion(Constantes.BUSCAR);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_FUNCIONALIDAD_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/test/usuario/atributos/modificarRol")
	@ResponseBody
	public RespEntity TestUsuarioAtributosAccionModificar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoDniUsuario = testUsuarioService
					.getPruebasAtributoUsuarioDniUsuario();
			final List<DatosPruebaAtributos> pruebaAtributoNombreUsuario = testUsuarioService
					.getPruebasAtributoUsuarioNombreUsuario();
			final List<DatosPruebaAtributos> pruebaAtributoPasswdUsuario = testUsuarioService
					.getPruebasAtributoUsuarioPasswdUsuario();
			final List<DatosPruebaAtributos> pruebaAtributoRolName = testRolService.getPruebasAtributoRolName();
			final List<DatosPruebaAtributos> pruebaAtributoRolDescription = testRolService
					.getPruebasAtributoRolDescription();

			resultadoPruebasAtributos.addAll(pruebaAtributoDniUsuario);
			resultadoPruebasAtributos.addAll(pruebaAtributoNombreUsuario);
			resultadoPruebasAtributos.addAll(pruebaAtributoPasswdUsuario);
			resultadoPruebasAtributos.addAll(pruebaAtributoRolName);
			resultadoPruebasAtributos.addAll(pruebaAtributoRolDescription);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_USUARIO_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_USUARIOS);
		respuestaTestAtributos.setAccion(Constantes.ACCION_AÑADIR_USUARIO);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_USUARIO_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/test/usuario/atributos/buscar")
	@ResponseBody
	public RespEntity TestUsuarioAtributosAccionBuscar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoDniUsuario = testUsuarioService
					.getPruebasAtributoUsuarioDniUsuarioBuscar();
			final List<DatosPruebaAtributos> pruebaAtributoNombreUsuario = testUsuarioService
					.getPruebasAtributoUsuarioNombreUsuarioBuscar();

			resultadoPruebasAtributos.addAll(pruebaAtributoDniUsuario);
			resultadoPruebasAtributos.addAll(pruebaAtributoNombreUsuario);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_USUARIO_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_USUARIOS);
		respuestaTestAtributos.setAccion(Constantes.ACCION_BUSCAR_USUARIO);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_USUARIO_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/test/persona/atributos/buscar")
	@ResponseBody
	public RespEntity TestPersonaAtributosAccionBuscar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoDniPersona = testPersonaService
					.getPruebasAtributoDniPBuscar();

			final List<DatosPruebaAtributos> pruebaAtributoNombrePersona = testPersonaService
					.getPruebasAtributoNombrePBuscar();
			final List<DatosPruebaAtributos> pruebaAtributoApellidosPersona = testPersonaService
					.getPruebasAtributoApellidosPBuscar();
			final List<DatosPruebaAtributos> pruebaAtributoFechaNacPersona = testPersonaService
					.getPruebasAtributoFechaNacPBuscar();
			final List<DatosPruebaAtributos> pruebaAtributoDireccionPersona = testPersonaService
					.getPruebasAtributoDireccionPBuscar();
			final List<DatosPruebaAtributos> pruebaAtributoEmailPersona = testPersonaService
					.getPruebasAtributoEmailPBuscar();
			final List<DatosPruebaAtributos> pruebaAtributoTelefonoPersona = testPersonaService
					.getPruebasAtributoTelefonoPBuscar();

			resultadoPruebasAtributos.addAll(pruebaAtributoDniPersona);

			resultadoPruebasAtributos.addAll(pruebaAtributoNombrePersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoApellidosPersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoFechaNacPersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoDireccionPersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoEmailPersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoTelefonoPersona);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_PERSONA_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_PERSONAS);
		respuestaTestAtributos.setAccion(Constantes.ACCION_BUSCAR_PERSONA);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_PERSONA_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/test/persona/atributos/anadir")
	@ResponseBody
	public RespEntity TestPersonaAtributosAccionAnadir() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoDniPersona = testPersonaService.getPruebasAtributoDniP();
			final List<DatosPruebaAtributos> pruebaAtributoNombrePersona = testPersonaService
					.getPruebasAtributoNombreP();
			final List<DatosPruebaAtributos> pruebaAtributoApellidosPersona = testPersonaService
					.getPruebasAtributoApellidosP();
			final List<DatosPruebaAtributos> pruebaAtributoFechaNacPersona = testPersonaService
					.getPruebasAtributoFechaNacP();
			final List<DatosPruebaAtributos> pruebaAtributoDireccionPersona = testPersonaService
					.getPruebasAtributoDireccionP();
			final List<DatosPruebaAtributos> pruebaAtributoEmailPersona = testPersonaService.getPruebasAtributoEmailP();
			final List<DatosPruebaAtributos> pruebaAtributoTelefonoPersona = testPersonaService
					.getPruebasAtributoTelefonoP();
			final List<DatosPruebaAtributos> pruebaAtributoUsuario = testPersonaService.getPruebasAtributoUsuario();
			final List<DatosPruebaAtributos> pruebaAtributoPasswdUsuario = testPersonaService
					.getPruebasAtributoContrasena();

			resultadoPruebasAtributos.addAll(pruebaAtributoDniPersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoNombrePersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoApellidosPersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoFechaNacPersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoDireccionPersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoEmailPersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoTelefonoPersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoUsuario);
			resultadoPruebasAtributos.addAll(pruebaAtributoPasswdUsuario);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_PERSONA_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_PERSONAS);
		respuestaTestAtributos.setAccion(Constantes.ACCION_AÑADIR_PERSONA);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_PERSONA_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/test/persona/atributos/modificar")
	@ResponseBody
	public RespEntity TestPersonaAtributosAccionModificar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {

			final List<DatosPruebaAtributos> pruebaAtributoNombrePersona = testPersonaService
					.getPruebasAtributoNombreP();
			final List<DatosPruebaAtributos> pruebaAtributoApellidosPersona = testPersonaService
					.getPruebasAtributoApellidosP();
			final List<DatosPruebaAtributos> pruebaAtributoFechaNacPersona = testPersonaService
					.getPruebasAtributoFechaNacP();
			final List<DatosPruebaAtributos> pruebaAtributoDireccionPersona = testPersonaService
					.getPruebasAtributoDireccionP();
			final List<DatosPruebaAtributos> pruebaAtributoEmailPersona = testPersonaService.getPruebasAtributoEmailP();
			final List<DatosPruebaAtributos> pruebaAtributoTelefonoPersona = testPersonaService
					.getPruebasAtributoTelefonoP();

			resultadoPruebasAtributos.addAll(pruebaAtributoNombrePersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoApellidosPersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoFechaNacPersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoDireccionPersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoEmailPersona);
			resultadoPruebasAtributos.addAll(pruebaAtributoTelefonoPersona);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_PERSONA_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_PERSONAS);
		respuestaTestAtributos.setAccion(Constantes.ACCION_AÑADIR_PERSONA);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_PERSONA_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/test/accion/accion/guardar")
	@ResponseBody
	public RespEntity TestAccionAccionGuardar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		try {
			datosPruebaAcciones = testAccionService.getPruebasAccionesAccionGuardar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_ACCION_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.ACCION_AÑADIR_ACCION);
		respuestaTestAcciones.setAccion(Constantes.ACCION_AÑADIR_ACCION);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_ACCION_OK, respuestaTestAcciones);

	}

	@GetMapping(value = "/test/accion/accion/modificar")
	@ResponseBody
	public RespEntity TestAccionAccionModificar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		try {
			datosPruebaAcciones = testAccionService.getPruebasAccionesAccionModificar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_ACCION_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.ACCION_MODIFICAR_ACCION);
		respuestaTestAcciones.setAccion(Constantes.ACCION_MODIFICAR_ACCION);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_ACCION_OK, respuestaTestAcciones);

	}

	@GetMapping(value = "/test/accion/accion/eliminar")
	@ResponseBody
	public RespEntity TestAccionAccionEliminar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		try {
			datosPruebaAcciones = testAccionService.getPruebasAccionesAccionEliminar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_ACCION_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.ELIMINAR_ACCION);
		respuestaTestAcciones.setAccion(Constantes.ELIMINAR_ACCION);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_ACCION_OK, respuestaTestAcciones);

	}

	@GetMapping(value = "/test/accion/accion/buscar")
	@ResponseBody
	public RespEntity TestAccionAccionBuscar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		try {
			datosPruebaAcciones = testAccionService.getPruebasAccionesAccionBuscar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_ACCION_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.BUSCAR_ACCION);
		respuestaTestAcciones.setAccion(Constantes.BUSCAR_ACCION);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_ACCION_OK, respuestaTestAcciones);

	}

	@GetMapping(value = "/test/rol/accion/guardar")
	@ResponseBody
	public RespEntity TestRolAccionGuardar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		try {
			datosPruebaAcciones = testRolService.getPruebasAccionesRolGuardar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_ROL_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.ACCION_AÑADIR_ROL);
		respuestaTestAcciones.setAccion(Constantes.ACCION_AÑADIR_ROL);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_ROL_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/test/rol/accion/eliminar")
	@ResponseBody
	public RespEntity TestRolAccionEliminar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		try {
			datosPruebaAcciones = testRolService.getPruebasAccionesRolEliminar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_ROL_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.ACCION_ELIMINAR_ROL);
		respuestaTestAcciones.setAccion(Constantes.ACCION_ELIMINAR_ROL);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_ROL_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/test/rol/accion/modificar")
	@ResponseBody
	public RespEntity TestRolAccionModificar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		try {
			datosPruebaAcciones = testRolService.getPruebasAccionesRolModificar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_ROL_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.ACCION_MODIFICAR_ROL);
		respuestaTestAcciones.setAccion(Constantes.ACCION_MODIFICAR_ROL);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_ROL_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/test/rol/accion/buscar")
	@ResponseBody
	public RespEntity TestRolAccionBuscar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		try {
			datosPruebaAcciones = testRolService.getPruebasAccionesRolBuscar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_ROL_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.ACCION_BUSCAR_ROL);
		respuestaTestAcciones.setAccion(Constantes.ACCION_BUSCAR_ROL);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_ROL_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/test/funcionalidad/accion/buscar")
	@ResponseBody
	public RespEntity TestFuncionalidadAccionBuscar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		try {
			datosPruebaAcciones = testFuncionalidadService.getPruebasAccionesFuncionalidadBuscar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_FUNCIONALIDAD_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.ACCION_BUSCAR_FUNCIONALIDAD);
		respuestaTestAcciones.setAccion(Constantes.ACCION_BUSCAR_FUNCIONALIDAD);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_FUNCIONALIDAD_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/test/funcionalidad/accion/guardar")
	@ResponseBody
	public RespEntity TestFuncionalidadAccionGuardar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		try {
			datosPruebaAcciones = testFuncionalidadService.getPruebasAccionesFuncionalidadGuardar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_FUNCIONALIDAD_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.ACCION_AÑADIR_FUNCIONALIDAD);
		respuestaTestAcciones.setAccion(Constantes.ACCION_AÑADIR_FUNCIONALIDAD);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_FUNCIONALIDAD_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/test/funcionalidad/accion/modificar")
	@ResponseBody
	public RespEntity TestFuncionalidadAccionModificar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		try {
			datosPruebaAcciones = testFuncionalidadService.getPruebasAccionesFuncionalidadModificar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_FUNCIONALIDAD_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.ACCION_MODIFICAR_FUNCIONALIDAD);
		respuestaTestAcciones.setAccion(Constantes.ACCION_MODIFICAR_FUNCIONALIDAD);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_FUNCIONALIDAD_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/test/funcionalidad/accion/eliminar")
	@ResponseBody
	public RespEntity TestFuncionalidadAccionEliminar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		try {
			datosPruebaAcciones = testFuncionalidadService.getPruebasAccionesFuncionalidadEliminar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_FUNCIONALIDAD_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.ACCION_ELIMINAR_FUNCIONALIDAD);
		respuestaTestAcciones.setAccion(Constantes.ACCION_ELIMINAR_FUNCIONALIDAD);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_FUNCIONALIDAD_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/test/usuario/accion/buscar")
	@ResponseBody
	public RespEntity TestUsuarioAccionBuscar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		try {
			datosPruebaAcciones = testUsuarioService.getPruebasAccionesUsuarioBuscar();

		} catch (IOException | ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_USUARIO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.ACCION_BUSCAR_USUARIO);
		respuestaTestAcciones.setAccion(Constantes.ACCION_BUSCAR_USUARIO);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_USUARIO_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/test/usuario/accion/eliminar")
	@ResponseBody
	public RespEntity TestUsuarioAccionEliminar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		try {
			datosPruebaAcciones = testUsuarioService.getPruebasAccionesUsuarioEliminar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_USUARIO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.ACCION_ELIMINAR_USUARIO);
		respuestaTestAcciones.setAccion(Constantes.ACCION_ELIMINAR_USUARIO);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_USUARIO_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/test/usuario/accion/modificarRolUsuario")
	@ResponseBody
	public RespEntity TestUsuarioAccionModificarRolUsuario() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		try {
			datosPruebaAcciones = testUsuarioService.getPruebasAccionesUsuarioModificarRol();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_USUARIO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.ACCION_MODIFICAR_ROL_USUARIO);
		respuestaTestAcciones.setAccion(Constantes.ACCION_MODIFICAR_ROL_USUARIO);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_USUARIO_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/test/usuario/accion/modificarPasswdUsuario")
	@ResponseBody
	public RespEntity TestUsuarioAccionModificarPasswdUsuario() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		try {
			datosPruebaAcciones = testUsuarioService.getPruebasAccionesUsuarioCambiarContraseña();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_USUARIO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.ACCION_MODIFICAR_PASSWD_USUARIO);
		respuestaTestAcciones.setAccion(Constantes.ACCION_MODIFICAR_PASSWD_USUARIO);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_USUARIO_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/test/persona/accion/buscar")
	@ResponseBody
	public RespEntity TestPersonaAccionBuscar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		try {
			datosPruebaAcciones = testPersonaService.getPruebasAccionesPersonaBuscar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_PERSONA_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.ACCION_BUSCAR_PERSONA);
		respuestaTestAcciones.setAccion(Constantes.ACCION_BUSCAR_PERSONA);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_PERSONA_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/test/persona/accion/guardar")
	@ResponseBody
	public RespEntity TestPersonaAccionGuardar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		try {
			datosPruebaAcciones = testPersonaService.getPruebasAccionesPersonaGuardar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_PERSONA_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.ACCION_AÑADIR_PERSONA);
		respuestaTestAcciones.setAccion(Constantes.ACCION_AÑADIR_PERSONA);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_PERSONA_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/test/persona/accion/eliminar")
	@ResponseBody
	public RespEntity TestPersonaAccionEliminar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		try {
			datosPruebaAcciones = testPersonaService.getPruebasAccionesPersonaEliminar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_PERSONA_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.ACCION_ELIMINAR_PERSONA);
		respuestaTestAcciones.setAccion(Constantes.ACCION_ELIMINAR_PERSONA);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_PERSONA_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/test/persona/accion/modificar")
	@ResponseBody
	public RespEntity TestPersonaAccionModificar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList();

		try {
			datosPruebaAcciones = testPersonaService.getPruebasAccionesPersonaModificar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_PERSONA_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.ACCION_AÑADIR_PERSONA);
		respuestaTestAcciones.setAccion(Constantes.ACCION_AÑADIR_PERSONA);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_PERSONA_OK, respuestaTestAcciones);
	}
}
