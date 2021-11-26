package com.sds.service.test.impl.atributos;

import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.DefinicionPruebas;
import com.sds.service.common.Mensajes;
import com.sds.service.common.enumerados.Atributo;
import com.sds.service.common.enumerados.Funcionalidad;
import com.sds.service.registro.model.Registro;
import com.sds.service.test.model.DatosPruebaAtributos;
import com.sds.service.test.util.CrearDatosPruebaAtributos;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosAcentos;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosBlank;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCaracteresEspeciales;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCorrectoAlfanumerico;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEnhe;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEspacios;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMayor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoFechaNacimientoPersonaRegistro {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosEnhe validacionesAtributosEnhe;
	private final ValidacionesAtributosAcentos validacionesAtributosAcentos;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoFechaNacimientoPersonaRegistro() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosEnhe = new ValidacionesAtributosEnhe();
		validacionesAtributosAcentos = new ValidacionesAtributosAcentos();
		validacionesAtributosCaracteresEspeciales = new ValidacionesAtributosCaracteresEspeciales();
		validacionesAtributosEspacios = new ValidacionesAtributosEspacios();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosMayor = new ValidacionesAtributosMayor();
		validacionesAtributosCorrectoAlfanumerico = new ValidacionesAtributosCorrectoAlfanumerico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestRegistroFechaNacimientoPersonaVacio(final Registro datosEntradaRegistroFechaNacimientoPersonaVacio) {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaRegistroFechaNacimientoPersonaVacio.getDatosPersona().getFechaNacP().toString(), Funcionalidad.REGISTRAR, Atributo.FECHA_NACIMIENTO);

		final String resultadoEsperado = CodigosMensajes.FECHA_NACIMIENTO_VACIA + " - "
				+ Mensajes.FECHA_NACIMIENTO_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaRegistroFechaNacimientoPersonaVacio.getDatosPersona().getFechaNacP().toString(),
				Constantes.FECHANACP);
	}

	public DatosPruebaAtributos getTestRegistroFechaNacimientoPersonaNumericoEnhe(final Registro datosEntradaRegistroFechaNacimientoPersonaNumericoEnhe) {

		final String resultadoObtenido = validacionesAtributosEnhe.comprobarAtributoEnhe(
				datosEntradaRegistroFechaNacimientoPersonaNumericoEnhe.getDatosPersona().getFechaNacP().toString(), Funcionalidad.REGISTRAR,
				Atributo.FECHA_NACIMIENTO);

		final String resultadoEsperado = CodigosMensajes.FECHA_NACIMIENTO_NUMERICA_INCORRECTA + " - "
				+ Mensajes.FECHA_NACIMIENTO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ENHE, Constantes.ERROR,
				datosEntradaRegistroFechaNacimientoPersonaNumericoEnhe.getDatosPersona().getFechaNacP().toString(), Constantes.FECHANACP);

	}

	public DatosPruebaAtributos getTestRegistroFechaNacimientoPersonaNumericoAcentos(
			final Registro datosEntradaRegistroFechaNacimientoPersonaNumericoAcentos) {

		final String resultadoObtenido = validacionesAtributosAcentos.comprobarAtributoAcentos(
				datosEntradaRegistroFechaNacimientoPersonaNumericoAcentos.getDatosPersona().getFechaNacP().toString(), Funcionalidad.REGISTRAR,
				Atributo.FECHA_NACIMIENTO);

		final String resultadoEsperado = CodigosMensajes.FECHA_NACIMIENTO_NUMERICA_INCORRECTA + " - "
				+ Mensajes.FECHA_NACIMIENTO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ACENTOS, Constantes.ERROR,
				datosEntradaRegistroFechaNacimientoPersonaNumericoAcentos.getDatosPersona().getFechaNacP().toString(), Constantes.FECHANACP);

	}

	public DatosPruebaAtributos getTestRegistroFechaNacimientoPNumericoCaracteresEspeciales(
			final Registro datosEntradaRegistroFechaNacimientoPNumericoCaracteresEspeciales) {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaRegistroFechaNacimientoPNumericoCaracteresEspeciales.getDatosPersona().getFechaNacP().toString(),
						Funcionalidad.REGISTRAR, Atributo.FECHA_NACIMIENTO);

		final String resultadoEsperado = CodigosMensajes.FECHA_NACIMIENTO_NUMERICA_INCORRECTA + " - "
				+ Mensajes.FECHA_NACIMIENTO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaRegistroFechaNacimientoPNumericoCaracteresEspeciales.getDatosPersona().getFechaNacP().toString(),
				Constantes.FECHANACP);
	}

	public DatosPruebaAtributos getTestRegistroFechaNacimientoPAlfanumericoEspacios(
			final Registro datosEntradaRegistroFechaNacimientoPAlfanumericoEspacios) {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaRegistroFechaNacimientoPAlfanumericoEspacios.getDatosPersona().getFechaNacP().toString(), Funcionalidad.REGISTRAR,
				Atributo.FECHA_NACIMIENTO);

		final String resultadoEsperado = CodigosMensajes.FECHA_NACIMIENTO_NUMERICA_INCORRECTA + " - "
				+ Mensajes.FECHA_NACIMIENTO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ESPACIOS, Constantes.ERROR,
				datosEntradaRegistroFechaNacimientoPAlfanumericoEspacios.getDatosPersona().getFechaNacP().toString(), Constantes.FECHANACP);
	}

	public DatosPruebaAtributos getTestRegistroFechaNacimientoPAlfanumericoMenor8(
			final Registro datosEntradaRegistroFechaNacimientoPAlfanumericoMenor8) {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaRegistroFechaNacimientoPAlfanumericoMenor8.getDatosPersona().getFechaNacP().toString(), Funcionalidad.REGISTRAR,
				Atributo.FECHA_NACIMIENTO, 8);

		final String resultadoEsperado = CodigosMensajes.FECHA_NACIMIENTO_MENOR_QUE_8 + " - "
				+ Mensajes.FECHA_NACIMIENTO_NO_PUEDE_SER_MENOR_QUE_8;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_MENOR_8, Constantes.ERROR,
				datosEntradaRegistroFechaNacimientoPAlfanumericoMenor8.getDatosPersona().getFechaNacP().toString(), Constantes.FECHANACP);
	}

	public DatosPruebaAtributos getTestRegistroFechaNacimientoPAlfanumericoMayor8(
			final Registro datosEntradaRegistroFechaNacimientoPAlfanumericoMayor8) {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaRegistroFechaNacimientoPAlfanumericoMayor8.getDatosPersona().getFechaNacP().toString(), Funcionalidad.REGISTRAR,
				Atributo.FECHA_NACIMIENTO, 8);

		final String resultadoEsperado = CodigosMensajes.FECHA_NACIMIENTO_MAYOR_QUE_8 + " - "
				+ Mensajes.FECHA_NACIMIENTO_NO_PUEDE_SER_MAYOR_QUE_8;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_MAYOR_8, Constantes.ERROR,
				datosEntradaRegistroFechaNacimientoPAlfanumericoMayor8.getDatosPersona().getFechaNacP().toString(), Constantes.FECHANACP);
	}

	public DatosPruebaAtributos getTestRegistroFechaNacimientoPersonaCorrectoNumerico(final Registro datosEntradaRegistroFechaNacimientoPersona) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(datosEntradaRegistroFechaNacimientoPersona.getDatosPersona().getFechaNacP().toString());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_FECHA_CORRECTO, Constantes.EXITO,
				datosEntradaRegistroFechaNacimientoPersona.getDatosPersona().getFechaNacP().toString(), Constantes.FECHANACP);

	}
}
