package com.sds.service.test.impl.atributos;

import java.text.ParseException;

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

public class TestAtributoTelefonoPersonaRegistro {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosEnhe validacionesAtributosEnhe;
	private final ValidacionesAtributosAcentos validacionesAtributosAcentos;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoTelefonoPersonaRegistro() {
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

	public DatosPruebaAtributos getTestRegistroTelefonoPersonaVacio(
			final Registro datosEntradaRegistroTelefonoPersonaVacio) throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaRegistroTelefonoPersonaVacio.getDatosPersona().getTelefonoP(), Funcionalidad.REGISTRAR,
				Atributo.TELEFONO);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_VACIO + " - " + Mensajes.TELEFONO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				datosEntradaRegistroTelefonoPersonaVacio.getDatosPersona().getTelefonoP(), Constantes.TELEFONOP);
	}

	public DatosPruebaAtributos getTestRegistroTelefonoPersonaNumericoEnhe(
			final Registro datosEntradaRegistroTelefonoPersonaNumericoEnhe) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEnhe.comprobarAtributoEnhe(
				datosEntradaRegistroTelefonoPersonaNumericoEnhe.getDatosPersona().getTelefonoP(),
				Funcionalidad.REGISTRAR, Atributo.TELEFONO);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_NUMERICO_INCORRECTO + " - "
				+ Mensajes.TELEFONO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ENHE, Constantes.ERROR,
				datosEntradaRegistroTelefonoPersonaNumericoEnhe.getDatosPersona().getTelefonoP(), Constantes.TELEFONOP);

	}

	public DatosPruebaAtributos getTestRegistroTelefonoPersonaNumericoAcentos(
			final Registro datosEntradaRegistroTelefonoPersonaNumericoAcentos) throws ParseException {

		final String resultadoObtenido = validacionesAtributosAcentos.comprobarAtributoAcentos(
				datosEntradaRegistroTelefonoPersonaNumericoAcentos.getDatosPersona().getTelefonoP(),
				Funcionalidad.REGISTRAR, Atributo.TELEFONO);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_NUMERICO_INCORRECTO + " - "
				+ Mensajes.TELEFONO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ACENTOS, Constantes.ERROR,
				datosEntradaRegistroTelefonoPersonaNumericoAcentos.getDatosPersona().getTelefonoP(),
				Constantes.TELEFONOP);

	}

	public DatosPruebaAtributos getTestRegistroTelefonoPNumericoCaracteresEspeciales(
			final Registro datosEntradaRegistroTelefonoPNumericoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaRegistroTelefonoPNumericoCaracteresEspeciales.getDatosPersona().getTelefonoP(),
						Funcionalidad.REGISTRAR, Atributo.TELEFONO);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_NUMERICO_INCORRECTO + " - "
				+ Mensajes.TELEFONO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaRegistroTelefonoPNumericoCaracteresEspeciales.getDatosPersona().getTelefonoP(),
				Constantes.TELEFONOP);
	}

	public DatosPruebaAtributos getTestRegistroTelefonoPNumericoEspacios(
			final Registro datosEntradaRegistroTelefonoPAlfanumericoEspacios) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaRegistroTelefonoPAlfanumericoEspacios.getDatosPersona().getTelefonoP(),
				Funcionalidad.REGISTRAR, Atributo.TELEFONO);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_NUMERICO_INCORRECTO + " - "
				+ Mensajes.TELEFONO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ESPACIOS, Constantes.ERROR,
				datosEntradaRegistroTelefonoPAlfanumericoEspacios.getDatosPersona().getTelefonoP(),
				Constantes.TELEFONOP);
	}

	public DatosPruebaAtributos getTestRegistroTelefonoPNumericoMenor9(
			final Registro datosEntradaRegistroTelefonoPNumericoMenor9) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaRegistroTelefonoPNumericoMenor9.getDatosPersona().getTelefonoP(), Funcionalidad.REGISTRAR,
				Atributo.TELEFONO, 9);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_MENOR_QUE_9 + " - "
				+ Mensajes.TELEFONO_NO_PUEDE_SER_MENOR_QUE_9;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_MENOR_9, Constantes.ERROR,
				datosEntradaRegistroTelefonoPNumericoMenor9.getDatosPersona().getTelefonoP(), Constantes.TELEFONOP);
	}

	public DatosPruebaAtributos getTestRegistroTelefonoPNumericoMayor9(
			final Registro datosEntradaRegistroTelefonoPNumericoMayor9) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaRegistroTelefonoPNumericoMayor9.getDatosPersona().getTelefonoP(), Funcionalidad.REGISTRAR,
				Atributo.TELEFONO, 9);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_MAYOR_QUE_9 + " - "
				+ Mensajes.TELEFONO_NO_PUEDE_SER_MAYOR_QUE_9;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_MAYOR_9, Constantes.ERROR,
				datosEntradaRegistroTelefonoPNumericoMayor9.getDatosPersona().getTelefonoP(), Constantes.TELEFONOP);
	}

	public DatosPruebaAtributos getTestRegistroTelefonoPersonaCorrectoNumerico(
			final Registro datosEntradaRegistroTelefonoPersona) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(
						datosEntradaRegistroTelefonoPersona.getDatosPersona().getTelefonoP());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_TELEFONO_CORRECTO, Constantes.EXITO,
				datosEntradaRegistroTelefonoPersona.getDatosPersona().getTelefonoP(), Constantes.TELEFONOP);

	}
}
