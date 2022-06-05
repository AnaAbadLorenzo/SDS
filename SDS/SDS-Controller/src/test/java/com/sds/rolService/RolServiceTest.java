package com.sds.rolService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sds.app.SDSApplication;
import com.sds.model.AccionEntity;
import com.sds.model.FuncionalidadEntity;
import com.sds.model.RolAccionFuncionalidadEntity;
import com.sds.model.RolEntity;
import com.sds.repository.RolAccionFuncionalidadRepository;
import com.sds.service.accion.AccionService;
import com.sds.service.accion.model.Accion;
import com.sds.service.common.CommonUtilities;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.AccionNoExisteException;
import com.sds.service.exception.AccionYaExisteException;
import com.sds.service.exception.FuncionalidadNoExisteException;
import com.sds.service.exception.FuncionalidadYaExisteException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.RolAsociadoAccionFuncionalidadException;
import com.sds.service.exception.RolAsociadoUsuarioException;
import com.sds.service.exception.RolNoExisteException;
import com.sds.service.exception.RolYaExisteException;
import com.sds.service.funcionalidad.FuncionalidadService;
import com.sds.service.funcionalidad.model.Funcionalidad;
import com.sds.service.rol.RolService;
import com.sds.service.rol.model.Rol;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SDSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RolServiceTest {

	@Autowired
	RolService rolService;

	@Autowired
	AccionService accionService;

	@Autowired
	FuncionalidadService funcionalidadService;

	@Autowired
	RolAccionFuncionalidadRepository rolAccionFuncionalidadRepository;

	@Test
	public void RolService_buscarRol() throws IOException, ParseException {

		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.BUSCAR_ROL);
		final RolEntity rolEntity = rol.getRol();

		final ReturnBusquedas<RolEntity> rolEncontrado = rolService.buscarRol(rolEntity.getRolName(),
				rolEntity.getRolDescription(), 0, 1);

		assertNotNull(rolEncontrado.getListaBusquedas());
	}

	@Test
	public void RolService_buscarRolNameVacio() throws IOException, ParseException {

		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.ROL_NAME_VACIO_DATA);
		final RolEntity rolEntity = rol.getRol();

		final ReturnBusquedas<RolEntity> rolEncontrado = rolService.buscarRol(rolEntity.getRolName(),
				rolEntity.getRolDescription(), 0, 1);

		assertNotNull(rolEncontrado.getListaBusquedas());
	}

	@Test
	public void RolService_buscarRolDescriptionVacio() throws IOException, ParseException {

		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.ROL_DESCRIPTION_VACIO_DATA);
		final RolEntity rolEntity = rol.getRol();

		final ReturnBusquedas<RolEntity> rolEncontrado = rolService.buscarRol(rolEntity.getRolName(),
				rolEntity.getRolDescription(), 0, 1);

		assertNotNull(rolEncontrado.getListaBusquedas());
	}

	@Test
	public void RolService_buscarRolNameDescriptionVacios() throws IOException, ParseException {

		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.ROL_NAME_DESCRIPTION_VACIOS);
		final RolEntity rolEntity = rol.getRol();

		final ReturnBusquedas<RolEntity> rolEncontrado = rolService.buscarRol(rolEntity.getRolName(),
				rolEntity.getRolDescription(), 0, 1);

		assertNotNull(rolEncontrado.getListaBusquedas());
	}

	@Test
	public void RolService_buscarTodos() throws IOException, ParseException {

		final ReturnBusquedas<RolEntity> roles = rolService.buscarTodos(0, 5);

		assertNotNull(roles.getListaBusquedas());
	}

	@Test
	public void RolService_guardarRol() throws RolYaExisteException, IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, RolNoExisteException {
		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.GUARDAR_ROL);

		String respuesta = StringUtils.EMPTY;

		respuesta = rolService.guardarRol(rol);

		assertEquals(respuesta, Constantes.OK);

		final ReturnBusquedas<RolEntity> rolDelete = rolService.buscarRol(rol.getRol().getRolName(),
				rol.getRol().getRolDescription(), 0, 1);

		rol.setRol(rolDelete.getListaBusquedas().get(0));

		rolService.deleteRol(rol);
	}

	@Test
	public void RolService_guardarRolRolNameVacio() throws RolYaExisteException, IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {
		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.ROL_NAME_VACIO_DATA);
		String respuesta = StringUtils.EMPTY;

		respuesta = rolService.guardarRol(rol);

		assertEquals(respuesta, CodeMessageErrors.ROL_VACIO.name());
	}

	@Test
	public void RolService_guardarRolRolDescriptionVacio() throws RolYaExisteException, IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {
		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.ROL_DESCRIPTION_VACIO_DATA);
		String respuesta = StringUtils.EMPTY;

		respuesta = rolService.guardarRol(rol);

		assertEquals(respuesta, CodeMessageErrors.ROL_VACIO.name());
	}

	@Test
	public void RolService_guardarRolRolNameDescriptionVacios() throws RolYaExisteException, IOException,
			ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {
		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.ROL_NAME_DESCRIPTION_VACIOS);

		String respuesta = StringUtils.EMPTY;

		respuesta = rolService.guardarRol(rol);

		assertEquals(respuesta, CodeMessageErrors.ROL_VACIO.name());
	}

	@Test(expected = RolYaExisteException.class)
	public void RolService_guardarRolRolYaExiste() throws RolYaExisteException, IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, RolNoExisteException {

		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.ROL_YA_EXISTE);

		rolService.guardarRol(rol);

		try {
			rolService.guardarRol(rol);
		} catch (final RolYaExisteException respuestaPosibleYaExisteException) {
			throw new RolYaExisteException(CodeMessageErrors.ROL_YA_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.ROL_YA_EXISTE_EXCEPTION.getCodigo()));
		} finally {
			final ReturnBusquedas<RolEntity> rolDelete = rolService.buscarRol(rol.getRol().getRolName(),
					rol.getRol().getRolDescription(), 0, 1);
			rolService.deleteRol(new Rol(rol.getUsuario(), rolDelete.getListaBusquedas().get(0)));
		}

	}

	@Test
	public void RolService_modificarRol() throws RolNoExisteException, IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, RolYaExisteException {

		final Rol rolGuardar = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.GUARDAR_ROL);

		String respuesta = StringUtils.EMPTY;

		rolService.guardarRol(rolGuardar);

		final ReturnBusquedas<RolEntity> rolModificar = rolService.buscarRol(rolGuardar.getRol().getRolName(),
				rolGuardar.getRol().getRolDescription(), 0, 1);

		rolModificar.getListaBusquedas().get(0).setRolName("Modificacion");
		rolModificar.getListaBusquedas().get(0).setRolDescription("Hecha la modificacion");

		rolGuardar.setRol(rolModificar.getListaBusquedas().get(0));

		respuesta = rolService.modificarRol(rolGuardar);

		assertEquals(respuesta, Constantes.OK);

		rolService.deleteRol(rolGuardar);

	}

	@Test
	public void RolService_modificarRolRolNameVacio() throws RolNoExisteException, IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {
		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.ROL_NAME_VACIO_DATA);

		String respuesta = StringUtils.EMPTY;

		respuesta = rolService.modificarRol(rol);

		assertEquals(respuesta, CodeMessageErrors.ROL_VACIO.name());
	}

	@Test
	public void RolService_modificarRolRolDescriptionVacio() throws RolNoExisteException, IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {
		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.ROL_DESCRIPTION_VACIO_DATA);

		String respuesta = StringUtils.EMPTY;

		respuesta = rolService.modificarRol(rol);

		assertEquals(respuesta, CodeMessageErrors.ROL_VACIO.name());
	}

	@Test
	public void RolService_modificarRolRolNameDescriptionVacio() throws RolNoExisteException, IOException,
			ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {
		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.ROL_NAME_DESCRIPTION_VACIOS);

		String respuesta = StringUtils.EMPTY;

		respuesta = rolService.modificarRol(rol);

		assertEquals(respuesta, CodeMessageErrors.ROL_VACIO.name());
	}

	@Test(expected = RolNoExisteException.class)
	public void RolService_modificarRolRolNoExiste() throws RolNoExisteException, IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {

		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.ROL_NO_EXISTE);

		rolService.modificarRol(rol);

	}

	@Test
	public void RolService_eliminarRolCorrecto() throws RolNoExisteException, RolAsociadoUsuarioException, IOException,
			ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, RolYaExisteException,
			RolAsociadoAccionFuncionalidadException {
		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.GUARDAR_ROL);

		rolService.guardarRol(rol);

		final ReturnBusquedas<RolEntity> rolGuardado = rolService.buscarRol(rol.getRol().getRolName(),
				rol.getRol().getRolDescription(), 0, 1);

		rol.setRol(rolGuardado.getListaBusquedas().get(0));

		final String respuesta = rolService.eliminarRol(rol);

		assertEquals(respuesta, Constantes.OK);

		rolService.deleteRol(rol);

	}

	@Test(expected = RolNoExisteException.class)
	public void RolService_eliminarRolNoExiste()
			throws RolNoExisteException, RolAsociadoUsuarioException, IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, RolAsociadoAccionFuncionalidadException {

		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.ELIMINAR_ROL_NO_EXISTE);

		rolService.eliminarRol(rol);

	}

	@Test(expected = RolAsociadoUsuarioException.class)
	public void RolService_eliminarRolAsociadoUsuario()
			throws RolNoExisteException, RolAsociadoUsuarioException, IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, RolAsociadoAccionFuncionalidadException {

		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.ELIMINAR_ROL_ASOCIADO_USUARIO);

		rolService.eliminarRol(rol);

	}

	@Test(expected = RolAsociadoAccionFuncionalidadException.class)
	public void RolService_eliminarRolAsociadoAccionFuncionalidad() throws RolNoExisteException,
			RolAsociadoUsuarioException, IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, RolAsociadoAccionFuncionalidadException, AccionYaExisteException,
			FuncionalidadYaExisteException, AccionNoExisteException, FuncionalidadNoExisteException {

		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA,
				Constantes.ELIMINAR_ROL_ASOCIADO_ACCION_FUNCIONALIDAD);

		final AccionEntity accionEntity = new AccionEntity(0, "nombreAccion", "descripcionAccion", 0);
		final FuncionalidadEntity funcionalidadEntity = new FuncionalidadEntity(0, "nombreFuncionalidad",
				"descripFuncionalidad", 0);

		accionService.anadirAccion(new Accion("ana", accionEntity));
		funcionalidadService.anadirFuncionalidad(new Funcionalidad("ana", funcionalidadEntity));

		final ReturnBusquedas<AccionEntity> accionBuscar = accionService.buscarAccion(accionEntity.getNombreAccion(),
				accionEntity.getDescripAccion(), 0, 1);
		final ReturnBusquedas<FuncionalidadEntity> funcionalidadBuscar = funcionalidadService.buscarFuncionalidad(
				funcionalidadEntity.getNombreFuncionalidad(), funcionalidadEntity.getDescripFuncionalidad(), 0, 1);

		final RolAccionFuncionalidadEntity rolAccionFuncionalidad = new RolAccionFuncionalidadEntity(
				accionBuscar.getListaBusquedas().get(0).getIdAccion(),
				funcionalidadBuscar.getListaBusquedas().get(0).getIdFuncionalidad(), rol.getRol().getIdRol());

		rolAccionFuncionalidadRepository.saveAndFlush(rolAccionFuncionalidad);

		try {
			rolService.eliminarRol(rol);
		} catch (final RolAsociadoAccionFuncionalidadException exception) {
			throw new RolAsociadoAccionFuncionalidadException(
					CodeMessageErrors.ROL_ASOCIADO_ACCION_FUNCIONALIDAD_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(
							CodeMessageErrors.ROL_ASOCIADO_ACCION_FUNCIONALIDAD_EXCEPTION.getCodigo()));
		} finally {
			rolAccionFuncionalidadRepository.delete(rolAccionFuncionalidad);
			accionService.deleteAccion(accionBuscar.getListaBusquedas().get(0));
			funcionalidadService.deleteFuncionalidad(funcionalidadBuscar.getListaBusquedas().get(0));

		}

	}

	@Test
	public void RolService_buscarRolesEliminados() throws RolNoExisteException, IOException, ParseException {

		final ReturnBusquedas<RolEntity> rolEncontrado = rolService.buscarRolesEliminados(0, 5);

		assertNotNull(rolEncontrado.getListaBusquedas());
	}

	@Test
	public void RolService_reactivarRol() throws IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, RolNoExisteException, RolYaExisteException {

		final Rol rolGuardar = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.REACTIVAR_ROL);

		String respuesta = StringUtils.EMPTY;

		rolService.guardarRol(rolGuardar);

		final ReturnBusquedas<RolEntity> rolModificar = rolService.buscarRol(rolGuardar.getRol().getRolName(),
				rolGuardar.getRol().getRolDescription(), 0, 1);

		rolModificar.getListaBusquedas().get(0).setBorradoRol(0);

		rolGuardar.setRol(rolModificar.getListaBusquedas().get(0));

		respuesta = rolService.reactivarRol(rolGuardar);

		assertEquals(Constantes.OK, respuesta);

		rolService.deleteRol(rolGuardar);

	}

	@Test(expected = RolNoExisteException.class)
	public void RolService_reactivarRolNoExiste() throws RolNoExisteException, IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, RolNoExisteException {

		final Rol rolGuardar = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.ROL_NO_EXISTE);

		rolService.reactivarRol(rolGuardar);

	}

	private Rol generateRol(final String fichero, final String nombrePrueba) throws IOException, ParseException {

		final JSONObject jsonRol = new Util().getDatosJson(fichero, nombrePrueba);

		final Rol rol = new Rol();
		final RolEntity rolEntity = new RolEntity();

		final String idRol = CommonUtilities.coalesce(jsonRol.get(Constantes.ROL_ID).toString(), StringUtils.EMPTY);

		rol.setUsuario(CommonUtilities.coalesce(jsonRol.get(Constantes.USUARIO).toString(), StringUtils.EMPTY));

		if (idRol.equals(StringUtils.EMPTY)) {
			rolEntity.setIdRol(0);
		} else {
			rolEntity.setIdRol(Integer.parseInt(idRol));
		}

		rolEntity.setRolName(CommonUtilities.coalesce(jsonRol.get(Constantes.ROL_NAME).toString(), StringUtils.EMPTY));
		rolEntity.setRolDescription(
				CommonUtilities.coalesce(jsonRol.get(Constantes.ROL_DESCRIPTION).toString(), StringUtils.EMPTY));
		rolEntity.setBorradoRol(0);

		rol.setRol(rolEntity);

		return rol;

	}
}
