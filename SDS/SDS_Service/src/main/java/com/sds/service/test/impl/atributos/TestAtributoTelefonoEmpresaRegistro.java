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
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosAcentos;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosBlank;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCaracteresEspeciales;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosCorrectoAlfanumerico;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEnhe;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosEspacios;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMayor;
import com.sds.service.util.validaciones.pruebas.ValidacionesAtributosMenor;

public class TestAtributoTelefonoEmpresaRegistro {

	private final ValidacionesAtributosBlank validacionesAtributosBlank;
	private final ValidacionesAtributosEnhe validacionesAtributosEnhe;
	private final ValidacionesAtributosAcentos validacionesAtributosAcentos;
	private final ValidacionesAtributosCaracteresEspeciales validacionesAtributosCaracteresEspeciales;
	private final ValidacionesAtributosEspacios validacionesAtributosEspacios;
	private final ValidacionesAtributosMenor validacionesAtributosMenor;
	private final ValidacionesAtributosMayor validacionesAtributosMayor;
	private final ValidacionesAtributosCorrectoAlfanumerico validacionesAtributosCorrectoAlfanumerico;
	private final CrearDatosPruebaAtributos crearDatosPruebaAtributos;

	public TestAtributoTelefonoEmpresaRegistro() {
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

	public DatosPruebaAtributos getTestRegistroTelefonoEmpresaVacio(
			final Registro datosEntradaRegistroTelefonoEmpresaVacio) throws ParseException {

		final String resultadoObtenido = validacionesAtributosBlank.comprobarAtributoBlank(
				datosEntradaRegistroTelefonoEmpresaVacio.getDatosEmpresa().getTelefonoEmpresa(),
				Funcionalidad.REGISTRAR, Atributo.TELEFONO);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_VACIO + " - " + Mensajes.TELEFONO_NO_PUEDE_SER_VACIO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.VACIO, Constantes.ERROR,
				datosEntradaRegistroTelefonoEmpresaVacio.getDatosEmpresa().getTelefonoEmpresa(),
				Constantes.TELEFONO_EMPRESA);
	}

