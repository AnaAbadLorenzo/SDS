package com.sds.funcionalidadService;

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
import com.sds.model.FuncionalidadEntity;
import com.sds.service.common.CommonUtilities;
import com.sds.service.common.Constantes;
import com.sds.service.exception.FuncionalidadAsociadaRolAccionException;
import com.sds.service.exception.FuncionalidadNoExisteException;
import com.sds.service.exception.FuncionalidadYaExisteException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.funcionalidad.FuncionalidadService;
import com.sds.service.funcionalidad.model.Funcionalidad;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SDSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FuncionalidadServiceTest {

	@Autowired
	FuncionalidadService funcionalidadService;

	@Test
	public void FuncionalidadService_buscarFuncionalidad()
			throws IOException, ParseException, FuncionalidadNoExisteException {

		final Funcionalidad funcionalidad = generateFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA,
				Constantes.BUSCAR_FUNCIONALIDAD);
		final FuncionalidadEntity funcionalidadEntity = funcionalidad.getFuncionalidadEntity();

		final List<FuncionalidadEntity> funcionalidadEncontrada = funcionalidadService.buscarFuncionalidad(
				funcionalidadEntity.getNombreFuncionalidad(), funcionalidadEntity.getDescripFuncionalidad());

		assertNotNull(funcionalidadEncontrada);
	}

	@Test
	public void FuncionalidadService_buscarFuncionalidadNombreVacio()
			throws IOException, ParseException, FuncionalidadNoExisteException {

		final Funcionalidad funcionalidad = generateFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA,
				Constantes.FUNCIONALIDAD_NAME_VACIO_DATA);
		final FuncionalidadEntity funcionalidadEntity = funcionalidad.getFuncionalidadEntity();

		final List<FuncionalidadEntity> funcionalidadEncontrada = funcionalidadService.buscarFuncionalidad(
				funcionalidadEntity.getNombreFuncionalidad(), funcionalidadEntity.getDescripFuncionalidad());

		assertNotNull(funcionalidadEncontrada);
	}

	@Test
	public void FuncionalidadService_buscarFuncionalidadDescriptionVacio()
			throws IOException, ParseException, FuncionalidadNoExisteException {

		final Funcionalidad funcionalidad = generateFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA,
				Constantes.FUNCIONALIDAD_DESCRIPTION_VACIO_DATA);
		final FuncionalidadEntity funcionalidadEntity = funcionalidad.getFuncionalidadEntity();

		final List<FuncionalidadEntity> funcionalidadEncontrada = funcionalidadService.buscarFuncionalidad(
				funcionalidadEntity.getNombreFuncionalidad(), funcionalidadEntity.getDescripFuncionalidad());

		assertNotNull(funcionalidadEncontrada);
	}

	@Test
	public void FuncionalidadService_buscarFuncionalidadNameDescriptionVacios()
			throws IOException, ParseException, FuncionalidadNoExisteException {

		final Funcionalidad funcionalidad = generateFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA,
				Constantes.FUNCIONALIDAD_NAME_DESCRIPTION_VACIOS);
		final FuncionalidadEntity funcionalidadEntity = funcionalidad.getFuncionalidadEntity();

		final List<FuncionalidadEntity> funcionalidadEncontrada = funcionalidadService.buscarFuncionalidad(
				funcionalidadEntity.getNombreFuncionalidad(), funcionalidadEntity.getDescripFuncionalidad());

		assertNotNull(funcionalidadEncontrada);
	}

	@Test
	public void FuncionalidadService_buscarTodos() throws IOException, ParseException {

		final List<FuncionalidadEntity> funcionalidades = funcionalidadService.buscarTodos();

		assertNotNull(funcionalidades);
	}

	@Test
	public void FuncionalidadService_guardarFuncionalidad()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			FuncionalidadYaExisteException, FuncionalidadNoExisteException {
		final Funcionalidad funcionalidad = generateFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA,
				Constantes.GUARDAR_FUNCIONALIDAD);

		String respuesta = StringUtils.EMPTY;

		respuesta = funcionalidadService.anadirFuncionalidad(funcionalidad);

		assertNotNull(respuesta);

		final List<FuncionalidadEntity> funcionalidadDelete = funcionalidadService.buscarFuncionalidad(
				funcionalidad.getFuncionalidadEntity().getNombreFuncionalidad(),
				funcionalidad.getFuncionalidadEntity().getDescripFuncionalidad());

		funcionalidad.setFuncionalidadEntity(funcionalidadDelete.get(0));

		funcionalidadService.deleteFuncionalidad(funcionalidad.getFuncionalidadEntity());
	}

	@Test
	public void FuncionalidadService_guardarFuncionalidadNameVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, FuncionalidadYaExisteException {
		final Funcionalidad funcionalidad = generateFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA,
				Constantes.FUNCIONALIDAD_NAME_VACIO_DATA);
		String respuesta = StringUtils.EMPTY;

		respuesta = funcionalidadService.anadirFuncionalidad(funcionalidad);

		assertEquals(respuesta, CodeMessageErrors.FUNCIONALIDAD_VACIA.name());

	}

	@Test
	public void FuncionalidadService_guardarFuncionalidadDescriptionVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, FuncionalidadYaExisteException {
		final Funcionalidad funcionalidad = generateFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA,
				Constantes.FUNCIONALIDAD_DESCRIPTION_VACIO_DATA);
		String respuesta = StringUtils.EMPTY;

		respuesta = funcionalidadService.anadirFuncionalidad(funcionalidad);

		assertEquals(respuesta, CodeMessageErrors.FUNCIONALIDAD_VACIA.name());
	}

	@Test
	public void FuncionaldiadService_guardarFuncionalidadNameDescriptionVacios() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, FuncionalidadYaExisteException {
		final Funcionalidad funcionalidad = generateFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA,
				Constantes.FUNCIONALIDAD_NAME_DESCRIPTION_VACIOS);
		String respuesta = StringUtils.EMPTY;

		respuesta = funcionalidadService.anadirFuncionalidad(funcionalidad);

		assertEquals(respuesta, CodeMessageErrors.FUNCIONALIDAD_VACIA.name());
	}

	@Test(expected = FuncionalidadYaExisteException.class)
	public void FuncionalidadService_guardarFunciondalidadYaExiste() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, FuncionalidadYaExisteException {

		final Funcionalidad funcionalidad = generateFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA,
				Constantes.FUNCIONALIDAD_YA_EXISTE);

		funcionalidadService.anadirFuncionalidad(funcionalidad);
	}

	@Test
	public void FuncionalidadService_modificarFuncionalidad()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			FuncionalidadYaExisteException, FuncionalidadNoExisteException {

		final Funcionalidad funcionalidadGuardar = generateFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA,
				Constantes.GUARDAR_FUNCIONALIDAD);

		String respuesta = StringUtils.EMPTY;

		funcionalidadService.anadirFuncionalidad(funcionalidadGuardar);

		final List<FuncionalidadEntity> funcionalidadModificar = funcionalidadService.buscarFuncionalidad(
				funcionalidadGuardar.getFuncionalidadEntity().getNombreFuncionalidad(),
				funcionalidadGuardar.getFuncionalidadEntity().getDescripFuncionalidad());

		funcionalidadModificar.get(0).setNombreFuncionalidad("Modificación");
		funcionalidadModificar.get(0).setDescripFuncionalidad("Hecha la modificación");

		funcionalidadGuardar.setFuncionalidadEntity(funcionalidadModificar.get(0));

		respuesta = funcionalidadService.modificarFuncionalidad(funcionalidadGuardar);

		assertNotNull(respuesta);

		funcionalidadService.deleteFuncionalidad(funcionalidadGuardar.getFuncionalidadEntity());

	}

	@Test
	public void FuncionalidadService_modificarFuncionalidadNameVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, FuncionalidadNoExisteException {
		final Funcionalidad funcionalidadGuardar = generateFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA,
				Constantes.FUNCIONALIDAD_NAME_VACIO_DATA);

		String respuesta = StringUtils.EMPTY;

		respuesta = funcionalidadService.modificarFuncionalidad(funcionalidadGuardar);

		assertEquals(respuesta, CodeMessageErrors.FUNCIONALIDAD_VACIA.name());
	}

	@Test
	public void FuncionalidadService_modificarFuncionalidadDescriptionVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, FuncionalidadNoExisteException {
		final Funcionalidad funcionalidadGuardar = generateFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA,
				Constantes.FUNCIONALIDAD_DESCRIPTION_VACIO_DATA);

		String respuesta = StringUtils.EMPTY;

		respuesta = funcionalidadService.modificarFuncionalidad(funcionalidadGuardar);

		assertEquals(respuesta, CodeMessageErrors.FUNCIONALIDAD_VACIA.name());
	}

	@Test
	public void FuncionalidadService_modificarFuncionalidadNameDescriptionVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, FuncionalidadNoExisteException {
		final Funcionalidad funcionalidadGuardar = generateFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA,
				Constantes.FUNCIONALIDAD_NAME_DESCRIPTION_VACIOS);

		String respuesta = StringUtils.EMPTY;

		respuesta = funcionalidadService.modificarFuncionalidad(funcionalidadGuardar);

		assertEquals(respuesta, CodeMessageErrors.FUNCIONALIDAD_VACIA.name());
	}

	@Test(expected = FuncionalidadNoExisteException.class)
	public void FuncionalidadService_modificarFuncionalidadnNoExiste() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, FuncionalidadNoExisteException {

		final Funcionalidad funcionalidadGuardar = generateFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA,
				Constantes.FUNCIONALIDAD_NO_EXISTE);

		funcionalidadService.modificarFuncionalidad(funcionalidadGuardar);

	}

	@Test
	public void FuncionalidadService_eliminarFuncionalidadCorrecto()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			FuncionalidadYaExisteException, FuncionalidadNoExisteException, FuncionalidadAsociadaRolAccionException {
		final Funcionalidad funcionalidad = generateFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA,
				Constantes.GUARDAR_FUNCIONALIDAD);

		funcionalidadService.anadirFuncionalidad(funcionalidad);

		final List<FuncionalidadEntity> funcionalidadGuardada = funcionalidadService.buscarFuncionalidad(
				funcionalidad.getFuncionalidadEntity().getNombreFuncionalidad(),
				funcionalidad.getFuncionalidadEntity().getDescripFuncionalidad());

		funcionalidad.setFuncionalidadEntity(funcionalidadGuardada.get(0));

		final String respuesta = funcionalidadService.eliminarFuncionalidad(funcionalidad);

		assertNotNull(respuesta);

		funcionalidadService.deleteFuncionalidad(funcionalidad.getFuncionalidadEntity());

	}

	@Test(expected = FuncionalidadNoExisteException.class)
	public void FuncionalidadService_eliminarFuncionalidadNoExiste()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			FuncionalidadNoExisteException, FuncionalidadAsociadaRolAccionException {

		final Funcionalidad funcionalidad = generateFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA,
				Constantes.ELIMINAR_FUNCIONALIDAD_NO_EXISTE);

		funcionalidadService.eliminarFuncionalidad(funcionalidad);

	}

	@Test(expected = FuncionalidadAsociadaRolAccionException.class)
	public void FuncionalidadService_eliminarFuncionalidadAsociadaRolAccion()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			FuncionalidadNoExisteException, FuncionalidadAsociadaRolAccionException {

		final Funcionalidad funcionalidad = generateFuncionalidad(Constantes.URL_JSON_FUNCIONALIDAD_DATA,
				Constantes.ELIMINAR_FUNCIONALIDAD_ASOCIADA_ROL_ACCION);

		funcionalidadService.eliminarFuncionalidad(funcionalidad);

	}

	@Test
	public void FuncionalidadService_buscarFuncionalidadesEliminadas() throws IOException, ParseException {

		final List<FuncionalidadEntity> funcionalidadEncontrada = funcionalidadService
				.buscarFuncionalidadesEliminadas();

		assertNotNull(funcionalidadEncontrada);
	}

	private Funcionalidad generateFuncionalidad(final String fichero, final String nombrePrueba)
			throws IOException, ParseException {

		final JSONObject jsonFuncionalidad = new Util().getDatosJson(fichero, nombrePrueba);

		final Funcionalidad funcionalidad = new Funcionalidad();
		final FuncionalidadEntity funcionalidadEntity = new FuncionalidadEntity();

		final String idFuncionalidad = CommonUtilities
				.coalesce(jsonFuncionalidad.get(Constantes.FUNCIONALIDAD_ID).toString(), StringUtils.EMPTY);

		funcionalidad.setUsuario(
				CommonUtilities.coalesce(jsonFuncionalidad.get(Constantes.USUARIO).toString(), StringUtils.EMPTY));

		if (idFuncionalidad.equals(StringUtils.EMPTY)) {
			funcionalidadEntity.setIdFuncionalidad(0);
		} else {
			funcionalidadEntity.setIdFuncionalidad(Integer.parseInt(idFuncionalidad));
		}

		funcionalidadEntity.setNombreFuncionalidad(CommonUtilities
				.coalesce(jsonFuncionalidad.get(Constantes.FUNCIONALIDAD_NAME).toString(), StringUtils.EMPTY));
		funcionalidadEntity.setDescripFuncionalidad(CommonUtilities
				.coalesce(jsonFuncionalidad.get(Constantes.FUNCIONALIDAD_DESCRIPTION).toString(), StringUtils.EMPTY));
		funcionalidadEntity.setBorradoFuncionalidad(0);

		funcionalidad.setFuncionalidadEntity(funcionalidadEntity);

		return funcionalidad;

	}
}
