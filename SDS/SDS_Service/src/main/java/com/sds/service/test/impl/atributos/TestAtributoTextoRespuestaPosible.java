package com.sds.service.test.impl.atributos;

import java.text.ParseException;

import com.sds.model.RespuestaPosibleEntity;
import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.DefinicionPruebas;
import com.sds.service.common.Mensajes;
import com.sds.service.common.enumerados.Atributo;
import com.sds.service.common.enumerados.Funcionalidad;
import com.sds.service.test.model.DatosPruebaAtributos;
import com.sds.service.test.util.CrearDatosPruebaAtributos;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosBlank;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCaracteresEspecialesSinSignosPuntuacion;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosTextoSignosPuntuacion;

public class TestAtributoTextoRespuestaPosible {
	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosCaracteresEspecialesSinSignosPuntuacion validacionesAtributosCaracteresEspecialesSinSignosPuntuacion;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosTextoSignosPuntuacion validacionesAtributosTextoSignosPuntuacion;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoTextoRespuestaPosible() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosCaracteresEspecialesSinSignosPuntuacion = new ValidacionesAtributosCaracteresEspecialesSinSignosPuntuacion();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosTextoSignosPuntuacion = new ValidacionesAtributosTextoSignosPuntuacion();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestTextoRespuestaPosibleVacio(
			final RespuestaPosibleEntity datosEntradaRespuestaPosibleVacio) throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaRespuestaPosibleVacio.getTextoRespuesta(), Funcionalidad.GESTION_RESPUESTA_POSIBLE,
				Atributo.TEXTO_RESPUESTA_POSIBLE);

		final String resultadoEsperado = CodigosMensajes.TEXTO_RESPUESTA_VACIO + " - "
				+ Mensajes.TEXTO_RESPUESTA_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaRespuestaPosibleVacio.getTextoRespuesta(),
				Constantes.TEXTO_RESPUESTA_POSIBLE);
	}

	public DatosPruebaAtributos getTestTextoRespuestaPosibleAlfabeticoCaracteresEspeciales(
			final RespuestaPosibleEntity datosEntradaTextoRespuestaPosibleCaracteresEspecialesSinSignosPuntuacion)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspecialesSinSignosPuntuacion
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaTextoRespuestaPosibleCaracteresEspecialesSinSignosPuntuacion.getTextoRespuesta(),
						Funcionalidad.GESTION_RESPUESTA_POSIBLE, Atributo.TEXTO_RESPUESTA_POSIBLE);

		final String resultadoEsperado = CodigosMensajes.TEXTO_RESPUESTA_ALFANUMERICO_SIGNOS_PUNTUACION_INCORRECTO
				+ " - " + Mensajes.TEXTO_RESPUESTA_SOLO_PUEDE_SER_ALFANUMERICO_CON_SIGNOS_PUNTUACION;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_SIGNOS_PUNTUACION, Constantes.ERROR,
				datosEntradaTextoRespuestaPosibleCaracteresEspecialesSinSignosPuntuacion.getTextoRespuesta(),
				Constantes.TEXTO_RESPUESTA_POSIBLE);
	}

	public DatosPruebaAtributos getTestTextoRespuestaPosibleAlfabeticoMenor2(
			final RespuestaPosibleEntity datosEntradaTextoRespuestaPosibleAlfabeticoMenor2) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaTextoRespuestaPosibleAlfabeticoMenor2.getTextoRespuesta(),
				Funcionalidad.GESTION_RESPUESTA_POSIBLE, Atributo.TEXTO_RESPUESTA_POSIBLE, 2);

		final String resultadoEsperado = CodigosMensajes.TEXTO_RESPUESTA_MENOR_QUE_2 + " - "
				+ Mensajes.TEXTO_RESPUESTA_NO_PUEDE_SER_MENOR_QUE_2;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_2, Constantes.ERROR,
				datosEntradaTextoRespuestaPosibleAlfabeticoMenor2.getTextoRespuesta(),
				Constantes.TEXTO_RESPUESTA_POSIBLE);
	}

	public DatosPruebaAtributos getTestTextoRespuestaPosibleAlfanumericoSignosPuntuacionCorrecto(
			final RespuestaPosibleEntity datosEntradaTextoRespuestaPosibleAlfanumericoSignosPuntuacionCorrecto) {

		final String resultadoObtenido = validacionesAtributosTextoSignosPuntuacion.comprobarTextoSignosPuntuacion(
				datosEntradaTextoRespuestaPosibleAlfanumericoSignosPuntuacionCorrecto.getTextoRespuesta());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_SIGNOS_PUNTUACION, Constantes.EXITO,
				datosEntradaTextoRespuestaPosibleAlfanumericoSignosPuntuacionCorrecto.getTextoRespuesta(),
				Constantes.TEXTO_NOTICIA);

	}
}
