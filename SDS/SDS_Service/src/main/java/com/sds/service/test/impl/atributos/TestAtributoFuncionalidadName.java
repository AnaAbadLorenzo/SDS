package com.sds.service.test.impl.atributos;

import java.text.ParseException;

import com.sds.model.FuncionalidadEntity;
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

public class TestAtributoFuncionalidadName {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosNumerico validacionesAtributosNumerico;
	private final ValidacionesAtributosCorrectoAlfabetico validacionesAtributosCorrectoAlfabetico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoFuncionalidadName() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosCaracteresEspeciales = new ValidacionesAtributosCaracteresEspeciales();
		validacionesAtributosEspacios = new ValidacionesAtributosEspacios();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosMayor = new ValidacionesAtributosMayor();
		validacionesAtributosNumerico = new ValidacionesAtributosNumerico();
		validacionesAtributosCorrectoAlfabetico = new ValidacionesAtributosCorrectoAlfabetico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestFuncionalidadNameVacio(
			final FuncionalidadEntity datosEntradaFuncionalidadNameVacio) throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaFuncionalidadNameVacio.getNombreFuncionalidad(), Funcionalidad.GESTION_FUNCIONALIDADES,
				Atributo.FUNCIONALIDAD_NAME);

		final String resultadoEsperado = CodigosMensajes.FUNCIONALIDAD_NAME_VACIA + " - "
				+ Mensajes.FUNCIONALIDAD_NAME_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaFuncionalidadNameVacio.getNombreFuncionalidad(),
				Constantes.FUNCIONALIDAD_NAME);
	}

	public DatosPruebaAtributos getTestFuncionalidadNameAlfabeticoCaracteresEspeciales(
			final FuncionalidadEntity datosEntradaFuncionalidadNameAlfabeticoCaracteresEspeciales)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaFuncionalidadNameAlfabeticoCaracteresEspeciales.getNombreFuncionalidad(),
						Funcionalidad.GESTION_FUNCIONALIDADES, Atributo.FUNCIONALIDAD_NAME);

		final String resultadoEsperado = CodigosMensajes.FUNCIONALIDAD_NAME_ALFABETICO_INCORRECTO + " - "
				+ Mensajes.FUNCIONALIDAD_NAME_SOLO_PUEDE_CONTENER_LETRAS_Y_ACENTOS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaFuncionalidadNameAlfabeticoCaracteresEspeciales.getNombreFuncionalidad(),
				Constantes.FUNCIONALIDAD_NAME);
	}

	public DatosPruebaAtributos getTestFuncionalidadNameAlfabeticoMenor3(
			final FuncionalidadEntity datosEntradaFuncionalidadNameAlfabeticoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaFuncionalidadNameAlfabeticoMenor3.getNombreFuncionalidad(),
				Funcionalidad.GESTION_FUNCIONALIDADES, Atributo.FUNCIONALIDAD_NAME, 3);

		final String resultadoEsperado = CodigosMensajes.FUNCIONALIDAD_NAME_MENOR_QUE_3 + " - "
				+ Mensajes.FUNCIONALIDAD_NAME_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MENOR_3, Constantes.ERROR,
				datosEntradaFuncionalidadNameAlfabeticoMenor3.getNombreFuncionalidad(), Constantes.FUNCIONALIDAD_NAME);
	}

	public DatosPruebaAtributos getTestFuncionalidadNameAlfabeticoMayor48(
			final FuncionalidadEntity datosEntradaFuncionalidadNameAlfabeticoMayor48) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaFuncionalidadNameAlfabeticoMayor48.getNombreFuncionalidad(),
				Funcionalidad.GESTION_FUNCIONALIDADES, Atributo.FUNCIONALIDAD_NAME, 48);

		final String resultadoEsperado = CodigosMensajes.FUNCIONALIDAD_NAME_MAYOR_QUE_48 + " - "
				+ Mensajes.FUNCIONALIDAD_NAME_NO_PUEDE_SER_MAYOR_QUE_48;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MAYOR_48, Constantes.ERROR,
				datosEntradaFuncionalidadNameAlfabeticoMayor48.getNombreFuncionalidad(), Constantes.FUNCIONALIDAD_NAME);
	}

	public DatosPruebaAtributos getTestFuncionalidadNameNumerico(
			final FuncionalidadEntity datosEntradaFuncionalidadNameNumerico) throws ParseException {

		final String resultadoObtenido = validacionesAtributosNumerico.comprobarAtributoNumerico(
				datosEntradaFuncionalidadNameNumerico.getNombreFuncionalidad(), Funcionalidad.GESTION_FUNCIONALIDADES,
				Atributo.FUNCIONALIDAD_NAME);

		final String resultadoEsperado = CodigosMensajes.FUNCIONALIDAD_NAME_ALFABETICO_INCORRECTO + " - "
				+ Mensajes.FUNCIONALIDAD_NAME_SOLO_PUEDE_CONTENER_LETRAS_Y_ACENTOS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO, Constantes.ERROR,
				datosEntradaFuncionalidadNameNumerico.getNombreFuncionalidad(), Constantes.FUNCIONALIDAD_NAME);
	}

	public DatosPruebaAtributos getTestFuncionalidadNameCorrectoAlfabetico(
			final FuncionalidadEntity datosEntradaFuncionalidadName) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfabetico
				.comprobarAtributoCorrectoAlfabetico(datosEntradaFuncionalidadName.getNombreFuncionalidad());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CORRECTO, Constantes.EXITO,
				datosEntradaFuncionalidadName.getNombreFuncionalidad(), Constantes.FUNCIONALIDAD_NAME);

	}
}
