package com.sds.service.test.impl;

import java.io.IOException;
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
import com.sds.repository.ObjetivoRepository;
import com.sds.repository.PlanRepository;
import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.DefinicionPruebas;
import com.sds.service.common.Mensajes;
import com.sds.service.test.TestObjetivoService;
import com.sds.service.test.impl.atributos.TestAtributoDescripcionObjetivo;
import com.sds.service.test.impl.atributos.TestAtributoNombreObjetivo;
import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;
import com.sds.service.test.util.CrearDatosPruebaAcciones;
import com.sds.service.test.util.GenerarJSON;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class TestObjetivoServiceImpl implements TestObjetivoService {

	private final TestAtributoNombreObjetivo testAtributoNombreObjetivo;
	private final TestAtributoDescripcionObjetivo testAtributoDescripcionObjetivo;
	private final CrearDatosPruebaAcciones crearDatosPruebaAcciones;
	private final GenerarJSON generarJSON;
	private final Validaciones validaciones;

	@Autowired
	ObjetivoRepository objetivoRepository;

	@Autowired
	PlanRepository planRepository;

	public TestObjetivoServiceImpl() {
		testAtributoNombreObjetivo = new TestAtributoNombreObjetivo();
		testAtributoDescripcionObjetivo = new TestAtributoDescripcionObjetivo();
		crearDatosPruebaAcciones = new CrearDatosPruebaAcciones();
		generarJSON = new GenerarJSON();
		validaciones = new Validaciones();
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoNombreObjetivo()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final ObjetivoEntity datosEntradaNombreObjetivoVacio = generarJSON.generarObjetivo(
				Constantes.URL_JSON_OBJETIVO_ATRIBUTOS_NOMBREOBJETIVO, Constantes.NOMBRE_OBJETIVO_VACIO);
		final ObjetivoEntity datosEntradaNombreObjetivoCaracteresEspeciales = generarJSON.generarObjetivo(
				Constantes.URL_JSON_OBJETIVO_ATRIBUTOS_NOMBREOBJETIVO, Constantes.NOMBRE_OBJETIVO_CARACTERESESPECIALES);
		final ObjetivoEntity datosEntradaNombreObjetivoAlfabeticoMenor3 = generarJSON.generarObjetivo(
				Constantes.URL_JSON_OBJETIVO_ATRIBUTOS_NOMBREOBJETIVO, Constantes.NOMBRE_OBJETIVO_MENOR_3);
		final ObjetivoEntity datosEntradaNombreObjetivoAlfabeticoMayor48 = generarJSON.generarObjetivo(
				Constantes.URL_JSON_OBJETIVO_ATRIBUTOS_NOMBREOBJETIVO, Constantes.NOMBRE_OBJETIVO_MAYOR_48);
		final ObjetivoEntity datosEntradaNombreObjetivoAlfabetico = generarJSON.generarObjetivo(
				Constantes.URL_JSON_OBJETIVO_ATRIBUTOS_NOMBREOBJETIVO, Constantes.NOMBRE_OBJETIVO_CORRECTO);

		datosPruebaAtributos
				.add(testAtributoNombreObjetivo.getTestNombreObjetivoVacio(datosEntradaNombreObjetivoVacio));
		datosPruebaAtributos.add(testAtributoNombreObjetivo
				.getTestNombreObjetivoAlfabeticoCaracteresEspeciales(datosEntradaNombreObjetivoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoNombreObjetivo
				.getTestNombreObjetivoAlfabeticoMenor3(datosEntradaNombreObjetivoAlfabeticoMenor3));
		datosPruebaAtributos.add(testAtributoNombreObjetivo
				.getTestNombreObjetivoAlfabeticoMayor48(datosEntradaNombreObjetivoAlfabeticoMayor48));
		datosPruebaAtributos.add(testAtributoNombreObjetivo
				.getTestNombreObjetivoCorrectoAlfanumerico(datosEntradaNombreObjetivoAlfabetico));

		return datosPruebaAtributos;

	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoNombreObjetivoBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final ObjetivoEntity datosEntradaNombreObjetivoCaracteresEspeciales = generarJSON.generarObjetivo(
				Constantes.URL_JSON_OBJETIVO_ATRIBUTOS_NOMBREOBJETIVO, Constantes.NOMBRE_OBJETIVO_CARACTERESESPECIALES);
		final ObjetivoEntity datosEntradaNombreObjetivoAlfabeticoMayor48 = generarJSON.generarObjetivo(
				Constantes.URL_JSON_OBJETIVO_ATRIBUTOS_NOMBREOBJETIVO, Constantes.NOMBRE_OBJETIVO_MAYOR_48);
		final ObjetivoEntity datosEntradaNombreObjetivoAlfabetico = generarJSON.generarObjetivo(
				Constantes.URL_JSON_OBJETIVO_ATRIBUTOS_NOMBREOBJETIVO, Constantes.NOMBRE_OBJETIVO_CORRECTO);

		datosPruebaAtributos.add(testAtributoNombreObjetivo
				.getTestNombreObjetivoAlfabeticoCaracteresEspeciales(datosEntradaNombreObjetivoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoNombreObjetivo
				.getTestNombreObjetivoAlfabeticoMayor48(datosEntradaNombreObjetivoAlfabeticoMayor48));
		datosPruebaAtributos.add(testAtributoNombreObjetivo
				.getTestNombreObjetivoCorrectoAlfanumerico(datosEntradaNombreObjetivoAlfabetico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoDescripObjetivo()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final ObjetivoEntity datosEntradaDescripcionObjetivoVacio = generarJSON.generarObjetivo(
				Constantes.URL_JSON_OBJETIVO_ATRIBUTOS_DESCRIPCIONOBJETIVO, Constantes.DESCRIPCION_OBJETIVO_VACIO);
		final ObjetivoEntity datosEntradaDescripcionObjetivoCaracteresEspeciales = generarJSON.generarObjetivo(
				Constantes.URL_JSON_OBJETIVO_ATRIBUTOS_DESCRIPCIONOBJETIVO,
				Constantes.DESCRIPCION_OBJETIVO_CARACTERESESPECIALES);
		final ObjetivoEntity datosEntradaDescripcionObjetivoAlfabeticoMenor3 = generarJSON.generarObjetivo(
				Constantes.URL_JSON_OBJETIVO_ATRIBUTOS_DESCRIPCIONOBJETIVO, Constantes.DESCRIPCION_OBJETIVO_MENOR_3);
		final ObjetivoEntity datosEntradaDescripcionObjetivoAlfabetico = generarJSON.generarObjetivo(
				Constantes.URL_JSON_OBJETIVO_ATRIBUTOS_DESCRIPCIONOBJETIVO, Constantes.DESCRIPCION_OBJETIVO_CORRECTO);

		datosPruebaAtributos.add(
				testAtributoDescripcionObjetivo.getTestDescripcionObjetivoVacio(datosEntradaDescripcionObjetivoVacio));
		datosPruebaAtributos
				.add(testAtributoDescripcionObjetivo.getTestDescripcionObjetivoAlfabeticoCaracteresEspeciales(
						datosEntradaDescripcionObjetivoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoDescripcionObjetivo
				.getTestDescripcionObjetivoAlfabeticoMenor3(datosEntradaDescripcionObjetivoAlfabeticoMenor3));
		datosPruebaAtributos.add(testAtributoDescripcionObjetivo
				.getTestDescripcionObjetivoAlfanumericoCorrecto(datosEntradaDescripcionObjetivoAlfabetico));

		return datosPruebaAtributos;

	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoDescripObjetivoBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final ObjetivoEntity datosEntradaDescripcionObjetivoCaracteresEspeciales = generarJSON.generarObjetivo(
				Constantes.URL_JSON_OBJETIVO_ATRIBUTOS_DESCRIPCIONOBJETIVO,
				Constantes.DESCRIPCION_OBJETIVO_CARACTERESESPECIALES);
		final ObjetivoEntity datosEntradaDescripcionObjetivoAlfabetico = generarJSON.generarObjetivo(
				Constantes.URL_JSON_OBJETIVO_ATRIBUTOS_DESCRIPCIONOBJETIVO, Constantes.DESCRIPCION_OBJETIVO_CORRECTO);

		datosPruebaAtributos
				.add(testAtributoDescripcionObjetivo.getTestDescripcionObjetivoAlfabeticoCaracteresEspeciales(
						datosEntradaDescripcionObjetivoCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoDescripcionObjetivo
				.getTestDescripcionObjetivoAlfanumericoCorrecto(datosEntradaDescripcionObjetivoAlfabetico));

		return datosPruebaAtributos;

	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesObjetivoBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final ObjetivoEntity datosEntradaBuscarObjetivoCorrecto = generarJSON
				.generarObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.BUSCAR_OBJETIVO);
		final ObjetivoEntity datosEntradaBuscarNombreVacio = generarJSON
				.generarObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.NOMBRE_OBJETIVO_VACIO);
		final ObjetivoEntity datosEntradaBuscarDescripcionVacio = generarJSON
				.generarObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.DESCRIPCION_OBJETIVO_VACIO);
		final ObjetivoEntity datosEntradaBuscarNombreDescripcionVacios = generarJSON
				.generarObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.DATOS_OBJETIVO_VACIOS);

		datosPruebaAcciones.add(getTestBuscarObjetivo(datosEntradaBuscarObjetivoCorrecto));
		datosPruebaAcciones.add(getTestBuscarObjetivo(datosEntradaBuscarNombreVacio));
		datosPruebaAcciones.add(getTestBuscarObjetivo(datosEntradaBuscarDescripcionVacio));
		datosPruebaAcciones.add(getTestBuscarObjetivo(datosEntradaBuscarNombreDescripcionVacios));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesObjetivoGuardar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final ObjetivoEntity datosEntradaGuardarObjetivoCorrecto = generarJSON
				.generarObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.GUARDAR_OBJETIVO);
		final ObjetivoEntity datosEntradaGuardarNombreVacio = generarJSON
				.generarObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.NOMBRE_OBJETIVO_VACIO);
		final ObjetivoEntity datosEntradaGuardarDescripcionVacio = generarJSON
				.generarObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.DESCRIPCION_OBJETIVO_VACIO);
		final ObjetivoEntity datosEntradaGuardarNombreDescripcionVacia = generarJSON
				.generarObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.DATOS_OBJETIVO_VACIOS);
		final ObjetivoEntity datosEntradaGuardarObjetivoYaExiste = generarJSON
				.generarObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.OBJETIVO_YA_EXISTE);

		datosPruebaAcciones.add(getTestGuardarObjetivoCorrecto(datosEntradaGuardarObjetivoCorrecto));
		datosPruebaAcciones.add(getTestGuardarNombreObjetivoVacio(datosEntradaGuardarNombreVacio));
		datosPruebaAcciones.add(getTestGuardarDescripcionObjetivoVacio(datosEntradaGuardarDescripcionVacio));
		datosPruebaAcciones
				.add(getTestGuardarNombreDescripcionObjetivoVacio(datosEntradaGuardarNombreDescripcionVacia));
		datosPruebaAcciones.add(getTestGuardarObjetivoYaExiste(datosEntradaGuardarObjetivoYaExiste));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesObjetivoEliminar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final ObjetivoEntity datosEntradaEliminarObjetivoCorrecto = generarJSON
				.generarObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.ELIMINAR_OBJETIVO);
		final ObjetivoEntity datosEntradaEliminarObjetivoNoExiste = generarJSON
				.generarObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.OBJETIVO_NO_EXISTE);
		final ObjetivoEntity datosEntradaEliminarObjetivoAsociadoPlan = generarJSON
				.generarObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.ELIMINAR_OBJETIVO);

		datosPruebaAcciones.add(getTestEliminarObjetivoCorrecto(datosEntradaEliminarObjetivoCorrecto));
		datosPruebaAcciones.add(getTestEliminarObjetivoNoExiste(datosEntradaEliminarObjetivoNoExiste));
		datosPruebaAcciones.add(getTestEliminarObjetivoAsociadoPlan(datosEntradaEliminarObjetivoAsociadoPlan));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesObjetivoModificar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final ObjetivoEntity datosEntradaModificarObjetivoCorrecto = generarJSON
				.generarObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.MODIFICAR_OBJETIVO);
		final ObjetivoEntity datosEntradaModificarNombreVacio = generarJSON
				.generarObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.NOMBRE_OBJETIVO_VACIO);
		final ObjetivoEntity datosEntradaModificarDescripcionVacio = generarJSON
				.generarObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.DESCRIPCION_OBJETIVO_VACIO);
		final ObjetivoEntity datosEntradaModificarNombreDescripcionVacia = generarJSON
				.generarObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.DATOS_OBJETIVO_VACIOS);
		final ObjetivoEntity datosEntradaModificarObjetivoNoExiste = generarJSON
				.generarObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.OBJETIVO_NO_EXISTE);

		datosPruebaAcciones.add(getTestModificarObjetivoCorrecto(datosEntradaModificarObjetivoCorrecto));
		datosPruebaAcciones.add(getTestModificarNombreObjetivoVacio(datosEntradaModificarNombreVacio));
		datosPruebaAcciones.add(getTestModificarDescripcionObjetivoVacio(datosEntradaModificarDescripcionVacio));
		datosPruebaAcciones
				.add(getTestModificarNombreDescripcionObjetivoVacio(datosEntradaModificarNombreDescripcionVacia));
		datosPruebaAcciones.add(getTestModificarObjetivoNoExiste(datosEntradaModificarObjetivoNoExiste));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesObjetivoReactivar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final ObjetivoEntity datosEntradaReactivarObjetivoCorrecto = generarJSON
				.generarObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.REACTIVAR_OBJETIVO);
		final ObjetivoEntity datosEntradaReactivarObjetivoNoExiste = generarJSON
				.generarObjetivo(Constantes.URL_JSON_OBJETIVO_DATA, Constantes.OBJETIVO_NO_EXISTE);

		datosPruebaAcciones.add(getTestReactivarObjetivoCorrecto(datosEntradaReactivarObjetivoCorrecto));
		datosPruebaAcciones.add(getTestReactivarObjetivoNoExiste(datosEntradaReactivarObjetivoNoExiste));

		return datosPruebaAcciones;
	}

	private DatosPruebaAcciones getTestBuscarObjetivo(final ObjetivoEntity datosEntradaAccionBuscarObjetivo) {

		final String resultadoObtenido = buscarObjetivo(datosEntradaAccionBuscarObjetivo);

		final String resultadoEsperado = CodigosMensajes.BUSCAR_OBJETIVO_CORRECTO + " - "
				+ Mensajes.OBJETIVO_BUSCADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.BUSCAR_CORRECTO, Constantes.EXITO,
				getValorObjetivo(datosEntradaAccionBuscarObjetivo));

	}

	private DatosPruebaAcciones getTestGuardarObjetivoCorrecto(final ObjetivoEntity datosEntradaAccionGuardarObjetivo) {

		final String resultadoObtenido = guardarObjetivo(datosEntradaAccionGuardarObjetivo);

		final String resultadoEsperado = CodigosMensajes.GUARDAR_OBJETIVO_CORRECTO + " - "
				+ Mensajes.OBJETIVO_GUARDADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.GUARDAR_OBJETIVO_CORRECTO, Constantes.EXITO,
				getValorObjetivo(datosEntradaAccionGuardarObjetivo));

	}

	private DatosPruebaAcciones getTestGuardarObjetivoYaExiste(
			final ObjetivoEntity datosEntradaAccionGuardarObjetivoYaExiste) {

		final String resultadoObtenido = guardarObjetivo(datosEntradaAccionGuardarObjetivoYaExiste);

		final String resultadoEsperado = CodigosMensajes.OBJETIVO_YA_EXISTE + " - " + Mensajes.OBJETIVO_YA_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.OBJETIVO_YA_EXISTE, Constantes.ERROR,
				getValorObjetivo(datosEntradaAccionGuardarObjetivoYaExiste));

	}

	private DatosPruebaAcciones getTestGuardarNombreObjetivoVacio(
			final ObjetivoEntity datosEntradaAccionGuardarNombreObjetivoVacio) {

		final String resultadoObtenido = guardarObjetivo(datosEntradaAccionGuardarNombreObjetivoVacio);

		final String resultadoEsperado = CodigosMensajes.OBJETIVO_NAME_VACIO + " - "
				+ Mensajes.NOMBRE_OBJETIVO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorObjetivo(datosEntradaAccionGuardarNombreObjetivoVacio));

	}

	private DatosPruebaAcciones getTestGuardarDescripcionObjetivoVacio(
			final ObjetivoEntity datosEntradaAccionGuardarDescripcionObjetivoVacio) {

		final String resultadoObtenido = guardarObjetivo(datosEntradaAccionGuardarDescripcionObjetivoVacio);

		final String resultadoEsperado = CodigosMensajes.OBJETIVO_DESCRIPTION_VACIO + " - "
				+ Mensajes.DESCRIPCION_OBJETIVO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorObjetivo(datosEntradaAccionGuardarDescripcionObjetivoVacio));

	}

	private DatosPruebaAcciones getTestGuardarNombreDescripcionObjetivoVacio(
			final ObjetivoEntity datosEntradaAccionGuardarNombreDescripcionObjetivoVacio) {

		final String resultadoObtenido = guardarObjetivo(datosEntradaAccionGuardarNombreDescripcionObjetivoVacio);

		final String resultadoEsperado = CodigosMensajes.OBJETIVO_VACIO + " - " + Mensajes.OBJETIVO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorObjetivo(datosEntradaAccionGuardarNombreDescripcionObjetivoVacio));
	}

	private DatosPruebaAcciones getTestModificarObjetivoCorrecto(
			final ObjetivoEntity datosEntradaAccionModificarObjetivo) {

		final String resultadoObtenido = modificarObjetivo(datosEntradaAccionModificarObjetivo);

		final String resultadoEsperado = CodigosMensajes.MODIFICAR_OBJETIVO_CORRECTO + " - "
				+ Mensajes.OBJETIVO_MODIFICADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.MODIFICAR_OBJETIVO_CORRECTO, Constantes.EXITO,
				getValorObjetivo(datosEntradaAccionModificarObjetivo));

	}

	private DatosPruebaAcciones getTestModificarObjetivoNoExiste(
			final ObjetivoEntity datosEntradaAccionModificarObjetivoNoExiste) {

		final String resultadoObtenido = modificarObjetivoNoExiste(datosEntradaAccionModificarObjetivoNoExiste);

		final String resultadoEsperado = CodigosMensajes.OBJETIVO_NO_EXISTE + " - " + Mensajes.OBJETIVO_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.OBJETIVO_NO_EXISTE, Constantes.ERROR,
				getValorObjetivo(datosEntradaAccionModificarObjetivoNoExiste));

	}

	private DatosPruebaAcciones getTestModificarNombreObjetivoVacio(
			final ObjetivoEntity datosEntradaAccionModificarNombreObjetivoVacio) {

		final String resultadoObtenido = modificarObjetivo(datosEntradaAccionModificarNombreObjetivoVacio);

		final String resultadoEsperado = CodigosMensajes.OBJETIVO_NAME_VACIO + " - "
				+ Mensajes.NOMBRE_OBJETIVO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorObjetivo(datosEntradaAccionModificarNombreObjetivoVacio));

	}

	private DatosPruebaAcciones getTestModificarDescripcionObjetivoVacio(
			final ObjetivoEntity datosEntradaAccionModificarDescripcionObjetivoVacio) {

		final String resultadoObtenido = modificarObjetivo(datosEntradaAccionModificarDescripcionObjetivoVacio);

		final String resultadoEsperado = CodigosMensajes.OBJETIVO_DESCRIPTION_VACIO + " - "
				+ Mensajes.DESCRIPCION_OBJETIVO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorObjetivo(datosEntradaAccionModificarDescripcionObjetivoVacio));

	}

	private DatosPruebaAcciones getTestModificarNombreDescripcionObjetivoVacio(
			final ObjetivoEntity datosEntradaAccionModificarNombreDescripcionObjetivoVacio) {

		final String resultadoObtenido = modificarObjetivo(datosEntradaAccionModificarNombreDescripcionObjetivoVacio);

		final String resultadoEsperado = CodigosMensajes.OBJETIVO_VACIO + " - " + Mensajes.OBJETIVO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorObjetivo(datosEntradaAccionModificarNombreDescripcionObjetivoVacio));
	}

	private DatosPruebaAcciones getTestEliminarObjetivoCorrecto(
			final ObjetivoEntity datosEntradaAccionEliminarObjetivo) {

		final String resultadoObtenido = eliminarObjetivo(datosEntradaAccionEliminarObjetivo);

		final String resultadoEsperado = CodigosMensajes.ELIMINAR_OBJETIVO_CORRECTO + " - "
				+ Mensajes.OBJETIVO_ELIMINADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ELIMINAR_OBJETIVO_CORRECTO, Constantes.ERROR,
				getValorObjetivo(datosEntradaAccionEliminarObjetivo));

	}

	private DatosPruebaAcciones getTestEliminarObjetivoNoExiste(
			final ObjetivoEntity datosEntradaAccionEliminarObjetivoNoExiste) {

		final String resultadoObtenido = eliminarObjetivoNoExiste(datosEntradaAccionEliminarObjetivoNoExiste);

		final String resultadoEsperado = CodigosMensajes.OBJETIVO_NO_EXISTE + " - " + Mensajes.OBJETIVO_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.OBJETIVO_NO_EXISTE, Constantes.ERROR,
				getValorObjetivo(datosEntradaAccionEliminarObjetivoNoExiste));

	}

	private DatosPruebaAcciones getTestEliminarObjetivoAsociadoPlan(
			final ObjetivoEntity datosEntradaAccionEliminarObjetivoAsociadoPlan) {

		final String resultadoObtenido = eliminarObjetivoAsociadoPlan(datosEntradaAccionEliminarObjetivoAsociadoPlan);

		final String resultadoEsperado = CodigosMensajes.OBJETIVO_ASOCIADO_PLAN + " - "
				+ Mensajes.OBJETIVO_ASOCIADO_PLAN;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.OBJETIVO_ASOCIADO_PLAN, Constantes.ERROR,
				getValorObjetivo(datosEntradaAccionEliminarObjetivoAsociadoPlan));

	}

	private DatosPruebaAcciones getTestReactivarObjetivoCorrecto(
			final ObjetivoEntity datosEntradaAccionEliminarObjetivo) {

		final String resultadoObtenido = reactivarObjetivo(datosEntradaAccionEliminarObjetivo);

		final String resultadoEsperado = CodigosMensajes.REACTIVAR_OBJETIVO_CORRECTO + " - "
				+ Mensajes.OBJETIVO_REACTIVADO_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.REACTIVAR_OBJETIVO_CORRECTO, Constantes.ERROR,
				getValorObjetivo(datosEntradaAccionEliminarObjetivo));

	}

	private DatosPruebaAcciones getTestReactivarObjetivoNoExiste(
			final ObjetivoEntity datosEntradaAccionReactivarObjetivoNoExiste) {

		final String resultadoObtenido = reactivarObjetivoNoExiste(datosEntradaAccionReactivarObjetivoNoExiste);

		final String resultadoEsperado = CodigosMensajes.OBJETIVO_NO_EXISTE + " - " + Mensajes.OBJETIVO_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.OBJETIVO_NO_EXISTE, Constantes.ERROR,
				getValorObjetivo(datosEntradaAccionReactivarObjetivoNoExiste));

	}

	private String buscarObjetivo(final ObjetivoEntity objetivo) {
		String resultado = StringUtils.EMPTY;

		objetivoRepository.findObjetivo(objetivo.getNombreObjetivo(), objetivo.getDescripObjetivo());

		resultado = CodigosMensajes.BUSCAR_OBJETIVO_CORRECTO + " - " + Mensajes.OBJETIVO_BUSCADO_CORRECTAMENTE;

		return resultado;
	}

	private String guardarObjetivo(final ObjetivoEntity objetivo) {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarNombreObjetivoBlank(objetivo.getNombreObjetivo())
				&& !validaciones.comprobarDescripcionObjetivoBlank(objetivo.getDescripObjetivo())) {
			resultado = CodigosMensajes.OBJETIVO_VACIO + " - " + Mensajes.OBJETIVO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarNombreObjetivoBlank(objetivo.getNombreObjetivo())) {
			resultado = CodigosMensajes.OBJETIVO_NAME_VACIO + " - " + Mensajes.NOMBRE_OBJETIVO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarDescripcionObjetivoBlank(objetivo.getDescripObjetivo())) {
			resultado = CodigosMensajes.OBJETIVO_DESCRIPTION_VACIO + " - "
					+ Mensajes.DESCRIPCION_OBJETIVO_NO_PUEDE_SER_VACIO;
		} else {
			final ObjetivoEntity objetivoBD = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());

			if (objetivoBD != null) {
				resultado = CodigosMensajes.OBJETIVO_YA_EXISTE + " - " + Mensajes.OBJETIVO_YA_EXISTE;
			} else {
				objetivo.setBorradoObjetivo(0);
				objetivoRepository.saveAndFlush(objetivo);
				resultado = CodigosMensajes.GUARDAR_OBJETIVO_CORRECTO + " - "
						+ Mensajes.OBJETIVO_GUARDADO_CORRECTAMENTE;

				final ObjetivoEntity objetivoBDNueva = objetivoRepository
						.findObjetivoByName(objetivo.getNombreObjetivo());

				objetivoRepository.delete(objetivoBDNueva);
			}
		}

		return resultado;
	}

	private String modificarObjetivo(final ObjetivoEntity objetivo) {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarNombreObjetivoBlank(objetivo.getNombreObjetivo())
				&& !validaciones.comprobarDescripcionObjetivoBlank(objetivo.getDescripObjetivo())) {
			resultado = CodigosMensajes.OBJETIVO_VACIO + " - " + Mensajes.OBJETIVO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarNombreObjetivoBlank(objetivo.getNombreObjetivo())) {
			resultado = CodigosMensajes.OBJETIVO_NAME_VACIO + " - " + Mensajes.NOMBRE_OBJETIVO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarDescripcionObjetivoBlank(objetivo.getDescripObjetivo())) {
			resultado = CodigosMensajes.OBJETIVO_DESCRIPTION_VACIO + " - "
					+ Mensajes.DESCRIPCION_OBJETIVO_NO_PUEDE_SER_VACIO;
		} else {

			objetivoRepository.saveAndFlush(objetivo);

			final ObjetivoEntity objetivoBD = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());
			objetivoBD.setDescripObjetivo("Descripción modificado");

			objetivoRepository.saveAndFlush(objetivoBD);

			resultado = CodigosMensajes.MODIFICAR_OBJETIVO_CORRECTO + " - "
					+ Mensajes.OBJETIVO_MODIFICADO_CORRECTAMENTE;

			objetivoRepository.delete(objetivoBD);

		}

		return resultado;
	}

	private String modificarObjetivoNoExiste(final ObjetivoEntity objetivo) {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarNombreObjetivoBlank(objetivo.getNombreObjetivo())
				&& !validaciones.comprobarDescripcionObjetivoBlank(objetivo.getDescripObjetivo())) {
			resultado = CodigosMensajes.OBJETIVO_VACIO + " - " + Mensajes.OBJETIVO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarNombreObjetivoBlank(objetivo.getNombreObjetivo())) {
			resultado = CodigosMensajes.OBJETIVO_NAME_VACIO + " - " + Mensajes.NOMBRE_OBJETIVO_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarDescripcionObjetivoBlank(objetivo.getDescripObjetivo())) {
			resultado = CodigosMensajes.OBJETIVO_DESCRIPTION_VACIO + " - "
					+ Mensajes.DESCRIPCION_OBJETIVO_NO_PUEDE_SER_VACIO;
		} else {

			final ObjetivoEntity objetivoBD = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());

			if (objetivoBD == null) {
				resultado = CodigosMensajes.OBJETIVO_NO_EXISTE + " - " + Mensajes.OBJETIVO_NO_EXISTE;
			}

		}

		return resultado;
	}

	private String eliminarObjetivo(final ObjetivoEntity objetivo) {
		final ObjetivoEntity objetivoBD = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());
		String resultado = StringUtils.EMPTY;

		if (objetivoBD == null) {
			objetivo.setBorradoObjetivo(0);
			objetivoRepository.saveAndFlush(objetivo);

			final ObjetivoEntity objetivoBDBorrar = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());

			objetivoBDBorrar.setBorradoObjetivo(1);
			objetivoRepository.saveAndFlush(objetivoBDBorrar);

			final ObjetivoEntity objetivoBDNuevo = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());

			objetivoRepository.deleteObjetivo(objetivoBDNuevo.getIdObjetivo());

			resultado = CodigosMensajes.ELIMINAR_OBJETIVO_CORRECTO + " - " + Mensajes.OBJETIVO_ELIMINADO_CORRECTAMENTE;

		}

		return resultado;
	}

	private String eliminarObjetivoNoExiste(final ObjetivoEntity objetivo) {
		final ObjetivoEntity objetivoBD = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());
		String resultado = StringUtils.EMPTY;

		if (objetivoBD == null) {
			resultado = CodigosMensajes.OBJETIVO_NO_EXISTE + " - " + Mensajes.OBJETIVO_NO_EXISTE;

		}

		return resultado;
	}

	private String eliminarObjetivoAsociadoPlan(final ObjetivoEntity objetivo) {
		final ObjetivoEntity objetivoBD = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());
		String resultado = StringUtils.EMPTY;
		Boolean eliminarObjetivo = Boolean.FALSE;

		if (objetivoBD == null) {
			objetivo.setBorradoObjetivo(0);
			objetivoRepository.saveAndFlush(objetivo);
			final ObjetivoEntity objetivoBuscar = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());
			final PlanEntity plan = new PlanEntity("Nombre plan", "Plan de pruebas", new Date(), 0);
			plan.setObjetivo(objetivoBuscar);
			planRepository.saveAndFlush(plan);

			final List<PlanEntity> planes = planRepository.findAll();

			if (!planes.isEmpty()) {
				for (final PlanEntity planEntity : planes) {
					if (!planEntity.getObjetivo().getIdObjetivo().equals(objetivoBuscar.getIdObjetivo())) {
						eliminarObjetivo = true;
					} else {
						eliminarObjetivo = false;
						break;
					}
				}
			}

			if (Boolean.FALSE.equals(eliminarObjetivo)) {
				resultado = CodigosMensajes.OBJETIVO_ASOCIADO_PLAN + " - " + Mensajes.OBJETIVO_ASOCIADO_PLAN;
			}

			final PlanEntity planBuscar = planRepository.findPlanByName(plan.getNombrePlan());

			planRepository.delete(planBuscar);
			objetivoRepository.delete(objetivoBuscar);

		}

		return resultado;
	}

	private String reactivarObjetivo(final ObjetivoEntity objetivo) {
		final ObjetivoEntity objetivoBD = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());
		String resultado = StringUtils.EMPTY;

		if (objetivoBD == null) {

			objetivo.setBorradoObjetivo(1);
			objetivoRepository.saveAndFlush(objetivo);

			final ObjetivoEntity objetivoBDReactivar = objetivoRepository
					.findObjetivoByName(objetivo.getNombreObjetivo());

			objetivoBDReactivar.setBorradoObjetivo(0);
			objetivoRepository.saveAndFlush(objetivoBDReactivar);

			final ObjetivoEntity objetivoBDNuevo = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());

			objetivoRepository.delete(objetivoBDNuevo);

			resultado = CodigosMensajes.REACTIVAR_OBJETIVO_CORRECTO + " - "
					+ Mensajes.OBJETIVO_REACTIVADO_CORRECTAMENTE;

		}

		return resultado;
	}

	private String reactivarObjetivoNoExiste(final ObjetivoEntity objetivo) {
		final ObjetivoEntity objetivoBD = objetivoRepository.findObjetivoByName(objetivo.getNombreObjetivo());
		String resultado = StringUtils.EMPTY;

		if (objetivoBD == null) {
			resultado = CodigosMensajes.OBJETIVO_NO_EXISTE + " - " + Mensajes.OBJETIVO_NO_EXISTE;

		}

		return resultado;
	}

	private Map<String, String> getValorObjetivo(final ObjetivoEntity objetivo) {
		final Map<String, String> valor = new HashMap<>();

		valor.put(Constantes.NOMBRE_OBJETIVO, objetivo.getNombreObjetivo());
		valor.put(Constantes.DESCRIPCION_OBJETIVO, objetivo.getDescripObjetivo());

		return valor;

	}

}
