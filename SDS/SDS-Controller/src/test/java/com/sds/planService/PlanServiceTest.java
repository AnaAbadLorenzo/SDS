package com.sds.planService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import com.sds.model.ObjetivoEntity;
import com.sds.model.PlanEntity;
import com.sds.model.ProcedimientoEntity;
import com.sds.repository.ObjetivoRepository;
import com.sds.repository.PlanRepository;
import com.sds.repository.ProcedimientoRepository;
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
import com.sds.service.plan.PlanService;
import com.sds.service.plan.model.Plan;
import com.sds.service.util.CodeMessageErrors;
import com.sds.service.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SDSApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlanServiceTest {

	@Autowired
	PlanService planService;

	@Autowired
	PlanRepository planRepository;

	@Autowired
	ObjetivoRepository objetivoRepository;

	@Autowired
	ProcedimientoRepository procedimientoRepository;

	@Test
	public void PlanService_buscarPlan() throws IOException, ParseException, java.text.ParseException {

		final Plan plan = generatePlan(Constantes.URL_JSON_PLAN_DATA, Constantes.BUSCAR_PLAN);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity planEntity = plan.getPlan();
		plan.getPlan().setObjetivo(objetivo);

		final ReturnBusquedas<PlanEntity> planEncontrado = planService.buscarPlan(planEntity.getNombrePlan(),
				planEntity.getDescripPlan(), planEntity.getFechaPlan(), planEntity.getObjetivo(), 0, 1);

		final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());

		objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());

		assertNotNull(planEncontrado.getListaBusquedas());
	}

	@Test
	public void PlanService_buscarPlanNombreVacio() throws IOException, ParseException, java.text.ParseException {

		final Plan plan = generatePlan(Constantes.URL_JSON_PLAN_DATA, Constantes.NOMBRE_PLAN_VACIO);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity planEntity = plan.getPlan();
		plan.getPlan().setObjetivo(objetivo);

		final ReturnBusquedas<PlanEntity> planEncontrado = planService.buscarPlan(planEntity.getNombrePlan(),
				planEntity.getDescripPlan(), planEntity.getFechaPlan(), planEntity.getObjetivo(), 0, 1);

		final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());

		objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());

		assertNotNull(planEncontrado.getListaBusquedas());
	}

	@Test
	public void PlanService_buscarPlanDescripcionVacio() throws IOException, ParseException, java.text.ParseException {

		final Plan plan = generatePlan(Constantes.URL_JSON_PLAN_DATA, Constantes.DESCRIPCION_PLAN_VACIO);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity planEntity = plan.getPlan();
		plan.getPlan().setObjetivo(objetivo);

		final ReturnBusquedas<PlanEntity> planEncontrado = planService.buscarPlan(planEntity.getNombrePlan(),
				planEntity.getDescripPlan(), planEntity.getFechaPlan(), planEntity.getObjetivo(), 0, 1);

		final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());

		objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());

		assertNotNull(planEncontrado.getListaBusquedas());
	}

	@Test
	public void PlanService_buscarPlanFechaVacia() throws IOException, ParseException, java.text.ParseException {

		final Plan plan = generatePlan(Constantes.URL_JSON_PLAN_DATA, Constantes.FECHA_PLAN_VACIA);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity planEntity = plan.getPlan();
		plan.getPlan().setObjetivo(objetivo);

		final ReturnBusquedas<PlanEntity> planEncontrado = planService.buscarPlan(planEntity.getNombrePlan(),
				planEntity.getDescripPlan(), planEntity.getFechaPlan(), planEntity.getObjetivo(), 0, 1);

		final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());

		objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());

		assertNotNull(planEncontrado.getListaBusquedas());
	}

	@Test
	public void PlanService_buscarPlanDatosPlanVacios() throws IOException, ParseException, java.text.ParseException {

		final Plan plan = generatePlan(Constantes.URL_JSON_PLAN_DATA, Constantes.DATOS_PLAN_VACIOS);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity planEntity = plan.getPlan();
		plan.getPlan().setObjetivo(objetivo);

		final ReturnBusquedas<PlanEntity> planEncontrado = planService.buscarPlan(planEntity.getNombrePlan(),
				planEntity.getDescripPlan(), planEntity.getFechaPlan(), planEntity.getObjetivo(), 0, 1);

		final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());

		objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());

		assertNotNull(planEncontrado.getListaBusquedas());
	}

	@Test
	public void PlanService_guardarPlan() throws IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, java.text.ParseException, PlanYaExisteException,
			FechaAnteriorFechaActualException, PlanNoExisteException, ObjetivoNoExisteException {

		final Plan plan = generatePlan(Constantes.URL_JSON_PLAN_DATA, Constantes.GUARDAR_PLAN);
		final PlanEntity planEntity = plan.getPlan();
		planEntity.setFechaPlan(new Date());
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripción objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		plan.getPlan().setObjetivo(objetivo);

		String respuesta = StringUtils.EMPTY;

		respuesta = planService.anadirPlan(plan);

		assertEquals(Constantes.OK, respuesta);

		final ReturnBusquedas<PlanEntity> planEncontrado = planService.buscarPlan(planEntity.getNombrePlan(),
				planEntity.getDescripPlan(), planEntity.getFechaPlan(), planEntity.getObjetivo(), 0, 1);
		final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());

		planService.deletePlan(planEncontrado.getListaBusquedas().get(0));
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());
	}

	@Test
	public void PlanService_guardarPlanNombrePlanVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, PlanYaExisteException,
			java.text.ParseException, FechaAnteriorFechaActualException, ObjetivoNoExisteException {
		final Plan plan = generatePlan(Constantes.URL_JSON_PLAN_DATA, Constantes.NOMBRE_PLAN_VACIO);

		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripción objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		plan.getPlan().setObjetivo(objetivo);
		String respuesta = StringUtils.EMPTY;

		respuesta = planService.anadirPlan(plan);

		final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

		assertEquals(CodeMessageErrors.PLAN_VACIO.name(), respuesta);

	}

	@Test
	public void PlanService_guardarPlanDescripcionPlanVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, PlanYaExisteException,
			java.text.ParseException, FechaAnteriorFechaActualException, ObjetivoNoExisteException {
		final Plan plan = generatePlan(Constantes.URL_JSON_PLAN_DATA, Constantes.DESCRIPCION_PLAN_VACIO);

		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripción objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		plan.getPlan().setObjetivo(objetivo);
		String respuesta = StringUtils.EMPTY;

		respuesta = planService.anadirPlan(plan);

		final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

		assertEquals(CodeMessageErrors.PLAN_VACIO.name(), respuesta);

	}

	@Test
	public void PlanService_guardarPlanFechaPlanVacio() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, PlanYaExisteException,
			java.text.ParseException, FechaAnteriorFechaActualException, ObjetivoNoExisteException {
		final Plan plan = generatePlan(Constantes.URL_JSON_PLAN_DATA, Constantes.FECHA_PLAN_VACIA);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripción objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		plan.getPlan().setObjetivo(objetivo);

		String respuesta = StringUtils.EMPTY;

		respuesta = planService.anadirPlan(plan);

		final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

		assertEquals(CodeMessageErrors.PLAN_VACIO.name(), respuesta);

	}

	@Test
	public void PlanService_guardarPlanDatosVacios() throws IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, java.text.ParseException, PlanYaExisteException,
			FechaAnteriorFechaActualException, ObjetivoNoExisteException {
		final Plan plan = generatePlan(Constantes.URL_JSON_PLAN_DATA, Constantes.DATOS_PLAN_VACIOS);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripción objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		plan.getPlan().setObjetivo(objetivo);

		String respuesta = StringUtils.EMPTY;

		respuesta = planService.anadirPlan(plan);

		final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

		assertEquals(CodeMessageErrors.PLAN_VACIO.name(), respuesta);
	}

	@Test(expected = FechaAnteriorFechaActualException.class)
	public void PlanService_guardarPlanFechaPlanAnteriorActual() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, PlanYaExisteException,
			java.text.ParseException, FechaAnteriorFechaActualException, ObjetivoNoExisteException {
		final Plan plan = generatePlan(Constantes.URL_JSON_PLAN_DATA,
				Constantes.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripción objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		plan.getPlan().setObjetivo(objetivo);

		try {
			planService.anadirPlan(plan);
		} catch (final FechaAnteriorFechaActualException fechaAnteriorFechaActualException) {
			throw new FechaAnteriorFechaActualException(
					CodeMessageErrors.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(
							CodeMessageErrors.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL.getCodigo()));
		} finally {
			final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository
					.findObjetivo(objetivo.getNombreObjetivo(), objetivo.getDescripObjetivo());
			objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());
		}

	}

	@Test(expected = PlanYaExisteException.class)
	public void PlanService_guardarPlanYaExiste() throws IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, java.text.ParseException, PlanYaExisteException,
			FechaAnteriorFechaActualException, ObjetivoNoExisteException {
		String fechaActualString = StringUtils.EMPTY;

		final Plan plan = generatePlan(Constantes.URL_JSON_PLAN_DATA, Constantes.PLAN_YA_EXISTE);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripción objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		plan.getPlan().setObjetivo(objetivo);
		plan.getPlan().setFechaPlan(new Date());
		planService.anadirPlan(plan);

		try {
			planService.anadirPlan(plan);
		} catch (final PlanYaExisteException planYaExiste) {
			throw new PlanYaExisteException(CodeMessageErrors.PLAN_YA_EXISTE_EXCEPTION.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(CodeMessageErrors.PLAN_YA_EXISTE_EXCEPTION.getCodigo()));
		} finally {
			final LocalDate fechaActual = LocalDate.now();
			if (CommonUtilities.countDigit(fechaActual.getDayOfMonth()) == 1) {
				fechaActualString = fechaActual.getYear() + "-0" + fechaActual.getMonthValue() + "-0"
						+ fechaActual.getDayOfMonth();
			} else {
				fechaActualString = fechaActual.getYear() + "-0" + fechaActual.getMonthValue() + "-"
						+ fechaActual.getDayOfMonth();
			}
			final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository
					.findObjetivo(objetivo.getNombreObjetivo(), objetivo.getDescripObjetivo());
			final List<PlanEntity> planEncontrado = planRepository.findPlan(plan.getPlan().getNombrePlan(),
					plan.getPlan().getDescripPlan(), fechaActualString, plan.getPlan().getObjetivo());
			planRepository.deletePlan(planEncontrado.get(0).getIdPlan());
			objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());
		}

	}

	@Test
	public void PlanService_modificarPlan() throws IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, PlanYaExisteException, java.text.ParseException,
			FechaAnteriorFechaActualException, PlanNoExisteException, ObjetivoNoExisteException {

		final Plan plan = generatePlan(Constantes.URL_JSON_PLAN_DATA, Constantes.MODIFICAR_PLAN);
		plan.getPlan().setFechaPlan(new Date());
		final PlanEntity planEntity = plan.getPlan();
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripción objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		plan.getPlan().setObjetivo(objetivo);

		String respuesta = StringUtils.EMPTY;

		planService.anadirPlan(plan);

		final ReturnBusquedas<PlanEntity> planModificar = planService.buscarPlan(planEntity.getNombrePlan(),
				planEntity.getDescripPlan(), planEntity.getFechaPlan(), planEntity.getObjetivo(), 0, 1);

		planModificar.getListaBusquedas().get(0).setDescripPlan("Descripcion modificada");

		plan.setPlan(planModificar.getListaBusquedas().get(0));

		respuesta = planService.modificarPlan(plan);

		assertEquals(Constantes.OK, respuesta);

		final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());
		planService.deletePlan(planModificar.getListaBusquedas().get(0));
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

	}

	@Test
	public void PlanService_modificarPlanNombrePlanVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			PlanYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException, PlanNoExisteException,
			ObjetivoNoExisteException {
		final Plan plan = generatePlan(Constantes.URL_JSON_PLAN_DATA, Constantes.NOMBRE_PLAN_VACIO);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripción objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		plan.getPlan().setObjetivo(objetivo);
		String respuesta = StringUtils.EMPTY;

		respuesta = planService.modificarPlan(plan);

		final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

		assertEquals(CodeMessageErrors.PLAN_VACIO.name(), respuesta);

	}

	@Test
	public void PlanService_modificarPlanDescripcionPlanVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			PlanYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException, PlanNoExisteException,
			ObjetivoNoExisteException {
		final Plan plan = generatePlan(Constantes.URL_JSON_PLAN_DATA, Constantes.DESCRIPCION_PLAN_VACIO);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripción objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		plan.getPlan().setObjetivo(objetivo);
		String respuesta = StringUtils.EMPTY;

		respuesta = planService.modificarPlan(plan);

		final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

		assertEquals(CodeMessageErrors.PLAN_VACIO.name(), respuesta);

	}

	@Test
	public void PlanService_modificarPlanFechaPlanVacio()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			PlanYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException, PlanNoExisteException,
			ObjetivoNoExisteException {
		final Plan plan = generatePlan(Constantes.URL_JSON_PLAN_DATA, Constantes.FECHA_PLAN_VACIA);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripción objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		plan.getPlan().setObjetivo(objetivo);
		String respuesta = StringUtils.EMPTY;

		respuesta = planService.modificarPlan(plan);

		final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

		assertEquals(CodeMessageErrors.PLAN_VACIO.name(), respuesta);

	}

	@Test
	public void PlanService_modificarPlanDatosVacios() throws IOException, ParseException,
			LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException, java.text.ParseException,
			PlanYaExisteException, FechaAnteriorFechaActualException, PlanNoExisteException, ObjetivoNoExisteException {
		final Plan plan = generatePlan(Constantes.URL_JSON_PLAN_DATA, Constantes.DATOS_PLAN_VACIOS);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripción objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		plan.getPlan().setObjetivo(objetivo);
		String respuesta = StringUtils.EMPTY;

		respuesta = planService.modificarPlan(plan);

		final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

		assertEquals(CodeMessageErrors.PLAN_VACIO.name(), respuesta);
	}

	@Test(expected = FechaAnteriorFechaActualException.class)
	public void PlanService_modificarPlanFechaPlanAnteriorActual()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			PlanYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException, PlanNoExisteException,
			ObjetivoNoExisteException {
		String fechaActualString = StringUtils.EMPTY;

		final Plan plan = generatePlan(Constantes.URL_JSON_PLAN_DATA,
				Constantes.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripción objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		plan.getPlan().setObjetivo(objetivo);
		final Date fechaAnteriorActual = plan.getPlan().getFechaPlan();
		final LocalDate fechaActual = LocalDate.now();
		plan.getPlan().setFechaPlan(new Date());
		if (CommonUtilities.countDigit(fechaActual.getDayOfMonth()) == 1) {
			fechaActualString = fechaActual.getYear() + "-0" + fechaActual.getMonthValue() + "-0"
					+ fechaActual.getDayOfMonth();
		} else {
			fechaActualString = fechaActual.getYear() + "-0" + fechaActual.getMonthValue() + "-"
					+ fechaActual.getDayOfMonth();
		}

		planService.anadirPlan(plan);

		final List<PlanEntity> planEncontrado = planRepository.findPlan(plan.getPlan().getNombrePlan(),
				plan.getPlan().getDescripPlan(), fechaActualString, plan.getPlan().getObjetivo());

		planEncontrado.get(0).setFechaPlan(fechaAnteriorActual);

		try {
			planService.modificarPlan(new Plan(plan.getUsuario(), planEncontrado.get(0)));
		} catch (final FechaAnteriorFechaActualException fechaAnteriorFechaActualException) {
			throw new FechaAnteriorFechaActualException(
					CodeMessageErrors.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL.getCodigo(),
					CodeMessageErrors.getTipoNameByCodigo(
							CodeMessageErrors.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL.getCodigo()));
		} finally {
			final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository
					.findObjetivo(objetivo.getNombreObjetivo(), objetivo.getDescripObjetivo());
			final List<PlanEntity> planEliminar = planRepository.findPlan(plan.getPlan().getNombrePlan(),
					plan.getPlan().getDescripPlan(), fechaActualString, plan.getPlan().getObjetivo());
			planRepository.deletePlan(planEliminar.get(0).getIdPlan());
			objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());
		}
	}

	@Test(expected = PlanNoExisteException.class)
	public void PlanService_modificarPlanNoExiste() throws IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, ObjetivoNoExisteException, java.text.ParseException,
			PlanNoExisteException, FechaAnteriorFechaActualException {

		final Plan plan = generatePlan(Constantes.URL_JSON_PLAN_DATA, Constantes.PLAN_NO_EXISTE);

		planService.modificarPlan(plan);

	}

	@Test
	public void PlanService_eliminarPlanCorrecto()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			java.text.ParseException, PlanYaExisteException, FechaAnteriorFechaActualException, PlanNoExisteException,
			ObjetivoNoExisteException, PlanAsociadoProcedimientoException {
		final Plan plan = generatePlan(Constantes.URL_JSON_PLAN_DATA, Constantes.ELIMINAR_PLAN);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripción objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		plan.getPlan().setObjetivo(objetivo);
		final PlanEntity planEntity = plan.getPlan();
		planEntity.setFechaPlan(new Date());

		planService.anadirPlan(plan);

		final ReturnBusquedas<PlanEntity> planEliminar = planService.buscarPlan(planEntity.getNombrePlan(),
				planEntity.getDescripPlan(), planEntity.getFechaPlan(), planEntity.getObjetivo(), 0, 1);
		planEliminar.getListaBusquedas().get(0).setBorradoPlan(1);

		final String respuesta = planService
				.eliminaPlan(new Plan(plan.getUsuario(), planEliminar.getListaBusquedas().get(0)));

		final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());

		planService.deletePlan(planEliminar.getListaBusquedas().get(0));
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

		assertEquals(Constantes.OK, respuesta);

	}

	@Test(expected = PlanNoExisteException.class)
	public void PlanService_eliminarPlanNoExiste() throws IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, java.text.ParseException, PlanNoExisteException {

		final Plan plan = generatePlan(Constantes.URL_JSON_PLAN_DATA, Constantes.PLAN_NO_EXISTE);

		planService.deletePlan(plan.getPlan());

	}

	@Test(expected = PlanAsociadoProcedimientoException.class)
	public void PlanService_eliminarPlanAsociadoProcedimiento()
			throws IOException, ParseException, LogAccionesNoGuardadoException, LogExcepcionesNoGuardadoException,
			PlanYaExisteException, java.text.ParseException, FechaAnteriorFechaActualException,
			ObjetivoNoExisteException, PlanNoExisteException, PlanAsociadoProcedimientoException {

		final Plan plan = generatePlan(Constantes.URL_JSON_PLAN_DATA, Constantes.PLAN_ASOCIADO_PROCEDIMIENTO);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripción objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		plan.getPlan().setFechaPlan(new Date());
		plan.getPlan().setObjetivo(objetivo);
		planService.anadirPlan(plan);

		final ReturnBusquedas<PlanEntity> planEliminar = planService.buscarPlan(plan.getPlan().getNombrePlan(),
				plan.getPlan().getDescripPlan(), new Date(), objetivo, 0, 1);

		final ProcedimientoEntity procedimiento = new ProcedimientoEntity("Nombre procedimiento",
				"Descripción procedimiento", new Date(), 0, Boolean.FALSE);
		procedimiento.setPlan(planEliminar.getListaBusquedas().get(0));
		procedimientoRepository.saveAndFlush(procedimiento);

		try {
			planService.eliminaPlan(new Plan(plan.getUsuario(), planEliminar.getListaBusquedas().get(0)));
		} catch (final PlanAsociadoProcedimientoException planAsociadoProcedimientoException) {
			throw new PlanAsociadoProcedimientoException(
					CodeMessageErrors.PLAN_ASOCIADO_PROCEDIMIENTO_EXCEPTION.getCodigo(), CodeMessageErrors
							.getTipoNameByCodigo(CodeMessageErrors.PLAN_ASOCIADO_PROCEDIMIENTO_EXCEPTION.getCodigo()));
		} finally {
			final ObjetivoEntity objetivoDelete = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());
			final PlanEntity planDelete = planRepository.findPlanByName(plan.getPlan().getNombrePlan());
			final ProcedimientoEntity procedimientoDelete = procedimientoRepository
					.findProcedimientoByName(procedimiento.getNombreProcedimiento());
			procedimientoRepository.deleteProcedimiento(procedimientoDelete.getIdProcedimiento());
			planRepository.deletePlan(planDelete.getIdPlan());
			objetivoRepository.deleteObjetivo(objetivoDelete.getIdObjetivo());
		}

	}

	@Test
	public void PlanService_reactivarPlanCorrecto() throws IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, java.text.ParseException, PlanYaExisteException,
			FechaAnteriorFechaActualException, PlanNoExisteException, ObjetivoNoExisteException {
		final Plan plan = generatePlan(Constantes.URL_JSON_PLAN_DATA, Constantes.REACTIVAR_PLAN);
		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripción objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		plan.getPlan().setObjetivo(objetivo);
		final PlanEntity planEntity = plan.getPlan();
		planEntity.setFechaPlan(new Date());

		plan.getPlan().setBorradoPlan(1);
		planService.anadirPlan(plan);

		final ReturnBusquedas<PlanEntity> planReactivar = planService.buscarPlan(planEntity.getNombrePlan(),
				planEntity.getDescripPlan(), planEntity.getFechaPlan(), planEntity.getObjetivo(), 0, 1);

		planReactivar.getListaBusquedas().get(0).setBorradoPlan(0);

		final String respuesta = planService
				.reactivarPlan(new Plan(plan.getUsuario(), planReactivar.getListaBusquedas().get(0)));

		final List<ObjetivoEntity> objetivoEncontrado = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());

		planService.deletePlan(planReactivar.getListaBusquedas().get(0));
		objetivoRepository.deleteObjetivo(objetivoEncontrado.get(0).getIdObjetivo());

		assertEquals(Constantes.OK, respuesta);

	}

	@Test(expected = PlanNoExisteException.class)
	public void PlanService_reactivarPlanNoExiste() throws IOException, ParseException, LogAccionesNoGuardadoException,
			LogExcepcionesNoGuardadoException, java.text.ParseException, PlanNoExisteException {

		final Plan plan = generatePlan(Constantes.URL_JSON_PLAN_DATA, Constantes.PLAN_NO_EXISTE);

		planService.reactivarPlan(plan);

	}

	private Plan generatePlan(final String fichero, final String nombrePrueba)
			throws IOException, ParseException, java.text.ParseException {

		final JSONObject jsonPlanVacios = new Util().getDatosJson(fichero, nombrePrueba);

		final Plan plan = new Plan();
		final PlanEntity planEntity = new PlanEntity();

		final SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

		final Date date;
		if (jsonPlanVacios.get(Constantes.FECHA_PLAN).toString().equals(StringUtils.EMPTY)) {
			date = formato.parse("0000-00-00");
		} else {
			date = formato.parse(
					CommonUtilities.coalesce(jsonPlanVacios.get(Constantes.FECHA_PLAN).toString(), StringUtils.EMPTY));
		}

		final String idPlan = CommonUtilities
				.coalesce(jsonPlanVacios.get(Constantes.PLAN_ID).toString(), StringUtils.EMPTY).toString();

		if (idPlan.equals(StringUtils.EMPTY)) {
			planEntity.setIdPlan(0);
		} else {
			planEntity.setIdPlan(Integer.parseInt(idPlan));
		}

		planEntity.setNombrePlan(
				CommonUtilities.coalesce(jsonPlanVacios.get(Constantes.NOMBRE_PLAN).toString(), StringUtils.EMPTY));
		planEntity.setDescripPlan(CommonUtilities.coalesce(jsonPlanVacios.get(Constantes.DESCRIPCION_PLAN).toString(),
				StringUtils.EMPTY));
		planEntity.setFechaPlan(date);
		planEntity.setBorradoPlan(0);
		plan.setUsuario(CommonUtilities.coalesce(jsonPlanVacios.get(Constantes.USUARIO).toString(), StringUtils.EMPTY));
		plan.setPlan(planEntity);

		return plan;

	}
}
