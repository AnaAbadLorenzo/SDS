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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCaracteresEspeciales;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCorrectoAlfanumerico;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEnhe;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEspacios;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMayor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoUsuarioRecuperarPass {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosEnhe validacionesAtributosEnhe;
	private final ValidacionesAtributosAcentos validacionesAtributosAcentos;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoUsuarioRecuperarPass() {
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

	public DatosPruebaAtributos getTestLoginUsuarioVacio(final RecuperarPass datosEntradaLoginUsuarioVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaLoginUsuarioVacio.getUsuario(), Funcionalidad.RECUPERAR_PASS, Atributo.USUARIO);

		final String resultadoEsperado = CodigosMensajes.LOGIN_USUARIO_VACIO + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaLoginUsuarioVacio.getUsuario(),
				Constantes.USUARIO);
	}

	public DatosPruebaAtributos getTestLoginUsuarioAlfanumericoEnhe(
			final RecuperarPass datosEntradaLoginUsuarioAlfanumericoEnhe) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEnhe.comprobarAtributoEnhe(
				datosEntradaLoginUsuarioAlfanumericoEnhe.getUsuario(), Funcionalidad.RECUPERAR_PASS, Atributo.USUARIO);

		final String resultadoEsperado = CodigosMensajes.LOGIN_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ENHE, Constantes.ERROR,
				datosEntradaLoginUsuarioAlfanumericoEnhe.getUsuario(), Constantes.USUARIO);

	}

	public DatosPruebaAtributos getTestLoginUsuarioAlfanumericoAcentos(
			final RecuperarPass datosEntradaLoginUsuarioAlfanumericoAcentos) throws ParseException {

		final String resultadoObtenido = validacionesAtributosAcentos.comprobarAtributoAcentos(
				datosEntradaLoginUsuarioAlfanumericoAcentos.getUsuario(), Funcionalidad.RECUPERAR_PASS,
				Atributo.USUARIO);

		final String resultadoEsperado = CodigosMensajes.LOGIN_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ACENTOS, Constantes.ERROR,
				datosEntradaLoginUsuarioAlfanumericoAcentos.getUsuario(), Constantes.USUARIO);

	}

	public DatosPruebaAtributos getTestLoginUsuarioAlfanumericoCaracteresEspeciales(
			final RecuperarPass datosEntradaLoginUsuarioAlfanumericoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaLoginUsuarioAlfanumericoCaracteresEspeciales.getUsuario(),
						Funcionalidad.RECUPERAR_PASS, Atributo.USUARIO);

		final String resultadoEsperado = CodigosMensajes.LOGIN_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaLoginUsuarioAlfanumericoCaracteresEspeciales.getUsuario(), Constantes.USUARIO);
	}

	public DatosPruebaAtributos getTestLoginUsuarioAlfanumericoEspacios(
			final RecuperarPass datosEntradaLoginUsuarioAlfanumericoEspacios) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaLoginUsuarioAlfanumericoEspacios.getUsuario(), Funcionalidad.RECUPERAR_PASS,
				Atributo.USUARIO);

		final String resultadoEsperado = CodigosMensajes.LOGIN_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ESPACIOS, Constantes.ERROR,
				datosEntradaLoginUsuarioAlfanumericoEspacios.getUsuario(), Constantes.USUARIO);
	}

	public DatosPruebaAtributos getTestLoginUsuarioAlfanumericoMenor3(
			final RecuperarPass datosEntradaLoginUsuarioAlfanumericoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaLoginUsuarioAlfanumericoMenor3.getUsuario(), Funcionalidad.RECUPERAR_PASS, Atributo.USUARIO,
				3);

		final String resultadoEsperado = CodigosMensajes.LOGIN_MENOR_QUE_3 + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_3, Constantes.ERROR,
				datosEntradaLoginUsuarioAlfanumericoMenor3.getUsuario(), Constantes.USUARIO);
	}

	public DatosPruebaAtributos getTestLoginUsuarioAlfanumericoMayor45(
			final RecuperarPass datosEntradaLoginUsuarioAlfanumericoMayor45) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaLoginUsuarioAlfanumericoMayor45.getUsuario(), Funcionalidad.RECUPERAR_PASS,
				Atributo.USUARIO, 45);

		final String resultadoEsperado = CodigosMensajes.LOGIN_MAYOR_QUE_45 + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_MAYOR_QUE_45;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MAYOR_45, Constantes.ERROR,
				datosEntradaLoginUsuarioAlfanumericoMayor45.getUsuario(), Constantes.USUARIO);
	}

	public DatosPruebaAtributos getTestLoginUsuarioCorrectoAlfanumerico(final RecuperarPass datosEntradaLoginUsuario) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(datosEntradaLoginUsuario.getUsuario());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CORRECTO, Constantes.EXITO, datosEntradaLoginUsuario.getUsuario(),
				Constantes.USUARIO);

	}
}
