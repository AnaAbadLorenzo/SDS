package com.sds.service.test.impl.atributos;

import java.text.ParseException;

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

public class TestAtributoContrasenaLogin {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosEnhe validacionesAtributosEnhe;
	private final ValidacionesAtributosAcentos validacionesAtributosAcentos;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoContrasenaLogin() {
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

	public DatosPruebaAtributos getTestLoginContrasenaVacio(final Login datosEntradaLoginContrasenaVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaLoginContrasenaVacio.getPasswdUsuario(), Funcionalidad.LOGIN, Atributo.PASSWD_USUARIO);

		final String resultadoEsperado = CodigosMensajes.PASS_USUARIO_VACIO + " - "
				+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaLoginContrasenaVacio.getPasswdUsuario(),
				Constantes.PASSWD_USUARIO);
	}

	public DatosPruebaAtributos getTestLoginContrasenaAlfanumericoEnhe(
			final Login datosEntradaLoginContrasenaAlfanumericoEnhe) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEnhe.comprobarAtributoEnhe(
				datosEntradaLoginContrasenaAlfanumericoEnhe.getPasswdUsuario(), Funcionalidad.LOGIN,
				Atributo.PASSWD_USUARIO);

		final String resultadoEsperado = CodigosMensajes.PASS_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ENHE, Constantes.ERROR,
				datosEntradaLoginContrasenaAlfanumericoEnhe.getPasswdUsuario(), Constantes.PASSWD_USUARIO);

	}

	public DatosPruebaAtributos getTestLoginContrasenaAlfanumericoAcentos(
			final Login datosEntradaLoginContrasenaAlfanumericoAcentos) throws ParseException {

		final String resultadoObtenido = validacionesAtributosAcentos.comprobarAtributoAcentos(
				datosEntradaLoginContrasenaAlfanumericoAcentos.getPasswdUsuario(), Funcionalidad.LOGIN,
				Atributo.PASSWD_USUARIO);

		final String resultadoEsperado = CodigosMensajes.PASS_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ACENTOS, Constantes.ERROR,
				datosEntradaLoginContrasenaAlfanumericoAcentos.getPasswdUsuario(), Constantes.PASSWD_USUARIO);

	}

	public DatosPruebaAtributos getTestLoginContrasenaAlfanumericoCaracteresEspeciales(
			final Login datosEntradaLoginContrasenaAlfanumericoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaLoginContrasenaAlfanumericoCaracteresEspeciales.getPasswdUsuario(),
						Funcionalidad.LOGIN, Atributo.PASSWD_USUARIO);

		final String resultadoEsperado = CodigosMensajes.PASS_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaLoginContrasenaAlfanumericoCaracteresEspeciales.getPasswdUsuario(),
				Constantes.PASSWD_USUARIO);
	}

	public DatosPruebaAtributos getTestLoginContrasenaAlfanumericoEspacios(
			final Login datosEntradaLoginContrasenaAlfanumericoEspacios) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaLoginContrasenaAlfanumericoEspacios.getPasswdUsuario(), Funcionalidad.LOGIN,
				Atributo.PASSWD_USUARIO);

		final String resultadoEsperado = CodigosMensajes.PASS_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ESPACIOS, Constantes.ERROR,
				datosEntradaLoginContrasenaAlfanumericoEspacios.getPasswdUsuario(), Constantes.PASSWD_USUARIO);
	}

	public DatosPruebaAtributos getTestLoginContrasenaAlfanumericoMenor3(
			final Login datosEntradaLoginContrasenaAlfanumericoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaLoginContrasenaAlfanumericoMenor3.getPasswdUsuario(), Funcionalidad.LOGIN,
				Atributo.PASSWD_USUARIO, 3);

		final String resultadoEsperado = CodigosMensajes.PASS_MENOR_QUE_3 + " - "
				+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_3, Constantes.ERROR,
				datosEntradaLoginContrasenaAlfanumericoMenor3.getPasswdUsuario(), Constantes.PASSWD_USUARIO);
	}

	public DatosPruebaAtributos getTestLoginContrasenaAlfanumericoMayor45(
			final Login datosEntradaLoginContrasenaAlfanumericoMayor45) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaLoginContrasenaAlfanumericoMayor45.getPasswdUsuario(), Funcionalidad.LOGIN,
				Atributo.PASSWD_USUARIO, 45);

		final String resultadoEsperado = CodigosMensajes.PASS_MAYOR_QUE_45 + " - "
				+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_SER_MAYOR_QUE_45;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MAYOR_45, Constantes.ERROR,
				datosEntradaLoginContrasenaAlfanumericoMayor45.getPasswdUsuario(), Constantes.PASSWD_USUARIO);
	}

	public DatosPruebaAtributos getTestLoginContrasenaCorrectoAlfanumerico(final Login datosEntradaLoginContrasena) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(datosEntradaLoginContrasena.getPasswdUsuario());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CORRECTO, Constantes.EXITO,
				datosEntradaLoginContrasena.getPasswdUsuario(), Constantes.PASSWD_USUARIO);

	}
}
