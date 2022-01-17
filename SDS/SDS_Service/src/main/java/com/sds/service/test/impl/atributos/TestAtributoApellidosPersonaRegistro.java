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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosBlank;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCaracteresEspeciales;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCorrectoAlfabetico;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMayor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosNumerico;

public class TestAtributoApellidosPersonaRegistro {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosNumerico validacionesAtributosNumerico;
	private final ValidacionesAtributosCorrectoAlfabetico validacionesAtributosCorrectoAlfabetico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoApellidosPersonaRegistro() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosCaracteresEspeciales = new ValidacionesAtributosCaracteresEspeciales();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosMayor = new ValidacionesAtributosMayor();
		validacionesAtributosNumerico = new ValidacionesAtributosNumerico();
		validacionesAtributosCorrectoAlfabetico = new ValidacionesAtributosCorrectoAlfabetico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestRegistroApellidosPersonaVacio(
			final Registro datosEntradaRegistroApellidosPersonaVacio) throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaRegistroApellidosPersonaVacio.getDatosPersona().getApellidosP(), Funcionalidad.REGISTRAR,
				Atributo.APELLIDOS_PERSONA);

		final String resultadoEsperado = CodigosMensajes.APELLIDOS_PERSONA_VACIO + " - "
				+ Mensajes.APELLIDOS_PERSONA_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				datosEntradaRegistroApellidosPersonaVacio.getDatosPersona().getApellidosP(), Constantes.APELLIDOSP);
	}

	public DatosPruebaAtributos getTestRegistroApellidosPAlfanumericoCaracteresEspeciales(
			final Registro datosEntradaRegistroApellidosPAlfanumericoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(datosEntradaRegistroApellidosPAlfanumericoCaracteresEspeciales
						.getDatosPersona().getApellidosP(), Funcionalidad.REGISTRAR, Atributo.APELLIDOS_PERSONA);

		final String resultadoEsperado = CodigosMensajes.APELLIDOS_PERSONA_LETRAS_ACENTOS_INCORRECTO + " - "
				+ Mensajes.APELLIDOS_PERSONA_NO_PUEDEN_CONTENER_MAS_QUE_LETRAS_Y_ACENTOS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaRegistroApellidosPAlfanumericoCaracteresEspeciales.getDatosPersona().getApellidosP(),
				Constantes.APELLIDOSP);
	}

	public DatosPruebaAtributos getTestRegistroApellidosPAlfanumericoMenor3(
			final Registro datosEntradaRegistroApellidosPersonaAlfanumericoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaRegistroApellidosPersonaAlfanumericoMenor3.getDatosPersona().getApellidosP(),
				Funcionalidad.REGISTRAR, Atributo.APELLIDOS_PERSONA, 3);

		final String resultadoEsperado = CodigosMensajes.APELLIDOS_PERSONA_MENOR_QUE_3 + " - "
				+ Mensajes.APELLIDOS_PERSONA_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MENOR_3, Constantes.ERROR,
				datosEntradaRegistroApellidosPersonaAlfanumericoMenor3.getDatosPersona().getApellidosP(),
				Constantes.APELLIDOSP);
	}

	public DatosPruebaAtributos getTestRegistroApellidosPAlfanumericoMayor128(
			final Registro datosEntradaRegistroApellidosPAlfanumericoMayor128) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaRegistroApellidosPAlfanumericoMayor128.getDatosPersona().getApellidosP(),
				Funcionalidad.REGISTRAR, Atributo.APELLIDOS_PERSONA, 128);

		final String resultadoEsperado = CodigosMensajes.APELLIDOS_PERSONA_MAYOR_QUE_128 + " - "
				+ Mensajes.APELlIDOS_PERSONA_NO_PUEDE_SER_MAYOR_QUE_128;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MAYOR_128, Constantes.ERROR,
				datosEntradaRegistroApellidosPAlfanumericoMayor128.getDatosPersona().getApellidosP(),
				Constantes.APELLIDOSP);
	}

	public DatosPruebaAtributos getTestRegistroAppellidosPNumerico(
			final Registro datosEntradaRegistroApellidosPNumerico) throws ParseException {
		final String resultadoObtenido = validacionesAtributosNumerico.comprobarAtributoNumerico(
				datosEntradaRegistroApellidosPNumerico.getDatosPersona().getApellidosP(), Funcionalidad.REGISTRAR,
				Atributo.APELLIDOS_PERSONA);

		final String resultadoEsperado = CodigosMensajes.APELLIDOS_PERSONA_LETRAS_ACENTOS_INCORRECTO + " - "
				+ Mensajes.APELLIDOS_PERSONA_NO_PUEDEN_CONTENER_MAS_QUE_LETRAS_Y_ACENTOS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO, Constantes.ERROR,
				datosEntradaRegistroApellidosPNumerico.getDatosPersona().getApellidosP(), Constantes.APELLIDOSP);

	}

	public DatosPruebaAtributos getTestRegistroApellidosPCorrectoAlfabetico(
			final Registro datosEntradaRegistroApellidosP) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfabetico
				.comprobarAtributoCorrectoAlfabetico(datosEntradaRegistroApellidosP.getDatosPersona().getApellidosP());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_CORRECTO, Constantes.EXITO,
				datosEntradaRegistroApellidosP.getDatosPersona().getApellidosP(), Constantes.APELLIDOSP);

	}
}
