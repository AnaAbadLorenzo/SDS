package com.sds.service.test.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.model.ObjetivoEntity;
import com.sds.model.PlanEntity;
import com.sds.repository.ObjetivoRepository;
import com.sds.repository.PlanRepository;
import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.DefinicionPruebas;
import com.sds.service.common.Mensajes;
import com.sds.service.test.TestPlanService;
import com.sds.service.test.impl.atributos.TestAtributoDescripcionPlan;
import com.sds.service.test.impl.atributos.TestAtributoFechaPlan;
import com.sds.service.test.impl.atributos.TestAtributoNombrePlan;
import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;
import com.sds.service.test.util.CrearDatosPruebaAcciones;
import com.sds.service.test.util.GenerarJSON;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class TestPlanServiceImpl implements TestPlanService {

	private final TestAtributoNombrePlan testAtributoNombrePlan;
	private final TestAtributoDescripcionPlan testAtributoDescripcionPlan;
	private final TestAtributoFechaPlan testAtributoFechaPlan;
	private final CrearDatosPruebaAcciones crearDatosPruebaAcciones;
	private final GenerarJSON generarJSON;
	private final Validaciones validaciones;

	@Autowired
	PlanRepository planRepository;

	@Autowired
	ObjetivoRepository objetivoRepository;

	public TestPlanServiceImpl() {
		testAtributoNombrePlan = new TestAtributoNombrePlan();
		testAtributoDescripcionPlan = new TestAtributoDescripcionPlan();
		testAtributoFechaPlan = new TestAtributoFechaPlan();
		crearDatosPruebaAcciones = new CrearDatosPruebaAcciones();
		generarJSON = new GenerarJSON();
		validaciones = new Validaciones();
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoNombrePlan()
			throws IOException, ParseException, java.text.ParseException {

		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final PlanEntity datosEntradaNombrePlanVacio = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_ATRIBUTOS_NOMBRE_PLAN, Constantes.NOMBRE_PLAN_VACIO);
		final PlanEntity datosEntradaNombrePlanCaracteresEspeciales = generarJSON.generarPlan(
				Constantes.URL_JSON_PLAN_ATRIBUTOS_NOMBRE_PLAN, Constantes.NOMBRE_PLAN_CARACTERESESPECIALES);
		final PlanEntity datosEntradaNombrePlanMenor3 = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_ATRIBUTOS_NOMBRE_PLAN, Constantes.NOMBRE_PLAN_MENOR_3);
		final PlanEntity datosEntradaNombrePlanMayor48 = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_ATRIBUTOS_NOMBRE_PLAN, Constantes.NOMBRE_PLAN_MAYOR_48);
		final PlanEntity datosEntradaNombrePlanAlfabeticoCorrecto = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_ATRIBUTOS_NOMBRE_PLAN, Constantes.NOMBRE_PLAN_CORRECTO);

		datosPruebaAtributos.add(testAtributoNombrePlan.getTestNombrePlanVacio(datosEntradaNombrePlanVacio));
		datosPruebaAtributos.add(testAtributoNombrePlan
				.getTestNombrePlanAlfabeticoCaracteresEspeciales(datosEntradaNombrePlanCaracteresEspeciales));
		datosPruebaAtributos
				.add(testAtributoNombrePlan.getTestNombrePlanAlfabeticoMenor3(datosEntradaNombrePlanMenor3));
		datosPruebaAtributos
				.add(testAtributoNombrePlan.getTestNombrePlanAlfabeticoMayor48(datosEntradaNombrePlanMayor48));
		datosPruebaAtributos.add(
				testAtributoNombrePlan.getTestNombrePlanCorrectoAlfabetico(datosEntradaNombrePlanAlfabeticoCorrecto));

		return datosPruebaAtributos;

	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoNombrePlanBuscar()
			throws IOException, ParseException, java.text.ParseException {

		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final PlanEntity datosEntradaNombrePlanCaracteresEspeciales = generarJSON.generarPlan(
				Constantes.URL_JSON_PLAN_ATRIBUTOS_NOMBRE_PLAN, Constantes.NOMBRE_PLAN_CARACTERESESPECIALES);
		final PlanEntity datosEntradaNombrePlanMayor48 = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_ATRIBUTOS_NOMBRE_PLAN, Constantes.NOMBRE_PLAN_MAYOR_48);
		final PlanEntity datosEntradaNombrePlanAlfabeticoCorrecto = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_ATRIBUTOS_NOMBRE_PLAN, Constantes.NOMBRE_PLAN_CORRECTO);

		datosPruebaAtributos.add(testAtributoNombrePlan
				.getTestNombrePlanAlfabeticoCaracteresEspeciales(datosEntradaNombrePlanCaracteresEspeciales));
		datosPruebaAtributos
				.add(testAtributoNombrePlan.getTestNombrePlanAlfabeticoMayor48(datosEntradaNombrePlanMayor48));
		datosPruebaAtributos.add(
				testAtributoNombrePlan.getTestNombrePlanCorrectoAlfabetico(datosEntradaNombrePlanAlfabeticoCorrecto));

		return datosPruebaAtributos;

	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoDescripPlan()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final PlanEntity datosEntradaDescripcionPlanVacio = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_ATRIBUTOS_DESCRIPCION_PLAN, Constantes.DESCRIPCION_PLAN_VACIO);
		final PlanEntity datosEntradaDescripcionPlanCaracteresEspeciales = generarJSON.generarPlan(
				Constantes.URL_JSON_PLAN_ATRIBUTOS_DESCRIPCION_PLAN, Constantes.DESCRIPCION_PLAN_CARACTERESESPECIALES);
		final PlanEntity datosEntradaDescripcionPlanMenor3 = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_ATRIBUTOS_DESCRIPCION_PLAN, Constantes.DESCRIPCION_PLAN_MENOR_3);
		final PlanEntity datosEntradaDescripcionPlanAlfabeticoCorrecto = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_ATRIBUTOS_DESCRIPCION_PLAN, Constantes.DESCRIPCION_PLAN_CORRECTO);

		datosPruebaAtributos
				.add(testAtributoDescripcionPlan.getTestDescripcionPlanVacio(datosEntradaDescripcionPlanVacio));
		datosPruebaAtributos.add(testAtributoDescripcionPlan
				.getTestDescripcionPlanAlfabeticoCaracteresEspeciales(datosEntradaDescripcionPlanCaracteresEspeciales));
		datosPruebaAtributos.add(
				testAtributoDescripcionPlan.getTestDescripcionPlanAlfabeticoMenor3(datosEntradaDescripcionPlanMenor3));
		datosPruebaAtributos.add(testAtributoDescripcionPlan
				.getTestDescripcionPlanAlfabeticoCorrecto(datosEntradaDescripcionPlanAlfabeticoCorrecto));

		return datosPruebaAtributos;

	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoDescripPlanBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final PlanEntity datosEntradaDescripcionPlanCaracteresEspeciales = generarJSON.generarPlan(
				Constantes.URL_JSON_PLAN_ATRIBUTOS_DESCRIPCION_PLAN, Constantes.DESCRIPCION_PLAN_CARACTERESESPECIALES);
		final PlanEntity datosEntradaDescripcionPlanAlfabeticoCorrecto = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_ATRIBUTOS_DESCRIPCION_PLAN, Constantes.DESCRIPCION_PLAN_CORRECTO);

		datosPruebaAtributos.add(testAtributoDescripcionPlan
				.getTestDescripcionPlanAlfabeticoCaracteresEspeciales(datosEntradaDescripcionPlanCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoDescripcionPlan
				.getTestDescripcionPlanAlfabeticoCorrecto(datosEntradaDescripcionPlanAlfabeticoCorrecto));

		return datosPruebaAtributos;

	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoFechaPlan()
			throws IOException, ParseException, java.text.ParseException {

		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final PlanEntity datosEntradaFechaPlanVacia = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_ATRIBUTOS_FECHA_PLAN, Constantes.FECHA_PLAN_VACIA);
		final PlanEntity datosEntradaFechaPlanAcentos = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_ATRIBUTOS_FECHA_PLAN, Constantes.FECHA_PLAN_ACENTOS);
		final PlanEntity datosEntradaFechaPlanEnhe = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_ATRIBUTOS_FECHA_PLAN, Constantes.FECHA_PLAN_ENHE);
		final PlanEntity datosEntradaFechaPlanCaracteresEspeciales = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_ATRIBUTOS_FECHA_PLAN, Constantes.FECHA_PLAN_CARACTERESESPECIALES);
		final PlanEntity datosEntradaFechaPlanMenor8 = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_ATRIBUTOS_FECHA_PLAN, Constantes.FECHA_PLAN_MENOR_8);
		final PlanEntity datosEntradaFechaPlanMayor8 = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_ATRIBUTOS_FECHA_PLAN, Constantes.FECHA_PLAN_MAYOR_8);
		final PlanEntity datosEntradaFechaPlanMenorFechaActual = generarJSON.generarPlan(
				Constantes.URL_JSON_PLAN_ATRIBUTOS_FECHA_PLAN, Constantes.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL);
		final PlanEntity datosEntradaFechaPlanNumericoCorrecto = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_ATRIBUTOS_FECHA_PLAN, Constantes.FECHA_PLAN_CORRECTO);

		datosPruebaAtributos.add(testAtributoFechaPlan.getTestFechaPlanVacio(datosEntradaFechaPlanVacia));
		datosPruebaAtributos.add(testAtributoFechaPlan.getTestFechaPlanNumericoAcentos(datosEntradaFechaPlanAcentos));
		datosPruebaAtributos.add(testAtributoFechaPlan.getTestFechaPlanNumericoEnhe(datosEntradaFechaPlanEnhe));
		datosPruebaAtributos.add(testAtributoFechaPlan
				.getTestFechaPlanNumericoCaracteresEspeciales(datosEntradaFechaPlanCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoFechaPlan.getTestFechaPlanAlfanumericoMenor8(datosEntradaFechaPlanMenor8));
		datosPruebaAtributos.add(testAtributoFechaPlan.getTestFechaPlanAlfanumericoMayor8(datosEntradaFechaPlanMayor8));
		datosPruebaAtributos
				.add(testAtributoFechaPlan.getTestFechaPlanMenorFechaActual(datosEntradaFechaPlanMenorFechaActual));
		datosPruebaAtributos
				.add(testAtributoFechaPlan.getTestFechaPlanCorrectoNumerico(datosEntradaFechaPlanNumericoCorrecto));

		return datosPruebaAtributos;

	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoFechaPlanBuscar()
			throws IOException, ParseException, java.text.ParseException {

		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final PlanEntity datosEntradaFechaPlanAcentos = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_ATRIBUTOS_FECHA_PLAN, Constantes.FECHA_PLAN_ACENTOS);
		final PlanEntity datosEntradaFechaPlanEnhe = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_ATRIBUTOS_FECHA_PLAN, Constantes.FECHA_PLAN_ENHE);
		final PlanEntity datosEntradaFechaPlanCaracteresEspeciales = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_ATRIBUTOS_FECHA_PLAN, Constantes.FECHA_PLAN_CARACTERESESPECIALES);
		final PlanEntity datosEntradaFechaPlanMayor8 = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_ATRIBUTOS_FECHA_PLAN, Constantes.FECHA_PLAN_MAYOR_8);
		final PlanEntity datosEntradaFechaPlanNumericoCorrecto = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_ATRIBUTOS_FECHA_PLAN, Constantes.FECHA_PLAN_CORRECTO);

		datosPruebaAtributos.add(testAtributoFechaPlan.getTestFechaPlanNumericoAcentos(datosEntradaFechaPlanAcentos));
		datosPruebaAtributos.add(testAtributoFechaPlan.getTestFechaPlanNumericoEnhe(datosEntradaFechaPlanEnhe));
		datosPruebaAtributos.add(testAtributoFechaPlan
				.getTestFechaPlanNumericoCaracteresEspeciales(datosEntradaFechaPlanCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoFechaPlan.getTestFechaPlanAlfanumericoMayor8(datosEntradaFechaPlanMayor8));
		datosPruebaAtributos
				.add(testAtributoFechaPlan.getTestFechaPlanCorrectoNumerico(datosEntradaFechaPlanNumericoCorrecto));

		return datosPruebaAtributos;

	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesPlanBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final PlanEntity datosEntradaBuscarPlanCorrecto = generarJSON.generarPlan(Constantes.URL_JSON_PLAN_DATA,
				Constantes.BUSCAR_PLAN);
		final PlanEntity datosEntradaBuscarNombrePlanCorrecto = generarJSON.generarPlan(Constantes.URL_JSON_PLAN_DATA,
				Constantes.NOMBRE_PLAN_VACIO);
		final PlanEntity datosEntradaBuscarDescripPlanCorrecto = generarJSON.generarPlan(Constantes.URL_JSON_PLAN_DATA,
				Constantes.DESCRIPCION_PLAN_VACIO);
		final PlanEntity datosEntradaBuscarFechaPlanCorrecto = generarJSON.generarPlan(Constantes.URL_JSON_PLAN_DATA,
				Constantes.FECHA_PLAN_VACIA);
		final PlanEntity datosEntradaBuscarDatosVacios = generarJSON.generarPlan(Constantes.URL_JSON_PLAN_DATA,
				Constantes.DATOS_PLAN_VACIOS);

		datosPruebaAcciones.add(getTestBuscarPlan(datosEntradaBuscarPlanCorrecto));
		datosPruebaAcciones.add(getTestBuscarPlan(datosEntradaBuscarNombrePlanCorrecto));
		datosPruebaAcciones.add(getTestBuscarPlan(datosEntradaBuscarDescripPlanCorrecto));
		datosPruebaAcciones.add(getTestBuscarPlan(datosEntradaBuscarFechaPlanCorrecto));
		datosPruebaAcciones.add(getTestBuscarPlan(datosEntradaBuscarDatosVacios));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesPlanGuardar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final PlanEntity datosEntradaGuardarPlanCorrecto = generarJSON.generarPlan(Constantes.URL_JSON_PLAN_DATA,
				Constantes.GUARDAR_PLAN);
		final PlanEntity datosEntradaGuardarPlanYaExiste = generarJSON.generarPlan(Constantes.URL_JSON_PLAN_DATA,
				Constantes.PLAN_YA_EXISTE);
		final PlanEntity datosEntradaGuardarNombrePlanVacio = generarJSON.generarPlan(Constantes.URL_JSON_PLAN_DATA,
				Constantes.NOMBRE_PLAN_VACIO);
		final PlanEntity datosEntradaGuardarDescripcionPlanVacia = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_DATA, Constantes.DESCRIPCION_PLAN_VACIO);
		final PlanEntity datosEntradaGuardarFechaPlanVacia = generarJSON.generarPlan(Constantes.URL_JSON_PLAN_DATA,
				Constantes.FECHA_PLAN_VACIA);
		final PlanEntity datosEntradaGuardarDatosPlanVacios = generarJSON.generarPlan(Constantes.URL_JSON_PLAN_DATA,
				Constantes.DATOS_PLAN_VACIOS);
		final PlanEntity datosEntradaGuardarFechaPlanAnteriorFechaActual = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_DATA, Constantes.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL);

		datosPruebaAcciones.add(getTestGuardarPlanCorrecto(datosEntradaGuardarPlanCorrecto));
		datosPruebaAcciones.add(getTestGuardarPlanYaExiste(datosEntradaGuardarPlanYaExiste));
		datosPruebaAcciones.add(getTestGuardarNombrePlanVacio(datosEntradaGuardarNombrePlanVacio));
		datosPruebaAcciones.add(getTestGuardarDescripcionPlanVacio(datosEntradaGuardarDescripcionPlanVacia));
		datosPruebaAcciones.add(getTestGuardarFechaPlanVacio(datosEntradaGuardarFechaPlanVacia));
		datosPruebaAcciones.add(getTestGuardarNombreDescripcionFechaPlanVacio(datosEntradaGuardarDatosPlanVacios));
		datosPruebaAcciones
				.add(getTestGuardarFechaPlanAnteriorFechaActual(datosEntradaGuardarFechaPlanAnteriorFechaActual));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesPlanModificar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final PlanEntity datosEntradaModificarPlanCorrecto = generarJSON.generarPlan(Constantes.URL_JSON_PLAN_DATA,
				Constantes.MODIFICAR_PLAN);
		final PlanEntity datosEntradaModificarPlanNoExiste = generarJSON.generarPlan(Constantes.URL_JSON_PLAN_DATA,
				Constantes.PLAN_NO_EXISTE);
		final PlanEntity datosEntradaModificarNombrePlanVacio = generarJSON.generarPlan(Constantes.URL_JSON_PLAN_DATA,
				Constantes.NOMBRE_PLAN_VACIO);
		final PlanEntity datosEntradaModificarDescripcionPlanVacia = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_DATA, Constantes.DESCRIPCION_PLAN_VACIO);
		final PlanEntity datosEntradaModificarFechaPlanVacia = generarJSON.generarPlan(Constantes.URL_JSON_PLAN_DATA,
				Constantes.FECHA_PLAN_VACIA);
		final PlanEntity datosEntradaModificarDatosPlanVacios = generarJSON.generarPlan(Constantes.URL_JSON_PLAN_DATA,
				Constantes.DATOS_PLAN_VACIOS);
		final PlanEntity datosEntradaModificarFechaPlanAnteriorFechaActual = generarJSON
				.generarPlan(Constantes.URL_JSON_PLAN_DATA, Constantes.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL);

		datosPruebaAcciones.add(getTestModificarPlanCorrecto(datosEntradaModificarPlanCorrecto));
		datosPruebaAcciones.add(getTestModificarPlanNoExiste(datosEntradaModificarPlanNoExiste));
		datosPruebaAcciones.add(getTestModificarNombrePlanVacio(datosEntradaModificarNombrePlanVacio));
		datosPruebaAcciones.add(getTestModificarDescripcionPlanVacio(datosEntradaModificarDescripcionPlanVacia));
		datosPruebaAcciones.add(getTestModificarFechaPlanVacio(datosEntradaModificarFechaPlanVacia));
		datosPruebaAcciones.add(getTestModificarNombreDescripcionFechaPlanVacio(datosEntradaModificarDatosPlanVacios));
		datosPruebaAcciones
				.add(getTestModificarFechaPlanAnteriorFechaActual(datosEntradaModificarFechaPlanAnteriorFechaActual));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesPlanEliminar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final PlanEntity datosEntradaEliminarPlanCorrecto = generarJSON.generarPlan(Constantes.URL_JSON_PLAN_DATA,
				Constantes.ELIMINAR_PLAN);
		final PlanEntity datosEntradaEliminarPlanNoExiste = generarJSON.generarPlan(Constantes.URL_JSON_PLAN_DATA,
				Constantes.PLAN_NO_EXISTE);

		datosPruebaAcciones.add(getTestEliminarPlanCorrecto(datosEntradaEliminarPlanCorrecto));
		datosPruebaAcciones.add(getTestEliminarPlanNoExiste(datosEntradaEliminarPlanNoExiste));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesPlanReactivar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final PlanEntity datosEntradaReactivarPlanCorrecto = generarJSON.generarPlan(Constantes.URL_JSON_PLAN_DATA,
				Constantes.REACTIVAR_PLAN);
		final PlanEntity datosEntradaReactivarPlanNoExiste = generarJSON.generarPlan(Constantes.URL_JSON_PLAN_DATA,
				Constantes.PLAN_NO_EXISTE);

		datosPruebaAcciones.add(getTestReactivarPlanCorrecto(datosEntradaReactivarPlanCorrecto));
		datosPruebaAcciones.add(getTestReactivarPlanNoExiste(datosEntradaReactivarPlanNoExiste));

		return datosPruebaAcciones;
	}

	private DatosPruebaAcciones getTestBuscarPlan(final PlanEntity datosEntradaAccionBuscarPlan) {

		final String resultadoObtenido = buscarPlan(datosEntradaAccionBuscarPlan);

		final String resultadoEsperado = CodigosMensajes.BUSCAR_PLAN_CORRECTO + " - "
				+ Mensajes.PLAN_BUSCADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.BUSCAR_CORRECTO, Constantes.EXITO, getValorPlan(datosEntradaAccionBuscarPlan));

	}

	private DatosPruebaAcciones getTestGuardarPlanCorrecto(final PlanEntity datosEntradaAccionGuardarPlan)
			throws java.text.ParseException {

		final String resultadoObtenido = guardarPlan(datosEntradaAccionGuardarPlan);

		final String resultadoEsperado = CodigosMensajes.GUARDAR_PLAN_CORRECTO + " - "
				+ Mensajes.PLAN_GUARDADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.GUARDAR_PLAN_CORRECTO, Constantes.EXITO, getValorPlan(datosEntradaAccionGuardarPlan));

	}

	private DatosPruebaAcciones getTestGuardarPlanYaExiste(final PlanEntity datosEntradaAccionGuardarPlanYaExiste)
			throws java.text.ParseException {

		final String resultadoObtenido = guardarPlan(datosEntradaAccionGuardarPlanYaExiste);

		final String resultadoEsperado = CodigosMensajes.PLAN_YA_EXISTE + " - " + Mensajes.PLAN_YA_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.PLAN_YA_EXISTE, Constantes.ERROR,
				getValorPlan(datosEntradaAccionGuardarPlanYaExiste));

	}

	private DatosPruebaAcciones getTestGuardarNombrePlanVacio(final PlanEntity datosEntradaAccionGuardarNombrePlanVacio)
			throws java.text.ParseException {

		final String resultadoObtenido = guardarPlan(datosEntradaAccionGuardarNombrePlanVacio);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_PLAN_VACIO + " - "
				+ Mensajes.NOMBRE_PLAN_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, getValorPlan(datosEntradaAccionGuardarNombrePlanVacio));

	}

	private DatosPruebaAcciones getTestGuardarDescripcionPlanVacio(
			final PlanEntity datosEntradaAccionGuardarDescripcionPlanVacio) throws java.text.ParseException {

		final String resultadoObtenido = guardarPlan(datosEntradaAccionGuardarDescripcionPlanVacio);

		final String resultadoEsperado = CodigosMensajes.DESCRIPCION_PLAN_VACIO + " - "
				+ Mensajes.DESCRIPCION_PLAN_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, getValorPlan(datosEntradaAccionGuardarDescripcionPlanVacio));

	}

	private DatosPruebaAcciones getTestGuardarFechaPlanVacio(final PlanEntity datosEntradaAccionGuardarFechaPlanVacio)
			throws java.text.ParseException {

		final String resultadoObtenido = guardarPlan(datosEntradaAccionGuardarFechaPlanVacio);

		final String resultadoEsperado = CodigosMensajes.FECHA_PLAN_VACIA + " - "
				+ Mensajes.FECHA_PLAN_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, getValorPlan(datosEntradaAccionGuardarFechaPlanVacio));

	}

	private DatosPruebaAcciones getTestGuardarNombreDescripcionFechaPlanVacio(
			final PlanEntity datosEntradaAccionGuardarNombreDescripcionFechaPlanVacio) throws java.text.ParseException {

		final String resultadoObtenido = guardarPlan(datosEntradaAccionGuardarNombreDescripcionFechaPlanVacio);

		final String resultadoEsperado = CodigosMensajes.PLAN_VACIO + " - " + Mensajes.PLAN_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorPlan(datosEntradaAccionGuardarNombreDescripcionFechaPlanVacio));
	}

	private DatosPruebaAcciones getTestGuardarFechaPlanAnteriorFechaActual(
			final PlanEntity datosEntradaAccionGuardarFechaPlanMenorFechaActual) throws java.text.ParseException {

		final String resultadoObtenido = guardarPlan(datosEntradaAccionGuardarFechaPlanMenorFechaActual);

		final String resultadoEsperado = CodigosMensajes.FECHA_INTRODUCIDA_MENOR_FECHA_ACTUAL + " - "
				+ Mensajes.FECHA_INTRODUCIDA_NO_PUEDE_SER_MENOR_QUE_FECHA_ACTUAL;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL, Constantes.ERROR,
				getValorPlan(datosEntradaAccionGuardarFechaPlanMenorFechaActual));
	}

	private DatosPruebaAcciones getTestModificarPlanCorrecto(final PlanEntity datosEntradaAccionModificarPlan)
			throws java.text.ParseException {

		final String resultadoObtenido = modificarPlan(datosEntradaAccionModificarPlan);

		final String resultadoEsperado = CodigosMensajes.MODIFICAR_PLAN_CORRECTO + " - "
				+ Mensajes.PLAN_MODIFICADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.MODIFICAR_PLAN_CORRECTO, Constantes.EXITO,
				getValorPlan(datosEntradaAccionModificarPlan));

	}

	private DatosPruebaAcciones getTestModificarPlanNoExiste(final PlanEntity datosEntradaAccionModificarPlanNoExiste)
			throws java.text.ParseException {

		final String resultadoObtenido = modificarPlanNoExiste(datosEntradaAccionModificarPlanNoExiste);

		final String resultadoEsperado = CodigosMensajes.PLAN_NO_EXISTE + " - " + Mensajes.PLAN_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.PLAN_NO_EXISTE, Constantes.ERROR,
				getValorPlan(datosEntradaAccionModificarPlanNoExiste));

	}

	private DatosPruebaAcciones getTestModificarNombrePlanVacio(
			final PlanEntity datosEntradaAccionModificarNombrePlanVacio) throws java.text.ParseException {

		final String resultadoObtenido = modificarPlan(datosEntradaAccionModificarNombrePlanVacio);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_PLAN_VACIO + " - "
				+ Mensajes.NOMBRE_PLAN_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, getValorPlan(datosEntradaAccionModificarNombrePlanVacio));

	}

	private DatosPruebaAcciones getTestModificarDescripcionPlanVacio(
			final PlanEntity datosEntradaAccionModificarDescripcionPlanVacio) throws java.text.ParseException {

		final String resultadoObtenido = modificarPlan(datosEntradaAccionModificarDescripcionPlanVacio);

		final String resultadoEsperado = CodigosMensajes.DESCRIPCION_PLAN_VACIO + " - "
				+ Mensajes.DESCRIPCION_PLAN_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorPlan(datosEntradaAccionModificarDescripcionPlanVacio));

	}

	private DatosPruebaAcciones getTestModificarFechaPlanVacio(
			final PlanEntity datosEntradaAccionModificarFechaPlanVacio) throws java.text.ParseException {

		final String resultadoObtenido = modificarPlan(datosEntradaAccionModificarFechaPlanVacio);

		final String resultadoEsperado = CodigosMensajes.FECHA_PLAN_VACIA + " - "
				+ Mensajes.FECHA_PLAN_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, getValorPlan(datosEntradaAccionModificarFechaPlanVacio));

	}

	private DatosPruebaAcciones getTestModificarNombreDescripcionFechaPlanVacio(
			final PlanEntity datosEntradaAccionModificarNombreDescripcionFechaPlanVacio)
			throws java.text.ParseException {

		final String resultadoObtenido = modificarPlan(datosEntradaAccionModificarNombreDescripcionFechaPlanVacio);

		final String resultadoEsperado = CodigosMensajes.PLAN_VACIO + " - " + Mensajes.PLAN_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorPlan(datosEntradaAccionModificarNombreDescripcionFechaPlanVacio));
	}

	private DatosPruebaAcciones getTestModificarFechaPlanAnteriorFechaActual(
			final PlanEntity datosEntradaAccionModificarfechaPlanMenorFechaActual) throws java.text.ParseException {

		final String resultadoObtenido = modificarPlan(datosEntradaAccionModificarfechaPlanMenorFechaActual);

		final String resultadoEsperado = CodigosMensajes.FECHA_INTRODUCIDA_MENOR_FECHA_ACTUAL + " - "
				+ Mensajes.FECHA_INTRODUCIDA_NO_PUEDE_SER_MENOR_QUE_FECHA_ACTUAL;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL, Constantes.ERROR,
				getValorPlan(datosEntradaAccionModificarfechaPlanMenorFechaActual));
	}

	private DatosPruebaAcciones getTestEliminarPlanCorrecto(final PlanEntity datosEntradaAccionEliminarPlanCorrecto)
			throws java.text.ParseException {

		final String resultadoObtenido = eliminarPlan(datosEntradaAccionEliminarPlanCorrecto);

		final String resultadoEsperado = CodigosMensajes.ELIMINAR_PLAN_CORRECTO + " - "
				+ Mensajes.PLAN_ELIMINADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ELIMINAR_PLAN_CORRECTO, Constantes.ERROR,
				getValorPlan(datosEntradaAccionEliminarPlanCorrecto));

	}

	private DatosPruebaAcciones getTestEliminarPlanNoExiste(final PlanEntity datosEntradaAccionEliminarPlanNoExiste)
			throws java.text.ParseException {

		final String resultadoObtenido = eliminarPlanNoExiste(datosEntradaAccionEliminarPlanNoExiste);

		final String resultadoEsperado = CodigosMensajes.PLAN_NO_EXISTE + " - " + Mensajes.PLAN_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.PLAN_NO_EXISTE, Constantes.ERROR,
				getValorPlan(datosEntradaAccionEliminarPlanNoExiste));
	}

	private DatosPruebaAcciones getTestReactivarPlanCorrecto(final PlanEntity datosEntradaAccionReactivarPlanCorrecto)
			throws java.text.ParseException {

		final String resultadoObtenido = reactivarPlan(datosEntradaAccionReactivarPlanCorrecto);

		final String resultadoEsperado = CodigosMensajes.REACTIVAR_PLAN_CORRECTO + " - "
				+ Mensajes.PLAN_REACTIVADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.REACTIVAR_PLAN_CORRECTO, Constantes.ERROR,
				getValorPlan(datosEntradaAccionReactivarPlanCorrecto));

	}

	private DatosPruebaAcciones getTestReactivarPlanNoExiste(final PlanEntity datosEntradaAccionReactivarPlanNoExiste)
			throws java.text.ParseException {

		final String resultadoObtenido = reactivarPlanNoExiste(datosEntradaAccionReactivarPlanNoExiste);

		final String resultadoEsperado = CodigosMensajes.PLAN_NO_EXISTE + " - " + Mensajes.PLAN_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.PLAN_NO_EXISTE, Constantes.ERROR,
				getValorPlan(datosEntradaAccionReactivarPlanNoExiste));
	}

	private String buscarPlan(final PlanEntity plan) {
		String resultado = StringUtils.EMPTY;

		java.sql.Date fechaSql;
		fechaSql = new java.sql.Date(plan.getFechaPlan().getTime());
		final String fecha = fechaSql.toString();

		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		plan.setObjetivo(objetivo);

		planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(), fecha, plan.getObjetivo());

		resultado = CodigosMensajes.BUSCAR_PLAN_CORRECTO + " - " + Mensajes.PLAN_BUSCADO_CORRECTAMENTE;

		final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());

		objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());

		return resultado;
	}

	private String guardarPlan(final PlanEntity plan) throws java.text.ParseException {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarNombrePlanBlank(plan.getNombrePlan())
				&& !validaciones.comprobarDescripPlanBlank(plan.getDescripPlan())
				&& !validaciones.comprobarFechaPlanBlank(plan.getFechaPlan())) {
			resultado = CodigosMensajes.PLAN_VACIO + " - " + Mensajes.PLAN_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarNombrePlanBlank(plan.getNombrePlan())) {
			resultado = CodigosMensajes.NOMBRE_PLAN_VACIO + " - " + Mensajes.NOMBRE_PLAN_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarDescripPlanBlank(plan.getDescripPlan())) {
			resultado = CodigosMensajes.DESCRIPCION_PLAN_VACIO + " - " + Mensajes.DESCRIPCION_PLAN_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarFechaPlanBlank(plan.getFechaPlan())) {
			resultado = CodigosMensajes.FECHA_PLAN_VACIA + " - " + Mensajes.FECHA_PLAN_NO_PUEDE_SER_VACIA;
		} else {
			final PlanEntity planBD = planRepository.findPlanByName(plan.getNombrePlan());

			if (planBD != null) {
				resultado = CodigosMensajes.PLAN_YA_EXISTE + " - " + Mensajes.PLAN_YA_EXISTE;
			} else {
				final LocalDate fechaActual = LocalDate.now();
				final String fechaIntroducidaUsuario = plan.getFechaPlan().toString();
				final String fechaActualString = fechaActual.getYear() + "-0" + fechaActual.getMonthValue() + "-"
						+ fechaActual.getDayOfMonth();

				if (fechaIntroducidaUsuario.compareTo(fechaActualString) < 0) {
					resultado = CodigosMensajes.FECHA_INTRODUCIDA_MENOR_FECHA_ACTUAL + " - "
							+ Mensajes.FECHA_INTRODUCIDA_NO_PUEDE_SER_MENOR_QUE_FECHA_ACTUAL;
				} else {
					plan.setBorradoPlan(0);
					final ObjetivoEntity objetivo = new ObjetivoEntity("Objetivo", "Objetivo de pruebas", 0);
					objetivoRepository.saveAndFlush(objetivo);
					plan.setObjetivo(objetivo);
					planRepository.saveAndFlush(plan);
					resultado = CodigosMensajes.GUARDAR_PLAN_CORRECTO + " - " + Mensajes.PLAN_GUARDADO_CORRECTAMENTE;

					final PlanEntity planBDNuevo = planRepository.findPlanByName(plan.getNombrePlan());
					final ObjetivoEntity objetivoBDNuevo = objetivoRepository
							.findObjetivoByName(objetivo.getNombreObjetivo());

					planRepository.delete(planBDNuevo);
					objetivoRepository.delete(objetivoBDNuevo);

				}

			}
		}

		return resultado;
	}

	private String modificarPlan(final PlanEntity plan) throws java.text.ParseException {
		String resultado = StringUtils.EMPTY;
		Boolean modificar = Boolean.FALSE;

		if (!validaciones.comprobarNombrePlanBlank(plan.getNombrePlan())
				&& !validaciones.comprobarDescripPlanBlank(plan.getDescripPlan())
				&& !validaciones.comprobarFechaPlanBlank(plan.getFechaPlan())) {
			resultado = CodigosMensajes.PLAN_VACIO + " - " + Mensajes.PLAN_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarNombrePlanBlank(plan.getNombrePlan())) {
			resultado = CodigosMensajes.NOMBRE_PLAN_VACIO + " - " + Mensajes.NOMBRE_PLAN_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarDescripPlanBlank(plan.getDescripPlan())) {
			resultado = CodigosMensajes.DESCRIPCION_PLAN_VACIO + " - " + Mensajes.DESCRIPCION_PLAN_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarFechaPlanBlank(plan.getFechaPlan())) {
			resultado = CodigosMensajes.FECHA_PLAN_VACIA + " - " + Mensajes.FECHA_PLAN_NO_PUEDE_SER_VACIA;
		} else {

			final ObjetivoEntity objetivo = new ObjetivoEntity("Objetivo", "Objetivo de pruebas", 0);
			objetivoRepository.saveAndFlush(objetivo);
			plan.setObjetivo(objetivo);
			planRepository.saveAndFlush(plan);

			final PlanEntity planBD = planRepository.findPlanByName(plan.getNombrePlan());

			final LocalDate fechaActual = LocalDate.now();
			final String fechaIntroducidaUsuario = plan.getFechaPlan().toString();
			final String fechaActualString = fechaActual.getYear() + "-0" + fechaActual.getMonthValue() + "-"
					+ fechaActual.getDayOfMonth();

			if (!fechaIntroducidaUsuario.equals(planBD.getFechaPlan().toString())) {
				if (fechaIntroducidaUsuario.compareTo(fechaActualString) < 0) {
					resultado = CodigosMensajes.FECHA_INTRODUCIDA_MENOR_FECHA_ACTUAL + " - "
							+ Mensajes.FECHA_INTRODUCIDA_NO_PUEDE_SER_MENOR_QUE_FECHA_ACTUAL;
				} else {
					modificar = true;
				}
			} else {
				modificar = true;
			}

			if (Boolean.TRUE.equals(modificar)) {
				planBD.setDescripPlan("Descripción modificada");

				planRepository.saveAndFlush(planBD);

				resultado = CodigosMensajes.MODIFICAR_PLAN_CORRECTO + " - " + Mensajes.PLAN_MODIFICADO_CORRECTAMENTE;

			}

			final PlanEntity planBDNuevo = planRepository.findPlanByName(plan.getNombrePlan());
			final ObjetivoEntity objetivoBDNuevo = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());

			planRepository.delete(planBDNuevo);
			objetivoRepository.delete(objetivoBDNuevo);

		}

		return resultado;
	}

	private String modificarPlanNoExiste(final PlanEntity plan) throws java.text.ParseException {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarNombrePlanBlank(plan.getNombrePlan())
				&& !validaciones.comprobarDescripPlanBlank(plan.getDescripPlan())
				&& !validaciones.comprobarFechaPlanBlank(plan.getFechaPlan())) {
			resultado = CodigosMensajes.PLAN_VACIO + " - " + Mensajes.PLAN_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarNombrePlanBlank(plan.getNombrePlan())) {
			resultado = CodigosMensajes.NOMBRE_PLAN_VACIO + " - " + Mensajes.NOMBRE_PLAN_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarDescripPlanBlank(plan.getDescripPlan())) {
			resultado = CodigosMensajes.DESCRIPCION_PLAN_VACIO + " - " + Mensajes.DESCRIPCION_PLAN_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarFechaPlanBlank(plan.getFechaPlan())) {
			resultado = CodigosMensajes.FECHA_PLAN_VACIA + " - " + Mensajes.FECHA_PLAN_NO_PUEDE_SER_VACIA;
		} else {

			final PlanEntity planBD = planRepository.findPlanByName(plan.getNombrePlan());

			if (planBD == null) {
				resultado = CodigosMensajes.PLAN_NO_EXISTE + " - " + Mensajes.PLAN_NO_EXISTE;
			}

		}

		return resultado;
	}

	private String eliminarPlan(final PlanEntity plan) {
		final PlanEntity planBD = planRepository.findPlanByName(plan.getNombrePlan());
		String resultado = StringUtils.EMPTY;

		if (planBD == null) {

			plan.setBorradoPlan(0);
			final ObjetivoEntity objetivo = new ObjetivoEntity("Objetivo", "Objetivo de pruebas", 0);
			objetivoRepository.saveAndFlush(objetivo);
			plan.setObjetivo(objetivo);
			planRepository.saveAndFlush(plan);

			final PlanEntity planBDABorrar = planRepository.findPlanByName(plan.getNombrePlan());

			planBDABorrar.setBorradoPlan(1);
			planRepository.saveAndFlush(planBDABorrar);

			resultado = CodigosMensajes.ELIMINAR_PLAN_CORRECTO + " - " + Mensajes.PLAN_ELIMINADO_CORRECTAMENTE;

			final PlanEntity planBDNuevo = planRepository.findPlanByName(plan.getNombrePlan());
			final ObjetivoEntity objetivoBDNuevo = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());

			planRepository.delete(planBDNuevo);
			objetivoRepository.delete(objetivoBDNuevo);

		}

		return resultado;
	}

	private String eliminarPlanNoExiste(final PlanEntity plan) {
		final PlanEntity planBD = planRepository.findPlanByName(plan.getNombrePlan());
		String resultado = StringUtils.EMPTY;

		if (planBD == null) {
			resultado = CodigosMensajes.PLAN_NO_EXISTE + " - " + Mensajes.PLAN_NO_EXISTE;

		}
		return resultado;
	}

	private String reactivarPlan(final PlanEntity plan) {
		final PlanEntity planBD = planRepository.findPlanByName(plan.getNombrePlan());
		String resultado = StringUtils.EMPTY;

		if (planBD == null) {

			plan.setBorradoPlan(1);
			final ObjetivoEntity objetivo = new ObjetivoEntity("Objetivo", "Objetivo de pruebas", 0);
			objetivoRepository.saveAndFlush(objetivo);
			plan.setObjetivo(objetivo);
			planRepository.saveAndFlush(plan);

			final PlanEntity planBDReactivar = planRepository.findPlanByName(plan.getNombrePlan());

			planBDReactivar.setBorradoPlan(0);
			planRepository.saveAndFlush(planBDReactivar);

			resultado = CodigosMensajes.REACTIVAR_PLAN_CORRECTO + " - " + Mensajes.PLAN_REACTIVADO_CORRECTAMENTE;

			final PlanEntity planBDNuevo = planRepository.findPlanByName(plan.getNombrePlan());
			final ObjetivoEntity objetivoBDNuevo = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());

			planRepository.delete(planBDNuevo);
			objetivoRepository.delete(objetivoBDNuevo);

		}

		return resultado;
	}

	private String reactivarPlanNoExiste(final PlanEntity plan) {
		final PlanEntity planBD = planRepository.findPlanByName(plan.getNombrePlan());
		String resultado = StringUtils.EMPTY;

		if (planBD == null) {
			resultado = CodigosMensajes.PLAN_NO_EXISTE + " - " + Mensajes.PLAN_NO_EXISTE;

		}

		return resultado;
	}

	private Map<String, String> getValorPlan(final PlanEntity plan) {
		final Map<String, String> valor = new HashMap<>();

		valor.put(Constantes.NOMBRE_PLAN, plan.getNombrePlan());
		valor.put(Constantes.DESCRIPCION_PLAN, plan.getDescripPlan());
		valor.put(Constantes.FECHA_PLAN, plan.getFechaPlan().toString());

		return valor;

	}

}
