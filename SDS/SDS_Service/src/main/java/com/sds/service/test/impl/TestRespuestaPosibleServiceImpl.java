package com.sds.service.test.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.model.ProcesoRespuestaPosibleEntity;
import com.sds.model.RespuestaPosibleEntity;
import com.sds.repository.ProcesoRespuestaPosibleRepository;
import com.sds.repository.RespuestaPosibleRepository;
import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.DefinicionPruebas;
import com.sds.service.common.Mensajes;
import com.sds.service.test.TestRespuestaPosibleService;
import com.sds.service.test.impl.atributos.TestAtributoTextoRespuestaPosible;
import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;
import com.sds.service.test.util.CrearDatosPruebaAcciones;
import com.sds.service.test.util.GenerarJSON;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class TestRespuestaPosibleServiceImpl implements TestRespuestaPosibleService {

	private final TestAtributoTextoRespuestaPosible testAtributoTextoRespuestaPosible;
	private final CrearDatosPruebaAcciones crearDatosPruebaAcciones;
	private final GenerarJSON generarJSON;
	private final Validaciones validaciones;

	@Autowired
	RespuestaPosibleRepository respuestaPosibleRepository;

	@Autowired
	ProcesoRespuestaPosibleRepository procesoRespuestaPosibleRepository;

	public TestRespuestaPosibleServiceImpl() {
		testAtributoTextoRespuestaPosible = new TestAtributoTextoRespuestaPosible();
		crearDatosPruebaAcciones = new CrearDatosPruebaAcciones();
		generarJSON = new GenerarJSON();
		validaciones = new Validaciones();
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoTextoRespuestaPosible()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final RespuestaPosibleEntity datosEntradaTextoRespuestaPosibleVacio = generarJSON.generarRespuestaPosible(
				Constantes.URL_JSON_RESPUESTA_POSIBLE_ATRIBUTOS_TEXTO_RESPUESTA,
				Constantes.TEXTO_RESPUESTA_POSIBLE_VACIO);
		final RespuestaPosibleEntity datosEntradaTextoRespuestaPosibleCaracteresEspeciales = generarJSON
				.generarRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_ATRIBUTOS_TEXTO_RESPUESTA,
						Constantes.TEXTO_RESPUESTA_POSIBLE_CARACTERESESPECIALES_SIN_SIGNOS_PUNTUACION);
		final RespuestaPosibleEntity datosEntradaTextoRespuestaPosibleAlfanumericoMenor2 = generarJSON
				.generarRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_ATRIBUTOS_TEXTO_RESPUESTA,
						Constantes.TEXTO_RESPUESTA_POSIBLE_MENOR_2);
		final RespuestaPosibleEntity datosEntradaTextoRespuestaAlfanumericoCorrecto = generarJSON
				.generarRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_ATRIBUTOS_TEXTO_RESPUESTA,
						Constantes.TEXTO_RESPUESTA_POSIBLE_CORRECTO);

		datosPruebaAtributos.add(testAtributoTextoRespuestaPosible
				.getTestTextoRespuestaPosibleVacio(datosEntradaTextoRespuestaPosibleVacio));
		datosPruebaAtributos
				.add(testAtributoTextoRespuestaPosible.getTestTextoRespuestaPosibleAlfabeticoCaracteresEspeciales(
						datosEntradaTextoRespuestaPosibleCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoTextoRespuestaPosible
				.getTestTextoRespuestaPosibleAlfabeticoMenor2(datosEntradaTextoRespuestaPosibleAlfanumericoMenor2));
		datosPruebaAtributos
				.add(testAtributoTextoRespuestaPosible.getTestTextoRespuestaPosibleAlfanumericoSignosPuntuacionCorrecto(
						datosEntradaTextoRespuestaAlfanumericoCorrecto));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoTextoRespuestaPosibleBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final RespuestaPosibleEntity datosEntradaTextoRespuestaPosibleCaracteresEspeciales = generarJSON
				.generarRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_ATRIBUTOS_TEXTO_RESPUESTA,
						Constantes.TEXTO_RESPUESTA_POSIBLE_CARACTERESESPECIALES_SIN_SIGNOS_PUNTUACION);
		final RespuestaPosibleEntity datosEntradaTextoRespuestaAlfanumericoCorrecto = generarJSON
				.generarRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_ATRIBUTOS_TEXTO_RESPUESTA,
						Constantes.TEXTO_RESPUESTA_POSIBLE_CORRECTO);

		datosPruebaAtributos
				.add(testAtributoTextoRespuestaPosible.getTestTextoRespuestaPosibleAlfabeticoCaracteresEspeciales(
						datosEntradaTextoRespuestaPosibleCaracteresEspeciales));
		datosPruebaAtributos
				.add(testAtributoTextoRespuestaPosible.getTestTextoRespuestaPosibleAlfanumericoSignosPuntuacionCorrecto(
						datosEntradaTextoRespuestaAlfanumericoCorrecto));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesRespuestaPosibleBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final RespuestaPosibleEntity datosEntradaBuscarRespuestaPosibleCorrecto = generarJSON.generarRespuestaPosible(
				Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA, Constantes.BUSCAR_RESPUESTA_POSIBLE);
		final RespuestaPosibleEntity datosEntradaBuscarRespuestaPosibleTextoVacio = generarJSON.generarRespuestaPosible(
				Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA, Constantes.TEXTO_RESPUESTA_POSIBLE_VACIO);
		final RespuestaPosibleEntity datosEntradaBuscarRespuestaPosibleFechaVacia = generarJSON.generarRespuestaPosible(
				Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA, Constantes.FECHA_RESPUESTA_POSIBLE_VACIA);
		final RespuestaPosibleEntity datosEntradaBuscarRespuestaPosibleRespuestaVacia = generarJSON
				.generarRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA,
						Constantes.DATOS_RESPUESTA_POSIBLE_VACIA);

		datosPruebaAcciones.add(getTestBuscarRespuestaPosible(datosEntradaBuscarRespuestaPosibleCorrecto));
		datosPruebaAcciones.add(getTestBuscarRespuestaPosible(datosEntradaBuscarRespuestaPosibleTextoVacio));
		datosPruebaAcciones.add(getTestBuscarRespuestaPosible(datosEntradaBuscarRespuestaPosibleFechaVacia));
		datosPruebaAcciones.add(getTestBuscarRespuestaPosible(datosEntradaBuscarRespuestaPosibleRespuestaVacia));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesRespuestaPosibleGuardar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final RespuestaPosibleEntity datosEntradaGuardarRespuestaPosibleCorrecto = generarJSON.generarRespuestaPosible(
				Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA, Constantes.GUARDAR_RESPUESTA_POSIBLE);
		final RespuestaPosibleEntity datosEntradaGuardarTextoRespuestaPosibleVacio = generarJSON
				.generarRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA,
						Constantes.TEXTO_RESPUESTA_POSIBLE_VACIO);
		final RespuestaPosibleEntity datosEntradaGuardarRespuestaPosibleYaExiste = generarJSON.generarRespuestaPosible(
				Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA, Constantes.RESPUESTA_POSIBLE_YA_EXISTE);

		datosPruebaAcciones.add(getTestGuardarRespuestaPosibleCorrecto(datosEntradaGuardarRespuestaPosibleCorrecto));
		datosPruebaAcciones
				.add(getTestGuardarTextoRespuestaPosibleVacio(datosEntradaGuardarTextoRespuestaPosibleVacio));
		datosPruebaAcciones.add(getTestGuardarRespuestaPosibleYaExiste(datosEntradaGuardarRespuestaPosibleYaExiste));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesRespuestaPosibleEliminar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final RespuestaPosibleEntity datosEntradaEliminarRespuestaPosibleCorrecto = generarJSON.generarRespuestaPosible(
				Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA, Constantes.ELIMINAR_RESPUESTA_POSIBLE);
		final RespuestaPosibleEntity datosEntradaEliminarRespuestaPosibleAsociadaProceso = generarJSON
				.generarRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA,
						Constantes.RESPUESTA_POSIBLE_ASOCIADA_PROCESO);
		final RespuestaPosibleEntity datosEntradaEliminarRespuestaPosibleNoExiste = generarJSON.generarRespuestaPosible(
				Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA, Constantes.RESPUESTA_POSIBLE_NO_EXISTE);

		datosPruebaAcciones.add(getTestEliminarRespuestaPosibleCorrecto(datosEntradaEliminarRespuestaPosibleCorrecto));
		datosPruebaAcciones.add(
				getTestEliminarRespuestaPosibleAsociadaProceso(datosEntradaEliminarRespuestaPosibleAsociadaProceso));
		datosPruebaAcciones.add(getTestEliminarRespuestaPosibleNoExiste(datosEntradaEliminarRespuestaPosibleNoExiste));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesRespuestaPosibleModificar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final RespuestaPosibleEntity datosEntradaModificarRespuestaPosibleCorrecto = generarJSON
				.generarRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA,
						Constantes.MODIFICAR_RESPUESTA_POSIBLE);
		final RespuestaPosibleEntity datosEntradaModificarTextoRespuestaPosibleVacio = generarJSON
				.generarRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA,
						Constantes.TEXTO_RESPUESTA_POSIBLE_VACIO);
		final RespuestaPosibleEntity datosEntradaModificarRespuestaPosibleNoExiste = generarJSON
				.generarRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA,
						Constantes.RESPUESTA_POSIBLE_NO_EXISTE);

		datosPruebaAcciones
				.add(getTestModificarRespuestaPosibleCorrecto(datosEntradaModificarRespuestaPosibleCorrecto));
		datosPruebaAcciones
				.add(getTestModificarTextoRespuestaPosibleVacio(datosEntradaModificarTextoRespuestaPosibleVacio));
		datosPruebaAcciones
				.add(getTestModificarRespuestaPosibleNoExiste(datosEntradaModificarRespuestaPosibleNoExiste));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasAccionesRespuestaPosibleReactivar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final RespuestaPosibleEntity datosEntradaReactivarRespuestaPosibleCorrecto = generarJSON
				.generarRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA,
						Constantes.REACTIVAR_RESPUESTA_POSIBLE);
		final RespuestaPosibleEntity datosEntradaReactivarRespuestaPosibleNoExiste = generarJSON
				.generarRespuestaPosible(Constantes.URL_JSON_RESPUESTA_POSIBLE_DATA,
						Constantes.RESPUESTA_POSIBLE_NO_EXISTE);

		datosPruebaAcciones
				.add(getTestReactivarRespuestaPosibleCorrecto(datosEntradaReactivarRespuestaPosibleCorrecto));
		datosPruebaAcciones
				.add(getTestReactivarRespuestaPosibleNoExiste(datosEntradaReactivarRespuestaPosibleNoExiste));

		return datosPruebaAcciones;
	}

	private DatosPruebaAcciones getTestBuscarRespuestaPosible(
			final RespuestaPosibleEntity datosEntradaBuscarRespuestaPosible) {

		final String resultadoObtenido = buscarRespuestaPosible(datosEntradaBuscarRespuestaPosible);

		final String resultadoEsperado = CodigosMensajes.BUSCAR_FUNCIONALIDAD_CORRECTO + " - "
				+ Mensajes.BUSCAR_FUNCIONALIDAD_CORRECTO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.BUSCAR_CORRECTO, Constantes.EXITO,
				getValorRespuestaPosible(datosEntradaBuscarRespuestaPosible));

	}

	private DatosPruebaAcciones getTestGuardarRespuestaPosibleCorrecto(
			final RespuestaPosibleEntity datosEntradaAccionGuardarRespuestaPosible) {

		final String resultadoObtenido = guardarRespuestaPosible(datosEntradaAccionGuardarRespuestaPosible);

		final String resultadoEsperado = CodigosMensajes.GUARDAR_RESPUESTA_POSIBLE_CORRECTO + " - "
				+ Mensajes.RESPUESTA_POSIBLE_GUARDADA_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.GUARDAR_RESPUESTA_POSIBLE_CORRECTO, Constantes.EXITO,
				getValorRespuestaPosible(datosEntradaAccionGuardarRespuestaPosible));

	}

	private DatosPruebaAcciones getTestGuardarRespuestaPosibleYaExiste(
			final RespuestaPosibleEntity datosEntradaAccionGuardarRespuestaPosibleYaExiste) {

		final String resultadoObtenido = guardarRespuestaPosible(datosEntradaAccionGuardarRespuestaPosibleYaExiste);

		final String resultadoEsperado = CodigosMensajes.RESPUESTA_POSIBLE_YA_EXISTE + " - "
				+ Mensajes.RESPUESTA_POSIBLE_YA_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.FUNCIONALIDAD_YA_EXISTE, Constantes.ERROR,
				getValorRespuestaPosible(datosEntradaAccionGuardarRespuestaPosibleYaExiste));

	}

	private DatosPruebaAcciones getTestGuardarTextoRespuestaPosibleVacio(
			final RespuestaPosibleEntity datosEntradaAccionGuardarTextoRespuestaPosibleVacio) {

		final String resultadoObtenido = guardarRespuestaPosible(datosEntradaAccionGuardarTextoRespuestaPosibleVacio);

		final String resultadoEsperado = CodigosMensajes.TEXTO_RESPUESTA_VACIO + " - "
				+ Mensajes.TEXTO_RESPUESTA_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorRespuestaPosible(datosEntradaAccionGuardarTextoRespuestaPosibleVacio));

	}

	private DatosPruebaAcciones getTestModificarRespuestaPosibleCorrecto(
			final RespuestaPosibleEntity datosEntradaAccionModificarRespuestaPosibleCorrecto) {

		final String resultadoObtenido = modificarRespuestaPosible(datosEntradaAccionModificarRespuestaPosibleCorrecto);

		final String resultadoEsperado = CodigosMensajes.MODIFICAR_RESPUESTA_POSIBLE_CORRECTO + " - "
				+ Mensajes.RESPUESTA_POSIBLE_MODIFICADA_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.MODIFICAR_RESPUESTA_POSIBLE_CORRECTO, Constantes.EXITO,
				getValorRespuestaPosible(datosEntradaAccionModificarRespuestaPosibleCorrecto));

	}

	private DatosPruebaAcciones getTestModificarRespuestaPosibleNoExiste(
			final RespuestaPosibleEntity datosEntradaAccionModificarRespuestaPosibleNoExiste) {

		final String resultadoObtenido = modificarRespuestaPosibleNoExiste(
				datosEntradaAccionModificarRespuestaPosibleNoExiste);

		final String resultadoEsperado = CodigosMensajes.RESPUESTA_POSIBLE_NO_EXISTE + " - "
				+ Mensajes.RESPUESTA_POSIBLE_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.FUNCIONALIDAD_NO_EXISTE, Constantes.ERROR,
				getValorRespuestaPosible(datosEntradaAccionModificarRespuestaPosibleNoExiste));

	}

	private DatosPruebaAcciones getTestModificarTextoRespuestaPosibleVacio(
			final RespuestaPosibleEntity datosEntradaAccionModificarTextoRespuestaPosibleVacio) {

		final String resultadoObtenido = modificarRespuestaPosible(
				datosEntradaAccionModificarTextoRespuestaPosibleVacio);

		final String resultadoEsperado = CodigosMensajes.TEXTO_RESPUESTA_VACIO + " - "
				+ Mensajes.TEXTO_RESPUESTA_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorRespuestaPosible(datosEntradaAccionModificarTextoRespuestaPosibleVacio));

	}

	private DatosPruebaAcciones getTestEliminarRespuestaPosibleCorrecto(
			final RespuestaPosibleEntity datosEntradaAccionEliminarRespuestaPosibleCorrecto) {

		final String resultadoObtenido = eliminarRespuestaPosible(datosEntradaAccionEliminarRespuestaPosibleCorrecto);

		final String resultadoEsperado = CodigosMensajes.ELIMINAR_RESPUESTA_POSIBLE_CORRECTO + " - "
				+ Mensajes.RESPUESTA_POSIBLE_ELIMINADA_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ELIMINAR_RESPUESTA_POSIBLE_CORRECTO, Constantes.EXITO,
				getValorRespuestaPosible(datosEntradaAccionEliminarRespuestaPosibleCorrecto));

	}

	private DatosPruebaAcciones getTestEliminarRespuestaPosibleAsociadaProceso(
			final RespuestaPosibleEntity datosEntradaAccionEliminarRespuestaPosibleAsociadaProceso) {

		final String resultadoObtenido = eliminarRespuestaPosibleAsociadaProceso(
				datosEntradaAccionEliminarRespuestaPosibleAsociadaProceso);

		final String resultadoEsperado = CodigosMensajes.ELIMINAR_RESPUESTA_POSIBLE_ASOCIADA_PROCESO + " - "
				+ Mensajes.RESPUESTA_POSIBLE_ASOCIADA_PROCESO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ELIMINAR_FUNCIONALIDAD_ASOCIADA_ROL_ACCION, Constantes.ERROR,
				getValorRespuestaPosible(datosEntradaAccionEliminarRespuestaPosibleAsociadaProceso));

	}

	private DatosPruebaAcciones getTestEliminarRespuestaPosibleNoExiste(
			final RespuestaPosibleEntity datosEntradaAccionEliminarRespuestaPosibleNoExiste) {

		final String resultadoObtenido = eliminarRespuestaPosibleNoExiste(
				datosEntradaAccionEliminarRespuestaPosibleNoExiste);

		final String resultadoEsperado = CodigosMensajes.RESPUESTA_POSIBLE_NO_EXISTE + " - "
				+ Mensajes.RESPUESTA_POSIBLE_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.RESPUESTA_POSIBLE_NO_EXISTE, Constantes.ERROR,
				getValorRespuestaPosible(datosEntradaAccionEliminarRespuestaPosibleNoExiste));

	}

	private DatosPruebaAcciones getTestReactivarRespuestaPosibleCorrecto(
			final RespuestaPosibleEntity datosEntradaAccionReactivarRespuestaPosibleCorrecto) {

		final String resultadoObtenido = reactivarRespuestaPosible(datosEntradaAccionReactivarRespuestaPosibleCorrecto);

		final String resultadoEsperado = CodigosMensajes.REACTIVAR_RESPUESTA_POSIBLE_CORRECTO + " - "
				+ Mensajes.RESPUESTA_POSIBLE_REACTIVADA_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.REACTIVAR_RESPUESTA_POSIBLE_CORRECTO, Constantes.EXITO,
				getValorRespuestaPosible(datosEntradaAccionReactivarRespuestaPosibleCorrecto));

	}

	private DatosPruebaAcciones getTestReactivarRespuestaPosibleNoExiste(
			final RespuestaPosibleEntity datosEntradaAccionReactivarRespuestaPosibleNoExiste) {

		final String resultadoObtenido = reactivarRespuestaPosibleNoExiste(
				datosEntradaAccionReactivarRespuestaPosibleNoExiste);

		final String resultadoEsperado = CodigosMensajes.RESPUESTA_POSIBLE_NO_EXISTE + " - "
				+ Mensajes.RESPUESTA_POSIBLE_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.RESPUESTA_POSIBLE_NO_EXISTE, Constantes.ERROR,
				getValorRespuestaPosible(datosEntradaAccionReactivarRespuestaPosibleNoExiste));

	}

	private String buscarRespuestaPosible(final RespuestaPosibleEntity respuestaPosible) {

		String resultado = StringUtils.EMPTY;

		respuestaPosibleRepository.findRespuestaPosible(respuestaPosible.getTextoRespuesta());

		resultado = CodigosMensajes.BUSCAR_RESPUESTA_POSIBLE_CORRECTO + " - "
				+ Mensajes.RESPUESTA_POSIBLE_BUSCADA_CORRECTAMENTE;

		return resultado;
	}

	private String guardarRespuestaPosible(final RespuestaPosibleEntity respuestaPosible) {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarTextoRespuestaPosibleBlank(respuestaPosible.getTextoRespuesta())) {
			resultado = CodigosMensajes.TEXTO_RESPUESTA_VACIO + " - " + Mensajes.TEXTO_RESPUESTA_NO_PUEDE_SER_VACIO;
		} else {
			final RespuestaPosibleEntity respuestaPosibleBD = respuestaPosibleRepository
					.findRespuestaPosibleByText(respuestaPosible.getTextoRespuesta());

			if (respuestaPosibleBD != null) {
				resultado = CodigosMensajes.RESPUESTA_POSIBLE_YA_EXISTE + " - " + Mensajes.RESPUESTA_POSIBLE_YA_EXISTE;

			} else {
				respuestaPosibleRepository.saveAndFlush(respuestaPosible);
				resultado = CodigosMensajes.GUARDAR_RESPUESTA_POSIBLE_CORRECTO + " - "
						+ Mensajes.RESPUESTA_POSIBLE_GUARDADA_CORRECTAMENTE;

				final RespuestaPosibleEntity nuevaRespuestaPosible = respuestaPosibleRepository
						.findRespuestaPosibleByText(respuestaPosible.getTextoRespuesta());

				respuestaPosibleRepository.deleteRespuestaPosible(nuevaRespuestaPosible.getIdRespuesta());
			}
		}

		return resultado;
	}

	private String modificarRespuestaPosible(final RespuestaPosibleEntity respuestaPosible) {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarTextoRespuestaPosibleBlank(respuestaPosible.getTextoRespuesta())) {
			resultado = CodigosMensajes.TEXTO_RESPUESTA_VACIO + " - " + Mensajes.TEXTO_RESPUESTA_NO_PUEDE_SER_VACIO;
		} else {
			respuestaPosibleRepository.saveAndFlush(respuestaPosible);

			final RespuestaPosibleEntity respuestaPosibleBuscar = respuestaPosibleRepository
					.findRespuestaPosibleByText(respuestaPosible.getTextoRespuesta());
			respuestaPosibleBuscar.setTextoRespuesta("Texto respuesta modificado");
			respuestaPosibleRepository.saveAndFlush(respuestaPosible);
			resultado = CodigosMensajes.MODIFICAR_RESPUESTA_POSIBLE_CORRECTO + " - "
					+ Mensajes.RESPUESTA_POSIBLE_MODIFICADA_CORRECTAMENTE;
			respuestaPosibleRepository.deleteRespuestaPosible(respuestaPosible.getIdRespuesta());

		}

		return resultado;
	}

	private String modificarRespuestaPosibleNoExiste(final RespuestaPosibleEntity respuestaPosible) {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarTextoRespuestaPosibleBlank(respuestaPosible.getTextoRespuesta())) {
			resultado = CodigosMensajes.TEXTO_RESPUESTA_VACIO + " - " + Mensajes.TEXTO_RESPUESTA_NO_PUEDE_SER_VACIO;
		} else {
			final RespuestaPosibleEntity respuestaPosibleBuscar = respuestaPosibleRepository
					.findRespuestaPosibleByText(respuestaPosible.getTextoRespuesta());

			if (respuestaPosibleBuscar == null) {
				resultado = CodigosMensajes.RESPUESTA_POSIBLE_NO_EXISTE + " - " + Mensajes.RESPUESTA_POSIBLE_NO_EXISTE;
			}

		}

		return resultado;
	}

	private String eliminarRespuestaPosible(final RespuestaPosibleEntity respuestaPosible) {
		String resultado = StringUtils.EMPTY;

		respuestaPosibleRepository.saveAndFlush(respuestaPosible);

		final RespuestaPosibleEntity respuestaPosibleBuscar = respuestaPosibleRepository
				.findRespuestaPosibleByText(respuestaPosible.getTextoRespuesta());
		respuestaPosibleBuscar.setBorradoRespuesta(1);
		respuestaPosibleRepository.saveAndFlush(respuestaPosible);
		resultado = CodigosMensajes.ELIMINAR_RESPUESTA_POSIBLE_CORRECTO + " - "
				+ Mensajes.RESPUESTA_POSIBLE_ELIMINADA_CORRECTAMENTE;
		respuestaPosibleRepository.deleteRespuestaPosible(respuestaPosible.getIdRespuesta());

		return resultado;
	}

	private String eliminarRespuestaPosibleAsociadaProceso(final RespuestaPosibleEntity respuestaPosible) {
		String resultado = StringUtils.EMPTY;

		respuestaPosibleRepository.saveAndFlush(respuestaPosible);

		final RespuestaPosibleEntity respuestaPosibleBuscar = respuestaPosibleRepository
				.findRespuestaPosibleByText(respuestaPosible.getTextoRespuesta());

		// TODO Revisar porque ahora tiene la fecha el objeto
		// ProcesoRespuestaPosibleEntity
		final ProcesoRespuestaPosibleEntity procesoRespuestaPosible = new ProcesoRespuestaPosibleEntity(1,
				respuestaPosibleBuscar.getIdRespuesta(), null);
		procesoRespuestaPosibleRepository.saveAndFlush(procesoRespuestaPosible);

		if (respuestaPosibleBuscar != null) {
			final List<ProcesoRespuestaPosibleEntity> procesoRespuestaPosibleBD = procesoRespuestaPosibleRepository
					.findAll();

			for (int i = 0; i < procesoRespuestaPosibleBD.size(); i++) {
				if (procesoRespuestaPosibleBD.get(i).getIdRespuesta().equals(respuestaPosibleBuscar.getIdRespuesta())) {
					resultado = CodigosMensajes.ELIMINAR_RESPUESTA_POSIBLE_ASOCIADA_PROCESO + " - "
							+ Mensajes.RESPUESTA_POSIBLE_ASOCIADA_PROCESO;
				}
			}
		}

		procesoRespuestaPosibleRepository.deleteProcesoRespuestaPosible(respuestaPosible.getIdRespuesta(), 1);
		respuestaPosibleRepository.deleteRespuestaPosible(respuestaPosibleBuscar.getIdRespuesta());
		return resultado;
	}

	private String eliminarRespuestaPosibleNoExiste(final RespuestaPosibleEntity respuestaPosible) {
		String resultado = StringUtils.EMPTY;

		final RespuestaPosibleEntity respuestaPosibleBuscar = respuestaPosibleRepository
				.findRespuestaPosibleByText(respuestaPosible.getTextoRespuesta());

		if (respuestaPosibleBuscar == null) {
			resultado = CodigosMensajes.RESPUESTA_POSIBLE_NO_EXISTE + " - " + Mensajes.RESPUESTA_POSIBLE_NO_EXISTE;
		}
		return resultado;
	}

	private String reactivarRespuestaPosible(final RespuestaPosibleEntity respuestaPosible) {
		String resultado = StringUtils.EMPTY;

		respuestaPosibleRepository.saveAndFlush(respuestaPosible);

		final RespuestaPosibleEntity respuestaPosibleBuscar = respuestaPosibleRepository
				.findRespuestaPosibleByText(respuestaPosible.getTextoRespuesta());
		respuestaPosibleBuscar.setBorradoRespuesta(1);
		respuestaPosibleRepository.saveAndFlush(respuestaPosible);
		resultado = CodigosMensajes.REACTIVAR_FUNCIONALIDAD_CORRECTO + " - "
				+ Mensajes.FUNCIONALIDAD_REACTIVADA_CORRECTAMENTE;
		respuestaPosibleRepository.deleteRespuestaPosible(respuestaPosible.getIdRespuesta());

		return resultado;
	}

	private String reactivarRespuestaPosibleNoExiste(final RespuestaPosibleEntity respuestaPosible) {
		String resultado = StringUtils.EMPTY;

		final RespuestaPosibleEntity respuestaPosibleBuscar = respuestaPosibleRepository
				.findRespuestaPosibleByText(respuestaPosible.getTextoRespuesta());

		if (respuestaPosibleBuscar == null) {
			resultado = CodigosMensajes.RESPUESTA_POSIBLE_NO_EXISTE + " - " + Mensajes.RESPUESTA_POSIBLE_NO_EXISTE;
		}
		return resultado;
	}

	private Map<String, String> getValorRespuestaPosible(final RespuestaPosibleEntity respuestaPosible) {

		final Map<String, String> valor = new HashMap<>();

		valor.put(Constantes.TEXTO_RESPUESTA_POSIBLE, respuestaPosible.getTextoRespuesta());

		return valor;
	}

}
