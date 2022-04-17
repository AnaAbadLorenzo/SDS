package com.sds.service.test.impl.atributos;

import java.text.ParseException;

import com.sds.model.NoticiasEntity;
import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.DefinicionPruebas;
import com.sds.service.common.Mensajes;
import com.sds.service.common.enumerados.Atributo;
import com.sds.service.common.enumerados.Funcionalidad;
import com.sds.service.test.model.DatosPruebaAtributos;
import com.sds.service.test.util.CrearDatosPruebaAtributos;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosBlank;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCaracteresEspeciales;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCorrectoAlfabetico;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMayor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoTituloNoticia {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfabetico validacionesAtributosCorrectoAlfabetico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoTituloNoticia() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosCaracteresEspeciales = new ValidacionesAtributosCaracteresEspeciales();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosMayor = new ValidacionesAtributosMayor();
		validacionesAtributosCorrectoAlfabetico = new ValidacionesAtributosCorrectoAlfabetico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestTituloNoticiaVacio(final NoticiasEntity datosEntradaTituloNoticiaVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaTituloNoticiaVacio.getTituloNoticia(), Funcionalidad.GESTION_NOTICIAS,
				Atributo.TITULO_NOTICIA);

		final String resultadoEsperado = CodigosMensajes.TITULO_NOTICIA_VACIO + " - "
				+ Mensajes.TITULO_NOTICIA_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaTituloNoticiaVacio.getTituloNoticia(),
				Constantes.TITULO_NOTICIA);
	}

	public DatosPruebaAtributos getTestTituloNoticiaAlfabeticoCaracteresEspeciales(
			final NoticiasEntity datosEntradaTituloNoticiaCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(datosEntradaTituloNoticiaCaracteresEspeciales.getTituloNoticia(),
						Funcionalidad.GESTION_NOTICIAS, Atributo.TITULO_NOTICIA);

		final String resultadoEsperado = CodigosMensajes.TITULO_NOTICIA_ALFABETICO_INCORRECTO + " - "
				+ Mensajes.TITULO_NOTICIA_SOLO_PUEDE_CONTENER_LETRAS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaTituloNoticiaCaracteresEspeciales.getTituloNoticia(), Constantes.TITULO_NOTICIA);
	}

	public DatosPruebaAtributos getTestTituloNoticiaAlfabeticoMenor3(
			final NoticiasEntity datosEntradaTituloNoticiaAlfabeticoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaTituloNoticiaAlfabeticoMenor3.getTituloNoticia(), Funcionalidad.GESTION_NOTICIAS,
				Atributo.TITULO_NOTICIA, 3);

		final String resultadoEsperado = CodigosMensajes.TITULO_NOTICIA_MENOR_QUE_3 + " - "
				+ Mensajes.TITULO_NOTICIA_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MENOR_3, Constantes.ERROR,
				datosEntradaTituloNoticiaAlfabeticoMenor3.getTituloNoticia(), Constantes.TITULO_NOTICIA);
	}

	public DatosPruebaAtributos getTestTituloNoticiaAlfabeticoMayor256(
			final NoticiasEntity datosEntradaTituloNoticiaAlfabeticoMayor256) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaTituloNoticiaAlfabeticoMayor256.getTituloNoticia(), Funcionalidad.GESTION_NOTICIAS,
				Atributo.TITULO_NOTICIA, 256);

		final String resultadoEsperado = CodigosMensajes.TITULO_NOTICIA_MAYOR_QUE_256 + " - "
				+ Mensajes.TITULO_NOTICIA_NO_PUEDE_SER_MAYOR_QUE_256;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MAYOR_256, Constantes.ERROR,
				datosEntradaTituloNoticiaAlfabeticoMayor256.getTituloNoticia(), Constantes.TITULO_NOTICIA);
	}

	public DatosPruebaAtributos getTestTituloNoticiaCorrectoAlfabetico(
			final NoticiasEntity datosEntradaTituloNoticiaCorrecto) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfabetico
				.comprobarAtributoCorrectoAlfabetico(datosEntradaTituloNoticiaCorrecto.getTituloNoticia());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CORRECTO, Constantes.EXITO,
				datosEntradaTituloNoticiaCorrecto.getTituloNoticia(), Constantes.TITULO_NOTICIA);

	}
}
