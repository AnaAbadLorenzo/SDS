package com.sds.service.test.impl.atributos;

import java.text.ParseException;

import com.sds.model.PersonaEntity;
import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.DefinicionPruebas;
import com.sds.service.common.Mensajes;
import com.sds.service.common.enumerados.Atributo;
import com.sds.service.common.enumerados.Funcionalidad;
import com.sds.service.test.model.DatosPruebaAtributos;
import com.sds.service.test.util.CrearDatosPruebaAtributos;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosBlank;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCorrectoAlfanumerico;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMayor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoDireccionPersona {
	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoDireccionPersona() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosMayor = new ValidacionesAtributosMayor();
		validacionesAtributosCorrectoAlfanumerico = new ValidacionesAtributosCorrectoAlfanumerico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestDireccionPersonaVacio(final PersonaEntity datosEntradaDireccionPersonaVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaDireccionPersonaVacio.getDireccionP(), Funcionalidad.GESTION_PERSONAS, Atributo.DIRECCION);

		final String resultadoEsperado = CodigosMensajes.DIRECCION_VACIO + " - "
				+ Mensajes.DIRECCION_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaDireccionPersonaVacio.getDireccionP(),
				Constantes.DIRECCIONP);
	}

	public DatosPruebaAtributos getTestDireccionPersonaAlfanumericoMenor3(
			final PersonaEntity datosEntradaDireccionPersonaAlfanumericoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaDireccionPersonaAlfanumericoMenor3.getDireccionP(), Funcionalidad.GESTION_PERSONAS,
				Atributo.DIRECCION, 3);

		final String resultadoEsperado = CodigosMensajes.DIRECCION_MENOR_QUE_3 + " - "
				+ Mensajes.DIRECCION_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_3, Constantes.ERROR,
				datosEntradaDireccionPersonaAlfanumericoMenor3.getDireccionP(), Constantes.DIRECCIONP);
	}

	public DatosPruebaAtributos getTestDireccionPersonaAlfanumericoMayor128(
			final PersonaEntity datosEntradaDireccionPersonaAlfanumericoMayor128) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaDireccionPersonaAlfanumericoMayor128.getDireccionP(), Funcionalidad.GESTION_PERSONAS,
				Atributo.DIRECCION, 128);

		final String resultadoEsperado = CodigosMensajes.DIRECCION_MAYOR_QUE_128 + " - "
				+ Mensajes.DIRECCION_NO_PUEDE_SER_MAYOR_QUE_128;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MAYOR_128, Constantes.ERROR,
				datosEntradaDireccionPersonaAlfanumericoMayor128.getDireccionP(), Constantes.DIRECCIONP);
	}

	public DatosPruebaAtributos getTestDireccionPCorrectoAlfanumerico(final PersonaEntity datosEntradaDireccionP)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(datosEntradaDireccionP.getDireccionP());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.DIRECCION_CORRECTO, Constantes.EXITO, datosEntradaDireccionP.getDireccionP(),
				Constantes.DIRECCIONP);

	}
}
