package com.sds.service.test.impl.atributos;

import java.text.ParseException;

import com.sds.model.EmpresaEntity;
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

public class TestAtributoDireccionEmpresa {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoDireccionEmpresa() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosMayor = new ValidacionesAtributosMayor();
		validacionesAtributosCorrectoAlfanumerico = new ValidacionesAtributosCorrectoAlfanumerico();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestDireccionEmpresaVacio(final EmpresaEntity datosEntradaDireccionEmpresaVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaDireccionEmpresaVacio.getDireccionEmpresa(), Funcionalidad.GESTION_EMPRESAS,
				Atributo.DIRECCION);

		final String resultadoEsperado = CodigosMensajes.DIRECCION_VACIO + " - "
				+ Mensajes.DIRECCION_NO_PUEDE_SER_VACIA;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaDireccionEmpresaVacio.getDireccionEmpresa(),
				Constantes.DIRECCION_EMPRESA);
	}

	public DatosPruebaAtributos getTestDireccionEmpresaAlfanumericoMenor3(
			final EmpresaEntity datosEntradaDireccionEmpresaAlfanumericoMenor3) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaDireccionEmpresaAlfanumericoMenor3.getDireccionEmpresa(), Funcionalidad.GESTION_EMPRESAS,
				Atributo.DIRECCION, 3);

		final String resultadoEsperado = CodigosMensajes.DIRECCION_MENOR_QUE_3 + " - "
				+ Mensajes.DIRECCION_NO_PUEDE_SER_MENOR_QUE_3;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MENOR_3, Constantes.ERROR,
				datosEntradaDireccionEmpresaAlfanumericoMenor3.getDireccionEmpresa(), Constantes.DIRECCION_EMPRESA);
	}

	public DatosPruebaAtributos getTestDireccionEmpresaAlfanumericoMayor128(
			final EmpresaEntity datosEntradaDireccionEmpresaAlfanumericoMayor128) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaDireccionEmpresaAlfanumericoMayor128.getDireccionEmpresa(), Funcionalidad.REGISTRAR,
				Atributo.DIRECCION, 128);

		final String resultadoEsperado = CodigosMensajes.DIRECCION_MAYOR_QUE_128 + " - "
				+ Mensajes.DIRECCION_NO_PUEDE_SER_MAYOR_QUE_128;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFABETICO_MAYOR_128, Constantes.ERROR,
				datosEntradaDireccionEmpresaAlfanumericoMayor128.getDireccionEmpresa(), Constantes.DIRECCION_EMPRESA);
	}

	public DatosPruebaAtributos getTestDireccionEmpresaCorrectoAlfanumerico(
			final EmpresaEntity datosEntradaDireccionEmpresa) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(datosEntradaDireccionEmpresa.getDireccionEmpresa());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.DIRECCION_CORRECTO, Constantes.EXITO,
				datosEntradaDireccionEmpresa.getDireccionEmpresa(), Constantes.DIRECCION_EMPRESA);

	}

}
