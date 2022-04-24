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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoFuncionalidadDescription {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosCorrectoAlfabetico validacionesAtributosCorrectoAlfabetico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoFuncionalidadDescription() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosCaracteresEspeciales = new ValidacionesAtributosCaracteresEspeciales();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosCorrectoAlfabetico = new ValidacionesAtributosCorrectoAlfabetico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestFuncionalidadDescriptionVacio(
			final FuncionalidadEntity datosEntradaFuncionalidadDescriptionVacio) throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaFuncionalidadDescriptionVacio.getDescripFuncionalidad(),
				Funcionalidad.GESTION_FUNCIONALIDADES, Atributo.FUNCIONALIDAD_DESCRIPTION);

		final String resultadoEsperado = CodigosMensajes.FUNCIONALIDAD_DESCRIPTION_VACIA + " - "
				+ Mensajes.FUNCIONALIDAD_DESCRIPTION_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				datosEntradaFuncionalidadDescriptionVacio.getDescripFuncionalidad(),
				Constantes.FUNCIONALIDAD_DESCRIPTION);
	}

	public DatosPruebaAtributos getTestFuncionalidadDescriptionAlfabeticoCaracteresEspeciales(
			final FuncionalidadEntity datosEntradaFuncionalidadDescriptionCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaFuncionalidadDescriptionCaracteresEspeciales.getDescripFuncionalidad(),
						Funcionalidad.GESTION_FUNCIONALIDADES, Atributo.FUNCIONALIDAD_DESCRIPTION);

		final String resultadoEsperado = CodigosMensajes.FUNCIONALIDAD_DESCRIPTION_ALFABETICO_INCORRECTO + " - "
				+ Mensajes.FUNCIONALIDAD_DESCRIPTION_SOLO_PUEDE_CONTENER_LETRAS_Y_ACENTOS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaFuncionalidadDescriptionCaracteresEspeciales.getDescripFuncionalidad(),
				Constantes.FUNCIONALIDAD_DESCRIPTION);
	}

	public DatosPruebaAtributos getTestFuncionalidadDescriptionAlfabeticoMenor3(
			final FuncionalidadEntity datosEntradaFuncionalidadDescriptionAlfabeticoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaFuncionalidadDescriptionAlfabeticoMenor3.getDescripFuncionalidad(),
				Funcionalidad.GESTION_FUNCIONALIDADES, Atributo.FUNCIONALIDAD_DESCRIPTION, 3);

		final String resultadoEsperado = CodigosMensajes.FUNCIONALIDAD_DESCRIPTION_MENOR_QUE_3 + " - "
				+ Mensajes.FUNCIONALIDAD_DESCRIPTION_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MENOR_3, Constantes.ERROR,
				datosEntradaFuncionalidadDescriptionAlfabeticoMenor3.getDescripFuncionalidad(),
				Constantes.FUNCIONALIDAD_DESCRIPTION);
	}

	public DatosPruebaAtributos getTestFuncionalidadDescriptionAlfabeticoCorrecto(
			final FuncionalidadEntity datosEntradaFuncionalidadDescription) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfabetico
				.comprobarAtributoCorrectoAlfabetico(datosEntradaFuncionalidadDescription.getDescripFuncionalidad());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CORRECTO, Constantes.EXITO,
				datosEntradaFuncionalidadDescription.getDescripFuncionalidad(), Constantes.FUNCIONALIDAD_DESCRIPTION);

	}
}
