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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEspacios;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMayor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosNumerico;

public class TestAtributoAccionName {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosNumerico validacionesAtributosNumerico;
	private final ValidacionesAtributosCorrectoAlfabetico validacionesAtributosCorrectoAlfabetico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoAccionName() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosCaracteresEspeciales = new ValidacionesAtributosCaracteresEspeciales();
		validacionesAtributosEspacios = new ValidacionesAtributosEspacios();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosMayor = new ValidacionesAtributosMayor();
		validacionesAtributosNumerico = new ValidacionesAtributosNumerico();
		validacionesAtributosCorrectoAlfabetico = new ValidacionesAtributosCorrectoAlfabetico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestAccionNameVacio(final AccionEntity datosEntradaAccionNameVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaAccionNameVacio.getNombreAccion(), Funcionalidad.GESTION_ACCIONES, Atributo.ACCION_NAME);

		final String resultadoEsperado = CodigosMensajes.ACCION_NAME_VACIO + " - "
				+ Mensajes.ACCION_NAME_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaAccionNameVacio.getNombreAccion(),
				Constantes.ACCION_NAME);
	}

	public DatosPruebaAtributos getTestAccionNameAlfabeticoCaracteresEspeciales(
			final AccionEntity datosEntradaAccionNameAlfabeticoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaAccionNameAlfabeticoCaracteresEspeciales.getNombreAccion(),
						Funcionalidad.GESTION_ACCIONES, Atributo.ACCION_NAME);

		final String resultadoEsperado = CodigosMensajes.ACCION_NAME_ALFABETICO_INCORRECTO + " - "
				+ Mensajes.ACCION_NAME_NO_PUEDE_CONTENER_MAS_QUE_LETRAS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaAccionNameAlfabeticoCaracteresEspeciales.getNombreAccion(), Constantes.ACCION_NAME);
	}

	public DatosPruebaAtributos getTestAccionNameAlfabeticoEspacios(
			final AccionEntity datosEntradaAccionNameAlfabeticoEspacios) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaAccionNameAlfabeticoEspacios.getNombreAccion(), Funcionalidad.GESTION_ACCIONES,
				Atributo.ACCION_NAME);

		final String resultadoEsperado = CodigosMensajes.ACCION_NAME_ALFABETICO_INCORRECTO + " - "
				+ Mensajes.ACCION_NAME_NO_PUEDE_CONTENER_MAS_QUE_LETRAS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_ESPACIOS, Constantes.ERROR,
				datosEntradaAccionNameAlfabeticoEspacios.getNombreAccion(), Constantes.ACCION_NAME);
	}

	public DatosPruebaAtributos getTestAccionNameAlfabeticoMenor3(
			final AccionEntity datosEntradaAccionNameAlfabeticoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaAccionNameAlfabeticoMenor3.getNombreAccion(), Funcionalidad.GESTION_ACCIONES,
				Atributo.ACCION_NAME, 3);

		final String resultadoEsperado = CodigosMensajes.ACCION_NAME_MENOR_QUE_3 + " - "
				+ Mensajes.ACCION_NAME_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MENOR_3, Constantes.ERROR,
				datosEntradaAccionNameAlfabeticoMenor3.getNombreAccion(), Constantes.ACCION_NAME);
	}

	public DatosPruebaAtributos getTestAccionNameAlfabeticoMayor48(
			final AccionEntity datosEntradaAccionNameAlfabeticoMayor48) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaAccionNameAlfabeticoMayor48.getNombreAccion(), Funcionalidad.GESTION_ACCIONES,
				Atributo.ACCION_NAME, 48);

		final String resultadoEsperado = CodigosMensajes.ACCION_NAME_MAYOR_QUE_48 + " - "
				+ Mensajes.ACCION_NAME_NO_PUEDE_SER_MAYOR_QUE_48;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MAYOR_48, Constantes.ERROR,
				datosEntradaAccionNameAlfabeticoMayor48.getNombreAccion(), Constantes.ACCION_NAME);
	}

	public DatosPruebaAtributos getTestAccionNameNumerico(final AccionEntity datosEntradaAccionNameNumerico)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosNumerico.comprobarAtributoNumerico(
				datosEntradaAccionNameNumerico.getNombreAccion(), Funcionalidad.GESTION_ACCIONES, Atributo.ACCION_NAME);

		final String resultadoEsperado = CodigosMensajes.ACCION_NAME_ALFABETICO_INCORRECTO + " - "
				+ Mensajes.ACCION_NAME_NO_PUEDE_CONTENER_MAS_QUE_LETRAS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO, Constantes.ERROR, datosEntradaAccionNameNumerico.getNombreAccion(),
				Constantes.ACCION_NAME);
	}

	public DatosPruebaAtributos getTestAccionNameCorrectoAlfabetico(final AccionEntity datosEntradaAccionName) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfabetico
				.comprobarAtributoCorrectoAlfabetico(datosEntradaAccionName.getNombreAccion());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CORRECTO, Constantes.EXITO, datosEntradaAccionName.getNombreAccion(),
				Constantes.ACCION_NAME);

	}

}
