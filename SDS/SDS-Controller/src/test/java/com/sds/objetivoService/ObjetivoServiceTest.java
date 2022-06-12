package com.sds.objetivoService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sds.app.SDSApplication;
import com.sds.model.ObjetivoEntity;
import com.sds.model.PlanEntity;
import com.sds.repository.PlanRepository;
import com.sds.service.common.CommonUtilities;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.ObjetivoAsociadoPlanException;
import com.sds.service.exception.ObjetivoAsociadoProcesoException;
import com.sds.service.exception.ObjetivoNoExisteException;
import com.sds.service.exception.ObjetivoYaExisteException;
import com.sds.service.exception.RolNoExisteException;
import com.sds.service.objetivo.ObjetivoService;
import com.sds.service.objetivo.model.Objetivo;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SDSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ObjetivoServiceTest {

	@Autowired
	ObjetivoService objetivoService;

	@Autowired
	PlanRepository planRepository;

	@Test
	public void ObjetivoService_buscarObjetivo() throws IOException, ParseException {

		final Objetivo objetivo = generateObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.BUSCAR_OBJETIVO);
		final ObjetivoEntity objetivoEntity = objetivo.getObjetivo();

		final ReturnBusquedas<ObjetivoEntity> objetivoEncontrado = objetivoService
				.buscarObjetivo(objetivoEntity.getNombreObjetivo(), objetivoEntity.getDescripObjetivo(), 0, 1);

		assertNotNull(objetivoEncontrado.getListaBusquedas());
	}

	@Test
	public void ObjetivoService_buscarObjetivoNombreVacio() throws IOException, ParseException {

		final Objetivo objetivo = generateObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.NOMBRE_OBJETIVO_VACIO);
		final ObjetivoEntity objetivoEntity = objetivo.getObjetivo();

		final ReturnBusquedas<ObjetivoEntity> objetivoEncontrado = objetivoService
				.buscarObjetivo(objetivoEntity.getNombreObjetivo(), objetivoEntity.getDescripObjetivo(), 0, 1);

		assertNotNull(objetivoEncontrado.getListaBusquedas());
	}

	@Test
	public void ObjetivoService_buscarObjetivoDescripcionVacio() throws IOException, ParseException {

		final Objetivo objetivo = generateObjetivo(Constantes.URL_JSON_OBJETIVO_DATA,
				Constantes.DESCRIPCION_OBJETIVO_VACIO);
		final ObjetivoEntity objetivoEntity = objetivo.getObjetivo();

		final ReturnBusquedas<ObjetivoEntity> objetivoEncontrado = objetivoService
				.buscarObjetivo(objetivoEntity.getNombreObjetivo(), objetivoEntity.getDescripObjetivo(), 0, 1);

		assertNotNull(objetivoEncontrado.getListaBusquedas());
	}

	@Test
	public void ObjetivoService_buscarObjetivoDatosObjetivoVacios() throws IOException, ParseException {

		final Objetivo objetivo = generateObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.DATOS_OBJETIVO_VACIOS);
		final ObjetivoEntity objetivoEntity = objetivo.getObjetivo();

		final ReturnBusquedas<ObjetivoEntity> objetivoEncontrado = objetivoService
				.buscarObjetivo(objetivoEntity.getNombreObjetivo(), objetivoEntity.getDescripObjetivo(), 0, 1);

		assertNotNull(objetivoEncontrado.getListaBusquedas());
	}

	@Test
	public void ObjetivoService_guardarObjetivo() throws IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, ObjetivoYaExisteException, ObjetivoNoExisteException {
		final Objetivo objetivo = generateObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.GUARDAR_OBJETIVO);

		String respuesta = StringUtils.EMPTY;

		respuesta = objetivoService.anadirObjetivo(objetivo);

		assertEquals(Constantes.OK, respuesta);

		final ReturnBusquedas<ObjetivoEntity> objetivoEncontrado = objetivoService.buscarObjetivo(
				objetivo.getObjetivo().getNombreObjetivo(), objetivo.getObjetivo().getDescripObjetivo(), 0, 1);

		objetivoService.deleteObjetivo(objetivoEncontrado.getListaBusquedas().get(0));
	}

	@Test
	public void ObjetivoService_guardarObjetivoNombreObjetivoVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ObjetivoYaExisteException, ObjetivoNoExisteException {
		final Objetivo objetivo = generateObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.NOMBRE_OBJETIVO_VACIO);
		String respuesta = StringUtils.EMPTY;

		respuesta = objetivoService.anadirObjetivo(objetivo);

		assertEquals(CodeMessageErrors.OBJETIVO_VACIO.name(), respuesta);

	}

	@Test
	public void ObjetivoService_guardarObjetivoDescripcionVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ObjetivoYaExisteException, ObjetivoNoExisteException {
		final Objetivo objetivo = generateObjetivo(Constantes.URL_JSON_OBJETIVO_DATA,
				Constantes.DESCRIPCION_OBJETIVO_VACIO);
		String respuesta = StringUtils.EMPTY;

		respuesta = objetivoService.anadirObjetivo(objetivo);

		assertEquals(CodeMessageErrors.OBJETIVO_VACIO.name(), respuesta);
	}

	@Test
	public void ObjetivoService_guardarObjetivoDatosVacios()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ObjetivoYaExisteException, ObjetivoNoExisteException {
		final Objetivo objetivo = generateObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.DATOS_OBJETIVO_VACIOS);
		String respuesta = StringUtils.EMPTY;

		respuesta = objetivoService.anadirObjetivo(objetivo);

		assertEquals(CodeMessageErrors.OBJETIVO_VACIO.name(), respuesta);
	}

	@Test(expected = ObjetivoYaExisteException.class)
	public void ObjetivoService_guardarObjetivoYaExiste()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ObjetivoYaExisteException, ObjetivoNoExisteException {

		final Objetivo objetivo = generateObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.OBJETIVO_YA_EXISTE);

		objetivoService.anadirObjetivo(objetivo);

		try {
			objetivoService.anadirObjetivo(objetivo);
		} catch (final ObjetivoYaExisteException objetivoYaExisteException) {
			throw new ObjetivoYaExisteException(CodeMessageErrors.OBJETIVO_YA_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.OBJETIVO_YA_EXISTE_EXCEPTION.getCodigo()));
		} finally {

			final ReturnBusquedas<ObjetivoEntity> objetivoDelete = objetivoService.buscarObjetivo(
					objetivo.getObjetivo().getNombreObjetivo(), objetivo.getObjetivo().getNombreObjetivo(), 0, 1);
			objetivoService.deleteObjetivo(objetivoDelete.getListaBusquedas().get(0));
		}
	}

	@Test
	public void ObjetivoService_modificarObjetivo() throws IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, ObjetivoYaExisteException, ObjetivoNoExisteException {

		final Objetivo objetivo = generateObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.MODIFICAR_OBJETIVO);

		String respuesta = StringUtils.EMPTY;

		objetivoService.anadirObjetivo(objetivo);

		final ReturnBusquedas<ObjetivoEntity> objetivoModificar = objetivoService.buscarObjetivo(
				objetivo.getObjetivo().getNombreObjetivo(), objetivo.getObjetivo().getDescripObjetivo(), 0, 1);

		objetivoModificar.getListaBusquedas().get(0).setDescripObjetivo("Descripcion modificada");

		objetivo.setObjetivo(objetivoModificar.getListaBusquedas().get(0));

		respuesta = objetivoService.modificarObjetivo(objetivo);

		assertEquals(Constantes.OK, respuesta);

		objetivoService.deleteObjetivo(objetivoModificar.getListaBusquedas().get(0));

	}

	@Test
	public void ObjetivoService_modificarObjetivoNombreVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, ObjetivoNoExisteException {

		final Objetivo objetivo = generateObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.NOMBRE_OBJETIVO_VACIO);

		String respuesta = StringUtils.EMPTY;

		respuesta = objetivoService.modificarObjetivo(objetivo);

		assertEquals(CodeMessageErrors.OBJETIVO_VACIO.name(), respuesta);
	}

	@Test
	public void ObjetivoService_modificarObjetivoDescripcionVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, ObjetivoNoExisteException {

		final Objetivo objetivo = generateObjetivo(Constantes.URL_JSON_OBJETIVO_DATA,
				Constantes.DESCRIPCION_OBJETIVO_VACIO);

		String respuesta = StringUtils.EMPTY;

		respuesta = objetivoService.modificarObjetivo(objetivo);

		assertEquals(CodeMessageErrors.OBJETIVO_VACIO.name(), respuesta);
	}

	@Test
	public void ObjetivoService_modificarObjetivoDatosVacios() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, ObjetivoNoExisteException {
		final Objetivo objetivo = generateObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.DATOS_OBJETIVO_VACIOS);

		String respuesta = StringUtils.EMPTY;

		respuesta = objetivoService.modificarObjetivo(objetivo);

		assertEquals(CodeMessageErrors.OBJETIVO_VACIO.name(), respuesta);
	}

	@Test(expected = ObjetivoNoExisteException.class)
	public void ObjetivoService_modificarObjetivoNoExiste() throws RolNoExisteException, IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, ObjetivoNoExisteException {

		final Objetivo objetivo = generateObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.OBJETIVO_NO_EXISTE);

		objetivoService.modificarObjetivo(objetivo);

	}

	@Test
	public void ObjetivoService_eliminarObjetivoCorrecto() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, ObjetivoNoExisteException,
			ObjetivoYaExisteException, ObjetivoAsociadoPlanException, ObjetivoAsociadoProcesoException {
		final Objetivo objetivo = generateObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.ELIMINAR_OBJETIVO);

		objetivoService.anadirObjetivo(objetivo);

		final ReturnBusquedas<ObjetivoEntity> objetivoEliminar = objetivoService.buscarObjetivo(
				objetivo.getObjetivo().getNombreObjetivo(), objetivo.getObjetivo().getDescripObjetivo(), 0, 1);

		objetivoEliminar.getListaBusquedas().get(0).setBorradoObjetivo(1);

		final String respuesta = objetivoService
				.eliminaObjetivo(new Objetivo(objetivo.getUsuario(), objetivoEliminar.getListaBusquedas().get(0)));

		objetivoService.deleteObjetivo(objetivoEliminar.getListaBusquedas().get(0));

		assertEquals(Constantes.OK, respuesta);

	}

	@Test(expected = ObjetivoNoExisteException.class)
	public void ObjetivoService_eliminarObjetivoNoExiste() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, ObjetivoNoExisteException {

		final Objetivo objetivo = generateObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.OBJETIVO_NO_EXISTE);

		objetivoService.deleteObjetivo(objetivo.getObjetivo());

	}

	@Test(expected = ObjetivoAsociadoPlanException.class)
	public void ObjetivoService_eliminarObjetivoAsociadoPlan() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, ObjetivoNoExisteException,
			ObjetivoYaExisteException, ObjetivoAsociadoPlanException, ObjetivoAsociadoProcesoException {

		final Objetivo objetivo = generateObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.ELIMINAR_OBJETIVO);
		objetivoService.anadirObjetivo(objetivo);
		final ReturnBusquedas<ObjetivoEntity> objetivoEliminar = objetivoService.buscarObjetivo(
				objetivo.getObjetivo().getNombreObjetivo(), objetivo.getObjetivo().getDescripObjetivo(), 0, 1);

		final PlanEntity plan = new PlanEntity("Nombre plan", "Plan de pruebas", new Date(), 0);
		plan.setObjetivo(objetivoEliminar.getListaBusquedas().get(0));
		planRepository.saveAndFlush(plan);

		try {
			objetivoService
					.eliminaObjetivo(new Objetivo(objetivo.getUsuario(), objetivoEliminar.getListaBusquedas().get(0)));
		} catch (final ObjetivoAsociadoPlanException objetivoAsociadoPlanException) {
			throw new ObjetivoAsociadoPlanException(CodeMessageErrors.OBJETIVO_ASOCIADO_PLAN_EXCEPTION.getCodigo(),
					CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.OBJETIVO_ASOCIADO_PLAN_EXCEPTION.getCodigo()));
		} finally {
			final PlanEntity planDelete = planRepository.findPlanByName(plan.getNombrePlan());

			planRepository.deletePlan(planDelete.getIdPlan());
			objetivoService.deleteObjetivo(objetivoEliminar.getListaBusquedas().get(0));
		}

	}

	@Test
	public void ObjetivoService_reactivarObjetivoCorrecto()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ObjetivoNoExisteException, ObjetivoYaExisteException {
		final Objetivo objetivo = generateObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.REACTIVAR_OBJETIVO);

		objetivo.getObjetivo().setBorradoObjetivo(1);
		objetivoService.anadirObjetivo(objetivo);

		final ReturnBusquedas<ObjetivoEntity> objetivoReactivar = objetivoService.buscarObjetivo(
				objetivo.getObjetivo().getNombreObjetivo(), objetivo.getObjetivo().getDescripObjetivo(), 0, 1);

		objetivoReactivar.getListaBusquedas().get(0).setBorradoObjetivo(0);

		final String respuesta = objetivoService
				.reactivarObjetivo(new Objetivo(objetivo.getUsuario(), objetivoReactivar.getListaBusquedas().get(0)));

		objetivoService.deleteObjetivo(objetivoReactivar.getListaBusquedas().get(0));

		assertEquals(Constantes.OK, respuesta);

	}

	@Test(expected = ObjetivoNoExisteException.class)
	public void ObjetivoService_reactivarObjetivoNoExiste() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, ObjetivoNoExisteException {

		final Objetivo objetivo = generateObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.REACTIVAR_OBJETIVO);

		objetivoService.reactivarObjetivo(objetivo);

	}

	private Objetivo generateObjetivo(final String fichero, final String nombrePrueba)
			throws IOException, ParseException {

		final JSONObject jsonObjetivoVacios = new Util().getDatosJson(fichero, nombrePrueba);

		final Objetivo objetivo = new Objetivo();
		final ObjetivoEntity objetivoEntity = new ObjetivoEntity();
		final String idObjetivo = CommonUtilities
				.coalesce(jsonObjetivoVacios.get(Constantes.OBJETIVO_ID).toString(), StringUtils.EMPTY).toString();

		if (idObjetivo.equals(StringUtils.EMPTY)) {
			objetivoEntity.setIdObjetivo(0);
		} else {
			objetivoEntity.setIdObjetivo(Integer.parseInt(idObjetivo));
		}

		objetivoEntity.setNombreObjetivo(CommonUtilities
				.coalesce(jsonObjetivoVacios.get(Constantes.NOMBRE_OBJETIVO).toString(), StringUtils.EMPTY));
		objetivoEntity.setDescripObjetivo(CommonUtilities
				.coalesce(jsonObjetivoVacios.get(Constantes.DESCRIPCION_OBJETIVO).toString(), StringUtils.EMPTY));
		objetivoEntity.setBorradoObjetivo(0);
		objetivo.setUsuario(
				CommonUtilities.coalesce(jsonObjetivoVacios.get(Constantes.USUARIO).toString(), StringUtils.EMPTY));
		objetivo.setObjetivo(objetivoEntity);

		return objetivo;

	}

}
