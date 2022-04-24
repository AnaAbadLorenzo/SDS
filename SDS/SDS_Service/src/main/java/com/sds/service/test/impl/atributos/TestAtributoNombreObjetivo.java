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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMayor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoNombreObjetivo {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfabetico validacionesAtributosCorrectoAlfabetico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoNombreObjetivo() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosCaracteresEspeciales = new ValidacionesAtributosCaracteresEspeciales();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosMayor = new ValidacionesAtributosMayor();
		validacionesAtributosCorrectoAlfabetico = new ValidacionesAtributosCorrectoAlfabetico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestNombreObjetivoVacio(final ObjetivoEntity datosEntradaObjetivoNameVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaObjetivoNameVacio.getNombreObjetivo(), Funcionalidad.GESTION_OBJETIVOS,
				Atributo.NOMBRE_OBJETIVO);

		final String resultadoEsperado = CodigosMensajes.OBJETIVO_NAME_VACIO + " - "
				+ Mensajes.NOMBRE_OBJETIVO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaObjetivoNameVacio.getNombreObjetivo(),
				Constantes.NOMBRE_OBJETIVO);
	}

	public DatosPruebaAtributos getTestNombreObjetivoAlfabeticoCaracteresEspeciales(
			final ObjetivoEntity datosEntradaNombreObjetivoAlfabeticoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaNombreObjetivoAlfabeticoCaracteresEspeciales.getNombreObjetivo(),
						Funcionalidad.GESTION_OBJETIVOS, Atributo.NOMBRE_OBJETIVO);

		final String resultadoEsperado = CodigosMensajes.OBJETIVO_NAME_ALFABETICO_INCORRECTO + " - "
				+ Mensajes.NOMBRE_OBJETIVO_SOLO_PUEDE_CONTENER_LETRAS_Y_ESPACIOS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaNombreObjetivoAlfabeticoCaracteresEspeciales.getNombreObjetivo(),
				Constantes.NOMBRE_OBJETIVO);
	}

	public DatosPruebaAtributos getTestNombreObjetivoAlfabeticoMenor3(
			final ObjetivoEntity datosEntradaNombreObjetivoAlfabeticoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaNombreObjetivoAlfabeticoMenor3.getNombreObjetivo(), Funcionalidad.GESTION_OBJETIVOS,
				Atributo.NOMBRE_OBJETIVO, 3);

		final String resultadoEsperado = CodigosMensajes.OBJETIVO_NAME_MENOR_QUE_3 + " - "
				+ Mensajes.NOMBRE_OBJETIVO_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MENOR_3, Constantes.ERROR,
				datosEntradaNombreObjetivoAlfabeticoMenor3.getNombreObjetivo(), Constantes.NOMBRE_OBJETIVO);
	}

	public DatosPruebaAtributos getTestNombreObjetivoAlfabeticoMayor48(
			final ObjetivoEntity datosEntradaNombreObjetivoAlfabeticoMayor48) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaNombreObjetivoAlfabeticoMayor48.getNombreObjetivo(), Funcionalidad.GESTION_OBJETIVOS,
				Atributo.NOMBRE_OBJETIVO, 48);

		final String resultadoEsperado = CodigosMensajes.OBJETIVO_NAME_MAYOR_QUE_48 + " - "
				+ Mensajes.NOMBRE_OBJETIVO_NO_PUEDE_SER_MAYOR_QUE_48;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MAYOR_48, Constantes.ERROR,
				datosEntradaNombreObjetivoAlfabeticoMayor48.getNombreObjetivo(), Constantes.NOMBRE_OBJETIVO);
	}

	public DatosPruebaAtributos getTestNombreObjetivoCorrectoAlfabetico(final ObjetivoEntity datosEntradaObjetivo) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfabetico
				.comprobarAtributoCorrectoAlfabetico(datosEntradaObjetivo.getNombreObjetivo());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CORRECTO, Constantes.EXITO, datosEntradaObjetivo.getNombreObjetivo(),
				Constantes.NOMBRE_OBJETIVO);

	}
}
