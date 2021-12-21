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
import com.sds.model.RolEntity;
import com.sds.service.common.CommonUtilities;
import com.sds.service.common.Constantes;
import com.sds.service.exception.RolNoExisteException;
import com.sds.service.rol.RolService;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SDSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RolServiceTest {

	@Autowired
	RolService rolService;

	@Test
	public void RolService_buscarRol() throws RolNoExisteException, IOException, ParseException {

		final RolEntity rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.BUSCAR_ROL);

		final RolEntity rolEncontrado = rolService.buscarRol(rol.getRolName());

		assertNotNull(rolEncontrado);
	}

	@Test
	public void RolService_NombreVacio() throws RolNoExisteException, IOException, ParseException {
		final RolEntity rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.ROL_NAME_VACIO_DATA);
		String respuesta = StringUtils.EMPTY;

		final RolEntity rolRespuesta = rolService.buscarRol(rol.getRolName());

		if (rolRespuesta == null) {
			respuesta = CodeMessageErrors.ROL_VACIO.name();
		}
		assertEquals(respuesta, CodeMessageErrors.ROL_VACIO.name());

	}

	@Test(expected = RolNoExisteException.class)
	public void RolService_rolNoExiste() throws RolNoExisteException, IOException, ParseException {

		final RolEntity rol = generateRol(Constantes.URL_JSON_ROL_DATA, Constantes.ROL_NO_EXISTE);

		rolService.buscarRol(rol.getRolName());

	}

	@Test
	public void RolService_buscarTodos() throws IOException, ParseException {

	}

	private RolEntity generateRol(final String fichero, final String nombrePrueba) throws IOException, ParseException {

		final JSONObject jsonRolVacios = new Util().getDatosJson(fichero, nombrePrueba);

		final RolEntity rol = new RolEntity();

		rol.setRolName(CommonUtilities.coalesce(jsonRolVacios.get(Constantes.ROL_NAME).toString(), StringUtils.EMPTY));
		rol.setRolDescription(
				CommonUtilities.coalesce(jsonRolVacios.get(Constantes.ROL_DESCRIPTION).toString(), StringUtils.EMPTY));
		rol.setBorradoRol(0);

		return rol;

	}
}
