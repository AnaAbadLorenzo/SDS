package com.sds.service.test.impl.atributos;

import java.text.ParseException;

import com.sds.model.ObjetivoEntity;
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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoDescripcionObjetivo {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosCorrectoAlfabetico validacionesAtributosCorrectoAlfabetico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoDescripcionObjetivo() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosCaracteresEspeciales = new ValidacionesAtributosCaracteresEspeciales();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosCorrectoAlfabetico = new ValidacionesAtributosCorrectoAlfabetico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestDescripcionObjetivoVacio(
			final ObjetivoEntity datosEntradaDescripcionObjetivoVacio) throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaDescripcionObjetivoVacio.getDescripObjetivo(), Funcionalidad.GESTION_OBJETIVOS,
				Atributo.DESCRIPCION_OBJETIVO);

		final String resultadoEsperado = CodigosMensajes.OBJETIVO_DESCRIPTION_VACIO + " - "
				+ Mensajes.DESCRIPCION_OBJETIVO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaDescripcionObjetivoVacio.getDescripObjetivo(),
				Constantes.DESCRIPCION_OBJETIVO);
	}

	public DatosPruebaAtributos getTestDescripcionObjetivoAlfabeticoCaracteresEspeciales(
			final ObjetivoEntity datosEntradaDescripcionObjetivoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaDescripcionObjetivoCaracteresEspeciales.getDescripObjetivo(),
						Funcionalidad.GESTION_OBJETIVOS, Atributo.DESCRIPCION_OBJETIVO);

		final String resultadoEsperado = CodigosMensajes.OBJETIVO_DESCRIPTION_ALFABETICO_INCORRECTO + " - "
				+ Mensajes.DESCRIPCION_OBJETIVO_SOLO_PUEDE_CONTENER_LETRAS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaDescripcionObjetivoCaracteresEspeciales.getDescripObjetivo(),
				Constantes.DESCRIPCION_OBJETIVO);
	}

	public DatosPruebaAtributos getTestDescripcionObjetivoAlfabeticoMenor3(
			final ObjetivoEntity datosEntradaDescripcionObjetivoAlfabeticoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaDescripcionObjetivoAlfabeticoMenor3.getDescripObjetivo(), Funcionalidad.GESTION_OBJETIVOS,
				Atributo.DESCRIPCION_OBJETIVO, 3);

		final String resultadoEsperado = CodigosMensajes.OBJETIVO_DESCRIPTION_MENOR_QUE_3 + " - "
				+ Mensajes.DESCRIPCION_OBJETIVO_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MENOR_3, Constantes.ERROR,
				datosEntradaDescripcionObjetivoAlfabeticoMenor3.getDescripObjetivo(), Constantes.DESCRIPCION_OBJETIVO);
	}

	public DatosPruebaAtributos getTestDescripcionObjetivoAlfabeticoCorrecto(
			final ObjetivoEntity datosEntradaDescripcionObjetivo) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfabetico
				.comprobarAtributoCorrectoAlfabetico(datosEntradaDescripcionObjetivo.getDescripObjetivo());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CORRECTO, Constantes.EXITO,
				datosEntradaDescripcionObjetivo.getDescripObjetivo(), Constantes.DESCRIPCION_OBJETIVO);

	}
}
