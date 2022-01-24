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

public class TestAtributoUsuarioRegistro {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosEnhe validacionesAtributosEnhe;
	private final ValidacionesAtributosAcentos validacionesAtributosAcentos;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoUsuarioRegistro() {
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

	public DatosPruebaAtributos getTestRegistroUsuarioVacio(final Registro datosEntradaRegistroUsuarioVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaRegistroUsuarioVacio.getDatosUsuario().getUsuario(), Funcionalidad.REGISTRAR,
				Atributo.USUARIO);

		final String resultadoEsperado = CodigosMensajes.LOGIN_USUARIO_VACIO + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				datosEntradaRegistroUsuarioVacio.getDatosUsuario().getUsuario(), Constantes.USUARIO);
	}

	public DatosPruebaAtributos getTestRegistroUsuarioAlfanumericoEnhe(
			final Registro datosEntradaRegistroUsuarioAlfanumericoEnhe) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEnhe.comprobarAtributoEnhe(
				datosEntradaRegistroUsuarioAlfanumericoEnhe.getDatosUsuario().getUsuario(), Funcionalidad.REGISTRAR,
				Atributo.USUARIO);

		final String resultadoEsperado = CodigosMensajes.LOGIN_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ENHE, Constantes.ERROR,
				datosEntradaRegistroUsuarioAlfanumericoEnhe.getDatosUsuario().getUsuario(), Constantes.USUARIO);

	}

	public DatosPruebaAtributos getTestRegistroUsuarioAlfanumericoAcentos(
			final Registro datosEntradaRegistroUsuarioAlfanumericoAcentos) throws ParseException {

		final String resultadoObtenido = validacionesAtributosAcentos.comprobarAtributoAcentos(
				datosEntradaRegistroUsuarioAlfanumericoAcentos.getDatosUsuario().getUsuario(), Funcionalidad.REGISTRAR,
				Atributo.USUARIO);

		final String resultadoEsperado = CodigosMensajes.LOGIN_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ACENTOS, Constantes.ERROR,
				datosEntradaRegistroUsuarioAlfanumericoAcentos.getDatosUsuario().getUsuario(), Constantes.USUARIO);

	}

	public DatosPruebaAtributos getTestRegistroUsuarioAlfanumericoCaracteresEspeciales(
			final Registro datosEntradaRegistroUsuarioAlfanumericoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaRegistroUsuarioAlfanumericoCaracteresEspeciales.getDatosUsuario().getUsuario(),
						Funcionalidad.REGISTRAR, Atributo.USUARIO);

		final String resultadoEsperado = CodigosMensajes.LOGIN_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaRegistroUsuarioAlfanumericoCaracteresEspeciales.getDatosUsuario().getUsuario(),
				Constantes.USUARIO);
	}

	public DatosPruebaAtributos getTestRegistroUsuarioAlfanumericoEspacios(
			final Registro datosEntradaRegistroUsuarioAlfanumericoEspacios) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaRegistroUsuarioAlfanumericoEspacios.getDatosUsuario().getUsuario(), Funcionalidad.REGISTRAR,
				Atributo.USUARIO);

		final String resultadoEsperado = CodigosMensajes.LOGIN_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ESPACIOS, Constantes.ERROR,
				datosEntradaRegistroUsuarioAlfanumericoEspacios.getDatosUsuario().getUsuario(), Constantes.USUARIO);
	}

	public DatosPruebaAtributos getTestRegistroUsuarioAlfanumericoMenor3(
			final Registro datosEntradaRegistroUsuarioAlfanumericoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaRegistroUsuarioAlfanumericoMenor3.getDatosUsuario().getUsuario(), Funcionalidad.REGISTRAR,
				Atributo.USUARIO, 3);

		final String resultadoEsperado = CodigosMensajes.LOGIN_MENOR_QUE_3 + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_3, Constantes.ERROR,
				datosEntradaRegistroUsuarioAlfanumericoMenor3.getDatosUsuario().getUsuario(), Constantes.USUARIO);
	}

	public DatosPruebaAtributos getTestRegistroUsuarioAlfanumericoMayor45(
			final Registro datosEntradaRegistroUsuarioAlfanumericoMayor45) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaRegistroUsuarioAlfanumericoMayor45.getDatosUsuario().getUsuario(), Funcionalidad.REGISTRAR,
				Atributo.USUARIO, 45);

		final String resultadoEsperado = CodigosMensajes.LOGIN_MAYOR_QUE_45 + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_MAYOR_QUE_45;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MAYOR_45, Constantes.ERROR,
				datosEntradaRegistroUsuarioAlfanumericoMayor45.getDatosUsuario().getUsuario(), Constantes.USUARIO);
	}

	public DatosPruebaAtributos getTestRegistroUsuarioCorrectoAlfanumerico(final Registro datosEntradaLoginUsuario) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(datosEntradaLoginUsuario.getDatosUsuario().getUsuario());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CORRECTO, Constantes.EXITO,
				datosEntradaLoginUsuario.getDatosUsuario().getUsuario(), Constantes.USUARIO);

	}

}
