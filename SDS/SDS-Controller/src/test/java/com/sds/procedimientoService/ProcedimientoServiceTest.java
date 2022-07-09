package com.sds.procedimientoService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
import com.sds.model.ProcesoEntity;
import com.sds.model.ProcesoProcedimientoEntity;
import com.sds.model.RolEntity;
import com.sds.model.UsuarioEntity;
import com.sds.repository.ObjetivoRepository;
import com.sds.repository.PersonaRepository;
import com.sds.repository.PlanRepository;
import com.sds.repository.ProcedimientoRepository;
import com.sds.repository.ProcedimientoUsuarioRepository;
import com.sds.repository.ProcesoProcedimientoRepository;
import com.sds.repository.ProcesoRepository;
import com.sds.repository.UsuarioRepository;
import com.sds.service.common.CommonUtilities;
import com.sds.service.common.Constantes;
import com.sds.service.common.ReturnBusquedas;
import com.sds.service.exception.FechaAnteriorFechaActualException;
import com.sds.service.exception.LogAccionesNoGuardadoException;
import com.sds.service.exception.LogExcepcionesNoGuardadoException;
import com.sds.service.exception.ObjetivoNoExisteException;
import com.sds.service.exception.PlanAsociadoProcedimientoException;
import com.sds.service.exception.PlanNoExisteException;
import com.sds.service.exception.PlanYaExisteException;
import com.sds.service.exception.ProcedimientoNoExisteException;
import com.sds.service.exception.ProcedimientoYaExisteException;
import com.sds.service.exception.ProcesoAsociadoProcedimientoException;
import com.sds.service.exception.UsuarioAsociadoProcedimientoException;
import com.sds.service.procedimiento.ProcedimientoService;
import com.sds.service.procedimiento.model.Procedimiento;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SDSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcedimientoServiceTest {

	@Autowired
	ProcedimientoService procedimientoService;

	@Autowired
	ProcedimientoRepository procedimientoRepository;

	@Autowired
	ObjetivoRepository objetivoRepository;

	@Autowired
	PlanRepository planRepository;

	@Autowired
	ProcesoRepository procesoRepository;

	@Autowired
	ProcesoProcedimientoRepository procesoProcedimientoRepository;

	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	ProcedimientoUsuarioRepository procedimientoUsuarioRepository;

	@Test
	public void ProcedimientoService_buscarProcedimiento()
			throws IOException, ParseException, java.text.ParseException {

		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.BUSCAR_PROCEDIMIENTO);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final Optional<PlanEntity> plan = planRepository.findById(1);
		procedimiento.getProcedimientoEntity().setPlan(plan.get());

		final ReturnBusquedas<ProcedimientoEntity> procedimientoEncontrado = procedimientoService.buscarProcedimiento(
				procedimiento.getProcedimientoEntity().getNombreProcedimiento(),
				procedimiento.getProcedimientoEntity().getDescripProcedimiento(), new Date(),
				procedimiento.getProcedimientoEntity().getCheckUsuario(),
				procedimiento.getProcedimientoEntity().getPlan(), 0, 1);

		final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());

		objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());

		assertNotNull(procedimientoEncontrado.getListaBusquedas());
	}

	@Test
	public void ProcedimientoService_buscarProcedimientoNombreProcedimientoVacio()
			throws IOException, ParseException, java.text.ParseException {

		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.NOMBRE_PROCEDIMIENTO_VACIO);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final Optional<PlanEntity> plan = planRepository.findById(1);
		procedimiento.getProcedimientoEntity().setPlan(plan.get());

		final ReturnBusquedas<ProcedimientoEntity> procedimientoEncontrado = procedimientoService.buscarProcedimiento(
				procedimiento.getProcedimientoEntity().getNombreProcedimiento(),
				procedimiento.getProcedimientoEntity().getDescripProcedimiento(), new Date(),
				procedimiento.getProcedimientoEntity().getCheckUsuario(),
				procedimiento.getProcedimientoEntity().getPlan(), 0, 1);

		final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());

		objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());

		assertNotNull(procedimientoEncontrado.getListaBusquedas());
	}

	@Test
	public void ProcedimientoService_buscarProcedimientoDescripcionProcedimientoVacio()
			throws IOException, ParseException, java.text.ParseException {

		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.DESCRIPCION_PROCEDIMIENTO_VACIO);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final Optional<PlanEntity> plan = planRepository.findById(1);
		procedimiento.getProcedimientoEntity().setPlan(plan.get());

		final ReturnBusquedas<ProcedimientoEntity> procedimientoEncontrado = procedimientoService.buscarProcedimiento(
				procedimiento.getProcedimientoEntity().getNombreProcedimiento(),
				procedimiento.getProcedimientoEntity().getDescripProcedimiento(), new Date(),
				procedimiento.getProcedimientoEntity().getCheckUsuario(),
				procedimiento.getProcedimientoEntity().getPlan(), 0, 1);

		final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());

		objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());

		assertNotNull(procedimientoEncontrado.getListaBusquedas());
	}

	@Test
	public void ProcedimientoService_buscarProcedimientoFechaProcedimientoVacia()
			throws IOException, ParseException, java.text.ParseException {

		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.FECHA_PROCEDIMIENTO_VACIA);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final Optional<PlanEntity> plan = planRepository.findById(1);
		procedimiento.getProcedimientoEntity().setPlan(plan.get());

		final ReturnBusquedas<ProcedimientoEntity> procedimientoEncontrado = procedimientoService.buscarProcedimiento(
				procedimiento.getProcedimientoEntity().getNombreProcedimiento(),
				procedimiento.getProcedimientoEntity().getDescripProcedimiento(), new Date(),
				procedimiento.getProcedimientoEntity().getCheckUsuario(),
				procedimiento.getProcedimientoEntity().getPlan(), 0, 1);

		final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());

		objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());

		assertNotNull(procedimientoEncontrado.getListaBusquedas());
	}

	@Test
	public void ProcedimientoService_buscarProcedimientoDatosProcedimientoVacios()
			throws IOException, ParseException, java.text.ParseException {

		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.DATOS_PROCEDIMIENTO_VACIOS);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final Optional<PlanEntity> plan = planRepository.findById(1);
		procedimiento.getProcedimientoEntity().setPlan(plan.get());

		final ReturnBusquedas<ProcedimientoEntity> procedimientoEncontrado = procedimientoService.buscarProcedimiento(
				procedimiento.getProcedimientoEntity().getNombreProcedimiento(),
				procedimiento.getProcedimientoEntity().getDescripProcedimiento(), new Date(),
				procedimiento.getProcedimientoEntity().getCheckUsuario(),
				procedimiento.getProcedimientoEntity().getPlan(), 0, 1);

		final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());

		objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());

		assertNotNull(procedimientoEncontrado.getListaBusquedas());
	}

	@Test
	public void ProcedimientoService_guardarProcedimiento()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			java.text.ParseException, PlanYaExisteException, FechaAnteriorFechaActualException, PlanNoExisteException,
			ObjetivoNoExisteException, ProcedimientoNoExisteException, ProcedimientoYaExisteException {

		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.GUARDAR_PROCEDIMIENTO);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		procedimiento.getProcedimientoEntity().setPlan(plan);
		procedimiento.getProcedimientoEntity().setFechaProcedimiento(new Date());
		procedimiento.getProcedimientoEntity().setBorradoProcedimiento(0);

		String respuesta = StringUtils.EMPTY;
		String dateActualPlanString = StringUtils.EMPTY;

		respuesta = procedimientoService.anadirProcedimiento(procedimiento);

		assertEquals(Constantes.OK, respuesta);

		final LocalDate dateActualPlan = plan.getFechaPlan().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		if (CommonUtilities.countDigit(dateActualPlan.getDayOfMonth()) == 1) {
			dateActualPlanString = dateActualPlan.getYear() + "-0" + dateActualPlan.getMonthValue() + "-0"
					+ dateActualPlan.getDayOfMonth();
		} else {
			dateActualPlanString = dateActualPlan.getYear() + "-0" + dateActualPlan.getMonthValue() + "-"
					+ dateActualPlan.getDayOfMonth();
		}

		final ReturnBusquedas<ProcedimientoEntity> procedimientoEncontrado = procedimientoService.buscarProcedimiento(
				procedimiento.getProcedimientoEntity().getNombreProcedimiento(),
				procedimiento.getProcedimientoEntity().getDescripProcedimiento(),
				procedimiento.getProcedimientoEntity().getFechaProcedimiento(),
				procedimiento.getProcedimientoEntity().getCheckUsuario(),
				procedimiento.getProcedimientoEntity().getPlan(), 0, 1);
		final List<PlanEntity> planEncontrado = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(),
				dateActualPlanString, plan.getObjetivo());
		final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());

		procedimientoService.deleteProcedimiento(procedimientoEncontrado.getListaBusquedas().get(0));
		planRepository.deletePlan(planEncontrado.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());
	}

	@Test
	public void ProcedimientoService_guardarProcedimientoNombreProcedimientoVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, PlanYaExisteException,
			java.text.ParseException, FechaAnteriorFechaActualException, ObjetivoNoExisteException,
			ProcedimientoYaExisteException, PlanNoExisteException, ProcedimientoNoExisteException {
		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.NOMBRE_PROCEDIMIENTO_VACIO);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		procedimiento.getProcedimientoEntity().setPlan(plan);
		procedimiento.getProcedimientoEntity().setFechaProcedimiento(new Date());
		procedimiento.getProcedimientoEntity().setBorradoProcedimiento(0);

		String respuesta = StringUtils.EMPTY;
		String dateActualPlanString = StringUtils.EMPTY;

		respuesta = procedimientoService.anadirProcedimiento(procedimiento);

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

		planRepository.deletePlan(planEncontrado.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

		assertEquals(CodeMessageErrors.PROCEDIMIENTO_VACIO.name(), respuesta);

	}

	@Test
	public void ProcedimientoService_guardarProcedimientoDescripcionProcedimientoVacio() throws IOException,
			ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, PlanYaExisteException,
			java.text.ParseException, FechaAnteriorFechaActualException, ObjetivoNoExisteException,
			ProcedimientoYaExisteException, PlanNoExisteException, ProcedimientoNoExisteException {
		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.DESCRIPCION_PROCEDIMIENTO_VACIO);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		procedimiento.getProcedimientoEntity().setPlan(plan);
		procedimiento.getProcedimientoEntity().setFechaProcedimiento(new Date());
		procedimiento.getProcedimientoEntity().setBorradoProcedimiento(0);

		String respuesta = StringUtils.EMPTY;
		String dateActualPlanString = StringUtils.EMPTY;

		respuesta = procedimientoService.anadirProcedimiento(procedimiento);

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

		planRepository.deletePlan(planEncontrado.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

		assertEquals(CodeMessageErrors.PROCEDIMIENTO_VACIO.name(), respuesta);

	}

	@Test
	public void ProcedimientoService_guardarProcedimientoFechaProcedimientoVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, PlanYaExisteException,
			java.text.ParseException, FechaAnteriorFechaActualException, ObjetivoNoExisteException,
			ProcedimientoYaExisteException, PlanNoExisteException, ProcedimientoNoExisteException {
		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.FECHA_PROCEDIMIENTO_VACIA);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		procedimiento.getProcedimientoEntity().setPlan(plan);
		procedimiento.getProcedimientoEntity().setBorradoProcedimiento(0);

		String respuesta = StringUtils.EMPTY;
		String dateActualPlanString = StringUtils.EMPTY;
		respuesta = procedimientoService.anadirProcedimiento(procedimiento);

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

		planRepository.deletePlan(planEncontrado.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

		assertEquals(CodeMessageErrors.PROCEDIMIENTO_VACIO.name(), respuesta);

	}

	@Test
	public void ProcedimientoService_guardarProcedimientoCheckUsuarioVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, PlanYaExisteException,
			java.text.ParseException, FechaAnteriorFechaActualException, ObjetivoNoExisteException,
			ProcedimientoYaExisteException, PlanNoExisteException, ProcedimientoNoExisteException {
		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.CHECK_USUARIO_VACIO);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		procedimiento.getProcedimientoEntity().setPlan(plan);
		procedimiento.getProcedimientoEntity().setFechaProcedimiento(new Date());
		procedimiento.getProcedimientoEntity().setBorradoProcedimiento(0);

		String respuesta = StringUtils.EMPTY;
		String dateActualPlanString = StringUtils.EMPTY;

		respuesta = procedimientoService.anadirProcedimiento(procedimiento);

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

		planRepository.deletePlan(planEncontrado.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

		assertEquals(CodeMessageErrors.PROCEDIMIENTO_VACIO.name(), respuesta);

	}

	@Test
	public void ProcedimientoService_guardarProcedimientoDatosProcedimientoVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, PlanYaExisteException,
			java.text.ParseException, FechaAnteriorFechaActualException, ObjetivoNoExisteException,
			ProcedimientoYaExisteException, PlanNoExisteException, ProcedimientoNoExisteException {
		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.DATOS_PROCEDIMIENTO_VACIOS);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		procedimiento.getProcedimientoEntity().setPlan(plan);
		procedimiento.getProcedimientoEntity().setFechaProcedimiento(new Date());
		procedimiento.getProcedimientoEntity().setBorradoProcedimiento(0);

		String respuesta = StringUtils.EMPTY;
		String dateActualPlanString = StringUtils.EMPTY;

		respuesta = procedimientoService.anadirProcedimiento(procedimiento);

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

		planRepository.deletePlan(planEncontrado.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

		assertEquals(CodeMessageErrors.PROCEDIMIENTO_VACIO.name(), respuesta);

	}

	@Test(expected = FechaAnteriorFechaActualException.class)
	public void ProcedimientoService_guardarProcedimientoFechaProcedimientoAnteriorActual() throws IOException,
			ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, PlanYaExisteException,
			java.text.ParseException, FechaAnteriorFechaActualException, ObjetivoNoExisteException,
			ProcedimientoYaExisteException, PlanNoExisteException, ProcedimientoNoExisteException {
		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		procedimiento.getProcedimientoEntity().setPlan(plan);
		procedimiento.getProcedimientoEntity().setBorradoProcedimiento(0);

		try {
			procedimientoService.anadirProcedimiento(procedimiento);
		} catch (final FechaAnteriorFechaActualException fechaAnteriorFechaActualException) {
			throw new FechaAnteriorFechaActualException(
					CodeMessageErrors.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(
							CodeMessageErrors.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL.getCodigo()));
		} finally {
			String dateActualPlanString = StringUtils.EMPTY;
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

			planRepository.deletePlan(planEncontrado.get(0).getIdPlan());
			objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

		}

	}

	@Test(expected = ProcedimientoYaExisteException.class)
	public void ProcedimientoService_guardarProcedimientoYaExiste() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, java.text.ParseException,
			PlanYaExisteException, FechaAnteriorFechaActualException, ObjetivoNoExisteException,
			ProcedimientoYaExisteException, PlanNoExisteException, ProcedimientoNoExisteException {

		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.PROCEDIMIENTO_YA_EXISTE);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		procedimiento.getProcedimientoEntity().setPlan(plan);
		procedimiento.getProcedimientoEntity().setFechaProcedimiento(new Date());
		procedimientoService.anadirProcedimiento(procedimiento);
		procedimiento.getProcedimientoEntity().setBorradoProcedimiento(0);

		try {
			procedimientoService.anadirProcedimiento(procedimiento);
		} catch (final ProcedimientoYaExisteException procedimientoYaExisteException) {
			throw new ProcedimientoYaExisteException(CodeMessageErrors.PROCEDIMIENTO_YA_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.PROCEDIMIENTO_YA_EXISTE_EXCEPTION.getCodigo()));
		} finally {
			String dateActualPlanString = StringUtils.EMPTY;
			final LocalDate dateActualPlan = plan.getFechaPlan().toInstant().atZone(ZoneId.systemDefault())
					.toLocalDate();
			if (CommonUtilities.countDigit(dateActualPlan.getDayOfMonth()) == 1) {
				dateActualPlanString = dateActualPlan.getYear() + "-0" + dateActualPlan.getMonthValue() + "-0"
						+ dateActualPlan.getDayOfMonth();
			} else {
				dateActualPlanString = dateActualPlan.getYear() + "-0" + dateActualPlan.getMonthValue() + "-"
						+ dateActualPlan.getDayOfMonth();
			}

			final ReturnBusquedas<ProcedimientoEntity> procedimientoEncontrado = procedimientoService
					.buscarProcedimiento(procedimiento.getProcedimientoEntity().getNombreProcedimiento(),
							procedimiento.getProcedimientoEntity().getDescripProcedimiento(), new Date(),
							procedimiento.getProcedimientoEntity().getCheckUsuario(),
							procedimiento.getProcedimientoEntity().getPlan(), 0, 1);
			final List<PlanEntity> planEncontrado = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(),
					dateActualPlanString, plan.getObjetivo());
			final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository
					.findObjetivo(objetivo.getNombreObjetivo(), objetivo.getDescripObjetivo());

			procedimientoService.deleteProcedimiento(procedimientoEncontrado.getListaBusquedas().get(0));
			planRepository.deletePlan(planEncontrado.get(0).getIdPlan());
			objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

		}

	}

	@Test
	public void ProcedimientoService_modificarProcedimiento()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			PlanYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException, PlanNoExisteException,
			ObjetivoNoExisteException, ProcedimientoNoExisteException, ProcedimientoYaExisteException {

		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.MODIFICAR_PROCEDIMIENTO);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		procedimiento.getProcedimientoEntity().setPlan(plan);
		procedimiento.getProcedimientoEntity().setFechaProcedimiento(new Date());
		procedimiento.getProcedimientoEntity().setBorradoProcedimiento(0);

		String respuesta = StringUtils.EMPTY;
		String dateActualPlanString = StringUtils.EMPTY;

		procedimientoService.anadirProcedimiento(procedimiento);

		final ReturnBusquedas<ProcedimientoEntity> procedimientoModificar = procedimientoService.buscarProcedimiento(
				procedimiento.getProcedimientoEntity().getNombreProcedimiento(),
				procedimiento.getProcedimientoEntity().getDescripProcedimiento(), new Date(),
				procedimiento.getProcedimientoEntity().getCheckUsuario(),
				procedimiento.getProcedimientoEntity().getPlan(), 0, 1);

		procedimientoModificar.getListaBusquedas().get(0).setDescripProcedimiento("Descripción modificada");
		procedimientoModificar.getListaBusquedas().get(0).setCheckUsuario(Boolean.TRUE);

		procedimiento.setProcedimientoEntity(procedimientoModificar.getListaBusquedas().get(0));

		respuesta = procedimientoService.modificarProcedimiento(procedimiento);

		assertEquals(Constantes.OK, respuesta);

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

		procedimientoService.deleteProcedimiento(procedimientoModificar.getListaBusquedas().get(0));
		planRepository.deletePlan(planEncontrado.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

	}

	@Test
	public void ProcedimientoService_modificarProcedimientoNombreProcedimientoVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			PlanYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException, PlanNoExisteException,
			ObjetivoNoExisteException, ProcedimientoNoExisteException {
		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.NOMBRE_PROCEDIMIENTO_VACIO);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		procedimiento.getProcedimientoEntity().setPlan(plan);
		procedimiento.getProcedimientoEntity().setFechaProcedimiento(new Date());
		procedimiento.getProcedimientoEntity().setBorradoProcedimiento(0);

		String respuesta = StringUtils.EMPTY;
		String dateActualPlanString = StringUtils.EMPTY;

		respuesta = procedimientoService.modificarProcedimiento(procedimiento);

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

		planRepository.deletePlan(planEncontrado.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

		assertEquals(CodeMessageErrors.PROCEDIMIENTO_VACIO.name(), respuesta);

	}

	@Test
	public void ProcedimientoService_modificarProcedimientoDescripcionProcedimientoVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			PlanYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException, PlanNoExisteException,
			ObjetivoNoExisteException, ProcedimientoNoExisteException {
		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.DESCRIPCION_PROCEDIMIENTO_VACIO);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		procedimiento.getProcedimientoEntity().setPlan(plan);
		procedimiento.getProcedimientoEntity().setFechaProcedimiento(new Date());
		procedimiento.getProcedimientoEntity().setBorradoProcedimiento(0);

		String respuesta = StringUtils.EMPTY;
		String dateActualPlanString = StringUtils.EMPTY;

		respuesta = procedimientoService.modificarProcedimiento(procedimiento);

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

		planRepository.deletePlan(planEncontrado.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

		assertEquals(CodeMessageErrors.PROCEDIMIENTO_VACIO.name(), respuesta);

	}

	@Test
	public void ProcedimientoService_modificarProcedimientoFechaProcedimientoVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			PlanYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException, PlanNoExisteException,
			ObjetivoNoExisteException, ProcedimientoNoExisteException {
		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.FECHA_PROCEDIMIENTO_VACIA);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		procedimiento.getProcedimientoEntity().setPlan(plan);
		procedimiento.getProcedimientoEntity().setBorradoProcedimiento(0);

		String respuesta = StringUtils.EMPTY;
		String dateActualPlanString = StringUtils.EMPTY;

		respuesta = procedimientoService.modificarProcedimiento(procedimiento);

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

		planRepository.deletePlan(planEncontrado.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

		assertEquals(CodeMessageErrors.PROCEDIMIENTO_VACIO.name(), respuesta);

	}

	@Test
	public void ProcedimientoService_modificarProcedimientoCheckUsuarioProcedimientoVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			PlanYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException, PlanNoExisteException,
			ObjetivoNoExisteException, ProcedimientoNoExisteException {
		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.CHECK_USUARIO_VACIO);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		procedimiento.getProcedimientoEntity().setPlan(plan);
		procedimiento.getProcedimientoEntity().setFechaProcedimiento(new Date());
		procedimiento.getProcedimientoEntity().setBorradoProcedimiento(0);

		String respuesta = StringUtils.EMPTY;
		String dateActualPlanString = StringUtils.EMPTY;

		respuesta = procedimientoService.modificarProcedimiento(procedimiento);

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

		planRepository.deletePlan(planEncontrado.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

		assertEquals(CodeMessageErrors.PROCEDIMIENTO_VACIO.name(), respuesta);

	}

	@Test
	public void ProcedimientoService_modificarProcedimientoDatosProcedimientoVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			PlanYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException, PlanNoExisteException,
			ObjetivoNoExisteException, ProcedimientoNoExisteException {
		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.DATOS_PROCEDIMIENTO_VACIOS);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		procedimiento.getProcedimientoEntity().setPlan(plan);
		procedimiento.getProcedimientoEntity().setFechaProcedimiento(new Date());
		procedimiento.getProcedimientoEntity().setBorradoProcedimiento(0);

		String respuesta = StringUtils.EMPTY;
		String dateActualPlanString = StringUtils.EMPTY;

		respuesta = procedimientoService.modificarProcedimiento(procedimiento);

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

		planRepository.deletePlan(planEncontrado.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

		assertEquals(CodeMessageErrors.PROCEDIMIENTO_VACIO.name(), respuesta);

	}

	@Test(expected = FechaAnteriorFechaActualException.class)
	public void ProcedimientoService_modificarProcedimientoFechaProcedimientoAnteriorActual()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			PlanYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException, PlanNoExisteException,
			ObjetivoNoExisteException, ProcedimientoYaExisteException, ProcedimientoNoExisteException {
		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		procedimiento.getProcedimientoEntity().setPlan(plan);
		procedimiento.getProcedimientoEntity().setBorradoProcedimiento(0);
		final Date fechaAnterior = procedimiento.getProcedimientoEntity().getFechaProcedimiento();
		procedimiento.getProcedimientoEntity().setFechaProcedimiento(new Date());
		procedimientoService.anadirProcedimiento(procedimiento);

		final ReturnBusquedas<ProcedimientoEntity> procedimientoEncontrado = procedimientoService.buscarProcedimiento(
				procedimiento.getProcedimientoEntity().getNombreProcedimiento(),
				procedimiento.getProcedimientoEntity().getDescripProcedimiento(), new Date(),
				procedimiento.getProcedimientoEntity().getCheckUsuario(), plan, 0, 1);

		procedimientoEncontrado.getListaBusquedas().get(0).setFechaProcedimiento(fechaAnterior);

		try {
			procedimientoService.modificarProcedimiento(
					new Procedimiento(procedimiento.getUsuario(), procedimientoEncontrado.getListaBusquedas().get(0)));
		} catch (final FechaAnteriorFechaActualException fechaAnteriorFechaActualException) {
			throw new FechaAnteriorFechaActualException(
					CodeMessageErrors.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(
							CodeMessageErrors.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL.getCodigo()));
		} finally {
			String dateActualPlanString = StringUtils.EMPTY;
			final LocalDate dateActualPlan = plan.getFechaPlan().toInstant().atZone(ZoneId.systemDefault())
					.toLocalDate();
			if (CommonUtilities.countDigit(dateActualPlan.getDayOfMonth()) == 1) {
				dateActualPlanString = dateActualPlan.getYear() + "-0" + dateActualPlan.getMonthValue() + "-0"
						+ dateActualPlan.getDayOfMonth();
			} else {
				dateActualPlanString = dateActualPlan.getYear() + "-0" + dateActualPlan.getMonthValue() + "-"
						+ dateActualPlan.getDayOfMonth();
			}
			procedimientoRepository
					.deleteProcedimiento(procedimientoEncontrado.getListaBusquedas().get(0).getIdProcedimiento());
			final List<PlanEntity> planEncontrado = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(),
					dateActualPlanString, plan.getObjetivo());
			final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository
					.findObjetivo(objetivo.getNombreObjetivo(), objetivo.getDescripObjetivo());

			planRepository.deletePlan(planEncontrado.get(0).getIdPlan());
			objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

		}
	}

	@Test(expected = ProcedimientoNoExisteException.class)
	public void ProcedimientoService_modificarProcedimientoNoExiste()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			ObjetivoNoExisteException, java.text.ParseException, PlanNoExisteException,
			FechaAnteriorFechaActualException, ProcedimientoNoExisteException {

		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.PROCEDIMIENTO_NO_EXISTE);

		procedimientoService.modificarProcedimiento(procedimiento);

	}

	@Test
	public void ProcedimientoService_eliminarProcedimientoCorrecto() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, java.text.ParseException,
			PlanYaExisteException, FechaAnteriorFechaActualException, PlanNoExisteException, ObjetivoNoExisteException,
			PlanAsociadoProcedimientoException, ProcedimientoYaExisteException, ProcedimientoNoExisteException,
			ProcesoAsociadoProcedimientoException, UsuarioAsociadoProcedimientoException {
		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.ELIMINAR_PROCEDIMIENTO);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		procedimiento.getProcedimientoEntity().setPlan(plan);
		procedimiento.getProcedimientoEntity().setFechaProcedimiento(new Date());
		procedimiento.getProcedimientoEntity().setBorradoProcedimiento(0);

		String respuesta = StringUtils.EMPTY;
		String dateActualPlanString = StringUtils.EMPTY;

		procedimientoService.anadirProcedimiento(procedimiento);

		final ReturnBusquedas<ProcedimientoEntity> procedimientoModificar = procedimientoService.buscarProcedimiento(
				procedimiento.getProcedimientoEntity().getNombreProcedimiento(),
				procedimiento.getProcedimientoEntity().getDescripProcedimiento(), new Date(),
				procedimiento.getProcedimientoEntity().getCheckUsuario(),
				procedimiento.getProcedimientoEntity().getPlan(), 0, 1);

		respuesta = procedimientoService.eliminaProcedimiento(
				new Procedimiento(procedimiento.getUsuario(), procedimientoModificar.getListaBusquedas().get(0)));

		assertEquals(Constantes.OK, respuesta);

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

		procedimientoService.deleteProcedimiento(procedimientoModificar.getListaBusquedas().get(0));
		planRepository.deletePlan(planEncontrado.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

	}

	@Test(expected = ProcedimientoNoExisteException.class)
	public void ProcedimientoService_eliminarProcedimientoNoExiste()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			java.text.ParseException, PlanNoExisteException, ProcedimientoNoExisteException,
			ProcesoAsociadoProcedimientoException, UsuarioAsociadoProcedimientoException {

		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.PROCEDIMIENTO_NO_EXISTE);

		procedimientoService.eliminaProcedimiento(
				new Procedimiento(procedimiento.getUsuario(), procedimiento.getProcedimientoEntity()));

	}

	@Test(expected = ProcesoAsociadoProcedimientoException.class)
	public void ProcedimientoService_eliminarProcedimientoProcesoAsociado()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			java.text.ParseException, PlanNoExisteException, ProcedimientoNoExisteException,
			ProcedimientoYaExisteException, FechaAnteriorFechaActualException, UsuarioAsociadoProcedimientoException,
			ProcesoAsociadoProcedimientoException {
		String fechaActualString = StringUtils.EMPTY;

		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.GUARDAR_PROCEDIMIENTO);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		procedimiento.getProcedimientoEntity().setPlan(plan);
		procedimiento.getProcedimientoEntity().setBorradoProcedimiento(0);
		procedimiento.getProcedimientoEntity().setFechaProcedimiento(new Date());

		procedimientoService.anadirProcedimiento(procedimiento);

		final ReturnBusquedas<ProcedimientoEntity> procedimientoEncontrado = procedimientoService.buscarProcedimiento(
				procedimiento.getProcedimientoEntity().getNombreProcedimiento(),
				procedimiento.getProcedimientoEntity().getDescripProcedimiento(), new Date(),
				procedimiento.getProcedimientoEntity().getCheckUsuario(), plan, 0, 1);

		final ProcesoEntity proceso = new ProcesoEntity("Nombre proceso", "Descripción proceso", new Date(), 0);
		procesoRepository.saveAndFlush(proceso);

		final LocalDate fechaActual = LocalDate.now();

		if (CommonUtilities.countDigit(fechaActual.getDayOfMonth()) == 1) {
			fechaActualString = fechaActual.getYear() + "-0" + fechaActual.getMonthValue() + "-0"
					+ fechaActual.getDayOfMonth();
		} else {
			fechaActualString = fechaActual.getYear() + "-0" + fechaActual.getMonthValue() + "-"
					+ fechaActual.getDayOfMonth();
		}

		final List<ProcesoEntity> procesoEncontrado = procesoRepository.findProceso(proceso.getNombreProceso(),
				proceso.getDescripProceso(), fechaActualString);
		final ProcesoProcedimientoEntity procesoProcedimiento = new ProcesoProcedimientoEntity(
				procesoEncontrado.get(0).getIdProceso(),
				procedimientoEncontrado.getListaBusquedas().get(0).getIdProcedimiento(), 2);
		procesoProcedimientoRepository.saveAndFlush(procesoProcedimiento);

		try {
			procedimientoService.eliminaProcedimiento(
					new Procedimiento(procedimiento.getUsuario(), procedimientoEncontrado.getListaBusquedas().get(0)));
		} catch (final ProcesoAsociadoProcedimientoException procesoAsociadoProcedimientoException) {
			throw new ProcesoAsociadoProcedimientoException(
					CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(
							CodeMessageErrors.PROCESO_ASOCIADO_PROCEDIMIENTO_EXCEPTION.getCodigo()));
		} finally {
			procesoProcedimientoRepository.deleteProcesoProcedimiento(procesoEncontrado.get(0).getIdProceso(),
					procedimientoEncontrado.getListaBusquedas().get(0).getIdProcedimiento());
			procesoRepository.deleteProceso(procesoEncontrado.get(0).getIdProceso());
			procedimientoRepository
					.deleteProcedimiento(procedimientoEncontrado.getListaBusquedas().get(0).getIdProcedimiento());

			final List<PlanEntity> planEncontrado = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(),
					fechaActualString, objetivo);
			planRepository.deletePlan(planEncontrado.get(0).getIdPlan());

			final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository
					.findObjetivo(objetivo.getNombreObjetivo(), objetivo.getDescripObjetivo());
			objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());
		}
	}

	@Test(expected = UsuarioAsociadoProcedimientoException.class)
	public void ProcedimientoService_eliminarProcedimientoUsuarioAsociado()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			java.text.ParseException, PlanNoExisteException, ProcedimientoNoExisteException,
			ProcedimientoYaExisteException, FechaAnteriorFechaActualException, UsuarioAsociadoProcedimientoException,
			ProcesoAsociadoProcedimientoException {
		String fechaActualString = StringUtils.EMPTY;
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		final RolEntity rolEntity = new RolEntity(2, "usuario",
				"Contendrá a todos los usuarios registrados de la aplicacion", 0);
		final PersonaEntity persona = new PersonaEntity("80004674W", "Pepe", "Pepe pepe", format.parse("2022-02-02"),
				"Calle de prueba", "988745121", "email@email.com", 0, null);
		personaRepository.saveAndFlush(persona);
		final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", "Contraseña", 0, rolEntity,
				persona);
		usuarioRepository.saveAndFlush(usuario);

		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.GUARDAR_PROCEDIMIENTO);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		procedimiento.getProcedimientoEntity().setPlan(plan);
		procedimiento.getProcedimientoEntity().setBorradoProcedimiento(0);
		procedimiento.getProcedimientoEntity().setFechaProcedimiento(new Date());

		procedimientoService.anadirProcedimiento(procedimiento);

		final ReturnBusquedas<ProcedimientoEntity> procedimientoEncontrado = procedimientoService.buscarProcedimiento(
				procedimiento.getProcedimientoEntity().getNombreProcedimiento(),
				procedimiento.getProcedimientoEntity().getDescripProcedimiento(), new Date(),
				procedimiento.getProcedimientoEntity().getCheckUsuario(), plan, 0, 1);

		final List<UsuarioEntity> usuarioEncontrado = usuarioRepository.findUsuario(usuario.getDniUsuario(),
				usuario.getUsuario(), rolEntity);
		final List<PersonaEntity> personaEncontrada = personaRepository.findPersona(persona.getDniP(),
				persona.getNombreP(), persona.getApellidosP(), "2022-02-02", persona.getDireccionP(),
				persona.getTelefonoP(), persona.getEmailP());

		final ProcedimientoUsuarioEntity procedimientoUsuarioEntity = new ProcedimientoUsuarioEntity(1, new Date(), 0,
				procedimientoEncontrado.getListaBusquedas().get(0), usuarioEncontrado.get(0));
		procedimientoUsuarioRepository.saveAndFlush(procedimientoUsuarioEntity);

		final List<ProcedimientoUsuarioEntity> procedimientoUsuarioEncontrado = procedimientoUsuarioRepository
				.findProcedimientoUsuario(fechaActualString, usuario,
						procedimientoEncontrado.getListaBusquedas().get(0));

		final LocalDate fechaActual = LocalDate.now();

		if (CommonUtilities.countDigit(fechaActual.getDayOfMonth()) == 1) {
			fechaActualString = fechaActual.getYear() + "-0" + fechaActual.getMonthValue() + "-0"
					+ fechaActual.getDayOfMonth();
		} else {
			fechaActualString = fechaActual.getYear() + "-0" + fechaActual.getMonthValue() + "-"
					+ fechaActual.getDayOfMonth();
		}

		try {
			procedimientoService.eliminaProcedimiento(
					new Procedimiento(procedimiento.getUsuario(), procedimientoEncontrado.getListaBusquedas().get(0)));
		} catch (final UsuarioAsociadoProcedimientoException usuarioAsociadoProcedimientoException) {
			throw new UsuarioAsociadoProcedimientoException(
					CodeMessageErrors.USUARIO_ASOCIADO_PROCEDIMIENTO_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(
							CodeMessageErrors.USUARIO_ASOCIADO_PROCEDIMIENTO_EXCEPTION.getCodigo()));
		} finally {
			procedimientoUsuarioRepository
					.deleteProcedimientoUsuario(procedimientoUsuarioEncontrado.get(0).getIdProcedimientoUsuario());
			usuarioRepository.deleteUsuario(usuarioEncontrado.get(0).getDniUsuario());
			personaRepository.deletePersona(personaEncontrada.get(0).getDniP());
			procedimientoRepository
					.deleteProcedimiento(procedimientoEncontrado.getListaBusquedas().get(0).getIdProcedimiento());

			final List<PlanEntity> planEncontrado = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(),
					fechaActualString, objetivo);
			planRepository.deletePlan(planEncontrado.get(0).getIdPlan());

			final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository
					.findObjetivo(objetivo.getNombreObjetivo(), objetivo.getDescripObjetivo());
			objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());
		}
	}

	@Test
	public void ProcedimientoService_reactivarProcedimientoCorrecto()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			java.text.ParseException, PlanYaExisteException, FechaAnteriorFechaActualException, PlanNoExisteException,
			ObjetivoNoExisteException, ProcedimientoYaExisteException, ProcedimientoNoExisteException {
		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.ELIMINAR_PROCEDIMIENTO);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);
		procedimiento.getProcedimientoEntity().setPlan(plan);
		procedimiento.getProcedimientoEntity().setFechaProcedimiento(new Date());
		procedimiento.getProcedimientoEntity().setBorradoProcedimiento(1);

		String respuesta = StringUtils.EMPTY;
		String dateActualPlanString = StringUtils.EMPTY;

		procedimientoService.anadirProcedimiento(procedimiento);

		final ReturnBusquedas<ProcedimientoEntity> procedimientoReactivar = procedimientoService.buscarProcedimiento(
				procedimiento.getProcedimientoEntity().getNombreProcedimiento(),
				procedimiento.getProcedimientoEntity().getDescripProcedimiento(), new Date(),
				procedimiento.getProcedimientoEntity().getCheckUsuario(),
				procedimiento.getProcedimientoEntity().getPlan(), 0, 1);

		respuesta = procedimientoService.reactivarProcedimiento(
				new Procedimiento(procedimiento.getUsuario(), procedimientoReactivar.getListaBusquedas().get(0)));
		assertEquals(Constantes.OK, respuesta);

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

		procedimientoService.deleteProcedimiento(procedimientoReactivar.getListaBusquedas().get(0));
		planRepository.deletePlan(planEncontrado.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

	}

	@Test(expected = ProcedimientoNoExisteException.class)
	public void ProcedimientoService_reactivarProcedimientoNoExiste()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			java.text.ParseException, PlanNoExisteException, ProcedimientoNoExisteException {

		final Procedimiento procedimiento = generateProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
				Constantes.PROCEDIMIENTO_NO_EXISTE);

		procedimientoService.reactivarProcedimiento(procedimiento);

	}

	private Procedimiento generateProcedimiento(final String fichero, final String nombrePrueba)
			throws IOException, ParseException, java.text.ParseException {

		final JSONObject jsonProcedimientoVacios = new Util().getDatosJson(fichero, nombrePrueba);

		final Procedimiento procedimiento = new Procedimiento();
		final ProcedimientoEntity procedimientoEntity = new ProcedimientoEntity();

		final SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

		final Date date;
		if (jsonProcedimientoVacios.get(Constantes.FECHA_PROCEDIMIENTO).toString().equals(StringUtils.EMPTY)) {
			date = formato.parse("0000-00-00");
		} else {
			date = formato.parse(CommonUtilities.coalesce(
					jsonProcedimientoVacios.get(Constantes.FECHA_PROCEDIMIENTO).toString(), StringUtils.EMPTY));
		}

		if (new String((jsonProcedimientoVacios.get(Constantes.PROCEDIMIENTO_ID).toString()).getBytes("UTF-8"))
				.equals(StringUtils.EMPTY)) {
			procedimientoEntity.setIdProcedimiento(0);
		} else {
			procedimientoEntity.setIdProcedimiento(Integer.parseInt(new String(
					(jsonProcedimientoVacios.get(Constantes.PROCEDIMIENTO_ID).toString()).getBytes("UTF-8"))));
		}
		procedimientoEntity.setNombreProcedimiento(CommonUtilities.coalesce(
				new String((jsonProcedimientoVacios.get(Constantes.NOMBRE_PROCEDIMIENTO).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		procedimientoEntity.setDescripProcedimiento(CommonUtilities.coalesce(new String(
				(jsonProcedimientoVacios.get(Constantes.DESCRIPCION_PROCEDIMIENTO).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY));
		final String checkUsuario = CommonUtilities.coalesce(
				new String((jsonProcedimientoVacios.get(Constantes.CHECK_USUARIO).toString()).getBytes("UTF-8")),
				StringUtils.EMPTY);
		if (checkUsuario.equals(StringUtils.EMPTY)) {
			procedimientoEntity.setCheckUsuario(null);
		} else {
			procedimientoEntity.setCheckUsuario(Boolean.valueOf((CommonUtilities.coalesce(
					new String((jsonProcedimientoVacios.get(Constantes.CHECK_USUARIO).toString()).getBytes("UTF-8")),
					StringUtils.EMPTY))));
		}

		procedimientoEntity.setFechaProcedimiento(date);

		procedimientoEntity.setBorradoProcedimiento(0);

		procedimiento.setUsuario(CommonUtilities.coalesce(jsonProcedimientoVacios.get(Constantes.USUARIO).toString(),
				StringUtils.EMPTY));
		procedimiento.setProcedimientoEntity(procedimientoEntity);

		return procedimiento;

	}

}
