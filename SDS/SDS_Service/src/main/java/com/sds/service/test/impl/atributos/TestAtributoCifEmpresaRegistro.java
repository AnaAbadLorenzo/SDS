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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCaracteresEspeciales;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCorrectoAlfanumerico;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEnhe;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEspacios;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMayor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoCifEmpresaRegistro {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosEnhe validacionesAtributosEnhe;
	private final ValidacionesAtributosAcentos validacionesAtributosAcentos;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoCifEmpresaRegistro() {
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

	public DatosPruebaAtributos getTestRegistroCifEmpresaVacio(final Registro datosEntradaRegistroCifEmpresaVacio) {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaRegistroCifEmpresaVacio.getDatosEmpresa().getCifEmpresa(), Funcionalidad.REGISTRAR,
				Atributo.CIF_EMPRESA);

		final String resultadoEsperado = CodigosMensajes.CIF_EMPRESA_VACIO + " - "
				+ Mensajes.CIF_EMPRESA_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				datosEntradaRegistroCifEmpresaVacio.getDatosEmpresa().getCifEmpresa(), Constantes.CIF_EMPRESA);
	}

	public DatosPruebaAtributos getTestRegistroCifEmpresaAlfanumericoEnhe(
			final Registro datosEntradaRegistroCifEmpresaAlfanumericoEnhe) {

		final String resultadoObtenido = validacionesAtributosEnhe.comprobarAtributoEnhe(
				datosEntradaRegistroCifEmpresaAlfanumericoEnhe.getDatosEmpresa().getCifEmpresa(),
				Funcionalidad.REGISTRAR, Atributo.CIF_EMPRESA);

		final String resultadoEsperado = CodigosMensajes.CIF_EMPRESA_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.CIF_EMPRESA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ENHE, Constantes.ERROR,
				datosEntradaRegistroCifEmpresaAlfanumericoEnhe.getDatosEmpresa().getCifEmpresa(),
				Constantes.CIF_EMPRESA);

	}

	public DatosPruebaAtributos getTestRegistroCifEmpresaAlfanumericoAcentos(
			final Registro datosEntradaRegistroCifEmpresaAlfanumericoAcentos) {

		final String resultadoObtenido = validacionesAtributosAcentos.comprobarAtributoAcentos(
				datosEntradaRegistroCifEmpresaAlfanumericoAcentos.getDatosEmpresa().getCifEmpresa(),
				Funcionalidad.REGISTRAR, Atributo.CIF_EMPRESA);

		final String resultadoEsperado = CodigosMensajes.CIF_EMPRESA_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.CIF_EMPRESA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ACENTOS, Constantes.ERROR,
				datosEntradaRegistroCifEmpresaAlfanumericoAcentos.getDatosEmpresa().getCifEmpresa(),
				Constantes.CIF_EMPRESA);

	}

	public DatosPruebaAtributos getTestRegistroCifEmpresaAlfanumericoCaracteresEspeciales(
			final Registro datosEntradaRegistroCifEmpresaAlfanumericoCaracteresEspeciales) {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(datosEntradaRegistroCifEmpresaAlfanumericoCaracteresEspeciales
						.getDatosEmpresa().getCifEmpresa(), Funcionalidad.REGISTRAR, Atributo.CIF_EMPRESA);

		final String resultadoEsperado = CodigosMensajes.CIF_EMPRESA_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.CIF_EMPRESA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaRegistroCifEmpresaAlfanumericoCaracteresEspeciales.getDatosEmpresa().getCifEmpresa(),
				Constantes.CIF_EMPRESA);
	}

	public DatosPruebaAtributos getTestRegistroCifEmpresaAlfanumericoEspacios(
			final Registro datosEntradaRegistroCifEmpresaAlfanumericoEspacios) {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaRegistroCifEmpresaAlfanumericoEspacios.getDatosEmpresa().getCifEmpresa(),
				Funcionalidad.REGISTRAR, Atributo.CIF_EMPRESA);

		final String resultadoEsperado = CodigosMensajes.CIF_EMPRESA_ALFANUMERICO_INCORRECTO + " - "
				+ Mensajes.CIF_EMPRESA_NO_PUEDE_CONTENER_MAS_QUE_LETRAS_Y_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_ESPACIOS, Constantes.ERROR,
				datosEntradaRegistroCifEmpresaAlfanumericoEspacios.getDatosEmpresa().getCifEmpresa(),
				Constantes.CIF_EMPRESA);
	}

	public DatosPruebaAtributos getTestRegistroCifEmpresaAlfanumericoMenor9(
			final Registro datosEntradaRegistroCifEmpresaAlfanumericoMenor9) {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaRegistroCifEmpresaAlfanumericoMenor9.getDatosEmpresa().getCifEmpresa(),
				Funcionalidad.REGISTRAR, Atributo.CIF_EMPRESA, 9);

		final String resultadoEsperado = CodigosMensajes.CIF_EMPRESA_MENOR_QUE_9 + " - "
				+ Mensajes.CIF_EMPRESA_NO_PUEDE_SER_MENOR_QUE_9;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MENOR_9, Constantes.ERROR,
				datosEntradaRegistroCifEmpresaAlfanumericoMenor9.getDatosEmpresa().getCifEmpresa(),
				Constantes.CIF_EMPRESA);
	}

	public DatosPruebaAtributos getTestRegistroCifEmpresaAlfanumericoMayor9(
			final Registro datosEntradaRegistroCifEmpresaAlfanumericoMayor9) {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaRegistroCifEmpresaAlfanumericoMayor9.getDatosEmpresa().getCifEmpresa(),
				Funcionalidad.REGISTRAR, Atributo.CIF_EMPRESA, 9);

		final String resultadoEsperado = CodigosMensajes.CIF_EMPRESA_MAYOR_QUE_9 + " - "
				+ Mensajes.CIF_EMPRESA_NO_PUEDE_SER_MAYOR_QUE_9;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.ALFANUMERICO_MAYOR_9, Constantes.ERROR,
				datosEntradaRegistroCifEmpresaAlfanumericoMayor9.getDatosEmpresa().getCifEmpresa(),
				Constantes.CIF_EMPRESA);
	}

	public DatosPruebaAtributos getTestRegistroCifEmpresaCorrectoAlfanumerico(
			final Registro datosEntradaRegistroCifEmpresa) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(
						datosEntradaRegistroCifEmpresa.getDatosEmpresa().getCifEmpresa());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.IDENTIFICADOR_CORRECTO, Constantes.EXITO,
				datosEntradaRegistroCifEmpresa.getDatosEmpresa().getCifEmpresa(), Constantes.CIF_EMPRESA);

	}

}
