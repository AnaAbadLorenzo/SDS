package com.sds.procesoService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
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
import com.sds.model.ObjetivoEntity;
import com.sds.model.PersonaEntity;
import com.sds.model.PlanEntity;
import com.sds.model.ProcedimientoEntity;
import com.sds.model.ProcedimientoUsuarioEntity;
import com.sds.model.ProcedimientoUsuarioProcesoEntity;
import com.sds.model.ProcesoEntity;
import com.sds.model.RespuestaPosibleEntity;
import com.sds.model.RolEntity;
import com.sds.model.UsuarioEntity;
import com.sds.repository.NivelRepository;
import com.sds.repository.ObjetivoRepository;
import com.sds.repository.PersonaRepository;
import com.sds.repository.PlanRepository;
import com.sds.repository.ProcedimientoRepository;
import com.sds.repository.ProcedimientoUsuarioProcesoRepository;
import com.sds.repository.ProcedimientoUsuarioRepository;
import com.sds.repository.ProcesoProcedimientoRepository;
import com.sds.repository.ProcesoRepository;
import com.sds.repository.ProcesoRespuestaPosibleRepository;
import com.sds.repository.RespuestaPosibleRepository;
import com.sds.repository.UsuarioRepository;
import com.sds.service.common.CommonUtilities;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.FechaAnteriorFechaActualException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.NivelYaExisteException;
import com.sds.service.exception.ObjetivoNoExisteException;
import com.sds.service.exception.OrdenProcesoIncorrectoException;
import com.sds.service.exception.ProcedimientoAsociadoProcesoException;
import com.sds.service.exception.ProcedimientoNoExisteException;
import com.sds.service.exception.ProcesoAsociadoObjetivoException;
import com.sds.service.exception.ProcesoAsociadoRespuestaPosibleException;
import com.sds.service.exception.ProcesoAsociadoUsuarioProcedimientoException;
import com.sds.service.exception.ProcesoNoExisteException;
import com.sds.service.exception.ProcesoProcedimientoNoExisteException;
import com.sds.service.exception.ProcesoProcedimientoYaExisteException;
import com.sds.service.exception.ProcesoRespuestaPosibleYaExisteException;
import com.sds.service.exception.ProcesoYaExisteException;
import com.sds.service.exception.RespuestaPosibleNoExisteException;
import com.sds.service.proceso.ProcesoService;
import com.sds.service.proceso.model.Proceso;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SDSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcesoServiceTest {

	@Autowired
	ProcesoService procesoService;

	@Autowired
	ProcesoRepository procesoRepository;

	@Autowired
	ProcedimientoRepository procedimientoRepository;

	@Autowired
	ObjetivoRepository objetivoRepository;

	@Autowired
	PlanRepository planRepository;

	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	ProcedimientoUsuarioProcesoRepository procedimientoUsuarioProcesoRepository;

	@Autowired
	ProcedimientoUsuarioRepository procedimientoUsuarioRepository;

	@Autowired
	ProcesoProcedimientoRepository procesoProcedimientoRepository;

	@Autowired
	RespuestaPosibleRepository respuestaPosibleRepository;

	@Autowired
	ProcesoRespuestaPosibleRepository procesoRespuestaPosibleRepository;

	@Autowired
	NivelRepository nivelRepository;

	@Test
	public void ProcesoService_buscarProceso() throws IOException, ParseException, java.text.ParseException {

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.BUSCAR_PROCESO);

		final ReturnBusquedas<ProcesoEntity> procesoEncontrado = procesoService.buscarProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0, 1);

		assertNotNull(procesoEncontrado.getListaBusquedas());
	}

	@Test
	public void ProcesoService_buscarProcesoNombreVacio() throws IOException, ParseException, java.text.ParseException {

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.NOMBRE_PROCESO_VACIO);

		final ReturnBusquedas<ProcesoEntity> procesoEncontrado = procesoService.buscarProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0, 1);

		assertNotNull(procesoEncontrado.getListaBusquedas());
	}

	@Test
	public void ProcesoService_buscarProcesoDescripVacia()
			throws IOException, ParseException, java.text.ParseException {

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.DESCRIPCION_PROCESO_VACIO);

		final ReturnBusquedas<ProcesoEntity> procesoEncontrado = procesoService.buscarProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0, 1);

		assertNotNull(procesoEncontrado.getListaBusquedas());
	}

	@Test
	public void ProcesoService_buscarProcesoFechaVacia() throws IOException, ParseException, java.text.ParseException {

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.FECHA_PROCESO_VACIA);

		final ReturnBusquedas<ProcesoEntity> procesoEncontrado = procesoService.buscarProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0, 1);

		assertNotNull(procesoEncontrado.getListaBusquedas());
	}

	@Test
	public void ProcesoService_buscarProcesoDatosVacios() throws IOException, ParseException, java.text.ParseException {

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.DATOS_PROCESO_VACIOS);

		final ReturnBusquedas<ProcesoEntity> procesoEncontrado = procesoService.buscarProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0, 1);

		assertNotNull(procesoEncontrado.getListaBusquedas());
	}

	@Test
	public void ProcesoService_guardarProceso() throws IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, ProcesoYaExisteException, java.text.ParseException,
			FechaAnteriorFechaActualException, ProcesoNoExisteException, ObjetivoNoExisteException,
			NivelYaExisteException, RespuestaPosibleNoExisteException, ProcesoRespuestaPosibleYaExisteException,
			ProcesoProcedimientoYaExisteException, ProcedimientoNoExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.GUARDAR_PROCESO);

		String respuesta = StringUtils.EMPTY;
		String dateActualPlanString = StringUtils.EMPTY;
		proceso.getProceso().setFechaProceso(new Date());
		final List<ObjetivoEntity> objetivosProceso = new ArrayList<>();
		final List<RespuestaPosibleEntity> respuestaPosibleProceso = new ArrayList<>();
		proceso.setRespuestasPosibles(respuestaPosibleProceso);
		proceso.setObjetivos(objetivosProceso);
		final List<Integer> nivelesProceso = new ArrayList<>();
		proceso.setNivel(nivelesProceso);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		final ProcedimientoEntity procedimientoEntity = new ProcedimientoEntity("Nombre procedimiento",
				"Descrip procedimiento", new Date(), 0, Boolean.FALSE, plan);
		final List<ProcedimientoEntity> procedimiento = new ArrayList<>();
		procedimiento.add(procedimientoEntity);
		final List<Integer> orden = new ArrayList<>();
		orden.add(1);
		proceso.setProcedimientos(procedimiento);
		proceso.setOrdenProceso(orden);
		procedimientoRepository.saveAndFlush(procedimientoEntity);
		respuesta = procesoService.anadirProceso(proceso);

		assertEquals(Constantes.OK, respuesta);

		final ReturnBusquedas<ProcesoEntity> procesoEncontrado = procesoService.buscarProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0, 1);

		final LocalDate dateActualPlan = plan.getFechaPlan().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		if (CommonUtilities.countDigit(dateActualPlan.getDayOfMonth()) == 1) {
			dateActualPlanString = dateActualPlan.getYear() + "-0" + dateActualPlan.getMonthValue() + "-0"
					+ dateActualPlan.getDayOfMonth();
		} else {
			dateActualPlanString = dateActualPlan.getYear() + "-0" + dateActualPlan.getMonthValue() + "-"
					+ dateActualPlan.getDayOfMonth();
		}
		final List<PlanEntity> planEncontrado = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(),
				dateActualPlanString, plan.getObjetivo());
		final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());

		final ProcedimientoEntity procedimientoEncontrado = procedimientoRepository
				.findProcedimientoByName(procedimientoEntity.getNombreProcedimiento());
		procesoProcedimientoRepository.deleteProcesoProcedimiento(
				procesoEncontrado.getListaBusquedas().get(0).getIdProceso(),
				procedimientoEncontrado.getIdProcedimiento());
		procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
		planRepository.deletePlan(planEncontrado.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());
		procesoService.deleteProceso(procesoEncontrado.getListaBusquedas().get(0));
	}

	@Test
	public void ProcesoService_guardarProcesoNombreProcesoVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException,
			ObjetivoNoExisteException, NivelYaExisteException, ProcesoNoExisteException,
			RespuestaPosibleNoExisteException, ProcesoRespuestaPosibleYaExisteException,
			ProcesoProcedimientoYaExisteException, ProcedimientoNoExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.NOMBRE_PROCESO_VACIO);

		String respuesta = StringUtils.EMPTY;
		respuesta = procesoService.anadirProceso(proceso);

		assertEquals(CodeMessageErrors.PROCESO_VACIO.name(), respuesta);
	}

	@Test
	public void ProcesoService_guardarProcesoDescripcionProcesoVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException,
			ObjetivoNoExisteException, NivelYaExisteException, ProcesoNoExisteException,
			RespuestaPosibleNoExisteException, ProcesoRespuestaPosibleYaExisteException,
			ProcesoProcedimientoYaExisteException, ProcedimientoNoExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.DESCRIPCION_PROCESO_VACIO);

		String respuesta = StringUtils.EMPTY;
		respuesta = procesoService.anadirProceso(proceso);

		assertEquals(CodeMessageErrors.PROCESO_VACIO.name(), respuesta);

	}

	@Test
	public void ProcesoService_guardarProcesoFechaProcesoVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException,
			ObjetivoNoExisteException, NivelYaExisteException, ProcesoNoExisteException,
			RespuestaPosibleNoExisteException, ProcesoRespuestaPosibleYaExisteException,
			ProcesoProcedimientoYaExisteException, ProcedimientoNoExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.FECHA_PROCESO_VACIA);

		String respuesta = StringUtils.EMPTY;
		respuesta = procesoService.anadirProceso(proceso);

		assertEquals(CodeMessageErrors.PROCESO_VACIO.name(), respuesta);

	}

	@Test
	public void ProcesoService_guardarProcesoDatosProcesoVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException,
			ObjetivoNoExisteException, NivelYaExisteException, ProcesoNoExisteException,
			RespuestaPosibleNoExisteException, ProcesoRespuestaPosibleYaExisteException,
			ProcesoProcedimientoYaExisteException, ProcedimientoNoExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.DATOS_PROCESO_VACIOS);

		String respuesta = StringUtils.EMPTY;
		respuesta = procesoService.anadirProceso(proceso);

		assertEquals(CodeMessageErrors.PROCESO_VACIO.name(), respuesta);

	}

	@Test(expected = ProcesoYaExisteException.class)
	public void ProcesoService_guardarProcesoYaExiste()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoNoExisteException, java.text.ParseException, ProcesoYaExisteException,
			FechaAnteriorFechaActualException, ObjetivoNoExisteException, NivelYaExisteException,
			RespuestaPosibleNoExisteException, ProcesoRespuestaPosibleYaExisteException,
			ProcesoProcedimientoYaExisteException, ProcedimientoNoExisteException {
		String dateActualPlanString = StringUtils.EMPTY;

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.PROCESO_YA_EXISTE);

		proceso.getProceso().setFechaProceso(new Date());
		final List<ObjetivoEntity> objetivosProceso = new ArrayList<>();
		proceso.setObjetivos(objetivosProceso);
		final List<RespuestaPosibleEntity> respuestaPosibleProceso = new ArrayList<>();
		proceso.setRespuestasPosibles(respuestaPosibleProceso);
		final List<Integer> nivelesProceso = new ArrayList<>();
		proceso.setNivel(nivelesProceso);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		final ProcedimientoEntity procedimientoEntity = new ProcedimientoEntity("Nombre procedimiento",
				"Descrip procedimiento", new Date(), 0, Boolean.FALSE, plan);
		procedimientoRepository.saveAndFlush(procedimientoEntity);
		final List<ProcedimientoEntity> procedimiento = new ArrayList<>();
		procedimiento.add(procedimientoEntity);
		final List<Integer> orden = new ArrayList<>();
		orden.add(1);
		proceso.setProcedimientos(procedimiento);
		proceso.setOrdenProceso(orden);
		procesoService.anadirProceso(proceso);

		try {
			procesoService.anadirProceso(proceso);
		} catch (final ProcesoYaExisteException procesoYaExisteException) {
			throw new ProcesoYaExisteException(CodeMessageErrors.PROCESO_YA_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PROCESO_YA_EXISTE_EXCEPTION.getCodigo()));
		} finally {

			final ReturnBusquedas<ProcesoEntity> procesoEncontrado = procesoService.buscarProceso(
					proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0,
					1);

			final LocalDate dateActualPlan = plan.getFechaPlan().toInstant().atZone(ZoneId.systemDefault())
					.toLocalDate();
			if (CommonUtilities.countDigit(dateActualPlan.getDayOfMonth()) == 1) {
				dateActualPlanString = dateActualPlan.getYear() + "-0" + dateActualPlan.getMonthValue() + "-0"
						+ dateActualPlan.getDayOfMonth();
			} else {
				dateActualPlanString = dateActualPlan.getYear() + "-0" + dateActualPlan.getMonthValue() + "-"
						+ dateActualPlan.getDayOfMonth();
			}
			final List<PlanEntity> planEncontrado = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(),
					dateActualPlanString, plan.getObjetivo());
			final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository
					.findObjetivo(objetivo.getNombreObjetivo(), objetivo.getDescripObjetivo());

			final ProcedimientoEntity procedimientoEncontrado = procedimientoRepository
					.findProcedimientoByName(procedimientoEntity.getNombreProcedimiento());
			procesoProcedimientoRepository.deleteProcesoProcedimiento(
					procesoEncontrado.getListaBusquedas().get(0).getIdProceso(),
					procedimientoEncontrado.getIdProcedimiento());
			procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
			planRepository.deletePlan(planEncontrado.get(0).getIdPlan());
			objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

			procesoService.deleteProceso(procesoEncontrado.getListaBusquedas().get(0));
		}
	}

	@Test(expected = FechaAnteriorFechaActualException.class)
	public void PlanService_guardarProcesoFechaProcesoAnteriorActual()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			java.text.ParseException, FechaAnteriorFechaActualException, ProcesoYaExisteException,
			ProcesoNoExisteException, ObjetivoNoExisteException, NivelYaExisteException,
			RespuestaPosibleNoExisteException, ProcesoRespuestaPosibleYaExisteException,
			ProcesoProcedimientoYaExisteException, ProcedimientoNoExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA,
				Constantes.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL);
		procesoService.anadirProceso(proceso);

	}

	@Test
	public void ProcesoService_modificarProceso() throws IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, java.text.ParseException, FechaAnteriorFechaActualException,
			ProcesoYaExisteException, ProcesoNoExisteException, ProcesoAsociadoUsuarioProcedimientoException,
			ObjetivoNoExisteException, NivelYaExisteException, RespuestaPosibleNoExisteException,
			ProcesoRespuestaPosibleYaExisteException, ProcesoProcedimientoYaExisteException,
			ProcedimientoNoExisteException, OrdenProcesoIncorrectoException, ProcesoProcedimientoNoExisteException {

		String respuesta = StringUtils.EMPTY;
		String dateActualPlanString = StringUtils.EMPTY;

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.MODIFICAR_PROCESO);
		proceso.getProceso().setFechaProceso(new Date());
		final List<ObjetivoEntity> objetivosProceso = new ArrayList<>();
		proceso.setObjetivos(objetivosProceso);
		final List<RespuestaPosibleEntity> respuestaPosibleProceso = new ArrayList<>();
		proceso.setRespuestasPosibles(respuestaPosibleProceso);
		final List<Integer> nivelesProceso = new ArrayList<>();
		proceso.setNivel(nivelesProceso);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		final ProcedimientoEntity procedimientoEntity = new ProcedimientoEntity("Nombre procedimiento",
				"Descrip procedimiento", new Date(), 0, Boolean.FALSE, plan);
		final List<ProcedimientoEntity> procedimiento = new ArrayList<>();
		procedimiento.add(procedimientoEntity);
		final List<Integer> orden = new ArrayList<>();
		orden.add(1);
		proceso.setProcedimientos(procedimiento);
		proceso.setOrdenProceso(orden);
		procedimientoRepository.saveAndFlush(procedimientoEntity);
		procesoService.anadirProceso(proceso);

		final ReturnBusquedas<ProcesoEntity> procesoModificar = procesoService.buscarProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0, 1);
		procesoModificar.getListaBusquedas().get(0).setDescripProceso("Descripción proceso");

		proceso.setProceso(procesoModificar.getListaBusquedas().get(0));

		respuesta = procesoService.modificarProceso(proceso);

		assertEquals(Constantes.OK, respuesta);

		final ReturnBusquedas<ProcesoEntity> procesoEncontrado = procesoService.buscarProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0, 1);

		final LocalDate dateActualPlan = plan.getFechaPlan().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		if (CommonUtilities.countDigit(dateActualPlan.getDayOfMonth()) == 1) {
			dateActualPlanString = dateActualPlan.getYear() + "-0" + dateActualPlan.getMonthValue() + "-0"
					+ dateActualPlan.getDayOfMonth();
		} else {
			dateActualPlanString = dateActualPlan.getYear() + "-0" + dateActualPlan.getMonthValue() + "-"
					+ dateActualPlan.getDayOfMonth();
		}
		final List<PlanEntity> planEncontrado = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(),
				dateActualPlanString, plan.getObjetivo());
		final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());

		final ProcedimientoEntity procedimientoEncontrado = procedimientoRepository
				.findProcedimientoByName(procedimientoEntity.getNombreProcedimiento());
		procesoProcedimientoRepository.deleteProcesoProcedimiento(
				procesoEncontrado.getListaBusquedas().get(0).getIdProceso(),
				procedimientoEncontrado.getIdProcedimiento());
		procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
		planRepository.deletePlan(planEncontrado.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());
		procesoService.deleteProceso(procesoEncontrado.getListaBusquedas().get(0));
	}

	@Test
	public void ProcesoService_modificarProcesoNombreProcesoVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException,
			ProcesoNoExisteException, ProcesoAsociadoUsuarioProcedimientoException, ObjetivoNoExisteException,
			NivelYaExisteException, ProcedimientoNoExisteException, OrdenProcesoIncorrectoException,
			RespuestaPosibleNoExisteException, ProcesoProcedimientoYaExisteException,
			ProcesoRespuestaPosibleYaExisteException, ProcesoProcedimientoNoExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.NOMBRE_PROCESO_VACIO);

		String respuesta = StringUtils.EMPTY;
		respuesta = procesoService.modificarProceso(proceso);

		assertEquals(CodeMessageErrors.PROCESO_VACIO.name(), respuesta);
	}

	@Test
	public void ProcesoService_modificarProcesoDescripcionProcesoVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, ProcesoNoExisteException,
			java.text.ParseException, FechaAnteriorFechaActualException, ProcesoAsociadoUsuarioProcedimientoException,
			ObjetivoNoExisteException, NivelYaExisteException, ProcedimientoNoExisteException,
			OrdenProcesoIncorrectoException, RespuestaPosibleNoExisteException, ProcesoProcedimientoYaExisteException,
			ProcesoRespuestaPosibleYaExisteException, ProcesoProcedimientoNoExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.DESCRIPCION_PROCESO_VACIO);

		String respuesta = StringUtils.EMPTY;
		respuesta = procesoService.modificarProceso(proceso);

		assertEquals(CodeMessageErrors.PROCESO_VACIO.name(), respuesta);

	}

	@Test
	public void ProcesoService_modificarProcesoFechaProcesoVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, ProcesoNoExisteException,
			java.text.ParseException, FechaAnteriorFechaActualException, ProcesoAsociadoUsuarioProcedimientoException,
			ObjetivoNoExisteException, NivelYaExisteException, ProcedimientoNoExisteException,
			OrdenProcesoIncorrectoException, RespuestaPosibleNoExisteException, ProcesoProcedimientoYaExisteException,
			ProcesoRespuestaPosibleYaExisteException, ProcesoProcedimientoNoExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.FECHA_PROCESO_VACIA);

		String respuesta = StringUtils.EMPTY;
		respuesta = procesoService.modificarProceso(proceso);

		assertEquals(CodeMessageErrors.PROCESO_VACIO.name(), respuesta);

	}

	@Test
	public void ProcesoService_modificarProcesoDatosProcesoVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, ProcesoNoExisteException,
			java.text.ParseException, FechaAnteriorFechaActualException, ProcesoAsociadoUsuarioProcedimientoException,
			ObjetivoNoExisteException, NivelYaExisteException, ProcedimientoNoExisteException,
			OrdenProcesoIncorrectoException, RespuestaPosibleNoExisteException, ProcesoProcedimientoYaExisteException,
			ProcesoRespuestaPosibleYaExisteException, ProcesoProcedimientoNoExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.DATOS_PROCESO_VACIOS);

		String respuesta = StringUtils.EMPTY;
		respuesta = procesoService.modificarProceso(proceso);

		assertEquals(CodeMessageErrors.PROCESO_VACIO.name(), respuesta);

	}

	@Test(expected = ProcesoNoExisteException.class)
	public void ProcesoService_modificarProcesoNoExiste()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoNoExisteException, java.text.ParseException, ProcesoYaExisteException,
			FechaAnteriorFechaActualException, ProcesoAsociadoUsuarioProcedimientoException, ObjetivoNoExisteException,
			NivelYaExisteException, ProcedimientoNoExisteException, OrdenProcesoIncorrectoException,
			RespuestaPosibleNoExisteException, ProcesoProcedimientoYaExisteException,
			ProcesoRespuestaPosibleYaExisteException, ProcesoProcedimientoNoExisteException {

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.PROCESO_NO_EXISTE);

		procesoService.modificarProceso(proceso);

	}

	@Test(expected = ProcesoAsociadoUsuarioProcedimientoException.class)
	public void ProcesoService_modificarProcesoAsociadoUsuarioProcedimiento()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoNoExisteException, java.text.ParseException, ProcesoYaExisteException,
			FechaAnteriorFechaActualException, ProcesoAsociadoUsuarioProcedimientoException, ObjetivoNoExisteException,
			NivelYaExisteException, RespuestaPosibleNoExisteException, ProcesoRespuestaPosibleYaExisteException,
			ProcesoProcedimientoYaExisteException, ProcedimientoNoExisteException, OrdenProcesoIncorrectoException,
			ProcesoProcedimientoNoExisteException {

		final String resultado = StringUtils.EMPTY;
		String dateActualPlanString = StringUtils.EMPTY;
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		final ObjetivoEntity objetivo = new ObjetivoEntity("Objetivo", "Objetivo de pruebas", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		final ObjetivoEntity objetivoBDNuevo = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());
		plan.setObjetivo(objetivoBDNuevo);
		planRepository.saveAndFlush(plan);
		final ProcedimientoEntity procedimientoEntity = new ProcedimientoEntity("Nombre procedimiento",
				"Descripción procedimiento", new Date(), 0, Boolean.FALSE, plan);
		final PersonaEntity persona = new PersonaEntity("04149249A", "Pepe", "Pepe pepe", format.parse("2022-02-02"),
				"Calle de prueba", "988745121", "email@email.com", 0, null);
		final RolEntity rolEntity = new RolEntity(2, "usuario",
				"Contendrá a todos los usuarios registrados de la aplicación", 0);
		final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", "pepeUsuario", 0, rolEntity);
		personaRepository.saveAndFlush(persona);
		usuarioRepository.saveAndFlush(usuario);
		procedimientoRepository.saveAndFlush(procedimientoEntity);

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.GUARDAR_PROCESO);
		proceso.getProceso().setFechaProceso(new Date());
		final List<ObjetivoEntity> objetivosProceso = new ArrayList<>();
		objetivosProceso.add(objetivoBDNuevo);
		proceso.setObjetivos(objetivosProceso);
		final List<RespuestaPosibleEntity> respuestaPosibleProceso = new ArrayList<>();
		proceso.setRespuestasPosibles(respuestaPosibleProceso);
		final List<Integer> nivelesProceso = new ArrayList<>();
		nivelesProceso.add(1);
		proceso.setNivel(nivelesProceso);
		final List<ProcedimientoEntity> procedimiento = new ArrayList<>();
		procedimiento.add(procedimientoEntity);
		final List<Integer> orden = new ArrayList<>();
		orden.add(1);
		proceso.setProcedimientos(procedimiento);
		proceso.setOrdenProceso(orden);

		procesoService.anadirProceso(proceso);

		final ProcedimientoEntity procedimientoEncontrado = procedimientoRepository
				.findProcedimientoByName(procedimientoEntity.getNombreProcedimiento());
		final List<ProcesoEntity> procesoEncontrado = procesoRepository.findProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), resultado);

		final ProcedimientoUsuarioEntity procedimientoUsuario = new ProcedimientoUsuarioEntity(0, new Date(), 0,
				procedimientoEntity, usuario);
		procedimientoUsuarioRepository.saveAndFlush(procedimientoUsuario);

		final RespuestaPosibleEntity respuestaPosible = new RespuestaPosibleEntity("Respuesta posible", 0);
		respuestaPosibleRepository.saveAndFlush(respuestaPosible);
		final RespuestaPosibleEntity respuestaPosibleEncontrada = respuestaPosibleRepository
				.findRespuestaPosibleByText(respuestaPosible.getTextoRespuesta());

		final List<ProcedimientoUsuarioEntity> procedimientoUsuarioEncontrado = procedimientoUsuarioRepository
				.findProcedimientoUsuarioByProcedimientoAndUsuario(usuario, procedimientoEncontrado);
		final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProceso = new ProcedimientoUsuarioProcesoEntity(
				new Date(), 0, respuestaPosibleEncontrada, procesoEncontrado.get(0),
				procedimientoUsuarioEncontrado.get(0));

		procedimientoUsuarioProcesoRepository.saveAndFlush(procedimientoUsuarioProceso);

		final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProcesoBD = procedimientoUsuarioProcesoRepository
				.findProcedimientoUsuarioProceso(procedimientoUsuarioEncontrado.get(0), procesoEncontrado.get(0));

		try {
			procesoService.modificarProceso(new Proceso(proceso.getUsuario(), procesoEncontrado.get(0)));
		} catch (final ProcesoAsociadoUsuarioProcedimientoException procesoAsociadoUsuarioProcedimientoException) {
			throw new ProcesoAsociadoUsuarioProcedimientoException(
					CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_USUARIO_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(
							CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_USUARIO_EXCEPTION.getCodigo()));
		} finally {

			final LocalDate dateActualPlan = plan.getFechaPlan().toInstant().atZone(ZoneId.systemDefault())
					.toLocalDate();
			if (CommonUtilities.countDigit(dateActualPlan.getDayOfMonth()) == 1) {
				dateActualPlanString = dateActualPlan.getYear() + "-0" + dateActualPlan.getMonthValue() + "-0"
						+ dateActualPlan.getDayOfMonth();
			} else {
				dateActualPlanString = dateActualPlan.getYear() + "-0" + dateActualPlan.getMonthValue() + "-"
						+ dateActualPlan.getDayOfMonth();
			}
			final List<PlanEntity> planEncontrado = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(),
					dateActualPlanString, plan.getObjetivo());
			final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository
					.findObjetivo(objetivo.getNombreObjetivo(), objetivo.getDescripObjetivo());

			procesoProcedimientoRepository.deleteProcesoProcedimiento(procesoEncontrado.get(0).getIdProceso(),
					procedimientoEncontrado.getIdProcedimiento());
			procedimientoUsuarioProcesoRepository.deleteProcedimientoUsuarioProceso(
					procedimientoUsuarioProcesoBD.getIdProcedimientoUsuarioProceso());
			procedimientoUsuarioRepository
					.deleteProcedimientoUsuario(procedimientoUsuarioEncontrado.get(0).getIdProcedimientoUsuario());
			procesoRepository.deleteProceso(procesoEncontrado.get(0).getIdProceso());
			procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
			planRepository.deletePlan(planEncontrado.get(0).getIdPlan());
			objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());
			respuestaPosibleRepository.deleteRespuestaPosible(respuestaPosibleEncontrada.getIdRespuesta());
			usuarioRepository.deleteUsuario(usuario.getDniUsuario());
			personaRepository.deletePersona(persona.getDniP());
		}

	}

	@Test
	public void ProcesoService_eliminarProcesoCorrecto() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, ProcesoNoExisteException,
			java.text.ParseException, ProcesoYaExisteException, FechaAnteriorFechaActualException,
			ProcedimientoAsociadoProcesoException, ProcesoAsociadoRespuestaPosibleException,
			ProcesoAsociadoUsuarioProcedimientoException, ProcesoAsociadoObjetivoException, ObjetivoNoExisteException,
			NivelYaExisteException, RespuestaPosibleNoExisteException, ProcesoRespuestaPosibleYaExisteException,
			ProcesoProcedimientoYaExisteException, ProcedimientoNoExisteException, OrdenProcesoIncorrectoException,
			ProcesoProcedimientoNoExisteException {
		String dateActualPlanString = StringUtils.EMPTY;
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.ELIMINAR_PROCESO);
		proceso.getProceso().setFechaProceso(new Date());
		final List<ObjetivoEntity> objetivosProceso = new ArrayList<>();
		final List<RespuestaPosibleEntity> respuestaPosibleProceso = new ArrayList<>();
		proceso.setRespuestasPosibles(respuestaPosibleProceso);
		final List<Integer> nivelesProceso = new ArrayList<>();
		proceso.setObjetivos(objetivosProceso);
		proceso.setNivel(nivelesProceso);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		final ProcedimientoEntity procedimientoEntity = new ProcedimientoEntity("Nombre procedimiento",
				"Descrip procedimiento", new Date(), 0, Boolean.FALSE, plan);
		final List<ProcedimientoEntity> procedimiento = new ArrayList<>();
		procedimiento.add(procedimientoEntity);
		final List<Integer> orden = new ArrayList<>();
		orden.add(1);
		proceso.setProcedimientos(procedimiento);
		proceso.setOrdenProceso(orden);
		procedimientoRepository.saveAndFlush(procedimientoEntity);
		procesoService.anadirProceso(proceso);

		final ReturnBusquedas<ProcesoEntity> procesoEliminar = procesoService.buscarProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0, 1);

		procesoEliminar.getListaBusquedas().get(0).setBorradoProceso(1);
		final Proceso procesoEliminarOk = new Proceso(proceso.getUsuario(), procesoEliminar.getListaBusquedas().get(0));
		procesoEliminarOk.setProcedimientos(procedimiento);
		procesoEliminarOk.setOrdenProceso(orden);
		procesoEliminarOk.setObjetivos(objetivosProceso);
		procesoEliminarOk.setNivel(nivelesProceso);
		procesoEliminarOk.setRespuestasPosibles(respuestaPosibleProceso);
		final String respuesta = procesoService.eliminaProceso(procesoEliminarOk);

		final LocalDate dateActualPlan = plan.getFechaPlan().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		if (CommonUtilities.countDigit(dateActualPlan.getDayOfMonth()) == 1) {
			dateActualPlanString = dateActualPlan.getYear() + "-0" + dateActualPlan.getMonthValue() + "-0"
					+ dateActualPlan.getDayOfMonth();
		} else {
			dateActualPlanString = dateActualPlan.getYear() + "-0" + dateActualPlan.getMonthValue() + "-"
					+ dateActualPlan.getDayOfMonth();
		}
		final List<PlanEntity> planEncontrado = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(),
				dateActualPlanString, plan.getObjetivo());
		final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());

		final ProcedimientoEntity procedimientoEncontrado = procedimientoRepository
				.findProcedimientoByName(procedimientoEntity.getNombreProcedimiento());
		procesoProcedimientoRepository.deleteProcesoProcedimiento(
				procesoEliminar.getListaBusquedas().get(0).getIdProceso(),
				procedimientoEncontrado.getIdProcedimiento());
		procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
		planRepository.deletePlan(planEncontrado.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());
		procesoService.deleteProceso(procesoEliminar.getListaBusquedas().get(0));

		assertEquals(Constantes.OK, respuesta);

	}

	@Test(expected = ProcesoNoExisteException.class)
	public void ProcesoService_eliminarProcesoNoExiste() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, ProcesoNoExisteException,
			java.text.ParseException, ProcesoYaExisteException, FechaAnteriorFechaActualException,
			ProcesoAsociadoUsuarioProcedimientoException, ProcedimientoAsociadoProcesoException,
			ProcesoAsociadoRespuestaPosibleException, ProcesoAsociadoObjetivoException, ObjetivoNoExisteException,
			NivelYaExisteException, ProcedimientoNoExisteException, OrdenProcesoIncorrectoException,
			RespuestaPosibleNoExisteException, ProcesoProcedimientoYaExisteException,
			ProcesoRespuestaPosibleYaExisteException, ProcesoProcedimientoNoExisteException {

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.PROCESO_NO_EXISTE);

		procesoService.eliminaProceso(proceso);

	}

	@Test(expected = ProcesoAsociadoUsuarioProcedimientoException.class)
	public void ProcesoService_eliminarProcesoProcedimientoUsuarioAsociado() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, ProcesoNoExisteException,
			java.text.ParseException, ProcesoYaExisteException, FechaAnteriorFechaActualException,
			ProcesoAsociadoUsuarioProcedimientoException, ProcedimientoAsociadoProcesoException,
			ProcesoAsociadoRespuestaPosibleException, ProcesoAsociadoObjetivoException, ObjetivoNoExisteException,
			NivelYaExisteException, RespuestaPosibleNoExisteException, ProcesoRespuestaPosibleYaExisteException,
			ProcesoProcedimientoYaExisteException, ProcedimientoNoExisteException, OrdenProcesoIncorrectoException,
			ProcesoProcedimientoNoExisteException {

		final String resultado = StringUtils.EMPTY;
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		final ObjetivoEntity objetivo = new ObjetivoEntity("Objetivo", "Objetivo de pruebas", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		final ProcedimientoEntity procedimientoEntity = new ProcedimientoEntity("Nombre procedimiento",
				"Descripción procedimiento", new Date(), 0, Boolean.FALSE, plan);
		final PersonaEntity persona = new PersonaEntity("04149249A", "Pepe", "Pepe pepe", format.parse("2022-02-02"),
				"Calle de prueba", "988745121", "email@email.com", 0, null);
		final RolEntity rolEntity = new RolEntity(2, "usuario",
				"Contendrá a todos los usuarios registrados de la aplicación", 0);
		final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", "pepeUsuario", 0, rolEntity);
		personaRepository.saveAndFlush(persona);
		usuarioRepository.saveAndFlush(usuario);
		procedimientoRepository.saveAndFlush(procedimientoEntity);

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.GUARDAR_PROCESO);
		proceso.getProceso().setFechaProceso(new Date());
		final List<ObjetivoEntity> objetivosProceso = new ArrayList<>();
		proceso.setObjetivos(objetivosProceso);
		final List<Integer> nivelesProceso = new ArrayList<>();
		proceso.setNivel(nivelesProceso);
		final List<RespuestaPosibleEntity> respuestaPosibleProceso = new ArrayList<>();
		proceso.setRespuestasPosibles(respuestaPosibleProceso);
		final List<ProcedimientoEntity> procedimiento = new ArrayList<>();
		procedimiento.add(procedimientoEntity);
		final List<Integer> orden = new ArrayList<>();
		orden.add(1);
		proceso.setProcedimientos(procedimiento);
		proceso.setOrdenProceso(orden);
		procesoService.anadirProceso(proceso);

		final ProcedimientoEntity procedimientoEncontrado = procedimientoRepository
				.findProcedimientoByName(procedimientoEntity.getNombreProcedimiento());
		final List<ProcesoEntity> procesoEncontrado = procesoRepository.findProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), resultado);
		final RespuestaPosibleEntity respuestaPosible = new RespuestaPosibleEntity("Respuesta posible", 0);
		respuestaPosibleRepository.saveAndFlush(respuestaPosible);
		final RespuestaPosibleEntity respuestaPosibleEncontrada = respuestaPosibleRepository
				.findRespuestaPosibleByText(respuestaPosible.getTextoRespuesta());

		final ProcedimientoUsuarioEntity procedimientoUsuario = new ProcedimientoUsuarioEntity(0, new Date(), 0,
				procedimientoEntity, usuario);
		procedimientoUsuarioRepository.saveAndFlush(procedimientoUsuario);

		final List<ProcedimientoUsuarioEntity> procedimientoUsuarioEncontrado = procedimientoUsuarioRepository
				.findProcedimientoUsuarioByProcedimientoAndUsuario(usuario, procedimientoEncontrado);
		final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProceso = new ProcedimientoUsuarioProcesoEntity(
				new Date(), 0, respuestaPosibleEncontrada, procesoEncontrado.get(0),
				procedimientoUsuarioEncontrado.get(0));

		procedimientoUsuarioProcesoRepository.saveAndFlush(procedimientoUsuarioProceso);

		final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProcesoBD = procedimientoUsuarioProcesoRepository
				.findProcedimientoUsuarioProceso(procedimientoUsuarioEncontrado.get(0), procesoEncontrado.get(0));

		try {
			procesoService.eliminaProceso(new Proceso(proceso.getUsuario(), procesoEncontrado.get(0)));
		} catch (final ProcesoAsociadoUsuarioProcedimientoException procesoAsociadoUsuarioProcedimientoException) {
			throw new ProcesoAsociadoUsuarioProcedimientoException(
					CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_USUARIO_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(
							CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_USUARIO_EXCEPTION.getCodigo()));
		} finally {
			procedimientoUsuarioProcesoRepository.deleteProcedimientoUsuarioProceso(
					procedimientoUsuarioProcesoBD.getIdProcedimientoUsuarioProceso());
			final PlanEntity planBDNuevo = planRepository.findPlanByName(plan.getNombrePlan());
			final ObjetivoEntity objetivoBDNuevo = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());
			procedimientoUsuarioRepository
					.deleteProcedimientoUsuario(procedimientoUsuarioEncontrado.get(0).getIdProcedimientoUsuario());
			procesoProcedimientoRepository.deleteProcesoProcedimiento(procesoEncontrado.get(0).getIdProceso(),
					procedimientoEncontrado.getIdProcedimiento());
			procesoRepository.deleteProceso(procesoEncontrado.get(0).getIdProceso());
			procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
			planRepository.deletePlan(planBDNuevo.getIdPlan());
			objetivoRepository.deleteObjetivo(objetivoBDNuevo.getIdObjetivo());
			respuestaPosibleRepository.deleteRespuestaPosible(respuestaPosibleEncontrada.getIdRespuesta());
			usuarioRepository.deleteUsuario(usuario.getDniUsuario());
			personaRepository.deletePersona(persona.getDniP());
		}
	}

	@Test
	public void ProcesoService_reactivarProcesoCorrecto()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ProcesoYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException,
			ProcesoNoExisteException, ObjetivoNoExisteException, NivelYaExisteException,
			RespuestaPosibleNoExisteException, ProcesoRespuestaPosibleYaExisteException,
			ProcesoProcedimientoYaExisteException, ProcedimientoNoExisteException {
		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.REACTIVAR_PROCESO);
		proceso.getProceso().setBorradoProceso(1);
		proceso.getProceso().setFechaProceso(new Date());
		final ObjetivoEntity objetivo = new ObjetivoEntity("Objetivo", "Objetivo de pruebas", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final ObjetivoEntity objetivoBDNuevo = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());
		proceso.setObjetivos(new ArrayList<>(objetivoBDNuevo.getIdObjetivo()));
		proceso.setNivel(new ArrayList<>(1));
		final List<RespuestaPosibleEntity> respuestaPosibleProceso = new ArrayList<>();
		proceso.setRespuestasPosibles(respuestaPosibleProceso);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		final PlanEntity planBDNuevo = planRepository.findPlanByName(plan.getNombrePlan());
		final ProcedimientoEntity procedimientoEntity = new ProcedimientoEntity("Nombre procedimiento",
				"Descripción procedimiento", new Date(), 0, Boolean.FALSE, plan);
		procedimientoRepository.saveAndFlush(procedimientoEntity);
		final List<ProcedimientoEntity> procedimiento = new ArrayList<>();
		procedimiento.add(procedimientoEntity);
		final List<Integer> orden = new ArrayList<>();
		orden.add(1);
		proceso.setProcedimientos(procedimiento);
		proceso.setOrdenProceso(orden);
		procesoService.anadirProceso(proceso);

		final ReturnBusquedas<ProcesoEntity> procesoReactivar = procesoService.buscarProceso(
				proceso.getProceso().getNombreProceso(), proceso.getProceso().getDescripProceso(), new Date(), 0, 1);

		final ProcedimientoEntity procedimientoEncontrado = procedimientoRepository
				.findProcedimientoByName(procedimientoEntity.getNombreProcedimiento());

		procesoReactivar.getListaBusquedas().get(0).setBorradoProceso(0);

		final String respuesta = procesoService
				.reactivarProceso(new Proceso(proceso.getUsuario(), procesoReactivar.getListaBusquedas().get(0)));

		assertEquals(Constantes.OK, respuesta);

		procesoProcedimientoRepository.deleteProcesoProcedimiento(
				procesoReactivar.getListaBusquedas().get(0).getIdProceso(),
				procedimientoEncontrado.getIdProcedimiento());
		procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
		procesoRepository.deleteProceso(procesoReactivar.getListaBusquedas().get(0).getIdProceso());
		planRepository.deletePlan(planBDNuevo.getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoBDNuevo.getIdObjetivo());

	}

	@Test(expected = ProcesoNoExisteException.class)
	public void ProcesoService_reactivarProcesoNoExiste()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ObjetivoNoExisteException, java.text.ParseException, ProcesoNoExisteException {

		final Proceso proceso = generateProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.PROCESO_NO_EXISTE);

		procesoService.reactivarProceso(proceso);

	}

	private Proceso generateProceso(final String fichero, final String nombrePrueba)
			throws IOException, ParseException, java.text.ParseException {

		final JSONObject jsonProcesoVacios = new Util().getDatosJson(fichero, nombrePrueba);

		final Proceso proceso = new Proceso();
		final ProcesoEntity procesoEntity = new ProcesoEntity();

		final SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

		final Date date;
		if (jsonProcesoVacios.get(Constantes.FECHA_PROCESO).toString().equals(StringUtils.EMPTY)) {
			date = formato.parse("0000-00-00");
		} else {
			date = formato.parse(CommonUtilities.coalesce(jsonProcesoVacios.get(Constantes.FECHA_PROCESO).toString(),
					StringUtils.EMPTY));
		}

		if (new String((jsonProcesoVacios.get(Constantes.PROCESO_ID).toString()).getBytes("UTF-8"))
				.equals(StringUtils.EMPTY)) {
			procesoEntity.setIdProceso(0);
		} else {
			procesoEntity.setIdProceso(Integer
					.parseInt(new String((jsonProcesoVacios.get(Constantes.PROCESO_ID).toString()).getBytes("UTF-8"))));
		}
		procesoEntity.setNombreProceso(CommonUtilities.coalesce(
				new String((jsonProcesoVacios.get(Constantes.NOMBRE_PROCESO).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		procesoEntity.setDescripProceso(CommonUtilities.coalesce(
				new String((jsonProcesoVacios.get(Constantes.DESCRIP_PROCESO).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		procesoEntity.setFechaProceso(date);
		procesoEntity.setBorradoProceso(0);

		proceso.setUsuario(
				CommonUtilities.coalesce(jsonProcesoVacios.get(Constantes.USUARIO).toString(), StringUtils.EMPTY));
		proceso.setProceso(procesoEntity);

		return proceso;

	}

}
