package com.sds.service.test.impl.atributos;

import java.text.ParseException;

import com.sds.model.ProcesoEntity;
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

public class TestAtributoNombreProceso {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoNombreProceso() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosCaracteresEspeciales = new ValidacionesAtributosCaracteresEspeciales();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosMayor = new ValidacionesAtributosMayor();
		validacionesAtributosCorrectoAlfanumerico = new ValidacionesAtributosCorrectoAlfanumerico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestNombreProcesoVacio(final ProcesoEntity datosEntradaNombreProcesoVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaNombreProcesoVacio.getNombreProceso(), Funcionalidad.GESTION_PROCESOS,
				Atributo.NOMBRE_PROCESO);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_PROCESO_VACIO + " - "
				+ Mensajes.NOMBRE_PROCESO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaNombreProcesoVacio.getNombreProceso(),
				Constantes.NOMBRE_PROCESO);
	}

	public DatosPruebaAtributos getTestNombreProcesoAlfanumericoCaracteresEspeciales(
			final ProcesoEntity datosEntradaNombreProcesoAlfanumericoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaNombreProcesoAlfanumericoCaracteresEspeciales.getNombreProceso(),
						Funcionalidad.GESTION_PROCESOS, Atributo.NOMBRE_PROCESO);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_PROCESO_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.NOMBRE_PROCESO_SOLO_PUEDE_CONTENER_LETRAS_NUMEROS_Y_ESPACIOS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaNombreProcesoAlfanumericoCaracteresEspeciales.getNombreProceso(),
				Constantes.NOMBRE_PROCESO);
	}

	public DatosPruebaAtributos getTestNombreProcesoAlfanumericoMenor3(
			final ProcesoEntity datosEntradaNombreProcesoAlfanumericoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaNombreProcesoAlfanumericoMenor3.getNombreProceso(), Funcionalidad.GESTION_PROCESOS,
				Atributo.NOMBRE_PROCESO, 3);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_PROCESO_MENOR_QUE_3 + " - "
				+ Mensajes.NOMBRE_PROCESO_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_3, Constantes.ERROR,
				datosEntradaNombreProcesoAlfanumericoMenor3.getNombreProceso(), Constantes.NOMBRE_PROCESO);
	}

	public DatosPruebaAtributos getTestNombreProcesoAlfanumericoMayor48(
			final ProcesoEntity datosEntradaNombreProcesoAlfanumericoMayor48) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaNombreProcesoAlfanumericoMayor48.getNombreProceso(), Funcionalidad.GESTION_PROCESOS,
				Atributo.NOMBRE_PROCESO, 48);

		final String resultadoEsperado = CodigosMensajes.NOMBRE_PROCESO_MAYOR_QUE_48 + " - "
				+ Mensajes.NOMBRE_PROCESO_NO_PUEDE_SER_MAYOR_QUE_48;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MAYOR_48, Constantes.ERROR,
				datosEntradaNombreProcesoAlfanumericoMayor48.getNombreProceso(), Constantes.NOMBRE_PROCESO);
	}

	public DatosPruebaAtributos getTestNombreProcesoCorrectoAlfanumerico(final ProcesoEntity datosEntradaProceso) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(datosEntradaProceso.getNombreProceso());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CORRECTO, Constantes.EXITO, datosEntradaProceso.getNombreProceso(),
				Constantes.NOMBRE_PROCESO);

	}
}
