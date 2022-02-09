package com.sds.service.test.impl.atributos;

import java.text.ParseException;

import com.sds.model.PersonaEntity;
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

public class TestAtributoFechaNacP {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosEnhe validacionesAtributosEnhe;
	private final ValidacionesAtributosAcentos validacionesAtributosAcentos;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoFechas validacionesAtributosCorrectoFechas;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoFechaNacP() {
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

	public DatosPruebaAtributos getTestFechaNacimientoPersonaVacio(
			final PersonaEntity datosEntradaFechaNacimientoPersonaVacio) throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaFechaNacimientoPersonaVacio.getFechaNacP().toString(), Funcionalidad.GESTION_PERSONAS,
				Atributo.FECHA_NACIMIENTO);

		final String resultadoEsperado = CodigosMensajes.FECHA_NACIMIENTO_VACIA + " - "
				+ Mensajes.FECHA_NACIMIENTO_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				datosEntradaFechaNacimientoPersonaVacio.getFechaNacP().toString(), Constantes.FECHANACP);
	}

	public DatosPruebaAtributos getTestFechaNacimientoPersonaNumericoEnhe(
			final PersonaEntity datosEntradaFechaNacimientoPersonaNumericoEnhe) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEnhe.comprobarAtributoEnhe(
				datosEntradaFechaNacimientoPersonaNumericoEnhe.getFechaNacP().toString(),
				Funcionalidad.GESTION_PERSONAS, Atributo.FECHA_NACIMIENTO);

		final String resultadoEsperado = CodigosMensajes.FECHA_NACIMIENTO_NUMERICA_INCORRECTA + " - "
				+ Mensajes.FECHA_NACIMIENTO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ENHE, Constantes.ERROR,
				datosEntradaFechaNacimientoPersonaNumericoEnhe.getFechaNacP().toString(), Constantes.FECHANACP);

	}

	public DatosPruebaAtributos getTestFechaNacimientoPersonaNumericoAcentos(
			final PersonaEntity datosEntradaFechaNacimientoPersonaNumericoAcentos) throws ParseException {

		final String resultadoObtenido = validacionesAtributosAcentos.comprobarAtributoAcentos(
				datosEntradaFechaNacimientoPersonaNumericoAcentos.getFechaNacP().toString(),
				Funcionalidad.GESTION_PERSONAS, Atributo.FECHA_NACIMIENTO);

		final String resultadoEsperado = CodigosMensajes.FECHA_NACIMIENTO_NUMERICA_INCORRECTA + " - "
				+ Mensajes.FECHA_NACIMIENTO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ACENTOS, Constantes.ERROR,
				datosEntradaFechaNacimientoPersonaNumericoAcentos.getFechaNacP().toString(), Constantes.FECHANACP);

	}

	public DatosPruebaAtributos getTestFechaNacimientoPNumericoCaracteresEspeciales(
			final PersonaEntity datosEntradaFechaNacimientoPNumericoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaFechaNacimientoPNumericoCaracteresEspeciales.getFechaNacP().toString(),
						Funcionalidad.GESTION_PERSONAS, Atributo.FECHA_NACIMIENTO);

		final String resultadoEsperado = CodigosMensajes.FECHA_NACIMIENTO_NUMERICA_INCORRECTA + " - "
				+ Mensajes.FECHA_NACIMIENTO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaFechaNacimientoPNumericoCaracteresEspeciales.getFechaNacP().toString(),
				Constantes.FECHANACP);
	}

	public DatosPruebaAtributos getTestFechaNacimientoPAlfanumericoEspacios(
			final PersonaEntity datosEntradaFechaNacimientoPAlfanumericoEspacios) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaFechaNacimientoPAlfanumericoEspacios.getFechaNacP().toString(),
				Funcionalidad.GESTION_PERSONAS, Atributo.FECHA_NACIMIENTO);

		final String resultadoEsperado = CodigosMensajes.FECHA_NACIMIENTO_NUMERICA_INCORRECTA + " - "
				+ Mensajes.FECHA_NACIMIENTO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ESPACIOS, Constantes.ERROR,
				datosEntradaFechaNacimientoPAlfanumericoEspacios.getFechaNacP().toString(), Constantes.FECHANACP);
	}

	public DatosPruebaAtributos getTestFechaNacimientoPAlfanumericoMenor8(
			final PersonaEntity datosEntradaFechaNacimientoPAlfanumericoMenor8) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaFechaNacimientoPAlfanumericoMenor8.getFechaNacP().toString(),
				Funcionalidad.GESTION_PERSONAS, Atributo.FECHA_NACIMIENTO, 8);

		final String resultadoEsperado = CodigosMensajes.FECHA_NACIMIENTO_MENOR_QUE_8 + " - "
				+ Mensajes.FECHA_NACIMIENTO_NO_PUEDE_SER_MENOR_QUE_8;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_MENOR_8, Constantes.ERROR,
				datosEntradaFechaNacimientoPAlfanumericoMenor8.getFechaNacP().toString(), Constantes.FECHANACP);
	}

	public DatosPruebaAtributos getTestFechaNacimientoPAlfanumericoMayor8(
			final PersonaEntity datosEntradaFechaNacimientoPAlfanumericoMayor8) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaFechaNacimientoPAlfanumericoMayor8.getFechaNacP().toString(),
				Funcionalidad.GESTION_PERSONAS, Atributo.FECHA_NACIMIENTO, 8);

		final String resultadoEsperado = CodigosMensajes.FECHA_NACIMIENTO_MAYOR_QUE_8 + " - "
				+ Mensajes.FECHA_NACIMIENTO_NO_PUEDE_SER_MAYOR_QUE_8;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_MAYOR_8, Constantes.ERROR,
				datosEntradaFechaNacimientoPAlfanumericoMayor8.getFechaNacP().toString(), Constantes.FECHANACP);
	}

	public DatosPruebaAtributos getTestFechaNacimientoPersonaCorrectoNumerico(
			final PersonaEntity datosEntradaFechaNacimientoPersona) {

		final String resultadoObtenido = validacionesAtributosCorrectoFechas
				.comprobarAtributoCorrectoFechas(datosEntradaFechaNacimientoPersona.getFechaNacP().toString());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_FECHA_CORRECTO, Constantes.EXITO,
				datosEntradaFechaNacimientoPersona.getFechaNacP().toString(), Constantes.FECHANACP);

	}
}