	public DatosPruebaAtributos getTestRegistroTelefonoEmpresaNumericoEnhe(
			final Registro datosEntradaRegistroTelefonoEmpresaNumericoEnhe) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEnhe.comprobarAtributoEnhe(
				datosEntradaRegistroTelefonoEmpresaNumericoEnhe.getDatosEmpresa().getTelefonoEmpresa(),
				Funcionalidad.REGISTRAR, Atributo.TELEFONO);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_NUMERICO_INCORRECTO + " - "
				+ Mensajes.TELEFONO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ENHE, Constantes.ERROR,
				datosEntradaRegistroTelefonoEmpresaNumericoEnhe.getDatosEmpresa().getTelefonoEmpresa(),
				Constantes.TELEFONO_EMPRESA);

	}

	public DatosPruebaAtributos getTestRegistroTelefonoEmpresaNumericoAcentos(
			final Registro datosEntradaRegistroTelefonoEmpresaNumericoAcentos) throws ParseException {

		final String resultadoObtenido = validacionesAtributosAcentos.comprobarAtributoAcentos(
				datosEntradaRegistroTelefonoEmpresaNumericoAcentos.getDatosEmpresa().getTelefonoEmpresa(),
				Funcionalidad.REGISTRAR, Atributo.TELEFONO);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_NUMERICO_INCORRECTO + " - "
				+ Mensajes.TELEFONO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ACENTOS, Constantes.ERROR,
				datosEntradaRegistroTelefonoEmpresaNumericoAcentos.getDatosEmpresa().getTelefonoEmpresa(),
				Constantes.TELEFONO_EMPRESA);

	}

	public DatosPruebaAtributos getTestRegistroTelefonoEmpresaNumericoCaracteresEspeciales(
			final Registro datosEntradaRegistroTelefonoEmpresaNumericoCaracteresEspeciales) throws ParseException {

		final String resultadoObtenido = validacionesAtributosCaracteresEspeciales
				.comprobarAtributoCaracteresEspeciales(datosEntradaRegistroTelefonoEmpresaNumericoCaracteresEspeciales
						.getDatosEmpresa().getTelefonoEmpresa(), Funcionalidad.REGISTRAR, Atributo.TELEFONO);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_NUMERICO_INCORRECTO + " - "
				+ Mensajes.TELEFONO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_CARACTERES_ESPECIALES, Constantes.ERROR,
				datosEntradaRegistroTelefonoEmpresaNumericoCaracteresEspeciales.getDatosEmpresa().getTelefonoEmpresa(),
				Constantes.TELEFONO_EMPRESA);
	}

	public DatosPruebaAtributos getTestRegistroTelefonoEmpresaAlfanumericoEspacios(
			final Registro datosEntradaRegistroTelefonoEmpresaAlfanumericoEspacios) throws ParseException {

		final String resultadoObtenido = validacionesAtributosEspacios.comprobarAtributoEspacios(
				datosEntradaRegistroTelefonoEmpresaAlfanumericoEspacios.getDatosEmpresa().getTelefonoEmpresa(),
				Funcionalidad.REGISTRAR, Atributo.TELEFONO);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_NUMERICO_INCORRECTO + " - "
				+ Mensajes.TELEFONO_NO_PUEDE_CONTENER_MAS_QUE_NUMEROS;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_ESPACIOS, Constantes.ERROR,
				datosEntradaRegistroTelefonoEmpresaAlfanumericoEspacios.getDatosEmpresa().getTelefonoEmpresa(),
				Constantes.TELEFONO_EMPRESA);
	}

	public DatosPruebaAtributos getTestRegistroTelefonoEmpresaNumericoMenor9(
			final Registro datosEntradaRegistroTelefonoEmpresaNumericoMenor9) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMenor.comprobarAtributoMenor(
				datosEntradaRegistroTelefonoEmpresaNumericoMenor9.getDatosEmpresa().getTelefonoEmpresa(),
				Funcionalidad.REGISTRAR, Atributo.TELEFONO, 9);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_MENOR_QUE_9 + " - "
				+ Mensajes.TELEFONO_NO_PUEDE_SER_MENOR_QUE_9;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_MENOR_9, Constantes.ERROR,
				datosEntradaRegistroTelefonoEmpresaNumericoMenor9.getDatosEmpresa().getTelefonoEmpresa(),
				Constantes.TELEFONO_EMPRESA);
	}

	public DatosPruebaAtributos getTestRegistroTelefonoEmpresaNumericoMayor9(
			final Registro datosEntradaRegistroTelefonoEmpresaNumericoMayor9) throws ParseException {

		final String resultadoObtenido = validacionesAtributosMayor.comprobarAtributoMayor(
				datosEntradaRegistroTelefonoEmpresaNumericoMayor9.getDatosEmpresa().getTelefonoEmpresa(),
				Funcionalidad.REGISTRAR, Atributo.TELEFONO, 9);

		final String resultadoEsperado = CodigosMensajes.TELEFONO_MAYOR_QUE_9 + " - "
				+ Mensajes.TELEFONO_NO_PUEDE_SER_MAYOR_QUE_9;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_MAYOR_9, Constantes.ERROR,
				datosEntradaRegistroTelefonoEmpresaNumericoMayor9.getDatosEmpresa().getTelefonoEmpresa(),
				Constantes.TELEFONO_EMPRESA);
	}

	public DatosPruebaAtributos getTestRegistroTelefonoEmpresaCorrectoNumerico(
			final Registro datosEntradaRegistroTelefonoEmpresa) {

		final String resultadoObtenido = validacionesAtributosCorrectoAlfanumerico
				.comprobarAtributoCorrectoAlfanumerico(
						datosEntradaRegistroTelefonoEmpresa.getDatosEmpresa().getTelefonoEmpresa());

		final String resultadoEsperado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;

		return crearDatosPruebaAtributos.createDatosPruebaAtributos(resultadoObtenido, resultadoEsperado,
				DefinicionPruebas.NUMERICO_TELEFONO_CORRECTO, Constantes.EXITO,
				datosEntradaRegistroTelefonoEmpresa.getDatosEmpresa().getTelefonoEmpresa(),
				Constantes.TELEFONO_EMPRESA);

	}
}
