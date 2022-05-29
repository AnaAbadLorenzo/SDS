package com.sds.service.test.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.model.ObjetivoEntity;
import com.sds.model.PlanEntity;
import com.sds.model.ProcedimientoEntity;
import com.sds.repository.ObjetivoRepository;
import com.sds.repository.PlanRepository;
import com.sds.repository.ProcedimientoRepository;
import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.DefinicionPruebas;
import com.sds.service.common.Mensajes;
import com.sds.service.test.TestProcedimientoService;
import com.sds.service.test.impl.atributos.TestAtributoDescripcionProcedimiento;
import com.sds.service.test.impl.atributos.TestAtributoFechaProcedimiento;
import com.sds.service.test.impl.atributos.TestAtributoNombreProcedimiento;
import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;
import com.sds.service.test.util.CrearDatosPruebaAcciones;
import com.sds.service.test.util.GenerarJSON;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class TestProcedimientoServiceImpl implements TestProcedimientoService {

	private final TestAtributoNombreProcedimiento testAtributoNombreProcedimiento;
	private final TestAtributoDescripcionProcedimiento testAtributoDescripcionProcedimiento;
	private final TestAtributoFechaProcedimiento testAtributoFechaProcedimiento;
	private final CrearDatosPruebaAcciones crearDatosPruebaAcciones;
	private final GenerarJSON generarJSON;
	private final Validaciones validaciones;

	@Autowired
	ProcedimientoRepository procedimientoRepository;

	@Autowired
	PlanRepository planRepository;

	@Autowired
	ObjetivoRepository objetivoRepository;

	public TestProcedimientoServiceImpl() {
		testAtributoNombreProcedimiento = new TestAtributoNombreProcedimiento();
		testAtributoDescripcionProcedimiento = new TestAtributoDescripcionProcedimiento();
		testAtributoFechaProcedimiento = new TestAtributoFechaProcedimiento();
		crearDatosPruebaAcciones = new CrearDatosPruebaAcciones();
		generarJSON = new GenerarJSON();
		validaciones = new Validaciones();
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoNombreProcedimiento()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final ProcedimientoEntity datosEntradaNombreProcedimientoVacio = generarJSON.generarProcedimiento(
				Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_NOMBRE_PROCEDIMIENTO,
				Constantes.NOMBRE_PROCEDIMIENTO_VACIO);
		final ProcedimientoEntity datosEntradaNombreProcedimientoCaracteresEspeciales = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_NOMBRE_PROCEDIMIENTO,
						Constantes.NOMBRE_PROCEDIMIENTO_CARACTERESESPECIALES);
		final ProcedimientoEntity datosEntradaNombreProcedimientoMenor3 = generarJSON.generarProcedimiento(
				Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_NOMBRE_PROCEDIMIENTO,
				Constantes.NOMBRE_PROCEDIMIENTO_MENOR_3);
		final ProcedimientoEntity datosEntradaNombreProcedimientoMayor48 = generarJSON.generarProcedimiento(
				Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_NOMBRE_PROCEDIMIENTO,
				Constantes.NOMBRE_PROCEDIMIENTO_MAYOR_48);
		final ProcedimientoEntity datosEntradaNombreProcedimientoAlfanumericoCorrecto = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_NOMBRE_PROCEDIMIENTO,
						Constantes.NOMBRE_PROCEDIMIENTO_CORRECTO);

		datosPruebaAtributos.add(
				testAtributoNombreProcedimiento.getTestNombreProcedimientoVacio(datosEntradaNombreProcedimientoVacio));
		datosPruebaAtributos
				.add(testAtributoNombreProcedimiento.getTestNombreProcedimientoAlfanumericoCaracteresEspeciales(
						datosEntradaNombreProcedimientoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoNombreProcedimiento
				.getTestNombreProcedimientoAlfanumericoMenor3(datosEntradaNombreProcedimientoMenor3));
		datosPruebaAtributos.add(testAtributoNombreProcedimiento
				.getTestNombreProcedimientoAlfanumericoMayor48(datosEntradaNombreProcedimientoMayor48));
		datosPruebaAtributos.add(testAtributoNombreProcedimiento
				.getTestNombreProcedimientoCorrectoAlfabetico(datosEntradaNombreProcedimientoAlfanumericoCorrecto));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoNombreProcedimientoBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final ProcedimientoEntity datosEntradaNombreProcedimientoCaracteresEspeciales = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_NOMBRE_PROCEDIMIENTO,
						Constantes.NOMBRE_PROCEDIMIENTO_CARACTERESESPECIALES);
		final ProcedimientoEntity datosEntradaNombreProcedimientoMayor48 = generarJSON.generarProcedimiento(
				Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_NOMBRE_PROCEDIMIENTO,
				Constantes.NOMBRE_PROCEDIMIENTO_MAYOR_48);
		final ProcedimientoEntity datosEntradaNombreProcedimientoAlfanumericoCorrecto = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_NOMBRE_PROCEDIMIENTO,
						Constantes.NOMBRE_PROCEDIMIENTO_CORRECTO);

		datosPruebaAtributos
				.add(testAtributoNombreProcedimiento.getTestNombreProcedimientoAlfanumericoCaracteresEspeciales(
						datosEntradaNombreProcedimientoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoNombreProcedimiento
				.getTestNombreProcedimientoAlfanumericoMayor48(datosEntradaNombreProcedimientoMayor48));
		datosPruebaAtributos.add(testAtributoNombreProcedimiento
				.getTestNombreProcedimientoCorrectoAlfabetico(datosEntradaNombreProcedimientoAlfanumericoCorrecto));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoDescripProcedimiento()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final ProcedimientoEntity datosEntradaDescripcionProcedimientoVacio = generarJSON.generarProcedimiento(
				Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_DESCRIPCION_PROCEDIMIENTO,
				Constantes.DESCRIPCION_PROCEDIMIENTO_VACIO);
		final ProcedimientoEntity datosEntradaDescripcionProcedimientoCaracteresEspeciales = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_DESCRIPCION_PROCEDIMIENTO,
						Constantes.DESCRIPCION_PROCEDIMIENTO_CARACTERESESPECIALES);
		final ProcedimientoEntity datosEntradaDescripcionProcedimientoMenor3 = generarJSON.generarProcedimiento(
				Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_DESCRIPCION_PROCEDIMIENTO,
				Constantes.DESCRIPCION_PROCEDIMIENTO_MENOR_3);
		final ProcedimientoEntity datosEntradaDescripcionProcedimientoAlfanumericoCorrecto = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_DESCRIPCION_PROCEDIMIENTO,
						Constantes.DESCRIPCION_PROCEDIMIENTO_CORRECTO);

		datosPruebaAtributos.add(testAtributoDescripcionProcedimiento
				.getTestDescripcionProcedimientoVacio(datosEntradaDescripcionProcedimientoVacio));
		datosPruebaAtributos.add(
				testAtributoDescripcionProcedimiento.getTestDescripcionProcedimientoAlfanumericoCaracteresEspeciales(
						datosEntradaDescripcionProcedimientoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoDescripcionProcedimiento
				.getTestDescripcionProcedimientoAlfanumericoMenor3(datosEntradaDescripcionProcedimientoMenor3));
		datosPruebaAtributos
				.add(testAtributoDescripcionProcedimiento.getTestDescripcionProcedimientoAlfanumericoCorrecto(
						datosEntradaDescripcionProcedimientoAlfanumericoCorrecto));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoDescripProcedimientoBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final ProcedimientoEntity datosEntradaDescripcionProcedimientoCaracteresEspeciales = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_DESCRIPCION_PROCEDIMIENTO,
						Constantes.DESCRIPCION_PROCEDIMIENTO_CARACTERESESPECIALES);
		final ProcedimientoEntity datosEntradaDescripcionProcedimientoAlfanumericoCorrecto = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_DESCRIPCION_PROCEDIMIENTO,
						Constantes.DESCRIPCION_PROCEDIMIENTO_CORRECTO);

		datosPruebaAtributos.add(
				testAtributoDescripcionProcedimiento.getTestDescripcionProcedimientoAlfanumericoCaracteresEspeciales(
						datosEntradaDescripcionProcedimientoCaracteresEspeciales));
		datosPruebaAtributos
				.add(testAtributoDescripcionProcedimiento.getTestDescripcionProcedimientoAlfanumericoCorrecto(
						datosEntradaDescripcionProcedimientoAlfanumericoCorrecto));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoFechaProcedimiento()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final ProcedimientoEntity datosEntradaFechaProcedimientoVacia = generarJSON.generarProcedimiento(
				Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_FECHA_PROCEDIMIENTO, Constantes.FECHA_PROCEDIMIENTO_VACIA);
		final ProcedimientoEntity datosEntradaFechaProcedimientoAcentos = generarJSON.generarProcedimiento(
				Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_FECHA_PROCEDIMIENTO,
				Constantes.FECHA_PROCEDIMIENTO_ACENTOS);
		final ProcedimientoEntity datosEntradaFechaProcedimientoEnhe = generarJSON.generarProcedimiento(
				Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_FECHA_PROCEDIMIENTO, Constantes.FECHA_PROCEDIMIENTO_ENHE);
		final ProcedimientoEntity datosEntradaFechaProcedimientoCaracteresEspeciales = generarJSON.generarProcedimiento(
				Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_FECHA_PROCEDIMIENTO,
				Constantes.FECHA_PROCEDIMIENTO_CARACTERESESPECIALES);
		final ProcedimientoEntity datosEntradaFechaProcedimientoMenor8 = generarJSON.generarProcedimiento(
				Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_FECHA_PROCEDIMIENTO,
				Constantes.FECHA_PROCEDIMIENTO_MENOR_8);
		final ProcedimientoEntity datosEntradaFechaProcedimientoMayor8 = generarJSON.generarProcedimiento(
				Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_FECHA_PROCEDIMIENTO,
				Constantes.FECHA_PROCEDIMIENTO_MAYOR_8);
		final ProcedimientoEntity datosEntradaFechaProcedimientoMenorFechaActual = generarJSON.generarProcedimiento(
				Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_FECHA_PROCEDIMIENTO,
				Constantes.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL);
		final ProcedimientoEntity datosEntradaFechaProcedimientoNumericoCorrecto = generarJSON.generarProcedimiento(
				Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_FECHA_PROCEDIMIENTO,
				Constantes.FECHA_PROCEDIMIENTO_CORRECTO);

		datosPruebaAtributos.add(
				testAtributoFechaProcedimiento.getTestFechaProcedimientoVacio(datosEntradaFechaProcedimientoVacia));
		datosPruebaAtributos.add(testAtributoFechaProcedimiento
				.getTestFechaProcedimientoNumericoAcentos(datosEntradaFechaProcedimientoAcentos));
		datosPruebaAtributos.add(testAtributoFechaProcedimiento
				.getTestFechaProcedimientoNumericoEnhe(datosEntradaFechaProcedimientoEnhe));
		datosPruebaAtributos.add(testAtributoFechaProcedimiento.getTestFechaProcedimientoNumericoCaracteresEspeciales(
				datosEntradaFechaProcedimientoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoFechaProcedimiento
				.getTestFechaProcedimientoAlfanumericoMenor8(datosEntradaFechaProcedimientoMenor8));
		datosPruebaAtributos.add(testAtributoFechaProcedimiento
				.getTestFechaProcedimientoAlfanumericoMayor8(datosEntradaFechaProcedimientoMayor8));
		datosPruebaAtributos.add(testAtributoFechaProcedimiento
				.getTestFechaProcedimientoMenorFechaActual(datosEntradaFechaProcedimientoMenorFechaActual));
		datosPruebaAtributos.add(testAtributoFechaProcedimiento
				.getTestFechaProcedimientoCorrectoNumerico(datosEntradaFechaProcedimientoNumericoCorrecto));

		return datosPruebaAtributos;

	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoFechaProcedimientoBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final ProcedimientoEntity datosEntradaFechaProcedimientoAcentos = generarJSON.generarProcedimiento(
				Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_FECHA_PROCEDIMIENTO,
				Constantes.FECHA_PROCEDIMIENTO_ACENTOS);
		final ProcedimientoEntity datosEntradaFechaProcedimientoEnhe = generarJSON.generarProcedimiento(
				Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_FECHA_PROCEDIMIENTO, Constantes.FECHA_PROCEDIMIENTO_ENHE);
		final ProcedimientoEntity datosEntradaFechaProcedimientoCaracteresEspeciales = generarJSON.generarProcedimiento(
				Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_FECHA_PROCEDIMIENTO,
				Constantes.FECHA_PROCEDIMIENTO_CARACTERESESPECIALES);
		final ProcedimientoEntity datosEntradaFechaProcedimientoMayor8 = generarJSON.generarProcedimiento(
				Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_FECHA_PROCEDIMIENTO,
				Constantes.FECHA_PROCEDIMIENTO_MAYOR_8);
		final ProcedimientoEntity datosEntradaFechaProcedimientoNumericoCorrecto = generarJSON.generarProcedimiento(
				Constantes.URL_JSON_PROCEDIMIENTO_ATRIBUTOS_FECHA_PROCEDIMIENTO,
				Constantes.FECHA_PROCEDIMIENTO_CORRECTO);

		datosPruebaAtributos.add(testAtributoFechaProcedimiento
				.getTestFechaProcedimientoNumericoAcentos(datosEntradaFechaProcedimientoAcentos));
		datosPruebaAtributos.add(testAtributoFechaProcedimiento
				.getTestFechaProcedimientoNumericoEnhe(datosEntradaFechaProcedimientoEnhe));
		datosPruebaAtributos.add(testAtributoFechaProcedimiento.getTestFechaProcedimientoNumericoCaracteresEspeciales(
				datosEntradaFechaProcedimientoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoFechaProcedimiento
				.getTestFechaProcedimientoAlfanumericoMayor8(datosEntradaFechaProcedimientoMayor8));
		datosPruebaAtributos.add(testAtributoFechaProcedimiento
				.getTestFechaProcedimientoCorrectoNumerico(datosEntradaFechaProcedimientoNumericoCorrecto));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesProcedimientoBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final ProcedimientoEntity datosEntradaBuscarProcedimientoCorrecto = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA, Constantes.BUSCAR_PROCEDIMIENTO);
		final ProcedimientoEntity datosEntradaBuscarNombreProcedimientoCorrecto = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA, Constantes.NOMBRE_PROCEDIMIENTO_VACIO);
		final ProcedimientoEntity datosEntradaBuscarDescripProcedimientoCorrecto = generarJSON.generarProcedimiento(
				Constantes.URL_JSON_PROCEDIMIENTO_DATA, Constantes.DESCRIPCION_PROCEDIMIENTO_VACIO);
		final ProcedimientoEntity datosEntradaBuscarFechaProcedimientoCorrecto = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA, Constantes.FECHA_PROCEDIMIENTO_VACIA);
		final ProcedimientoEntity datosEntradaBuscarCheckUsuarioCorrecto = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA, Constantes.CHECK_USUARIO_VACIO);
		final ProcedimientoEntity datosEntradaBuscarDatosVacios = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA, Constantes.DATOS_PROCEDIMIENTO_VACIOS);

		datosPruebaAcciones.add(getTestBuscarProcedimiento(datosEntradaBuscarProcedimientoCorrecto));
		datosPruebaAcciones.add(getTestBuscarProcedimiento(datosEntradaBuscarNombreProcedimientoCorrecto));
		datosPruebaAcciones.add(getTestBuscarProcedimiento(datosEntradaBuscarDescripProcedimientoCorrecto));
		datosPruebaAcciones.add(getTestBuscarProcedimiento(datosEntradaBuscarFechaProcedimientoCorrecto));
		datosPruebaAcciones.add(getTestBuscarProcedimiento(datosEntradaBuscarCheckUsuarioCorrecto));
		datosPruebaAcciones.add(getTestBuscarProcedimiento(datosEntradaBuscarDatosVacios));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesProcedimientoGuardar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final ProcedimientoEntity datosEntradaGuardarProcedimientoCorrecto = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA, Constantes.GUARDAR_PROCEDIMIENTO);
		final ProcedimientoEntity datosEntradaGuardarProcedimientoYaExiste = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA, Constantes.PROCEDIMIENTO_YA_EXISTE);
		final ProcedimientoEntity datosEntradaGuardarNombreProcedimientoVacio = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA, Constantes.NOMBRE_PROCEDIMIENTO_VACIO);
		final ProcedimientoEntity datosEntradaGuardarDescripcionProcedimientoVacia = generarJSON.generarProcedimiento(
				Constantes.URL_JSON_PROCEDIMIENTO_DATA, Constantes.DESCRIPCION_PROCEDIMIENTO_VACIO);
		final ProcedimientoEntity datosEntradaGuardarFechaProcedimientoVacia = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA, Constantes.FECHA_PROCEDIMIENTO_VACIA);
		final ProcedimientoEntity datosEntradaGuardarCheckUsuarioProcedimientoVacio = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA, Constantes.CHECK_USUARIO_VACIO);
		final ProcedimientoEntity datosEntradaGuardarDatosProcedimientoVacios = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA, Constantes.DATOS_PROCEDIMIENTO_VACIOS);
		final ProcedimientoEntity datosEntradaGuardarFechaProcedimientoAnteriorFechaActual = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
						Constantes.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL);

		datosPruebaAcciones.add(getTestGuardarProcedimientoCorrecto(datosEntradaGuardarProcedimientoCorrecto));
		datosPruebaAcciones.add(getTestGuardarProcedimientoYaExiste(datosEntradaGuardarProcedimientoYaExiste));
		datosPruebaAcciones.add(getTestGuardarNombreProcedimientoVacio(datosEntradaGuardarNombreProcedimientoVacio));
		datosPruebaAcciones
				.add(getTestGuardarDescripcionProcedimientoVacio(datosEntradaGuardarDescripcionProcedimientoVacia));
		datosPruebaAcciones.add(getTestGuardarFechaProcedimientoVacio(datosEntradaGuardarFechaProcedimientoVacia));
		datosPruebaAcciones
				.add(getTestGuardarCheckUsuarioProcedimientoVacio(datosEntradaGuardarCheckUsuarioProcedimientoVacio));
		datosPruebaAcciones.add(getTestGuardarNombreDescripcionFechaCheckProcedimientoVacio(
				datosEntradaGuardarDatosProcedimientoVacios));
		datosPruebaAcciones.add(getTestGuardarFechaProcedimientoAnteriorFechaActual(
				datosEntradaGuardarFechaProcedimientoAnteriorFechaActual));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesProcedimientoEliminar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final ProcedimientoEntity datosEntradaEliminarProcedimientoCorrecto = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA, Constantes.ELIMINAR_PROCEDIMIENTO);
		final ProcedimientoEntity datosEntradaEliminarProcedimientoNoExiste = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA, Constantes.PROCEDIMIENTO_NO_EXISTE);

		datosPruebaAcciones.add(getTestEliminarProcedimientoCorrecto(datosEntradaEliminarProcedimientoCorrecto));
		datosPruebaAcciones.add(getTestEliminarProcedimientoNoExiste(datosEntradaEliminarProcedimientoNoExiste));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesProcedimientoModificar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final ProcedimientoEntity datosEntradaModificarProcedimientoCorrecto = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA, Constantes.MODIFICAR_PROCEDIMIENTO);
		final ProcedimientoEntity datosEntradaModificarProcedimientoNoExiste = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA, Constantes.PROCEDIMIENTO_NO_EXISTE);
		final ProcedimientoEntity datosEntradaModificarNombreProcedimientoVacio = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA, Constantes.NOMBRE_PROCEDIMIENTO_VACIO);
		final ProcedimientoEntity datosEntradaModificarDescripcionProcedimientoVacia = generarJSON.generarProcedimiento(
				Constantes.URL_JSON_PROCEDIMIENTO_DATA, Constantes.DESCRIPCION_PROCEDIMIENTO_VACIO);
		final ProcedimientoEntity datosEntradaModificarFechaProcedimientoVacia = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA, Constantes.FECHA_PROCEDIMIENTO_VACIA);
		final ProcedimientoEntity datosEntradaModificarCheckUsuarioProcedimientoVacio = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA, Constantes.FECHA_PROCEDIMIENTO_VACIA);
		final ProcedimientoEntity datosEntradaModificarDatosProcedimientoVacios = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA, Constantes.DATOS_PROCEDIMIENTO_VACIOS);
		final ProcedimientoEntity datosEntradaModificarFechaProcedimientoAnteriorFechaActual = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA,
						Constantes.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL);

		datosPruebaAcciones.add(getTestModificarProcedimientoCorrecto(datosEntradaModificarProcedimientoCorrecto));
		datosPruebaAcciones.add(getTestModificarProcedimientoNoExiste(datosEntradaModificarProcedimientoNoExiste));
		datosPruebaAcciones
				.add(getTestModificarNombreProcedimientoVacio(datosEntradaModificarNombreProcedimientoVacio));
		datosPruebaAcciones
				.add(getTestModificarDescripcionProcedimientoVacio(datosEntradaModificarDescripcionProcedimientoVacia));
		datosPruebaAcciones.add(getTestModificarFechaProcedimientoVacio(datosEntradaModificarFechaProcedimientoVacia));
		datosPruebaAcciones.add(
				getTestModificarCheckUsuarioProcedimientoVacio(datosEntradaModificarCheckUsuarioProcedimientoVacio));
		datosPruebaAcciones.add(getTestModificarNombreDescripcionFechaCheckProcedimientoVacio(
				datosEntradaModificarDatosProcedimientoVacios));
		datosPruebaAcciones.add(getTestModificarFechaProcedimientoAnteriorFechaActual(
				datosEntradaModificarFechaProcedimientoAnteriorFechaActual));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesProcedimientoReactivar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final ProcedimientoEntity datosEntradaReactivarProcedimientoCorrecto = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA, Constantes.REACTIVAR_PROCEDIMIENTO);
		final ProcedimientoEntity datosEntradaReactivarProcedimientoNoExiste = generarJSON
				.generarProcedimiento(Constantes.URL_JSON_PROCEDIMIENTO_DATA, Constantes.PROCEDIMIENTO_NO_EXISTE);

		datosPruebaAcciones.add(getTestReactivarProcedimientoCorrecto(datosEntradaReactivarProcedimientoCorrecto));
		datosPruebaAcciones.add(getTestReactivarProcedimientoNoExiste(datosEntradaReactivarProcedimientoNoExiste));

		return datosPruebaAcciones;
	}

	private DatosPruebaAcciones getTestBuscarProcedimiento(
			final ProcedimientoEntity datosEntradaAccionBuscarProcedimiento) {

		final String resultadoObtenido = buscarProcedimiento(datosEntradaAccionBuscarProcedimiento);

		final String resultadoEsperado = CodigosMensajes.BUSCAR_PLAN_CORRECTO + " - "
				+ Mensajes.PLAN_BUSCADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.BUSCAR_CORRECTO, Constantes.EXITO,
				getValorProcedimiento(datosEntradaAccionBuscarProcedimiento));

	}

	private DatosPruebaAcciones getTestGuardarProcedimientoCorrecto(
			final ProcedimientoEntity datosEntradaAccionGuardarProcedimiento) throws java.text.ParseException {

		final String resultadoObtenido = guardarProcedimiento(datosEntradaAccionGuardarProcedimiento);

		final String resultadoEsperado = CodigosMensajes.GUARDAR_PROCEDIMIENTO_CORRECTO + " - "
				+ Mensajes.PROCEDIMIENTO_GUARDADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.GUARDAR_PROCEDIMIENTO_CORRECTO, Constantes.EXITO,
				getValorProcedimiento(datosEntradaAccionGuardarProcedimiento));

	}

	private DatosPruebaAcciones getTestGuardarProcedimientoYaExiste(
			final ProcedimientoEntity datosEntradaAccionGuardarProcedimientoYaExiste) throws java.text.ParseException {

		final String resultadoObtenido = guardarProcedimiento(datosEntradaAccionGuardarProcedimientoYaExiste);

		final String resultadoEsperado = CodigosMensajes.PROCEDIMIENTO_YA_EXISTE + " - "
				+ Mensajes.PROCEDIMIENTO_YA_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.PROCEDIMIENTO_YA_EXISTE, Constantes.ERROR,
				getValorProcedimiento(datosEntradaAccionGuardarProcedimientoYaExiste));

	}

	private DatosPruebaAcciones getTestGuardarNombreProcedimientoVacio(
			final ProcedimientoEntity datosEntradaAccionGuardarNombreProcedimientoVacio)
			throws java.text.ParseException {

		final String resultadoObtenido = guardarProcedimiento(datosEntradaAccionGuardarNombreProcedimientoVacio);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_PROCEDIMIENTO_VACIO + " - "
				+ Mensajes.NOMBRE_PROCEDIMIENTO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProcedimiento(datosEntradaAccionGuardarNombreProcedimientoVacio));

	}

	private DatosPruebaAcciones getTestGuardarDescripcionProcedimientoVacio(
			final ProcedimientoEntity datosEntradaAccionGuardarDescripcionProcedimientoVacio)
			throws java.text.ParseException {

		final String resultadoObtenido = guardarProcedimiento(datosEntradaAccionGuardarDescripcionProcedimientoVacio);

		final String resultadoEsperado = CodigosMensajes.DESCRIPCION_PROCEDIMIENTO_VACIO + " - "
				+ Mensajes.DESCRIPCION_PROCEDIMIENTO_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProcedimiento(datosEntradaAccionGuardarDescripcionProcedimientoVacio));

	}

	private DatosPruebaAcciones getTestGuardarFechaProcedimientoVacio(
			final ProcedimientoEntity datosEntradaAccionGuardarFechaProcedimientoVacio)
			throws java.text.ParseException {

		final String resultadoObtenido = guardarProcedimiento(datosEntradaAccionGuardarFechaProcedimientoVacio);

		final String resultadoEsperado = CodigosMensajes.FECHA_PROCEDIMIENTO_VACIA + " - "
				+ Mensajes.FECHA_PROCEDIMIENTO_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProcedimiento(datosEntradaAccionGuardarFechaProcedimientoVacio));

	}

	private DatosPruebaAcciones getTestGuardarCheckUsuarioProcedimientoVacio(
			final ProcedimientoEntity datosEntradaAccionGuardarCheckUsuarioProcedimientoVacio)
			throws java.text.ParseException {

		final String resultadoObtenido = guardarProcedimiento(datosEntradaAccionGuardarCheckUsuarioProcedimientoVacio);

		final String resultadoEsperado = CodigosMensajes.CHECK_USUARIO_VACIO + " - "
				+ Mensajes.CHECK_USUARIO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProcedimiento(datosEntradaAccionGuardarCheckUsuarioProcedimientoVacio));

	}

	private DatosPruebaAcciones getTestGuardarNombreDescripcionFechaCheckProcedimientoVacio(
			final ProcedimientoEntity datosEntradaAccionGuardarNombreDescripcionFechaCheckProcedimientoVacio)
			throws java.text.ParseException {

		final String resultadoObtenido = guardarProcedimiento(
				datosEntradaAccionGuardarNombreDescripcionFechaCheckProcedimientoVacio);

		final String resultadoEsperado = CodigosMensajes.PROCEDIMIENTO_VACIO + " - "
				+ Mensajes.PROCEDIMIENTO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProcedimiento(datosEntradaAccionGuardarNombreDescripcionFechaCheckProcedimientoVacio));
	}

	private DatosPruebaAcciones getTestGuardarFechaProcedimientoAnteriorFechaActual(
			final ProcedimientoEntity datosEntradaAccionGuardarFechaProcedimientoMenorFechaActual)
			throws java.text.ParseException {

		final String resultadoObtenido = guardarProcedimiento(
				datosEntradaAccionGuardarFechaProcedimientoMenorFechaActual);

		final String resultadoEsperado = CodigosMensajes.FECHA_INTRODUCIDA_MENOR_FECHA_ACTUAL + " - "
				+ Mensajes.FECHA_INTRODUCIDA_NO_PUEDE_SER_MENOR_QUE_FECHA_ACTUAL;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL, Constantes.ERROR,
				getValorProcedimiento(datosEntradaAccionGuardarFechaProcedimientoMenorFechaActual));
	}

	private DatosPruebaAcciones getTestModificarProcedimientoCorrecto(
			final ProcedimientoEntity datosEntradaAccionModificarProcedimiento) throws java.text.ParseException {

		final String resultadoObtenido = modificarProcedimiento(datosEntradaAccionModificarProcedimiento);

		final String resultadoEsperado = CodigosMensajes.MODIFICAR_PROCEDIMIENTO_CORRECTO + " - "
				+ Mensajes.PROCEDIMIENTO_MODIFICADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.MODIFICAR_PROCEDIMIENTO_CORRECTO, Constantes.EXITO,
				getValorProcedimiento(datosEntradaAccionModificarProcedimiento));

	}

	private DatosPruebaAcciones getTestModificarProcedimientoNoExiste(
			final ProcedimientoEntity datosEntradaAccionModificarProcedimientoNoExiste)
			throws java.text.ParseException {

		final String resultadoObtenido = modificarProcedimientoNoExiste(
				datosEntradaAccionModificarProcedimientoNoExiste);

		final String resultadoEsperado = CodigosMensajes.PROCEDIMIENTO_NO_EXISTE + " - "
				+ Mensajes.PROCEDIMIENTO_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.PROCEDIMIENTO_NO_EXISTE, Constantes.ERROR,
				getValorProcedimiento(datosEntradaAccionModificarProcedimientoNoExiste));

	}

	private DatosPruebaAcciones getTestModificarNombreProcedimientoVacio(
			final ProcedimientoEntity datosEntradaAccionModificarNombreProcedimientoVacio)
			throws java.text.ParseException {

		final String resultadoObtenido = modificarProcedimiento(datosEntradaAccionModificarNombreProcedimientoVacio);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_PROCEDIMIENTO_VACIO + " - "
				+ Mensajes.NOMBRE_PROCEDIMIENTO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProcedimiento(datosEntradaAccionModificarNombreProcedimientoVacio));

	}

	private DatosPruebaAcciones getTestModificarDescripcionProcedimientoVacio(
			final ProcedimientoEntity datosEntradaAccionModificarDescripcionProcedimientoVacio)
			throws java.text.ParseException {

		final String resultadoObtenido = modificarProcedimiento(
				datosEntradaAccionModificarDescripcionProcedimientoVacio);

		final String resultadoEsperado = CodigosMensajes.DESCRIPCION_PROCEDIMIENTO_VACIO + " - "
				+ Mensajes.DESCRIPCION_PROCEDIMIENTO_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProcedimiento(datosEntradaAccionModificarDescripcionProcedimientoVacio));

	}

	private DatosPruebaAcciones getTestModificarFechaProcedimientoVacio(
			final ProcedimientoEntity datosEntradaAccionModificarFechaProcedimientoVacio)
			throws java.text.ParseException {

		final String resultadoObtenido = modificarProcedimiento(datosEntradaAccionModificarFechaProcedimientoVacio);

		final String resultadoEsperado = CodigosMensajes.FECHA_PROCEDIMIENTO_VACIA + " - "
				+ Mensajes.FECHA_PROCEDIMIENTO_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProcedimiento(datosEntradaAccionModificarFechaProcedimientoVacio));

	}

	private DatosPruebaAcciones getTestModificarCheckUsuarioProcedimientoVacio(
			final ProcedimientoEntity datosEntradaAccionModificarCheckUsuarioProcedimientoVacio)
			throws java.text.ParseException {

		final String resultadoObtenido = modificarProcedimiento(
				datosEntradaAccionModificarCheckUsuarioProcedimientoVacio);

		final String resultadoEsperado = CodigosMensajes.CHECK_USUARIO_VACIO + " - "
				+ Mensajes.CHECK_USUARIO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProcedimiento(datosEntradaAccionModificarCheckUsuarioProcedimientoVacio));

	}

	private DatosPruebaAcciones getTestModificarNombreDescripcionFechaCheckProcedimientoVacio(
			final ProcedimientoEntity datosEntradaAccionModificarNombreDescripcionFechaCheckProcedimientoVacio)
			throws java.text.ParseException {

		final String resultadoObtenido = modificarProcedimiento(
				datosEntradaAccionModificarNombreDescripcionFechaCheckProcedimientoVacio);

		final String resultadoEsperado = CodigosMensajes.PROCEDIMIENTO_VACIO + " - "
				+ Mensajes.PROCEDIMIENTO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProcedimiento(datosEntradaAccionModificarNombreDescripcionFechaCheckProcedimientoVacio));
	}

	private DatosPruebaAcciones getTestModificarFechaProcedimientoAnteriorFechaActual(
			final ProcedimientoEntity datosEntradaAccionModificarFechaProcedimientoMenorFechaActual)
			throws java.text.ParseException {

		final String resultadoObtenido = modificarProcedimiento(
				datosEntradaAccionModificarFechaProcedimientoMenorFechaActual);

		final String resultadoEsperado = CodigosMensajes.FECHA_INTRODUCIDA_MENOR_FECHA_ACTUAL + " - "
				+ Mensajes.FECHA_INTRODUCIDA_NO_PUEDE_SER_MENOR_QUE_FECHA_ACTUAL;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL, Constantes.ERROR,
				getValorProcedimiento(datosEntradaAccionModificarFechaProcedimientoMenorFechaActual));
	}

	private DatosPruebaAcciones getTestEliminarProcedimientoCorrecto(
			final ProcedimientoEntity datosEntradaAccionEliminarProcedimientoCorrecto) throws java.text.ParseException {

		final String resultadoObtenido = eliminarProcedimiento(datosEntradaAccionEliminarProcedimientoCorrecto);

		final String resultadoEsperado = CodigosMensajes.ELIMINAR_PROCEDIMIENTO_CORRECTO + " - "
				+ Mensajes.PROCEDIMIENTO_ELIMINADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ELIMINAR_PROCEDIMIENTO_CORRECTO, Constantes.ERROR,
				getValorProcedimiento(datosEntradaAccionEliminarProcedimientoCorrecto));

	}

	private DatosPruebaAcciones getTestEliminarProcedimientoNoExiste(
			final ProcedimientoEntity datosEntradaAccionEliminarProcedimientoNoExiste) throws java.text.ParseException {

		final String resultadoObtenido = eliminarProcedimientoNoExiste(datosEntradaAccionEliminarProcedimientoNoExiste);

		final String resultadoEsperado = CodigosMensajes.PROCEDIMIENTO_NO_EXISTE + " - "
				+ Mensajes.PROCEDIMIENTO_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.PROCEDIMIENTO_NO_EXISTE, Constantes.ERROR,
				getValorProcedimiento(datosEntradaAccionEliminarProcedimientoNoExiste));
	}

	private DatosPruebaAcciones getTestReactivarProcedimientoCorrecto(
			final ProcedimientoEntity datosEntradaAccionReactivarProcedimientoCorrecto)
			throws java.text.ParseException {

		final String resultadoObtenido = reactivarProcedimiento(datosEntradaAccionReactivarProcedimientoCorrecto);

		final String resultadoEsperado = CodigosMensajes.REACTIVAR_PROCEDIMIENTO_CORRECTO + " - "
				+ Mensajes.PROCEDIMIENTO_REACTIVADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.REACTIVAR_PROCEDIMIENTO_CORRECTO, Constantes.ERROR,
				getValorProcedimiento(datosEntradaAccionReactivarProcedimientoCorrecto));

	}

	private DatosPruebaAcciones getTestReactivarProcedimientoNoExiste(
			final ProcedimientoEntity datosEntradaAccionReactivarProcedimientoNoExiste)
			throws java.text.ParseException {

		final String resultadoObtenido = reactivarProcedimientoNoExiste(
				datosEntradaAccionReactivarProcedimientoNoExiste);

		final String resultadoEsperado = CodigosMensajes.PROCEDIMIENTO_NO_EXISTE + " - "
				+ Mensajes.PROCEDIMIENTO_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.PROCEDIMIENTO_NO_EXISTE, Constantes.ERROR,
				getValorProcedimiento(datosEntradaAccionReactivarProcedimientoNoExiste));
	}

	private String buscarProcedimiento(final ProcedimientoEntity procedimiento) {
		String resultado = StringUtils.EMPTY;

		java.sql.Date fechaSql;
		fechaSql = new java.sql.Date(procedimiento.getFechaProcedimiento().getTime());
		final String fecha = fechaSql.toString();

		final ObjetivoEntity objetivo = new ObjetivoEntity("Nombre objetivo", "Descripcion objetivo", 0);
		objetivoRepository.saveAndFlush(objetivo);
		final PlanEntity plan = new PlanEntity("Nombre plan", "Descripcion plan", new Date(), 0);
		plan.setObjetivo(objetivo);
		planRepository.saveAndFlush(plan);

		procedimientoRepository.findProcedimiento(procedimiento.getNombreProcedimiento(),
				procedimiento.getDescripProcedimiento(), fecha, Boolean.FALSE, plan);

		resultado = CodigosMensajes.BUSCAR_PROCEDIMIENTO_CORRECTO + " - "
				+ Mensajes.PROCEDIMIENTO_BUSCADO_CORRECTAMENTE;

		final LocalDate datePlan = plan.getFechaPlan().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		final String fechaPlanString = datePlan.getYear() + "-0" + datePlan.getMonthValue() + "-"
				+ datePlan.getDayOfMonth();

		final List<PlanEntity> planEliminar = planRepository.findPlan(plan.getNombrePlan(), plan.getDescripPlan(),
				fechaPlanString, objetivo);
		final List<ObjetivoEntity> objetivoEliminar = objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(),
				objetivo.getDescripObjetivo());

		planRepository.deletePlan(planEliminar.get(0).getIdPlan());
		objetivoRepository.deleteObjetivo(objetivoEliminar.get(0).getIdObjetivo());

		return resultado;
	}

	private String guardarProcedimiento(final ProcedimientoEntity procedimiento) throws java.text.ParseException {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarNombreProcedimientoBlank(procedimiento.getNombreProcedimiento())
				&& !validaciones.comprobarDescripProcedimientoBlank(procedimiento.getDescripProcedimiento())
				&& !validaciones.comprobarFechaProcedimientoBlank(procedimiento.getFechaProcedimiento())
				&& !validaciones.comprobarCheckUsuarioBlank(procedimiento.getCheckUsuario())) {
			resultado = CodigosMensajes.PROCEDIMIENTO_VACIO + " - " + Mensajes.PROCEDIMIENTO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarNombreProcedimientoBlank(procedimiento.getNombreProcedimiento())) {
			resultado = CodigosMensajes.NOMBRE_PROCEDIMIENTO_VACIO + " - "
					+ Mensajes.NOMBRE_PROCEDIMIENTO_NO_PUEDE_SER_VACIO;
		} else if (!!validaciones.comprobarDescripProcedimientoBlank(procedimiento.getDescripProcedimiento())) {
			resultado = CodigosMensajes.DESCRIPCION_PROCEDIMIENTO_VACIO + " - "
					+ Mensajes.DESCRIPCION_PROCEDIMIENTO_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarFechaProcedimientoBlank(procedimiento.getFechaProcedimiento())) {
			resultado = CodigosMensajes.FECHA_PROCEDIMIENTO_VACIA + " - "
					+ Mensajes.FECHA_PROCEDIMIENTO_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarCheckUsuarioBlank(procedimiento.getCheckUsuario())) {
			resultado = CodigosMensajes.CHECK_USUARIO_VACIO + " - " + Mensajes.CHECK_USUARIO_NO_PUEDE_SER_VACIO;
		} else {
			final ProcedimientoEntity procedimientoBD = procedimientoRepository
					.findProcedimientoByName(procedimiento.getNombreProcedimiento());

			if (procedimientoBD != null) {
				resultado = CodigosMensajes.PROCEDIMIENTO_YA_EXISTE + " - " + Mensajes.PROCEDIMIENTO_YA_EXISTE;
			} else {
				final LocalDate fechaActual = LocalDate.now();
				final String fechaIntroducidaUsuario = procedimiento.getFechaProcedimiento().toString();
				final String fechaActualString = fechaActual.getYear() + "-0" + fechaActual.getMonthValue() + "-"
						+ fechaActual.getDayOfMonth();

				if (fechaIntroducidaUsuario.compareTo(fechaActualString) < 0) {
					resultado = CodigosMensajes.FECHA_INTRODUCIDA_MENOR_FECHA_ACTUAL + " - "
							+ Mensajes.FECHA_INTRODUCIDA_NO_PUEDE_SER_MENOR_QUE_FECHA_ACTUAL;
				} else {
					procedimiento.setBorradoProcedimiento(0);
					final ObjetivoEntity objetivo = new ObjetivoEntity("Objetivo", "Objetivo de pruebas", 0);
					objetivoRepository.saveAndFlush(objetivo);
					final PlanEntity plan = new PlanEntity("Nombre plan", "Descripcion plan", new Date(), 0);
					plan.setObjetivo(objetivo);
					planRepository.saveAndFlush(plan);
					procedimiento.setPlan(plan);
					procedimientoRepository.saveAndFlush(procedimiento);
					resultado = CodigosMensajes.GUARDAR_PROCEDIMIENTO_CORRECTO + " - "
							+ Mensajes.PROCEDIMIENTO_GUARDADO_CORRECTAMENTE;

					final ProcedimientoEntity procedimientoBDNuevo = procedimientoRepository
							.findProcedimientoByName(procedimiento.getNombreProcedimiento());
					final PlanEntity planBDNuevo = planRepository.findPlanByName(plan.getNombrePlan());
					final ObjetivoEntity objetivoBDNuevo = objetivoRepository
							.findObjetivoByName(objetivo.getNombreObjetivo());

					procedimientoRepository.delete(procedimientoBDNuevo);
					planRepository.delete(planBDNuevo);
					objetivoRepository.delete(objetivoBDNuevo);

				}

			}
		}

		return resultado;
	}

	private String modificarProcedimiento(final ProcedimientoEntity procedimiento) throws java.text.ParseException {
		String resultado = StringUtils.EMPTY;
		Boolean modificar = Boolean.FALSE;

		if (!validaciones.comprobarNombreProcedimientoBlank(procedimiento.getNombreProcedimiento())
				&& !validaciones.comprobarDescripProcedimientoBlank(procedimiento.getDescripProcedimiento())
				&& !validaciones.comprobarFechaProcedimientoBlank(procedimiento.getFechaProcedimiento())
				&& !validaciones.comprobarCheckUsuarioBlank(procedimiento.getCheckUsuario())) {
			resultado = CodigosMensajes.PROCEDIMIENTO_VACIO + " - " + Mensajes.PROCEDIMIENTO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarNombreProcedimientoBlank(procedimiento.getNombreProcedimiento())) {
			resultado = CodigosMensajes.NOMBRE_PROCEDIMIENTO_VACIO + " - "
					+ Mensajes.NOMBRE_PROCEDIMIENTO_NO_PUEDE_SER_VACIO;
		} else if (!!validaciones.comprobarDescripProcedimientoBlank(procedimiento.getDescripProcedimiento())) {
			resultado = CodigosMensajes.DESCRIPCION_PROCEDIMIENTO_VACIO + " - "
					+ Mensajes.DESCRIPCION_PROCEDIMIENTO_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarFechaProcedimientoBlank(procedimiento.getFechaProcedimiento())) {
			resultado = CodigosMensajes.FECHA_PROCEDIMIENTO_VACIA + " - "
					+ Mensajes.FECHA_PROCEDIMIENTO_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarCheckUsuarioBlank(procedimiento.getCheckUsuario())) {
			resultado = CodigosMensajes.CHECK_USUARIO_VACIO + " - " + Mensajes.CHECK_USUARIO_NO_PUEDE_SER_VACIO;
		} else {

			final ObjetivoEntity objetivo = new ObjetivoEntity("Objetivo", "Objetivo de pruebas", 0);
			objetivoRepository.saveAndFlush(objetivo);
			final PlanEntity plan = new PlanEntity("Nombre plan", "Descripcion plan", new Date(), 0);
			plan.setObjetivo(objetivo);
			planRepository.saveAndFlush(plan);
			procedimiento.setPlan(plan);
			procedimientoRepository.saveAndFlush(procedimiento);

			final ProcedimientoEntity procedimientoBD = procedimientoRepository
					.findProcedimientoByName(procedimiento.getNombreProcedimiento());

			final LocalDate fechaActual = LocalDate.now();
			final String fechaIntroducidaUsuario = procedimiento.getFechaProcedimiento().toString();
			final String fechaActualString = fechaActual.getYear() + "-0" + fechaActual.getMonthValue() + "-"
					+ fechaActual.getDayOfMonth();

			if (!fechaIntroducidaUsuario.equals(procedimientoBD.getFechaProcedimiento().toString())) {
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
				procedimientoBD.setDescripProcedimiento("Descripción modificada");

				procedimientoRepository.saveAndFlush(procedimientoBD);

				resultado = CodigosMensajes.MODIFICAR_PROCEDIMIENTO_CORRECTO + " - "
						+ Mensajes.PROCEDIMIENTO_MODIFICADO_CORRECTAMENTE;

			}

			final ProcedimientoEntity procedimientoBDNuevo = procedimientoRepository
					.findProcedimientoByName(procedimiento.getNombreProcedimiento());
			final PlanEntity planBDNuevo = planRepository.findPlanByName(plan.getNombrePlan());
			final ObjetivoEntity objetivoBDNuevo = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());

			procedimientoRepository.delete(procedimientoBDNuevo);
			planRepository.delete(planBDNuevo);
			objetivoRepository.delete(objetivoBDNuevo);

		}

		return resultado;
	}

	private String modificarProcedimientoNoExiste(final ProcedimientoEntity procedimiento)
			throws java.text.ParseException {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarNombreProcedimientoBlank(procedimiento.getNombreProcedimiento())
				&& !validaciones.comprobarDescripProcedimientoBlank(procedimiento.getDescripProcedimiento())
				&& !validaciones.comprobarFechaProcedimientoBlank(procedimiento.getFechaProcedimiento())
				&& !validaciones.comprobarCheckUsuarioBlank(procedimiento.getCheckUsuario())) {
			resultado = CodigosMensajes.PROCEDIMIENTO_VACIO + " - " + Mensajes.PROCEDIMIENTO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarNombreProcedimientoBlank(procedimiento.getNombreProcedimiento())) {
			resultado = CodigosMensajes.NOMBRE_PROCEDIMIENTO_VACIO + " - "
					+ Mensajes.NOMBRE_PROCEDIMIENTO_NO_PUEDE_SER_VACIO;
		} else if (!!validaciones.comprobarDescripProcedimientoBlank(procedimiento.getDescripProcedimiento())) {
			resultado = CodigosMensajes.DESCRIPCION_PROCEDIMIENTO_VACIO + " - "
					+ Mensajes.DESCRIPCION_PROCEDIMIENTO_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarFechaProcedimientoBlank(procedimiento.getFechaProcedimiento())) {
			resultado = CodigosMensajes.FECHA_PROCEDIMIENTO_VACIA + " - "
					+ Mensajes.FECHA_PROCEDIMIENTO_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarCheckUsuarioBlank(procedimiento.getCheckUsuario())) {
			resultado = CodigosMensajes.CHECK_USUARIO_VACIO + " - " + Mensajes.CHECK_USUARIO_NO_PUEDE_SER_VACIO;
		} else {

			final ProcedimientoEntity procedimientoBD = procedimientoRepository
					.findProcedimientoByName(procedimiento.getNombreProcedimiento());

			if (procedimientoBD == null) {
				resultado = CodigosMensajes.PROCEDIMIENTO_NO_EXISTE + " - " + Mensajes.PROCEDIMIENTO_NO_EXISTE;
			}

		}

		return resultado;
	}

	private String eliminarProcedimiento(final ProcedimientoEntity procedimiento) {
		final ProcedimientoEntity procedimientoBD = procedimientoRepository
				.findProcedimientoByName(procedimiento.getNombreProcedimiento());
		String resultado = StringUtils.EMPTY;

		if (procedimientoBD == null) {

			procedimiento.setBorradoProcedimiento(0);
			final ObjetivoEntity objetivo = new ObjetivoEntity("Objetivo", "Objetivo de pruebas", 0);
			objetivoRepository.saveAndFlush(objetivo);
			final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
			plan.setObjetivo(objetivo);
			planRepository.saveAndFlush(plan);
			procedimiento.setPlan(plan);
			procedimientoRepository.saveAndFlush(procedimiento);

			final ProcedimientoEntity procedimientoBDABorrar = procedimientoRepository
					.findProcedimientoByName(procedimiento.getNombreProcedimiento());

			procedimientoBDABorrar.setBorradoProcedimiento(1);
			procedimientoRepository.saveAndFlush(procedimientoBDABorrar);

			resultado = CodigosMensajes.ELIMINAR_PROCEDIMIENTO_CORRECTO + " - "
					+ Mensajes.PROCEDIMIENTO_ELIMINADO_CORRECTAMENTE;

			final ProcedimientoEntity procedimientoBDNuevo = procedimientoRepository
					.findProcedimientoByName(procedimiento.getNombreProcedimiento());
			final PlanEntity planBDNuevo = planRepository.findPlanByName(plan.getNombrePlan());
			final ObjetivoEntity objetivoBDNuevo = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());

			procedimientoRepository.delete(procedimientoBDNuevo);
			planRepository.delete(planBDNuevo);
			objetivoRepository.delete(objetivoBDNuevo);

		}

		return resultado;
	}

	private String eliminarProcedimientoNoExiste(final ProcedimientoEntity procedimiento) {
		final ProcedimientoEntity procedimientoBD = procedimientoRepository
				.findProcedimientoByName(procedimiento.getNombreProcedimiento());
		String resultado = StringUtils.EMPTY;

		if (procedimientoBD == null) {
			resultado = CodigosMensajes.PROCEDIMIENTO_NO_EXISTE + " - " + Mensajes.PROCEDIMIENTO_NO_EXISTE;

		}
		return resultado;
	}

	private String reactivarProcedimiento(final ProcedimientoEntity procedimiento) {
		final ProcedimientoEntity procedimientoBD = procedimientoRepository
				.findProcedimientoByName(procedimiento.getNombreProcedimiento());
		String resultado = StringUtils.EMPTY;

		if (procedimientoBD == null) {

			procedimiento.setBorradoProcedimiento(1);
			final ObjetivoEntity objetivo = new ObjetivoEntity("Objetivo", "Objetivo de pruebas", 0);
			objetivoRepository.saveAndFlush(objetivo);
			final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
			plan.setObjetivo(objetivo);
			planRepository.saveAndFlush(plan);
			procedimiento.setPlan(plan);
			procedimiento.setBorradoProcedimiento(1);
			procedimientoRepository.saveAndFlush(procedimiento);

			final ProcedimientoEntity procedimientoBDReactivar = procedimientoRepository
					.findProcedimientoByName(procedimiento.getNombreProcedimiento());

			procedimientoBDReactivar.setBorradoProcedimiento(1);
			procedimientoRepository.saveAndFlush(procedimientoBDReactivar);

			resultado = CodigosMensajes.REACTIVAR_PROCEDIMIENTO_CORRECTO + " - "
					+ Mensajes.PROCEDIMIENTO_REACTIVADO_CORRECTAMENTE;

			final ProcedimientoEntity procedimientoBDNuevo = procedimientoRepository
					.findProcedimientoByName(procedimiento.getNombreProcedimiento());
			final PlanEntity planBDNuevo = planRepository.findPlanByName(plan.getNombrePlan());
			final ObjetivoEntity objetivoBDNuevo = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());

			procedimientoRepository.delete(procedimientoBDNuevo);
			planRepository.delete(planBDNuevo);
			objetivoRepository.delete(objetivoBDNuevo);

		}

		return resultado;
	}

	private String reactivarProcedimientoNoExiste(final ProcedimientoEntity procedimiento) {
		final ProcedimientoEntity procedimientoBD = procedimientoRepository
				.findProcedimientoByName(procedimiento.getNombreProcedimiento());
		String resultado = StringUtils.EMPTY;

		if (procedimientoBD == null) {
			resultado = CodigosMensajes.PROCEDIMIENTO_NO_EXISTE + " - " + Mensajes.PROCEDIMIENTO_NO_EXISTE;

		}

		return resultado;
	}

	private Map<String, String> getValorProcedimiento(final ProcedimientoEntity procedimiento) {
		final Map<String, String> valor = new HashMap<>();

		valor.put(Constantes.NOMBRE_PROCEDIMIENTO, procedimiento.getNombreProcedimiento());
		valor.put(Constantes.DESCRIPCION_PROCEDIMIENTO, procedimiento.getDescripProcedimiento());
		valor.put(Constantes.FECHA_PROCEDIMIENTO, procedimiento.getFechaProcedimiento().toString());
		valor.put(Constantes.CHECK_USUARIO, procedimiento.getCheckUsuario().toString());

		return valor;

	}

}
