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

import com.sds.model.NoticiasEntity;
import com.sds.repository.NoticiasRepository;
import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.DefinicionPruebas;
import com.sds.service.common.Mensajes;
import com.sds.service.test.TestNoticiaService;
import com.sds.service.test.impl.atributos.TestAtributoTextoNoticia;
import com.sds.service.test.impl.atributos.TestAtributoTituloNoticia;
import com.sds.service.test.model.DatosPruebaAcciones;
import com.sds.service.test.model.DatosPruebaAtributos;
import com.sds.service.test.util.CrearDatosPruebaAcciones;
import com.sds.service.test.util.GenerarJSON;
import com.sds.service.util.validaciones.Validaciones;

@Service
public class TestNoticiaServiceImpl implements TestNoticiaService {

	@Autowired
	NoticiasRepository noticiasRepository;

	private final TestAtributoTituloNoticia testAtributoTituloNoticia;
	private final TestAtributoTextoNoticia testAtributoTextoNoticia;
	private final CrearDatosPruebaAcciones crearDatosPruebaAcciones;
	private final GenerarJSON generarJSON;
	private final Validaciones validaciones;

	public TestNoticiaServiceImpl() {
		testAtributoTituloNoticia = new TestAtributoTituloNoticia();
		testAtributoTextoNoticia = new TestAtributoTextoNoticia();
		crearDatosPruebaAcciones = new CrearDatosPruebaAcciones();
		generarJSON = new GenerarJSON();
		validaciones = new Validaciones();
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoTituloNoticia()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final NoticiasEntity datosEntradaTituloNoticiaVacio = generarJSON
				.generarNoticia(Constantes.URL_JSON_NOTICIA_ATRIBUTOS_TITULONOTICIA, Constantes.TITULO_NOTICIA_VACIO);
		final NoticiasEntity datosEntradaTituloNoticiaCaracteresEspeciales = generarJSON.generarNoticia(
				Constantes.URL_JSON_NOTICIA_ATRIBUTOS_TITULONOTICIA, Constantes.TITULO_NOTICIA_CARACTERESESPECIALES);
		final NoticiasEntity datosEntradaTituloNoticiaMenor3 = generarJSON
				.generarNoticia(Constantes.URL_JSON_NOTICIA_ATRIBUTOS_TITULONOTICIA, Constantes.TITULO_NOTICIA_MENOR_3);
		final NoticiasEntity datosEntradaTituloNoticiaMayor256 = generarJSON.generarNoticia(
				Constantes.URL_JSON_NOTICIA_ATRIBUTOS_TITULONOTICIA, Constantes.TITULO_NOTICIA_MAYOR_256);
		final NoticiasEntity datosEntradaTituloNoticiaAlfabetico = generarJSON.generarNoticia(
				Constantes.URL_JSON_NOTICIA_ATRIBUTOS_TITULONOTICIA, Constantes.TITULO_NOTICIA_CORRECTO);

		datosPruebaAtributos.add(testAtributoTituloNoticia.getTestTituloNoticiaVacio(datosEntradaTituloNoticiaVacio));
		datosPruebaAtributos.add(testAtributoTituloNoticia
				.getTestTituloNoticiaAlfabeticoCaracteresEspeciales(datosEntradaTituloNoticiaCaracteresEspeciales));
		datosPruebaAtributos
				.add(testAtributoTituloNoticia.getTestTituloNoticiaAlfabeticoMenor3(datosEntradaTituloNoticiaMenor3));
		datosPruebaAtributos.add(
				testAtributoTituloNoticia.getTestTituloNoticiaAlfabeticoMayor256(datosEntradaTituloNoticiaMayor256));
		datosPruebaAtributos.add(
				testAtributoTituloNoticia.getTestTituloNoticiaCorrectoAlfabetico(datosEntradaTituloNoticiaAlfabetico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoTituloNoticiaBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final NoticiasEntity datosEntradaTituloNoticiaCaracteresEspeciales = generarJSON.generarNoticia(
				Constantes.URL_JSON_NOTICIA_ATRIBUTOS_TITULONOTICIA, Constantes.TITULO_NOTICIA_CARACTERESESPECIALES);
		final NoticiasEntity datosEntradaTituloNoticiaMayor256 = generarJSON.generarNoticia(
				Constantes.URL_JSON_NOTICIA_ATRIBUTOS_TITULONOTICIA, Constantes.TITULO_NOTICIA_MAYOR_256);
		final NoticiasEntity datosEntradaTituloNoticiaAlfabetico = generarJSON.generarNoticia(
				Constantes.URL_JSON_NOTICIA_ATRIBUTOS_TITULONOTICIA, Constantes.TITULO_NOTICIA_CORRECTO);

		datosPruebaAtributos.add(testAtributoTituloNoticia
				.getTestTituloNoticiaAlfabeticoCaracteresEspeciales(datosEntradaTituloNoticiaCaracteresEspeciales));
		datosPruebaAtributos.add(
				testAtributoTituloNoticia.getTestTituloNoticiaAlfabeticoMayor256(datosEntradaTituloNoticiaMayor256));

		datosPruebaAtributos.add(
				testAtributoTituloNoticia.getTestTituloNoticiaCorrectoAlfabetico(datosEntradaTituloNoticiaAlfabetico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoTextoNoticia()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final NoticiasEntity datosEntradaTextoNoticiaVacio = generarJSON
				.generarNoticia(Constantes.URL_JSON_NOTICIA_ATRIBUTOS_TEXTONOTICIA, Constantes.TEXTO_NOTICIA_VACIO);
		final NoticiasEntity datosEntradaTextoNoticiaCaracteresEspeciales = generarJSON.generarNoticia(
				Constantes.URL_JSON_NOTICIA_ATRIBUTOS_TEXTONOTICIA, Constantes.TEXTO_NOTICIA_CARACTERESESPECIALES);
		final NoticiasEntity datosEntradaTextoNoticiaMenor3 = generarJSON
				.generarNoticia(Constantes.URL_JSON_NOTICIA_ATRIBUTOS_TEXTONOTICIA, Constantes.TEXTO_NOTICIA_MENOR_3);
		final NoticiasEntity datosEntradaTextoNoticiaAlfabetico = generarJSON
				.generarNoticia(Constantes.URL_JSON_NOTICIA_ATRIBUTOS_TEXTONOTICIA, Constantes.TEXTO_NOTICIA_CORRECTO);

		datosPruebaAtributos.add(testAtributoTextoNoticia.getTestTextoNoticiaVacio(datosEntradaTextoNoticiaVacio));
		datosPruebaAtributos.add(testAtributoTextoNoticia
				.getTestTextoNoticiaAlfabeticoCaracteresEspeciales(datosEntradaTextoNoticiaCaracteresEspeciales));
		datosPruebaAtributos
				.add(testAtributoTextoNoticia.getTestTextoNoticiaAlfabeticoMenor3(datosEntradaTextoNoticiaMenor3));
		datosPruebaAtributos.add(testAtributoTextoNoticia
				.getTestTextoNoticiaAlfanumericoSignosPuntuacionCorrecto(datosEntradaTextoNoticiaAlfabetico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAtributos> getPruebasAtributoTextoNoticiaBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAtributos> datosPruebaAtributos = new ArrayList<>();

		final NoticiasEntity datosEntradaTextoNoticiaCaracteresEspeciales = generarJSON.generarNoticia(
				Constantes.URL_JSON_NOTICIA_ATRIBUTOS_TEXTONOTICIA, Constantes.TEXTO_NOTICIA_CARACTERESESPECIALES);
		final NoticiasEntity datosEntradaTextoNoticiaAlfabetico = generarJSON
				.generarNoticia(Constantes.URL_JSON_NOTICIA_ATRIBUTOS_TEXTONOTICIA, Constantes.TEXTO_NOTICIA_CORRECTO);

		datosPruebaAtributos.add(testAtributoTextoNoticia
				.getTestTextoNoticiaAlfabeticoCaracteresEspeciales(datosEntradaTextoNoticiaCaracteresEspeciales));
		datosPruebaAtributos.add(testAtributoTextoNoticia
				.getTestTextoNoticiaAlfanumericoSignosPuntuacionCorrecto(datosEntradaTextoNoticiaAlfabetico));

		return datosPruebaAtributos;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasNoticiasAccionBuscar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final NoticiasEntity datosEntradaBuscarNoticiaCorrecto = generarJSON
				.generarNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.BUSCAR_NOTICIA);
		final NoticiasEntity datosEntradaBuscarTituloVacio = generarJSON
				.generarNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.TITULO_NOTICIA_VACIO);
		final NoticiasEntity datosEntradaBuscarTextoVacio = generarJSON.generarNoticia(Constantes.URL_JSON_NOTICIA_DATA,
				Constantes.TEXTO_NOTICIA_VACIO);
		final NoticiasEntity datosEntradaBuscarFechaVacia = generarJSON.generarNoticia(Constantes.URL_JSON_NOTICIA_DATA,
				Constantes.FECHA_NOTICIA_VACIA);
		final NoticiasEntity datosEntradaBuscarTituloTextoFechaVacia = generarJSON
				.generarNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.DATOS_NOTICIA_VACIOS);

		datosPruebaAcciones.add(getTestBuscarNoticia(datosEntradaBuscarNoticiaCorrecto));
		datosPruebaAcciones.add(getTestBuscarNoticia(datosEntradaBuscarTituloVacio));
		datosPruebaAcciones.add(getTestBuscarNoticia(datosEntradaBuscarTextoVacio));
		datosPruebaAcciones.add(getTestBuscarNoticia(datosEntradaBuscarFechaVacia));
		datosPruebaAcciones.add(getTestBuscarNoticia(datosEntradaBuscarTituloTextoFechaVacia));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasNoticiasAccionAñadir()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final NoticiasEntity datosEntradaGuardarNoticiaCorrecto = generarJSON
				.generarNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.GUARDAR_NOTICIA);
		final NoticiasEntity datosEntradaGuardarTituloVacio = generarJSON
				.generarNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.TITULO_NOTICIA_VACIO);
		final NoticiasEntity datosEntradaGuardarTextoVacio = generarJSON
				.generarNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.TEXTO_NOTICIA_VACIO);
		final NoticiasEntity datosEntradaGuardarTituloTextoFechaVacia = generarJSON
				.generarNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.DATOS_NOTICIA_VACIOS);
		final NoticiasEntity datosEntradaGuardarNoticiaYaExiste = generarJSON
				.generarNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.NOTICIA_YA_EXISTE);

		datosPruebaAcciones.add(getTestGuardarNoticiaCorrecto(datosEntradaGuardarNoticiaCorrecto));
		datosPruebaAcciones.add(getTestGuardarNoticiaTituloVacio(datosEntradaGuardarTituloVacio));
		datosPruebaAcciones.add(getTestGuardarTextoNoticiaVacio(datosEntradaGuardarTextoVacio));
		datosPruebaAcciones.add(getTestGuardarNoticiaTituloTextoVacio(datosEntradaGuardarTituloTextoFechaVacia));
		datosPruebaAcciones.add(getTestGuardarNoticiaYaExiste(datosEntradaGuardarNoticiaYaExiste));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasNoticiasAccionModificar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final NoticiasEntity datosEntradaModificarNoticiaCorrecto = generarJSON
				.generarNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.MODIFICAR_NOTICIA);
		final NoticiasEntity datosEntradaModificarTituloVacio = generarJSON
				.generarNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.TITULO_NOTICIA_VACIO);
		final NoticiasEntity datosEntradaModificarTextoVacio = generarJSON
				.generarNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.TEXTO_NOTICIA_VACIO);
		final NoticiasEntity datosEntradaModificarTituloTextoFechaVacia = generarJSON
				.generarNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.DATOS_NOTICIA_VACIOS);
		final NoticiasEntity datosEntradaModificarNoticiaNoExiste = generarJSON
				.generarNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.NOTICIA_NO_EXISTE);

		datosPruebaAcciones.add(getTestModificarNoticiaCorrecto(datosEntradaModificarNoticiaCorrecto));
		datosPruebaAcciones.add(getTestModificarNoticiaTituloVacio(datosEntradaModificarTituloVacio));
		datosPruebaAcciones.add(getTestModificarTextoNoticiaVacio(datosEntradaModificarTextoVacio));
		datosPruebaAcciones.add(getTestModificarNoticiaTituloTextoVacio(datosEntradaModificarTituloTextoFechaVacia));
		datosPruebaAcciones.add(getTestModificarNoticiaNoExiste(datosEntradaModificarNoticiaNoExiste));

		return datosPruebaAcciones;
	}

	@Override
	public List<DatosPruebaAcciones> getPruebasNoticiasAccionEliminar()
			throws IOException, ParseException, java.text.ParseException {
		final List<DatosPruebaAcciones> datosPruebaAcciones = new ArrayList<>();

		final NoticiasEntity datosEntradaEliminarNoticiaCorrecto = generarJSON
				.generarNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.ELIMINAR_NOTICIA);
		final NoticiasEntity datosEntradaEliminarNoticiaNoExiste = generarJSON
				.generarNoticia(Constantes.URL_JSON_NOTICIA_DATA, Constantes.NOTICIA_NO_EXISTE);

		datosPruebaAcciones.add(getTestEliminarNoticiaCorrecto(datosEntradaEliminarNoticiaCorrecto));
		datosPruebaAcciones.add(getTestEliminarNoticiaNoExiste(datosEntradaEliminarNoticiaNoExiste));

		return datosPruebaAcciones;
	}

	private DatosPruebaAcciones getTestBuscarNoticia(final NoticiasEntity datosEntradaAccionBuscarNoticia) {

		final String resultadoObtenido = buscarNoticia(datosEntradaAccionBuscarNoticia);

		final String resultadoEsperado = CodigosMensajes.BUSCAR_NOTICIA_CORRECTO + " - "
				+ Mensajes.NOTICIA_BUSCADA_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.BUSCAR_CORRECTO, Constantes.EXITO, getValorNoticia(datosEntradaAccionBuscarNoticia));

	}

	private DatosPruebaAcciones getTestGuardarNoticiaCorrecto(final NoticiasEntity datosEntradaAccionGuardarNoticia) {

		final String resultadoObtenido = guardarNoticia(datosEntradaAccionGuardarNoticia);

		final String resultadoEsperado = CodigosMensajes.GUARDAR_NOTICIA_CORRECTO + " - "
				+ Mensajes.NOTICIA_GUARDADA_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.GUARDAR_NOTICIA_CORRECTO, Constantes.EXITO,
				getValorNoticia(datosEntradaAccionGuardarNoticia));

	}

	private DatosPruebaAcciones getTestGuardarNoticiaYaExiste(
			final NoticiasEntity datosEntradaAccionGuardarNoticiaYaExiste) {

		final String resultadoObtenido = guardarNoticia(datosEntradaAccionGuardarNoticiaYaExiste);

		final String resultadoEsperado = CodigosMensajes.NOTICIA_YA_EXISTE + " - " + Mensajes.NOTICIA_YA_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ACCION_YA_EXISTE, Constantes.ERROR,
				getValorNoticia(datosEntradaAccionGuardarNoticiaYaExiste));

	}

	private DatosPruebaAcciones getTestGuardarNoticiaTituloVacio(
			final NoticiasEntity datosEntradaAccionGuardarNoticiaTituloVacio) {

		final String resultadoObtenido = guardarNoticia(datosEntradaAccionGuardarNoticiaTituloVacio);

		final String resultadoEsperado = CodigosMensajes.TITULO_NOTICIA_VACIO + " - "
				+ Mensajes.TITULO_NOTICIA_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorNoticia(datosEntradaAccionGuardarNoticiaTituloVacio));

	}

	private DatosPruebaAcciones getTestGuardarTextoNoticiaVacio(
			final NoticiasEntity datosEntradaAccionGuardarNoticiaTextoVacio) {

		final String resultadoObtenido = guardarNoticia(datosEntradaAccionGuardarNoticiaTextoVacio);

		final String resultadoEsperado = CodigosMensajes.TEXTO_NOTICIA_VACIO + " - "
				+ Mensajes.TEXTO_NOTICIA_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, getValorNoticia(datosEntradaAccionGuardarNoticiaTextoVacio));

	}

	private DatosPruebaAcciones getTestGuardarNoticiaTituloTextoVacio(
			final NoticiasEntity datosEntradaAccionGuardarNoticiaTituloTextoVacio) {

		final String resultadoObtenido = guardarNoticia(datosEntradaAccionGuardarNoticiaTituloTextoVacio);

		final String resultadoEsperado = CodigosMensajes.NOTICIA_VACIA + " - " + Mensajes.NOTICIA_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorNoticia(datosEntradaAccionGuardarNoticiaTituloTextoVacio));

	}

	private DatosPruebaAcciones getTestModificarNoticiaCorrecto(
			final NoticiasEntity datosEntradaAccionModificarNoticia) {

		final String resultadoObtenido = modificarNoticia(datosEntradaAccionModificarNoticia);

		final String resultadoEsperado = CodigosMensajes.MODIFICAR_NOTICIA_CORRECTO + " - "
				+ Mensajes.NOTICIA_MODIFICADA_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.MODIFICAR_NOTICIA_CORRECTO, Constantes.EXITO,
				getValorNoticia(datosEntradaAccionModificarNoticia));

	}

	private DatosPruebaAcciones getTestModificarNoticiaNoExiste(
			final NoticiasEntity datosEntradaAccionModificarNoticiaNoExiste) {

		final String resultadoObtenido = modificarNoticiaNoExiste(datosEntradaAccionModificarNoticiaNoExiste);

		final String resultadoEsperado = CodigosMensajes.NOTICIA_NO_EXISTE + " - " + Mensajes.NOTICIA_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NOTICIA_NO_EXISTE, Constantes.ERROR,
				getValorNoticia(datosEntradaAccionModificarNoticiaNoExiste));

	}

	private DatosPruebaAcciones getTestModificarNoticiaTituloVacio(
			final NoticiasEntity datosEntradaAccionModificarNoticiaTituloVacio) {

		final String resultadoObtenido = modificarNoticia(datosEntradaAccionModificarNoticiaTituloVacio);

		final String resultadoEsperado = CodigosMensajes.TITULO_NOTICIA_VACIO + " - "
				+ Mensajes.TITULO_NOTICIA_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorNoticia(datosEntradaAccionModificarNoticiaTituloVacio));

	}

	private DatosPruebaAcciones getTestModificarTextoNoticiaVacio(
			final NoticiasEntity datosEntradaAccionModificarNoticiaTextoVacio) {

		final String resultadoObtenido = modificarNoticia(datosEntradaAccionModificarNoticiaTextoVacio);

		final String resultadoEsperado = CodigosMensajes.TEXTO_NOTICIA_VACIO + " - "
				+ Mensajes.TEXTO_NOTICIA_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorNoticia(datosEntradaAccionModificarNoticiaTextoVacio));

	}

	private DatosPruebaAcciones getTestModificarNoticiaTituloTextoVacio(
			final NoticiasEntity datosEntradaAccionModificarNoticiaTituloTextoVacio) {

		final String resultadoObtenido = modificarNoticia(datosEntradaAccionModificarNoticiaTituloTextoVacio);

		final String resultadoEsperado = CodigosMensajes.NOTICIA_VACIA + " - " + Mensajes.NOTICIA_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				getValorNoticia(datosEntradaAccionModificarNoticiaTituloTextoVacio));

	}

	private DatosPruebaAcciones getTestEliminarNoticiaCorrecto(final NoticiasEntity datosEntradaAccionEliminarNoticia) {

		final String resultadoObtenido = eliminarNoticia(datosEntradaAccionEliminarNoticia);

		final String resultadoEsperado = CodigosMensajes.ELIMINAR_NOTICIA_CORRECTO + " - "
				+ Mensajes.NOTICIA_ELIMINADA_CORRECTAMENTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ELIMINAR_NOTICIA_CORRECTO, Constantes.ERROR,
				getValorNoticia(datosEntradaAccionEliminarNoticia));

	}

	private DatosPruebaAcciones getTestEliminarNoticiaNoExiste(
			final NoticiasEntity datosEntradaAccionEliminarNoticiaNoExiste) {

		final String resultadoObtenido = eliminarNoticiaNoExiste(datosEntradaAccionEliminarNoticiaNoExiste);

		final String resultadoEsperado = CodigosMensajes.NOTICIA_NO_EXISTE + " - " + Mensajes.NOTICIA_NO_EXISTE;

		return crearDatosPruebaAcciones.createDatosPruebaAcciones(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NOTICIA_NO_EXISTE, Constantes.ERROR,
				getValorNoticia(datosEntradaAccionEliminarNoticiaNoExiste));

	}

	private String buscarNoticia(final NoticiasEntity noticia) {
		String resultado = StringUtils.EMPTY;

		String fecha = StringUtils.EMPTY;

		if (noticia.getFechaNoticia() != null) {
			java.sql.Date fechaSql;
			fechaSql = new java.sql.Date(noticia.getFechaNoticia().getTime());
			fecha = fechaSql.toString();
		} else {
			fecha = StringUtils.EMPTY;
		}

		noticiasRepository.findNoticia(noticia.getTituloNoticia(), noticia.getTextoNoticia(), fecha);

		resultado = CodigosMensajes.BUSCAR_NOTICIA_CORRECTO + " - " + Mensajes.NOTICIA_BUSCADA_CORRECTAMENTE;

		return resultado;
	}

	private String guardarNoticia(final NoticiasEntity noticia) {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarTituloNoticiaBlank(noticia.getTituloNoticia())
				&& !validaciones.comprobarTextoNoticiaBlank(noticia.getTextoNoticia())) {
			resultado = CodigosMensajes.NOTICIA_VACIA + " - " + Mensajes.NOTICIA_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarTituloNoticiaBlank(noticia.getTituloNoticia())) {
			resultado = CodigosMensajes.TITULO_NOTICIA_VACIO + " - " + Mensajes.TITULO_NOTICIA_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarTextoNoticiaBlank(noticia.getTextoNoticia())) {
			resultado = CodigosMensajes.TEXTO_NOTICIA_VACIO + " - " + Mensajes.TEXTO_NOTICIA_NO_PUEDE_SER_VACIO;
		} else {
			final NoticiasEntity noticiaBD = noticiasRepository.findByTituloNoticia(noticia.getTituloNoticia());

			if (noticiaBD != null) {
				resultado = CodigosMensajes.NOTICIA_YA_EXISTE + " - " + Mensajes.NOTICIA_YA_EXISTE;
			} else {
				final java.util.Date fechaActual = new Date();
				noticia.setFechaNoticia(fechaActual);
				noticiasRepository.saveAndFlush(noticia);
				resultado = CodigosMensajes.GUARDAR_NOTICIA_CORRECTO + " - " + Mensajes.NOTICIA_GUARDADA_CORRECTAMENTE;

				final NoticiasEntity noticiaBDNueva = noticiasRepository
						.findByTituloNoticia(noticia.getTituloNoticia());

				noticiasRepository.deleteNoticia(noticiaBDNueva.getIdNoticia());
			}
		}

		return resultado;
	}

	private String modificarNoticia(final NoticiasEntity noticia) {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarTituloNoticiaBlank(noticia.getTituloNoticia())
				&& !validaciones.comprobarTextoNoticiaBlank(noticia.getTextoNoticia())) {
			resultado = CodigosMensajes.NOTICIA_VACIA + " - " + Mensajes.NOTICIA_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarTituloNoticiaBlank(noticia.getTituloNoticia())) {
			resultado = CodigosMensajes.TITULO_NOTICIA_VACIO + " - " + Mensajes.TITULO_NOTICIA_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarTextoNoticiaBlank(noticia.getTextoNoticia())) {
			resultado = CodigosMensajes.TEXTO_NOTICIA_VACIO + " - " + Mensajes.TEXTO_NOTICIA_NO_PUEDE_SER_VACIO;
		} else {
			final java.util.Date fechaActual = new Date();
			noticia.setFechaNoticia(fechaActual);
			noticiasRepository.saveAndFlush(noticia);

			final NoticiasEntity noticiaBD = noticiasRepository.findByTituloNoticia(noticia.getTituloNoticia());
			noticiaBD.setTextoNoticia("Texto Modificado");
			final java.util.Date fechaModificacion = new Date();
			noticia.setFechaNoticia(fechaModificacion);
			noticiasRepository.saveAndFlush(noticiaBD);
			resultado = CodigosMensajes.MODIFICAR_NOTICIA_CORRECTO + " - " + Mensajes.NOTICIA_MODIFICADA_CORRECTAMENTE;

			noticiasRepository.deleteNoticia(noticiaBD.getIdNoticia());

		}

		return resultado;
	}

	private String modificarNoticiaNoExiste(final NoticiasEntity noticia) {
		String resultado = StringUtils.EMPTY;

		if (!validaciones.comprobarTituloNoticiaBlank(noticia.getTituloNoticia())
				&& !validaciones.comprobarTextoNoticiaBlank(noticia.getTextoNoticia())) {
			resultado = CodigosMensajes.NOTICIA_VACIA + " - " + Mensajes.NOTICIA_NO_PUEDE_SER_VACIA;
		} else if (!validaciones.comprobarTituloNoticiaBlank(noticia.getTituloNoticia())) {
			resultado = CodigosMensajes.TITULO_NOTICIA_VACIO + " - " + Mensajes.TITULO_NOTICIA_NO_PUEDE_SER_VACIO;
		} else if (!validaciones.comprobarTextoNoticiaBlank(noticia.getTextoNoticia())) {
			resultado = CodigosMensajes.TEXTO_NOTICIA_VACIO + " - " + Mensajes.TEXTO_NOTICIA_NO_PUEDE_SER_VACIO;
		} else {
			final java.util.Date fechaActual = new Date();
			noticia.setFechaNoticia(fechaActual);

			final NoticiasEntity noticiaBD = noticiasRepository.findByTituloNoticia(noticia.getTituloNoticia());

			if (noticiaBD == null) {
				resultado = CodigosMensajes.NOTICIA_NO_EXISTE + " - " + Mensajes.NOTICIA_NO_EXISTE;
			}

		}

		return resultado;
	}

	private String eliminarNoticia(final NoticiasEntity noticia) {
		final NoticiasEntity noticiaBD = noticiasRepository.findByTituloNoticia(noticia.getTituloNoticia());
		String resultado = StringUtils.EMPTY;

		if (noticiaBD == null) {

			final java.util.Date fechaActual = new Date();
			noticia.setFechaNoticia(fechaActual);
			noticiasRepository.saveAndFlush(noticia);

			final NoticiasEntity noticiaGuardada = noticiasRepository.findByTituloNoticia(noticia.getTituloNoticia());

			noticiasRepository.deleteNoticia(noticiaGuardada.getIdNoticia());

			resultado = CodigosMensajes.ELIMINAR_NOTICIA_CORRECTO + " - " + Mensajes.NOTICIA_ELIMINADA_CORRECTAMENTE;

		}

		return resultado;
	}

	private String eliminarNoticiaNoExiste(final NoticiasEntity noticia) {
		final NoticiasEntity noticiaBD = noticiasRepository.findByTituloNoticia(noticia.getTituloNoticia());
		String resultado = StringUtils.EMPTY;

		if (noticiaBD == null) {
			resultado = CodigosMensajes.NOTICIA_NO_EXISTE + " - " + Mensajes.NOTICIA_NO_EXISTE;

		}

		return resultado;
	}

	private Map<String, String> getValorNoticia(final NoticiasEntity noticia) {
		final Map<String, String> valor = new HashMap<>();

		valor.put(Constantes.TITULO_NOTICIA, noticia.getTituloNoticia());
		valor.put(Constantes.TEXTO_NOTICIA, noticia.getTextoNoticia());

		return valor;

	}

}
