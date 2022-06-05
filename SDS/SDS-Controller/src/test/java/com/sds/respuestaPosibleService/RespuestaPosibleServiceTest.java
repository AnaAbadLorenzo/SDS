package com.sds.respuestaPosibleService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
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
import com.sds.model.ProcesoEntity;
import com.sds.model.ProcesoRespuestaPosibleEntity;
import com.sds.model.RespuestaPosibleEntity;
import com.sds.repository.ProcesoRepository;
import com.sds.repository.ProcesoRespuestaPosibleRepository;
import com.sds.service.common.CommonUtilities;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.NoticiaNoExisteException;
import com.sds.service.exception.ObjetivoNoExisteException;
import com.sds.service.exception.RespuestaPosibleAsociadaProcesoException;
import com.sds.service.exception.RespuestaPosibleNoExisteException;
import com.sds.service.exception.RespuestaPosibleYaExisteException;
import com.sds.service.exception.RolNoExisteException;
import com.sds.service.respuestaposible.RespuestaPosibleService;
import com.sds.service.respuestaposible.model.RespuestaPosible;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SDSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RespuestaPosibleServiceTest {

	@Autowired
	RespuestaPosibleService respuestaPosibleService;

	@Autowired
	ProcesoRespuestaPosibleRepository procesoRespuestaPosibleRepository;

	@Autowired
	ProcesoRepository procesoRepository;

	@Test
	public void RespuestaPosibleService_buscarRespuestaPosible()
			throws IOException, ParseException, java.text.ParseException {

		final RespuestaPosible respuestaPosible = generateRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA,
				Constantes.BUSCAR_RESPUESTA_POSIBLE);
		final RespuestaPosibleEntity respuestaPosibleEntity = respuestaPosible.getRespuestaPosibleEntity();

		final ReturnBusquedas<RespuestaPosibleEntity> respuestaPosibleEncontrada = respuestaPosibleService
				.buscarRespuestaPosible(respuestaPosibleEntity.getTextoRespuesta(), 0, 1);

		assertNotNull(respuestaPosibleEncontrada.getListaBusquedas());
	}

	@Test
	public void RespuestaPosibleService_buscarRespuestaPosibleTextoRespuestaVacio()
			throws IOException, ParseException, java.text.ParseException {

		final RespuestaPosible respuestaPosible = generateRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA,
				Constantes.TEXTO_RESPUESTA_POSIBLE_VACIO);
		final RespuestaPosibleEntity respuestaPosibleEntity = respuestaPosible.getRespuestaPosibleEntity();

		final ReturnBusquedas<RespuestaPosibleEntity> respuestaPosibleEncontrada = respuestaPosibleService
				.buscarRespuestaPosible(respuestaPosibleEntity.getTextoRespuesta(), 0, 1);

		assertNotNull(respuestaPosibleEncontrada.getListaBusquedas());
	}

	@Test
	public void RespuestaPosibleService_guardarRespuestaPosible()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			java.text.ParseException, RespuestaPosibleYaExisteException, RespuestaPosibleNoExisteException {
		final RespuestaPosible respuestaPosible = generateRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA,
				Constantes.GUARDAR_RESPUESTA_POSIBLE);

		String respuesta = StringUtils.EMPTY;

		respuesta = respuestaPosibleService.anadirRespuestaPosible(respuestaPosible);

		assertEquals(Constantes.OK, respuesta);

		final ReturnBusquedas<RespuestaPosibleEntity> respuestaPosibleEncontrada = respuestaPosibleService
				.buscarRespuestaPosible(respuestaPosible.getRespuestaPosibleEntity().getTextoRespuesta(), 0, 1);

		respuestaPosibleService.deleteRespuestaPosible(respuestaPosibleEncontrada.getListaBusquedas().get(0));
	}

	@Test
	public void RespuestaPosibleService_guardarRespuestaPosibleTextoRespuestaPosibleVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			java.text.ParseException, RespuestaPosibleYaExisteException {
		final RespuestaPosible respuestaPosible = generateRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA,
				Constantes.TEXTO_RESPUESTA_POSIBLE_VACIO);

		String respuesta = StringUtils.EMPTY;

		respuesta = respuestaPosibleService.anadirRespuestaPosible(respuestaPosible);

		assertEquals(CodeMessageErrors.RESPUESTA_POSIBLE_VACIA.name(), respuesta);

	}

	@Test(expected = RespuestaPosibleYaExisteException.class)
	public void RespuestaPosibleService_guardarRespuestaPosibleYaExiste()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			java.text.ParseException, RespuestaPosibleYaExisteException, RespuestaPosibleNoExisteException {

		final RespuestaPosible respuestaPosible = generateRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA,
				Constantes.RESPUESTA_POSIBLE_YA_EXISTE);

		respuestaPosibleService.anadirRespuestaPosible(respuestaPosible);

		try {
			respuestaPosibleService.anadirRespuestaPosible(respuestaPosible);
		} catch (final RespuestaPosibleYaExisteException respuestaPosibleYaExisteException) {
			throw new RespuestaPosibleYaExisteException(
					CodeMessageErrors.RESPUESTA_POSIBLE_YA_EXISTE_EXCEPTION.getCodigo(), CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.RESPUESTA_POSIBLE_YA_EXISTE_EXCEPTION.getCodigo()));
		} finally {
			final ReturnBusquedas<RespuestaPosibleEntity> respuestaPosibleDelete = respuestaPosibleService
					.buscarRespuestaPosible(respuestaPosible.getRespuestaPosibleEntity().getTextoRespuesta(), 0, 1);
			respuestaPosibleService.deleteRespuestaPosible(respuestaPosibleDelete.getListaBusquedas().get(0));
		}
	}

	@Test
	public void RespuestaPosibleService_modificarRespuestaPosible()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			java.text.ParseException, RespuestaPosibleNoExisteException, RespuestaPosibleYaExisteException {

		final RespuestaPosible respuestaPosible = generateRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA,
				Constantes.MODIFICAR_RESPUESTA_POSIBLE);

		String respuesta = StringUtils.EMPTY;

		respuestaPosibleService.anadirRespuestaPosible(respuestaPosible);

		final ReturnBusquedas<RespuestaPosibleEntity> respuestaPosibleModificar = respuestaPosibleService
				.buscarRespuestaPosible(respuestaPosible.getRespuestaPosibleEntity().getTextoRespuesta(), 0, 1);

		respuestaPosibleModificar.getListaBusquedas().get(0).setTextoRespuesta("Texto respuesta");

		respuestaPosible.setRespuestaPosibleEntity(respuestaPosibleModificar.getListaBusquedas().get(0));

		respuesta = respuestaPosibleService.modificarRespuestaPosible(respuestaPosible);

		assertEquals(Constantes.OK, respuesta);

		respuestaPosibleService.deleteRespuestaPosible(respuestaPosibleModificar.getListaBusquedas().get(0));

	}

	@Test
	public void RespuestaPosibleService_modificarRespuestaPosibleTextoRespuestaPosibleVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			java.text.ParseException, RespuestaPosibleNoExisteException {

		final RespuestaPosible respuestaPosible = generateRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA,
				Constantes.TEXTO_RESPUESTA_POSIBLE_VACIO);

		String respuesta = StringUtils.EMPTY;

		respuesta = respuestaPosibleService.modificarRespuestaPosible(respuestaPosible);

		assertEquals(CodeMessageErrors.RESPUESTA_POSIBLE_VACIA.name(), respuesta);
	}

	@Test(expected = RespuestaPosibleNoExisteException.class)
	public void RespuestaPosibleService_modificarRespuestaPosibleNoExiste()
			throws RolNoExisteException, IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, java.text.ParseException, RespuestaPosibleNoExisteException {

		final RespuestaPosible respuestaPosible = generateRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA,
				Constantes.RESPUESTA_POSIBLE_NO_EXISTE);

		respuestaPosibleService.modificarRespuestaPosible(respuestaPosible);

	}

	@Test
	public void RespuestaPosibleService_eliminarRespuestaPosibleCorrecto()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			java.text.ParseException, RespuestaPosibleYaExisteException, RespuestaPosibleNoExisteException,
			RespuestaPosibleAsociadaProcesoException {
		final RespuestaPosible respuestaPosible = generateRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA,
				Constantes.ELIMINAR_RESPUESTA_POSIBLE);

		respuestaPosibleService.anadirRespuestaPosible(respuestaPosible);

		final ReturnBusquedas<RespuestaPosibleEntity> respuestaPosibleEliminar = respuestaPosibleService
				.buscarRespuestaPosible(respuestaPosible.getRespuestaPosibleEntity().getTextoRespuesta(), 0, 1);

		respuestaPosibleEliminar.getListaBusquedas().get(0).setBorradoRespuesta(1);

		final String respuesta = respuestaPosibleService.eliminarRespuestaPosible(new RespuestaPosible(
				respuestaPosible.getUsuario(), respuestaPosibleEliminar.getListaBusquedas().get(0)));

		respuestaPosibleService.deleteRespuestaPosible(respuestaPosibleEliminar.getListaBusquedas().get(0));

		assertEquals(Constantes.OK, respuesta);

	}

	@Test(expected = RespuestaPosibleAsociadaProcesoException.class)
	public void RespuestaPosibleService_eliminarRespuestaPosibleAsociadaProceso()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			java.text.ParseException, RespuestaPosibleYaExisteException, RespuestaPosibleNoExisteException,
			RespuestaPosibleAsociadaProcesoException {

		String fechaProcesoString = StringUtils.EMPTY;

		final RespuestaPosible respuestaPosible = generateRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA,
				Constantes.RESPUESTA_POSIBLE_ASOCIADA_PROCESO);

		respuestaPosibleService.anadirRespuestaPosible(respuestaPosible);

		final ProcesoEntity proceso = new ProcesoEntity("Nombre proceso", "Descrip proceso", new Date(), 0);
		procesoRepository.saveAndFlush(proceso);

		final LocalDate fechaActualProceso = LocalDate.now();

		if (CommonUtilities.countDigit(fechaActualProceso.getDayOfMonth()) == 1) {
			fechaProcesoString = fechaActualProceso.getYear() + "-0" + fechaActualProceso.getMonthValue() + "-0"
					+ fechaActualProceso.getDayOfMonth();
		} else {
			fechaProcesoString = fechaActualProceso.getYear() + "-0" + fechaActualProceso.getMonthValue() + "-"
					+ fechaActualProceso.getDayOfMonth();
		}

		final ReturnBusquedas<RespuestaPosibleEntity> respuestaPosibleAsociarProceso = respuestaPosibleService
				.buscarRespuestaPosible(respuestaPosible.getRespuestaPosibleEntity().getTextoRespuesta(), 0, 1);

		final List<ProcesoEntity> procesoBuscado = procesoRepository.findProceso(proceso.getNombreProceso(),
				proceso.getDescripProceso(), fechaProcesoString);

		final ProcesoRespuestaPosibleEntity procesoRespuestaPosible = new ProcesoRespuestaPosibleEntity(
				procesoBuscado.get(0).getIdProceso(),
				respuestaPosibleAsociarProceso.getListaBusquedas().get(0).getIdRespuesta(), new Date());

		procesoRespuestaPosibleRepository.saveAndFlush(procesoRespuestaPosible);

		try {
			respuestaPosibleService.eliminarRespuestaPosible(new RespuestaPosible(respuestaPosible.getUsuario(),
					respuestaPosibleAsociarProceso.getListaBusquedas().get(0)));
		} catch (final RespuestaPosibleAsociadaProcesoException respuestaPosibleAsociadaProcesoException) {
			throw new RespuestaPosibleAsociadaProcesoException(
					CodeMessageErrors.RESPUESTA_POSIBLE_ASOCIADA_PROCESO.getCodigo(), CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.RESPUESTA_POSIBLE_ASOCIADA_PROCESO.getCodigo()));
		} finally {
			procesoRespuestaPosibleRepository.deleteProcesoRespuestaPosible(
					respuestaPosibleAsociarProceso.getListaBusquedas().get(0).getIdRespuesta(),
					procesoBuscado.get(0).getIdProceso());
			procesoRepository.deleteProceso(procesoBuscado.get(0).getIdProceso());
			respuestaPosibleService.deleteRespuestaPosible(respuestaPosibleAsociarProceso.getListaBusquedas().get(0));
		}

	}

	@Test(expected = RespuestaPosibleNoExisteException.class)
	public void RespuestaPosibleService_eliminarRespuestaPosibleNoExiste()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			java.text.ParseException, RespuestaPosibleNoExisteException, RespuestaPosibleAsociadaProcesoException {

		final RespuestaPosible respuestaPosible = generateRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA,
				Constantes.RESPUESTA_POSIBLE_NO_EXISTE);

		respuestaPosibleService.eliminarRespuestaPosible(respuestaPosible);

	}

	@Test
	public void RespuestaPosibleService_reactivarRespuestaPosibleCorrecto()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			NoticiaNoExisteException, ObjetivoNoExisteException, java.text.ParseException,
			RespuestaPosibleYaExisteException, RespuestaPosibleNoExisteException {
		final RespuestaPosible respuestaPosible = generateRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA,
				Constantes.REACTIVAR_RESPUESTA_POSIBLE);

		respuestaPosible.getRespuestaPosibleEntity().setBorradoRespuesta(1);
		respuestaPosibleService.anadirRespuestaPosible(respuestaPosible);

		final ReturnBusquedas<RespuestaPosibleEntity> respuestaPosibleReactivar = respuestaPosibleService
				.buscarRespuestaPosible(respuestaPosible.getRespuestaPosibleEntity().getTextoRespuesta(), 0, 1);

		respuestaPosibleReactivar.getListaBusquedas().get(0).setBorradoRespuesta(0);

		final String respuesta = respuestaPosibleService.reactivarRespuestaPosible(new RespuestaPosible(
				respuestaPosible.getUsuario(), respuestaPosibleReactivar.getListaBusquedas().get(0)));

		respuestaPosibleService.deleteRespuestaPosible(respuestaPosibleReactivar.getListaBusquedas().get(0));
		assertEquals(Constantes.OK, respuesta);

	}

	@Test(expected = RespuestaPosibleNoExisteException.class)
	public void RespuestaPosibleService_reactivarRespuestaPosibleNoExiste()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			java.text.ParseException, RespuestaPosibleNoExisteException {

		final RespuestaPosible respuestaPosible = generateRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA,
				Constantes.RESPUESTA_POSIBLE_NO_EXISTE);

		respuestaPosibleService.reactivarRespuestaPosible(respuestaPosible);

	}

	private RespuestaPosible generateRespuestaPosible(final String fichero, final String nombrePrueba)
			throws IOException, ParseException, java.text.ParseException {

		final JSONObject jsonRespuestaPosibleVacios = new Util().getDatosJson(fichero, nombrePrueba);

		final RespuestaPosible respuestaPosible = new RespuestaPosible();
		final RespuestaPosibleEntity respuestaPosibleEntity = new RespuestaPosibleEntity();
		final String idRespuestaPosible = CommonUtilities
				.coalesce(jsonRespuestaPosibleVacios.get(Constantes.RESPUESTA_POSIBLE_ID).toString(), StringUtils.EMPTY)
				.toString();

		if (idRespuestaPosible.equals(StringUtils.EMPTY)) {
			respuestaPosibleEntity.setIdRespuesta(0);
		} else {
			respuestaPosibleEntity.setIdRespuesta(Integer.parseInt(idRespuestaPosible));
		}

		respuestaPosibleEntity.setTextoRespuesta(CommonUtilities.coalesce(
				jsonRespuestaPosibleVacios.get(Constantes.TEXTO_RESPUESTA_POSIBLE).toString(), StringUtils.EMPTY));
		respuestaPosible.setUsuario(CommonUtilities
				.coalesce(jsonRespuestaPosibleVacios.get(Constantes.USUARIO).toString(), StringUtils.EMPTY));
		respuestaPosible.setRespuestaPosibleEntity(respuestaPosibleEntity);

		return respuestaPosible;

	}
}
