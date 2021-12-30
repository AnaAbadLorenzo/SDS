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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosBlank;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCorrectoAlfanumerico;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMayor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoDireccionPersonaRegistro {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoDireccionPersonaRegistro() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosMayor = new ValidacionesAtributosMayor();
		validacionesAtributosCorrectoAlfanumerico = new ValidacionesAtributosCorrectoAlfanumerico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestRegistroDireccionPersonaVacio(
			final Registro datosEntradaRegistroDireccionPersonaVacio) {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaRegistroDireccionPersonaVacio.getDatosPersona().getDireccionP(), Funcionalidad.REGISTRAR,
				Atributo.DIRECCION);

		final String resultadoEsperado = CodigosMensajes.DIRECCION_VACIO + " - "
				+ Mensajes.DIRECCION_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				datosEntradaRegistroDireccionPersonaVacio.getDatosPersona().getDireccionP(), Constantes.DIRECCIONP);
	}

	public DatosPruebaAtributos getTestRegistroDireccionPersonaAlfanumericoMenor3(
			final Registro datosEntradaRegistroDireccionPersonaAlfanumericoMenor3) {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaRegistroDireccionPersonaAlfanumericoMenor3.getDatosPersona().getDireccionP(),
				Funcionalidad.REGISTRAR, Atributo.DIRECCION, 3);

		final String resultadoEsperado = CodigosMensajes.DIRECCION_MENOR_QUE_3 + " - "
				+ Mensajes.DIRECCION_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_3, Constantes.ERROR,
				datosEntradaRegistroDireccionPersonaAlfanumericoMenor3.getDatosPersona().getDireccionP(),
				Constantes.DIRECCIONP);
	}

	public DatosPruebaAtributos getTestRegistroDireccionPersonaAlfanumericoMayor128(
			final Registro datosEntradaRegistroDireccionPersonaAlfanumericoMayor128) {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaRegistroDireccionPersonaAlfanumericoMayor128.getDatosPersona().getDireccionP(),
				Funcionalidad.REGISTRAR, Atributo.DIRECCION, 128);

		final String resultadoEsperado = CodigosMensajes.DIRECCION_MAYOR_QUE_128 + " - "
				+ Mensajes.DIRECCION_NO_PUEDE_SER_MAYOR_QUE_128;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MAYOR_128, Constantes.ERROR,
				datosEntradaRegistroDireccionPersonaAlfanumericoMayor128.getDatosPersona().getDireccionP(),
				Constantes.DIRECCIONP);
	}

	public DatosPruebaAtributos getTestRegistroDireccionPCorrectoAlfanumerico(
			final Registro datosEntradaRegistroDireccionP) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(
						datosEntradaRegistroDireccionP.getDatosPersona().getDireccionP());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.DIRECCION_CORRECTO, Constantes.EXITO,
				datosEntradaRegistroDireccionP.getDatosPersona().getDireccionP(), Constantes.DIRECCIONP);

	}

}
