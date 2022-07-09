package com.sds.service.test.impl.atributos;

import java.text.ParseException;

import com.sds.model.ProcedimientoEntity;
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

public class TestAtributoDescripcionProcedimiento {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoDescripcionProcedimiento() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosCaracteresEspeciales = new ValidacionesAtributosCaracteresEspeciales();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosCorrectoAlfanumerico = new ValidacionesAtributosCorrectoAlfanumerico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestDescripcionProcedimientoVacio(
			final ProcedimientoEntity datosEntradaDescripcionProcedimientoVacio) throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaDescripcionProcedimientoVacio.getDescripProcedimiento(),
				Funcionalidad.GESTION_PROCEDIMIENTOS, Atributo.DESCRIP_PROCEDIMIENTO);

		final String resultadoEsperado = CodigosMensajes.DESCRIPCION_PROCEDIMIENTO_VACIO + " - "
				+ Mensajes.DESCRIPCION_PROCEDIMIENTO_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				datosEntradaDescripcionProcedimientoVacio.getDescripProcedimiento(), Constantes.DESCRIPCION_PLAN);
	}

	public DatosPruebaAtributos getTestDescripcionProcedimientoAlfanumericoCaracteresEspeciales(
			final ProcedimientoEntity datosEntradaDescripcionProcedimientoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaDescripcionProcedimientoCaracteresEspeciales.getDescripProcedimiento(),
						Funcionalidad.GESTION_PROCEDIMIENTOS, Atributo.DESCRIP_PROCEDIMIENTO);

		final String resultadoEsperado = CodigosMensajes.DESCRIPCION_PROCEDIMIENTO_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.DESCRIPCION_PROCEDIMIENTO_PUEDE_CONTENER_LETRAS_NUMEROS_Y_ESPACIOS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaDescripcionProcedimientoCaracteresEspeciales.getDescripProcedimiento(),
				Constantes.DESCRIPCION_PROCEDIMIENTO);
	}

	public DatosPruebaAtributos getTestDescripcionProcedimientoAlfanumericoMenor3(
			final ProcedimientoEntity datosEntradaDescripcionProcedimientoAlfanumericoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaDescripcionProcedimientoAlfanumericoMenor3.getDescripProcedimiento(),
				Funcionalidad.GESTION_PROCEDIMIENTOS, Atributo.DESCRIP_PROCEDIMIENTO, 3);

		final String resultadoEsperado = CodigosMensajes.DESCRIPCION_PROCEDIMIENTO_MENOR_QUE_3 + " - "
				+ Mensajes.DESCRIPCION_PROCEDIMIENTO_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_3, Constantes.ERROR,
				datosEntradaDescripcionProcedimientoAlfanumericoMenor3.getDescripProcedimiento(),
				Constantes.DESCRIPCION_PROCEDIMIENTO);
	}

	public DatosPruebaAtributos getTestDescripcionProcedimientoAlfanumericoCorrecto(
			final ProcedimientoEntity datosEntradaDescripcionProcedimiento) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(datosEntradaDescripcionProcedimiento.getDescripProcedimiento());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CORRECTO, Constantes.EXITO,
				datosEntradaDescripcionProcedimiento.getDescripProcedimiento(), Constantes.DESCRIPCION_PROCEDIMIENTO);

	}
}
