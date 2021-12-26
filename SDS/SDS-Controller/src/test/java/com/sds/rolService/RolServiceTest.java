package com.sds.rolService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sds.app.SDSApplication;
import com.sds.model.RolEntity;
import com.sds.service.common.CommonUtilities;
import com.sds.service.common.Constantes;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.RolAsociadoUsuarioException;
import com.sds.service.exception.RolNoExisteException;
import com.sds.service.exception.RolYaExisteException;
import com.sds.service.rol.RolService;
import com.sds.service.rol.model.Rol;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SDSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RolServiceTest {

	@Autowired
	RolService rolService;

	@Test
	public void RolService_buscarRol() throws RolNoExisteException, IOException, ParseException {

		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.BUSCAR_ROL);
		final RolEntity rolEntity = rol.getRol();

		final RolEntity rolEncontrado = rolService.buscarRol(rolEntity.getRolName());

		assertNotNull(rolEncontrado);
	}

	@Test
	public void RolService_NombreVacio() throws RolNoExisteException, IOException, ParseException {
		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.ROL_NAME_VACIO_DATA);
		final RolEntity rolEntity = rol.getRol();

		String respuesta = StringUtils.EMPTY;

		final RolEntity rolRespuesta = rolService.buscarRol(rolEntity.getRolName());

		if (rolRespuesta == null) {
			respuesta = CodeMessageErrors.ROL_VACIO.name();
		}
		assertEquals(respuesta, CodeMessageErrors.ROL_VACIO.name());

	}

	@Test(expected = RolNoExisteException.class)
	public void RolService_rolNoExiste() throws RolNoExisteException, IOException, ParseException {

		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.ROL_NO_EXISTE);
		final RolEntity rolEntity = rol.getRol();

		rolService.buscarRol(rolEntity.getRolName());

	}

	@Test
	public void RolService_buscarTodos() throws IOException, ParseException {

		final List<RolEntity> roles = rolService.buscarTodos();

		assertNotNull(roles);
	}

	@Test
	public void RolService_guardarRol() throws RolYaExisteException, IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {
		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.GUARDAR_ROL);

		String respuesta = StringUtils.EMPTY;

		respuesta = rolService.guardarRol(rol);

		assertNotNull(respuesta);
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
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {

		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.ROL_YA_EXISTE);

		rolService.guardarRol(rol);

	}

	@Test
	public void RolService_modificarRol() throws RolNoExisteException, IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {
		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.MODIFICAR_ROL);

		String respuesta = StringUtils.EMPTY;

		respuesta = rolService.modificarRol(rol);

		assertNotNull(respuesta);

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
			ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {
		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.ELIMINAR_ROL);

		final String respuesta = rolService.eliminarRol(rol);

		assertNotNull(respuesta);

	}

	@Test(expected = RolNoExisteException.class)
	public void RolService_eliminarRolNoExiste() throws RolNoExisteException, RolAsociadoUsuarioException, IOException,
			ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {

		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.ELIMINAR_ROL_NO_EXISTE);

		rolService.eliminarRol(rol);

	}

	@Test(expected = RolAsociadoUsuarioException.class)
	public void RolService_eliminarRolAsociadoUsuario() throws RolNoExisteException, RolAsociadoUsuarioException,
			IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException {

		final Rol rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.ELIMINAR_ROL_ASOCIADO_USUARIO);

		rolService.eliminarRol(rol);

	}

	@Test
	public void RolService_buscarRolesEliminados() throws RolNoExisteException, IOException, ParseException {

		final List<RolEntity> rolEncontrado = rolService.buscarRolesEliminados();

		assertNotNull(rolEncontrado);
	}

	private Rol generateRol(final String fichero, final String nombrePrueba) throws IOException, ParseException {

		final JSONObject jsonRol = new Util().getDatosJson(fichero, nombrePrueba);

		final Rol rol = new Rol();
		final RolEntity rolEntity = new RolEntity();

		final String idRol = CommonUtilities.coalesce(jsonRol.get(Constantes.ROL_ID).toString(), StringUtils.EMPTY);

		rol.setUsuario(CommonUtilities.coalesce(jsonRol.get(Constantes.USUARIO).toString(), StringUtils.EMPTY));

		if (idRol.equals("")) {
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
