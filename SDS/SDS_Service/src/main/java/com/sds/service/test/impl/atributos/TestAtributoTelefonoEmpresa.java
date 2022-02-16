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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCorrectoAlfanumerico;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEnhe;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEspacios;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMayor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoTelefonoEmpresa {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosEnhe validacionesAtributosEnhe;
	private final ValidacionesAtributosAcentos validacionesAtributosAcentos;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoTelefonoEmpresa() {
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

	public DatosPruebaAtributos getTestTelefonoEmpresaVacio(final EmpresaEntity datosEntradaTelefonoEmpresaVacio)
			throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaTelefonoEmpresaVacio.getTelefonoEmpresa(), Funcionalidad.GESTION_EMPRESAS,
				Atributo.TELEFONO);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_VACIO + " - " + Mensajes.TELEFONO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR, datosEntradaTelefonoEmpresaVacio.getTelefonoEmpresa(),
				Constantes.TELEFONO_EMPRESA);
	}

	public DatosPruebaAtributos getTestTelefonoEmpresaNumericoEnhe(
			final EmpresaEntity datosEntradaTelefonoEmpresaNumericoEnhe) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEnhe.comprobarAtributoEnhe(
				datosEntradaTelefonoEmpresaNumericoEnhe.getTelefonoEmpresa(), Funcionalidad.GESTION_EMPRESAS,
				Atributo.TELEFONO);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_NUMERICO_INCORRECTO + " - "
				+ Mensajes.TELEFONO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ENHE, Constantes.ERROR,
				datosEntradaTelefonoEmpresaNumericoEnhe.getTelefonoEmpresa(), Constantes.TELEFONO_EMPRESA);

	}

	public DatosPruebaAtributos getTestTelefonoEmpresaNumericoAcentos(
			final EmpresaEntity datosEntradaTelefonoEmpresaNumericoAcentos) throws ParseException {

		final String resultadoObtenido = validacionesAtributosAcentos.comprobarAtributoAcentos(
				datosEntradaTelefonoEmpresaNumericoAcentos.getTelefonoEmpresa(), Funcionalidad.GESTION_EMPRESAS,
				Atributo.TELEFONO);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_NUMERICO_INCORRECTO + " - "
				+ Mensajes.TELEFONO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ACENTOS, Constantes.ERROR,
				datosEntradaTelefonoEmpresaNumericoAcentos.getTelefonoEmpresa(), Constantes.TELEFONO_EMPRESA);

	}

	public DatosPruebaAtributos getTestTelefonoEmpresaNumericoCaracteresEspeciales(
			final EmpresaEntity datosEntradaTelefonoEmpresaNumericoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(
						datosEntradaTelefonoEmpresaNumericoCaracteresEspeciales.getTelefonoEmpresa(),
						Funcionalidad.GESTION_EMPRESAS, Atributo.TELEFONO);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_NUMERICO_INCORRECTO + " - "
				+ Mensajes.TELEFONO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaTelefonoEmpresaNumericoCaracteresEspeciales.getTelefonoEmpresa(),
				Constantes.TELEFONO_EMPRESA);
	}

	public DatosPruebaAtributos getTestTelefonoEmpresaAlfanumericoEspacios(
			final EmpresaEntity datosEntradaTelefonoEmpresaAlfanumericoEspacios) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaTelefonoEmpresaAlfanumericoEspacios.getTelefonoEmpresa(), Funcionalidad.GESTION_EMPRESAS,
				Atributo.TELEFONO);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_NUMERICO_INCORRECTO + " - "
				+ Mensajes.TELEFONO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ESPACIOS, Constantes.ERROR,
				datosEntradaTelefonoEmpresaAlfanumericoEspacios.getTelefonoEmpresa(), Constantes.TELEFONO_EMPRESA);
	}

	public DatosPruebaAtributos getTestTelefonoEmpresaNumericoMenor9(
			final EmpresaEntity datosEntradaTelefonoEmpresaNumericoMenor9) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaTelefonoEmpresaNumericoMenor9.getTelefonoEmpresa(), Funcionalidad.GESTION_EMPRESAS,
				Atributo.TELEFONO, 9);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_MENOR_QUE_9 + " - "
				+ Mensajes.TELEFONO_NO_PUEDE_SER_MENOR_QUE_9;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_MENOR_9, Constantes.ERROR,
				datosEntradaTelefonoEmpresaNumericoMenor9.getTelefonoEmpresa(), Constantes.TELEFONO_EMPRESA);
	}

	public DatosPruebaAtributos getTestTelefonoEmpresaNumericoMayor9(
			final EmpresaEntity datosEntradaTelefonoEmpresaNumericoMayor9) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaTelefonoEmpresaNumericoMayor9.getTelefonoEmpresa(), Funcionalidad.GESTION_EMPRESAS,
				Atributo.TELEFONO, 9);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_MAYOR_QUE_9 + " - "
				+ Mensajes.TELEFONO_NO_PUEDE_SER_MAYOR_QUE_9;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_MAYOR_9, Constantes.ERROR,
				datosEntradaTelefonoEmpresaNumericoMayor9.getTelefonoEmpresa(), Constantes.TELEFONO_EMPRESA);
	}

	public DatosPruebaAtributos getTestTelefonoEmpresaCorrectoNumerico(
			final EmpresaEntity datosEntradaTelefonoEmpresa) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(datosEntradaTelefonoEmpresa.getTelefonoEmpresa());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_TELEFONO_CORRECTO, Constantes.EXITO,
				datosEntradaTelefonoEmpresa.getTelefonoEmpresa(), Constantes.TELEFONO_EMPRESA);

	}
}
