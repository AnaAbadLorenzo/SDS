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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosAcentos;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosBlank;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCaracteresEspeciales;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCorrectoFechas;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEnhe;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEspacios;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMayor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoFechaProceso {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosEnhe validacionesAtributosEnhe;
	private final ValidacionesAtributosAcentos validacionesAtributosAcentos;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoFechas validacionesAtributosCorrectoFechas;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoFechaProceso() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosEnhe = new ValidacionesAtributosEnhe();
		validacionesAtributosAcentos = new ValidacionesAtributosAcentos();
		validacionesAtributosCaracteresEspeciales = new ValidacionesAtributosCaracteresEspeciales();
		validacionesAtributosEspacios = new ValidacionesAtributosEspacios();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosMayor = new ValidacionesAtributosMayor();
		validacionesAtributosCorrectoFechas = new ValidacionesAtributosCorrectoFechas();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestFechaProcesoVacio(final ProcesoEntity datosEntradaFechaProcesoVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaFechaProcesoVacio.getFechaProceso().toString(), Funcionalidad.GESTION_PROCESOS,
				Atributo.FECHA_PROCESO);

		final String resultadoEsperado = CodigosMensajes.FECHA_PROCESO_VACIA + " - "
				+ Mensajes.FECHA_PROCESO_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaFechaProcesoVacio.getFechaProceso().toString(),
				Constantes.FECHA_PROCESO);
	}

	public DatosPruebaAtributos getTestFechaProcesoNumericoEnhe(
			final ProcesoEntity datosEntradaFechaProcesoNumericoEnhe) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEnhe.comprobarAtributoEnhe(
				datosEntradaFechaProcesoNumericoEnhe.getFechaProceso().toString(), Funcionalidad.GESTION_PROCESOS,
				Atributo.FECHA_PROCESO);

		final String resultadoEsperado = CodigosMensajes.FECHA_PROCESO_NUMERICA_INCORRECTA + " - "
				+ Mensajes.FECHA_PROCESO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ENHE, Constantes.ERROR,
				datosEntradaFechaProcesoNumericoEnhe.getFechaProceso().toString(), Constantes.FECHA_PROCESO);

	}

	public DatosPruebaAtributos getTestFechaProcesoNumericoAcentos(
			final ProcesoEntity datosEntradaFechaProcesoNumericoAcentos) throws ParseException {

		final String resultadoObtenido = validacionesAtributosAcentos.comprobarAtributoAcentos(
				datosEntradaFechaProcesoNumericoAcentos.getFechaProceso().toString(), Funcionalidad.GESTION_PROCESOS,
				Atributo.FECHA_PROCESO);

		final String resultadoEsperado = CodigosMensajes.FECHA_PROCESO_NUMERICA_INCORRECTA + " - "
				+ Mensajes.FECHA_PROCESO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ACENTOS, Constantes.ERROR,
				datosEntradaFechaProcesoNumericoAcentos.getFechaProceso().toString(), Constantes.FECHA_PROCESO);

	}

	public DatosPruebaAtributos getTestFechaProcesoNumericoCaracteresEspeciales(
			final ProcesoEntity datosEntradaFechaProcesoNumericoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaFechaProcesoNumericoCaracteresEspeciales.getFechaProceso().toString(),
						Funcionalidad.GESTION_PROCESOS, Atributo.FECHA_PROCESO);

		final String resultadoEsperado = CodigosMensajes.FECHA_PROCESO_NUMERICA_INCORRECTA + " - "
				+ Mensajes.FECHA_PROCESO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaFechaProcesoNumericoCaracteresEspeciales.getFechaProceso().toString(),
				Constantes.FECHA_PROCESO);
	}

	public DatosPruebaAtributos getTestFechaProcesoAlfanumericoEspacios(
			final ProcesoEntity datosEntradaFechaProcesoAlfanumericoEspacios) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaFechaProcesoAlfanumericoEspacios.getFechaProceso().toString(),
				Funcionalidad.GESTION_PROCESOS, Atributo.FECHA_PROCESO);

		final String resultadoEsperado = CodigosMensajes.FECHA_PROCESO_NUMERICA_INCORRECTA + " - "
				+ Mensajes.FECHA_PROCESO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ESPACIOS, Constantes.ERROR,
				datosEntradaFechaProcesoAlfanumericoEspacios.getFechaProceso().toString(), Constantes.FECHA_PROCESO);
	}

	public DatosPruebaAtributos getTestFechaProcesoAlfanumericoMenor8(
			final ProcesoEntity datosEntradaFechaProcesoAlfanumericoMenor8) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaFechaProcesoAlfanumericoMenor8.getFechaProceso().toString(), Funcionalidad.GESTION_PROCESOS,
				Atributo.FECHA_PROCESO, 8);

		final String resultadoEsperado = CodigosMensajes.FECHA_PROCESO_MENOR_QUE_8 + " - "
				+ Mensajes.FECHA_PROCESO_NO_PUEDE_SER_MENOR_QUE_8;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_MENOR_8, Constantes.ERROR,
				datosEntradaFechaProcesoAlfanumericoMenor8.getFechaProceso().toString(), Constantes.FECHA_PROCESO);
	}

	public DatosPruebaAtributos getTestFechaProcesoAlfanumericoMayor8(
			final ProcesoEntity datosEntradaFechaProcesoAlfanumericoMayor8) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaFechaProcesoAlfanumericoMayor8.getFechaProceso().toString(), Funcionalidad.GESTION_PROCESOS,
				Atributo.FECHA_PROCESO, 8);

		final String resultadoEsperado = CodigosMensajes.FECHA_PROCESO_MAYOR_QUE_8 + " - "
				+ Mensajes.FECHA_PROCESO_NO_PUEDE_SER_MAYOR_QUE_8;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_MAYOR_8, Constantes.ERROR,
				datosEntradaFechaProcesoAlfanumericoMayor8.getFechaProceso().toString(), Constantes.FECHA_PROCESO);
	}

	public DatosPruebaAtributos getTestFechaProcesoMenorFechaActual(
			final ProcesoEntity datosEntradaFechaProcesoMenorFechaActual) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCorrectoFechas
				.comprobarAnterioridadFecha(datosEntradaFechaProcesoMenorFechaActual.getFechaProceso().toString());

		final String resultadoEsperado = CodigosMensajes.FECHA_INTRODUCIDA_MENOR_FECHA_ACTUAL + " - "
				+ Mensajes.FECHA_INTRODUCIDA_NO_PUEDE_SER_MENOR_QUE_FECHA_ACTUAL;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL, Constantes.ERROR,
				datosEntradaFechaProcesoMenorFechaActual.getFechaProceso().toString(), Constantes.FECHA_PROCESO);
	}

	public DatosPruebaAtributos getTestFechaProcesoCorrectoNumerico(final ProcesoEntity datosEntradaFechaProceso) {

		final String resultadoObtenido = validacionesAtributosCorrectoFechas
				.comprobarAtributoCorrectoFechas(datosEntradaFechaProceso.getFechaProceso().toString());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_FECHA_CORRECTO, Constantes.EXITO,
				datosEntradaFechaProceso.getFechaProceso().toString(), Constantes.FECHA_PROCESO);

	}
}
