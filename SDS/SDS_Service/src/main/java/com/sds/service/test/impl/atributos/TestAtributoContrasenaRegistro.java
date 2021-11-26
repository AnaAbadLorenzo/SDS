package com.sds.service.test.impl.atributos;

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

public class TestAtributoContrasenaRegistro {
	
	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosEnhe validacionesAtributosEnhe;
	private final ValidacionesAtributosAcentos validacionesAtributosAcentos;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoContrasenaRegistro() {
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

	public DatosPruebaAtributos getTestRegistroContrasenaVacio(final Registro datosEntradaRegistroContrasenaVacio) {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaRegistroContrasenaVacio.getDatosUsuario().getPasswdUsuario(), Funcionalidad.REGISTRAR, Atributo.PASSWD_USUARIO);

		final String resultadoEsperado = CodigosMensajes.PASS_USUARIO_VACIO + " - "
				+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaRegistroContrasenaVacio.getDatosUsuario().getPasswdUsuario(),
				Constantes.PASSWD_USUARIO);
	}

	public DatosPruebaAtributos getTestRegistroContrasenaAlfanumericoEnhe(
			final Registro datosEntradaRegistroContrasenaAlfanumericoEnhe) {

		final String resultadoObtenido = validacionesAtributosEnhe.comprobarAtributoEnhe(
				datosEntradaRegistroContrasenaAlfanumericoEnhe.getDatosUsuario().getPasswdUsuario(), Funcionalidad.REGISTRAR,
				Atributo.PASSWD_USUARIO);

		final String resultadoEsperado = CodigosMensajes.PASS_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ENHE, Constantes.ERROR,
				datosEntradaRegistroContrasenaAlfanumericoEnhe.getDatosUsuario().getPasswdUsuario(), Constantes.PASSWD_USUARIO);

	}

	public DatosPruebaAtributos getTestRegistroContrasenaAlfanumericoAcentos(
			final Registro datosEntradaRegistroContrasenaAlfanumericoAcentos) {

		final String resultadoObtenido = validacionesAtributosAcentos.comprobarAtributoAcentos(
				datosEntradaRegistroContrasenaAlfanumericoAcentos.getDatosUsuario().getPasswdUsuario(), Funcionalidad.REGISTRAR,
				Atributo.PASSWD_USUARIO);

		final String resultadoEsperado = CodigosMensajes.PASS_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ACENTOS, Constantes.ERROR,
				datosEntradaRegistroContrasenaAlfanumericoAcentos.getDatosUsuario().getPasswdUsuario(), Constantes.PASSWD_USUARIO);
	}

	public DatosPruebaAtributos getTestRegistroContrasenaAlfanumericoCaracteresEspeciales(
			final Registro datosEntradaRegistroContrasenaAlfanumericoCaracteresEspeciales) {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaRegistroContrasenaAlfanumericoCaracteresEspeciales.getDatosUsuario().getPasswdUsuario(),
						Funcionalidad.REGISTRAR, Atributo.PASSWD_USUARIO);

		final String resultadoEsperado = CodigosMensajes.PASS_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaRegistroContrasenaAlfanumericoCaracteresEspeciales.getDatosUsuario().getPasswdUsuario(),
				Constantes.PASSWD_USUARIO);
	}

	public DatosPruebaAtributos getTestRegistroContrasenaAlfanumericoEspacios(
			final Registro datosEntradaRegistroContrasenaAlfanumericoEspacios) {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaRegistroContrasenaAlfanumericoEspacios.getDatosUsuario().getPasswdUsuario(), Funcionalidad.REGISTRAR,
				Atributo.PASSWD_USUARIO);

		final String resultadoEsperado = CodigosMensajes.PASS_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ESPACIOS, Constantes.ERROR,
				datosEntradaRegistroContrasenaAlfanumericoEspacios.getDatosUsuario().getPasswdUsuario(), Constantes.PASSWD_USUARIO);
	}

	public DatosPruebaAtributos getTestRegistroContrasenaAlfanumericoMenor3(
			final Registro datosEntradaRegistroContrasenaAlfanumericoMenor3) {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaRegistroContrasenaAlfanumericoMenor3.getDatosUsuario().getPasswdUsuario(), Funcionalidad.REGISTRAR,
				Atributo.PASSWD_USUARIO, 3);

		final String resultadoEsperado = CodigosMensajes.PASS_MENOR_QUE_3 + " - "
				+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_3, Constantes.ERROR,
				datosEntradaRegistroContrasenaAlfanumericoMenor3.getDatosUsuario().getPasswdUsuario(), Constantes.PASSWD_USUARIO);
	}

	public DatosPruebaAtributos getTestRegistroContrasenaAlfanumericoMayor45(
			final Registro datosEntradaRegistroContrasenaAlfanumericoMayor45) {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaRegistroContrasenaAlfanumericoMayor45.getDatosUsuario().getPasswdUsuario(), Funcionalidad.REGISTRAR,
				Atributo.PASSWD_USUARIO, 45);

		final String resultadoEsperado = CodigosMensajes.PASS_MAYOR_QUE_45 + " - "
				+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_SER_MAYOR_QUE_45;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MAYOR_45, Constantes.ERROR,
				datosEntradaRegistroContrasenaAlfanumericoMayor45.getDatosUsuario().getPasswdUsuario(), Constantes.PASSWD_USUARIO);
	}

	public DatosPruebaAtributos getTestRegistroContrasenaCorrectoAlfanumerico(final Registro datosEntradaRegistroContrasena) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(datosEntradaRegistroContrasena.getDatosUsuario().getPasswdUsuario());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CORRECTO, Constantes.EXITO,
				datosEntradaRegistroContrasena.getDatosUsuario().getPasswdUsuario(), Constantes.PASSWD_USUARIO);

	}

}
