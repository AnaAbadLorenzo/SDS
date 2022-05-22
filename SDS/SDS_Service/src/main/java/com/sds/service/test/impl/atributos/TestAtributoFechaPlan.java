package com.sds.service.test.impl.atributos;

import java.text.ParseException;

import com.sds.model.PlanEntity;
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

public class TestAtributoFechaPlan {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosEnhe validacionesAtributosEnhe;
	private final ValidacionesAtributosAcentos validacionesAtributosAcentos;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoFechas validacionesAtributosCorrectoFechas;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoFechaPlan() {
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

	public DatosPruebaAtributos getTestFechaPlanVacio(final PlanEntity datosEntradaFechaPlanVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaFechaPlanVacio.getFechaPlan().toString(), Funcionalidad.GESTION_PLANES,
				Atributo.FECHA_PLAN);

		final String resultadoEsperado = CodigosMensajes.FECHA_PLAN_VACIA + " - "
				+ Mensajes.FECHA_PLAN_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaFechaPlanVacio.getFechaPlan().toString(),
				Constantes.FECHA_PLAN);
	}

	public DatosPruebaAtributos getTestFechaPlanNumericoEnhe(final PlanEntity datosEntradaFechaPlanNumericoEnhe)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosEnhe.comprobarAtributoEnhe(
				datosEntradaFechaPlanNumericoEnhe.getFechaPlan().toString(), Funcionalidad.GESTION_PLANES,
				Atributo.FECHA_PLAN);

		final String resultadoEsperado = CodigosMensajes.FECHA_PLAN_NUMERICA_INCORRECTA + " - "
				+ Mensajes.FECHA_PLAN_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ENHE, Constantes.ERROR,
				datosEntradaFechaPlanNumericoEnhe.getFechaPlan().toString(), Constantes.FECHA_PLAN);

	}

	public DatosPruebaAtributos getTestFechaPlanNumericoAcentos(final PlanEntity datosEntradaFechaPlanNumericoAcentos)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosAcentos.comprobarAtributoAcentos(
				datosEntradaFechaPlanNumericoAcentos.getFechaPlan().toString(), Funcionalidad.GESTION_PLANES,
				Atributo.FECHA_PLAN);

		final String resultadoEsperado = CodigosMensajes.FECHA_PLAN_NUMERICA_INCORRECTA + " - "
				+ Mensajes.FECHA_PLAN_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ACENTOS, Constantes.ERROR,
				datosEntradaFechaPlanNumericoAcentos.getFechaPlan().toString(), Constantes.FECHA_PLAN);

	}

	public DatosPruebaAtributos getTestFechaPlanNumericoCaracteresEspeciales(
			final PlanEntity datosEntradaFechaPlanNumericoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaFechaPlanNumericoCaracteresEspeciales.getFechaPlan().toString(),
						Funcionalidad.GESTION_PLANES, Atributo.FECHA_PLAN);

		final String resultadoEsperado = CodigosMensajes.FECHA_PLAN_NUMERICA_INCORRECTA + " - "
				+ Mensajes.FECHA_PLAN_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaFechaPlanNumericoCaracteresEspeciales.getFechaPlan().toString(), Constantes.FECHA_PLAN);
	}

	public DatosPruebaAtributos getTestFechaPlanAlfanumericoEspacios(
			final PlanEntity datosEntradaFechaPlanAlfanumericoEspacios) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaFechaPlanAlfanumericoEspacios.getFechaPlan().toString(), Funcionalidad.GESTION_PLANES,
				Atributo.FECHA_PLAN);

		final String resultadoEsperado = CodigosMensajes.FECHA_PLAN_NUMERICA_INCORRECTA + " - "
				+ Mensajes.FECHA_PLAN_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ESPACIOS, Constantes.ERROR,
				datosEntradaFechaPlanAlfanumericoEspacios.getFechaPlan().toString(), Constantes.FECHA_PLAN);
	}

	public DatosPruebaAtributos getTestFechaPlanAlfanumericoMenor8(
			final PlanEntity datosEntradaFechaPlanAlfanumericoMenor8) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaFechaPlanAlfanumericoMenor8.getFechaPlan().toString(), Funcionalidad.GESTION_PLANES,
				Atributo.FECHA_PLAN, 8);

		final String resultadoEsperado = CodigosMensajes.FECHA_PLAN_MENOR_QUE_8 + " - "
				+ Mensajes.FECHA_PLAN_NO_PUEDE_SER_MENOR_QUE_8;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_MENOR_8, Constantes.ERROR,
				datosEntradaFechaPlanAlfanumericoMenor8.getFechaPlan().toString(), Constantes.FECHA_PLAN);
	}

	public DatosPruebaAtributos getTestFechaPlanAlfanumericoMayor8(
			final PlanEntity datosEntradaFechaPlanAlfanumericoMayor8) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaFechaPlanAlfanumericoMayor8.getFechaPlan().toString(), Funcionalidad.GESTION_PLANES,
				Atributo.FECHA_PLAN, 8);

		final String resultadoEsperado = CodigosMensajes.FECHA_PLAN_MAYOR_QUE_8 + " - "
				+ Mensajes.FECHA_PLAN_NO_PUEDE_SER_MAYOR_QUE_8;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_MAYOR_8, Constantes.ERROR,
				datosEntradaFechaPlanAlfanumericoMayor8.getFechaPlan().toString(), Constantes.FECHA_PLAN);
	}

	public DatosPruebaAtributos getTestFechaPlanMenorFechaActual(final PlanEntity datosEntradaFechaPlanMenorFechaActual)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosCorrectoFechas
				.comprobarAnterioridadFecha(datosEntradaFechaPlanMenorFechaActual.getFechaPlan().toString());

		final String resultadoEsperado = CodigosMensajes.FECHA_INTRODUCIDA_MENOR_FECHA_ACTUAL + " - "
				+ Mensajes.FECHA_INTRODUCIDA_NO_PUEDE_SER_MENOR_QUE_FECHA_ACTUAL;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL, Constantes.ERROR,
				datosEntradaFechaPlanMenorFechaActual.getFechaPlan().toString(), Constantes.FECHA_PLAN);
	}

	public DatosPruebaAtributos getTestFechaPlanCorrectoNumerico(final PlanEntity datosEntradaFechaPlan) {

		final String resultadoObtenido = validacionesAtributosCorrectoFechas
				.comprobarAtributoCorrectoFechas(datosEntradaFechaPlan.getFechaPlan().toString());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_FECHA_CORRECTO, Constantes.EXITO,
				datosEntradaFechaPlan.getFechaPlan().toString(), Constantes.FECHA_PLAN);

	}
}
