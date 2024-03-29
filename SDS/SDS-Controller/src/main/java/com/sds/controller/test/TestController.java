package com.sds.controller.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sds.controller.test.model.RespuestaTestAcciones;
import com.sds.controller.test.model.RespuestaTestAtributos;
import com.sds.model.RespCode;
import com.sds.model.RespEntity;
import com.sds.service.common.Constantes;
import com.sds.service.test.TestAccionService;
import com.sds.service.test.TestEmpresaService;
import com.sds.service.test.TestFuncionalidadService;
import com.sds.service.test.TestLoginService;
import com.sds.service.test.TestNoticiaService;
import com.sds.service.test.TestObjetivoService;
import com.sds.service.test.TestPersonaService;
import com.sds.service.test.TestPlanService;
import com.sds.service.test.TestProcedimientoService;
import com.sds.service.test.TestProcesoService;
import com.sds.service.test.TestRecuperarPassService;
import com.sds.service.test.TestRegistrarService;
import com.sds.service.test.TestRespuestaPosibleService;
import com.sds.service.test.TestRolService;
import com.sds.service.test.TestUsuarioService;
import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;

@RestController
@RequestMapping(value = "/test")
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

	@Autowired
	TestEmpresaService testEmpresaService;

	@Autowired
	TestNoticiaService testNoticiaService;

	@Autowired
	TestObjetivoService testObjetivoService;

	@Autowired
	TestRespuestaPosibleService testRespuestaPosibleService;

	@Autowired
	TestPlanService testPlanService;

	@Autowired
	TestProcedimientoService testProcedimientoService;

	@Autowired
	TestProcesoService testProcesoService;

	@GetMapping(value = "/login/atributos")
	@ResponseBody
	public RespEntity TestLoginAtributos() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

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

	@GetMapping(value = "/recuperarPass/atributos")
	@ResponseBody
	public RespEntity TestRecuperarPassAtributos() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoUsuario = testRecuperarPass.getPruebasAtributoUsuario();
			final List<DatosPruebaAtributos> pruebaAtributoEmail = testRecuperarPass.getPruebasAtributoEmail();
			resultadoPruebasAtributos.addAll(pruebaAtributoUsuario);
			resultadoPruebasAtributos.addAll(pruebaAtributoEmail);
		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_RECUPERARPASS_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.LOGIN);
		respuestaTestAtributos.setAccion(Constantes.RECUPERAR_PASS);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_RECUPERARPASS_OK, respuestaTestAtributos);
	}

	@GetMapping(value = "/login/acciones")
	@ResponseBody
	public RespEntity TestLoginAcciones() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

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

	@GetMapping(value = "/recuperarPass/acciones")
	@ResponseBody
	public RespEntity TestRecuperarPassAcciones() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			final List<DatosPruebaAcciones> pruebaAtributoAcciones = testRecuperarPass
					.getPruebasAccionesRecuperarPass();
			datosPruebaAcciones.addAll(pruebaAtributoAcciones);
		} catch (IOException | ParseException | java.text.ParseException | MessagingException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_RECUPERARPASS_KO, StringUtils.EMPTY);
		}
		respuestaTestAcciones.setFuncionalidad(Constantes.LOGIN);
		respuestaTestAcciones.setAccion(Constantes.RECUPERAR_PASS);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_RECUPERARPASS_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/registrar/atributos")
	@ResponseBody
	public RespEntity TestRegistrarAtributos() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

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

	@GetMapping(value = "/registrar/acciones")
	@ResponseBody
	public RespEntity TestRegistrarAcciones() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

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

	@GetMapping(value = "/rol/atributos/guardar")
	@ResponseBody
	public RespEntity TestRolAtributosAccionGuardar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

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
		respuestaTestAtributos.setAccion(Constantes.ACCION_AÑADIR_ROL);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_ROL_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/rol/atributos/buscar")
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
		respuestaTestAtributos.setAccion(Constantes.ACCION_BUSCAR_ROL);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_ROL_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/rol/atributos/modificar")
	@ResponseBody
	public RespEntity TestRolAtributosAccionModificar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

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
		respuestaTestAtributos.setAccion(Constantes.ACCION_MODIFICAR_ROL);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_ROL_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/accion/atributos/guardar")
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
		respuestaTestAtributos.setAccion(Constantes.ACCION_AÑADIR_ACCION);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_ACCION_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/accion/atributos/buscar")
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
		respuestaTestAtributos.setAccion(Constantes.ACCION_BUSCAR_ACCION);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_ACCION_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/accion/atributos/modificar")
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
		respuestaTestAtributos.setAccion(Constantes.ACCION_MODIFICAR_ACCION);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_ACCION_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/funcionalidad/atributos/modificar")
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
		respuestaTestAtributos.setAccion(Constantes.ACCION_MODIFICAR_FUNCIONALIDAD);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_FUNCIONALIDAD_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/funcionalidad/atributos/guardar")
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
		respuestaTestAtributos.setAccion(Constantes.ACCION_AÑADIR_FUNCIONALIDAD);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_FUNCIONALIDAD_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/funcionalidad/atributos/buscar")
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
		respuestaTestAtributos.setAccion(Constantes.ACCION_BUSCAR_FUNCIONALIDAD);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_FUNCIONALIDAD_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/usuario/atributos/modificarRol")
	@ResponseBody
	public RespEntity TestUsuarioAtributosAccionModificarRol() {

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
		respuestaTestAtributos.setAccion(Constantes.ACCION_MODIFICAR_ROL_USUARIO);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_USUARIO_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/usuario/atributos/buscar")
	@ResponseBody
	public RespEntity TestUsuarioAtributosAccionBuscar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoNombreUsuario = testUsuarioService
					.getPruebasAtributoUsuarioNombreUsuarioBuscar();

			resultadoPruebasAtributos.addAll(pruebaAtributoNombreUsuario);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_USUARIO_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_USUARIOS);
		respuestaTestAtributos.setAccion(Constantes.ACCION_BUSCAR_USUARIO);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_USUARIO_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/persona/atributos/buscar")
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

	@GetMapping(value = "/persona/atributos/guardar")
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

	@GetMapping(value = "/persona/atributos/modificar")
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
		respuestaTestAtributos.setAccion(Constantes.ACCION_MODIFICAR_PERSONA);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_PERSONA_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/empresa/atributos/guardar")
	@ResponseBody
	public RespEntity TestEmpresaAtributosAccionGuardar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {

			final List<DatosPruebaAtributos> pruebaAtributoCifEmpresa = testEmpresaService
					.getPruebasAtributoCifEmpresa();
			final List<DatosPruebaAtributos> pruebaAtributoNombreEmpresa = testEmpresaService
					.getPruebasAtributoNombreEmpresa();
			final List<DatosPruebaAtributos> pruebaAtributoDireccionEmpresa = testEmpresaService
					.getPruebasAtributoDireccionEmpresa();
			final List<DatosPruebaAtributos> pruebaAtributoTelefonoEmpresa = testEmpresaService
					.getPruebasAtributoTelefonoEmpresa();

			resultadoPruebasAtributos.addAll(pruebaAtributoCifEmpresa);
			resultadoPruebasAtributos.addAll(pruebaAtributoNombreEmpresa);
			resultadoPruebasAtributos.addAll(pruebaAtributoDireccionEmpresa);
			resultadoPruebasAtributos.addAll(pruebaAtributoTelefonoEmpresa);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_EMPRESA_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_EMPRESAS);
		respuestaTestAtributos.setAccion(Constantes.ACCION_AÑADIR_EMPRESA);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_EMPRESA_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/empresa/atributos/modificar")
	@ResponseBody
	public RespEntity TestEmpresaAtributosAccionModificar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {

			final List<DatosPruebaAtributos> pruebaAtributoCifEmpresa = testEmpresaService
					.getPruebasAtributoCifEmpresa();
			final List<DatosPruebaAtributos> pruebaAtributoNombreEmpresa = testEmpresaService
					.getPruebasAtributoNombreEmpresa();
			final List<DatosPruebaAtributos> pruebaAtributoDireccionEmpresa = testEmpresaService
					.getPruebasAtributoDireccionEmpresa();
			final List<DatosPruebaAtributos> pruebaAtributoTelefonoEmpresa = testEmpresaService
					.getPruebasAtributoTelefonoEmpresa();

			resultadoPruebasAtributos.addAll(pruebaAtributoCifEmpresa);
			resultadoPruebasAtributos.addAll(pruebaAtributoNombreEmpresa);
			resultadoPruebasAtributos.addAll(pruebaAtributoDireccionEmpresa);
			resultadoPruebasAtributos.addAll(pruebaAtributoTelefonoEmpresa);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_EMPRESA_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_EMPRESAS);
		respuestaTestAtributos.setAccion(Constantes.ACCION_MODIFICAR_EMPRESA);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_EMPRESA_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/empresa/atributos/buscar")
	@ResponseBody
	public RespEntity TestEmpresaAtributosAccionBuscar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {

			final List<DatosPruebaAtributos> pruebaAtributoCifEmpresa = testEmpresaService
					.getPruebasAtributoCifEmpresaBuscar();
			final List<DatosPruebaAtributos> pruebaAtributoNombreEmpresa = testEmpresaService
					.getPruebasAtributoNombreEmpresaBuscar();
			final List<DatosPruebaAtributos> pruebaAtributoDireccionEmpresa = testEmpresaService
					.getPruebasAtributoDireccionEmpresaBuscar();
			final List<DatosPruebaAtributos> pruebaAtributoTelefonoEmpresa = testEmpresaService
					.getPruebasAtributoTelefonoEmpresaBuscar();

			resultadoPruebasAtributos.addAll(pruebaAtributoCifEmpresa);
			resultadoPruebasAtributos.addAll(pruebaAtributoNombreEmpresa);
			resultadoPruebasAtributos.addAll(pruebaAtributoDireccionEmpresa);
			resultadoPruebasAtributos.addAll(pruebaAtributoTelefonoEmpresa);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_EMPRESA_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_EMPRESAS);
		respuestaTestAtributos.setAccion(Constantes.ACCION_BUSCAR_EMPRESA);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_EMPRESA_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/accion/accion/guardar")
	@ResponseBody
	public RespEntity TestAccionAccionGuardar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testAccionService.getPruebasAccionesAccionGuardar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_ACCION_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_ACCIONES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_AÑADIR_ACCION);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_ACCION_OK, respuestaTestAcciones);

	}

	@GetMapping(value = "/accion/accion/modificar")
	@ResponseBody
	public RespEntity TestAccionAccionModificar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testAccionService.getPruebasAccionesAccionModificar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_ACCION_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_ACCIONES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_MODIFICAR_ACCION);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_ACCION_OK, respuestaTestAcciones);

	}

	@GetMapping(value = "/accion/accion/eliminar")
	@ResponseBody
	public RespEntity TestAccionAccionEliminar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testAccionService.getPruebasAccionesAccionEliminar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_ACCION_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_ACCIONES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_ELIMINAR_ACCION);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_ACCION_OK, respuestaTestAcciones);

	}

	@GetMapping(value = "/accion/accion/buscar")
	@ResponseBody
	public RespEntity TestAccionAccionBuscar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testAccionService.getPruebasAccionesAccionBuscar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_ACCION_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_ACCIONES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_BUSCAR_ACCION);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_ACCION_OK, respuestaTestAcciones);

	}

	@GetMapping(value = "/accion/accion/reactivar")
	@ResponseBody
	public RespEntity TestAccionAccionReactivar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testAccionService.getPruebasAccionesAccionReactivar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_ACCION_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_ACCIONES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_REACTIVAR_ACCION);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_ACCION_OK, respuestaTestAcciones);

	}

	@GetMapping(value = "/accion/accion/asignar")
	@ResponseBody
	public RespEntity TestAccionAccionAsignarAcciones() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testAccionService.getPruebasAccionesAccionAsignarAcciones();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_ACCION_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_ACCIONES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_ASIGNAR_ACCION);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_ACCION_OK, respuestaTestAcciones);

	}

	@GetMapping(value = "/accion/accion/revocar")
	@ResponseBody
	public RespEntity TestAccionAccionRevocarAcciones() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testAccionService.getPruebasAccionesAccionDesasignarAcciones();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_ACCION_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_ACCIONES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_REVOCAR_ACCION);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_ACCION_OK, respuestaTestAcciones);

	}

	@GetMapping(value = "/rol/accion/guardar")
	@ResponseBody
	public RespEntity TestRolAccionGuardar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testRolService.getPruebasAccionesRolGuardar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_ROL_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_ROLES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_AÑADIR_ROL);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_ROL_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/rol/accion/eliminar")
	@ResponseBody
	public RespEntity TestRolAccionEliminar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testRolService.getPruebasAccionesRolEliminar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_ROL_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_ROLES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_ELIMINAR_ROL);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_ROL_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/rol/accion/modificar")
	@ResponseBody
	public RespEntity TestRolAccionModificar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testRolService.getPruebasAccionesRolModificar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_ROL_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_ROLES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_MODIFICAR_ROL);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_ROL_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/rol/accion/buscar")
	@ResponseBody
	public RespEntity TestRolAccionBuscar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testRolService.getPruebasAccionesRolBuscar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_ROL_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_ROLES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_BUSCAR_ROL);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_ROL_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/rol/accion/reactivar")
	@ResponseBody
	public RespEntity TestRolAccionReactivar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testRolService.getPruebasAccionesRolReactivar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_ROL_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_ROLES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_REACTIVAR_ROL);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_ROL_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/funcionalidad/accion/buscar")
	@ResponseBody
	public RespEntity TestFuncionalidadAccionBuscar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testFuncionalidadService.getPruebasAccionesFuncionalidadBuscar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_FUNCIONALIDAD_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_FUNCIONALIDADES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_BUSCAR_FUNCIONALIDAD);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_FUNCIONALIDAD_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/funcionalidad/accion/guardar")
	@ResponseBody
	public RespEntity TestFuncionalidadAccionGuardar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testFuncionalidadService.getPruebasAccionesFuncionalidadGuardar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_FUNCIONALIDAD_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_FUNCIONALIDADES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_AÑADIR_FUNCIONALIDAD);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_FUNCIONALIDAD_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/funcionalidad/accion/modificar")
	@ResponseBody
	public RespEntity TestFuncionalidadAccionModificar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testFuncionalidadService.getPruebasAccionesFuncionalidadModificar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_FUNCIONALIDAD_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_FUNCIONALIDADES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_MODIFICAR_FUNCIONALIDAD);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_FUNCIONALIDAD_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/funcionalidad/accion/eliminar")
	@ResponseBody
	public RespEntity TestFuncionalidadAccionEliminar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testFuncionalidadService.getPruebasAccionesFuncionalidadEliminar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_FUNCIONALIDAD_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_FUNCIONALIDADES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_ELIMINAR_FUNCIONALIDAD);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_FUNCIONALIDAD_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/funcionalidad/accion/reactivar")
	@ResponseBody
	public RespEntity TestFuncionalidadAccionReactivar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testFuncionalidadService.getPruebasAccionesFuncionalidadReactivar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_FUNCIONALIDAD_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_FUNCIONALIDADES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_REACTIVAR_FUNCIONALIDAD);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_FUNCIONALIDAD_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/usuario/accion/buscar")
	@ResponseBody
	public RespEntity TestUsuarioAccionBuscar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testUsuarioService.getPruebasAccionesUsuarioBuscar();

		} catch (IOException | ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_USUARIO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_USUARIOS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_BUSCAR_USUARIO);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_USUARIO_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/usuario/accion/eliminar")
	@ResponseBody
	public RespEntity TestUsuarioAccionEliminar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testUsuarioService.getPruebasAccionesUsuarioEliminar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_USUARIO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_USUARIOS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_ELIMINAR_USUARIO);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_USUARIO_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/usuario/accion/modificarRolUsuario")
	@ResponseBody
	public RespEntity TestUsuarioAccionModificarRolUsuario() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testUsuarioService.getPruebasAccionesUsuarioModificarRol();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_USUARIO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_USUARIOS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_MODIFICAR_ROL_USUARIO);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_USUARIO_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/usuario/accion/modificarPasswdUsuario")
	@ResponseBody
	public RespEntity TestUsuarioAccionModificarPasswdUsuario() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testUsuarioService.getPruebasAccionesUsuarioCambiarContraseña();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_USUARIO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_USUARIOS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_MODIFICAR_PASSWD_USUARIO);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_USUARIO_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/usuario/accion/reactivar")
	@ResponseBody
	public RespEntity TestUsuarioAccionReactivar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testUsuarioService.getPruebasAccionesUsuarioReactivar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_USUARIO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_USUARIOS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_REACTIVAR_USUARIO);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_USUARIO_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/persona/accion/buscar")
	@ResponseBody
	public RespEntity TestPersonaAccionBuscar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testPersonaService.getPruebasAccionesPersonaBuscar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_PERSONA_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_PERSONAS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_BUSCAR_PERSONA);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_PERSONA_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/persona/accion/guardar")
	@ResponseBody
	public RespEntity TestPersonaAccionGuardar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testPersonaService.getPruebasAccionesPersonaGuardar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_PERSONA_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_PERSONAS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_AÑADIR_PERSONA);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_PERSONA_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/persona/accion/eliminar")
	@ResponseBody
	public RespEntity TestPersonaAccionEliminar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testPersonaService.getPruebasAccionesPersonaEliminar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_PERSONA_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_PERSONAS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_ELIMINAR_PERSONA);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_PERSONA_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/persona/accion/modificar")
	@ResponseBody
	public RespEntity TestPersonaAccionModificar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testPersonaService.getPruebasAccionesPersonaModificar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_PERSONA_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_PERSONAS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_MODIFICAR_PERSONA);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_PERSONA_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/empresa/accion/buscar")
	@ResponseBody
	public RespEntity TestEmpresaAccionBuscar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testEmpresaService.getPruebasAccionesEmpresaBuscar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_EMPRESA_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_EMPRESAS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_BUSCAR_EMPRESA);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_EMPRESA_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/empresa/accion/guardar")
	@ResponseBody
	public RespEntity TestEmpresaAccionGuardar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testEmpresaService.getPruebasAccionesEmpresaGuardar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_EMPRESA_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_EMPRESAS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_AÑADIR_EMPRESA);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_EMPRESA_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/empresa/accion/modificar")
	@ResponseBody
	public RespEntity TestEmpresaAccionModificar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testEmpresaService.getPruebasAccionesEmpresaModificar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_EMPRESA_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_EMPRESAS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_MODIFICAR_EMPRESA);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_EMPRESA_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/empresa/accion/eliminar")
	@ResponseBody
	public RespEntity TestEmpresaAccionEliminar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testEmpresaService.getPruebasAccionesEmpresaEliminar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_EMPRESA_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_EMPRESAS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_ELIMINAR_EMPRESA);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_EMPRESA_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/empresa/accion/reactivar")
	@ResponseBody
	public RespEntity TestEmpresaAccionReactivar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testEmpresaService.getPruebasAccionesEmpresaReactivar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_EMPRESA_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_EMPRESAS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_REACTIVAR_EMPRESA);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_EMPRESA_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/noticia/atributos/guardar")
	@ResponseBody
	public RespEntity TestNoticiaAtributosAccionGuardar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoTituloNoticia = testNoticiaService
					.getPruebasAtributoTituloNoticia();
			final List<DatosPruebaAtributos> pruebaAtributoTextoNoticia = testNoticiaService
					.getPruebasAtributoTextoNoticia();

			resultadoPruebasAtributos.addAll(pruebaAtributoTituloNoticia);
			resultadoPruebasAtributos.addAll(pruebaAtributoTextoNoticia);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_NOTICIA_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_NOTICIAS);
		respuestaTestAtributos.setAccion(Constantes.ACCION_AÑADIR_NOTICIA);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_NOTICIA_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/noticia/atributos/modificar")
	@ResponseBody
	public RespEntity TestNoticiaAtributosAccionModificar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoTituloNoticia = testNoticiaService
					.getPruebasAtributoTituloNoticia();
			final List<DatosPruebaAtributos> pruebaAtributoTextoNoticia = testNoticiaService
					.getPruebasAtributoTextoNoticia();

			resultadoPruebasAtributos.addAll(pruebaAtributoTituloNoticia);
			resultadoPruebasAtributos.addAll(pruebaAtributoTextoNoticia);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_NOTICIA_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_NOTICIAS);
		respuestaTestAtributos.setAccion(Constantes.ACCION_MODIFICAR_NOTICIA);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_NOTICIA_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/noticia/atributos/buscar")
	@ResponseBody
	public RespEntity TestNoticiaAtributosAccionBuscar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoTituloNoticia = testNoticiaService
					.getPruebasAtributoTituloNoticiaBuscar();

			final List<DatosPruebaAtributos> pruebaAtributoTextoNoticia = testNoticiaService
					.getPruebasAtributoTextoNoticiaBuscar();

			resultadoPruebasAtributos.addAll(pruebaAtributoTituloNoticia);
			resultadoPruebasAtributos.addAll(pruebaAtributoTextoNoticia);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_NOTICIA_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_NOTICIAS);
		respuestaTestAtributos.setAccion(Constantes.ACCION_BUSCAR_NOTICIA);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_NOTICIA_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/noticia/accion/buscar")
	@ResponseBody
	public RespEntity TestNoticiaAccionBuscar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testNoticiaService.getPruebasNoticiasAccionBuscar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_NOTICIA_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_NOTICIAS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_BUSCAR_NOTICIA);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_NOTICIA_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/noticia/accion/guardar")
	@ResponseBody
	public RespEntity TestNoticiaAccionGuardar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testNoticiaService.getPruebasNoticiasAccionAñadir();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_NOTICIA_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_NOTICIAS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_AÑADIR_NOTICIA);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_NOTICIA_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/noticia/accion/modificar")
	@ResponseBody
	public RespEntity TestNoticiaAccionModificar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testNoticiaService.getPruebasNoticiasAccionModificar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_NOTICIA_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_NOTICIAS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_MODIFICAR_NOTICIA);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_NOTICIA_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/noticia/accion/eliminar")
	@ResponseBody
	public RespEntity TestNoticiaAccionEliminar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testNoticiaService.getPruebasNoticiasAccionEliminar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_NOTICIA_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_NOTICIAS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_ELIMINAR_NOTICIA);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_NOTICIA_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/objetivo/atributos/guardar")
	@ResponseBody
	public RespEntity TestObjetivoAtributosAccionGuardar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoNombreObjetivo = testObjetivoService
					.getPruebasAtributoNombreObjetivo();
			final List<DatosPruebaAtributos> pruebaAtributoDescripcionObjetivo = testObjetivoService
					.getPruebasAtributoDescripObjetivo();

			resultadoPruebasAtributos.addAll(pruebaAtributoNombreObjetivo);
			resultadoPruebasAtributos.addAll(pruebaAtributoDescripcionObjetivo);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_OBJETIVO_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_OBJETIVOS);
		respuestaTestAtributos.setAccion(Constantes.ACCION_AÑADIR_OBJETIVO);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_OBJETIVO_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/objetivo/atributos/modificar")
	@ResponseBody
	public RespEntity TestObjetivoAtributosAccionModificar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoNombreObjetivo = testObjetivoService
					.getPruebasAtributoNombreObjetivo();
			final List<DatosPruebaAtributos> pruebaAtributoDescripcionObjetivo = testObjetivoService
					.getPruebasAtributoDescripObjetivo();

			resultadoPruebasAtributos.addAll(pruebaAtributoNombreObjetivo);
			resultadoPruebasAtributos.addAll(pruebaAtributoDescripcionObjetivo);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_OBJETIVO_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_OBJETIVOS);
		respuestaTestAtributos.setAccion(Constantes.ACCION_MODIFICAR_OBJETIVO);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_OBJETIVO_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/objetivo/atributos/buscar")
	@ResponseBody
	public RespEntity TestObjetivoAtributosAccionBuscar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoNombreObjetivo = testObjetivoService
					.getPruebasAtributoNombreObjetivoBuscar();
			final List<DatosPruebaAtributos> pruebaAtributoDescripcionObjetivo = testObjetivoService
					.getPruebasAtributoDescripObjetivoBuscar();

			resultadoPruebasAtributos.addAll(pruebaAtributoNombreObjetivo);
			resultadoPruebasAtributos.addAll(pruebaAtributoDescripcionObjetivo);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_OBJETIVO_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_OBJETIVOS);
		respuestaTestAtributos.setAccion(Constantes.ACCION_BUSCAR_OBJETIVO);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_OBJETIVO_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/objetivo/accion/buscar")
	@ResponseBody
	public RespEntity TestObjetivoAccionBuscar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testObjetivoService.getPruebasAccionesObjetivoBuscar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_OBJETIVO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_OBJETIVOS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_BUSCAR_OBJETIVO);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_OBJETIVO_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/objetivo/accion/guardar")
	@ResponseBody
	public RespEntity TestObjetivoAccionGuardar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testObjetivoService.getPruebasAccionesObjetivoGuardar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_OBJETIVO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_OBJETIVOS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_AÑADIR_OBJETIVO);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_OBJETIVO_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/objetivo/accion/modificar")
	@ResponseBody
	public RespEntity TestObjetivoAccionModificar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testObjetivoService.getPruebasAccionesObjetivoModificar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_OBJETIVO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_OBJETIVOS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_MODIFICAR_OBJETIVO);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_OBJETIVO_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/objetivo/accion/eliminar")
	@ResponseBody
	public RespEntity TestObjetivoAccionEliminar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testObjetivoService.getPruebasAccionesObjetivoEliminar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_OBJETIVO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_OBJETIVOS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_ELIMINAR_OBJETIVO);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_OBJETIVO_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/objetivo/accion/reactivar")
	@ResponseBody
	public RespEntity TestObjetivoAccionReactivar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testObjetivoService.getPruebasAccionesObjetivoReactivar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_OBJETIVO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_OBJETIVOS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_REACTIVAR_OBJETIVO);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_OBJETIVO_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/respuestaPosible/atributos/guardar")
	@ResponseBody
	public RespEntity TestRespuestaPosibleAtributosAccionGuardar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoTextoRespuestaPosible = testRespuestaPosibleService
					.getPruebasAtributoTextoRespuestaPosible();

			resultadoPruebasAtributos.addAll(pruebaAtributoTextoRespuestaPosible);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_RESPUESTA_POSIBLE_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_RESPUESTAS_POSIBLES);
		respuestaTestAtributos.setAccion(Constantes.ACCION_AÑADIR_RESPUESTA);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_RESPUESTA_POSIBLE_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/respuestaPosible/atributos/buscar")
	@ResponseBody
	public RespEntity TestRespuestaPosibleAtributosAccionBuscar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoTextoRespuestaPosible = testRespuestaPosibleService
					.getPruebasAtributoTextoRespuestaPosibleBuscar();

			resultadoPruebasAtributos.addAll(pruebaAtributoTextoRespuestaPosible);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_RESPUESTA_POSIBLE_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_RESPUESTAS_POSIBLES);
		respuestaTestAtributos.setAccion(Constantes.ACCION_BUSCAR_RESPUESTA);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_RESPUESTA_POSIBLE_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/respuestaPosible/atributos/modificar")
	@ResponseBody
	public RespEntity TestRespuestaPosibleAtributosAccionModificar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoTextoRespuestaPosible = testRespuestaPosibleService
					.getPruebasAtributoTextoRespuestaPosible();

			resultadoPruebasAtributos.addAll(pruebaAtributoTextoRespuestaPosible);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_RESPUESTA_POSIBLE_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_RESPUESTAS_POSIBLES);
		respuestaTestAtributos.setAccion(Constantes.ACCION_MODIFICAR_RESPUESTA);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_RESPUESTA_POSIBLE_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/respuestaPosible/accion/guardar")
	@ResponseBody
	public RespEntity TestRespuestaPosibleAccionGuardar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testRespuestaPosibleService.getPruebasAccionesRespuestaPosibleGuardar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_RESPUESTA_POSIBLE_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_RESPUESTAS_POSIBLES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_AÑADIR_RESPUESTA);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_RESPUESTA_POSIBLE_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/respuestaPosible/accion/buscar")
	@ResponseBody
	public RespEntity TestRespuestaPosibleAccionBuscar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testRespuestaPosibleService.getPruebasAccionesRespuestaPosibleBuscar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_RESPUESTA_POSIBLE_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_RESPUESTAS_POSIBLES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_BUSCAR_RESPUESTA);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_RESPUESTA_POSIBLE_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/respuestaPosible/accion/modificar")
	@ResponseBody
	public RespEntity TestRespuestaPosibleAccionModificar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testRespuestaPosibleService.getPruebasAccionesRespuestaPosibleModificar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_RESPUESTA_POSIBLE_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_RESPUESTAS_POSIBLES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_MODIFICAR_RESPUESTA);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_RESPUESTA_POSIBLE_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/respuestaPosible/accion/eliminar")
	@ResponseBody
	public RespEntity TestRespuestaPosibleAccionEliminar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testRespuestaPosibleService.getPruebasAccionesRespuestaPosibleEliminar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_RESPUESTA_POSIBLE_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_RESPUESTAS_POSIBLES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_ELIMINAR_RESPUESTA);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_RESPUESTA_POSIBLE_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/respuestaPosible/accion/reactivar")
	@ResponseBody
	public RespEntity TestRespuestaPosibleAccionReactivar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testRespuestaPosibleService.getPruebasAccionesRespuestaPosibleReactivar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_RESPUESTA_POSIBLE_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_RESPUESTAS_POSIBLES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_REACTIVAR_RESPUESTA);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_RESPUESTA_POSIBLE_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/plan/atributos/buscar")
	@ResponseBody
	public RespEntity TestPlanAtributosAccionBuscar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoNombrePlan = testPlanService
					.getPruebasAtributoNombrePlanBuscar();
			final List<DatosPruebaAtributos> pruebaAtributoDescripPlan = testPlanService
					.getPruebasAtributoDescripPlanBuscar();
			final List<DatosPruebaAtributos> pruebaAtributoFechaPlan = testPlanService
					.getPruebasAtributoFechaPlanBuscar();

			resultadoPruebasAtributos.addAll(pruebaAtributoNombrePlan);
			resultadoPruebasAtributos.addAll(pruebaAtributoDescripPlan);
			resultadoPruebasAtributos.addAll(pruebaAtributoFechaPlan);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_PLAN_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_PLANES);
		respuestaTestAtributos.setAccion(Constantes.ACCION_BUSCAR_PLAN);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_PLAN_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/plan/atributos/guardar")
	@ResponseBody
	public RespEntity TestPlanAtributosAccionGuardar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoNombrePlan = testPlanService.getPruebasAtributoNombrePlan();
			final List<DatosPruebaAtributos> pruebaAtributoDescripPlan = testPlanService
					.getPruebasAtributoDescripPlan();
			final List<DatosPruebaAtributos> pruebaAtributoFechaPlan = testPlanService.getPruebasAtributoFechaPlan();

			resultadoPruebasAtributos.addAll(pruebaAtributoNombrePlan);
			resultadoPruebasAtributos.addAll(pruebaAtributoDescripPlan);
			resultadoPruebasAtributos.addAll(pruebaAtributoFechaPlan);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_PLAN_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_PLANES);
		respuestaTestAtributos.setAccion(Constantes.ACCION_AÑADIR_PLAN);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_PLAN_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/plan/atributos/modificar")
	@ResponseBody
	public RespEntity TestPlanAtributosAccionModificar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoNombrePlan = testPlanService.getPruebasAtributoNombrePlan();
			final List<DatosPruebaAtributos> pruebaAtributoDescripPlan = testPlanService
					.getPruebasAtributoDescripPlan();
			final List<DatosPruebaAtributos> pruebaAtributoFechaPlan = testPlanService.getPruebasAtributoFechaPlan();

			resultadoPruebasAtributos.addAll(pruebaAtributoNombrePlan);
			resultadoPruebasAtributos.addAll(pruebaAtributoDescripPlan);
			resultadoPruebasAtributos.addAll(pruebaAtributoFechaPlan);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_PLAN_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_PLANES);
		respuestaTestAtributos.setAccion(Constantes.ACCION_MODIFICAR_PLAN);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_PLAN_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/plan/accion/buscar")
	@ResponseBody
	public RespEntity TestPlanAccionBuscar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testPlanService.getPruebasAccionesPlanBuscar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_PLAN_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_PLANES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_BUSCAR_PLAN);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_PLAN_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/plan/accion/guardar")
	@ResponseBody
	public RespEntity TestPlanAccionGuardar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testPlanService.getPruebasAccionesPlanGuardar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_PLAN_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_PLANES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_AÑADIR_PLAN);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_PLAN_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/plan/accion/modificar")
	@ResponseBody
	public RespEntity TestPlanAccionModificar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testPlanService.getPruebasAccionesPlanModificar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_PLAN_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_PLANES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_MODIFICAR_PLAN);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_PLAN_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/plan/accion/eliminar")
	@ResponseBody
	public RespEntity TestPlanAccionEliminar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testPlanService.getPruebasAccionesPlanEliminar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_PLAN_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_PLANES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_ELIMINAR_PLAN);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_PLAN_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/plan/accion/reactivar")
	@ResponseBody
	public RespEntity TestPlanAccionReactivar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testPlanService.getPruebasAccionesPlanReactivar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_PLAN_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_PLANES);
		respuestaTestAcciones.setAccion(Constantes.ACCION_REACTIVAR_PLAN);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_PLAN_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/proceso/atributos/buscar")
	@ResponseBody
	public RespEntity TestProcesoAtributosAccionBuscar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoNombreProceso = testProcesoService
					.getPruebasAtributoNombreProcesoBuscar();
			final List<DatosPruebaAtributos> pruebaAtributoDescripProceso = testProcesoService
					.getPruebasAtributoDescripProcesoBuscar();
			final List<DatosPruebaAtributos> pruebaAtributoFechaProceso = testProcesoService
					.getPruebasAtributoFechaProcesoBuscar();

			resultadoPruebasAtributos.addAll(pruebaAtributoNombreProceso);
			resultadoPruebasAtributos.addAll(pruebaAtributoDescripProceso);
			resultadoPruebasAtributos.addAll(pruebaAtributoFechaProceso);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_PROCESO_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_PROCESOS);
		respuestaTestAtributos.setAccion(Constantes.ACCION_BUSCAR_PROCESO);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_PROCESO_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/proceso/atributos/guardar")
	@ResponseBody
	public RespEntity TestProcesoAtributosAccionGuardar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoNombreProceso = testProcesoService
					.getPruebasAtributoNombreProceso();
			final List<DatosPruebaAtributos> pruebaAtributoDescripProceso = testProcesoService
					.getPruebasAtributoDescripProceso();
			final List<DatosPruebaAtributos> pruebaAtributoFechaProceso = testProcesoService
					.getPruebasAtributoFechaProceso();

			resultadoPruebasAtributos.addAll(pruebaAtributoNombreProceso);
			resultadoPruebasAtributos.addAll(pruebaAtributoDescripProceso);
			resultadoPruebasAtributos.addAll(pruebaAtributoFechaProceso);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_PROCESO_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_PROCESOS);
		respuestaTestAtributos.setAccion(Constantes.ACCION_AÑADIR_PROCESO);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_PROCESO_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/proceso/atributos/modificar")
	@ResponseBody
	public RespEntity TestProcesoAtributosAccionModificar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoNombreProceso = testProcesoService
					.getPruebasAtributoNombreProceso();
			final List<DatosPruebaAtributos> pruebaAtributoDescripProceso = testProcesoService
					.getPruebasAtributoDescripProceso();
			final List<DatosPruebaAtributos> pruebaAtributoFechaProceso = testProcesoService
					.getPruebasAtributoFechaProceso();

			resultadoPruebasAtributos.addAll(pruebaAtributoNombreProceso);
			resultadoPruebasAtributos.addAll(pruebaAtributoDescripProceso);
			resultadoPruebasAtributos.addAll(pruebaAtributoFechaProceso);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_PROCESO_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_PROCESOS);
		respuestaTestAtributos.setAccion(Constantes.ACCION_MODIFICAR_PROCESO);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_PROCESO_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/proceso/accion/buscar")
	@ResponseBody
	public RespEntity TestProcesooAccionBuscar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testProcesoService.getPruebasAccionesProcesoBuscar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_PROCESO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_PROCESOS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_BUSCAR_PROCESO);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_PROCESO_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/proceso/accion/guardar")
	@ResponseBody
	public RespEntity TestProcesooAccionGuardar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testProcesoService.getPruebasAccionesProcesoGuardar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_PROCESO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_PROCESOS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_AÑADIR_PROCESO);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_PROCESO_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/proceso/accion/modificar")
	@ResponseBody
	public RespEntity TestProcesoAccionModificar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testProcesoService.getPruebasAccionesProcesoModificar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_PROCESO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_PROCESOS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_MODIFICAR_PROCESO);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_PROCESO_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/proceso/accion/eliminar")
	@ResponseBody
	public RespEntity TestProcesoAccionEliminar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testProcesoService.getPruebasAccionesProcesoEliminar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_PROCESO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_PROCESOS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_ELIMINAR_PROCESO);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_PROCESO_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/proceso/accion/reactivar")
	@ResponseBody
	public RespEntity TestProcesoAccionReactivar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testProcesoService.getPruebasAccionesProcesoReactivar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_PROCESO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_PROCESOS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_REACTIVAR_PROCESO);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_PROCESO_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/procedimiento/atributos/buscar")
	@ResponseBody
	public RespEntity TestProcedimientoAtributosAccionBuscar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoNombreProcedimiento = testProcedimientoService
					.getPruebasAtributoNombreProcedimientoBuscar();
			final List<DatosPruebaAtributos> pruebaAtributoDescripProcedimiento = testProcedimientoService
					.getPruebasAtributoDescripProcedimientoBuscar();
			final List<DatosPruebaAtributos> pruebaAtributoFechaProcedimiento = testProcedimientoService
					.getPruebasAtributoFechaProcedimientoBuscar();

			resultadoPruebasAtributos.addAll(pruebaAtributoNombreProcedimiento);
			resultadoPruebasAtributos.addAll(pruebaAtributoDescripProcedimiento);
			resultadoPruebasAtributos.addAll(pruebaAtributoFechaProcedimiento);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_PROCEDIMIENTO_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_PROCEDIMIENTOS);
		respuestaTestAtributos.setAccion(Constantes.ACCION_BUSCAR_PROCEDIMIENTO);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_PROCEDIMIENTO_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/procedimiento/atributos/guardar")
	@ResponseBody
	public RespEntity TestProcedimientoAtributosAccionGuardar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoNombreProcedimiento = testProcedimientoService
					.getPruebasAtributoNombreProcedimiento();
			final List<DatosPruebaAtributos> pruebaAtributoDescripProcedimiento = testProcedimientoService
					.getPruebasAtributoDescripProcedimiento();
			final List<DatosPruebaAtributos> pruebaAtributoFechaProcedimiento = testProcedimientoService
					.getPruebasAtributoFechaProcedimiento();

			resultadoPruebasAtributos.addAll(pruebaAtributoNombreProcedimiento);
			resultadoPruebasAtributos.addAll(pruebaAtributoDescripProcedimiento);
			resultadoPruebasAtributos.addAll(pruebaAtributoFechaProcedimiento);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_PROCEDIMIENTO_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_PROCEDIMIENTOS);
		respuestaTestAtributos.setAccion(Constantes.ACCION_AÑADIR_PROCEDIMIENTO);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_PROCEDIMIENTO_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/procedimiento/atributos/modificar")
	@ResponseBody
	public RespEntity TestProcedimientoAtributosAccionModificar() {

		final RespuestaTestAtributos respuestaTestAtributos = new RespuestaTestAtributos();

		final List<DatosPruebaAtributos> resultadoPruebasAtributos = new ArrayList<>();

		try {
			final List<DatosPruebaAtributos> pruebaAtributoNombreProcedimiento = testProcedimientoService
					.getPruebasAtributoNombreProcedimiento();
			final List<DatosPruebaAtributos> pruebaAtributoDescripProcedimiento = testProcedimientoService
					.getPruebasAtributoDescripProcedimiento();
			final List<DatosPruebaAtributos> pruebaAtributoFechaProcedimiento = testProcedimientoService
					.getPruebasAtributoFechaProcedimiento();

			resultadoPruebasAtributos.addAll(pruebaAtributoNombreProcedimiento);
			resultadoPruebasAtributos.addAll(pruebaAtributoDescripProcedimiento);
			resultadoPruebasAtributos.addAll(pruebaAtributoFechaProcedimiento);

		} catch (IOException | ParseException | java.text.ParseException exc) {
			return new RespEntity(RespCode.TEST_ATRIBUTOS_PROCEDIMIENTO_KO, StringUtils.EMPTY);
		}

		respuestaTestAtributos.setFuncionalidad(Constantes.GESTION_PROCEDIMIENTOS);
		respuestaTestAtributos.setAccion(Constantes.ACCION_MODIFICAR_PROCEDIMIENTO);
		respuestaTestAtributos.setDatosPruebaAtributos(resultadoPruebasAtributos);

		return new RespEntity(RespCode.TEST_ATRIBUTOS_PROCEDIMIENTO_OK, respuestaTestAtributos);

	}

	@GetMapping(value = "/procedimiento/accion/buscar")
	@ResponseBody
	public RespEntity TestProcedimientoAccionBuscar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testProcedimientoService.getPruebasAccionesProcedimientoBuscar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_PROCEDIMIENTO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_PROCEDIMIENTOS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_BUSCAR_PROCEDIMIENTO);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_PROCEDIMIENTO_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/procedimiento/accion/guardar")
	@ResponseBody
	public RespEntity TestProcedimientoAccionGuardar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testProcedimientoService.getPruebasAccionesProcedimientoGuardar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_PROCEDIMIENTO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_PROCEDIMIENTOS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_AÑADIR_PROCEDIMIENTO);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_PROCEDIMIENTO_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/procedimiento/accion/modificar")
	@ResponseBody
	public RespEntity TestProcedimientoAccionModificar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testProcedimientoService.getPruebasAccionesProcedimientoModificar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_PROCEDIMIENTO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_PROCEDIMIENTOS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_MODIFICAR_PROCEDIMIENTO);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_PROCEDIMIENTO_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/procedimiento/accion/eliminar")
	@ResponseBody
	public RespEntity TestProcedimientoAccionEliminar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testProcedimientoService.getPruebasAccionesProcedimientoEliminar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_PROCEDIMIENTO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_PROCEDIMIENTOS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_ELIMINAR_PROCEDIMIENTO);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_PROCEDIMIENTO_OK, respuestaTestAcciones);
	}

	@GetMapping(value = "/procedimiento/accion/reactivar")
	@ResponseBody
	public RespEntity TestProcedimientoAccionReactivar() {

		final RespuestaTestAcciones respuestaTestAcciones = new RespuestaTestAcciones();
		List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		try {
			datosPruebaAcciones = testProcedimientoService.getPruebasAccionesProcedimientoReactivar();

		} catch (IOException | ParseException | java.text.ParseException e) {
			return new RespEntity(RespCode.TEST_ACCIONES_PROCEDIMIENTO_KO, StringUtils.EMPTY);
		}

		respuestaTestAcciones.setFuncionalidad(Constantes.GESTION_PROCEDIMIENTOS);
		respuestaTestAcciones.setAccion(Constantes.ACCION_REACTIVAR_PROCEDIMIENTO);
		respuestaTestAcciones.setDatosPruebaAcciones(datosPruebaAcciones);

		return new RespEntity(RespCode.TEST_ACCIONES_PROCEDIMIENTO_OK, respuestaTestAcciones);
	}

}
