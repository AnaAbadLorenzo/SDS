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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosAcentos;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosBlank;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCaracteresEspeciales;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCorrectoFechas;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEnhe;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEspacios;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMayor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoFechaProcedimiento {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosEnhe validacionesAtributosEnhe;
	private final ValidacionesAtributosAcentos validacionesAtributosAcentos;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoFechas validacionesAtributosCorrectoFechas;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoFechaProcedimiento() {
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

	public DatosPruebaAtributos getTestFechaProcedimientoVacio(
			final ProcedimientoEntity datosEntradaFechaProcedimientoVacio) throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaFechaProcedimientoVacio.getFechaProcedimiento().toString(),
				Funcionalidad.GESTION_PROCEDIMIENTOS, Atributo.FECHA_PROCEDIMIENTO);

		final String resultadoEsperado = CodigosMensajes.FECHA_PROCEDIMIENTO_VACIA + " - "
				+ Mensajes.FECHA_PROCEDIMIENTO_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				datosEntradaFechaProcedimientoVacio.getFechaProcedimiento().toString(), Constantes.FECHA_PROCEDIMIENTO);
	}

	public DatosPruebaAtributos getTestFechaProcedimientoNumericoEnhe(
			final ProcedimientoEntity datosEntradaFechaProcedimientoNumericoEnhe) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEnhe.comprobarAtributoEnhe(
				datosEntradaFechaProcedimientoNumericoEnhe.getFechaProcedimiento().toString(),
				Funcionalidad.GESTION_PROCEDIMIENTOS, Atributo.FECHA_PROCEDIMIENTO);

		final String resultadoEsperado = CodigosMensajes.FECHA_PROCEDIMIENTO_NUMERICA_INCORRECTA + " - "
				+ Mensajes.FECHA_PROCEDIMIENTO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ENHE, Constantes.ERROR,
				datosEntradaFechaProcedimientoNumericoEnhe.getFechaProcedimiento().toString(),
				Constantes.FECHA_PROCEDIMIENTO);

	}

	public DatosPruebaAtributos getTestFechaProcedimientoNumericoAcentos(
			final ProcedimientoEntity datosEntradaFechaProcedimientoNumericoAcentos) throws ParseException {

		final String resultadoObtenido = validacionesAtributosAcentos.comprobarAtributoAcentos(
				datosEntradaFechaProcedimientoNumericoAcentos.getFechaProcedimiento().toString(),
				Funcionalidad.GESTION_PROCEDIMIENTOS, Atributo.FECHA_PROCEDIMIENTO);

		final String resultadoEsperado = CodigosMensajes.FECHA_PROCEDIMIENTO_NUMERICA_INCORRECTA + " - "
				+ Mensajes.FECHA_PROCEDIMIENTO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ACENTOS, Constantes.ERROR,
				datosEntradaFechaProcedimientoNumericoAcentos.getFechaProcedimiento().toString(),
				Constantes.FECHA_PROCEDIMIENTO);

	}

	public DatosPruebaAtributos getTestFechaProcedimientoNumericoCaracteresEspeciales(
			final ProcedimientoEntity datosEntradaFechaProcedimientoNumericoCaracteresEspeciales)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaFechaProcedimientoNumericoCaracteresEspeciales.getFechaProcedimiento().toString(),
						Funcionalidad.GESTION_PROCEDIMIENTOS, Atributo.FECHA_PROCEDIMIENTO);

		final String resultadoEsperado = CodigosMensajes.FECHA_PROCEDIMIENTO_NUMERICA_INCORRECTA + " - "
				+ Mensajes.FECHA_PROCEDIMIENTO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaFechaProcedimientoNumericoCaracteresEspeciales.getFechaProcedimiento().toString(),
				Constantes.FECHA_PROCEDIMIENTO);
	}

	public DatosPruebaAtributos getTestFechaProcedimientoAlfanumericoEspacios(
			final ProcedimientoEntity datosEntradaFechaProcedimientoAlfanumericoEspacios) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaFechaProcedimientoAlfanumericoEspacios.getFechaProcedimiento().toString(),
				Funcionalidad.GESTION_PROCEDIMIENTOS, Atributo.FECHA_PROCEDIMIENTO);

		final String resultadoEsperado = CodigosMensajes.FECHA_PROCEDIMIENTO_NUMERICA_INCORRECTA + " - "
				+ Mensajes.FECHA_PROCEDIMIENTO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ESPACIOS, Constantes.ERROR,
				datosEntradaFechaProcedimientoAlfanumericoEspacios.getFechaProcedimiento().toString(),
				Constantes.FECHA_PROCEDIMIENTO);
	}

	public DatosPruebaAtributos getTestFechaProcedimientoAlfanumericoMenor8(
			final ProcedimientoEntity datosEntradaFechaProcedimientoAlfanumericoMenor8) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaFechaProcedimientoAlfanumericoMenor8.getFechaProcedimiento().toString(),
				Funcionalidad.GESTION_PROCEDIMIENTOS, Atributo.FECHA_PROCEDIMIENTO, 8);

		final String resultadoEsperado = CodigosMensajes.FECHA_PROCEDIMIENTO_MENOR_QUE_8 + " - "
				+ Mensajes.FECHA_PROCEDIMIENTO_NO_PUEDE_SER_MENOR_QUE_8;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_MENOR_8, Constantes.ERROR,
				datosEntradaFechaProcedimientoAlfanumericoMenor8.getFechaProcedimiento().toString(),
				Constantes.FECHA_PROCEDIMIENTO);
	}

	public DatosPruebaAtributos getTestFechaProcedimientoAlfanumericoMayor8(
			final ProcedimientoEntity datosEntradaFechaProcedimientoAlfanumericoMayor8) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaFechaProcedimientoAlfanumericoMayor8.getFechaProcedimiento().toString(),
				Funcionalidad.GESTION_PROCEDIMIENTOS, Atributo.FECHA_PROCEDIMIENTO, 8);

		final String resultadoEsperado = CodigosMensajes.FECHA_PROCEDIMIENTO_MAYOR_QUE_8 + " - "
				+ Mensajes.FECHA_PROCEDIMIENTO_NO_PUEDE_SER_MAYOR_QUE_8;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_MAYOR_8, Constantes.ERROR,
				datosEntradaFechaProcedimientoAlfanumericoMayor8.getFechaProcedimiento().toString(),
				Constantes.FECHA_PROCEDIMIENTO);
	}

	public DatosPruebaAtributos getTestFechaProcedimientoMenorFechaActual(
			final ProcedimientoEntity datosEntradaFechaPlanMenorFechaActual) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCorrectoFechas
				.comprobarAnterioridadFecha(datosEntradaFechaPlanMenorFechaActual.getFechaProcedimiento().toString());

		final String resultadoEsperado = CodigosMensajes.FECHA_INTRODUCIDA_MENOR_FECHA_ACTUAL + " - "
				+ Mensajes.FECHA_INTRODUCIDA_NO_PUEDE_SER_MENOR_QUE_FECHA_ACTUAL;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL, Constantes.ERROR,
				datosEntradaFechaPlanMenorFechaActual.getFechaProcedimiento().toString(),
				Constantes.FECHA_PROCEDIMIENTO);
	}

	public DatosPruebaAtributos getTestFechaProcedimientoCorrectoNumerico(
			final ProcedimientoEntity datosEntradaFechaProcedimiento) {

		final String resultadoObtenido = validacionesAtributosCorrectoFechas
				.comprobarAtributoCorrectoFechas(datosEntradaFechaProcedimiento.getFechaProcedimiento().toString());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_FECHA_CORRECTO, Constantes.EXITO,
				datosEntradaFechaProcedimiento.getFechaProcedimiento().toString(), Constantes.FECHA_PROCEDIMIENTO);

	}
}
