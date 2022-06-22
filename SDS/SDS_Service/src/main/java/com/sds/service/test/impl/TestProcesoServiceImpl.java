package com.sds.service.test.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import com.sds.model.PersonaEntity;
import com.sds.model.PlanEntity;
import com.sds.model.ProcedimientoEntity;
import com.sds.model.ProcedimientoUsuarioEntity;
import com.sds.model.ProcedimientoUsuarioProcesoEntity;
import com.sds.model.ProcesoEntity;
import com.sds.model.ProcesoProcedimientoEntity;
import com.sds.model.ProcesoRespuestaPosibleEntity;
import com.sds.model.RespuestaPosibleEntity;
import com.sds.model.UsuarioEntity;
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
import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.DefinicionPruebas;
import com.sds.service.common.Mensajes;
import com.sds.service.test.TestProcesoService;
import com.sds.service.test.impl.atributos.TestAtributoDescripcionProceso;
import com.sds.service.test.impl.atributos.TestAtributoFechaProceso;
import com.sds.service.test.impl.atributos.TestAtributoNombreProceso;
import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;
import com.sds.service.test.util.CrearDatosPruebaAcciones;
import com.sds.service.test.util.GenerarJSON;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class TestProcesoServiceImpl implements TestProcesoService {

	private final TestAtributoNombreProceso testAtributoNombreProceso;
	private final TestAtributoDescripcionProceso testAtributoDescripcionProceso;
	private final TestAtributoFechaProceso testAtributoFechaProceso;
	private final CrearDatosPruebaAcciones crearDatosPruebaAcciones;
	private final GenerarJSON generarJSON;
	private final Validaciones validaciones;

	@Autowired
	ProcesoRepository procesoRepository;

	@Autowired
	ObjetivoRepository objetivoRepository;

	@Autowired
	PlanRepository planRepository;

	@Autowired
	ProcesoProcedimientoRepository procesoProcedimientoRepository;

	@Autowired
	ProcedimientoRepository procedimientoRepository;

	@Autowired
	RespuestaPosibleRepository respuestaPosibleRepository;

	@Autowired
	ProcesoRespuestaPosibleRepository procesoRespuestaPosibleRepository;

	@Autowired
	ProcedimientoUsuarioProcesoRepository procedimientoUsuarioProcesoRepository;

	@Autowired
	ProcedimientoUsuarioRepository procedimientoUsuarioRepository;

	@Autowired
	PersonaRepository personaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	public TestProcesoServiceImpl() {
		testAtributoNombreProceso = new TestAtributoNombreProceso();
		testAtributoDescripcionProceso = new TestAtributoDescripcionProceso();
		testAtributoFechaProceso = new TestAtributoFechaProceso();
		crearDatosPruebaAcciones = new CrearDatosPruebaAcciones();
		generarJSON = new GenerarJSON();
		validaciones = new Validaciones();
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoNombreProceso()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final ProcesoEntity datosEntradaNombreProcesoVacio = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_ATRIBUTOS_NOMBRE_PROCESO, Constantes.NOMBRE_PROCESO_VACIO);
		final ProcesoEntity datosEntradaNombreProcesoCaracteresEspeciales = generarJSON.generarProceso(
				Constantes.URL_JSON_PROCESO_ATRIBUTOS_NOMBRE_PROCESO, Constantes.NOMBRE_PROCESO_CARACTERESESPECIALES);
		final ProcesoEntity datosEntradaNombreProcesoMenor3 = generarJSON.generarProceso(
				Constantes.URL_JSON_PROCESO_ATRIBUTOS_NOMBRE_PROCESO, Constantes.NOMBRE_PROCESO_MENOR_3);
		final ProcesoEntity datosEntradaNombreProcesoMayor48 = generarJSON.generarProceso(
				Constantes.URL_JSON_PROCESO_ATRIBUTOS_NOMBRE_PROCESO, Constantes.NOMBRE_PROCESO_MAYOR_48);
		final ProcesoEntity datosEntradaNombreProcesoAlfanumericoCorrecto = generarJSON.generarProceso(
				Constantes.URL_JSON_PROCESO_ATRIBUTOS_NOMBRE_PROCESO, Constantes.NOMBRE_PROCESO_CORRECTO);

		datosPruebaAtributos.add(testAtributoNombreProceso.getTestNombreProcesoVacio(datosEntradaNombreProcesoVacio));
		datosPruebaAtributos.add(testAtributoNombreProceso
				.getTestNombreProcesoAlfanumericoCaracteresEspeciales(datosEntradaNombreProcesoCaracteresEspeciales));
		datosPruebaAtributos
				.add(testAtributoNombreProceso.getTestNombreProcesoAlfanumericoMenor3(datosEntradaNombreProcesoMenor3));
		datosPruebaAtributos.add(
				testAtributoNombreProceso.getTestNombreProcesoAlfanumericoMayor48(datosEntradaNombreProcesoMayor48));
		datosPruebaAtributos.add(testAtributoNombreProceso
				.getTestNombreProcesoCorrectoAlfanumerico(datosEntradaNombreProcesoAlfanumericoCorrecto));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoNombreProcesoBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final ProcesoEntity datosEntradaNombreProcesoCaracteresEspeciales = generarJSON.generarProceso(
				Constantes.URL_JSON_PROCESO_ATRIBUTOS_NOMBRE_PROCESO, Constantes.NOMBRE_PROCESO_CARACTERESESPECIALES);
		final ProcesoEntity datosEntradaNombreProcesoMayor48 = generarJSON.generarProceso(
				Constantes.URL_JSON_PROCESO_ATRIBUTOS_NOMBRE_PROCESO, Constantes.NOMBRE_PROCESO_MAYOR_48);
		final ProcesoEntity datosEntradaNombreProcesoAlfanumericoCorrecto = generarJSON.generarProceso(
				Constantes.URL_JSON_PROCESO_ATRIBUTOS_NOMBRE_PROCESO, Constantes.NOMBRE_PROCESO_CORRECTO);

		datosPruebaAtributos.add(testAtributoNombreProceso
				.getTestNombreProcesoAlfanumericoCaracteresEspeciales(datosEntradaNombreProcesoCaracteresEspeciales));
		datosPruebaAtributos.add(
				testAtributoNombreProceso.getTestNombreProcesoAlfanumericoMayor48(datosEntradaNombreProcesoMayor48));
		datosPruebaAtributos.add(testAtributoNombreProceso
				.getTestNombreProcesoCorrectoAlfanumerico(datosEntradaNombreProcesoAlfanumericoCorrecto));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoDescripProceso()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final ProcesoEntity datosEntradaDescripcionProcesoVacio = generarJSON.generarProceso(
				Constantes.URL_JSON_PROCESO_ATRIBUTOS_DESCRIPCION_PROCESO, Constantes.DESCRIPCION_PROCESO_VACIO);
		final ProcesoEntity datosEntradaDescripcionProcesoCaracteresEspeciales = generarJSON.generarProceso(
				Constantes.URL_JSON_PROCESO_ATRIBUTOS_DESCRIPCION_PROCESO,
				Constantes.DESCRIPCION_PROCESO_CARACTERESESPECIALES);
		final ProcesoEntity datosEntradaDescripcionProcesoMenor3 = generarJSON.generarProceso(
				Constantes.URL_JSON_PROCESO_ATRIBUTOS_DESCRIPCION_PROCESO, Constantes.DESCRIPCION_PROCESO_MENOR_3);
		final ProcesoEntity datosEntradaDescripcionProcesoAlfanumericoSignosPuntuacionCorrecto = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_ATRIBUTOS_DESCRIPCION_PROCESO,
						Constantes.DESCRIPCION_PROCESO_CORRECTO);

		datosPruebaAtributos.add(
				testAtributoDescripcionProceso.getTestDescripcionProcesoVacio(datosEntradaDescripcionProcesoVacio));
		datosPruebaAtributos
				.add(testAtributoDescripcionProceso.getTestDescripcionProcesoAlfanumericoCaracteresEspeciales(
						datosEntradaDescripcionProcesoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoDescripcionProceso
				.getTestDescripcionProcesoAlfanumericoMenor3(datosEntradaDescripcionProcesoMenor3));
		datosPruebaAtributos.add(testAtributoDescripcionProceso.getTestDescripcionProcesoAlfanumericoCorrecto(
				datosEntradaDescripcionProcesoAlfanumericoSignosPuntuacionCorrecto));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoDescripProcesoBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final ProcesoEntity datosEntradaDescripcionProcesoCaracteresEspeciales = generarJSON.generarProceso(
				Constantes.URL_JSON_PROCESO_ATRIBUTOS_DESCRIPCION_PROCESO,
				Constantes.DESCRIPCION_PROCESO_CARACTERESESPECIALES);
		final ProcesoEntity datosEntradaDescripcionProcesoAlfanumericoSignosPuntuacionCorrecto = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_ATRIBUTOS_DESCRIPCION_PROCESO,
						Constantes.DESCRIPCION_PROCESO_CORRECTO);

		datosPruebaAtributos
				.add(testAtributoDescripcionProceso.getTestDescripcionProcesoAlfanumericoCaracteresEspeciales(
						datosEntradaDescripcionProcesoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoDescripcionProceso.getTestDescripcionProcesoAlfanumericoCorrecto(
				datosEntradaDescripcionProcesoAlfanumericoSignosPuntuacionCorrecto));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoFechaProceso()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final ProcesoEntity datosEntradaFechaProcesoVacia = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_ATRIBUTOS_FECHA_PROCESO, Constantes.FECHA_PROCESO_VACIA);
		final ProcesoEntity datosEntradaFechaProcesoAcentos = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_ATRIBUTOS_FECHA_PROCESO, Constantes.FECHA_PROCESO_ACENTOS);
		final ProcesoEntity datosEntradaFechaProcesoEnhe = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_ATRIBUTOS_FECHA_PROCESO, Constantes.FECHA_PROCESO_ENHE);
		final ProcesoEntity datosEntradaFechaProcesoCaracteresEspeciales = generarJSON.generarProceso(
				Constantes.URL_JSON_PROCESO_ATRIBUTOS_FECHA_PROCESO, Constantes.FECHA_PROCESO_CARACTERESESPECIALES);
		final ProcesoEntity datosEntradaFechaProcesoMenor8 = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_ATRIBUTOS_FECHA_PROCESO, Constantes.FECHA_PROCESO_MENOR_8);
		final ProcesoEntity datosEntradaFechaProcesoMayor8 = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_ATRIBUTOS_FECHA_PROCESO, Constantes.FECHA_PROCESO_MAYOR_8);
		final ProcesoEntity datosEntradaFechaProcesoMenorFechaActual = generarJSON.generarProceso(
				Constantes.URL_JSON_PROCESO_ATRIBUTOS_FECHA_PROCESO,
				Constantes.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL);
		final ProcesoEntity datosEntradaFechaProcesoNumericoCorrecto = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_ATRIBUTOS_FECHA_PROCESO, Constantes.FECHA_PROCESO_CORRECTO);

		datosPruebaAtributos.add(testAtributoFechaProceso.getTestFechaProcesoVacio(datosEntradaFechaProcesoVacia));
		datosPruebaAtributos
				.add(testAtributoFechaProceso.getTestFechaProcesoNumericoAcentos(datosEntradaFechaProcesoAcentos));
		datosPruebaAtributos
				.add(testAtributoFechaProceso.getTestFechaProcesoNumericoEnhe(datosEntradaFechaProcesoEnhe));
		datosPruebaAtributos.add(testAtributoFechaProceso
				.getTestFechaProcesoNumericoCaracteresEspeciales(datosEntradaFechaProcesoCaracteresEspeciales));
		datosPruebaAtributos
				.add(testAtributoFechaProceso.getTestFechaProcesoAlfanumericoMenor8(datosEntradaFechaProcesoMenor8));
		datosPruebaAtributos
				.add(testAtributoFechaProceso.getTestFechaProcesoAlfanumericoMayor8(datosEntradaFechaProcesoMayor8));
		datosPruebaAtributos.add(
				testAtributoFechaProceso.getTestFechaProcesoMenorFechaActual(datosEntradaFechaProcesoMenorFechaActual));
		datosPruebaAtributos.add(
				testAtributoFechaProceso.getTestFechaProcesoCorrectoNumerico(datosEntradaFechaProcesoNumericoCorrecto));

		return datosPruebaAtributos;

	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoFechaProcesoBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final ProcesoEntity datosEntradaFechaProcesoAcentos = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_ATRIBUTOS_FECHA_PROCESO, Constantes.FECHA_PROCESO_ACENTOS);
		final ProcesoEntity datosEntradaFechaProcesoEnhe = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_ATRIBUTOS_FECHA_PROCESO, Constantes.FECHA_PROCESO_ENHE);
		final ProcesoEntity datosEntradaFechaProcesoCaracteresEspeciales = generarJSON.generarProceso(
				Constantes.URL_JSON_PROCESO_ATRIBUTOS_FECHA_PROCESO, Constantes.FECHA_PROCESO_CARACTERESESPECIALES);
		final ProcesoEntity datosEntradaFechaProcesoMayor8 = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_ATRIBUTOS_FECHA_PROCESO, Constantes.FECHA_PROCESO_MAYOR_8);
		final ProcesoEntity datosEntradaFechaProcesoNumericoCorrecto = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_ATRIBUTOS_FECHA_PROCESO, Constantes.FECHA_PROCESO_CORRECTO);

		datosPruebaAtributos
				.add(testAtributoFechaProceso.getTestFechaProcesoNumericoAcentos(datosEntradaFechaProcesoAcentos));
		datosPruebaAtributos
				.add(testAtributoFechaProceso.getTestFechaProcesoNumericoEnhe(datosEntradaFechaProcesoEnhe));
		datosPruebaAtributos.add(testAtributoFechaProceso
				.getTestFechaProcesoNumericoCaracteresEspeciales(datosEntradaFechaProcesoCaracteresEspeciales));
		datosPruebaAtributos
				.add(testAtributoFechaProceso.getTestFechaProcesoAlfanumericoMayor8(datosEntradaFechaProcesoMayor8));
		datosPruebaAtributos.add(
				testAtributoFechaProceso.getTestFechaProcesoCorrectoNumerico(datosEntradaFechaProcesoNumericoCorrecto));

		return datosPruebaAtributos;

	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesProcesoBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final ProcesoEntity datosEntradaBuscarProcesoCorrecto = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.BUSCAR_PROCESO);
		final ProcesoEntity datosEntradaBuscarNombreProcesoCorrecto = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.NOMBRE_PROCESO_VACIO);
		final ProcesoEntity datosEntradaBuscarDescripProcesoCorrecto = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.DESCRIPCION_PROCESO_VACIO);
		final ProcesoEntity datosEntradaBuscarFechaProcesoCorrecto = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.FECHA_PROCESO_VACIA);
		final ProcesoEntity datosEntradaBuscarDatosVacios = generarJSON.generarProceso(Constantes.URL_JSON_PROCESO_DATA,
				Constantes.DATOS_PROCESO_VACIOS);

		datosPruebaAcciones.add(getTestBuscarProceso(datosEntradaBuscarProcesoCorrecto));
		datosPruebaAcciones.add(getTestBuscarProceso(datosEntradaBuscarNombreProcesoCorrecto));
		datosPruebaAcciones.add(getTestBuscarProceso(datosEntradaBuscarDescripProcesoCorrecto));
		datosPruebaAcciones.add(getTestBuscarProceso(datosEntradaBuscarFechaProcesoCorrecto));
		datosPruebaAcciones.add(getTestBuscarProceso(datosEntradaBuscarDatosVacios));

		return datosPruebaAcciones;
	}

	private DatosPruebaAcciones getTestBuscarProceso(final ProcesoEntity datosEntradaAccionBuscarProceso) {

		final String resultadoObtenido = buscarProceso(datosEntradaAccionBuscarProceso);

		final String resultadoEsperado = CodigosMensajes.BUSCAR_PROCESO_CORRECTO + " - "
				+ Mensajes.PROCESO_BUSCADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.BUSCAR_CORRECTO, Constantes.EXITO, getValorProceso(datosEntradaAccionBuscarProceso));

	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesProcesoGuardar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final ProcesoEntity datosEntradaGuardarProcesoCorrecto = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.GUARDAR_PROCESO);
		final ProcesoEntity datosEntradaGuardarProcesoYaExiste = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.PROCESO_YA_EXISTE);
		final ProcesoEntity datosEntradaGuardarNombreProcesoVacio = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.NOMBRE_PROCESO_VACIO);
		final ProcesoEntity datosEntradaGuardarDescripcionProcesoVacia = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.DESCRIPCION_PROCESO_VACIO);
		final ProcesoEntity datosEntradaGuardarFechaProcesoVacia = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.FECHA_PROCESO_VACIA);
		final ProcesoEntity datosEntradaGuardarDatosProcesoVacios = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.DATOS_PROCESO_VACIOS);
		final ProcesoEntity datosEntradaGuardarFechaProcesoAnteriorFechaActual = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL);

		datosPruebaAcciones.add(getTestGuardarProcesoCorrecto(datosEntradaGuardarProcesoCorrecto));
		datosPruebaAcciones.add(getTestGuardarProcesoYaExiste(datosEntradaGuardarProcesoYaExiste));
		datosPruebaAcciones.add(getTestGuardarNombreProcesoVacio(datosEntradaGuardarNombreProcesoVacio));
		datosPruebaAcciones.add(getTestGuardarDescripcionProcesoVacio(datosEntradaGuardarDescripcionProcesoVacia));
		datosPruebaAcciones.add(getTestGuardarFechaProcesoVacio(datosEntradaGuardarFechaProcesoVacia));
		datosPruebaAcciones
				.add(getTestGuardarNombreDescripcionFechaProcesoVacios(datosEntradaGuardarDatosProcesoVacios));
		datosPruebaAcciones
				.add(getTestGuardarFechaProcesoAnteriorFechaActual(datosEntradaGuardarFechaProcesoAnteriorFechaActual));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesProcesoEliminar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final ProcesoEntity datosEntradaEliminarProcesoCorrecto = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.ELIMINAR_PROCESO);
		final ProcesoEntity datosEntradaEliminarProcesoNoExiste = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.PROCESO_NO_EXISTE);
		final ProcesoEntity datosEntradaEliminarProcesoAsociadoProcedimiento = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.PROCEDIMIENTO_ASOCIADO_PROCESO);
		final ProcesoEntity datosEntradaEliminarProcesoAsociadoRespuestaPosible = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.PROCESO_ASOCIADO_RESPUESTA_POSIBLE);
		final ProcesoEntity datosEntradaEliminarProcesoAsociadoProcedimientoUsuario = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.PROCESO_ASOCIADO_PROCEDIMIENTO_USUARIO);

		datosPruebaAcciones.add(getTestEliminarProcesoCorrecto(datosEntradaEliminarProcesoCorrecto));
		datosPruebaAcciones.add(getTestEliminarProcesoNoExiste(datosEntradaEliminarProcesoNoExiste));
		datosPruebaAcciones
				.add(getTestEliminarProcesoAsociadoProcedimiento(datosEntradaEliminarProcesoAsociadoProcedimiento));
		datosPruebaAcciones.add(
				getTestEliminarProcesoAsociadoRespuestaPosible(datosEntradaEliminarProcesoAsociadoRespuestaPosible));
		datosPruebaAcciones.add(getTestEliminarProcesoAsociadoProcedimientoUsuario(
				datosEntradaEliminarProcesoAsociadoProcedimientoUsuario));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesProcesoModificar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final ProcesoEntity datosEntradaModificarProcesoCorrecto = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.MODIFICAR_PROCESO);
		final ProcesoEntity datosEntradaModificarProcesoNoExiste = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.PROCESO_NO_EXISTE);
		final ProcesoEntity datosEntradaModificarNombreProcesoVacio = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.NOMBRE_PROCESO_VACIO);
		final ProcesoEntity datosEntradaModificarDescripcionProcesoVacia = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.DESCRIPCION_PROCESO_VACIO);
		final ProcesoEntity datosEntradaModificarFechaProcesoVacia = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.FECHA_PROCESO_VACIA);
		final ProcesoEntity datosEntradaModificarDatosProcesoVacios = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.DATOS_PROCESO_VACIOS);
		final ProcesoEntity datosEntradaModificarFechaProcesoAnteriorFechaActual = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL);

		datosPruebaAcciones.add(getTestModificarProcesoCorrecto(datosEntradaModificarProcesoCorrecto));
		datosPruebaAcciones.add(getTestModificarProcesoNoExiste(datosEntradaModificarProcesoNoExiste));
		datosPruebaAcciones.add(getTestModificarNombreProcesoVacio(datosEntradaModificarNombreProcesoVacio));
		datosPruebaAcciones.add(getTestModificarDescripcionProcesoVacio(datosEntradaModificarDescripcionProcesoVacia));
		datosPruebaAcciones.add(getTestModificarFechaProcesoVacio(datosEntradaModificarFechaProcesoVacia));
		datosPruebaAcciones
				.add(getTestModificarNombreDescripcionFechaProcesoVacios(datosEntradaModificarDatosProcesoVacios));
		datosPruebaAcciones.add(
				getTestModificarFechaProcesoAnteriorFechaActual(datosEntradaModificarFechaProcesoAnteriorFechaActual));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesProcesoReactivar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final ProcesoEntity datosEntradaReactivarProcesoCorrecto = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.REACTIVAR_PROCESO);
		final ProcesoEntity datosEntradaReactivarProcesoNoExiste = generarJSON
				.generarProceso(Constantes.URL_JSON_PROCESO_DATA, Constantes.PROCESO_NO_EXISTE);

		datosPruebaAcciones.add(getTestReactivarProcesoCorrecto(datosEntradaReactivarProcesoCorrecto));
		datosPruebaAcciones.add(getTestReactivarProcesoNoExiste(datosEntradaReactivarProcesoNoExiste));

		return datosPruebaAcciones;
	}

	private DatosPruebaAcciones getTestGuardarProcesoCorrecto(final ProcesoEntity datosEntradaAccionGuardarProceso)
			throws java.text.ParseException {

		final String resultadoObtenido = guardarProceso(datosEntradaAccionGuardarProceso);

		final String resultadoEsperado = CodigosMensajes.GUARDAR_PROCESO_CORRECTO + " - "
				+ Mensajes.PROCESO_GUARDADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.GUARDAR_PROCESO_CORRECTO, Constantes.EXITO,
				getValorProceso(datosEntradaAccionGuardarProceso));

	}

	private DatosPruebaAcciones getTestGuardarProcesoYaExiste(
			final ProcesoEntity datosEntradaAccionGuardarProcesoYaExiste) throws java.text.ParseException {

		final String resultadoObtenido = guardarProceso(datosEntradaAccionGuardarProcesoYaExiste);

		final String resultadoEsperado = CodigosMensajes.PROCESO_YA_EXISTE + " - " + Mensajes.PROCESO_YA_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.PROCESO_YA_EXISTE, Constantes.ERROR,
				getValorProceso(datosEntradaAccionGuardarProcesoYaExiste));

	}

	private DatosPruebaAcciones getTestGuardarNombreProcesoVacio(
			final ProcesoEntity datosEntradaAccionGuardarNombreProcesoVacio) throws java.text.ParseException {

		final String resultadoObtenido = guardarProceso(datosEntradaAccionGuardarNombreProcesoVacio);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_PROCESO_VACIO + " - "
				+ Mensajes.NOMBRE_PROCESO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProceso(datosEntradaAccionGuardarNombreProcesoVacio));

	}

	private DatosPruebaAcciones getTestGuardarDescripcionProcesoVacio(
			final ProcesoEntity datosEntradaAccionGuardarDescripcionProcesoVacio) throws java.text.ParseException {

		final String resultadoObtenido = guardarProceso(datosEntradaAccionGuardarDescripcionProcesoVacio);

		final String resultadoEsperado = CodigosMensajes.DESCRIPCION_PROCESO_VACIO + " - "
				+ Mensajes.DESCRIPCION_PROCESO_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProceso(datosEntradaAccionGuardarDescripcionProcesoVacio));

	}

	private DatosPruebaAcciones getTestGuardarFechaProcesoVacio(
			final ProcesoEntity datosEntradaAccionGuardarFechaProcesoVacio) throws java.text.ParseException {

		final String resultadoObtenido = guardarProceso(datosEntradaAccionGuardarFechaProcesoVacio);

		final String resultadoEsperado = CodigosMensajes.FECHA_PROCESO_VACIA + " - "
				+ Mensajes.FECHA_PROCESO_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, getValorProceso(datosEntradaAccionGuardarFechaProcesoVacio));

	}

	private DatosPruebaAcciones getTestGuardarNombreDescripcionFechaProcesoVacios(
			final ProcesoEntity datosEntradaAccionGuardarNombreDescripcionFechaProcesoVacios)
			throws java.text.ParseException {

		final String resultadoObtenido = guardarProceso(datosEntradaAccionGuardarNombreDescripcionFechaProcesoVacios);

		final String resultadoEsperado = CodigosMensajes.PROCESO_VACIO + " - " + Mensajes.PROCESO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProceso(datosEntradaAccionGuardarNombreDescripcionFechaProcesoVacios));
	}

	private DatosPruebaAcciones getTestGuardarFechaProcesoAnteriorFechaActual(
			final ProcesoEntity datosEntradaAccionGuardarFechaProcesoMenorFechaActual) throws java.text.ParseException {

		final String resultadoObtenido = guardarProceso(datosEntradaAccionGuardarFechaProcesoMenorFechaActual);

		final String resultadoEsperado = CodigosMensajes.FECHA_INTRODUCIDA_MENOR_FECHA_ACTUAL + " - "
				+ Mensajes.FECHA_INTRODUCIDA_NO_PUEDE_SER_MENOR_QUE_FECHA_ACTUAL;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL, Constantes.ERROR,
				getValorProceso(datosEntradaAccionGuardarFechaProcesoMenorFechaActual));
	}

	private DatosPruebaAcciones getTestModificarProcesoCorrecto(final ProcesoEntity datosEntradaAccionModificarProceso)
			throws java.text.ParseException {

		final String resultadoObtenido = modificarProceso(datosEntradaAccionModificarProceso);

		final String resultadoEsperado = CodigosMensajes.MODIFICAR_PROCESO_CORRECTO + " - "
				+ Mensajes.PROCESO_MODIFICADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.MODIFICAR_PROCESO_CORRECTO, Constantes.EXITO,
				getValorProceso(datosEntradaAccionModificarProceso));

	}

	private DatosPruebaAcciones getTestModificarProcesoNoExiste(
			final ProcesoEntity datosEntradaAccionModificarProcesoNoExiste) throws java.text.ParseException {

		final String resultadoObtenido = modificarProcesoNoExiste(datosEntradaAccionModificarProcesoNoExiste);

		final String resultadoEsperado = CodigosMensajes.PROCESO_NO_EXISTE + " - " + Mensajes.PROCESO_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.PROCESO_NO_EXISTE, Constantes.ERROR,
				getValorProceso(datosEntradaAccionModificarProcesoNoExiste));

	}

	private DatosPruebaAcciones getTestModificarNombreProcesoVacio(
			final ProcesoEntity datosEntradaAccionModificarNombreProcesoVacio) throws java.text.ParseException {

		final String resultadoObtenido = modificarProceso(datosEntradaAccionModificarNombreProcesoVacio);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_PROCESO_VACIO + " - "
				+ Mensajes.NOMBRE_PROCESO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProceso(datosEntradaAccionModificarNombreProcesoVacio));

	}

	private DatosPruebaAcciones getTestModificarDescripcionProcesoVacio(
			final ProcesoEntity datosEntradaAccionModificarDescripcionProcesoVacio) throws java.text.ParseException {

		final String resultadoObtenido = modificarProceso(datosEntradaAccionModificarDescripcionProcesoVacio);

		final String resultadoEsperado = CodigosMensajes.DESCRIPCION_PROCESO_VACIO + " - "
				+ Mensajes.DESCRIPCION_PROCESO_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProceso(datosEntradaAccionModificarDescripcionProcesoVacio));

	}

	private DatosPruebaAcciones getTestModificarFechaProcesoVacio(
			final ProcesoEntity datosEntradaAccionModificarFechaProcesoVacio) throws java.text.ParseException {

		final String resultadoObtenido = modificarProceso(datosEntradaAccionModificarFechaProcesoVacio);

		final String resultadoEsperado = CodigosMensajes.FECHA_PROCESO_VACIA + " - "
				+ Mensajes.FECHA_PROCESO_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProceso(datosEntradaAccionModificarFechaProcesoVacio));

	}

	private DatosPruebaAcciones getTestModificarNombreDescripcionFechaProcesoVacios(
			final ProcesoEntity datosEntradaAccionModificarNombreDescripcionFechaProcesoVacios)
			throws java.text.ParseException {

		final String resultadoObtenido = modificarProceso(
				datosEntradaAccionModificarNombreDescripcionFechaProcesoVacios);

		final String resultadoEsperado = CodigosMensajes.PROCESO_VACIO + " - " + Mensajes.PROCESO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorProceso(datosEntradaAccionModificarNombreDescripcionFechaProcesoVacios));
	}

	private DatosPruebaAcciones getTestModificarFechaProcesoAnteriorFechaActual(
			final ProcesoEntity datosEntradaAccionModificarFechaProcesoMenorFechaActual)
			throws java.text.ParseException {

		final String resultadoObtenido = modificarProceso(datosEntradaAccionModificarFechaProcesoMenorFechaActual);

		final String resultadoEsperado = CodigosMensajes.FECHA_INTRODUCIDA_MENOR_FECHA_ACTUAL + " - "
				+ Mensajes.FECHA_INTRODUCIDA_NO_PUEDE_SER_MENOR_QUE_FECHA_ACTUAL;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL, Constantes.ERROR,
				getValorProceso(datosEntradaAccionModificarFechaProcesoMenorFechaActual));
	}

	private DatosPruebaAcciones getTestEliminarProcesoCorrecto(
			final ProcesoEntity datosEntradaAccionEliminarProcesoCorrecto) throws java.text.ParseException {

		final String resultadoObtenido = eliminarProceso(datosEntradaAccionEliminarProcesoCorrecto);

		final String resultadoEsperado = CodigosMensajes.ELIMINAR_PROCESO_CORRECTO + " - "
				+ Mensajes.PROCESO_ELIMINADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ELIMINAR_PROCESO_CORRECTO, Constantes.ERROR,
				getValorProceso(datosEntradaAccionEliminarProcesoCorrecto));
	}

	private DatosPruebaAcciones getTestEliminarProcesoNoExiste(
			final ProcesoEntity datosEntradaAccionEliminarProcesoNoExiste) throws java.text.ParseException {

		final String resultadoObtenido = eliminarProcesoNoExiste(datosEntradaAccionEliminarProcesoNoExiste);

		final String resultadoEsperado = CodigosMensajes.PROCESO_NO_EXISTE + " - " + Mensajes.PROCESO_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.PROCESO_NO_EXISTE, Constantes.ERROR,
				getValorProceso(datosEntradaAccionEliminarProcesoNoExiste));
	}

	private DatosPruebaAcciones getTestEliminarProcesoAsociadoProcedimiento(
			final ProcesoEntity datosEntradaAccionEliminarProcesoAsociadoProcedimiento)
			throws java.text.ParseException {

		final String resultadoObtenido = eliminarProcesoAsociadoProcedimiento(
				datosEntradaAccionEliminarProcesoAsociadoProcedimiento);

		final String resultadoEsperado = CodigosMensajes.PROCESO_ASOCIADO_PROCEDIMIENTO + " - "
				+ Mensajes.PROCESO_ASOCIADO_PROCEDIMIENTO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.PROCESO_ASOCIADO_PROCEDIMIENTO, Constantes.ERROR,
				getValorProceso(datosEntradaAccionEliminarProcesoAsociadoProcedimiento));
	}

	private DatosPruebaAcciones getTestEliminarProcesoAsociadoRespuestaPosible(
			final ProcesoEntity datosEntradaAccionEliminarProcesoAsociadoRespuestaPosible)
			throws java.text.ParseException, ParseException {

		final String resultadoObtenido = eliminarProcesoAsociadoRespuestaPosible(
				datosEntradaAccionEliminarProcesoAsociadoRespuestaPosible);

		final String resultadoEsperado = CodigosMensajes.RESPUESTA_POSIBLE_ASOCIADA_PROCESO + " - "
				+ Mensajes.PROCESO_ASOCIADO_RESPUESTA_POSIBLES;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.PROCESO_ASOCIADO_RESPUESTA_POSIBLE, Constantes.ERROR,
				getValorProceso(datosEntradaAccionEliminarProcesoAsociadoRespuestaPosible));
	}

	private DatosPruebaAcciones getTestEliminarProcesoAsociadoProcedimientoUsuario(
			final ProcesoEntity datosEntradaAccionEliminarProcesoAsociadoProcedimientoUsuario)
			throws java.text.ParseException {

		final String resultadoObtenido = eliminarProcesoAsociadoProcedimientoUsuario(
				datosEntradaAccionEliminarProcesoAsociadoProcedimientoUsuario);

		final String resultadoEsperado = CodigosMensajes.PROCESO_ASOCIADO_PROCEDIMIENTO_USUARIO + " - "
				+ Mensajes.PROCESO_ASOCIADO_PROCEDIMIENTO_USUARIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.PROCESO_ASOCIADO_PROCEDIMIENTO_USUARIO, Constantes.ERROR,
				getValorProceso(datosEntradaAccionEliminarProcesoAsociadoProcedimientoUsuario));
	}

	private DatosPruebaAcciones getTestReactivarProcesoCorrecto(
			final ProcesoEntity datosEntradaAccionReactivarProcesoCorrecto) throws java.text.ParseException {

		final String resultadoObtenido = reactivarProceso(datosEntradaAccionReactivarProcesoCorrecto);

		final String resultadoEsperado = CodigosMensajes.REACTIVAR_PROCESO_CORRECTO + " - "
				+ Mensajes.PROCESO_REACTIVADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.PROCESO_ASOCIADO_RESPUESTA_POSIBLE, Constantes.ERROR,
				getValorProceso(datosEntradaAccionReactivarProcesoCorrecto));
	}

	private DatosPruebaAcciones getTestReactivarProcesoNoExiste(
			final ProcesoEntity datosEntradaAccionReactivarProcesoNoExiste) throws java.text.ParseException {

		final String resultadoObtenido = reactivarProcesoNoExiste(datosEntradaAccionReactivarProcesoNoExiste);

		final String resultadoEsperado = CodigosMensajes.PROCESO_NO_EXISTE + " - " + Mensajes.PROCESO_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.PROCESO_NO_EXISTE, Constantes.ERROR,
				getValorProceso(datosEntradaAccionReactivarProcesoNoExiste));
	}

	private String buscarProceso(final ProcesoEntity proceso) {
		String resultado = StringUtils.EMPTY;

		java.sql.Date fechaSql;
		fechaSql = new java.sql.Date(proceso.getFechaProceso().getTime());
		final String fecha = fechaSql.toString();

		procesoRepository.findProceso(proceso.getNombreProceso(), proceso.getDescripProceso(), fecha);

		resultado = CodigosMensajes.BUSCAR_PROCESO_CORRECTO + " - " + Mensajes.PROCESO_BUSCADO_CORRECTAMENTE;

		return resultado;
	}

	private String guardarProceso(final ProcesoEntity proceso) throws java.text.ParseException {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarNombreProcesoBlank(proceso.getNombreProceso())
				&& !validaciones.comprobarDescripProcesoBlank(proceso.getDescripProceso())
				&& !validaciones.comprobarFechaProcesoBlank(proceso.getFechaProceso())) {
			resultado = CodigosMensajes.PROCESO_VACIO + " - " + Mensajes.PROCESO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarNombreProcesoBlank(proceso.getNombreProceso())) {
			resultado = CodigosMensajes.NOMBRE_PROCESO_VACIO + " - " + Mensajes.NOMBRE_PROCESO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarDescripProcesoBlank(proceso.getDescripProceso())) {
			resultado = CodigosMensajes.DESCRIPCION_PROCESO_VACIO + " - "
					+ Mensajes.DESCRIPCION_PROCESO_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarFechaProcesoBlank(proceso.getFechaProceso())) {
			resultado = CodigosMensajes.FECHA_PROCESO_VACIA + " - " + Mensajes.FECHA_PROCESO_NO_PUEDE_SER_VACIA;
		} else {
			final ProcesoEntity procesoBD = procesoRepository.findProcesoByName(proceso.getNombreProceso());

			if (procesoBD != null) {
				resultado = CodigosMensajes.PROCESO_YA_EXISTE + " - " + Mensajes.PROCESO_YA_EXISTE;
			} else {
				final LocalDate fechaActual = LocalDate.now();
				final String fechaIntroducidaUsuario = proceso.getFechaProceso().toString();
				final String fechaActualString = fechaActual.getYear() + "-0" + fechaActual.getMonthValue() + "-"
						+ fechaActual.getDayOfMonth();

				if (fechaIntroducidaUsuario.compareTo(fechaActualString) < 0) {
					resultado = CodigosMensajes.FECHA_INTRODUCIDA_MENOR_FECHA_ACTUAL + " - "
							+ Mensajes.FECHA_INTRODUCIDA_NO_PUEDE_SER_MENOR_QUE_FECHA_ACTUAL;
				} else {
					proceso.setBorradoProceso(0);
					procesoRepository.saveAndFlush(proceso);
					resultado = CodigosMensajes.GUARDAR_PROCESO_CORRECTO + " - "
							+ Mensajes.PROCESO_GUARDADO_CORRECTAMENTE;

					final ProcesoEntity procesoBDEncontrado = procesoRepository
							.findProcesoByName(proceso.getNombreProceso());

					procesoRepository.deleteProceso(procesoBDEncontrado.getIdProceso());

				}

			}
		}

		return resultado;
	}

	private String modificarProceso(final ProcesoEntity proceso) throws java.text.ParseException {
		String resultado = StringUtils.EMPTY;
		Boolean modificar = Boolean.FALSE;

		if (!validaciones.comprobarNombreProcesoBlank(proceso.getNombreProceso())
				&& !validaciones.comprobarDescripProcesoBlank(proceso.getDescripProceso())
				&& !validaciones.comprobarFechaProcesoBlank(proceso.getFechaProceso())) {
			resultado = CodigosMensajes.PROCESO_VACIO + " - " + Mensajes.PROCESO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarNombreProcesoBlank(proceso.getNombreProceso())) {
			resultado = CodigosMensajes.NOMBRE_PROCESO_VACIO + " - " + Mensajes.NOMBRE_PROCESO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarDescripProcesoBlank(proceso.getDescripProceso())) {
			resultado = CodigosMensajes.DESCRIPCION_PROCESO_VACIO + " - "
					+ Mensajes.DESCRIPCION_PROCESO_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarFechaProcesoBlank(proceso.getFechaProceso())) {
			resultado = CodigosMensajes.FECHA_PROCESO_VACIA + " - " + Mensajes.FECHA_PROCESO_NO_PUEDE_SER_VACIA;
		} else {
			procesoRepository.saveAndFlush(proceso);

			final ProcesoEntity procesoBD = procesoRepository.findProcesoByName(proceso.getNombreProceso());

			final LocalDate fechaActual = LocalDate.now();
			final String fechaIntroducidaUsuario = proceso.getFechaProceso().toString();
			final String fechaActualString = fechaActual.getYear() + "-0" + fechaActual.getMonthValue() + "-"
					+ fechaActual.getDayOfMonth();

			if (!fechaIntroducidaUsuario.equals(procesoBD.getFechaProceso().toString())) {
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
				procesoBD.setDescripProceso("Descripción modificada");

				procesoRepository.saveAndFlush(procesoBD);

				resultado = CodigosMensajes.MODIFICAR_PROCESO_CORRECTO + " - "
						+ Mensajes.PROCESO_MODIFICADO_CORRECTAMENTE;

			}

			final ProcesoEntity procesoBDEncontrado = procesoRepository.findProcesoByName(proceso.getNombreProceso());

			procesoRepository.deleteProceso(procesoBDEncontrado.getIdProceso());

		}

		return resultado;
	}

	private String modificarProcesoNoExiste(final ProcesoEntity proceso) throws java.text.ParseException {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarNombreProcesoBlank(proceso.getNombreProceso())
				&& !validaciones.comprobarDescripProcesoBlank(proceso.getDescripProceso())
				&& !validaciones.comprobarFechaProcesoBlank(proceso.getFechaProceso())) {
			resultado = CodigosMensajes.PROCESO_VACIO + " - " + Mensajes.PROCESO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarNombreProcesoBlank(proceso.getNombreProceso())) {
			resultado = CodigosMensajes.NOMBRE_PROCESO_VACIO + " - " + Mensajes.NOMBRE_PROCESO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarDescripProcesoBlank(proceso.getDescripProceso())) {
			resultado = CodigosMensajes.DESCRIPCION_PROCESO_VACIO + " - "
					+ Mensajes.DESCRIPCION_PROCESO_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarFechaProcesoBlank(proceso.getFechaProceso())) {
			resultado = CodigosMensajes.FECHA_PROCESO_VACIA + " - " + Mensajes.FECHA_PROCESO_NO_PUEDE_SER_VACIA;
		} else {

			final ProcesoEntity procesoBD = procesoRepository.findProcesoByName(proceso.getNombreProceso());

			if (procesoBD == null) {
				resultado = CodigosMensajes.PROCESO_NO_EXISTE + " - " + Mensajes.PROCESO_NO_EXISTE;
			}

		}

		return resultado;
	}

	private String eliminarProceso(final ProcesoEntity proceso) {
		final ProcesoEntity procesoBD = procesoRepository.findProcesoByName(proceso.getNombreProceso());
		String resultado = StringUtils.EMPTY;

		if (procesoBD == null) {

			proceso.setBorradoProceso(0);
			procesoRepository.saveAndFlush(proceso);

			final ProcesoEntity procesoBDABorrar = procesoRepository.findProcesoByName(proceso.getNombreProceso());

			procesoBDABorrar.setBorradoProceso(1);
			procesoRepository.saveAndFlush(procesoBDABorrar);

			resultado = CodigosMensajes.ELIMINAR_PROCESO_CORRECTO + " - " + Mensajes.PROCESO_ELIMINADO_CORRECTAMENTE;

			final ProcesoEntity procesoBDEncontrado = procesoRepository.findProcesoByName(proceso.getNombreProceso());

			procesoRepository.deleteProceso(procesoBDEncontrado.getIdProceso());

		}

		return resultado;
	}

	private String eliminarProcesoNoExiste(final ProcesoEntity proceso) {
		final ProcesoEntity procesoBD = procesoRepository.findProcesoByName(proceso.getNombreProceso());
		String resultado = StringUtils.EMPTY;

		if (procesoBD == null) {
			resultado = CodigosMensajes.PROCESO_NO_EXISTE + " - " + Mensajes.PROCESO_NO_EXISTE;

		}
		return resultado;
	}

	private String eliminarProcesoAsociadoProcedimiento(final ProcesoEntity proceso) {
		final ProcesoEntity procesoBD = procesoRepository.findProcesoByName(proceso.getNombreProceso());
		String resultado = StringUtils.EMPTY;

		if (procesoBD == null) {
			proceso.setBorradoProceso(0);
			procesoRepository.saveAndFlush(proceso);

			final ObjetivoEntity objetivo = new ObjetivoEntity("Objetivo", "Objetivo de pruebas", 0);
			objetivoRepository.saveAndFlush(objetivo);
			final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
			plan.setObjetivo(objetivo);
			planRepository.saveAndFlush(plan);
			final ProcedimientoEntity procedimiento = new ProcedimientoEntity("Nombre procedimiento",
					"Descripción procedimiento", new Date(), 0, Boolean.FALSE, plan);
			procedimientoRepository.saveAndFlush(procedimiento);

			final ProcedimientoEntity procedimientoEncontrado = procedimientoRepository
					.findProcedimientoByName(procedimiento.getNombreProcedimiento());

			final List<ProcesoEntity> procesoEncontrado = procesoRepository.findProceso(proceso.getNombreProceso(),
					proceso.getDescripProceso(), resultado);
			final ProcesoProcedimientoEntity procesoProcedimientoEntity = new ProcesoProcedimientoEntity(
					procesoEncontrado.get(0).getIdProceso(), procedimientoEncontrado.getIdProcedimiento(), 3);
			procesoProcedimientoRepository.saveAndFlush(procesoProcedimientoEntity);

			final List<Integer> procesoProcedimientos = procesoProcedimientoRepository
					.findIdProcesoByIdProcedimiento(procedimientoEncontrado.getIdProcedimiento());

			if (!procesoProcedimientos.isEmpty()) {
				resultado = CodigosMensajes.PROCESO_ASOCIADO_PROCEDIMIENTO + " - "
						+ Mensajes.PROCESO_ASOCIADO_PROCEDIMIENTO;
			}

			final PlanEntity planBDNuevo = planRepository.findPlanByName(plan.getNombrePlan());
			final ObjetivoEntity objetivoBDNuevo = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());

			procesoProcedimientoRepository.deleteProcesoProcedimiento(procesoEncontrado.get(0).getIdProceso(),
					procedimientoEncontrado.getIdProcedimiento());
			procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
			procesoRepository.deleteProceso(procesoEncontrado.get(0).getIdProceso());
			planRepository.deletePlan(planBDNuevo.getIdPlan());
			objetivoRepository.deleteObjetivo(objetivoBDNuevo.getIdObjetivo());

		}

		return resultado;
	}

	private String eliminarProcesoAsociadoRespuestaPosible(final ProcesoEntity proceso) throws ParseException {
		final ProcesoEntity procesoBD = procesoRepository.findProcesoByName(proceso.getNombreProceso());
		String resultado = StringUtils.EMPTY;

		if (procesoBD == null) {
			proceso.setBorradoProceso(0);
			procesoRepository.saveAndFlush(proceso);

			final RespuestaPosibleEntity respuestaPosibleEntity = new RespuestaPosibleEntity("Cada 6 meses", 0);

			final RespuestaPosibleEntity respuestaPosibleEncontrada = respuestaPosibleRepository
					.findRespuestaPosibleByText(respuestaPosibleEntity.getTextoRespuesta());

			final List<ProcesoEntity> procesoEncontrado = procesoRepository.findProceso(proceso.getNombreProceso(),
					proceso.getDescripProceso(), resultado);
			final ProcesoRespuestaPosibleEntity procesoRespuestaPosible = new ProcesoRespuestaPosibleEntity(
					procesoEncontrado.get(0).getIdProceso(), respuestaPosibleEncontrada.getIdRespuesta(), new Date());
			procesoRespuestaPosibleRepository.saveAndFlush(procesoRespuestaPosible);

			final List<ProcesoRespuestaPosibleEntity> procesoRespuestaPosibleBD = procesoRespuestaPosibleRepository
					.findRespuestaPosibleByIdProceso(procesoEncontrado.get(0).getIdProceso());

			if (!procesoRespuestaPosibleBD.isEmpty()) {
				resultado = CodigosMensajes.RESPUESTA_POSIBLE_ASOCIADA_PROCESO + " - "
						+ Mensajes.PROCESO_ASOCIADO_RESPUESTA_POSIBLES;
			}

			procesoRespuestaPosibleRepository.deleteProcesoRespuestaPosible(respuestaPosibleEncontrada.getIdRespuesta(),
					procesoEncontrado.get(0).getIdProceso());
			respuestaPosibleRepository.deleteRespuestaPosible(respuestaPosibleEncontrada.getIdRespuesta());

		}

		return resultado;
	}

	private String eliminarProcesoAsociadoProcedimientoUsuario(final ProcesoEntity proceso)
			throws java.text.ParseException {
		final ProcesoEntity procesoBD = procesoRepository.findProcesoByName(proceso.getNombreProceso());
		String resultado = StringUtils.EMPTY;
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		if (procesoBD == null) {
			proceso.setBorradoProceso(0);
			procesoRepository.saveAndFlush(proceso);

			final ObjetivoEntity objetivo = new ObjetivoEntity("Objetivo", "Objetivo de pruebas", 0);
			objetivoRepository.saveAndFlush(objetivo);
			final PlanEntity plan = new PlanEntity("Nombre plan", "Descripción plan", new Date(), 0);
			plan.setObjetivo(objetivo);
			planRepository.saveAndFlush(plan);
			final ProcedimientoEntity procedimiento = new ProcedimientoEntity("Nombre procedimiento",
					"Descripción procedimiento", new Date(), 0, Boolean.FALSE, plan);
			procedimientoRepository.saveAndFlush(procedimiento);
			final ProcedimientoEntity procedimientoEncontrado = procedimientoRepository
					.findProcedimientoByName(procedimiento.getNombreProcedimiento());
			final PersonaEntity persona = new PersonaEntity("04149249A", "Pepe", "Pepe pepe",
					format.parse("2022-02-02"), "Calle de prueba", "988745121", "email@email.com", 0, null);
			final UsuarioEntity usuario = new UsuarioEntity(persona.getDniP(), "pepeUsuario", 0);
			personaRepository.saveAndFlush(persona);
			usuarioRepository.saveAndFlush(usuario);
			final ProcedimientoUsuarioEntity procedimientoUsuario = new ProcedimientoUsuarioEntity(0, new Date(), 0,
					procedimiento, usuario);
			procedimientoUsuarioRepository.saveAndFlush(procedimientoUsuario);
			final ProcedimientoUsuarioEntity procedimientoUsuarioEncontrado = procedimientoUsuarioRepository
					.findProcedimientoUsuarioByProcedimientoAndUsuario(usuario, procedimientoEncontrado);
			final List<ProcesoEntity> procesoEncontrado = procesoRepository.findProceso(proceso.getNombreProceso(),
					proceso.getDescripProceso(), resultado);
			final RespuestaPosibleEntity respuestaPosible = new RespuestaPosibleEntity("Respuesta posible", 0);
			respuestaPosibleRepository.saveAndFlush(respuestaPosible);
			final RespuestaPosibleEntity respuestaPosibleEncontrada = respuestaPosibleRepository
					.findRespuestaPosibleByText(respuestaPosible.getTextoRespuesta());

			final ProcedimientoUsuarioProcesoEntity procedimientoUsuarioProceso = new ProcedimientoUsuarioProcesoEntity(
					new Date(), 0, respuestaPosibleEncontrada, procesoEncontrado.get(0),
					procedimientoUsuarioEncontrado);
			procedimientoUsuarioProcesoRepository.saveAndFlush(procedimientoUsuarioProceso);

			final List<ProcedimientoUsuarioProcesoEntity> procedimientoUsuarioProcesoBD = procedimientoUsuarioProcesoRepository
					.findProcedimientoUsuarioProcesoByIdProceso(procesoEncontrado.get(0));

			if (!procedimientoUsuarioProcesoBD.isEmpty()) {
				resultado = CodigosMensajes.PROCESO_ASOCIADO_PROCEDIMIENTO_USUARIO + " - "
						+ Mensajes.PROCESO_ASOCIADO_PROCEDIMIENTO_USUARIO;
			}

			final PlanEntity planBDNuevo = planRepository.findPlanByName(plan.getNombrePlan());
			final ObjetivoEntity objetivoBDNuevo = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());

			procedimientoUsuarioProcesoRepository.deleteProcedimientoUsuarioProceso(
					procedimientoUsuarioProcesoBD.get(0).getIdProcedimientoUsuarioProceso());
			procedimientoUsuarioRepository
					.deleteProcedimientoUsuario(procedimientoUsuarioEncontrado.getIdProcedimientoUsuario());
			procesoRepository.deleteProceso(procesoEncontrado.get(0).getIdProceso());
			procedimientoRepository.deleteProcedimiento(procedimientoEncontrado.getIdProcedimiento());
			planRepository.deletePlan(planBDNuevo.getIdPlan());
			objetivoRepository.deleteObjetivo(objetivoBDNuevo.getIdObjetivo());
			respuestaPosibleRepository.findById(respuestaPosibleEncontrada.getIdRespuesta());
			usuarioRepository.deleteUsuario(usuario.getDniUsuario());
			personaRepository.deletePersona(persona.getDniP());

		}

		return resultado;
	}

	private String reactivarProceso(final ProcesoEntity proceso) {
		final ProcesoEntity procesoBD = procesoRepository.findProcesoByName(proceso.getNombreProceso());
		String resultado = StringUtils.EMPTY;

		if (procesoBD == null) {

			proceso.setBorradoProceso(0);
			procesoRepository.saveAndFlush(proceso);

			final ProcesoEntity procesoBDReactivar = procesoRepository.findProcesoByName(proceso.getNombreProceso());

			procesoBDReactivar.setBorradoProceso(1);
			procesoRepository.saveAndFlush(procesoBDReactivar);

			resultado = CodigosMensajes.REACTIVAR_PROCESO_CORRECTO + " - " + Mensajes.PROCESO_REACTIVADO_CORRECTAMENTE;

			procesoRepository.deleteProceso(procesoBDReactivar.getIdProceso());

		}

		return resultado;
	}

	private String reactivarProcesoNoExiste(final ProcesoEntity proceso) {
		final ProcesoEntity procesoBD = procesoRepository.findProcesoByName(proceso.getNombreProceso());
		String resultado = StringUtils.EMPTY;

		if (procesoBD == null) {
			resultado = CodigosMensajes.PROCESO_NO_EXISTE + " - " + Mensajes.PROCESO_NO_EXISTE;

		}

		return resultado;
	}

	private Map<String, String> getValorProceso(final ProcesoEntity proceso) {
		final Map<String, String> valor = new HashMap<>();

		valor.put(Constantes.NOMBRE_PROCESO, proceso.getNombreProceso());
		valor.put(Constantes.DESCRIP_PROCESO, proceso.getDescripProceso());
		valor.put(Constantes.FECHA_PROCESO, proceso.getFechaProceso().toString());

		return valor;

	}

}
