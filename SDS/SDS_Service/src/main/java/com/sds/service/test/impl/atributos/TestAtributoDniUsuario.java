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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCorrectoDNI;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEnhe;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEspacios;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMayor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoDniUsuario {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosEnhe validacionesAtributosEnhe;
	private final ValidacionesAtributosAcentos validacionesAtributosAcentos;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoDNI validacionesAtributosCorrectoDNI;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoDniUsuario() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosEnhe = new ValidacionesAtributosEnhe();
		validacionesAtributosAcentos = new ValidacionesAtributosAcentos();
		validacionesAtributosCaracteresEspeciales = new ValidacionesAtributosCaracteresEspeciales();
		validacionesAtributosEspacios = new ValidacionesAtributosEspacios();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosMayor = new ValidacionesAtributosMayor();
		validacionesAtributosCorrectoDNI = new ValidacionesAtributosCorrectoDNI();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestDniUsuarioVacio(final UsuarioEntity datosEntradaDniUsuarioVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaDniUsuarioVacio.getDniUsuario(), Funcionalidad.GESTION_USUARIOS, Atributo.DNI_USUARIO);

		final String resultadoEsperado = CodigosMensajes.DNI_PERSONA_VACIO + " - "
				+ Mensajes.DNI_PERSONA_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaDniUsuarioVacio.getDniUsuario(),
				Constantes.DNI_USUARIO);
	}

	public DatosPruebaAtributos getTestDniUsuarioAlfanumericoEnhe(
			final UsuarioEntity datosEntradaDniUsuarioAlfanumericoEnhe) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEnhe.comprobarAtributoEnhe(
				datosEntradaDniUsuarioAlfanumericoEnhe.getDniUsuario(), Funcionalidad.GESTION_USUARIOS,
				Atributo.DNI_USUARIO);

		final String resultadoEsperado = CodigosMensajes.DNI_PERSONA_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.DNI_PERSONA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ENHE, Constantes.ERROR,
				datosEntradaDniUsuarioAlfanumericoEnhe.getDniUsuario(), Constantes.DNI_USUARIO);

	}

	public DatosPruebaAtributos getTestDniUsuarioAlfanumericoAcentos(
			final UsuarioEntity datosEntradaDniUsuarioAlfanumericoAcentos) throws ParseException {

		final String resultadoObtenido = validacionesAtributosAcentos.comprobarAtributoAcentos(
				datosEntradaDniUsuarioAlfanumericoAcentos.getDniUsuario(), Funcionalidad.GESTION_USUARIOS,
				Atributo.DNI_USUARIO);

		final String resultadoEsperado = CodigosMensajes.DNI_PERSONA_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.DNI_PERSONA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ACENTOS, Constantes.ERROR,
				datosEntradaDniUsuarioAlfanumericoAcentos.getDniUsuario(), Constantes.DNI_USUARIO);

	}

	public DatosPruebaAtributos getTestDniUsuarioAlfanumericoCaracteresEspeciales(
			final UsuarioEntity datosEntradaDniUsuarioAlfanumericoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaDniUsuarioAlfanumericoCaracteresEspeciales.getDniUsuario(),
						Funcionalidad.GESTION_USUARIOS, Atributo.DNI_USUARIO);

		final String resultadoEsperado = CodigosMensajes.DNI_PERSONA_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.DNI_PERSONA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaDniUsuarioAlfanumericoCaracteresEspeciales.getDniUsuario(), Constantes.DNI_USUARIO);
	}

	public DatosPruebaAtributos getTestDniUsuarioAlfanumericoEspacios(
			final UsuarioEntity datosEntradaDniUsuarioAlfanumericoEspacios) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaDniUsuarioAlfanumericoEspacios.getDniUsuario(), Funcionalidad.GESTION_USUARIOS,
				Atributo.DNI_USUARIO);

		final String resultadoEsperado = CodigosMensajes.DNI_PERSONA_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.DNI_PERSONA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ESPACIOS, Constantes.ERROR,
				datosEntradaDniUsuarioAlfanumericoEspacios.getDniUsuario(), Constantes.DNI_USUARIO);
	}

	public DatosPruebaAtributos getTestDniUsuarioAlfanumericoMenor9(
			final UsuarioEntity datosDniUsuarioAlfanumericoMenor9) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosDniUsuarioAlfanumericoMenor9.getDniUsuario(), Funcionalidad.GESTION_USUARIOS, Atributo.DNI_USUARIO,
				9);

		final String resultadoEsperado = CodigosMensajes.DNI_PERSONA_MENOR_QUE_9 + " - "
				+ Mensajes.DNI_NO_PUEDE_SER_MENOR_QUE_9;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_9, Constantes.ERROR,
				datosDniUsuarioAlfanumericoMenor9.getDniUsuario(), Constantes.DNI_USUARIO);
	}

	public DatosPruebaAtributos getTestDniUsuarioAlfanumericoMayor9(
			final UsuarioEntity datosEntradaDniUsuarioAlfanumericoMayor9) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaDniUsuarioAlfanumericoMayor9.getDniUsuario(), Funcionalidad.GESTION_USUARIOS,
				Atributo.DNI_USUARIO, 9);

		final String resultadoEsperado = CodigosMensajes.DNI_PERSONA_MAYOR_QUE_9 + " - "
				+ Mensajes.DNI_NO_PUEDE_SER_MAYOR_QUE_9;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MAYOR_9, Constantes.ERROR,
				datosEntradaDniUsuarioAlfanumericoMayor9.getDniUsuario(), Constantes.DNI_USUARIO);
	}

	public DatosPruebaAtributos getTestDniUsuarioCorrectoAlfanumerico(final UsuarioEntity datosEntradaDniUsuario) {

		final String resultadoObtenido = validacionesAtributosCorrectoDNI
				.comprobarAtributoCorrectoDNI(datosEntradaDniUsuario.getDniUsuario());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.IDENTIFICADOR_CORRECTO, Constantes.EXITO, datosEntradaDniUsuario.getDniUsuario(),
				Constantes.DNI_USUARIO);

	}

	public DatosPruebaAtributos getTestDniUsuarioCorrectoValido(final UsuarioEntity datosEntradaDniUsuario) {

		final String resultadoObtenido = validacionesAtributosCorrectoDNI
				.comprobarAtributoDNIValido(datosEntradaDniUsuario.getDniUsuario());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.IDENTIFICADOR_CORRECTO, Constantes.EXITO, datosEntradaDniUsuario.getDniUsuario(),
				Constantes.DNI_USUARIO);

	}

	public DatosPruebaAtributos getTestDniUsuarioCorrectoNoValido(final UsuarioEntity datosEntradaDniUsuario) {

		final String resultadoObtenido = validacionesAtributosCorrectoDNI
				.comprobarAtributoDNIValido(datosEntradaDniUsuario.getDniUsuario());

		final String resultadoEsperado = CodigosMensajes.DNI_PERSONA_NO_VALIDO + " - " + Mensajes.DNI_NO_ES_VALIDO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.IDENTIFICADOR_INCORRECTO, Constantes.ERROR, datosEntradaDniUsuario.getDniUsuario(),
				Constantes.DNI_USUARIO);
	}
}
