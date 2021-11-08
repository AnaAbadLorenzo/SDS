package com.sds.service.test.impl.atributos;

import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.DefinicionPruebas;
import com.sds.service.common.Mensajes;
import com.sds.service.common.enumerados.Atributo;
import com.sds.service.common.enumerados.Funcionalidad;
import com.sds.service.login.model.Login;
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

public class TestAtributoUsuarioLogin {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosEnhe validacionesAtributosEnhe;
	private final ValidacionesAtributosAcentos validacionesAtributosAcentos;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoUsuarioLogin() {
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

	public DatosPruebaAtributos getTestLoginUsuarioVacio(final Login datosEntradaLoginUsuarioVacio) {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaLoginUsuarioVacio.getUsuario(), Funcionalidad.LOGIN, Atributo.USUARIO);

		final String resultadoEsperado = CodigosMensajes.LOGIN_USUARIO_VACIO + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaLoginUsuarioVacio.getUsuario(),
				Constantes.USUARIO);
	}

	public DatosPruebaAtributos getTestLoginUsuarioAlfanumericoEnhe(
			final Login datosEntradaLoginUsuarioAlfanumericoEnhe) {

		final String resultadoObtenido = validacionesAtributosEnhe.comprobarAtributoEnhe(
				datosEntradaLoginUsuarioAlfanumericoEnhe.getUsuario(), Funcionalidad.LOGIN, Atributo.USUARIO);

		final String resultadoEsperado = CodigosMensajes.LOGIN_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ENHE, Constantes.ERROR,
				datosEntradaLoginUsuarioAlfanumericoEnhe.getUsuario(), Constantes.USUARIO);

	}

	public DatosPruebaAtributos getTestLoginUsuarioAlfanumericoAcentos(
			final Login datosEntradaLoginUsuarioAlfanumericoAcentos) {

		final String resultadoObtenido = validacionesAtributosAcentos.comprobarAtributoAcentos(
				datosEntradaLoginUsuarioAlfanumericoAcentos.getUsuario(), Funcionalidad.LOGIN, Atributo.USUARIO);

		final String resultadoEsperado = CodigosMensajes.LOGIN_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ACENTOS, Constantes.ERROR,
				datosEntradaLoginUsuarioAlfanumericoAcentos.getUsuario(), Constantes.USUARIO);

	}

	public DatosPruebaAtributos getTestLoginUsuarioAlfanumericoCaracteresEspeciales(
			final Login datosEntradaLoginUsuarioAlfanumericoCaracteresEspeciales) {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaLoginUsuarioAlfanumericoCaracteresEspeciales.getUsuario(), Funcionalidad.LOGIN,
						Atributo.USUARIO);

		final String resultadoEsperado = CodigosMensajes.LOGIN_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaLoginUsuarioAlfanumericoCaracteresEspeciales.getUsuario(), Constantes.USUARIO);
	}

	public DatosPruebaAtributos getTestLoginUsuarioAlfanumericoEspacios(
			final Login datosEntradaLoginUsuarioAlfanumericoEspacios) {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaLoginUsuarioAlfanumericoEspacios.getUsuario(), Funcionalidad.LOGIN, Atributo.USUARIO);

		final String resultadoEsperado = CodigosMensajes.LOGIN_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ESPACIOS, Constantes.ERROR,
				datosEntradaLoginUsuarioAlfanumericoEspacios.getUsuario(), Constantes.USUARIO);
	}

	public DatosPruebaAtributos getTestLoginUsuarioAlfanumericoMenor3(
			final Login datosEntradaLoginUsuarioAlfanumericoMenor3) {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaLoginUsuarioAlfanumericoMenor3.getUsuario(), Funcionalidad.LOGIN, Atributo.USUARIO, 3);

		final String resultadoEsperado = CodigosMensajes.LOGIN_MENOR_QUE_3 + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_3, Constantes.ERROR,
				datosEntradaLoginUsuarioAlfanumericoMenor3.getUsuario(), Constantes.USUARIO);
	}

	public DatosPruebaAtributos getTestLoginUsuarioAlfanumericoMayor45(
			final Login datosEntradaLoginUsuarioAlfanumericoMayor45) {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaLoginUsuarioAlfanumericoMayor45.getUsuario(), Funcionalidad.LOGIN, Atributo.USUARIO, 45);

		final String resultadoEsperado = CodigosMensajes.LOGIN_MAYOR_QUE_45 + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_MAYOR_QUE_45;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MAYOR_45, Constantes.ERROR,
				datosEntradaLoginUsuarioAlfanumericoMayor45.getUsuario(), Constantes.USUARIO);
	}

	public DatosPruebaAtributos getTestLoginUsuarioCorrectoAlfanumerico(final Login datosEntradaLoginUsuario) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(datosEntradaLoginUsuario.getUsuario());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CORRECTO, Constantes.EXITO, datosEntradaLoginUsuario.getUsuario(),
				Constantes.USUARIO);

	}

}
