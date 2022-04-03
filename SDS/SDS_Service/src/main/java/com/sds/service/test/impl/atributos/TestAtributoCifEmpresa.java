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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosAcentos;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosBlank;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCaracteresEspeciales;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCorrectoCIF;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEnhe;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEspacios;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMayor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoCifEmpresa {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosEnhe validacionesAtributosEnhe;
	private final ValidacionesAtributosAcentos validacionesAtributosAcentos;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoCIF validacionesAtributosCorrectoCIF;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoCifEmpresa() {
		validacionesAtributosBlank = new ValidacionesAtributosBlank();
		validacionesAtributosEnhe = new ValidacionesAtributosEnhe();
		validacionesAtributosAcentos = new ValidacionesAtributosAcentos();
		validacionesAtributosCaracteresEspeciales = new ValidacionesAtributosCaracteresEspeciales();
		validacionesAtributosEspacios = new ValidacionesAtributosEspacios();
		validacionesAtributosMenor = new ValidacionesAtributosMenor();
		validacionesAtributosMayor = new ValidacionesAtributosMayor();
		validacionesAtributosCorrectoCIF = new ValidacionesAtributosCorrectoCIF();
		crearDatosPruebaAtributos = new CrearDatosPruebaAtributos();
	}

	public DatosPruebaAtributos getTestCifEmpresaVacio(final EmpresaEntity datosEntradaCifEmpresaVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaCifEmpresaVacio.getCifEmpresa(), Funcionalidad.GESTION_EMPRESAS, Atributo.CIF_EMPRESA);

		final String resultadoEsperado = CodigosMensajes.CIF_EMPRESA_VACIO + " - "
				+ Mensajes.CIF_EMPRESA_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaCifEmpresaVacio.getCifEmpresa(),
				Constantes.CIF_EMPRESA);
	}

	public DatosPruebaAtributos getTestCifEmpresaAlfanumericoEnhe(
			final EmpresaEntity datosEntradaCifEmpresaAlfanumericoEnhe) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEnhe.comprobarAtributoEnhe(
				datosEntradaCifEmpresaAlfanumericoEnhe.getCifEmpresa(), Funcionalidad.GESTION_EMPRESAS,
				Atributo.CIF_EMPRESA);

		final String resultadoEsperado = CodigosMensajes.CIF_EMPRESA_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.CIF_EMPRESA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ENHE, Constantes.ERROR,
				datosEntradaCifEmpresaAlfanumericoEnhe.getCifEmpresa(), Constantes.CIF_EMPRESA);

	}

	public DatosPruebaAtributos getTestCifEmpresaAlfanumericoAcentos(
			final EmpresaEntity datosEntradaCifEmpresaAlfanumericoAcentos) throws ParseException {

		final String resultadoObtenido = validacionesAtributosAcentos.comprobarAtributoAcentos(
				datosEntradaCifEmpresaAlfanumericoAcentos.getCifEmpresa(), Funcionalidad.GESTION_EMPRESAS,
				Atributo.CIF_EMPRESA);

		final String resultadoEsperado = CodigosMensajes.CIF_EMPRESA_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.CIF_EMPRESA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ACENTOS, Constantes.ERROR,
				datosEntradaCifEmpresaAlfanumericoAcentos.getCifEmpresa(), Constantes.CIF_EMPRESA);

	}

	public DatosPruebaAtributos getTestCifEmpresaAlfanumericoCaracteresEspeciales(
			final EmpresaEntity datosEntradaCifEmpresaAlfanumericoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaCifEmpresaAlfanumericoCaracteresEspeciales.getCifEmpresa(),
						Funcionalidad.GESTION_EMPRESAS, Atributo.CIF_EMPRESA);

		final String resultadoEsperado = CodigosMensajes.CIF_EMPRESA_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.CIF_EMPRESA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaCifEmpresaAlfanumericoCaracteresEspeciales.getCifEmpresa(), Constantes.CIF_EMPRESA);
	}

	public DatosPruebaAtributos getTestCifEmpresaAlfanumericoEspacios(
			final EmpresaEntity datosEntradaCifEmpresaAlfanumericoEspacios) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaCifEmpresaAlfanumericoEspacios.getCifEmpresa(), Funcionalidad.GESTION_EMPRESAS,
				Atributo.CIF_EMPRESA);

		final String resultadoEsperado = CodigosMensajes.CIF_EMPRESA_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.CIF_EMPRESA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ESPACIOS, Constantes.ERROR,
				datosEntradaCifEmpresaAlfanumericoEspacios.getCifEmpresa(), Constantes.CIF_EMPRESA);
	}

	public DatosPruebaAtributos getTestCifEmpresaAlfanumericoMenor9(
			final EmpresaEntity datosEntradaCifEmpresaAlfanumericoMenor9) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaCifEmpresaAlfanumericoMenor9.getCifEmpresa(), Funcionalidad.GESTION_EMPRESAS,
				Atributo.CIF_EMPRESA, 9);

		final String resultadoEsperado = CodigosMensajes.CIF_EMPRESA_MENOR_QUE_9 + " - "
				+ Mensajes.CIF_EMPRESA_NO_PUEDE_SER_MENOR_QUE_9;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_9, Constantes.ERROR,
				datosEntradaCifEmpresaAlfanumericoMenor9.getCifEmpresa(), Constantes.CIF_EMPRESA);
	}

	public DatosPruebaAtributos getTestCifEmpresaAlfanumericoMayor9(
			final EmpresaEntity datosEntradaCifEmpresaAlfanumericoMayor9) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaCifEmpresaAlfanumericoMayor9.getCifEmpresa(), Funcionalidad.GESTION_EMPRESAS,
				Atributo.CIF_EMPRESA, 9);

		final String resultadoEsperado = CodigosMensajes.CIF_EMPRESA_MAYOR_QUE_9 + " - "
				+ Mensajes.CIF_EMPRESA_NO_PUEDE_SER_MAYOR_QUE_9;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MAYOR_9, Constantes.ERROR,
				datosEntradaCifEmpresaAlfanumericoMayor9.getCifEmpresa(), Constantes.CIF_EMPRESA);
	}

	public DatosPruebaAtributos getTestCifEmpresaCorrectoAlfanumerico(final EmpresaEntity datosEntradaCifEmpresa) {

		final String resultadoObtenido = validacionesAtributosCorrectoCIF
				.comprobarAtributoCorrectoCIF(datosEntradaCifEmpresa.getCifEmpresa());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.IDENTIFICADOR_CORRECTO, Constantes.EXITO, datosEntradaCifEmpresa.getCifEmpresa(),
				Constantes.CIF_EMPRESA);

	}
}
