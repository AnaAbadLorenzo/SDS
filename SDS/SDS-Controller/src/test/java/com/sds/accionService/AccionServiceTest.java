package com.sds.accionService;

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
import com.sds.service.accion.AccionService;
import com.sds.service.accion.model.Accion;
import com.sds.service.common.CommonUtilities;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.AccionAsociadaRolFuncionalidadException;
import com.sds.service.exception.AccionNoExisteException;
import com.sds.service.exception.AccionYaExisteException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.RolNoExisteException;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SDSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccionServiceTest {

	@Autowired
	AccionService accionService;

	@Test
	public void AccionService_buscarAccion() throws IOException, ParseException {

		final Accion accion = generateAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.BUSCAR_ACCION);
		final AccionEntity accionEntity = accion.getAccion();

		final ReturnBusquedas<AccionEntity> accionEncontrada = accionService
				.buscarAccion(accionEntity.getNombreAccion(), accionEntity.getDescripAccion(), 0, 1);

		assertNotNull(accionEncontrada.getListaBusquedas());
	}

	@Test
	public void AccionService_buscarAccionNameVacio() throws IOException, ParseException {
		final Accion accion = generateAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.ACCION_NAME_VACIO_DATA);
		final AccionEntity accionEntity = accion.getAccion();

		final ReturnBusquedas<AccionEntity> accionEncontrada = accionService
				.buscarAccion(accionEntity.getNombreAccion(), accionEntity.getDescripAccion(), 0, 1);

		assertNotNull(accionEncontrada);
	}

	@Test
	public void AccionService_buscarAccionDescriptionVacio() throws IOException, ParseException {
		final Accion accion = generateAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.ACCION_DESCRIPTION_VACIO_DATA);
		final AccionEntity accionEntity = accion.getAccion();

		final ReturnBusquedas<AccionEntity> accionEncontrada = accionService
				.buscarAccion(accionEntity.getNombreAccion(), accionEntity.getDescripAccion(), 0, 1);

		assertNotNull(accionEncontrada);
	}

	@Test
	public void AccionService_buscarAccionNameDescriptionVacios() throws IOException, ParseException {
		final Accion accion = generateAccion(Constantes.URL_JSON_ACCION_DATA,
				Constantes.ACCION_NAME_DESCRIPTION_VACIOS);
		final AccionEntity accionEntity = accion.getAccion();

		final ReturnBusquedas<AccionEntity> accionEncontrada = accionService
				.buscarAccion(accionEntity.getNombreAccion(), accionEntity.getDescripAccion(), 0, 1);

		assertNotNull(accionEncontrada);
	}

	@Test
	public void AccionService_buscarTodos() throws IOException, ParseException {

		final ReturnBusquedas<AccionEntity> acciones = accionService.buscarTodos(0, 3);

		assertNotNull(acciones.getListaBusquedas());
	}

	@Test
	public void AccionService_buscarAccionesEliminadas() throws IOException, ParseException {

		final ReturnBusquedas<AccionEntity> accionEncontrada = accionService.buscarAccionesEliminadas(0, 3);

		assertNotNull(accionEncontrada.getListaBusquedas());
	}

	@Test
	public void AccionService_guardarAccion() throws IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, AccionNoExisteException, AccionYaExisteException {
		final Accion accion = generateAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.GUARDAR_ACCION);

		String respuesta = StringUtils.EMPTY;

		respuesta = accionService.anadirAccion(accion);

		assertEquals(Constantes.OK, respuesta);

		final ReturnBusquedas<AccionEntity> accionDelete = accionService
				.buscarAccion(accion.getAccion().getNombreAccion(), accion.getAccion().getDescripAccion(), 0, 1);

		accion.setAccion(accionDelete.getListaBusquedas().get(0));

		accionService.deleteAccion(accion.getAccion());
	}

	@Test
	public void AccionService_guardarAccionNameVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			AccionYaExisteException, AccionNoExisteException {
		final Accion accion = generateAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.ACCION_NAME_VACIO_DATA);
		String respuesta = StringUtils.EMPTY;

		respuesta = accionService.anadirAccion(accion);

		assertEquals(CodeMessageErrors.ACCION_VACIA.name(), respuesta);

	}

	@Test
	public void AccionService_guardarAccionDescriptionVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, AccionYaExisteException {
		final Accion accion = generateAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.ACCION_DESCRIPTION_VACIO_DATA);
		String respuesta = StringUtils.EMPTY;

		respuesta = accionService.anadirAccion(accion);

		assertEquals(CodeMessageErrors.ACCION_VACIA.name(), respuesta);
	}

	@Test
	public void AccionService_guardarAccionNameDescriptionVacios() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, AccionYaExisteException {
		final Accion accion = generateAccion(Constantes.URL_JSON_ACCION_DATA,
				Constantes.ACCION_NAME_DESCRIPTION_VACIOS);
		String respuesta = StringUtils.EMPTY;

		respuesta = accionService.anadirAccion(accion);

		assertEquals(CodeMessageErrors.ACCION_VACIA.name(), respuesta);
	}

	@Test(expected = AccionYaExisteException.class)
	public void AccionService_guardarAccionYaExiste() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, AccionYaExisteException {

		final Accion accion = generateAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.ACCION_YA_EXISTE);

		accionService.anadirAccion(accion);

	}

	@Test
	public void AccionService_modificarAccion() throws IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, AccionYaExisteException, AccionNoExisteException {

		final Accion accionGuardar = generateAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.GUARDAR_ACCION);

		String respuesta = StringUtils.EMPTY;

		accionService.anadirAccion(accionGuardar);

		final ReturnBusquedas<AccionEntity> accionModificar = accionService.buscarAccion(
				accionGuardar.getAccion().getNombreAccion(), accionGuardar.getAccion().getDescripAccion(), 0, 1);

		accionModificar.getListaBusquedas().get(0).setNombreAccion("Modificación");
		accionModificar.getListaBusquedas().get(0).setDescripAccion("Hecha la modificación");

		accionGuardar.setAccion(accionModificar.getListaBusquedas().get(0));

		respuesta = accionService.modificarAccion(accionGuardar);

		assertEquals(Constantes.OK, respuesta);

		accionService.deleteAccion(accionGuardar.getAccion());

	}

	@Test
	public void AccionService_modificarAccionNameVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, AccionNoExisteException {
		final Accion accionGuardar = generateAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.ACCION_NAME_VACIO_DATA);

		String respuesta = StringUtils.EMPTY;

		respuesta = accionService.modificarAccion(accionGuardar);

		assertEquals(CodeMessageErrors.ACCION_VACIA.name(), respuesta);
	}

	@Test
	public void AccionService_modificarAccionDescriptionVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, AccionNoExisteException {
		final Accion accionGuardar = generateAccion(Constantes.URL_JSON_ACCION_DATA,
				Constantes.ACCION_DESCRIPTION_VACIO_DATA);

		String respuesta = StringUtils.EMPTY;

		respuesta = accionService.modificarAccion(accionGuardar);

		assertEquals(CodeMessageErrors.ACCION_VACIA.name(), respuesta);
	}

	@Test
	public void AccionService_modificarAccionNameDescriptionVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, AccionNoExisteException {
		final Accion accionGuardar = generateAccion(Constantes.URL_JSON_ACCION_DATA,
				Constantes.ACCION_NAME_DESCRIPTION_VACIOS);

		String respuesta = StringUtils.EMPTY;

		respuesta = accionService.modificarAccion(accionGuardar);

		assertEquals(CodeMessageErrors.ACCION_VACIA.name(), respuesta);
	}

	@Test(expected = AccionNoExisteException.class)
	public void AccionService_modificarAccionNoExiste() throws RolNoExisteException, IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, AccionNoExisteException {

		final Accion accionGuardar = generateAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.ACCION_NO_EXISTE);

		accionService.modificarAccion(accionGuardar);

	}

	@Test
	public void AccionService_eliminarAccionCorrecto()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			AccionYaExisteException, AccionNoExisteException, AccionAsociadaRolFuncionalidadException {
		final Accion accion = generateAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.GUARDAR_ACCION);

		accionService.anadirAccion(accion);

		final ReturnBusquedas<AccionEntity> accionGuardada = accionService
				.buscarAccion(accion.getAccion().getNombreAccion(), accion.getAccion().getDescripAccion(), 0, 1);

		accion.setAccion(accionGuardada.getListaBusquedas().get(0));

		final String respuesta = accionService.eliminarAccion(accion);

		assertEquals(Constantes.OK, respuesta);

		accionService.deleteAccion(accion.getAccion());

	}

	@Test(expected = AccionNoExisteException.class)
	public void AccionService_eliminarAccionNoExiste()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			AccionNoExisteException, AccionAsociadaRolFuncionalidadException {

		final Accion accion = generateAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.ELIMINAR_ACCION_NO_EXISTE);

		accionService.eliminarAccion(accion);

	}

	@Test(expected = AccionAsociadaRolFuncionalidadException.class)
	public void AccionService_eliminarAccionAsociadaRolFuncionalidad()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			AccionNoExisteException, AccionAsociadaRolFuncionalidadException {

		final Accion accion = generateAccion(Constantes.URL_JSON_ACCION_DATA,
				Constantes.ELIMINAR_ACCION_ASOCIADA_ROL_FUNCIONALIDAD);

		accionService.eliminarAccion(accion);

	}

	@Test
	public void AccionService_reactivarAccion() throws IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, AccionYaExisteException, AccionNoExisteException {

		final Accion accionGuardar = generateAccion(Constantes.URL_JSON_ACCION_DATA,
				Constantes.REACTIVAR_ACCION_CORRECTO);

		String respuesta = StringUtils.EMPTY;

		accionService.anadirAccion(accionGuardar);

		final ReturnBusquedas<AccionEntity> accionModificar = accionService.buscarAccion(
				accionGuardar.getAccion().getNombreAccion(), accionGuardar.getAccion().getDescripAccion(), 0, 1);

		accionModificar.getListaBusquedas().get(0).setBorradoAccion(0);

		accionGuardar.setAccion(accionModificar.getListaBusquedas().get(0));

		respuesta = accionService.reactivarAccion(accionGuardar);

		assertEquals(Constantes.OK, respuesta);

		accionService.deleteAccion(accionGuardar.getAccion());

	}

	@Test(expected = AccionNoExisteException.class)
	public void AccionService_reactivarAccionNoExiste() throws RolNoExisteException, IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, AccionNoExisteException {

		final Accion accionGuardar = generateAccion(Constantes.URL_JSON_ACCION_DATA, Constantes.ACCION_NO_EXISTE);

		accionService.reactivarAccion(accionGuardar);

	}

	private Accion generateAccion(final String fichero, final String nombrePrueba) throws IOException, ParseException {

		final JSONObject jsonAccion = new Util().getDatosJson(fichero, nombrePrueba);

		final Accion accion = new Accion();
		final AccionEntity accionEntity = new AccionEntity();

		final String idAccion = CommonUtilities.coalesce(jsonAccion.get(Constantes.ACCION_ID).toString(),
				StringUtils.EMPTY);

		accion.setUsuario(CommonUtilities.coalesce(jsonAccion.get(Constantes.USUARIO).toString(), StringUtils.EMPTY));

		if (idAccion.equals(StringUtils.EMPTY)) {
			accionEntity.setIdAccion(0);
		} else {
			accionEntity.setIdAccion(Integer.parseInt(idAccion));
		}

		accionEntity.setNombreAccion(
				CommonUtilities.coalesce(jsonAccion.get(Constantes.ACCION_NAME).toString(), StringUtils.EMPTY));
		accionEntity.setDescripAccion(
				CommonUtilities.coalesce(jsonAccion.get(Constantes.ACCION_DESCRIPTION).toString(), StringUtils.EMPTY));
		accionEntity.setBorradoAccion(0);

		accion.setAccion(accionEntity);

		return accion;

	}
}
