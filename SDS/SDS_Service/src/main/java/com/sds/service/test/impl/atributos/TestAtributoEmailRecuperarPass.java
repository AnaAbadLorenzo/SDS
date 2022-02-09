package com.sds.service.test.impl.atributos;

import java.text.ParseException;

import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.DefinicionPruebas;
import com.sds.service.common.Mensajes;
import com.sds.service.common.enumerados.Atributo;
import com.sds.service.common.enumerados.Funcionalidad;
import com.sds.service.login.model.RecuperarPass;
import com.sds.service.test.model.DatosPruebaAtributos;
import com.sds.service.test.util.CrearDatosPruebaAtributos;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosAcentos;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosBlank;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCorrectoEmail;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEnhe;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEspacios;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMayor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoEmailRecuperarPass {
	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosEnhe validacionesAtributosEnhe;
	private final ValidacionesAtributosAcentos validacionesAtributosAcentos;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoEmail validacionesAtributosCorrectoEmail;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoEmailRecuperarPass() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosEnhe = new ValidacionesAtributosEnhe();
		validacionesAtributosAcentos = new ValidacionesAtributosAcentos();
		validacionesAtributosEspacios = new ValidacionesAtributosEspacios();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosMayor = new ValidacionesAtributosMayor();
		validacionesAtributosCorrectoEmail = new ValidacionesAtributosCorrectoEmail();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestEmailPersonaVacio(final RecuperarPass datosEntradaEmailPersonaVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaEmailPersonaVacio.getEmailUsuario(), Funcionalidad.RECUPERAR_PASS, Atributo.EMAIL);

		final String resultadoEsperado = CodigosMensajes.EMAIL_VACIO + " - " + Mensajes.EMAIL_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaEmailPersonaVacio.getEmailUsuario(),
				Constantes.EMAILP);
	}

	public DatosPruebaAtributos getTestEmailPersonaAlfanumericoEnhe(
			final RecuperarPass datosEntradaEmailPersonaAlfanumericoEnhe) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEnhe.comprobarAtributoEnhe(
				datosEntradaEmailPersonaAlfanumericoEnhe.getEmailUsuario(), Funcionalidad.RECUPERAR_PASS,
				Atributo.EMAIL);

		final String resultadoEsperado = CodigosMensajes.EMAIL_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.EMAIL_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ENHE, Constantes.ERROR,
				datosEntradaEmailPersonaAlfanumericoEnhe.getEmailUsuario(), Constantes.EMAILP);

	}

	public DatosPruebaAtributos getTestEmailPersonaAlfanumericoAcentos(
			final RecuperarPass datosEntradaEmailPersonaAlfanumericoAcentos) throws ParseException {

		final String resultadoObtenido = validacionesAtributosAcentos.comprobarAtributoAcentos(
				datosEntradaEmailPersonaAlfanumericoAcentos.getEmailUsuario(), Funcionalidad.RECUPERAR_PASS,
				Atributo.EMAIL);

		final String resultadoEsperado = CodigosMensajes.EMAIL_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.EMAIL_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ACENTOS, Constantes.ERROR,
				datosEntradaEmailPersonaAlfanumericoAcentos.getEmailUsuario(), Constantes.EMAILP);

	}

	public DatosPruebaAtributos getTestEmailPAlfanumericoEspacios(
			final RecuperarPass datosEntradaEmailPAlfanumericoEspacios) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaEmailPAlfanumericoEspacios.getEmailUsuario(), Funcionalidad.RECUPERAR_PASS, Atributo.EMAIL);

		final String resultadoEsperado = CodigosMensajes.EMAIL_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.EMAIL_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ESPACIOS, Constantes.ERROR,
				datosEntradaEmailPAlfanumericoEspacios.getEmailUsuario(), Constantes.EMAILP);
	}

	public DatosPruebaAtributos getTestEmailPAlfanumericoMenor4(
			final RecuperarPass datosEntradaEmailPAlfanumericoMenor4) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaEmailPAlfanumericoMenor4.getEmailUsuario(), Funcionalidad.RECUPERAR_PASS, Atributo.EMAIL,
				4);

		final String resultadoEsperado = CodigosMensajes.EMAIL_MENOR_QUE_4 + " - "
				+ Mensajes.EMAIL_NO_PUEDE_SER_MENOR_QUE_4;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_4, Constantes.ERROR,
				datosEntradaEmailPAlfanumericoMenor4.getEmailUsuario(), Constantes.EMAILP);
	}

	public DatosPruebaAtributos getTestEmailPAlfanumericoMayor128(
			final RecuperarPass datosEntradaEmailPAlfanumericoMayor128) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaEmailPAlfanumericoMayor128.getEmailUsuario(), Funcionalidad.RECUPERAR_PASS, Atributo.EMAIL,
				48);

		final String resultadoEsperado = CodigosMensajes.EMAIL_MAYOR_QUE_48 + " - "
				+ Mensajes.EMAIL_NO_PUEDE_SER_MAYOR_QUE_48;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MAYOR_48, Constantes.ERROR,
				datosEntradaEmailPAlfanumericoMayor128.getEmailUsuario(), Constantes.EMAILP);
	}

	public DatosPruebaAtributos getTestEmailPCorrectoAlfanumerico(final RecuperarPass datosEntradaEmailP) {

		final String resultadoObtenido = validacionesAtributosCorrectoEmail
				.comprobarAtributoCorrectoEmail(datosEntradaEmailP.getEmailUsuario());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CORRECTO, Constantes.EXITO, datosEntradaEmailP.getEmailUsuario(),
				Constantes.EMAILP);

	}
}
