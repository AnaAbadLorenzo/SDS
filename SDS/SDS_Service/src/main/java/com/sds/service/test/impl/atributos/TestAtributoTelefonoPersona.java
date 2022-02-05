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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCorrectoAlfanumerico;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEnhe;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEspacios;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMayor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoTelefonoPersona {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosEnhe validacionesAtributosEnhe;
	private final ValidacionesAtributosAcentos validacionesAtributosAcentos;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoTelefonoPersona() {
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

	public DatosPruebaAtributos getTestTelefonoPersonaVacio(final PersonaEntity datosEntradaTelefonoPersonaVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaTelefonoPersonaVacio.getTelefonoP(), Funcionalidad.GESTION_PERSONAS, Atributo.TELEFONO);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_VACIO + " - " + Mensajes.TELEFONO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaTelefonoPersonaVacio.getTelefonoP(),
				Constantes.TELEFONOP);
	}

	public DatosPruebaAtributos getTestTelefonoPersonaNumericoEnhe(
			final PersonaEntity datosEntradaTelefonoPersonaNumericoEnhe) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEnhe.comprobarAtributoEnhe(
				datosEntradaTelefonoPersonaNumericoEnhe.getTelefonoP(), Funcionalidad.GESTION_PERSONAS,
				Atributo.TELEFONO);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_NUMERICO_INCORRECTO + " - "
				+ Mensajes.TELEFONO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ENHE, Constantes.ERROR,
				datosEntradaTelefonoPersonaNumericoEnhe.getTelefonoP(), Constantes.TELEFONOP);

	}

	public DatosPruebaAtributos getTestTelefonoPersonaNumericoAcentos(
			final PersonaEntity datosEntradaTelefonoPersonaNumericoAcentos) throws ParseException {

		final String resultadoObtenido = validacionesAtributosAcentos.comprobarAtributoAcentos(
				datosEntradaTelefonoPersonaNumericoAcentos.getTelefonoP(), Funcionalidad.GESTION_PERSONAS,
				Atributo.TELEFONO);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_NUMERICO_INCORRECTO + " - "
				+ Mensajes.TELEFONO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ACENTOS, Constantes.ERROR,
				datosEntradaTelefonoPersonaNumericoAcentos.getTelefonoP(), Constantes.TELEFONOP);

	}

	public DatosPruebaAtributos getTestTelefonoPNumericoCaracteresEspeciales(
			final PersonaEntity datosEntradaTelefonoPNumericoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(datosEntradaTelefonoPNumericoCaracteresEspeciales.getTelefonoP(),
						Funcionalidad.GESTION_PERSONAS, Atributo.TELEFONO);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_NUMERICO_INCORRECTO + " - "
				+ Mensajes.TELEFONO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaTelefonoPNumericoCaracteresEspeciales.getTelefonoP(), Constantes.TELEFONOP);
	}

	public DatosPruebaAtributos getTestTelefonoPNumericoEspacios(
			final PersonaEntity datosEntradaTelefonoPAlfanumericoEspacios) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaTelefonoPAlfanumericoEspacios.getTelefonoP(), Funcionalidad.GESTION_PERSONAS,
				Atributo.TELEFONO);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_NUMERICO_INCORRECTO + " - "
				+ Mensajes.TELEFONO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ESPACIOS, Constantes.ERROR,
				datosEntradaTelefonoPAlfanumericoEspacios.getTelefonoP(), Constantes.TELEFONOP);
	}

	public DatosPruebaAtributos getTestTelefonoPNumericoMenor9(final PersonaEntity datosEntradaTelefonoPNumericoMenor9)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaTelefonoPNumericoMenor9.getTelefonoP(), Funcionalidad.GESTION_PERSONAS, Atributo.TELEFONO,
				9);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_MENOR_QUE_9 + " - "
				+ Mensajes.TELEFONO_NO_PUEDE_SER_MENOR_QUE_9;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_MENOR_9, Constantes.ERROR,
				datosEntradaTelefonoPNumericoMenor9.getTelefonoP(), Constantes.TELEFONOP);
	}

	public DatosPruebaAtributos getTestTelefonoPNumericoMayor9(final PersonaEntity datosEntradaTelefonoPNumericoMayor9)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaTelefonoPNumericoMayor9.getTelefonoP(), Funcionalidad.GESTION_PERSONAS, Atributo.TELEFONO,
				9);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_MAYOR_QUE_9 + " - "
				+ Mensajes.TELEFONO_NO_PUEDE_SER_MAYOR_QUE_9;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_MAYOR_9, Constantes.ERROR,
				datosEntradaTelefonoPNumericoMayor9.getTelefonoP(), Constantes.TELEFONOP);
	}

	public DatosPruebaAtributos getTestTelefonoPersonaCorrectoNumerico(
			final PersonaEntity datosEntradaTelefonoPersona) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(datosEntradaTelefonoPersona.getTelefonoP());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_TELEFONO_CORRECTO, Constantes.EXITO,
				datosEntradaTelefonoPersona.getTelefonoP(), Constantes.TELEFONOP);

	}
}
