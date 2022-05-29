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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMayor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoNombreProcedimiento {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoNombreProcedimiento() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosCaracteresEspeciales = new ValidacionesAtributosCaracteresEspeciales();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosMayor = new ValidacionesAtributosMayor();
		validacionesAtributosCorrectoAlfanumerico = new ValidacionesAtributosCorrectoAlfanumerico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestNombreProcedimientoVacio(
			final ProcedimientoEntity datosEntradaNombreProcedimientoVacio) throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaNombreProcedimientoVacio.getNombreProcedimiento(), Funcionalidad.GESTION_PROCEDIMIENTOS,
				Atributo.NOMBRE_PROCEDIMIENTO);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_PROCEDIMIENTO_VACIO + " - "
				+ Mensajes.NOMBRE_PROCEDIMIENTO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				datosEntradaNombreProcedimientoVacio.getNombreProcedimiento(), Constantes.NOMBRE_PROCEDIMIENTO);
	}

	public DatosPruebaAtributos getTestNombreProcedimientoAlfanumericoCaracteresEspeciales(
			final ProcedimientoEntity datosEntradaNombrePlanAlfanumericoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaNombrePlanAlfanumericoCaracteresEspeciales.getNombreProcedimiento(),
						Funcionalidad.GESTION_PROCEDIMIENTOS, Atributo.NOMBRE_PROCEDIMIENTO);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_PROCEDIMIENTO_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.NOMBRE_PROCEDIMIENTO_SOLO_PUEDE_CONTENER_LETRAS_NUMEROS_Y_ESPACIOS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaNombrePlanAlfanumericoCaracteresEspeciales.getNombreProcedimiento(),
				Constantes.NOMBRE_PROCEDIMIENTO);
	}

	public DatosPruebaAtributos getTestNombreProcedimientoAlfanumericoMenor3(
			final ProcedimientoEntity datosEntradaNombreProcedimientoAlfanumericoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaNombreProcedimientoAlfanumericoMenor3.getNombreProcedimiento(),
				Funcionalidad.GESTION_PROCEDIMIENTOS, Atributo.NOMBRE_PROCEDIMIENTO, 3);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_PROCEDIMIENTO_MENOR_QUE_3 + " - "
				+ Mensajes.NOMBRE_PROCEDIMIENTO_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_3, Constantes.ERROR,
				datosEntradaNombreProcedimientoAlfanumericoMenor3.getNombreProcedimiento(),
				Constantes.NOMBRE_PROCEDIMIENTO);
	}

	public DatosPruebaAtributos getTestNombreProcedimientoAlfanumericoMayor48(
			final ProcedimientoEntity datosEntradaNombreProcedimientoAlfanumericoMayor48) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaNombreProcedimientoAlfanumericoMayor48.getNombreProcedimiento(),
				Funcionalidad.GESTION_PROCEDIMIENTOS, Atributo.NOMBRE_PROCEDIMIENTO, 48);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_PROCEDIMIENTO_MAYOR_QUE_48 + " - "
				+ Mensajes.NOMBRE_PROCEDIMIENTO_NO_PUEDE_SER_MAYOR_QUE_48;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MAYOR_48, Constantes.ERROR,
				datosEntradaNombreProcedimientoAlfanumericoMayor48.getNombreProcedimiento(),
				Constantes.NOMBRE_PROCEDIMIENTO);
	}

	public DatosPruebaAtributos getTestNombreProcedimientoCorrectoAlfabetico(
			final ProcedimientoEntity datosEntradaProcedimiento) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(datosEntradaProcedimiento.getNombreProcedimiento());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CORRECTO, Constantes.EXITO,
				datosEntradaProcedimiento.getNombreProcedimiento(), Constantes.NOMBRE_PROCEDIMIENTO);

	}

}
