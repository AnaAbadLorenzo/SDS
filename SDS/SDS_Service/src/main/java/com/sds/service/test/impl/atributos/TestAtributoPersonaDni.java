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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCorrectoDNI;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEnhe;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEspacios;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMayor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoPersonaDni {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosEnhe validacionesAtributosEnhe;
	private final ValidacionesAtributosAcentos validacionesAtributosAcentos;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoDNI validacionesAtributosCorrectoDNI;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoPersonaDni() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosEnhe = new ValidacionesAtributosEnhe();
		validacionesAtributosAcentos = new ValidacionesAtributosAcentos();
		validacionesAtributosCaracteresEspeciales = new ValidacionesAtributosCaracteresEspeciales();
		validacionesAtributosEspacios = new ValidacionesAtributosEspacios();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosMayor = new ValidacionesAtributosMayor();
		validacionesAtributosCorrectoDNI = new ValidacionesAtributosCorrectoDNI();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestDniPersonaVacio(final PersonaEntity datosEntradaDniPersonaVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaDniPersonaVacio.getDniP(), Funcionalidad.GESTION_PERSONAS, Atributo.DNI_PERSONA);

		final String resultadoEsperado = CodigosMensajes.DNI_PERSONA_VACIO + " - "
				+ Mensajes.DNI_PERSONA_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaDniPersonaVacio.getDniP(), Constantes.DNIP);
	}

	public DatosPruebaAtributos getTestDniPersonaAlfanumericoEnhe(
			final PersonaEntity datosEntradaDniPersonaAlfanumericoEnhe) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEnhe.comprobarAtributoEnhe(
				datosEntradaDniPersonaAlfanumericoEnhe.getDniP(), Funcionalidad.GESTION_PERSONAS, Atributo.DNI_PERSONA);

		final String resultadoEsperado = CodigosMensajes.DNI_PERSONA_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.DNI_PERSONA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ENHE, Constantes.ERROR, datosEntradaDniPersonaAlfanumericoEnhe.getDniP(),
				Constantes.DNIP);

	}

	public DatosPruebaAtributos getTestDniPersonaAlfanumericoAcentos(
			final PersonaEntity datosEntradaDniPersonaAlfanumericoAcentos) throws ParseException {

		final String resultadoObtenido = validacionesAtributosAcentos.comprobarAtributoAcentos(
				datosEntradaDniPersonaAlfanumericoAcentos.getDniP(), Funcionalidad.GESTION_PERSONAS,
				Atributo.DNI_PERSONA);

		final String resultadoEsperado = CodigosMensajes.DNI_PERSONA_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.DNI_PERSONA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ACENTOS, Constantes.ERROR,
				datosEntradaDniPersonaAlfanumericoAcentos.getDniP(), Constantes.DNIP);

	}

	public DatosPruebaAtributos getTestDniPAlfanumericoCaracteresEspeciales(
			final PersonaEntity datosEntradaDniPAlfanumericoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(datosEntradaDniPAlfanumericoCaracteresEspeciales.getDniP(),
						Funcionalidad.GESTION_PERSONAS, Atributo.DNI_PERSONA);

		final String resultadoEsperado = CodigosMensajes.DNI_PERSONA_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.DNI_PERSONA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaDniPAlfanumericoCaracteresEspeciales.getDniP(), Constantes.DNIP);
	}

	public DatosPruebaAtributos getTestDniPAlfanumericoEspacios(
			final PersonaEntity datosEntradaDniPAlfanumericoEspacios) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaDniPAlfanumericoEspacios.getDniP(), Funcionalidad.GESTION_PERSONAS, Atributo.DNI_PERSONA);

		final String resultadoEsperado = CodigosMensajes.DNI_PERSONA_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.DNI_PERSONA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ESPACIOS, Constantes.ERROR,
				datosEntradaDniPAlfanumericoEspacios.getDniP(), Constantes.DNIP);
	}

	public DatosPruebaAtributos getTestDniPAlfanumericoMenor9(final PersonaEntity datosEntradaDniPAlfanumericoMenor9)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaDniPAlfanumericoMenor9.getDniP(), Funcionalidad.GESTION_PERSONAS, Atributo.DNI_PERSONA, 9);

		final String resultadoEsperado = CodigosMensajes.DNI_PERSONA_MENOR_QUE_9 + " - "
				+ Mensajes.DNI_NO_PUEDE_SER_MENOR_QUE_9;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_9, Constantes.ERROR, datosEntradaDniPAlfanumericoMenor9.getDniP(),
				Constantes.DNIP);
	}

	public DatosPruebaAtributos getTestDniPAlfanumericoMayor9(final PersonaEntity datosEntradaDniPAlfanumericoMayor9)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaDniPAlfanumericoMayor9.getDniP(), Funcionalidad.GESTION_PERSONAS, Atributo.DNI_PERSONA, 9);

		final String resultadoEsperado = CodigosMensajes.DNI_PERSONA_MAYOR_QUE_9 + " - "
				+ Mensajes.DNI_NO_PUEDE_SER_MAYOR_QUE_9;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MAYOR_9, Constantes.ERROR, datosEntradaDniPAlfanumericoMayor9.getDniP(),
				Constantes.DNIP);
	}

	public DatosPruebaAtributos getTestDniPCorrecto(final PersonaEntity datosEntradaDniP) {

		final String resultadoObtenido = validacionesAtributosCorrectoDNI
				.comprobarAtributoCorrectoDNI(datosEntradaDniP.getDniP());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.IDENTIFICADOR_CORRECTO, Constantes.EXITO, datosEntradaDniP.getDniP(),
				Constantes.DNIP);

	}

	public DatosPruebaAtributos getTestDniPCorrectoValido(final PersonaEntity datosEntradaDniP) {

		final String resultadoObtenido = validacionesAtributosCorrectoDNI
				.comprobarAtributoDNIValido(datosEntradaDniP.getDniP());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.IDENTIFICADOR_CORRECTO, Constantes.EXITO, datosEntradaDniP.getDniP(),
				Constantes.DNIP);

	}

	public DatosPruebaAtributos getTestDniPCorrectoNoValido(final PersonaEntity datosEntradaDniP) {

		final String resultadoObtenido = validacionesAtributosCorrectoDNI
				.comprobarAtributoDNIValido(datosEntradaDniP.getDniP());

		final String resultadoEsperado = CodigosMensajes.DNI_PERSONA_NO_VALIDO + " - " + Mensajes.DNI_NO_ES_VALIDO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.IDENTIFICADOR_INCORRECTO, Constantes.ERROR, datosEntradaDniP.getDniP(),
				Constantes.DNIP);
	}
}
