package com.sds.service.test.impl.atributos;

import java.text.ParseException;

import com.sds.model.AccionEntity;
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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosNumerico;

public class TestAtributoAccionDescription {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosNumerico validacionesAtributosNumerico;
	private final ValidacionesAtributosCorrectoAlfabetico validacionesAtributosCorrectoAlfabetico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoAccionDescription() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosCaracteresEspeciales = new ValidacionesAtributosCaracteresEspeciales();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosNumerico = new ValidacionesAtributosNumerico();
		validacionesAtributosCorrectoAlfabetico = new ValidacionesAtributosCorrectoAlfabetico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestAccionDescriptionVacio(final AccionEntity datosEntradaAccionDescriptionVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaAccionDescriptionVacio.getDescripAccion(), Funcionalidad.GESTION_ACCIONES,
				Atributo.ACCION_DESCRIPTION);

		final String resultadoEsperado = CodigosMensajes.ACCION_DESCRIPTION_VACIO + " - "
				+ Mensajes.ACCION_DESCRIPTION_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaAccionDescriptionVacio.getDescripAccion(),
				Constantes.ACCION_DESCRIPTION);
	}

	public DatosPruebaAtributos getTestAccionDescriptionAlfabeticoCaracteresEspeciales(
			final AccionEntity datosEntradaAccionDescriptionCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaAccionDescriptionCaracteresEspeciales.getDescripAccion(),
						Funcionalidad.GESTION_ACCIONES, Atributo.ACCION_DESCRIPTION);

		final String resultadoEsperado = CodigosMensajes.ACCION_DESCRIPTION_ALFABETICO_INCORRECTO + " - "
				+ Mensajes.ACCION_DESCRIPTION_NO_PUEDE_CONTENER_MAS_QUE_LETRAS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaAccionDescriptionCaracteresEspeciales.getDescripAccion(), Constantes.ACCION_DESCRIPTION);
	}

	public DatosPruebaAtributos getTestAccionDescriptionAlfabeticoMenor3(
			final AccionEntity datosEntradaAccionDescriptionAlfabeticoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaAccionDescriptionAlfabeticoMenor3.getDescripAccion(), Funcionalidad.GESTION_ACCIONES,
				Atributo.ACCION_DESCRIPTION, 3);

		final String resultadoEsperado = CodigosMensajes.ACCION_DESCRIPTION_MENOR_QUE_3 + " - "
				+ Mensajes.ACCION_DESCRIPTION_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MENOR_3, Constantes.ERROR,
				datosEntradaAccionDescriptionAlfabeticoMenor3.getDescripAccion(), Constantes.ACCION_DESCRIPTION);
	}

	public DatosPruebaAtributos getTestAccionDescriptionNumerico(
			final AccionEntity datosEntradaAccionDescriptionNumerico) throws ParseException {

		final String resultadoObtenido = validacionesAtributosNumerico.comprobarAtributoNumerico(
				datosEntradaAccionDescriptionNumerico.getDescripAccion(), Funcionalidad.GESTION_ACCIONES,
				Atributo.ACCION_DESCRIPTION);

		final String resultadoEsperado = CodigosMensajes.ACCION_DESCRIPTION_ALFABETICO_INCORRECTO + " - "
				+ Mensajes.ACCION_DESCRIPTION_NO_PUEDE_CONTENER_MAS_QUE_LETRAS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO, Constantes.ERROR, datosEntradaAccionDescriptionNumerico.getDescripAccion(),
				Constantes.ACCION_DESCRIPTION);
	}

	public DatosPruebaAtributos getTestAccionDescriptionAlfabeticoCorrecto(
			final AccionEntity datosEntradaAccionDescription) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfabetico
				.comprobarAtributoCorrectoAlfabetico(datosEntradaAccionDescription.getDescripAccion());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CORRECTO, Constantes.EXITO,
				datosEntradaAccionDescription.getDescripAccion(), Constantes.ACCION_DESCRIPTION);

	}
}
