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

public class TestAtributoNombreUsuario {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosEnhe validacionesAtributosEnhe;
	private final ValidacionesAtributosAcentos validacionesAtributosAcentos;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoNombreUsuario() {
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

	public DatosPruebaAtributos getTestUsuarioVacio(final UsuarioEntity datosEntradaUsuarioVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaUsuarioVacio.getUsuario(), Funcionalidad.GESTION_USUARIOS, Atributo.USUARIO);

		final String resultadoEsperado = CodigosMensajes.LOGIN_USUARIO_VACIO + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaUsuarioVacio.getUsuario(), Constantes.USUARIO);
	}

	public DatosPruebaAtributos getTestUsuarioAlfanumericoEnhe(final UsuarioEntity datosEntradaUsuarioAlfanumericoEnhe)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosEnhe.comprobarAtributoEnhe(
				datosEntradaUsuarioAlfanumericoEnhe.getUsuario(), Funcionalidad.GESTION_USUARIOS, Atributo.USUARIO);

		final String resultadoEsperado = CodigosMensajes.LOGIN_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ENHE, Constantes.ERROR, datosEntradaUsuarioAlfanumericoEnhe.getUsuario(),
				Constantes.USUARIO);

	}

	public DatosPruebaAtributos getTestUsuarioAlfanumericoAcentos(
			final UsuarioEntity datosEntradaUsuarioAlfanumericoAcentos) throws ParseException {

		final String resultadoObtenido = validacionesAtributosAcentos.comprobarAtributoAcentos(
				datosEntradaUsuarioAlfanumericoAcentos.getUsuario(), Funcionalidad.GESTION_USUARIOS, Atributo.USUARIO);

		final String resultadoEsperado = CodigosMensajes.LOGIN_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ACENTOS, Constantes.ERROR,
				datosEntradaUsuarioAlfanumericoAcentos.getUsuario(), Constantes.USUARIO);

	}

	public DatosPruebaAtributos getTestUsuarioAlfanumericoCaracteresEspeciales(
			final UsuarioEntity datosEntradaUsuarioAlfanumericoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(datosEntradaUsuarioAlfanumericoCaracteresEspeciales.getUsuario(),
						Funcionalidad.GESTION_USUARIOS, Atributo.USUARIO);

		final String resultadoEsperado = CodigosMensajes.LOGIN_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaUsuarioAlfanumericoCaracteresEspeciales.getUsuario(), Constantes.USUARIO);
	}

	public DatosPruebaAtributos getTestUsuarioAlfanumericoEspacios(
			final UsuarioEntity datosEntradaUsuarioAlfanumericoEspacios) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaUsuarioAlfanumericoEspacios.getUsuario(), Funcionalidad.GESTION_USUARIOS, Atributo.USUARIO);

		final String resultadoEsperado = CodigosMensajes.LOGIN_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ESPACIOS, Constantes.ERROR,
				datosEntradaUsuarioAlfanumericoEspacios.getUsuario(), Constantes.USUARIO);
	}

	public DatosPruebaAtributos getTestUsuarioAlfanumericoMenor3(
			final UsuarioEntity datosEntradaUsuarioAlfanumericoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaUsuarioAlfanumericoMenor3.getUsuario(), Funcionalidad.GESTION_USUARIOS, Atributo.USUARIO,
				3);

		final String resultadoEsperado = CodigosMensajes.LOGIN_MENOR_QUE_3 + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_3, Constantes.ERROR,
				datosEntradaUsuarioAlfanumericoMenor3.getUsuario(), Constantes.USUARIO);
	}

	public DatosPruebaAtributos getTestUsuarioAlfanumericoMayor45(
			final UsuarioEntity datosEntradaUsuarioAlfanumericoMayor45) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaUsuarioAlfanumericoMayor45.getUsuario(), Funcionalidad.GESTION_USUARIOS, Atributo.USUARIO,
				45);

		final String resultadoEsperado = CodigosMensajes.LOGIN_MAYOR_QUE_45 + " - "
				+ Mensajes.LOGIN_USUARIO_NO_PUEDE_SER_MAYOR_QUE_45;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MAYOR_45, Constantes.ERROR,
				datosEntradaUsuarioAlfanumericoMayor45.getUsuario(), Constantes.USUARIO);
	}

	public DatosPruebaAtributos getTestUsuarioCorrectoAlfanumerico(final UsuarioEntity datosEntradaLoginUsuario) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(datosEntradaLoginUsuario.getUsuario());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CORRECTO, Constantes.EXITO, datosEntradaLoginUsuario.getUsuario(),
				Constantes.USUARIO);

	}
}
