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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCorrectoAlfanumerico;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEnhe;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEspacios;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMayor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoEmailPersonaRegistro {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosEnhe validacionesAtributosEnhe;
	private final ValidacionesAtributosAcentos validacionesAtributosAcentos;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoEmailPersonaRegistro() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosEnhe = new ValidacionesAtributosEnhe();
		validacionesAtributosAcentos = new ValidacionesAtributosAcentos();
		validacionesAtributosEspacios = new ValidacionesAtributosEspacios();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosMayor = new ValidacionesAtributosMayor();
		validacionesAtributosCorrectoAlfanumerico = new ValidacionesAtributosCorrectoAlfanumerico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestRegistroEmailPersonaVacio(final Registro datosEntradaRegistroEmailPersonaVacio) {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaRegistroEmailPersonaVacio.getDatosPersona().getEmailP(), Funcionalidad.REGISTRAR, Atributo.EMAIL);

		final String resultadoEsperado = CodigosMensajes.EMAIL_VACIO + " - "
				+ Mensajes.EMAIL_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaRegistroEmailPersonaVacio.getDatosPersona().getEmailP(),
				Constantes.EMAILP);
	}

	public DatosPruebaAtributos getTestRegistroEmailPersonaAlfanumericoEnhe(final Registro datosEntradaRegistroEmailPersonaAlfanumericoEnhe) {

		final String resultadoObtenido = validacionesAtributosEnhe.comprobarAtributoEnhe(
				datosEntradaRegistroEmailPersonaAlfanumericoEnhe.getDatosPersona().getEmailP(), Funcionalidad.REGISTRAR,
				Atributo.EMAIL);

		final String resultadoEsperado = CodigosMensajes.EMAIL_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.EMAIL_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ENHE, Constantes.ERROR,
				datosEntradaRegistroEmailPersonaAlfanumericoEnhe.getDatosPersona().getEmailP(), Constantes.EMAILP);

	}

	public DatosPruebaAtributos getTestRegistroEmailPersonaAlfanumericoAcentos(
			final Registro datosEntradaRegistroEmailPersonaAlfanumericoAcentos) {

		final String resultadoObtenido = validacionesAtributosAcentos.comprobarAtributoAcentos(
				datosEntradaRegistroEmailPersonaAlfanumericoAcentos.getDatosPersona().getEmailP(), Funcionalidad.REGISTRAR,
				Atributo.EMAIL);

		final String resultadoEsperado = CodigosMensajes.EMAIL_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.EMAIL_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ACENTOS, Constantes.ERROR,
				datosEntradaRegistroEmailPersonaAlfanumericoAcentos.getDatosPersona().getEmailP(), Constantes.EMAILP);

	}


	public DatosPruebaAtributos getTestRegistroEmailPAlfanumericoEspacios(
			final Registro datosEntradaRegistroEmailPAlfanumericoEspacios) {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaRegistroEmailPAlfanumericoEspacios.getDatosPersona().getEmailP(), Funcionalidad.REGISTRAR,
				Atributo.EMAIL);

		final String resultadoEsperado = CodigosMensajes.EMAIL_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.EMAIL_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ESPACIOS, Constantes.ERROR,
				datosEntradaRegistroEmailPAlfanumericoEspacios.getDatosPersona().getEmailP(), Constantes.EMAILP);
	}

	public DatosPruebaAtributos getTestRegistroEmailPAlfanumericoMenor4(
			final Registro datosEntradaRegistroEmailPAlfanumericoMenor4) {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaRegistroEmailPAlfanumericoMenor4.getDatosPersona().getEmailP(), Funcionalidad.REGISTRAR,
				Atributo.EMAIL, 4);

		final String resultadoEsperado = CodigosMensajes.EMAIL_MENOR_QUE_4 + " - "
				+ Mensajes.EMAIL_NO_PUEDE_SER_MENOR_QUE_4;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_4, Constantes.ERROR,
				datosEntradaRegistroEmailPAlfanumericoMenor4.getDatosPersona().getEmailP(), Constantes.EMAILP);
	}

	public DatosPruebaAtributos getTestRegistroEmailPAlfanumericoMayor128(
			final Registro datosEntradaRegistroEmailPAlfanumericoMayor128) {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaRegistroEmailPAlfanumericoMayor128.getDatosPersona().getEmailP(), Funcionalidad.REGISTRAR,
				Atributo.EMAIL, 48);

		final String resultadoEsperado = CodigosMensajes.EMAIL_MAYOR_QUE_128 + " - "
				+ Mensajes.EMAIL_NO_PUEDE_SER_MAYOR_QUE_128;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MAYOR_48, Constantes.ERROR,
				datosEntradaRegistroEmailPAlfanumericoMayor128.getDatosPersona().getEmailP(), Constantes.EMAILP);
	}

	public DatosPruebaAtributos getTestRegistroEmailPCorrectoAlfanumerico(final Registro datosEntradaRegistroEmailP) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(datosEntradaRegistroEmailP.getDatosPersona().getEmailP());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CORRECTO, Constantes.EXITO,
				datosEntradaRegistroEmailP.getDatosPersona().getEmailP(), Constantes.EMAILP);

	}
}