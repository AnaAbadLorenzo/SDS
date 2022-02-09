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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCorrectoEmail;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEnhe;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEspacios;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMayor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoEmailPersona {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosEnhe validacionesAtributosEnhe;
	private final ValidacionesAtributosAcentos validacionesAtributosAcentos;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoEmail validacionesAtributosCorrectoEmail;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoEmailPersona() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosEnhe = new ValidacionesAtributosEnhe();
		validacionesAtributosAcentos = new ValidacionesAtributosAcentos();
		validacionesAtributosEspacios = new ValidacionesAtributosEspacios();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosMayor = new ValidacionesAtributosMayor();
		validacionesAtributosCorrectoEmail = new ValidacionesAtributosCorrectoEmail();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestEmailPersonaVacio(final PersonaEntity datosEntradaEmailPersonaVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaEmailPersonaVacio.getEmailP(), Funcionalidad.GESTION_PERSONAS, Atributo.EMAIL);

		final String resultadoEsperado = CodigosMensajes.EMAIL_VACIO + " - " + Mensajes.EMAIL_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaEmailPersonaVacio.getEmailP(),
				Constantes.EMAILP);
	}

	public DatosPruebaAtributos getTestEmailPersonaAlfanumericoEnhe(
			final PersonaEntity datosEntradaEmailPersonaAlfanumericoEnhe) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEnhe.comprobarAtributoEnhe(
				datosEntradaEmailPersonaAlfanumericoEnhe.getEmailP(), Funcionalidad.GESTION_PERSONAS, Atributo.EMAIL);

		final String resultadoEsperado = CodigosMensajes.EMAIL_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.EMAIL_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ENHE, Constantes.ERROR,
				datosEntradaEmailPersonaAlfanumericoEnhe.getEmailP(), Constantes.EMAILP);

	}

	public DatosPruebaAtributos getTestEmailPersonaAlfanumericoAcentos(
			final PersonaEntity datosEntradaEmailPersonaAlfanumericoAcentos) throws ParseException {

		final String resultadoObtenido = validacionesAtributosAcentos.comprobarAtributoAcentos(
				datosEntradaEmailPersonaAlfanumericoAcentos.getEmailP(), Funcionalidad.GESTION_PERSONAS,
				Atributo.EMAIL);

		final String resultadoEsperado = CodigosMensajes.EMAIL_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.EMAIL_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ACENTOS, Constantes.ERROR,
				datosEntradaEmailPersonaAlfanumericoAcentos.getEmailP(), Constantes.EMAILP);

	}

	public DatosPruebaAtributos getTestEmailPAlfanumericoEspacios(
			final PersonaEntity datosEntradaEmailPAlfanumericoEspacios) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaEmailPAlfanumericoEspacios.getEmailP(), Funcionalidad.GESTION_PERSONAS, Atributo.EMAIL);

		final String resultadoEsperado = CodigosMensajes.EMAIL_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.EMAIL_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ESPACIOS, Constantes.ERROR,
				datosEntradaEmailPAlfanumericoEspacios.getEmailP(), Constantes.EMAILP);
	}

	public DatosPruebaAtributos getTestEmailPAlfanumericoMenor4(
			final PersonaEntity datosEntradaEmailPAlfanumericoMenor4) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaEmailPAlfanumericoMenor4.getEmailP(), Funcionalidad.GESTION_PERSONAS, Atributo.EMAIL, 4);

		final String resultadoEsperado = CodigosMensajes.EMAIL_MENOR_QUE_4 + " - "
				+ Mensajes.EMAIL_NO_PUEDE_SER_MENOR_QUE_4;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_4, Constantes.ERROR,
				datosEntradaEmailPAlfanumericoMenor4.getEmailP(), Constantes.EMAILP);
	}

	public DatosPruebaAtributos getTestEmailPAlfanumericoMayor128(
			final PersonaEntity datosEntradaEmailPAlfanumericoMayor128) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaEmailPAlfanumericoMayor128.getEmailP(), Funcionalidad.GESTION_PERSONAS, Atributo.EMAIL, 48);

		final String resultadoEsperado = CodigosMensajes.EMAIL_MAYOR_QUE_48 + " - "
				+ Mensajes.EMAIL_NO_PUEDE_SER_MAYOR_QUE_48;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MAYOR_48, Constantes.ERROR,
				datosEntradaEmailPAlfanumericoMayor128.getEmailP(), Constantes.EMAILP);
	}

	public DatosPruebaAtributos getTestEmailPCorrectoAlfanumerico(final PersonaEntity datosEntradaEmailP) {

		final String resultadoObtenido = validacionesAtributosCorrectoEmail
				.comprobarAtributoCorrectoEmail(datosEntradaEmailP.getEmailP());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CORRECTO, Constantes.EXITO, datosEntradaEmailP.getEmailP(),
				Constantes.EMAILP);

	}
}
