package com.sds.service.test.impl.atributos;

import java.text.ParseException;

import com.sds.model.UsuarioEntity;
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

public class TestAtributoContrasenaUsuario {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosEnhe validacionesAtributosEnhe;
	private final ValidacionesAtributosAcentos validacionesAtributosAcentos;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoContrasenaUsuario() {
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

	public DatosPruebaAtributos getTestContrasenaVacio(final UsuarioEntity datosEntradaContrasenaVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaContrasenaVacio.getPasswdUsuario(), Funcionalidad.GESTION_USUARIOS,
				Atributo.PASSWD_USUARIO);

		final String resultadoEsperado = CodigosMensajes.PASS_USUARIO_VACIO + " - "
				+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaContrasenaVacio.getPasswdUsuario(),
				Constantes.PASSWD_USUARIO);
	}

	public DatosPruebaAtributos getTestContrasenaAlfanumericoEnhe(
			final UsuarioEntity datosEntradaContrasenaAlfanumericoEnhe) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEnhe.comprobarAtributoEnhe(
				datosEntradaContrasenaAlfanumericoEnhe.getPasswdUsuario(), Funcionalidad.GESTION_USUARIOS,
				Atributo.PASSWD_USUARIO);

		final String resultadoEsperado = CodigosMensajes.PASS_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ENHE, Constantes.ERROR,
				datosEntradaContrasenaAlfanumericoEnhe.getPasswdUsuario(), Constantes.PASSWD_USUARIO);

	}

	public DatosPruebaAtributos getTestContrasenaAlfanumericoAcentos(
			final UsuarioEntity datosEntradaContrasenaAlfanumericoAcentos) throws ParseException {

		final String resultadoObtenido = validacionesAtributosAcentos.comprobarAtributoAcentos(
				datosEntradaContrasenaAlfanumericoAcentos.getPasswdUsuario(), Funcionalidad.GESTION_USUARIOS,
				Atributo.PASSWD_USUARIO);

		final String resultadoEsperado = CodigosMensajes.PASS_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ACENTOS, Constantes.ERROR,
				datosEntradaContrasenaAlfanumericoAcentos.getPasswdUsuario(), Constantes.PASSWD_USUARIO);
	}

	public DatosPruebaAtributos getTestContrasenaAlfanumericoCaracteresEspeciales(
			final UsuarioEntity datosEntradaContrasenaAlfanumericoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaContrasenaAlfanumericoCaracteresEspeciales.getPasswdUsuario(),
						Funcionalidad.GESTION_USUARIOS, Atributo.PASSWD_USUARIO);

		final String resultadoEsperado = CodigosMensajes.PASS_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaContrasenaAlfanumericoCaracteresEspeciales.getPasswdUsuario(), Constantes.PASSWD_USUARIO);
	}

	public DatosPruebaAtributos getTestContrasenaAlfanumericoEspacios(
			final UsuarioEntity datosEntradaContrasenaAlfanumericoEspacios) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaContrasenaAlfanumericoEspacios.getPasswdUsuario(), Funcionalidad.GESTION_USUARIOS,
				Atributo.PASSWD_USUARIO);

		final String resultadoEsperado = CodigosMensajes.PASS_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ESPACIOS, Constantes.ERROR,
				datosEntradaContrasenaAlfanumericoEspacios.getPasswdUsuario(), Constantes.PASSWD_USUARIO);
	}

	public DatosPruebaAtributos getTestContrasenaAlfanumericoMenor3(
			final UsuarioEntity datosEntradaContrasenaAlfanumericoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaContrasenaAlfanumericoMenor3.getPasswdUsuario(), Funcionalidad.GESTION_USUARIOS,
				Atributo.PASSWD_USUARIO, 3);

		final String resultadoEsperado = CodigosMensajes.PASS_MENOR_QUE_3 + " - "
				+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_3, Constantes.ERROR,
				datosEntradaContrasenaAlfanumericoMenor3.getPasswdUsuario(), Constantes.PASSWD_USUARIO);
	}

	public DatosPruebaAtributos getTestContrasenaAlfanumericoMayor45(
			final UsuarioEntity datosEntradaContrasenaAlfanumericoMayor45) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaContrasenaAlfanumericoMayor45.getPasswdUsuario(), Funcionalidad.GESTION_USUARIOS,
				Atributo.PASSWD_USUARIO, 45);

		final String resultadoEsperado = CodigosMensajes.PASS_MAYOR_QUE_45 + " - "
				+ Mensajes.CONTRASENA_USUARIO_NO_PUEDE_SER_MAYOR_QUE_45;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MAYOR_45, Constantes.ERROR,
				datosEntradaContrasenaAlfanumericoMayor45.getPasswdUsuario(), Constantes.PASSWD_USUARIO);
	}

	public DatosPruebaAtributos getTestContrasenaCorrectoAlfanumerico(final UsuarioEntity datosEntradaContrasena) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(datosEntradaContrasena.getPasswdUsuario());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CORRECTO, Constantes.EXITO, datosEntradaContrasena.getPasswdUsuario(),
				Constantes.PASSWD_USUARIO);

	}
}
