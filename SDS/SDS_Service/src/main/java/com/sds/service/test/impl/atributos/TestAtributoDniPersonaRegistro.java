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

public class TestAtributoDniPersonaRegistro {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosEnhe validacionesAtributosEnhe;
	private final ValidacionesAtributosAcentos validacionesAtributosAcentos;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoDniPersonaRegistro() {
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

	public DatosPruebaAtributos getTestRegistroDniPersonaVacio(final Registro datosEntradaRegistroDniPersonaVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaRegistroDniPersonaVacio.getDatosPersona().getDniP(), Funcionalidad.REGISTRAR,
				Atributo.DNI_PERSONA);

		final String resultadoEsperado = CodigosMensajes.DNI_PERSONA_VACIO + " - "
				+ Mensajes.DNI_PERSONA_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				datosEntradaRegistroDniPersonaVacio.getDatosPersona().getDniP(), Constantes.DNIP);
	}

	public DatosPruebaAtributos getTestRegistroDniPersonaAlfanumericoEnhe(
			final Registro datosEntradaRegistroDniPersonaAlfanumericoEnhe) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEnhe.comprobarAtributoEnhe(
				datosEntradaRegistroDniPersonaAlfanumericoEnhe.getDatosPersona().getDniP(), Funcionalidad.REGISTRAR,
				Atributo.DNI_PERSONA);

		final String resultadoEsperado = CodigosMensajes.DNI_PERSONA_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.DNI_PERSONA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ENHE, Constantes.ERROR,
				datosEntradaRegistroDniPersonaAlfanumericoEnhe.getDatosPersona().getDniP(), Constantes.DNIP);

	}

	public DatosPruebaAtributos getTestRegistroDniPersonaAlfanumericoAcentos(
			final Registro datosEntradaRegistroDniPersonaAlfanumericoAcentos) throws ParseException {

		final String resultadoObtenido = validacionesAtributosAcentos.comprobarAtributoAcentos(
				datosEntradaRegistroDniPersonaAlfanumericoAcentos.getDatosPersona().getDniP(), Funcionalidad.REGISTRAR,
				Atributo.DNI_PERSONA);

		final String resultadoEsperado = CodigosMensajes.DNI_PERSONA_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.DNI_PERSONA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ACENTOS, Constantes.ERROR,
				datosEntradaRegistroDniPersonaAlfanumericoAcentos.getDatosPersona().getDniP(), Constantes.DNIP);

	}

	public DatosPruebaAtributos getTestRegistroDniPAlfanumericoCaracteresEspeciales(
			final Registro datosEntradaRegistroDniPAlfanumericoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaRegistroDniPAlfanumericoCaracteresEspeciales.getDatosPersona().getDniP(),
						Funcionalidad.REGISTRAR, Atributo.DNI_PERSONA);

		final String resultadoEsperado = CodigosMensajes.DNI_PERSONA_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.DNI_PERSONA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaRegistroDniPAlfanumericoCaracteresEspeciales.getDatosPersona().getDniP(), Constantes.DNIP);
	}

	public DatosPruebaAtributos getTestRegistroDniPAlfanumericoEspacios(
			final Registro datosEntradaRegistroDniPAlfanumericoEspacios) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaRegistroDniPAlfanumericoEspacios.getDatosPersona().getDniP(), Funcionalidad.REGISTRAR,
				Atributo.DNI_PERSONA);

		final String resultadoEsperado = CodigosMensajes.DNI_PERSONA_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.DNI_PERSONA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ESPACIOS, Constantes.ERROR,
				datosEntradaRegistroDniPAlfanumericoEspacios.getDatosPersona().getDniP(), Constantes.DNIP);
	}

	public DatosPruebaAtributos getTestRegistroDniPAlfanumericoMenor9(
			final Registro datosEntradaRegistroDniPAlfanumericoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaRegistroDniPAlfanumericoMenor3.getDatosPersona().getDniP(), Funcionalidad.REGISTRAR,
				Atributo.DNI_PERSONA, 9);

		final String resultadoEsperado = CodigosMensajes.DNI_PERSONA_MENOR_QUE_9 + " - "
				+ Mensajes.DNI_NO_PUEDE_SER_MENOR_QUE_9;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_9, Constantes.ERROR,
				datosEntradaRegistroDniPAlfanumericoMenor3.getDatosPersona().getDniP(), Constantes.DNIP);
	}

	public DatosPruebaAtributos getTestRegistroDniPAlfanumericoMayor9(
			final Registro datosEntradaRegistroDniPAlfanumericoMayor9) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaRegistroDniPAlfanumericoMayor9.getDatosPersona().getDniP(), Funcionalidad.REGISTRAR,
				Atributo.DNI_PERSONA, 9);

		final String resultadoEsperado = CodigosMensajes.DNI_PERSONA_MAYOR_QUE_9 + " - "
				+ Mensajes.DNI_NO_PUEDE_SER_MAYOR_QUE_9;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MAYOR_9, Constantes.ERROR,
				datosEntradaRegistroDniPAlfanumericoMayor9.getDatosPersona().getDniP(), Constantes.DNIP);
	}

	public DatosPruebaAtributos getTestRegistroDniPCorrectoAlfanumerico(final Registro datosEntradaRegistroDniP) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(datosEntradaRegistroDniP.getDatosPersona().getDniP());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.IDENTIFICADOR_CORRECTO, Constantes.EXITO,
				datosEntradaRegistroDniP.getDatosPersona().getDniP(), Constantes.DNIP);

	}
}
