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

public class TestAtributoDireccionEmpresaRegistro {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoDireccionEmpresaRegistro() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosMayor = new ValidacionesAtributosMayor();
		validacionesAtributosCorrectoAlfanumerico = new ValidacionesAtributosCorrectoAlfanumerico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestRegistroDireccionEmpresaVacio(
			final Registro datosEntradaRegistroDireccionEmpresaVacio) {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaRegistroDireccionEmpresaVacio.getDatosEmpresa().getDireccionEmpresa(),
				Funcionalidad.REGISTRAR, Atributo.DIRECCION);

		final String resultadoEsperado = CodigosMensajes.DIRECCION_VACIO + " - "
				+ Mensajes.DIRECCION_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				datosEntradaRegistroDireccionEmpresaVacio.getDatosEmpresa().getDireccionEmpresa(),
				Constantes.DIRECCION_EMPRESA);
	}

	public DatosPruebaAtributos getTestRegistroDireccionEmpresaAlfanumericoMenor3(
			final Registro datosEntradaRegistroDireccionEmpresaAlfanumericoMenor3) {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaRegistroDireccionEmpresaAlfanumericoMenor3.getDatosEmpresa().getDireccionEmpresa(),
				Funcionalidad.REGISTRAR, Atributo.DIRECCION, 3);

		final String resultadoEsperado = CodigosMensajes.DIRECCION_MENOR_QUE_3 + " - "
				+ Mensajes.DIRECCION_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MENOR_3, Constantes.ERROR,
				datosEntradaRegistroDireccionEmpresaAlfanumericoMenor3.getDatosEmpresa().getDireccionEmpresa(),
				Constantes.DIRECCION_EMPRESA);
	}

	public DatosPruebaAtributos getTestRegistroDireccionEmpresaAlfanumericoMayor128(
			final Registro datosEntradaRegistroDireccionEmpresaAlfanumericoMayor128) {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaRegistroDireccionEmpresaAlfanumericoMayor128.getDatosEmpresa().getDireccionEmpresa(),
				Funcionalidad.REGISTRAR, Atributo.DIRECCION, 128);

		final String resultadoEsperado = CodigosMensajes.DIRECCION_MAYOR_QUE_128 + " - "
				+ Mensajes.DIRECCION_NO_PUEDE_SER_MAYOR_QUE_128;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MAYOR_128, Constantes.ERROR,
				datosEntradaRegistroDireccionEmpresaAlfanumericoMayor128.getDatosEmpresa().getDireccionEmpresa(),
				Constantes.DIRECCION_EMPRESA);
	}

	public DatosPruebaAtributos getTestRegistroDireccionEmpresaCorrectoAlfanumerico(
			final Registro datosEntradaRegistroDireccionEmpresa) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(
						datosEntradaRegistroDireccionEmpresa.getDatosEmpresa().getDireccionEmpresa());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.DIRECCION_CORRECTO, Constantes.EXITO,
				datosEntradaRegistroDireccionEmpresa.getDatosEmpresa().getDireccionEmpresa(),
				Constantes.DIRECCION_EMPRESA);

	}

}
