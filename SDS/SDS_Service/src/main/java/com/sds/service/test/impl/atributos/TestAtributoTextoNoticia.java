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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCorrectoAlfanumerico;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoTextoNoticia {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoTextoNoticia() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosCaracteresEspeciales = new ValidacionesAtributosCaracteresEspeciales();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosCorrectoAlfanumerico = new ValidacionesAtributosCorrectoAlfanumerico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestTextoNoticiaVacio(final NoticiasEntity datosEntradaTextoNoticiaVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaTextoNoticiaVacio.getTextoNoticia(), Funcionalidad.GESTION_NOTICIAS,
				Atributo.TEXTO_NOTICIA);

		final String resultadoEsperado = CodigosMensajes.TEXTO_NOTICIA_VACIO + " - "
				+ Mensajes.TEXTO_NOTICIA_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaTextoNoticiaVacio.getTextoNoticia(),
				Constantes.TEXTO_NOTICIA);
	}

	public DatosPruebaAtributos getTestTextoNoticiaAlfabeticoCaracteresEspeciales(
			final NoticiasEntity datosEntradaTextoNoticiaCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(datosEntradaTextoNoticiaCaracteresEspeciales.getTextoNoticia(),
						Funcionalidad.GESTION_NOTICIAS, Atributo.TEXTO_NOTICIA);

		final String resultadoEsperado = CodigosMensajes.TEXTO_NOTICIA_ALFABETICO_INCORRECTO + " - "
				+ Mensajes.TEXTO_NOTICIA_SOLO_PUEDE_CONTENER_LETRAS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaTextoNoticiaCaracteresEspeciales.getTextoNoticia(), Constantes.TEXTO_NOTICIA);
	}

	public DatosPruebaAtributos getTestTextoNoticiaAlfabeticoMenor3(
			final NoticiasEntity datosEntradaTextoNoticiaAlfabeticoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaTextoNoticiaAlfabeticoMenor3.getTextoNoticia(), Funcionalidad.GESTION_NOTICIAS,
				Atributo.TEXTO_NOTICIA, 3);

		final String resultadoEsperado = CodigosMensajes.TEXTO_NOTICIA_MENOR_QUE_3 + " - "
				+ Mensajes.TEXTO_NOTICIA_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MENOR_3, Constantes.ERROR,
				datosEntradaTextoNoticiaAlfabeticoMenor3.getTextoNoticia(), Constantes.TEXTO_NOTICIA);
	}

	public DatosPruebaAtributos getTestTextoNoticiaAlfabeticoCorrecto(
			final NoticiasEntity datosEntradaTextoNoticiaAlfabeticoCorrecto) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(datosEntradaTextoNoticiaAlfabeticoCorrecto.getTextoNoticia());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CORRECTO, Constantes.EXITO,
				datosEntradaTextoNoticiaAlfabeticoCorrecto.getTextoNoticia(), Constantes.TEXTO_NOTICIA);

	}

}
